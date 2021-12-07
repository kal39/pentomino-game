package pentomino.bot;

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
}
