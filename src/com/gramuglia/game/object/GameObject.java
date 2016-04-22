package com.gramuglia.game.object;

public class GameObject {
	private double x = 0;
	private double y = 0;
	private double degrees = 0;
	
	public GameObject(double x, double y, double degrees) {
		this.x = x;
		this.y = y;
		this.degrees = degrees;
	}

	public void translate(double xVelocity, double yVelocity) {
		x += xVelocity;
		y += yVelocity;
	}

	public void rotate(double angularVelocity) {
		this.degrees += angularVelocity;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setDegrees(double degrees) {
		this.degrees = degrees;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getDegrees() {
		return degrees;
	}
}
