package com.gramuglia.game;

import java.awt.AWTException;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

//
// Class to record user input
//
public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

	// To detect movement
	private boolean forward = false;
	private boolean backward = false;
	private boolean strafeLeft = false;
	private boolean strafeRight = false;

	// Position of cursor
	private int x = 0;
	private int y = 0;

	// Original location of cursor
	public int xOrigin;
	public int yOrigin;

	// Game window
	private JFrame window;

	// Dimension of the window
	private int width;
	private int height;

	// Toggles user input
	private boolean enable = false;

	// The cursor
	Cursor blankCursor;

	// Robot to move cursor
	Robot robot;

	/**/
	/*
	 * public InputHandler(JFrame window, int width, int height)
	 * 
	 * NAME public InputHandler(JFrame window, int width, int height) -
	 * constructor for InputHandler
	 * 
	 * SYNOPSIS public void InputHandler(JFrame window, int width, int height)
	 * 
	 * JFrame window - the window int width - the width of the window int height
	 * - the height of the window
	 * 
	 * DESCRIPTION Retrieves data from mouse and keyboard.
	 * 
	 * RETURNS A InputHandler object
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public InputHandler(JFrame window, int width, int height) {

		// Saves parameters
		this.window = window;
		this.width = width;
		this.height = height;

		// Create robot
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Create cursor
		BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");

		// Save cursor's origin
		xOrigin = width / 2;
		yOrigin = height / 2;
	}

	/**/
	/*
	 * public boolean getForward()
	 * 
	 * NAME public boolean getForward() - Check if the camera moved forward
	 * 
	 * SYNOPSIS public boolean getForward()
	 * 
	 * DESCRIPTION Check if the camera moved forwards
	 * 
	 * RETURNS boolean - forward
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public boolean getForward() {
		return forward;
	}

	/**/
	/*
	 * public boolean getBackward()
	 * 
	 * NAME public boolean getBackward() - Check if the camera moved backwards
	 * 
	 * SYNOPSIS public boolean getBackward()
	 * 
	 * DESCRIPTION Check if the camera moved backwards
	 * 
	 * RETURNS boolean - backward
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public boolean getBackward() {
		return backward;
	}

	/**/
	/*
	 * public boolean getStrafeLeft()
	 * 
	 * NAME public boolean getStrafeLeft() - Check if the camera moved left
	 * 
	 * SYNOPSIS public boolean getStrafeLeft()
	 * 
	 * DESCRIPTION Check if the camera moved left
	 * 
	 * RETURNS boolean - strafeLeft
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public boolean getStrafeLeft() {
		return strafeLeft;
	}

	/**/
	/*
	 * public boolean getStrafeRight()
	 * 
	 * NAME public boolean getStrafeRight() - Check if the camera moved right
	 * 
	 * SYNOPSIS public boolean getStrafeRight()
	 * 
	 * DESCRIPTION Check if the camera moved right
	 * 
	 * RETURNS boolean - strafeRight
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public boolean getStrafeRight() {
		return strafeRight;
	}

	/**/
	/*
	 * public boolean getX()
	 * 
	 * NAME public boolean getX() - Get cursor's x distance traveled
	 * 
	 * SYNOPSIS public boolean getX()
	 * 
	 * DESCRIPTION Move the origin to the current location. Reset x.
	 * 
	 * RETURNS int - distance the cursor traveled
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public int getX() {
		// Move origin
		xOrigin += x;

		// Save distance traveled
		int temp = x;

		// Reset distance traveled
		x = 0;

		// Return distance traveled
		return temp;
	}

	/**/
	/*
	 * public boolean getY()
	 * 
	 * NAME public boolean getY() - Get cursor's y distance traveled
	 * 
	 * SYNOPSIS public boolean getY()
	 * 
	 * DESCRIPTION Move the origin to the current location. Reset y.
	 * 
	 * RETURNS int - distance the cursor traveled
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	public int getY() {

		// Move origin
		yOrigin += y;

		// Save distance traveled
		int temp = y;

		// Reset distance traveled
		y = 0;

		// Return distance traveled
		return temp;
	}

	/**/
	/*
	 * public void mouseDragged(MouseEvent arg0)
	 * 
	 * NAME public void mouseDragged(MouseEvent arg0) - Detect a mouse drag
	 * 
	 * SYNOPSIS public void mouseDragged(MouseEvent arg0)
	 * 
	 * MouseEvent arg0 - ""
	 * 
	 * DESCRIPTION N/A
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**/
	/*
	 * public void mouseMoved(MouseEvent arg0)
	 * 
	 * NAME public void mouseMoved(MouseEvent arg0) - Detect a mouse movement
	 * 
	 * SYNOPSIS public void mouseMoved(MouseEvent arg0)
	 * 
	 * MouseEvent arg0 - ""
	 * 
	 * DESCRIPTION Save the distance traveled.
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// Save the distance traveled if input is enabled
		if (!enable) {
			x = arg0.getX() - xOrigin;
			y = arg0.getY() - yOrigin;
		}
	}

	/**/
	/*
	 * public void keyPressed(KeyEvent arg0)
	 * 
	 * NAME public void keyPressed(KeyEvent arg0) - Detect any key press
	 * 
	 * SYNOPSIS public void keyPressed(KeyEvent arg0)
	 * 
	 * KeyEvent arg0 - ""
	 * 
	 * DESCRIPTION Save the state of the movement and enable buttons.
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void keyPressed(KeyEvent arg0) {

		// Save the state of the movement buttons if enabled
		if (!enable) {
			if (arg0.getKeyCode() == 65) {
				strafeLeft = true;
			}
			if (arg0.getKeyCode() == 68) {
				strafeRight = true;
			}
			if (arg0.getKeyCode() == 87) {
				forward = true;
			}
			if (arg0.getKeyCode() == 83) {
				backward = true;
			}
		}

		// Toggle enabled state and cursor control
		if (arg0.getKeyCode() == 27) {
			enable = !enable;
			if (enable) {
				window.setCursor(Cursor.getDefaultCursor());
			} else {
				window.setCursor(blankCursor);
			}
		}
	}

	/**/
	/*
	 * public void keyReleased(KeyEvent arg0)
	 * 
	 * NAME public void keyReleased(KeyEvent arg0) - Detect any key release
	 * 
	 * SYNOPSIS public void keyReleased(KeyEvent arg0)
	 * 
	 * KeyEvent arg0 - ""
	 * 
	 * DESCRIPTION Save the state of the movement buttons.
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		if (arg0.getKeyCode() == 65) {
			strafeLeft = false;
		}
		if (arg0.getKeyCode() == 68) {
			strafeRight = false;
		}
		if (arg0.getKeyCode() == 87) {
			forward = false;
		}
		if (arg0.getKeyCode() == 83) {
			backward = false;
		}
	}

	/**/
	/*
	 * public void keyTyped(KeyEvent arg0)
	 * 
	 * NAME public void keyTyped(KeyEvent arg0) - Detect any key typed
	 * 
	 * SYNOPSIS public void keyTyped(KeyEvent arg0)
	 * 
	 * KeyEvent arg0 - ""
	 * 
	 * DESCRIPTION N/A
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**/
	/*
	 * public void mouseClicked(MouseEvent arg0)
	 * 
	 * NAME public void mouseClicked(MouseEvent arg0) - Detect any mouse clicked
	 * 
	 * SYNOPSIS public void mouseClicked(MouseEvent arg0)
	 * 
	 * KeyEvent arg0 - ""
	 * 
	 * DESCRIPTION N/A
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**/
	/*
	 * public void mouseEntered(MouseEvent arg0)
	 * 
	 * NAME public void mouseEntered(MouseEvent arg0) - Detect any mouse entered
	 * 
	 * SYNOPSIS public void mouseEntered(MouseEvent arg0)
	 * 
	 * KeyEvent arg0 - ""
	 * 
	 * DESCRIPTION N/A
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**/
	/*
	 * public void mouseExited(MouseEvent arg0)
	 * 
	 * NAME public void mouseExited(MouseEvent arg0) - Detect any mouse exited
	 * 
	 * SYNOPSIS public void mouseExited(MouseEvent arg0)
	 * 
	 * MouseEvent arg0 - ""
	 * 
	 * DESCRIPTION Move the cursor to the screen and save any data.
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void mouseExited(MouseEvent arg0) {
		// If input is enabled
		if (!enable) {
			// Recenter the cursor
			robot.mouseMove(window.getX() + width / 2, window.getY() + height / 2);

			// Calculate the distance traveled
			x = arg0.getX() - xOrigin;
			y = arg0.getY() - yOrigin;

			// Reset the origin
			xOrigin = width / 2;
			yOrigin = height / 2;
		}
	}

	/**/
	/*
	 * public void mousePressed(MouseEvent arg0)
	 * 
	 * NAME public void mousePressed(MouseEvent arg0) - Detect any mouse pressed
	 * 
	 * SYNOPSIS public void mousePressed(MouseEvent arg0)
	 * 
	 * MouseEvent arg0 - ""
	 * 
	 * DESCRIPTION N/A
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/**/
	/*
	 * public void mouseReleased(MouseEvent arg0)
	 * 
	 * NAME public void mouseReleased(MouseEvent arg0) - Detect any mouse
	 * released
	 * 
	 * SYNOPSIS public void mouseReleased(MouseEvent arg0)
	 * 
	 * MouseEvent arg0 - ""
	 * 
	 * DESCRIPTION N/A
	 * 
	 * RETURNS void
	 * 
	 * AUTHOR Joe Gramuglia
	 */
	/**/
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
