package pentomino.bot;

public class Bot2 extends Bot {
	public Bot2(double[] aWeights) {
		super(aWeights);
	}

	public void optimal_move(int[][] board, int[][] piece, int[][] nextPiece) {
		// int[][][] placements = PlacementGenerator.generate_all_placements(board,
		// piece);
		// int[][] inputSequences =
		// PlacementGenerator.generate_all_input_sequences(board, piece);
		// double[] scores = new double[placements.length];

		// for (int i = 0; i < placements.length; i++) {
		// int[][][] placements2 =
		// PlacementGenerator.generate_all_placements(placements[i], nextPiece);
		// scores[i] = Score.calculate_score(placements2[0], weights);

		// for (int j = 1; j < placements2.length; j++) {
		// double score = Score.calculate_score(placements2[j], weights);
		// if (score < scores[i])
		// scores[i] = score;
		// }
		// }

		// int minIdx = min_idx(scores);
		// placement = placements[minIdx];
		// inputSequence = inputSequences[minIdx];
		// score = scores[minIdx];
		// calculate_inputs(board[0].length, piece[0].length);
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
