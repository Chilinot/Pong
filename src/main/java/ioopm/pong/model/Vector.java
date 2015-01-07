package ioopm.pong.model;

public class Vector {
	
	private double x;
	private double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector add(Vector u, Vector v) {
		return new Vector(v.getX()+u.getX(),v.getY()+u.getY() );
	}
	
	public Vector subtract(Vector u, Vector v) {
		return new Vector(u.getX()-v.getX(),u.getY()-v.getY() );
	}
	
	public double dotProduct(Vector u, Vector v) {
		return u.getX() * v.getX() + u.getY() * v.getY();
	}

	public Vector scalarMultiply(double s, Vector v){
		return new Vector(v.getX()*s,v.getY()*s);
	}
	
	public void scalarMultiply(double s){
		this.x *= s;
		this.y *= s;
	}
	
	public void reflect(Vector normal) {
		Vector lol = subtract(scalarMultiply(2*dotProduct(normal, this), normal), this);
		this.x = lol.getX();
		this.y = lol.getY();
	}
	
	public double getLength() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public void normalize() {
		double length = this.getLength();
		this.x /= length;
		this.y /= length;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public double getX() {
		return this.x;
	}
	
	public double getY() {
		return this.y;
	}
}
