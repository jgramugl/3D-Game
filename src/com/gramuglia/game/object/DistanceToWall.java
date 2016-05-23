package com.gramuglia.game.object;

import java.util.Comparator;

//
// Class to organize Camera to Wall data
//
public class DistanceToWall implements Comparator<DistanceToWall> {
	
	// Camera's distance to wall
	private double distance;
	
	// The wall
	private Wall wall;
	
	/**/
	/*
	public DistanceToWall(double distance, Wall wall)
	
	NAME
		public DistanceToWall(double distance, Wall wall) - constructor of DistanceToWall
	
	SYNOPSIS
		public DistanceToWall(double distance, Wall wall)
		
		double distance - Camera's distance to Wall
		Wall wall - the wall
	
	DESCRIPTION
		Create the distance to wall object.
	
	RETURNS
		A DistanceToWall object
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public DistanceToWall(double distance, Wall wall) {
		// Save parameters
		this.distance = distance;
		this.wall = wall;
	}

	/**/
	/*
	public DistanceToWall()
	
	NAME
		public DistanceToWall() - constructor of DistanceToWall
	
	SYNOPSIS
		public DistanceToWall()
	
	DESCRIPTION
		Create an empty distance to wall object.
	
	RETURNS
		A DistanceToWall object
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public DistanceToWall() {
		// TODO Auto-generated constructor stub
	}

	/**/
	/*
	public double getDistance()
	
	NAME
		public double getDistance() - Retrieve distance.
	
	SYNOPSIS
		public double getDistance()
	
	DESCRIPTION
		Retrieve distance.
	
	RETURNS
		double - distance
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public double getDistance() {
		return distance;
	}
	
	/**/
	/*
	public Wall getWall()
	
	NAME
		public Wall getWall() - Retrieve wall
	
	SYNOPSIS
		public Wall getWall()
	
	DESCRIPTION
		Retrieve wall.
	
	RETURNS
		Wall - the wall
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public Wall getWall() {
		return wall;
	}

	/**/
	/*
	public int compare(DistanceToWall arg0, DistanceToWall arg1)
	
	NAME
		public int compare(DistanceToWall arg0, DistanceToWall arg1) - Compare two walls
	
	SYNOPSIS
		public int compare(DistanceToWall arg0, DistanceToWall arg1)
		
		DistanceToWall arg0 - a wall
		DistanceToWall arg1 - a wall
	
	DESCRIPTION
		Compare the distance from the camera of two walls
	
	RETURNS
		int - the difference between the distances
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	@Override
	public int compare(DistanceToWall arg0, DistanceToWall arg1) {
		// TODO Auto-generated method stub
		return (int)(arg0.getDistance()*100 - arg1.getDistance()*100);
	}
}
