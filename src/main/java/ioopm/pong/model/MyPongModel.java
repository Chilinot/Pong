package ioopm.pong.model;

import java.awt.*;
import java.util.Set;

public class MyPongModel implements PongModel {

	public MyPongModel(String leftPlayer, String rightPlayer) {

	}

	@Override
	public void compute(Set<Input> input, long delta_t) {

	}

	@Override
	public int getBarPos(BarKey k) {
		return 0;
	}

	@Override
	public int getBarHeight(BarKey k) {
		return 0;
	}

	@Override
	public Point getBallPos() {
		return null;
	}

	@Override
	public String getMessage() {
		return null;
	}

	@Override
	public String getScore(BarKey k) {
		return null;
	}

	@Override
	public Dimension getFieldSize() {
		return null;
	}
}
