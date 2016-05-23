<<<<<<< HEAD
=======
// Loads a texture and allows access to pixels

>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
package com.gramuglia.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

<<<<<<< HEAD
//
// Class to load and retrieve texture data
//
public class Texture {

	// The texture
	BufferedImage image = null;
	
	// The pixels of the texture
	private int[] pixels;
	
	/**/
	/*
	public Texture(String fileName, int size)
	
	NAME
		public Texture(String fileName, int size) - constructor of Texture
	
	SYNOPSIS
		public Texture(String fileName, int size)
		
		String fileName - Name of texture file
		int size - dimension of texture(textures are squares)
	
	DESCRIPTION
		Loads the texture.
	
	RETURNS
		A Texture object
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public Texture(String fileName, int size) {
		// Load the texture
=======
public class Texture {

	BufferedImage image = null;
	private int[] pixels;
	
	public Texture(String fileName, int size) {
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
		try {
		    image = ImageIO.read(new File(fileName));
		    
			pixels = image.getRGB(0, 0, size, size, null, 0, image.getWidth());
		} catch (IOException e) {
			System.out.println("Error " + fileName + " not found.");
		}
	}
	
<<<<<<< HEAD
	/**/
	/*
	public int getPixelColor(int pixelIndex)
	
	NAME
		public int getPixelColor(int pixelIndex) - Retrieve a pixel color
	
	SYNOPSIS
		public int getPixelColor(int pixelIndex)
		
		int pixelIndex - the pixel
	
	DESCRIPTION
		Retrieve the pixel color.
	
	RETURNS
		int - the pixel's color
	
	AUTHOR
		Joe Gramuglia
	*/
	/**/
	public int getPixelColor(int pixelIndex) {
		// Return nothing if the texture didn't load
=======
	public int getPixelColor(int pixelIndex) {
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
		if (pixels == null) {
			return 0;
		}
		
<<<<<<< HEAD
		// Return nothing if the pixel doesn't exist
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
		if (pixelIndex < 0 || pixelIndex >= pixels.length) {
			return 0;
		}
		
<<<<<<< HEAD
		// Return the pixel's color
=======
>>>>>>> bbf0205244b7183f6a30dfb4438931d527bf123e
		return pixels[pixelIndex];
	}
}
