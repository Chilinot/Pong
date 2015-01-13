package ioopm.pong.model;

import java.awt.*;
import java.util.Set;

/**
 * The worlds least playable pong...
 * @author TheGrandmother and Chilinot
 *
 */
public class MyPongModel implements PongModel {

	private final static Dimension FIELD_SIZE = new Dimension(2000, 1000);

	private final String LEFT_PLAYERNAME;
	private final String RIGHT_PLAYERNAME;
	
	private int level = 1;
	/**
	 * How much speed the ball gains each level.
	 */
	private double speed_increment = .1;
	/**
	 * How much speed the paddles gain each level.
	 */
	private double bar_speed_increment = .05;
	/**
	 * The time interval between level-ups
	 */
	private final int time_per_level = 3;
	private long level_timer;
	private boolean game_started = false;

	private final double aim_sensitivity = 150;
	
	private int left_score  = 0;
	private int right_score = 0;

	private int left_barheight  = 200;
	private int right_barheight = 200;
	
	private int left_barpos  = (int) FIELD_SIZE.getHeight() / 2;
	private int right_barpos = (int) FIELD_SIZE.getHeight() / 2;
	
	private double ball_speed = .5;
	private double bar_speed = 1;

	private final Vector ball          = new Vector((int) FIELD_SIZE.getWidth() / 2, (int) (FIELD_SIZE.getHeight() / 2));
	private final Vector ball_direction = new Vector(Math.random()>.5 ? 1 : -1, (Math.random() * 2 - 1)/2);

	
	/**
	 * 
	 * Creates a new MyPongModel. Player names are not yet implemented
	 * 
	 * @param leftPlayer Name for the left player
	 * @param rightPlayer Name for the right player
	 */
	@Deprecated
	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.LEFT_PLAYERNAME = leftPlayer;
		this.RIGHT_PLAYERNAME = rightPlayer;
		
		
	}
	
	/**
	 * Creates a new pong model.
	 */
	public MyPongModel() {
		ball_direction.normalize();
		ball_direction.scalarMultiply(ball_speed);
		LEFT_PLAYERNAME ="";
		RIGHT_PLAYERNAME= "";
	}

	@Override
	public void compute(Set<Input> input, long delta_t) {
		if(!game_started){
			game_started = true;
			level_timer = System.currentTimeMillis();
		}
		
		
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
								this.left_barpos -= bar_speed*delta_t;
								
							}
							break;
						
						case DOWN:
							if(left_bot < FIELD_SIZE.getHeight()) {
								this.left_barpos += bar_speed*delta_t;
							}
							break;
					}
					break;
					
				case RIGHT:
					switch(in.dir) {
						case UP:
							if(right_top > 0) {
								this.right_barpos -= bar_speed*delta_t;
							}
							break;
						
						case DOWN:
							if(right_bot < FIELD_SIZE.getHeight()) {
								this.right_barpos += bar_speed*delta_t;
							}
							break;
					}
					break;
			}
		}

		horizontalCollisionHandling();
		
		verticalCollisonHandling();
		
		levelHandler();
		
		// Move ball according to direction vector.
		ball.set(ball.getX() + ball_direction.getX()*delta_t, ball.getY() + ball_direction.getY()*delta_t);
	}

	/**
	 *Handles the collision when the balls hits either the left end or the right end of the playing field.
	 *Checks whether the ball hits the paddle or not. If the ball hits the paddle {@link MyPongModel#paddleBounce(boolean, double)} will be called
	 *and if the ball hits the edge {@link MyPongModel#death(boolean)} will be called.
	 */
	private void horizontalCollisionHandling(){
		
		int left_top = left_barpos - left_barheight / 2;
		int left_bot = left_top + left_barheight;

		int right_top = right_barpos - right_barheight / 2;
		int right_bot = right_top + right_barheight;
		
		// Reflect vector against walls
		if(ball.getX() < 0) {
			// Hit left wall
			
			if(ball.getY() >= left_top && ball.getY() <= left_bot) {
				paddleBounce(true,aim_sensitivity);
			}
			else {
				death(true);
			}
		}
		else if(ball.getX() > FIELD_SIZE.getWidth()) {
			// Hit right wall
			
			if(ball.getY() >= right_top && ball.getY() <= right_bot) {

				paddleBounce(false,aim_sensitivity);
			}
			else {
				death(false);
			}
		}
	}
	
	/**
	 * Bounces the ball against the top or bottom edge of the playing field.
	 */
	private void verticalCollisonHandling(){
		// Reflect vector against roof and floor.
		if(ball.getY() < 0) {
			// Hit ceiling
			ball.set(ball.getX(), 0);
			ball_direction.reflect(new Vector(0,1));
			ball_direction.invert();
		}
		else if(ball.getY() > FIELD_SIZE.getHeight()) {
			// Hit floor
			ball.set(ball.getX(), FIELD_SIZE.getHeight());
			ball_direction.reflect(new Vector(0,-1));
			ball_direction.invert();
		}
	}
	
	/**
	 * Handles the bouncing of the ball against the paddle.
	 * @param left_side If the ball hit the left side of the field.
	 * @param aim_sensitivity How sensitive the aim should be. A lower value leads to a higher sensitivity.
	 */
	private void paddleBounce(boolean left_side,double aim_sensitivity){
		int paddle_center = left_side ? left_barpos : right_barpos;
		Vector return_vector;
		Vector reflection_point;
		if(left_side){
			reflection_point = new Vector(-aim_sensitivity,0);
		}else{
			reflection_point = new Vector(aim_sensitivity,0);
		}
		
		
		return_vector = Vector.subtract(new Vector(0,ball.getY()-paddle_center), reflection_point);
		return_vector.normalize();
		ball_direction.setX(return_vector.getX());
		ball_direction.setY(return_vector.getY());
		ball_direction.setMagnitude(ball_speed);	
	}
	
	/**
	 * 
	 * Handles the assignment of points, resizing of paddles and the resetting of the ball.
	 * 
	 * @param left_side If the ball exited the left side or not.
	 */
	private void death(boolean left_side){
		if(left_side){
			right_score++;
			left_barheight += 20;
			right_barheight -= 20;
		}else{
			left_score++;
			right_barheight += 20;
			left_barheight -= 20;
		}
		
		
		ball_direction.set(Math.random()>.5 ? 1 : -1, (Math.random() * 2 - 1)/2);
		ball_direction.setMagnitude(ball_speed);
		ball.set(FIELD_SIZE.getWidth() / 2,(FIELD_SIZE.getHeight() / 2));
		
		
		
		
	}
	
	/**
	 * This function tests whether the next level has been reached and takes action accordingly.
	 */
	public void levelHandler(){
		if(game_started && ((System.currentTimeMillis()-level_timer)/1000.) >= time_per_level){
			level++;
			ball_speed += speed_increment;
			bar_speed += bar_speed_increment;
			level_timer = System.currentTimeMillis();
		}
		
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
		return new Point((int)this.ball.getX(),(int)this.ball.getY());
	}

	@Override
	public String getMessage() {
		return "Level: " + level;
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