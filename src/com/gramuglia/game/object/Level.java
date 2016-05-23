package com.gramuglia.game.object;

import java.util.Vector;

<<<<<<< HEAD
//
// Class to represent a level in the game
//
public class Level {
	
	// The walls
	private Vector<Wall> walls;
	
	// The file name of the wall texture
	protected String texture;
	
	// The pixel size of the textures
	protected int size;
	
	/**/
	/*
	public Level(String texture, int size)
	
	NAME
		public Level(String texture, int size) - constructor of Level
	
	SYNOPSIS
		public Level(String texture, int size)
		
		String texture - the file name of the wall texture
		int size - the pixel size of the wall texture
	
	DESCRIPTION
		Create a level object.
	
	RETURNS
		A Level object
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public Level(String texture, int size) {
		// Save parameters
		this.texture = texture;
		this.size = size;
		
		// Create walls
		walls = new Vector<Wall>();
	}
	
	/**/
	/*
	public void addWall(Wall wall)
	
	NAME
		public void addWall(Wall wall) - Add a wall to the level
	
	SYNOPSIS
		public void addWall(Wall wall)
		
		Wall wall - the wall to add
	
	DESCRIPTION
		Add a wall to the level.
	
	RETURNS
		Void
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
public class Level {
	
	private Vector<Wall> walls;
	protected String texture;
	protected int size;
	protected String george = "res/Monkey128x128.png";
	
	public Level(String texture, int size) {
		this.texture = texture;
		this.size = size;
		
		walls = new Vector<Wall>();
	}
	
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public void addWall(Wall wall) {
		walls.add(wall);
	}
	
<<<<<<< HEAD
	/**/
	/*
	public void getWall(int i)
	
	NAME
		public void getWall(int i) - Get a wall based order added
	
	SYNOPSIS
		public void getWall(int i)
		
		Wall wall - the wall to add
	
	DESCRIPTION
		Retrieve a wall from the level.
	
	RETURNS
		Wall - the wall
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public Wall getWall(int i) {
		return walls.get(i);
	}

<<<<<<< HEAD
	/**/
	/*
	public int getNumberOfWalls()
	
	NAME
		public int getNumberOfWalls() - Retrieve number of walls in the level
	
	SYNOPSIS
		public int getNumberOfWalls()
	
	DESCRIPTION
		Retrieve number of walls in the level.
	
	RETURNS
		int - the number of wall in the level
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public int getNumberOfWalls() {
		return walls.size();
	}
}
