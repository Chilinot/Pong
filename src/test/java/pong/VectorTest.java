package pong;

import static org.junit.Assert.*;
import ioopm.pong.model.Vector;

import org.junit.Test;

public class VectorTest {

	private final double test_delta = 0.01;
	
	@Test
	public void testAdd() {
		Vector a = new Vector(0,1);
		Vector b = new Vector(1,0);
		assertTrue("Addition failed :/", vectorEqual(Vector.add(a, b),1,1));
		
		a = new Vector(1,0);
		b = new Vector(0,1);
		assertTrue("Addition not commutative:/", vectorEqual(Vector.add(a, b),1,1));
	}
	
	@Test
	public void testSubtract(){
		Vector a = new Vector(0,1);
		Vector b = new Vector(1,0);
		assertTrue("Subtraction failed :/", vectorEqual(Vector.subtract(a, b),-1,1));
		
		a = new Vector(1,1);
		b = new Vector(0,0);
		assertTrue("Subtraction has dumb ordering:/", vectorEqual(Vector.subtract(a, b),1,1));
		
		a = new Vector(0,0);
		b = new Vector(1,1);
		assertTrue("Subtraction has dumb ordering:/", vectorEqual(Vector.subtract(a, b),-1,-1));
	}
	
	@Test
	public void testDotProduct(){
		Vector a = new Vector(0,1);
		Vector b = new Vector(1,0);
		assertTrue("Dot product failed :/", Vector.dotProduct(a, b) == 0);
		
		a = new Vector(1,1);
		b = new Vector(1,0);
		assertTrue("Dot product failed :/", Vector.dotProduct(a, b) == 1);
		
		a = new Vector(3,2);
		b = new Vector(3,2);
		assertTrue("Dot product failed :/", Vector.dotProduct(a, b) == (3*3+2*2));
		
		a = new Vector(7,3);
		b = new Vector(-4.2,18);
		assertTrue("Dot product failed :/", Vector.dotProduct(a, b) == (7*(-4.2)+3*18));
	}
	
	@Test
	public void testScalarProduct(){
		Vector a = new Vector(1,1);
		double s = 1;
		assertTrue("Scalar product failed :/", vectorEqual(Vector.scalarMultiply(s, a),1,1));
		
		a = new Vector(1,1);
		s = 0;
		assertTrue("Scalar product failed :/", vectorEqual(Vector.scalarMultiply(s, a),0,0));
		
		a = new Vector(1,1);
		s = -1;
		assertTrue("Scalar product failed :/", vectorEqual(Vector.scalarMultiply(s, a),-1,-1));
	}
	
	@Test
	public void testReflect(){
		Vector a = new Vector(0,0);
		Vector normal = new Vector(1,0);
		a.reflect(normal);
		assertTrue("Reflect 1 failed :/", vectorEqual(a,0,0));
		
		a = new Vector(1,1);
		normal = new Vector(1,1);
		a.reflect(normal);
		assertTrue("Reflect 2 failed :/", vectorEqual(a,1,1));
		
		a = new Vector(1,0);
		normal = new Vector(0,1);
		a.reflect(normal);
		assertTrue("Reflect 3 failed :/", vectorEqual(a,-1,0));
		
	}
	
	@Test
	public void testInvert(){
		Vector a = new Vector(0,0);
		a.invert();
		assertTrue("Invert 1 failed", vectorEqual(a, 0, 0));
		
		a = new Vector(1,1);
		a.invert();
		assertTrue("Invert 1 failed", vectorEqual(a, -1, -1));
	}
	
	@Test
	public void testGetLength(){
		Vector a = new Vector(0,0);
		assertTrue("Getlength 1 failed", Math.abs(a.getLength() - 0) < test_delta);
		
		a = new Vector(1,1);
		assertTrue("Get length 2 failed", Math.abs(a.getLength() - Math.sqrt(2)) < test_delta);
		
	}
	
	@Test
	public void testSetMagnitude(){
		//ASSUMES THAT GET LENGTH IS WORKING
		Vector a = new Vector(1,1);
		a.setMagnitude(3);
		assertTrue("set magnitude 1 failed", Math.abs(a.getLength() - 3) < test_delta);
		
		a = new Vector(-71,1.91);
		a.setMagnitude(138);
		assertTrue("set magnitude 2 failed", Math.abs(a.getLength() - 138) < test_delta);
	}
	
	@Test
	public void testNormalize(){
		//ASSUMES THAT GET LENGTH IS WORKING
		Vector a = new Vector(1,1);
		a.normalize();
		assertTrue("normalize 1 failed", Math.abs(a.getLength() - 1) < test_delta);
		
		a = new Vector(-4564646,1324);
		a.normalize();
		assertTrue("normalize 2 failed", Math.abs(a.getLength() - 1) < test_delta);		
		
		a = new Vector(0.18,-0.000001);
		a.normalize();
		assertTrue("normalize 3 failed", Math.abs(a.getLength() - 1) < test_delta);
		
	}
	
	
	private boolean vectorEqual(Vector a, double x, double y){
		
		return (Math.abs(a.getX() - x) < test_delta) && (Math.abs(a.getY() - y) < test_delta);
	} 
	
	private boolean vectorEqual(Vector a, Vector b){
		double delta = 0.01;
		return (Math.abs(a.getX() - b.getX()) < delta) && (Math.abs(a.getY() - b.getY()) < delta);

	}

}
