package ioopm.pong.model;

import java.awt.*;
import java.util.Set;

public class MyPongModel implements PongModel {

	private final static Dimension FIELD_SIZE = new Dimension(2000, 1000);

	private final String LEFT_PLAYERNAME;
	private final String RIGHT_PLAYERNAME;
	
	private int left_score  = 0;
	private int right_score = 0;

	private int left_barheight  = 200;
	private int right_barheight = 200;
	
	private int left_barpos  = left_barheight / 2;
	private int right_barpos = right_barheight / 2;
	
	private final int ball_speed = 20;
	private final int bar_speed = 20;
	private final int aim_sensitivity = 50;
	
	private final Point  ball           = new Point((int) FIELD_SIZE.getWidth() / 2, (int) (FIELD_SIZE.getHeight() / 2));
	private final Vector ball_direction = new Vector(Math.random() * 2 - 1, Math.random() * 2 - 1);

	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.LEFT_PLAYERNAME = leftPlayer;
		this.RIGHT_PLAYERNAME = rightPlayer;
		
		ball_direction.normalize();
		ball_direction.scalarMultiply(ball_speed);
	}

	@Override
	public void compute(Set<Input> input, long delta_t) {

		int left_top = left_barpos - left_barheight / 2;
		int left_bot = left_top + left_barheight;

		int right_top = right_barpos - right_barheight / 2;
		int right_bot = right_top + right_barheight;
		
		for(Input in : input) {
			switch(in.key) {
				case LEFT:
					switch(in.dir) {
						case UP:
							if(left_top > 0) {
								this.left_barpos -= bar_speed;
							}
							break;
						
						case DOWN:
							if(left_bot < FIELD_SIZE.getHeight()) {
								this.left_barpos += bar_speed;
							}
							break;
					}
					break;
					
				case RIGHT:
					switch(in.dir) {
						case UP:
							if(right_top > 0) {
								this.right_barpos -= bar_speed;
							}
							break;
						
						case DOWN:
							if(right_bot < FIELD_SIZE.getHeight()) {
								this.right_barpos += bar_speed;
							}
							break;
					}
					break;
			}
		}

		horizontalCollisionHandling();
		
		verticalCollisonHandling();
		
		// Move ball according to direction vector.
		ball.setLocation(ball.getX() + ball_direction.getX(), ball.getY() + ball_direction.getY());
	}

	private void horizontalCollisionHandling(){
		
		int left_top = left_barpos - left_barheight / 2;
		int left_bot = left_top + left_barheight;

		int right_top = right_barpos - right_barheight / 2;
		int right_bot = right_top + right_barheight;
		
		// Reflect vector against walls
		if(ball.getX() < 0) {
			// Hit left wall
			ball.setLocation(0, ball.getY());
			ball_direction.reflect(new Vector(1,0));
			ball_direction.invert();
			
			if(ball.getY() >= left_top && ball.getY() <= left_bot) {
				// Hit the bar
				//TODO direct ball vector according to bar movement.
				paddleBounce(true);
			}
			else {
				this.right_score += 1;
			}
		}
		else if(ball.getX() > FIELD_SIZE.getWidth()) {
			// Hit right wall
			ball.setLocation(FIELD_SIZE.getWidth(), ball.getY());
			ball_direction.reflect(new Vector(-1,0));
			ball_direction.invert();
			
			if(ball.getY() >= right_top && ball.getY() <= right_bot) {
				// Hit the bar
				//TODO direct ball vector according to bar movement.
				paddleBounce(false);
			}
			else {
				this.left_score += 1;
			}
		}
	}
	
	private void verticalCollisonHandling(){
		// Reflect vector against roof and floor.
		if(ball.getY() < 0) {
			// Hit ceiling
			ball.setLocation(ball.getX(), 0);
			ball_direction.reflect(new Vector(0,1));
			ball_direction.invert();
		}
		else if(ball.getY() > FIELD_SIZE.getHeight()) {
			// Hit floor
			ball.setLocation(ball.getX(), FIELD_SIZE.getHeight());
			ball_direction.reflect(new Vector(0,-1));
			ball_direction.invert();
		}
	}
	
	private void paddleBounce(boolean left_side){
		int paddle_center = left_side ? left_barpos : right_barpos;
		Vector return_vector;
		Vector reflection_point;
		if(left_side){
			reflection_point = new Vector(-aim_sensitivity,0);
		}else{
			reflection_point = new Vector(aim_sensitivity,0);
		}
		
		//Vector realtive_vector = new Vector(0,ball.y-paddle_center); 
		
		return_vector = Vector.subtract(new Vector(0,ball.y-paddle_center), reflection_point);
		return_vector.normalize();
		ball_direction.setX(return_vector.getX());
		ball_direction.setY(return_vector.getY());
		ball_direction.scalarMultiply(ball_speed);
		
	}

	@Override
	public int getBarPos(BarKey k) {
		switch(k) {
			case LEFT:
				return this.left_barpos;
			case RIGHT:
				return this.right_barpos;
		}
		
		// This dead code.
		return 0;
	}

	@Override
	public int getBarHeight(BarKey k) {
		switch(k) {
			case LEFT:
				return left_barheight;
			case RIGHT:
				return right_barheight;
		}
		
		// This is dead code.
		return 0;
	}

	@Override
	public Point getBallPos() {
		return this.ball;
	}

	@Override
	public String getMessage() {
		//TODO fix getMessage()
		return "FOOBAR";
	}

	@Override
	public String getScore(BarKey k) {
		switch(k) {
			case LEFT:
				return String.valueOf(this.left_score);
			case RIGHT:
				return String.valueOf(this.right_score);
		}
		
		return "ERROR";
	}

	@Override
	public Dimension getFieldSize() {
		return FIELD_SIZE;
	}
}