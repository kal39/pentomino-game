package pentomino.ui;

import javax.swing.JFrame;

public class UI {
	JFrame f;
	GUI gui;

	int width;
	int height;

	public UI(int size) {
		width = size * (5 + 2 + 4);
		height = size * (15 + 2);

		gui = new GUI();
		gui.setSize(width, height);
		gui.set_cell_size(size);

		f = new JFrame("Pentomino Game");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(gui);
		gui.addKeyListener(new CustomKeyListener());
		f.pack();
		f.setVisible(true);
		f.setResizable(false);

	}

	/**
	 * Redraw the gui. Any changes won't take affect until this is called
	 */

	public void redraw() {
		gui.repaint();
	}

	/**
	 * Make the gui show the title
	 */

	public void set_title() {
		gui.set_title();
	}

	/**
	 * Make the gui run the game
	 */

	public void set_game() {
		gui.set_game();
	}

	/**
	 * Make the gui show the game over screen
	 */

	public void set_loose() {
		gui.set_loose();
	}

	/**
	 * Set the contents of the board to be drawn
	 * 
	 * @param newBoard the new board (size: 5x15)
	 */

	public void set_board(int[][] newBoard) {
		gui.set_board(newBoard);
	}

	/**
	 * Set the size of the cells
	 * 
	 * @param size the cell size in pixels
	 */

	public void set_cell_size(int size) {
		gui.set_cell_size(size);
	}

	/**
	 * Set the current score to be displayed
	 * 
	 * @param newScore the new score
	 */

	public void set_score(int newScore) {
		gui.set_score(newScore);
	}

	/**
	 * Set the high score to be displayed
	 * 
	 * @param newHighScore the new highscore
	 */

	public void set_high_score(int newHighScore) {
		gui.set_high_score(newHighScore);
	}

	/**
	 * Set the next pentomino to be displayed
	 * 
	 * @param newNextPiece the id of the pentomino (1 ~ 12)
	 */

	public void set_next_piece(int newNextPiece) {
		gui.set_next_piece(newNextPiece);
	}
}