package com.gramuglia.game.object;

import com.gramuglia.game.graphics.Texture;

public class Wall extends GameObject {
	
	private Texture texture;
	private int size;
	
	public Wall(double x, double y, double degrees, String textureFile, int size) {
		super(x, y, degrees);
		
		texture = new Texture(textureFile, size);
		
		this.size = size;
	}
	
	public int getPixelColor(int pixelIndex) {
		return texture.getPixelColor(pixelIndex);
	}
	
	public int getSize() {
		return size;
	}
}