// Loads a texture and allows access to pixels

package com.gramuglia.game.graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Texture {

	BufferedImage image = null;
	private int[] pixels;
	
	public Texture(String fileName, int size) {
		try {
		    image = ImageIO.read(new File(fileName));
		    
			pixels = image.getRGB(0, 0, size, size, null, 0, image.getWidth());
		} catch (IOException e) {
			System.out.println("Error " + fileName + " not found.");
		}
	}
	
	public int getPixelColor(int pixelIndex) {
		if (pixels == null) {
			return 0;
		}
		
		if (pixelIndex < 0 || pixelIndex >= pixels.length) {
			return 0;
		}
		
		return pixels[pixelIndex];
	}
}
