package pentomino.bot;

public class Bot1 extends Bot {
	public Bot1(double[] aWeights) {
		super(aWeights);
	}

	public void optimal_move(int[][] board, int[][] piece) {
		int[][][] placements = PlacementGenerator.generate_all_placements(board, piece);
		int[][] inputSequences = PlacementGenerator.generate_all_input_sequences(board, piece);
		double[] scores = new double[placements.length];

		for (int i = 0; i < placements.length; i++) {
			scores[i] = Score.calculate_score(placements[i], weights);
		}

		int minIdx = min_idx(scores);
		placement = placements[minIdx];
		inputSequence = inputSequences[minIdx];
		score = scores[minIdx];
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