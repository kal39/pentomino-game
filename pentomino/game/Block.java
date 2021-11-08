package pentomino.game;

import java.util.Random;
import pentomino.Representations;

public class Block {
	int[][] shape;
	int id;
	int x;
	int y;
	private static int boardWidth = 5;

	public Block() {
		Random rand = new Random();

		id = rand.nextInt(12);
		shape = Representations.get_shape(id);

		int rotation = rand.nextInt(4);
		for (int i = 0; i < rotation; i++) {
			ArrayUtils.rotate(shape);
		}

		x = ((5 - shape[0].length) / 2);
		y = 5 - shape.length;
	}

	public void rotate(Board board) {
		int oldLength = shape.length;
		int oldWidth = shape[0].length;
		int oldX = x;
		int oldY = y;

		shape = ArrayUtils.rotate(shape);

		if (oldLength > shape.length) {
			y += (oldLength - shape.length) / 2;
		} else if (shape.length > oldLength) {
			y -= (shape.length - oldLength) / 2;
		}

		if (oldWidth > shape[0].length) {
			x += (oldWidth - shape[0].length) / 2;
		} else if (shape[0].length > oldWidth) {
			x -= (shape[0].length - oldWidth) / 2;
		}

		if (x < 0) {
			x += Math.abs(x);
		} else if (x + (shape[0].length - 1) > 4) {
			x -= (x + (shape[0].length - 1)) - 4;
		}

		boolean check = false;
		for (int r = 0; r < shape.length; r++) {
			for (int c = 0; c < shape[0].length; c++) {
				if (shape[r][c] != 0 && board.get_board()[r + y][c + x] != 0) {
					check = true;
				}
			}
		}

		if (check) {
			for (int i = 0; i < 3; i++) {
				shape = ArrayUtils.rotate(shape);
			}
			x = oldX;
			y = oldY;
		}

	}

	public boolean move_down(Board board) {
		if (board.bottom_check(this) && board.collision_check(this)) {
			y++;
			return true;
		} else {
			return false;
		}
	}

	public void move_right(Board board) {
		if (x + shape[0].length != boardWidth && board.perpendicular_collision_right(this))
			x++;
	}

	public void move_left(Board board) {
		if (x != 0 && board.perpendicular_collision_left(this))
			x--;
	}

	public int[][] get_shape() {
		return shape;
	}

	public int get_id() {
		return id;
	}

	public int get_xPos() {
		return x;
	}

	public int get_yPos() {
		return y;
	}

	public int get_length() {
		return shape.length;
	}

	public int get_width() {
		return shape[0].length;
	}

}