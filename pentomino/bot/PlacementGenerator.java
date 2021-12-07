package pentomino.bot;

import pentomino.utils.ArrayUtils;

public class PlacementGenerator {
	public static void main(String[] args) {
		int[][] board = { { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0 }, { 0, 0, 0, 0, 0 }, { 0, 0, 0, 1, 0 }, { 0, 0, 1, 1, 0 }, { 1, 1, 1, 1, 1 }, };
		int[][] piece = { { 1, 1, 1 }, { 1, 1, 0 } };

		print_board(board);
		print_board(piece);

		int[][][] placements = generate_all_placements(board, piece);

		for (int i = 0; i < placements.length; i++) {
			print_board(placements[i]);
		}

	}

	public static int[][][] generate_all_placements(int[][] board, int[][] piece) {
		int[][][] placements = new int[0][0][0];
		int[][][] orientations = generate_orientations(piece);

		for (int i = 0; i < orientations.length; i++) {
			for (int j = 0; j < board[0].length - orientations[i][0].length + 1; j++) {
				placements = ArrayUtils.add_element(placements, drop_piece(board, orientations[i], j));
			}
		}

		return placements;
	}

	public static int[][] generate_all_input_sequences(int[][] board, int[][] piece) {
		int[][] inputSequences = new int[0][0];
		int[][][] orientations = generate_orientations(piece);

		for (int i = 0; i < orientations.length; i++) {
			for (int j = 0; j < board[0].length - orientations[i][0].length + 1; j++) {
				inputSequences = ArrayUtils.add_element(inputSequences, new int[] { j, i });
			}
		}

		return inputSequences;
	}

	private static int[][][] generate_orientations(int[][] piece) {
		int[][][] orientations = new int[0][0][0];

		for (int i = 0; i < 4; i++) {
			boolean distinctElement = true;
			for (int j = 0; j < orientations.length; j++) {
				if (ArrayUtils.is_identical(orientations[j], piece))
					distinctElement = false;
			}

			if (distinctElement)
				orientations = ArrayUtils.add_element(orientations, piece);

			piece = ArrayUtils.rotate(piece);
		}

		return orientations;
	}

	private static int[][] drop_piece(int[][] board, int[][] piece, int col) {
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

	// private static int[][] remove_filled_lines(int[][] board) {
	// for (int i = 0; i < board.length; i++) {
	// int filled = 0;
	// for (int j = 0; j < board[0].length; j++) {
	// if (board[i][j] != 0)
	// filled++;
	// }

	// if (filled == board[0].length)
	// board = remove_line(board, i);
	// }

	// return board;
	// }

	// private static int[][] remove_line(int[][] board, int line) {
	// int[][] newBoard = ArrayUtils.copy(board);

	// newBoard[0] = new int[5];
	// for (int i = 0; i < line; i++) {
	// for (int j = 0; j < board[0].length; j++) {
	// newBoard[i + 1][j] = board[i][j];
	// }
	// }

	// return newBoard;
	// }

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
