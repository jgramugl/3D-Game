package com.gramuglia.game.graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;

import com.gramuglia.game.object.GameObject;
import com.gramuglia.game.object.Wall;

public class Camera extends GameObject {
	private BufferedImage image;
	private int[] pixels;
	private Canvas canvas;
	private int width;
	private int height;

	Random random = new Random();
	
	double time = 0;
	int size = 2000;
	
	Wall wall = new Wall(0, 1, 0, "res/dolphin2000.png", size);
	//Wall wall2 = new Wall(1, 1, 0, "res/wallTexture.png", size);
	

	Wall wall3 = new Wall(-0.5, 1, 0, "res/dolphin2000.png", size);
	Wall wall4 = new Wall(0.5, 1, 0, "res/dolphin2000.png", size);
	Wall wall5 = new Wall(1.5, 1, 0, "res/dolphin2000.png", size);

	public Camera(Canvas canvas, int width, int height) {
		super(0, 0, 0);
		this.canvas = canvas;
		this.width = width;
		this.height = height;

		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void render() {
		BufferStrategy bs = canvas.getBufferStrategy();

		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}
		
		clear();
		
		renderFloor();
		
		renderCeiling();
		
		renderWalls(wall);
		//renderWalls(wall2);
		
		temp(wall3);
		temp(wall4);
		temp(wall5);
		
		Graphics g = bs.getDrawGraphics();

		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);

		g.dispose();
		bs.show();
	}

	private void renderWalls(Wall wall) {
		
		// The differences between the wall and the camera
		double xDifference = wall.getX() - this.getX();
		double yDifference = wall.getY() - this.getY();
		double degreeDifference = Math.toRadians(this.getDegrees() - wall.getDegrees());
		
		// The sine and cosine of the camera
		double sine = Math.sin(Math.toRadians(this.getDegrees()));
		double cosine = Math.cos(Math.toRadians(this.getDegrees()));
		
		
		
		
		
		// Left Edge
		double leftX = xDifference - 0.5 * Math.cos(wall.getDegrees());
		double leftY = yDifference + 0.5 * Math.sin(wall.getDegrees());
		
		double leftXCoord = leftX * cosine + leftY * sine;
		double leftYCoord = leftY * cosine - leftX * sine;
		
		double leftWallSize = width / (leftYCoord + 1.0);
		
		double leftWallWidth = leftWallSize * Math.cos(degreeDifference);
		
		int leftEdge = (int) (width / 2) - (int) (leftWallWidth / 2) + (int) (leftWallSize * leftXCoord);
		
		
		
		// Right Edge
		double rightX = xDifference + 0.5 * Math.cos(wall.getDegrees());
		double rightY = yDifference - 0.5 * Math.sin(wall.getDegrees());
		
		double rightXCoord = rightX * cosine + rightY * sine;
		double rightYCoord = rightY * cosine - rightX * sine;
		
		double rightWallSize = width / (rightYCoord + 1.0);
		
		double rightWallWidth = rightWallSize * Math.cos(degreeDifference);
		
		int rightEdge = (int) (width / 2) - (int) (rightWallWidth / 2) + (int) (rightWallSize * rightXCoord);
		
		
		//System.out.println(leftWallSize + ", " + rightWallSize);
		
		
		
		
		
		// The coordinates of the wall relative to the camera
		double xCoord = xDifference * cosine + yDifference * sine;
		double yCoord = yDifference * cosine - xDifference * sine;

		// Don't render if the wall is behind the camera
		if (yCoord < 0) {
			return;
		}
		
		// Don't render walls outside field of view
		if (xCoord != 0) {
			if (yCoord / xCoord < 1 && yCoord / xCoord > -1) {
				return;
			}
		}
		
		// The size the wall should be at a given depth
		double wallSize = width / (yCoord + 1.0);
		
		// The height of the wall when accounting for rotation
		double startHeight = (width / (yCoord + 1.0 + 0.5 * Math.sin(degreeDifference)));
		double endHeight = (width / (yCoord + 1.0 - 0.5 * Math.sin(degreeDifference)));
		double heightDifference = startHeight - endHeight;
		
		
		
		
		/*
		// X coordinates of the ends of the wall
		double leftEdgeX = xCoord + 0.5 * -Math.cos(degreeDifference);
		double rightEdgeX = xCoord + 0.5 * Math.cos(degreeDifference);
		
		// Y coordinates of the ends of the wall
		double leftEdgeY = yCoord + 0.5 * Math.sin(degreeDifference);
		double rightEdgeY = yCoord + 0.5 * -Math.sin(degreeDifference);
		
		if (leftEdgeY < 0 || rightEdgeY < 0) {
			return;
		}
		
		// The spacing between each coordinate for both ends of the wall
		double leftWallSize = width / (leftEdgeY + 1.0);
		double rightWallSize = width / (rightEdgeY + 1.0);
		
		// The width the wall should be when centered at the ends of the wall
		double wallWidthLeft = leftWallSize * Math.cos(degreeDifference);
		double wallWidthRight = rightWallSize * Math.cos(degreeDifference);
		
		// The on screen locations of each end of the wall
		int leftSide = (int) (width / 2) - (int) (wallWidthLeft / 2) + (int) (leftWallSize * leftEdgeX);
		int rightSide = (int) (width / 2) - (int) (wallWidthRight / 2) + (int) (rightWallSize * rightEdgeX);
		*/
		// The width of the wall
		double wallWidth = rightEdge - leftEdge;//rightSide - leftSide;//(rightXCoord - leftXCoord) * wallSize;//3;//rightSide - leftSide;
		
		
		
		
		
		for (int x = 0; x < wallWidth; x++) {
			
			int xx = x + (int) (width / 2) - (int) (wallWidth / 2) + (int) (wallSize * xCoord);
			
			double wallHeight = startHeight - x / wallWidth * heightDifference;
			
			for (int y = 0; y < wallHeight; y++) {
				
				int yy = y + (int) (height / 2) - (int) (wallHeight / 2);
				
				if (yy < 0 || yy >= height) {
					continue;
				}
				
				if (xx < 0 || xx >= width) {
					continue;
				}
				
				int colorIndex = (int) (x / (wallWidth / size)) + (int) (y / (wallHeight / size)) * size;
				
				if (colorIndex >= 0 && colorIndex < wall.getSize() * wall.getSize()) {
					pixels[xx + yy * width] = wall.getPixelColor(colorIndex);//colorIndex * 256 * 16;
				}
				else {
					pixels[xx + yy * width] = colorIndex * 256 * 16;
				}
			}
		}
	}

	private void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	private void renderFloor() {
		for (int i = pixels.length / 2; i < pixels.length; i++) {
			pixels[i] = 0x666666;
		}
	}
	
	private void renderCeiling() {
		for (int i = 0; i < pixels.length / 2; i++) {
			pixels[i] = 0x999999;
		}
	}
	
	
private void temp(Wall wall) {
		
		// The differences between the wall and the camera
		double xDifference = wall.getX() - this.getX();
		double yDifference = wall.getY() - this.getY();
		
		// The sine and cosine of the camera
		double sine = Math.sin(Math.toRadians(this.getDegrees()));
		double cosine = Math.cos(Math.toRadians(this.getDegrees()));
		
		// The coordinates of the wall relative to the camera
		double xCoord = xDifference * cosine + yDifference * sine;
		double yCoord = yDifference * cosine - xDifference * sine;
		
		// Don't render if the wall is behind the camera
		if (yCoord < 0) {
			return;
		}
		
		// Don't render walls outside field of view
		if (xCoord != 0) {
			if (yCoord / xCoord < 1 && yCoord / xCoord > -1) {
				return;
			}
		}
		
		// The size the wall should be at a given depth
		double wallSize = width / (yCoord + 1.0);
		
		// The width of the wall
		double wallWidth = 3;
		
		
		for (int x = 0; x < wallWidth; x++) {
			
			int xx = x + (int) (width / 2) - (int) (wallWidth / 2) + (int) (wallSize * xCoord);
			
			double wallHeight = wallSize;
			
			for (int y = 0; y < wallHeight; y++) {
				
				int yy = y + (int) (height / 2) - (int) (wallHeight / 2);
				
				if (yy < 0 || yy >= height) {
					continue;
				}
				
				if (xx < 0 || xx >= width) {
					continue;
				}
				
				pixels[xx + yy * width] = 0xff00ff;
			}
		}
	}
}
