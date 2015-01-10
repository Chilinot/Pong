import ioopm.pong.model.Vector;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VectorTest {
	
	@Test
	public void testInit() {
		Vector v = new Vector(12.2, 23.3);
		assertTrue(v.getX() == 12.2 && v.getY() == 23.3);
	}
	
	@Test
	public void testAdd() {
		Vector v1 = new Vector(1, 2);
		Vector v2 = new Vector(2, 1);
		
		Vector v3 = Vector.add(v1, v2);
		
		assertEquals(3D, v3.getX(), 0D);
		assertEquals(3D, v3.getY(), 0D);
	}
	
	@Test
	public void testSubtract() {
		Vector v1 = new Vector(1, 2);
		Vector v2 = new Vector(2, 1);

		Vector v3 = Vector.subtract(v1, v2);

		assertEquals(-1D, v3.getX(), 0D);
		assertEquals(1D, v3.getY(), 0D);
	}
	
	
}
