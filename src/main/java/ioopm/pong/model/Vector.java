package ioopm.pong.model;

import java.awt.Point;

/**
 * 
 * Lame class corresponding to a vector with associated vector operations.
 * @author TheGrandmother and Chilinot
 *
 */
public class Vector {
	
	private double x;
	private double y;
	
	
	/**
	 * Creates a new vector.
	 * @param x The x component.
	 * @param y The y component.
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Adds two vectors together
	 * 
	 * @param u Some arbitary vector
	 * @param v Another equally arbitrary vector
	 * @return The sum of the two vectors.
	 */
	public static Vector add(Vector u, Vector v) {
		return new Vector(v.getX()+u.getX(),v.getY()+u.getY() );
	}
	
	/**
	 * Subtracts one vector from another
	 * 
	 * @param u A vector
	 * @param v A vector
	 * @return returns the difference between u and v
	 */
	public static Vector subtract(Vector u, Vector v) {
		return new Vector(u.getX()-v.getX(),u.getY()-v.getY() );
	}
	
	/**
	 * Computes the dot product of two vectors
	 * @param u A vector
	 * @param v Another vector
	 * @return The dot product of u and v.
	 */
	public static double dotProduct(Vector u, Vector v) {
		return u.getX() * v.getX() + u.getY() * v.getY();
	}
	
	/**
	 * Multiplies a vector with a scalar.
	 * @param s A scalar
	 * @param v A vector
	 * @return A vector corresponding to v multiplied by s
	 */
	public static Vector scalarMultiply(double s, Vector v){
		return new Vector(v.getX()*s,v.getY()*s);
	}
	
	/**
	 * Multiplies the vector by a scalar
	 * 
	 * @param s a scalar
	 */
	public void scalarMultiply(double s){
		this.x *= s;
		this.y *= s;
	}
	
	/**
	 * Reflects the vector against another vector
	 * @param normal The vector against which the reflection is performed.
	 */
	public void reflect(Vector normal) {
		normal.normalize();
		Vector lol = subtract(scalarMultiply(2*dotProduct(normal, this), normal), this);
		this.x = lol.getX();
		this.y = lol.getY();
	}
	
	/**
	 * Inverts the vector
	 */
	public void invert() {
		this.scalarMultiply(-1D);
	}
	
	/**
	 * Gets the length of the vector
	 * @return The length of the vector
	 */
	public double getLength() {
		return Math.sqrt(this.x * this.x + this.y * this.y);
	}
	
	/**
	 * Sets the magnitude of the vector to be s. 
	 * Performing both a normalization and a scalar multiplication.
	 * @param s The new desired magnitude of the vector.
	 */
	public void setMagnitude(double s){
		this.normalize();
		this.scalarMultiply(s);
	}
	
	/**
	 * Normalizes the vector.
	 */
	public void normalize() {
		double length = this.getLength();
		this.x /= length;
		this.y /= length;
	}
	
	/**
	 * Sets the x component of the vector.
	 * @param x the new x component of the vector
	 */
	public void setX(double x) {
		this.x = x;
	}
	
	/**
	 * Sets the y component of the vector.
	 * @param y the new y component of the vector
	 */
	public void setY(double y) {
		this.y = y;
	}
	
	/**
	 * Retrieves the x component of the vector
	 * @return the x component of the vector
	 */
	public double getX() {
		return this.x;
	}
	
	/**
	 * Retrieves the y component of the vector
	 * @return the y component of the vector
	 */
	public double getY() {
		return this.y;
	}
	
	/**
	 * Assigns both x and y.
	 * @param x the new x component
	 * @param y the new y component
	 */
	public void set(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	public static Vector pointToVector(Point p){
		return new Vector(p.x,p.y);
		
	}
}
