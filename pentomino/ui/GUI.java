package pentomino.ui;

import java.awt.*;

import pentomino.Representations;

public class GUI extends Canvas {
	int cellSize;
	float fontRatio = 0.4f;
	int textPadding = 10;

	int[][] board = new int[15][5];
	int score;
	int highScore;
	int nextPiece;

	int mode = 0;

	public void set_board(int[][] newBoard) {
		board = copy_board(newBoard);
	}

	public void set_cell_size(int size) {
		cellSize = size;
	}

	public void set_score(int newScore) {
		score = newScore;
	}

	public void set_high_score(int newHighScore) {
		highScore = newHighScore;
	}

	public void set_next_piece(int newNextPiece) {
		nextPiece = newNextPiece;
	}

	public void set_title() {
		mode = 0;
	}

	public void set_game() {
		mode = 1;
	}

	public void set_loose() {
		mode = 2;
	}

	public void update(Graphics G) {
		Image buffer = createImage(cellSize * (5 + 2 + 4), cellSize * (15 + 2));
		Graphics g = buffer.getGraphics();

		switch (mode) {
		case 0:
			draw_title(g);
			break;
		case 1:
			draw_background(g);
			draw_border(g);
			draw_board(g);
			draw_info(g);
			break;

		case 2:
			draw_loose(g);
			break;
		}

		G.drawImage(buffer, 0, 0, null);
	}

	private void draw_title(Graphics g) {
		draw_background(g);

		for (int i = 0; i < board.length + 2; i++) {
			for (int j = 0; j < board[0].length + 2 + 4; j++) {
				if (i == 0 || j == 0 || i == board.length + 1 || j == board[0].length + 5)
					draw_cell(g, j, i, -1); // negative represents a border cell
			}
		}

		int fontSize = (int) (fontRatio * cellSize);

		Font titleFont = new Font("Monospace", Font.BOLD, fontSize * 2);
		Font optionsFont = new Font("Monospace", Font.BOLD, fontSize * 3 / 4);

		g.setFont(titleFont);
		FontMetrics m = g.getFontMetrics(titleFont);

		int centerX = ((board[0].length + 5 + 1) * cellSize) / 2;
		int centerY = ((board.length + 2 + 1) * cellSize) / 2;

		String title = "PENTOMINO TETRIS";
		g.setColor(Color.white);
		g.drawString(title, centerX - m.stringWidth(title) / 2, centerY / 2);

		g.setFont(optionsFont);
		m = g.getFontMetrics(optionsFont);

		String options1 = "Press [UP] to play";
		g.drawString(options1, centerX - m.stringWidth(options1) / 2, centerY);

		String options2 = "Press [LEFT] for bot 1 | Press [RIGHT] for bot 2";
		g.drawString(options2, centerX - m.stringWidth(options2) / 2, centerY + fontSize * 2);

		String options3 = "Press [DOWN] to quit";
		g.drawString(options3, centerX - m.stringWidth(options3) / 2, centerY + fontSize * 4);

	}

	private void draw_loose(Graphics g) {
		draw_background(g);

		for (int i = 0; i < board.length + 2; i++) {
			for (int j = 0; j < board[0].length + 2 + 4; j++) {
				if (i == 0 || j == 0 || i == board.length + 1 || j == board[0].length + 5)
					draw_cell(g, j, i, -1); // negative represents a border cell
			}
		}

		int fontSize = (int) (fontRatio * cellSize);

		Font scoreFont = new Font("Monospace", Font.BOLD, fontSize * 2);
		Font optionsFont = new Font("Monospace", Font.BOLD, fontSize * 3 / 4);

		g.setFont(scoreFont);
		FontMetrics m = g.getFontMetrics(scoreFont);

		int centerX = ((board[0].length + 5 + 1) * cellSize) / 2;
		int centerY = ((board.length + 2 + 1) * cellSize) / 2;

		String scoreString = "Score: " + score;
		g.setColor(Color.white);
		g.drawString(scoreString, centerX - m.stringWidth(scoreString) / 2, centerY / 2);

		g.setFont(optionsFont);
		m = g.getFontMetrics(optionsFont);

		String options1 = "Press [UP] to play again";
		g.drawString(options1, centerX - m.stringWidth(options1) / 2, centerY);

		String options2 = "Press [DOWN] to show leaderboard";
		g.drawString(options2, centerX - m.stringWidth(options2) / 2, centerY + fontSize * 2);

	}

	private void draw_background(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, cellSize * (5 + 2 + 4), cellSize * (15 + 2));
	}

	private void draw_border(Graphics g) {
		for (int i = 0; i < board.length + 2; i++) {
			for (int j = 0; j < board[0].length + 2 + 4; j++) {
				if (i == 0 || j == 0 || i == board.length + 1 || j == board[0].length + 1 || j == board[0].length + 5)
					draw_cell(g, j, i, -1); // negative represents a border cell
			}
		}
	}

	private void draw_board(Graphics g) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				draw_cell(g, j + 1, i + 1, board[i][j]); // the color of the cell depends on the pentomino type
			}
		}
	}

	private void draw_info(Graphics g) {
		int fontSize = (int) (fontRatio * cellSize);
		g.setFont(new Font("Monospace", Font.PLAIN, fontSize));

		int xOff = cellSize * 7 + textPadding;
		int yOff = cellSize + fontSize + textPadding;

		g.setColor(Color.white);
		g.drawString("Score:", xOff, yOff);
		yOff += fontSize + textPadding;
		g.drawString(String.valueOf(score), xOff, yOff);
		yOff += fontSize + textPadding;
		g.drawString("------------------", xOff, yOff);
		yOff += fontSize + textPadding;

		g.drawString("Highscore:", xOff, yOff);
		yOff += fontSize + textPadding;
		g.drawString(String.valueOf(highScore), xOff, yOff);
		yOff += fontSize + textPadding;
		g.drawString("------------------", xOff, yOff);
		yOff += fontSize + textPadding;

		g.drawString("Next piece:", xOff, yOff);
		yOff += fontSize + textPadding;

		int pentominoOffset = (cellSize * 3 - (cellSize / 2) * 3) / 2;
		draw_2d_array(g, Representations.get_shape(nextPiece), cellSize * 7 + pentominoOffset, yOff, cellSize / 2,
				get_pentomino_color(nextPiece));

	}

	private void draw_2d_array(Graphics g, int[][] array, int x, int y, int cellSize, Color color) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				if (array[i][j] == 1)
					draw_3d_square(g, x + j * cellSize, y + i * cellSize, cellSize, color);
			}
		}
	}

	private void draw_cell(Graphics g, int x, int y, int piece) {
		if (piece == 0)
			return; // empty cell

		draw_3d_square(g, x * cellSize, y * cellSize, cellSize, get_pentomino_color(piece));
	}

	private Color get_pentomino_color(int pentomino) {
		switch (pentomino) {
		case 1:
			return Color.red;
		case 2:
			return Color.green;
		case 3:
			return Color.blue;
		case 4:
			return Color.yellow;
		case 5:
			return Color.pink;
		case 6:
			return Color.cyan;
		case 7:
			return Color.magenta;
		case 8:
			return Color.orange;
		case 9:
			return new Color(100, 0, 255);
		case 10:
			return new Color(200, 200, 200);
		case 11:
			return new Color(210, 255, 0);
		case 12:
			return new Color(0, 170, 255);
		default:
			return Color.gray; // the border
		}
	}

	private void draw_3d_square(Graphics g, int x, int y, int size, Color color) {
		int contrast = 50;
		int borderSize = size / 5;

		g.setColor(darken_color(color, contrast * 2));
		draw_triangle(g, x, y + size, x + size, y + size, x + size / 2, y + size / 2);

		g.setColor(darken_color(color, contrast));
		draw_triangle(g, x + size, y, x + size, y + size, x + size / 2, y + size / 2);

		g.setColor(lighten_color(color, contrast * 2));
		draw_triangle(g, x, y, x + size, y, x + size / 2, y + size / 2);

		g.setColor(lighten_color(color, contrast));
		draw_triangle(g, x, y, x, y + size, x + size / 2, y + size / 2);

		g.setColor(color);
		g.fillRect(x + borderSize, y + borderSize, size - borderSize * 2, size - borderSize * 2);
	}

	private void draw_triangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
		Polygon p = new Polygon();
		p.addPoint(x1, y1);
		p.addPoint(x2, y2);
		p.addPoint(x3, y3);

		g.fillPolygon(p);
	}

	private Color lighten_color(Color color, int magnitude) {
		return new Color(Math.min(color.getRed() + magnitude, 255), Math.min(color.getGreen() + magnitude, 255),
				Math.min(color.getBlue() + magnitude, 255));
	}

	private Color darken_color(Color color, int magnitude) {
		return new Color(Math.max(color.getRed() - magnitude, 0), Math.max(color.getGreen() - magnitude, 0),
				Math.max(color.getBlue() - magnitude, 0));
	}

	private int[][] copy_board(int[][] in) {
		int[][] out = new int[in.length][in[0].length];

		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < in[0].length; j++) {
				out[i][j] = in[i][j];
			}
		}

		return out;
	}
}