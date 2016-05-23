package com.gramuglia.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		try {
		    image = ImageIO.read(new File(fileName));
		    
			pixels = image.getRGB(0, 0, size, size, null, 0, image.getWidth());
		} catch (IOException e) {
			System.out.println("Error " + fileName + " not found.");
		}
	}
	
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
		if (pixels == null) {
			return 0;
		}
		
		// Return nothing if the pixel doesn't exist
		if (pixelIndex < 0 || pixelIndex >= pixels.length) {
			return 0;
		}
		
		// Return the pixel's color
		return pixels[pixelIndex];
	}
}
