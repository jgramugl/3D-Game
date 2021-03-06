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

//
// Class to run and play the game
//
public class Game implements Runnable {

	// The pixel width to height ratio the window will be
	private final double ASPECT_RATIO = 9.0 / 16.0;

	// The width of the window
	private final int WIDTH = 1920 / 2;

	// The height of the window
	private final int HEIGHT = (int) (WIDTH * ASPECT_RATIO);

	// The thread the game runs on
	private Thread thread;

	// Used to determine if the game is running
	private boolean running = false;

	// The camera for the game
	private Camera camera;

	// The canvas to draw the game on
	private Canvas canvas;

	// The input handler for the game
	private InputHandler input;

	// The level for the game
	Level level;

	// Speed of the camera
	double moveSpeed = 0.05;

	// The last position of the camera
	double previousX;
	double previousY;

	/**/
	/*
	 * public Game()
	 * 
	 * NAME public Game() - Creates a game
	 * 
	 * SYNOPSIS public Game()
	 * 
	 * DESCRIPTION Create all aspects of the game
	 * 
	 * RETURNS Game object - constructor
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public Game() {
		// Create the size of the window
		Dimension dimension = new Dimension(WIDTH, HEIGHT);

		// Create a canvas the size of the window
		canvas = new Canvas();
		canvas.setPreferredSize(dimension);

		// Create level
		int size = 128;
		String texture = "res/BrickWall128x128.png";
		level = new DemoLevel(texture, size);

		// Create a camera
		camera = new Camera(canvas, WIDTH, HEIGHT, level, size);

		// Save previous state of camera
		previousX = camera.getX();
		previousY = camera.getY();

		// Create a window
		JFrame window = createWindow(canvas);

		// Create an input handler
		input = new InputHandler(window, (int) (window.getWidth()), (int) (window.getHeight()));

		// Add the input handler to the canvas
		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);

		// Center the mouse inside the window
		try {
			Robot robot = new Robot();
			robot.mouseMove(window.getX() + (int) (window.getWidth()) / 2,
					window.getY() + (int) (window.getHeight()) / 2);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Focus the canvas
		canvas.requestFocus();
	}

	/**/
	/*
	 * public void start()
	 * 
	 * NAME public void start() - Starts game
	 * 
	 * SYNOPSIS public void start()
	 * 
	 * DESCRIPTION Starts the game thread
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public void start() {
		// Run the game
		running = true;

		// Create a new thread for the game
		thread = new Thread(this);

		// Start the game
		thread.start();
	}

	/**/
	/*
	 * public void stop()
	 * 
	 * NAME public void stop() - Stops game
	 * 
	 * SYNOPSIS public void stop()
	 * 
	 * DESCRIPTION Stops the game thread
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
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

	/**/
	/*
	 * public void run()
	 * 
	 * NAME public void run() - Runs game
	 * 
	 * SYNOPSIS public void run()
	 * 
	 * DESCRIPTION Runs the game. Update and renders the game. Keeps track of
	 * frame rate.
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public void run() {
		// Number of frames
		int frames = 0;

		// Amount of time that hasn't been used
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
			while (unusedTime > secondsPerUpdate) {
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

	/**/
	/*
	 * public void update()
	 * 
	 * NAME public void update() - Update the game
	 * 
	 * SYNOPSIS public void update()
	 * 
	 * DESCRIPTION Update user input. Update collisions.
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private void update() {
		// Check user input
		updateCamera();

		// Check collisions
		resolveCollisions();
	}

	/**/
	/*
	 * public void updateCamera()
	 * 
	 * NAME public void updateCamera() - Update game camera
	 * 
	 * SYNOPSIS public void updateCamera()
	 * 
	 * DESCRIPTION Saves the last position of the camera. Rotates the camera.
	 * Moves the camera.
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private void updateCamera() {

		// Save previous positions data
		previousX = camera.getX();
		previousY = camera.getY();

		// Mouse rotation control
		camera.rotate(-input.getX() / 5.0);

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

	/**/
	/*
	 * public void resolveCollisions()
	 * 
	 * NAME public void resolveCollisions() - Adjust camera for collisions
	 * 
	 * SYNOPSIS public void resolveCollisions()
	 * 
	 * DESCRIPTION Moves the camera to the proper location outside of each
	 * wall's boundaries.
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private void resolveCollisions() {

		// Store the distance of every wall
		Vector<DistanceToWall> distances = new Vector<DistanceToWall>(level.getNumberOfWalls());

		// Calculate the distance of every wall
		for (int i = 0; i < level.getNumberOfWalls(); i++) {
			double x = level.getWall(i).getX() - camera.getX();
			double y = level.getWall(i).getY() - camera.getY();
			double distance = Math.sqrt(x * x + y * y);

			distances.add(new DistanceToWall(distance, level.getWall(i)));
		}

		// Sort the walls based on distance from the camera
		Collections.sort(distances, new DistanceToWall());

		// Calculate the slope and y-intercept of the camera
		double cameraSlope = (camera.getY() - previousY) / (camera.getX() - previousX);
		double cameraIntercept = camera.getY() - cameraSlope * camera.getX();

		// Get the camera boundaries
		double lowerXBoundCamera;
		double upperXBoundCamera;

		double lowerYBoundCamera;
		double upperYBoundCamera;

		if (camera.getX() < previousX) {
			lowerXBoundCamera = camera.getX();
			upperXBoundCamera = previousX;
		} else {
			lowerXBoundCamera = previousX;
			upperXBoundCamera = camera.getX();
		}

		if (camera.getY() < previousY) {
			lowerYBoundCamera = camera.getY();
			upperYBoundCamera = previousY;
		} else {
			lowerYBoundCamera = previousY;
			upperYBoundCamera = camera.getY();
		}

		// The distance away from the wall
		double wallBoundary = 0.005;

		// For every wall
		for (int i = 0; i < distances.size(); i++) {

			// Properties of the wall
			double x = distances.get(i).getWall().getX();
			double y = distances.get(i).getWall().getY();
			double degrees = distances.get(i).getWall().getDegrees();

			double sine = Math.sin(Math.toRadians(degrees));
			double cosine = Math.cos(Math.toRadians(degrees));

			// Edge coordinates of the wall
			double wallEdge1X = x - 0.5 * cosine;
			double wallEdge1Y = y - 0.5 * sine;

			double wallEdge2X = x + 0.5 * cosine;
			double wallEdge2Y = y + 0.5 * sine;

			// Calculate the slope and y-intercept of the wall

			double wallSlope = (wallEdge2Y - wallEdge1Y) / (wallEdge2X - wallEdge1X);

			double wallIntercept = wallEdge2Y - wallSlope * wallEdge2X;

			// The intersection of the two lines
			double intersectX = (wallIntercept - cameraIntercept) / (cameraSlope - wallSlope);
			double intersectY = cameraSlope * intersectX + cameraIntercept;

			// Account for an infinite slope
			if (Double.isInfinite(wallSlope)) {
				intersectX = x;
				intersectY = cameraSlope * intersectX + cameraIntercept;
			}

			// Get the wall boundaries
			double lowerXBoundWall;
			double upperXBoundWall;

			double lowerYBoundWall;
			double upperYBoundWall;

			if (wallEdge2X < wallEdge1X) {
				lowerXBoundWall = wallEdge2X;
				upperXBoundWall = wallEdge1X;
			} else {
				lowerXBoundWall = wallEdge1X;
				upperXBoundWall = wallEdge2X;
			}

			if (wallEdge2Y < wallEdge1Y) {
				lowerYBoundWall = wallEdge2Y;
				upperYBoundWall = wallEdge1Y;
			} else {
				lowerYBoundWall = wallEdge1Y;
				upperYBoundWall = wallEdge2Y;
			}

			// Used to test for boundaries
			boolean isInWallBoundsX = false;
			boolean isInWallBoundsY = false;
			boolean isInCameraBoundsX = false;
			boolean isInCameraBoundsY = false;

			// Account for computation rounding error
			double error = 0.0001;

			// Test the boundaries
			if (intersectX + error >= lowerXBoundWall && intersectX - error <= upperXBoundWall) {
				isInWallBoundsX = true;
			}
			if (intersectY + error >= lowerYBoundWall && intersectY - error <= upperYBoundWall) {
				isInWallBoundsY = true;
			}
			if (intersectX + error >= lowerXBoundCamera && intersectX - error <= upperXBoundCamera) {
				isInCameraBoundsX = true;
			}
			if (intersectY + error >= lowerYBoundCamera && intersectY - error <= upperYBoundCamera) {
				isInCameraBoundsY = true;
			}

			// Check if collision occured
			if (isInWallBoundsX && isInWallBoundsY && isInCameraBoundsX && isInCameraBoundsY) {
				// Calculate new position

				// Perpendicular Slope
				double perpendicularSlope = -(wallEdge2X - wallEdge1X) / (wallEdge2Y - wallEdge1Y);

				// Degree of Perpendicular Slope
				// angle = arctan(m)
				double perpendicularAngle = Math.atan(perpendicularSlope);

				// X Component
				double xOffset = wallBoundary * Math.cos(perpendicularAngle);

				// Y Component
				double yOffset = wallBoundary * Math.sin(perpendicularAngle);

				// Reposition the camera
				if (previousX <= intersectX) {
					camera.setX(intersectX - xOffset);
				} else {
					camera.setX(intersectX + xOffset);
				}

				camera.setY(intersectY + yOffset);
			}

		}

		// Save state of camera
		previousX = camera.getX();
		previousY = camera.getY();
	}

	/**/
	/*
	 * public void render()
	 * 
	 * NAME public void render() - Render the scene
	 * 
	 * SYNOPSIS public void render()
	 * 
	 * DESCRIPTION Render the scene of every camera
	 * 
	 * RETURNS Void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private void render() {
		// Allow the camera to render
		camera.render();
	}

	/**/
	/*
	 * public JFrame createWindow(Canvas canvas)
	 * 
	 * NAME public JFrame createWindow(Canvas canvas) - Create a window
	 * 
	 * SYNOPSIS public JFrame createWindow(Canvas canvas)
	 * 
	 * Canvas canvas - required canvas for window
	 * 
	 * DESCRIPTION Create a window with a canvas inside.
	 * 
	 * RETURNS JFrame - a window with a canvas.
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	private JFrame createWindow(Canvas canvas) {
		// Create a window
		JFrame window = new JFrame();

		// Title of window
		window.setTitle("3D Game");

		// Close the window when exit is pressed
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Create a blank image, create a cursor with that image, then add the
		// cursor to the window
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
