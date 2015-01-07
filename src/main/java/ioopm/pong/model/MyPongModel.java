package ioopm.pong.model;

import java.awt.*;
import java.util.Set;

public class MyPongModel implements PongModel {

	private final static Dimension FIELD_SIZE = new Dimension(2000, 1000);

	private final String LEFT_PLAYERNAME;
	private final String RIGHT_PLAYERNAME;

	private int left_barheight  = 100;
	private int right_barheight = 100;
	
	private int left_barpos  = left_barheight / 2;
	private int right_barpos = right_barheight / 2;
	
	private final int BALL_SPEED = 3;
	
	private final Point  ball           = new Point((int) FIELD_SIZE.getWidth() / 2, (int) (FIELD_SIZE.getHeight() / 2));
	private final Vector ball_direction = new Vector(Math.random() * BALL_SPEED - BALL_SPEED / 2, Math.random() * BALL_SPEED - BALL_SPEED / 2);

	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.LEFT_PLAYERNAME = leftPlayer;
		this.RIGHT_PLAYERNAME = rightPlayer;
		
		ball_direction.normalize();
		//TODO multiply ball_direction with ball speed
	}

	@Override
	public void compute(Set<Input> input, long delta_t) {
		
		//TODO restrict movement of bars
		for(Input in : input) {
			switch(in.key) {
				case LEFT:
					switch(in.dir) {
						case UP:
							this.left_barpos -= 10;
							break;
						
						case DOWN:
							this.left_barpos += 10;
							break;
					}
					break;
					
				case RIGHT:
					switch(in.dir) {
						case UP:
							this.right_barpos -= 10;
							break;
						
						case DOWN:
							this.right_barpos += 10;
							break;
					}
					break;
			}
		}
		
		//TODO bounce ball
		
		if(ball.getX() <= 0) {
			// Hit left wall

		}
		
		ball.setLocation(ball.getX() + ball_direction.getX(), ball.getY() + ball_direction.getY());
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
		return "tjosan";
	}

	@Override
	public String getScore(BarKey k) {
		//TODO fix getScore()
		return "2";
	}

	@Override
	public Dimension getFieldSize() {
		return FIELD_SIZE;
	}
}