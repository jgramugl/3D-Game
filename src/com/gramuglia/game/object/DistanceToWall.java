package com.gramuglia.game.object;

import java.util.Comparator;

public class DistanceToWall implements Comparator<DistanceToWall> {
	private double distance;
	private Wall wall;
	
	public DistanceToWall(double distance, Wall wall){
		this.distance = distance;
		this.wall = wall;
	}

	public DistanceToWall() {
		// TODO Auto-generated constructor stub
	}

	public double getDistance(){
		return distance;
	}
	
	public Wall getWall() {
		return wall;
	}

	@Override
	public int compare(DistanceToWall arg0, DistanceToWall arg1) {
		// TODO Auto-generated method stub
		return (int)(arg0.getDistance()*100 - arg1.getDistance()*100);
	}
}
