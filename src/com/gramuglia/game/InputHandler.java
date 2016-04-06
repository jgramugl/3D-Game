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

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {
	
	private boolean forward = false;
	private boolean backward = false;
	private boolean strafeLeft = false;
	private boolean strafeRight = false;
	
	
	private boolean rotateLeft = false;
	private boolean rotateRight = false;
	

	private int x;
	private int y;

	private JFrame window;
	private int width;
	private int height;

	private boolean enable = false;

	Cursor blankCursor;
	Robot robot;
	
	public InputHandler(JFrame window, int width, int height) {
		this.window = window;
		this.width = width;
		this.height = height;

		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BufferedImage cursorImg = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
		blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank");
	}

	public boolean getForward() {
		return forward;
	}

	public boolean getBackward() {
		return backward;
	}

	public boolean getStrafeLeft() {
		return strafeLeft;
	}

	public boolean getStrafeRight() {
		return strafeRight;
	}
	
	
	public boolean getRotateLeft() {
		return rotateLeft;
	}
	public boolean getRotateRight() {
		return rotateRight;
	}

	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		/*if (!enable) {
			x = arg0.getX();
			y = arg0.getY();
		}*/

	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
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
			
			if (arg0.getKeyCode() == 81) {
				// Rotate Left
				rotateLeft = true;
			}
			if (arg0.getKeyCode() == 69) {
				// Rotate Right
				rotateRight = true;
			}
		}

		// Escape key pressed
		if (arg0.getKeyCode() == 27) {
			/*enable = !enable;
			if (enable) {
				// System.out.println("Default cursor enabled.");
				window.setCursor(Cursor.getDefaultCursor());
			} else {
				// System.out.println("Invisible cursor enabled.");
				window.setCursor(blankCursor);
			}*/
		}
	}

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
		
		if (arg0.getKeyCode() == 81) {
			// Rotate Left
			rotateLeft = false;
		}
		if (arg0.getKeyCode() == 69) {
			// Rotate Right
			rotateRight = false;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		/*if (!enable) {
			robot.mouseMove(window.getX() + width / 2, window.getY() + height / 2);
		}*/
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
