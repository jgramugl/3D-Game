package com.gramuglia.game;

import java.awt.AWTException;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.gramuglia.game.graphics.Camera;

public class Game implements Runnable {
	private final double ASPECT_RATIO = 9.0 / 16.0;
	private final int WIDTH = 1920 / 4;
	private final int HEIGHT = (int) (WIDTH * ASPECT_RATIO);
	private final double SCALE = 2;

	private Thread thread;
	private boolean running = false;
	private Camera camera;
	private Canvas canvas;
	private InputHandler input;
	
	public Game() {
		Dimension dimension = new Dimension((int) (WIDTH * SCALE), (int) (HEIGHT * SCALE));

		canvas = new Canvas();
		canvas.setPreferredSize(dimension);
		
		camera = new Camera(canvas, (int) (WIDTH * SCALE), (int) (HEIGHT * SCALE));
		
		JFrame window = createWindow(canvas);
		
		input = new InputHandler(window, (int)(window.getWidth()), (int) (window.getHeight()));

		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		
		try {
			Robot robot = new Robot();
			robot.mouseMove(window.getX() + (int)(window.getWidth()) / 2, window.getY() + (int)(window.getHeight()) / 2);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		canvas.requestFocus();
	}

	public void start() {
		running = true;

		thread = new Thread(this);
		thread.start();
	}

	public void stop() {
		running = false;

		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		int frames = 0;
		
		double unusedTime = 0;
		long previousTime = System.nanoTime();
		double secondsPerUpdate = 1 / 60.0;
		
		int updateCount = 0;
		
		while (running) {
			long currentTime = System.nanoTime();
			long difference = currentTime - previousTime;
			previousTime = currentTime;
			
			if (difference < 0) {
				difference = 0;
			}
			if (difference > 1000000000) {
				difference = 1000000000;
			}
			unusedTime += difference / 1000000000.0;
			
			boolean updated = false;
			while(unusedTime > secondsPerUpdate) {
				update();
				unusedTime -= secondsPerUpdate;
				updated = true;
				
				updateCount++;
				if (updateCount % 60 == 0) {
					System.out.println(frames + " fps");
					frames = 0;
					updateCount = 0;
				}
			}
			
			if (updated) {
				render();
				frames++;
			}
		}
	}

	
	private void update() {
		if (input.getRotateLeft()) {
			camera.rotate(1);
		}
		if (input.getRotateRight()) {
			camera.rotate(-1);
		}
		

		double sine = Math.sin(Math.toRadians(camera.getDegrees()));
		double cosine = Math.cos(Math.toRadians(camera.getDegrees()));
		double moveSpeed = 0.1;
		
		if (input.getForward()) {
			//System.out.println("Forward.");
			camera.translate(-moveSpeed * sine, moveSpeed * cosine);
		}
		if (input.getBackward()) {
			//System.out.println("Backward.");
			camera.translate(moveSpeed * sine, -moveSpeed * cosine);
		}
		if (input.getStrafeLeft()) {
			//System.out.println("Strafe Left.");
			camera.translate(-moveSpeed * cosine, -moveSpeed * sine);
		}
		if (input.getStrafeRight()) {
			//System.out.println("Strafe Right.");
			camera.translate(moveSpeed * cosine, moveSpeed * sine);
		}
	}

	private void render() {
		camera.render();
	}

	private JFrame createWindow(Canvas canvas) {
		JFrame window = new JFrame();
		window.setTitle("3D Game");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
		window.setCursor(blankCursor);
		*/
		window.setSize(WIDTH, HEIGHT);
		window.add(canvas);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		return window;
	}
}
