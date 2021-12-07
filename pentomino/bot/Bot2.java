package pentomino.bot;

import pentomino.game.Block;
import pentomino.game.Board;

public class Bot2 extends Bot {
	public Bot2(double weight1, double weight2, double weight3, double weight4,
			double weight5) {
		super(weight1, weight2, weight3, weight4, weight5);
	}

	public double[] simulate_cases2(Block gameBlock, Block nextBlock, Board gameBoard) {
		Block simulationBlockOne = gameBlock.clone();
		Block simulationBlockTwo = nextBlock.clone();
		int previousYPos;
		int rotationCount = 0;
		double[] result = { 999999, 0, 0 };

		// for every rotation
		for (int i = 0; i < 4; i++) {

			previousYPos = simulationBlockOne.get_yPos();
			simulationBlockOne.set_xPos(0);

			// for every possible X-Position
			while (simulationBlockOne.get_xPos() != 6 - simulationBlockOne.get_width()) {

				// simulate the block falling down entirely
				while (gameBoard.bottom_check(simulationBlockOne) &&
						gameBoard.collision_check(simulationBlockOne)) {
					simulationBlockOne.set_yPos(simulationBlockOne.get_yPos() + 1);
				}
				Board simulationBoard = gameBoard.clone();
				simulationBoard.update_board(simulationBlockOne);
				double substraction = simulationBoard.clear_lines();

				// Get the best placement for second block
				double[] tempResult = super.simulate_cases(simulationBlockTwo,
						simulationBoard);
				double score = tempResult[0] - substraction;

				// If the simulated score is lower than the current one save the current
				// rotation and X-Pos of the first block
				if (score < result[0]) {
					result[0] = score;
					result[1] = rotationCount;
					result[2] = simulationBlockOne.get_xPos();
				}

				simulationBlockOne.set_yPos(previousYPos);
				simulationBlockOne.set_xPos(simulationBlockOne.get_xPos() + 1);
			}
			simulationBlockOne.rotate(gameBoard);
			rotationCount++;
		}
		return result;
	}

}
