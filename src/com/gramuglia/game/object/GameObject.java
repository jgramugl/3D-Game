package com.gramuglia.game.object;

<<<<<<< HEAD
//
// Class to represent an object in the game
//
public class GameObject {
	// The location
	private double x = 0;
	private double y = 0;
	
	// The direction it's facing
	private double degrees = 0;
	
	/**/
	/*
	public GameObject(double x, double y, double degrees)
	
	NAME
		public GameObject(double x, double y, double degrees) - constructor of GameObject
	
	SYNOPSIS
		public GameObject(double x, double y, double degrees)
		
		double x - x coordinate
		double y - y coordinate
		double degrees - degrees of rotation
	
	DESCRIPTION
		Create a game object.
	
	RETURNS
		A GameObject object
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
public class GameObject {
	private double x = 0;
	private double y = 0;
	private double degrees = 0;
	
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public GameObject(double x, double y, double degrees) {
		this.x = x;
		this.y = y;
		this.degrees = degrees;
	}

<<<<<<< HEAD
	/**/
	/*
	public void translate(double xVelocity, double yVelocity)
	
	NAME
		public void translate(double xVelocity, double yVelocity) - Move the object
	
	SYNOPSIS
		public void translate(double xVelocity, double yVelocity)
		
		double xVelocity - the distance in the x direction to move the object
		double yVelocity - the distance in the y direction to move the object 
	
	DESCRIPTION
		Move the object.
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public void translate(double xVelocity, double yVelocity) {
		x += xVelocity;
		y += yVelocity;
	}

<<<<<<< HEAD
	/**/
	/*
	public void rotate(double angularVelocity)
	
	NAME
		public void rotate(double angularVelocity) - Rotate the object
	
	SYNOPSIS
		public void rotate(double angularVelocity)
		
		double angularVelocity - the amount of degrees to rotate the object
	
	DESCRIPTION
		Rotate the object.
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public void rotate(double angularVelocity) {
		this.degrees += angularVelocity;
	}
	
<<<<<<< HEAD
	/**/
	/*
	public void setX(double x)
	
	NAME
		public void setX(double x) - Set the x coordinate of the object
	
	SYNOPSIS
		public void setX(double x)
		
		double x - the new x coordinate
	
	DESCRIPTION
		Reposition the object along the x axis.
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public void setX(double x) {
		this.x = x;
	}
	
<<<<<<< HEAD
	/**/
	/*
	public void setY(double y)
	
	NAME
		public void setY(double y) - Set the y coordinate of the object
	
	SYNOPSIS
		public void setY(double y)
		
		double y - the new y coordinate
	
	DESCRIPTION
		Reposition the object along the y axis.
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public void setY(double y) {
		this.y = y;
	}
	
<<<<<<< HEAD
	/**/
	/*
	public void setDegrees(double degrees)
	
	NAME
		public void setDegrees(double degrees) - Set the degrees of the object
	
	SYNOPSIS
		public void setDegrees(double degrees)
		
		double degrees - the new degrees
	
	DESCRIPTION
		Redirect the object.
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public void setDegrees(double degrees) {
		this.degrees = degrees;
	}
	
<<<<<<< HEAD
	/**/
	/*
	public double getX()
	
	NAME
		public double getX() - Retrieve x coordinate
	
	SYNOPSIS
		public double getX()
	
	DESCRIPTION
		Retrieve x coordinate
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public double getX() {
		return x;
	}
	
<<<<<<< HEAD
	/**/
	/*
	public double getY()
	
	NAME
		public double getY() - Retrieve y coordinate
	
	SYNOPSIS
		public double getY()
	
	DESCRIPTION
		Retrieve y coordinate
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public double getY() {
		return y;
	}
	
<<<<<<< HEAD
	/**/
	/*
	public double getDegrees()
	
	NAME
		public double getDegrees() - Retrieve degrees
	
	SYNOPSIS
		public double getDegrees()
	
	DESCRIPTION
		Retrieve degrees
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public double getDegrees() {
		return degrees;
	}
}
