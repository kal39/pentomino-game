package pentomino.bot;

import pentomino.game.*;

public class BotTest {
	public static void main(String[] args) {
		double[] weights = new double[] { -0.9, -0.05, -0.38, 0.26, 0.73 };
		int times = 10;

		long t1 = System.nanoTime();
		double score = test_times(1, times, weights);
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

			// System.out.println(score);
			total += score;
			if (score > max)
				max = score;
		}

		// System.out.println("Average: " + total / times + ", max: " + max);

		return total / times;
	}

	public static int test_bot1(double[] weights) {
		Bot bot = new Bot(weights);
		Board board = new Board();
		Block nextBlock = new Block();
		int score = 0;

		while (board.game_is_running()) {

			Block block = nextBlock;
			nextBlock = new Block();

			double[]optimumPlacement = bot.simulate_cases(block, board);
			//move the block into the right position and rotation
			bot.move_into_position(block, board, optimumPlacement);

			while (board.collision_check(block) && block.get_yPos() != 19 - block.get_length()) {
				block.move_down(board);
			}

			board.update_board(block);
			score += board.detect_line();
		}
		return score;
	}

	public static int test_bot2(double[] weights) {
		Bot2 bot2 = new Bot2(weights);
		Board board = new Board();
		Block nextBlock = new Block();
		int score = 0;

		while (board.game_is_running()) {
			Block block = nextBlock;
			nextBlock = new Block();
			
			double[] optimumPlacement = bot2.simulate_cases2(block, nextBlock, board);
			bot2.move_into_position(block, board, optimumPlacement);

			while (board.collision_check(block) && block.get_yPos() != 19 - block.get_length()) {
				block.move_down(board);
			}
			
			board.update_board(block);
			score += board.detect_line();
		}
		return score;
	}
}
