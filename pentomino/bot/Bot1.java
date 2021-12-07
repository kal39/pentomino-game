package pentomino.bot;

import pentomino.utils.*;

public class Bot1 extends Bot {
	public Bot1(double[] aWeights) {
		super(aWeights);
	}

	public void optimal_move(int[][] board, int[][] piece) {
		// generate all rotations
		int[][][] orientations = new int[0][0][0];

		for (int i = 0; i < 4; i++) {
			// check that it's not a duplicate
			boolean distinctElement = true;
			for (int j = 0; j < orientations.length; j++) {
				if (ArrayUtils.is_identical(orientations[j], piece))
					distinctElement = false;
			}

			if (distinctElement)
				orientations = ArrayUtils.add_element(orientations, piece);

			// next rotation
			piece = ArrayUtils.rotate(piece);
		}

		// generate all positions
		int[][][] placements = new int[0][0][0];
		int[][] inputSequences = new int[0][0];

		// for each rotation
		for (int i = 0; i < orientations.length; i++) {
			// for each x position
			for (int j = 0; j < board[0].length - orientations[i][0].length + 1; j++) {
				// store the position of the dropped block
				placements = ArrayUtils.add_element(placements, drop_piece(board, orientations[i], j));
				// store the inputs necessary to move the block to the x position
				inputSequences = ArrayUtils.add_element(inputSequences, new int[] { j, i });
			}
		}

		double[] scores = new double[placements.length];

		// for each position calculate the score
		for (int i = 0; i < placements.length; i++) {
			scores[i] = Score.calculate_score(placements[i], weights);
		}

		// get index of position with lowest score (lower = better)
		int minIdx = min_idx(scores);

		// select position and input sequence with lowest score
		placement = placements[minIdx];
		inputSequence = inputSequences[minIdx];
		score = scores[minIdx];

		// calculate exact inputs necessary to move the piece
		calculate_inputs(board[0].length, piece[0].length);
	}

	private int min_idx(double[] values) {
		double min = values[0];
		int minIdx = 0;

		for (int i = 1; i < values.length; i++) {
			if (values[i] < min) {
				min = values[i];
				minIdx = i;
			}
		}

		return minIdx;
	}

}