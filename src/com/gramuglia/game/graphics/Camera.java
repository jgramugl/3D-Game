package com.gramuglia.game.graphics;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Collections;
import java.util.Vector;

import com.gramuglia.game.object.DistanceToWall;
import com.gramuglia.game.object.GameObject;
import com.gramuglia.game.object.Level;
import com.gramuglia.game.object.Wall;

//
// Class to render the game scene
//
public class Camera extends GameObject {

	// The image to render on
	private BufferedImage image;

	// The pixels of the image
	private int[] pixels;

	// The canvas of the game
	private Canvas canvas;

	// The width of the canvas
	private int width;

	// The height of the canvas
	private int height;

	// The level the camera is in
	Level level;

	// The size of objects in the world in pixels
	int size;

	/**/
	/*
	 * public Camera(Canvas canvas, int width, int height, Level level, int
	 * size)
	 * 
	 * NAME public Camera(Canvas canvas, int width, int height, Level level, int
	 * size) - constructor of Camera
	 * 
	 * SYNOPSIS public Camera(Canvas canvas, int width, int height, Level level,
	 * int size)
	 * 
	 * Canvas canvas - canvas to draw on int width - width of canvas int height
	 * - height of canvas Level level - level the camera is in int size - size
	 * of game objects in pixels
	 * 
	 * DESCRIPTION Create the camera.
	 * 
	 * RETURNS A Camera object
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public Camera(Canvas canvas, int width, int height, Level level, int size) {
		// Camera's location and direction
		super(0, 0, 0);

		// Save parameters
		this.canvas = canvas;
		this.width = width;
		this.height = height;
		this.level = level;
		this.size = size;

		// Create an image and get the pixels from it
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	/**/
	/*
	 * public void render()
	 * 
	 * NAME public void render() - Render the scene
	 * 
	 * SYNOPSIS public void render()
	 * 
	 * DESCRIPTION Clears the screen. Renders the floor. Renders the ceiling.
	 * Renders every wall from farthest to closest.
	 * 
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public void render() {
		// Get the windows buffer strategy
		BufferStrategy bs = canvas.getBufferStrategy();

		// Create a buffer strategy if there is none
		if (bs == null) {
			canvas.createBufferStrategy(3);
			return;
		}

		// Clear the screen
		clear();

		// Render the floor
		renderFloor();

		// Render the ceiling
		renderCeiling();

		// Store the distance of every wall
		Vector<DistanceToWall> distances = new Vector<DistanceToWall>();

		// Calculate the distance of every wall
		for (int i = 0; i < level.getNumberOfWalls(); i++) {
			double x = level.getWall(i).getX() - this.getX();
			double y = level.getWall(i).getY() - this.getY();
			double distance = Math.sqrt(x * x + y * y);

			distances.add(new DistanceToWall(distance, level.getWall(i)));
		}

		// Sort the walls based on distance from the camera
		Collections.sort(distances, new DistanceToWall());

		// Render every wall in the proper order
		for (int i = distances.size() - 1; i >= 0; i--) {
			renderWall(distances.get(i).getWall());
		}

		// Get the graphics of the window
		Graphics g = bs.getDrawGraphics();

		// Draw the image to the graphics
		g.drawImage(image, 0, 0, canvas.getWidth(), canvas.getHeight(), null);

		// Release graphics
		g.dispose();

		// Display the graphic
		bs.show();
	}

	/**/
	/*
	 * private void renderWall(Wall wall)
	 * 
	 * NAME private void renderWall(Wall wall) - Render a wall
	 * 
	 * SYNOPSIS private void renderWall(Wall wall)
	 * 
	 * Wall wall - the wall to render
	 * 
	 * DESCRIPTION Determines if the wall should be rendered. Calculates where
	 * the wall should be rendered. Changes every pixel to the appropriate
	 * texture color.
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private void renderWall(Wall wall) {

		// The differences between the wall and the camera
		double xDifference = wall.getX() - this.getX();
		double yDifference = wall.getY() - this.getY();
		double degreeDifference = Math.toRadians(this.getDegrees() - wall.getDegrees());

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
			if (yCoord / xCoord < 0.5 && yCoord / xCoord > -0.5) {
				return;
			}
		}

		// The height of the wall when accounting for rotation
		double startHeight = (width / (yCoord + 1.0 + 0.5 * Math.sin(degreeDifference)));
		double endHeight = (width / (yCoord + 1.0 - 0.5 * Math.sin(degreeDifference)));
		double heightDifference = startHeight - endHeight;

		// The coordinates of the left edge of the wall
		double leftX = xDifference - 0.5 * Math.cos(Math.toRadians(wall.getDegrees()));
		double leftY = yDifference - 0.5 * Math.sin(Math.toRadians(wall.getDegrees()));

		// The coordinates of the right edge of the wall
		double rightX = xDifference + 0.5 * Math.cos(Math.toRadians(wall.getDegrees()));
		double rightY = yDifference + 0.5 * Math.sin(Math.toRadians(wall.getDegrees()));

		// The appropriate coordinates for the left edge when accounting for
		// rotation
		double leftXCoord = leftX * cosine + leftY * sine;
		double leftYCoord = leftY * cosine - leftX * sine;

		// The appropriate coordinates for the right edge when accounting for
		// rotation
		double rightXCoord = rightX * cosine + rightY * sine;
		double rightYCoord = rightY * cosine - rightX * sine;

		// The side of a wall if it was positioned at each edge
		double leftWallSize = width / (leftYCoord + 1.0);
		double rightWallSize = width / (rightYCoord + 1.0);

		// The horizontal position of each edge on the screen
		double leftPosition = (int) (width / 2) + (int) (leftWallSize * leftXCoord);
		double rightPosition = (int) (width / 2) + (int) (rightWallSize * rightXCoord);

		// The width of the wall
		double wallWidth = rightPosition - leftPosition;

		// Loop through every pixel of the wall's width
		for (int x = 0; x < wallWidth; x++) {

			// The pixel's x coordinate
			int xx = (int) (x + leftPosition);

			// The height of the wall for this pixel
			double wallHeight = startHeight - x / wallWidth * heightDifference;

			// Loop through every pixel of the wall's height
			for (int y = 0; y < wallHeight; y++) {

				// The pixel's y coordinate
				int yy = y + (int) (height / 2) - (int) (wallHeight / 2);

				// Don't render outside the window
				if (yy < 0 || yy >= height || xx < 0 || xx >= width) {
					continue;
				}

				// The location of the appropriate color from the texture
				int colorIndex = (int) (x / (wallWidth / size)) + (int) (y / (wallHeight / size)) * size;

				// If the color can't be located on the texture
				if (colorIndex >= 0 && colorIndex < wall.getSize() * wall.getSize()) {
					// Get the texture color
					int pixelColor = wall.getPixelColor(colorIndex);

					// Don't render if the color is transparent
					if (pixelColor < 0) {
						// Set the pixel on the screen to the color
						pixels[xx + yy * width] = pixelColor;
					}
				} else {
					// Set a default color
					pixels[xx + yy * width] = 0xff00ff;
				}
			}
		}
	}

	/**/
	/*
	 * private void clear()
	 * 
	 * NAME private void clear() - Resets the canvas
	 * 
	 * SYNOPSIS private void clear()
	 * 
	 * DESCRIPTION Reset every pixel on the canvas
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private void clear() {
		// For every pixel on the canvas
		for (int i = 0; i < pixels.length; i++) {
			// Set the pixel to black
			pixels[i] = 0;
		}
	}

	/**/
	/*
	 * private void renderFloor()
	 * 
	 * NAME private void renderFloor() - Render the floor
	 * 
	 * SYNOPSIS private void renderFloor()
	 * 
	 * DESCRIPTION Change every pixel below the horizon a color
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private void renderFloor() {
		// For the upper half of the screen
		for (int i = pixels.length / 2; i < pixels.length; i++) {
			// Set every pixel
			pixels[i] = 0x707070;
		}
	}

	/**/
	/*
	 * private void renderCeiling()
	 * 
	 * NAME private void renderCeiling() - Render the ceiling
	 * 
	 * SYNOPSIS private void renderCeiling()
	 * 
	 * DESCRIPTION Change every pixel above the horizon a color
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	private void renderCeiling() {
		// For the lower half of the screen
		for (int i = 0; i < pixels.length / 2; i++) {
			// Set every pixel
			pixels[i] = 0x383838;
		}
	}
}
