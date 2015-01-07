package ioopm.pong.model;

public class Vector {
	
	private double x;
	private double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(Vector v) {
		this.x += v.getX();
		this.y += v.getY();
	}
	
	public void subtract(Vector v) {
		this.x -= v.getX();
		this.y -= v.getY();
	}
	
	public double scalar(Vector v) {
		return this.x * v.getX() + this.y * v.getY();
	}
	
	public void reflect(Vector normal) {
		0
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
