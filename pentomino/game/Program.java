package pentomino.game;

import pentomino.ui.Input;
import pentomino.ui.UI;

public class Program {
	final static int GRID_SIZE = 40;
	final static int GAME_SPEED = 500;

	public static void main(String[] args) {

		// create a new board object and UI
		Board board = new Board();
		UI ui = new UI(GRID_SIZE);

		Block nextBlock = new Block();
		int score = 0;

		// keep the game running as long as the top row isn't filled
		while (board.game_is_running()) {

			// create a new pentomino block object and display it
			Block block = nextBlock;
			nextBlock = new Block();
			ui.set_board(board.get_state(block));

			ui.set_next_piece(nextBlock.id);
			ui.set_score(score);

			long prevTime = 0;
			boolean canFall = true;
			boolean drop = false;

			while (canFall) {
				long currTime = System.currentTimeMillis();

				// Process keyboard input
				if (!drop) {
					if (Input.is_left_pressed()) {
						block.move_left(board);
						ui.set_board(board.get_state(block));
					}
					if (Input.is_right_pressed()) {
						block.move_right(board);
						ui.set_board(board.get_state(block));
					}
					if (Input.is_drop_pressed()) {
						drop = true;
					}
					if (Input.is_rotate_pressed()) {
						block.rotate(board);
						ui.set_board(board.get_state(block));
					}
				}

				if (currTime - prevTime > (drop ? GAME_SPEED / 100 : GAME_SPEED)) {
					// move the block down by one row
					canFall = block.move_down(board);

					// display the new state
					ui.set_board(board.get_state(block));
					prevTime = currTime;
				}

				if (!canFall)
					drop = false;

				score += board.detect_line();

			}

			// permanently add the block to the board once it has landed or collided
			board.update_board(block);
			block = null;
		}
	}

}
