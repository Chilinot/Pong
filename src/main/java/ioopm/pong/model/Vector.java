package ioopm.pong.model;

import java.awt.Point;

public class Vector {
	
	private double x;
	private double y;
	
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public static Vector add(Vector u, Vector v) {
		return new Vector(v.getX()+u.getX(),v.getY()+u.getY() );
	}
	
	public static Vector subtract(Vector u, Vector v) {
		return new Vector(u.getX()-v.getX(),u.getY()-v.getY() );
	}
	
	public static double dotProduct(Vector u, Vector v) {
		return u.getX() * v.getX() + u.getY() * v.getY();
	}

	public static Vector scalarMultiply(double s, Vector v){
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
	
	public void invert() {
		this.scalarMultiply(-1D);
	}
	
	public double getLength() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	public void setMagnitude(double s){
		this.normalize();
		this.scalarMultiply(s);
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
	
	public void set(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector pointToVector(Point p){
		return new Vector(p.x,p.y);
		
	}
}
