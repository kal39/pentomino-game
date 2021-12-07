package pentomino.bot;

public class Score {
	public static double calculate_score(int[][] board, double[] weights) {
		return weights[0] * count_cleared_lines(board) + weights[1] * measure_height(board)
				+ weights[2] * measure_total_height(board) + weights[3] * measure_bumpiness(board)
				+ weights[4] * count_holes(board);
	}

	private static int count_cleared_lines(int[][] board) {
		int clearedLines = 0;

		for (int i = 0; i < board.length; i++) {
			int count = 0;
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0)
					count++;
			}

			if (count == board[0].length)
				clearedLines++;
		}

		return clearedLines;
	}

	private static int measure_height(int[][] board) {
		int maxHeight = 0;
		for (int i = 0; i < board[0].length; i++) {
			int colHeight = column_height(board, i);
			if (colHeight > maxHeight)
				maxHeight = colHeight;
		}

		return maxHeight;
	}

	private static int measure_total_height(int[][] board) {
		int totalHeight = 0;
		for (int i = 0; i < board[0].length; i++) {
			totalHeight += column_height(board, i);
		}

		return totalHeight;
	}

	private static int measure_bumpiness(int[][] board) {
		int bumpiness = 0;
		int prevHeight = column_height(board, 0);
		for (int i = 1; i < board[0].length; i++) {
			int currHeight = column_height(board, i);
			bumpiness += Math.abs(prevHeight - currHeight);
			prevHeight = currHeight;
		}

		return bumpiness;
	}

	private static int count_holes(int[][] board) {
		int holes = 0;

		for (int i = 1; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0) {
					for (int k = i - 1; k > 0; k--) {
						if (board[k][j] != 0) {
							holes++;
							break;
						}
					}
				}
			}
		}

		return holes;
	}

	private static int column_height(int[][] board, int column) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][column] != 0)
				return board.length - i;
		}

		return 0;
	}
}
