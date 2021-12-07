package pentomino.ui;

import java.awt.event.*;

public class CustomKeyListener implements KeyListener {

	@Override
	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			Input.press_left();
			break;

		case KeyEvent.VK_RIGHT:
			Input.press_right();
			break;

		case KeyEvent.VK_SPACE:
			Input.press_drop();
			break;

		case KeyEvent.VK_DOWN:
			Input.press_fall();
			break;

		case KeyEvent.VK_UP:
			Input.press_rotate();
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();

		switch (keyCode) {
		case KeyEvent.VK_LEFT:
			Input.release_left();
			break;

		case KeyEvent.VK_RIGHT:
			Input.release_right();
			break;

		case KeyEvent.VK_SPACE:
			Input.release_drop();
			break;

		case KeyEvent.VK_DOWN:
			Input.release_fall();
			break;

		case KeyEvent.VK_UP:
			Input.release_rotate();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}
}