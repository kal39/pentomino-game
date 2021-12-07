package pentomino.bot;

import pentomino.utils.ArrayUtils;

public class BotTest {
	public static void main(String[] args) {
		double[] weights = new double[] { -0.9, -0.05, -0.38, 0.26, 0.73 };
		int times = 1000;

		long t1 = System.nanoTime();
		double score = test_times(2, times, weights);
		long t2 = System.nanoTime();

		System.out.println("Average score: " + score + ", execution time: " + ((t2 - t1) / times / 1000) + "[μs]");
	}

	public static double test_times(int bot, int times, double[] weights) {
		double total = 0;
		int max = 0;

		for (int i = 0; i < times; i++) {
			int score = 0;
			switch (bot) {
				case 1:
					score = test_bot1(weights);
					break;
				case 2:
					score = test_bot2(weights);
					break;
			}

			System.out.println(score);
			total += score;
			if (score > max)
				max = score;
		}

		System.out.println("Average: " + total / times + ", max: " + max);

		return total / times;
	}

	public static int test_bot1(double[] weights) {
		Bot1 bot = new Bot1(weights);
		int[][] board = new int[20][5];
		int clearedLines = 0;

		while (!board_filled(board)) {
			bot.optimal_move(board, new_piece());
			board = bot.get_placement();
			clearedLines += count_filled_lines(board);
			board = remove_filled_lines(board);
		}
		return clearedLines;
	}

	public static int test_bot2(double[] weights) {
		Bot2 bot = new Bot2(weights);
		int[][] board = new int[20][5];
		int clearedLines = 0;

		int[][] nextPiece = new_piece();

		while (!board_filled(board)) {
			int[][] currentPiece = nextPiece.clone();
			nextPiece = new_piece();
			bot.optimal_move(board, currentPiece, nextPiece);
			board = bot.get_placement();
			clearedLines += count_filled_lines(board);
			board = remove_filled_lines(board);
		}

		return clearedLines;
	}

	private static boolean board_filled(int[][] board) {
		for (int i = 0; i < board[0].length; i++) {
			if (board[5][i] != 0)
				return true;
		}

		return false;
	}

	private static int[][] new_piece() {
		return new pentomino.game.Block().get_shape();
	}

	private static int count_filled_lines(int[][] board) {
		int clearedLines = 0;

		for (int i = 0; i < board.length; i++) {
			int filled = 0;
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0)
					filled++;
			}

			if (filled == board[0].length)
				clearedLines++;
		}

		return clearedLines;
	}

	private static int[][] remove_filled_lines(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			int filled = 0;
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] != 0)
					filled++;
			}

			if (filled == board[0].length)
				board = remove_line(board, i);

		}

		return board;
	}

	private static int[][] remove_line(int[][] board, int line) {
		int[][] newBoard = ArrayUtils.copy(board);

		newBoard[0] = new int[5];
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < board[0].length; j++) {
				newBoard[i + 1][j] = board[i][j];
			}
		}

		return newBoard;
	}

	public static void print_board(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] == 0 ? "░░" : "██");
			}
			System.out.println();
		}
		System.out.println();
	}
}
