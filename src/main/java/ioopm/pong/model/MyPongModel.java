package ioopm.pong.model;

import java.awt.*;
import java.util.Set;

public class MyPongModel implements PongModel {

	private final static Dimension FIELD_SIZE = new Dimension(1000, 2000);

	private final String LEFT_PLAYERNAME;
	private final String RIGHT_PLAYERNAME;
	
	private int left_barpos = 0;
	private int right_barpos = 0;

	private final Point  ball           = new Point((int) FIELD_SIZE.getWidth() / 2, (int) (FIELD_SIZE.getHeight() / 2));
	private final Vector ball_direction = new Vector(Math.random(), Math.random());

	public MyPongModel(String leftPlayer, String rightPlayer) {
		this.LEFT_PLAYERNAME = leftPlayer;
		this.RIGHT_PLAYERNAME = rightPlayer;
	}

	@Override
	public void compute(Set<Input> input, long delta_t) {
		for(Input in : input) {
			switch(in.key) {
				case LEFT:
					switch(in.dir) {
						case UP:
							this.left_barpos -= 1;
							break;
						
						case DOWN:
							this.left_barpos += 1;
							break;
					}
					break;
					
				case RIGHT:
					switch(in.dir) {
						case UP:
							this.right_barpos -= 1;
							break;
						
						case DOWN:
							this.right_barpos += 1;
							break;
					}
					break;
			}
		}
		
		//TODO move ball.
	}

	@Override
	public int getBarPos(BarKey k) {
		switch(k) {
			case LEFT:
				return this.left_barpos;
			case RIGHT:
				return this.right_barpos;
		}
		
		// This can never be reached.
		return 0;
	}

	@Override
	public int getBarHeight(BarKey k) {
		return 20;
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
		return "0";
	}

	@Override
	public Dimension getFieldSize() {
		return FIELD_SIZE;
	}
}
