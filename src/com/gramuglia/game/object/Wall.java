package com.gramuglia.game.object;

import com.gramuglia.game.graphics.Texture;

<<<<<<< HEAD
//
// Class to represent a wall in the game
//
public class Wall extends GameObject {
	
	// The texture on the wall
	private Texture texture;
	
	// The size of the texture
	private int size;
	
	/**/
	/*
	public Wall(double x, double y, double degrees, String textureFile, int size)
	
	NAME
		public Wall(double x, double y, double degrees, String textureFile, int size) - constructor of Wall
	
	SYNOPSIS
		public Wall(double x, double y, double degrees, String textureFile, int size)
		
		double x - the x coordinate
		double y - the y coordinate
		double degrees - the degrees
		String textureFile - the file name of the wall texture
		int size - the pixel size of the wall texture
	
	DESCRIPTION
		Create a wall object.
	
	RETURNS
		A Wall object
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public Wall(double x, double y, double degrees, String textureFile, int size) {
		// Create a game object
		super(x, y, degrees);
		
		// Load the texture
		texture = new Texture(textureFile, size);
		
		// Save the size
		this.size = size;
	}
	
	/**/
	/*
	public int getPixelColor(int pixelIndex)
	
	NAME
		public int getPixelColor(int pixelIndex) - Retrieve the texture's pixel's color
	
	SYNOPSIS
		public int getPixelColor(int pixelIndex)
		
		int pixelIndex - the location of the pixel
	
	DESCRIPTION
		Retrieve the texture's pixel's color
	
	RETURNS
		int - the pixel's color
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
public class Wall extends GameObject {
	
	private Texture texture;
	private int size;
	
	public Wall(double x, double y, double degrees, String textureFile, int size) {
		super(x, y, degrees);
		
		texture = new Texture(textureFile, size);
		
		this.size = size;
	}
	
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public int getPixelColor(int pixelIndex) {
		return texture.getPixelColor(pixelIndex);
	}
	
<<<<<<< HEAD
	/**/
	/*
	public int getSize()
	
	NAME
		public int getSize() - Retrieve the size
	
	SYNOPSIS
		public int getSize()
	
	DESCRIPTION
		Retrieve the size.
	
	RETURNS
		int - the size
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
	public int getSize() {
		return size;
	}
}