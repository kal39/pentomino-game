package pentomino.bot;

import pentomino.game.Block;
import pentomino.game.Board;

public class Bot {
	double[] weights;

	public Bot(double[] weights) {
		this.weights = weights.clone();
	}

	public double[] simulate_cases(Block gameBlock, Board gameBoard) {
		Block simulationBlock = gameBlock.clone();
		int previousYPos;
		int rotationCount = 0;
		double[] result = { 999999, 0, 0 };

		// for every rotation
		for (int i = 0; i < 4; i++) {

			previousYPos = simulationBlock.get_yPos();
			simulationBlock.set_xPos(0);

			// for every possible X-Position
			while (simulationBlock.get_xPos() != 6 - simulationBlock.get_width()) {
				Board simulationBoard = gameBoard.clone();

				// simulate the block falling down entirely
				while (simulationBoard.bottom_check(simulationBlock)
						&& simulationBoard.collision_check(simulationBlock)) {
					simulationBlock.set_yPos(simulationBlock.get_yPos() + 1);
				}

				simulationBoard.update_board(simulationBlock);

				double subtraction = simulationBoard.clear_lines();

				int[][] test = simulationBoard.get_board();

				// calculates score and sets it as the new minimum score if smaller than current
				// score
				double score = Score.calculate_Score(test, weights) + weights[0] * subtraction;
				if (score < result[0]) {
					result[0] = score;
					result[1] = rotationCount;
					result[2] = simulationBlock.get_xPos();
				}
				simulationBlock.set_yPos(previousYPos);
				simulationBlock.set_xPos(simulationBlock.get_xPos() + 1);
			}
			simulationBlock.rotate(gameBoard);
			rotationCount++;
		}
		return result;
	}

	public void move_into_position(Block gameBlock, Board gameBoard, double[] placement) {
		for (int i = 0; i < placement[1]; i++) {
			gameBlock.rotate(gameBoard);
		}

		if (placement[2] < gameBlock.get_xPos()) {
			int offset = (int) (gameBlock.get_xPos() - placement[2]);

			for (int i = 0; i < offset; i++) {
				gameBlock.move_left(gameBoard);
			}
		} else if (placement[2] > gameBlock.get_xPos()) {
			int offset = (int) (placement[2] - gameBlock.get_xPos());

			for (int i = 0; i < offset; i++) {
				gameBlock.move_right(gameBoard);
			}
		}
	}
}