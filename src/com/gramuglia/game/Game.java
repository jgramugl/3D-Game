package com.gramuglia.game;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Collections;
import java.util.Vector;

import javax.swing.JFrame;

import com.gramuglia.game.graphics.Camera;
import com.gramuglia.game.object.DemoLevel;
import com.gramuglia.game.object.DistanceToWall;
import com.gramuglia.game.object.Level;

public class Game implements Runnable {
	private final double ASPECT_RATIO = 9.0 / 16.0;
	private final int WIDTH = 1920 / 2;
	private final int HEIGHT = (int) (WIDTH * ASPECT_RATIO);
	private final double SCALE = 1;

	private Thread thread;
	private boolean running = false;
	
	private Camera camera;
	private Canvas canvas;
	private InputHandler input;
	
	Level level;
	
	// Speed of the camera
	double moveSpeed = 0.05;
	
	double previousX;
	double previousY;
	double previousDegrees;
	
	public Game() {
		// Create the size of the window
		Dimension dimension = new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE));
		
		// Create a canvas the size of the window 
		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		
		// Create level
		int size = 128;
		String texture = "res/BrickWall128x128.png";
		level = new DemoLevel(texture, size);
		
		// Create a camera
		camera = new Camera(canvas, (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE), level, size);
		
		// Save previous state of camera
		previousX = camera.getX();
		previousY = camera.getY();
		previousDegrees = camera.getDegrees();
		
		// Create a window
		JFrame window = createWindow(canvas);
		
		// Create an input handler
		input = new InputHandler(window, (int)(window.getWidth()), (int) (window.getHeight()));

		// Add the input handler to the canvas
		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		
		// Center the mouse inside the window
		try {
			Robot robot = new Robot();
			robot.mouseMove(window.getX() + (int)(window.getWidth()) / 2, window.getY() + (int)(window.getHeight()) / 2);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Focus the canvas 
		canvas.requestFocus();
	}

	public void start() {
		// Run the game
		running = true;

		// Create a new thread for the game
		thread = new Thread(this);
		
		// Start the game
		thread.start();
	}

	public void stop() {
		// Stop the game
		running = false;

		// Join the thread
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		// Number of frames
		int frames = 0;
		
		// Amount of time that wasn't used
		double unusedTime = 0;
		
		// The last time the system time was retrieved
		long previousTime = System.nanoTime();
		
		// The number of times to update per second
		double secondsPerUpdate = 1 / 60.0;
		
		// The number of times the game has been updated
		int updateCount = 0;
		
		while (running) {
			// The current time of the system
			long currentTime = System.nanoTime();
			
			// The amount of time since the last time
			long difference = currentTime - previousTime;
			
			// Save the time
			previousTime = currentTime;
			
			// Keep the time difference within a second
			if (difference < 0) {
				difference = 0;
			}
			if (difference > 1000000000) {
				difference = 1000000000;
			}
			
			// Store the time
			unusedTime += difference / 1000000000.0;
			
			// Update x amount of times per second
			boolean updated = false;
			
			// If enough time has passed
			while(unusedTime > secondsPerUpdate) {
				// Update the game
				update();
				
				// Remove a section of time from the whole
				unusedTime -= secondsPerUpdate;
				
				// The game was updated
				updated = true;
				
				// Account for the update
				updateCount++;
				
				// Every 60 updates
				if (updateCount % 60 == 0) {
					// Display frames per second
					System.out.println(frames + " fps");
					
					// Reset
					frames = 0;
					updateCount = 0;
				}
			}
			
			// If the game was updated
			if (updated) {
				// Render the game
				render();
				
				// Account for the frame swap
				frames++;
			}
		}
	}

	
	private void update() {
		// Check user input
		updateCamera();
		
		// Resolve collisions
		resolveCollisions();
	}
	
	private void updateCamera() {
		
		// Save previous location
		previousX = camera.getX();
		previousY = camera.getY();
		previousDegrees = camera.getDegrees();
		
		
		// Keyboard rotation controls
		if (input.getRotateLeft()) {
			camera.rotate(1);
		}
		if (input.getRotateRight()) {
			camera.rotate(-1);
		}
		
		
		// Mouse rotation control
		//camera.rotate(-input.getX() / 5.0);

		// Used for camera movement
		double sine = Math.sin(Math.toRadians(camera.getDegrees()));
		double cosine = Math.cos(Math.toRadians(camera.getDegrees()));
		
		// Keyboard controls for movement
		if (input.getForward()) {
			camera.translate(-moveSpeed * sine, moveSpeed * cosine);
		}
		if (input.getBackward()) {
			camera.translate(moveSpeed * sine, -moveSpeed * cosine);
		}
		if (input.getStrafeLeft()) {
			camera.translate(-moveSpeed * cosine, -moveSpeed * sine);
		}
		if (input.getStrafeRight()) {
			camera.translate(moveSpeed * cosine, moveSpeed * sine);
		}
	}
	
	private void resolveCollisions() {
		/*
		// If camera didn't move, don't bother checking for collision
		if (camera.getX() == previousX && camera.getY() == previousY) {
			return;
		}
		
		// Store the distance of every wall
		Vector<DistanceToWall> distances = new Vector<DistanceToWall>();
		
		// Calculate the distance of every wall
		for (int i = 0; i < level.getNumberOfWalls(); i++) {
			double x = level.getWall(i).getX() - camera.getX();
			double y = level.getWall(i).getY() - camera.getY();
			double distance = Math.sqrt(x * x + y * y);

			distances.add(new DistanceToWall(distance, level.getWall(i)));
		}

		// Sort the walls based on distance from the camera
		Collections.sort(distances, new DistanceToWall());
		
		// Calculate direction player moved in (slope of the players movement)
		
		double cameraX = camera.getX() - previousX;
		double cameraY = camera.getY() - previousY;
		//double degreeDifference = camera.getDegrees() - previousDegrees;
		//y = mx + b
		// m = rise / run = yDifference / xDifference
		// b = y - mx
		
		
		double cameraSlope = cameraY / cameraX;
		double cameraIntercept = camera.getY() - cameraSlope * camera.getX();
		
		//System.out.println(cameraSlope);
		//System.out.println(cameraIntercept);
		
		// Check for collision with each wall
		for (int i = 0; i < distances.size(); i++) {
			if (distances.get(i).getDistance() > 0.6) {
				break;
			}
			
			
			// Calculate the slope of the wall
			
			double wallX = distances.get(i).getWall().getX();
			double wallY = distances.get(i).getWall().getY();
			
			// The coordinates of the left edge of the wall
			double leftX = wallX - 0.5 * Math.cos(Math.toRadians(distances.get(i).getWall().getDegrees()));
			double leftY = wallY - 0.5 * Math.sin(Math.toRadians(distances.get(i).getWall().getDegrees()));
			
			// The coordinates of the right edge of the wall
			double rightX = wallX + 0.5 * Math.cos(Math.toRadians(distances.get(i).getWall().getDegrees()));
			double rightY = wallY + 0.5 * Math.sin(Math.toRadians(distances.get(i).getWall().getDegrees()));
			
			double wallSlope = (rightY - leftY) / (rightX - leftX);
			double wallIntercept = wallX - wallSlope * wallY;
			
			// Check if the player's slope intercepts the wall's slope
			double x = (wallIntercept - cameraIntercept) / (cameraSlope - wallSlope);
			double y = wallSlope * wallX + wallIntercept;
			
			//System.out.println(wallSlope);
			//System.out.println(wallIntercept);
			//System.out.println(x + ", " + y);
			
			// Adjust the position of the player and break out of the loop
			double interceptDistance = Math.sqrt(x * x + y * y);
			double distanceToWall = interceptDistance - distances.get(i).getDistance();
			
			if (distanceToWall < moveSpeed) {
				camera.setX(x);
				camera.setY(y);
			}
			*/
			//System.out.println(distances.get(i).getWall().getX() + ", " + distances.get(i).getWall().getY() + " | " + distances.get(i).getDistance());
		}
		System.out.println("---------------------------------------------");
		
		
		/*if (camera.getX() > 1) {
			camera.setX(1);
		}*/
		
		// Save state of camera
		previousX = camera.getX();
		previousY = camera.getY();
		previousDegrees = camera.getDegrees();
	}

	private void render() {
		// Allow the camera to render
		camera.render();
	}

	private JFrame createWindow(Canvas canvas) {
		// Create a window
		JFrame window = new JFrame();
		
		// Title of window
		window.setTitle("3D Game");
		
		// Close the window when exit is pressed
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Create a blank image, create a cursor with that image, then add the cursor to the window 
		BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
		window.setCursor(blankCursor);
		
		// Add the canvas to the window
		window.add(canvas);
		
		// Fit the window to the canvas
		window.pack();
		
		// Center the window on the screen
		window.setLocationRelativeTo(null);
		
		// Make the window visible
		window.setVisible(true);
		
		// Return the window
		return window;
	}
}
