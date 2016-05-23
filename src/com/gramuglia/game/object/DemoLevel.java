package com.gramuglia.game.object;

//
// Class to represent an example level
//
public class DemoLevel extends Level {

	// File name for enemies
	private String demon = "res/demon.png";
	private String imp = "res/imp.png";
	
	/**/
	/*
	public DemoLevel(String texture, int size)
	
	NAME
		public DemoLevel(String texture, int size) - constructor of DemoLevel
	
	SYNOPSIS
		public DemoLevel(String texture, int size)
		
		String texture - File name of wall texture
		int size - size of wall texture
	
	DESCRIPTION
		Create the demo level.
	
	RETURNS
		A DemoLevel object
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public DemoLevel(String texture, int size) {
		// Create Level
		super(texture, size);
		
		// Create walls
		addWall(new Wall(-2, 2.5, 0, texture, size));
		addWall(new Wall(-1, 2.5, 0, texture, size));
		//addWall(new Wall(0, 2.5, 0, texture, size));
		//addWall(new Wall(1, 2.5, 0, texture, size));
		addWall(new Wall(2, 2.5, 0, texture, size));

		addWall(new Wall(-2.5, -2, 90, texture, size));
		addWall(new Wall(-2.5, -1, 90, texture, size));
		addWall(new Wall(-2.5, 0, 90, texture, size));
		addWall(new Wall(-2.5, 1, 90, texture, size));
		addWall(new Wall(-2.5, 2, 90, texture, size));

		addWall(new Wall(-2, -2.5, 180, texture, size));
		addWall(new Wall(-1, -2.5, 180, texture, size));
		addWall(new Wall(0, -2.5, 180, texture, size));
		addWall(new Wall(1, -2.5, 180, texture, size));
		addWall(new Wall(2, -2.5, 180, texture, size));

		addWall(new Wall(2.5, -2, 270, texture, size));
		addWall(new Wall(2.5, -1, 270, texture, size));
		//addWall(new Wall(2.5, 0, 270, texture, size));
		//addWall(new Wall(2.5, 1, 270, texture, size));
		addWall(new Wall(2.5, 2, 270, texture, size));
		
		
		addWall(new Wall(-0.5, 3, 90, texture, size));
		addWall(new Wall(2+-0.5, 3, 270, texture, size));
		
		addWall(new Wall(5+-0.5, 3, 90, texture, size));
		addWall(new Wall(5+2+-0.5, 3, 270, texture, size));
		
		
		addWall(new Wall(-2, 6+2.5, 0, texture, size));
		addWall(new Wall(-1, 6+2.5, 0, texture, size));
		addWall(new Wall(0, 6+2.5, 0, texture, size));
		addWall(new Wall(1, 6+2.5, 0, texture, size));
		addWall(new Wall(2, 6+2.5, 0, texture, size));

		addWall(new Wall(-2.5, 6+-2, 90, texture, size));
		addWall(new Wall(-2.5, 6+-1, 90, texture, size));
		addWall(new Wall(-2.5, 6+0, 90, texture, size));
		addWall(new Wall(-2.5, 6+1, 90, texture, size));
		addWall(new Wall(-2.5, 6+2, 90, texture, size));

		addWall(new Wall(-2, 6+-2.5, 180, texture, size));
		addWall(new Wall(-1, 6+-2.5, 180, texture, size));
		//addWall(new Wall(0, 6+-2.5, 180, texture, size));
		//addWall(new Wall(1, 6+-2.5, 180, texture, size));
		addWall(new Wall(2, 6+-2.5, 180, texture, size));

		addWall(new Wall(2.5, 6+-2, 270, texture, size));
		//addWall(new Wall(2.5, 6+-1, 270, texture, size));
		//addWall(new Wall(2.5, 6+0, 270, texture, size));
		addWall(new Wall(2.5, 6+1, 270, texture, size));
		addWall(new Wall(2.5, 6+2, 270, texture, size));
		

		addWall(new Wall(3, 1.5, 0, texture, size));
		addWall(new Wall(3, -0.5, 180, texture, size));
		
		addWall(new Wall(3, 5+1.5, 0, texture, size));
		addWall(new Wall(3, 5+-0.5, 180, texture, size));
		
		
		addWall(new Wall(6+-2, 2.5, 0, texture, size));
		//addWall(new Wall(6+-1, 2.5, 0, texture, size));
		//addWall(new Wall(6+0, 2.5, 0, texture, size));
		addWall(new Wall(6+1, 2.5, 0, texture, size));
		addWall(new Wall(6+2, 2.5, 0, texture, size));

		addWall(new Wall(6+-2.5, -2, 90, texture, size));
		addWall(new Wall(6+-2.5, -1, 90, texture, size));
		//addWall(new Wall(6+-2.5, 0, 90, texture, size));
		//addWall(new Wall(6+-2.5, 1, 90, texture, size));
		addWall(new Wall(6+-2.5, 2, 90, texture, size));

		addWall(new Wall(6+-2, -2.5, 180, texture, size));
		addWall(new Wall(6+-1, -2.5, 180, texture, size));
		addWall(new Wall(6+0, -2.5, 180, texture, size));
		addWall(new Wall(6+1, -2.5, 180, texture, size));
		addWall(new Wall(6+2, -2.5, 180, texture, size));

		addWall(new Wall(6+2.5, -2, 270, texture, size));
		addWall(new Wall(6+2.5, -1, 270, texture, size));
		addWall(new Wall(6+2.5, 0, 270, texture, size));
		addWall(new Wall(6+2.5, 1, 270, texture, size));
		addWall(new Wall(6+2.5, 2, 270, texture, size));
		
		
		
		addWall(new Wall(6+-2, 6+2.5, 0, texture, size));
		addWall(new Wall(6+-1, 6+2.5, 0, texture, size));
		addWall(new Wall(6+0, 6+2.5, 0, texture, size));
		addWall(new Wall(6+1, 6+2.5, 0, texture, size));
		addWall(new Wall(6+2, 6+2.5, 0, texture, size));

		addWall(new Wall(6+-2.5, 6+-2, 90, texture, size));
		//addWall(new Wall(6+-2.5, 6+-1, 90, texture, size));
		//addWall(new Wall(6+-2.5, 6+0, 90, texture, size));
		addWall(new Wall(6+-2.5, 6+1, 90, texture, size));
		addWall(new Wall(6+-2.5, 6+2, 90, texture, size));

		addWall(new Wall(6+-2, 6+-2.5, 180, texture, size));
		//addWall(new Wall(6+-1, 6+-2.5, 180, texture, size));
		//addWall(new Wall(6+0, 6+-2.5, 180, texture, size));
		addWall(new Wall(6+1, 6+-2.5, 180, texture, size));
		addWall(new Wall(6+2, 6+-2.5, 180, texture, size));

		addWall(new Wall(6+2.5, 6+-2, 270, texture, size));
		addWall(new Wall(6+2.5, 6+-1, 270, texture, size));
		addWall(new Wall(6+2.5, 6+0, 270, texture, size));
		addWall(new Wall(6+2.5, 6+1, 270, texture, size));
		addWall(new Wall(6+2.5, 6+2, 270, texture, size));
		
		
		addWall(new Wall(2, 2, 0, imp, size));
		addWall(new Wall(8, 2, 270, imp, size));
		addWall(new Wall(8, 5, 270, imp, size));
		addWall(new Wall(4, 4, 90, imp, size));
		addWall(new Wall(0, 7, 0, demon, size));
		addWall(new Wall(-2, -1, 90, demon, size));
		addWall(new Wall(5, -1, 180, demon, size));
	}   
}
