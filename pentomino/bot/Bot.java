package pentomino.bot;

import pentomino.utils.*;

public class Bot {
	public int[][] placement;
	public int[] inputSequence;
	public double score;

	public double[] weights;

	private int leftPresses;
	private int rightPresses;
	private int rotatePresses;

	public Bot(double[] aWeights) {
		weights = aWeights.clone();
	}

	public void calculate_inputs(int boardWidth, int pieceWidth) {
		rotatePresses = inputSequence[1];

		leftPresses = boardWidth;
		rightPresses = inputSequence[0];
	}

	public int[][] get_placement() {
		return placement.clone();
	}

	public void print_placement() {
		for (int i = 0; i < placement.length; i++) {
			for (int j = 0; j < placement[0].length; j++) {
				System.out.print(placement[i][j] == 0 ? "░░" : "██");
			}
			System.out.println();
		}
		System.out.println("Score: " + score + "\n");
	}

	public boolean is_left_pressed() {
		if (leftPresses > 0) {
			leftPresses--;
			return true;
		} else {
			return false;
		}
	}

	public boolean is_right_pressed() {
		if (leftPresses == 0 && rightPresses > 0) {
			rightPresses--;
			return true;
		} else {
			return false;
		}
	}

	public boolean is_rotate_pressed() {
		if (rotatePresses > 0) {
			rotatePresses--;
			return true;
		} else {
			return false;
		}
	}

	public boolean is_drop_pressed() {
		if (leftPresses == 0 && rightPresses == 0 && rotatePresses == 0) {
			return true;
		} else {
			return false;
		}
	}

	protected static int[][] drop_piece(int[][] board, int[][] piece, int col) {
		int originalCount = count_filled_cells(board) + count_filled_cells(piece);
		int i = 1;
		while (true) {
			if (i + piece.length > board.length)
				break;
			int[][] newBoard = add_piece_to_board(board, piece, col, i);
			if (originalCount != count_filled_cells(newBoard))
				break;
			i++;
		}

		// return remove_filled_lines(add_piece_to_board(board, piece, col, i - 1));
		return add_piece_to_board(board, piece, col, i - 1);
	}

	private static int[][] add_piece_to_board(int board[][], int[][] piece, int x, int y) {
		int[][] newBoard = ArrayUtils.copy(board);
		for (int i = 0; i < piece.length; i++) {
			for (int j = 0; j < piece[0].length; j++) {
				if (piece[i][j] != 0)
					newBoard[i + y][j + x] = piece[i][j];
			}
		}

		return newBoard;
	}

	private static int count_filled_cells(int board[][]) {
		int count = 0;

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0)
					count++;
			}
		}

		return count;
	}
}
