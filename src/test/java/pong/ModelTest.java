package pong;

import static org.junit.Assert.*;

import java.awt.Point;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;

import ioopm.pong.model.BarKey;
import ioopm.pong.model.Input;
import ioopm.pong.model.MyPongModel;
import ioopm.pong.model.Input.Dir;

import org.junit.Test;

public class ModelTest {
	
	MyPongModel test_model = null;

	@Test
	public void testMovePaddles() {
		setUpModel();
		int left_start_pos = test_model.getBarPos(BarKey.LEFT);
		
		int right_start_pos = test_model.getBarPos(BarKey.RIGHT);
		
		test_model.compute(makeInputSet(new Input(BarKey.LEFT, Dir.UP)), 1);
		
		assertTrue("Left paddle did not move up",test_model.getBarPos(BarKey.LEFT) < left_start_pos);
		assertEquals("The right bar moved when moving left bar",right_start_pos, test_model.getBarPos(BarKey.RIGHT), 0.001);
		test_model.compute(makeInputSet(new Input(BarKey.LEFT, Dir.DOWN), new Input(BarKey.LEFT, Dir.DOWN)), 1);
		assertTrue("Left paddle did not move down",test_model.getBarPos(BarKey.LEFT) > left_start_pos);
		assertEquals("The right bar moved when moving left bar",right_start_pos, test_model.getBarPos(BarKey.RIGHT), 0.001);
		
		left_start_pos = test_model.getBarPos(BarKey.LEFT);
		test_model.compute(makeInputSet(new Input(BarKey.RIGHT, Dir.UP)), 1);
		assertTrue("Right paddle did not move up",test_model.getBarPos(BarKey.RIGHT) < right_start_pos);
		assertEquals("The left bar moved when moving right bar",left_start_pos, test_model.getBarPos(BarKey.LEFT), 0.001);
		test_model.compute(makeInputSet(new Input(BarKey.RIGHT, Dir.DOWN), new Input(BarKey.RIGHT, Dir.DOWN)), 1);
		assertTrue("Right paddle did not move down",test_model.getBarPos(BarKey.RIGHT) > right_start_pos);
		assertEquals("The left bar moved when moving right bar",left_start_pos, test_model.getBarPos(BarKey.LEFT), 0.001);
		
	}
	
	
	@Test
	//We can only test if the ball moved at all due to encapsulation
	public void testMotion(){
		setUpModel();
		Point starting_position = test_model.getBallPos();
		test_model.compute(makeInputSet(new Input(BarKey.LEFT, Dir.UP)), 10);
		assertNotEquals("Ball did not move",starting_position, test_model.getBallPos());
	}
	

	
	
	private void setUpModel(){
		test_model = new MyPongModel("p1", "p2");
	}
	
	private HashSet<Input> makeInputSet(Input... a){
		return new HashSet<Input>(Arrays.asList(a));
	}
	
}
