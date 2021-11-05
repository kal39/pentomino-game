package pentomino.game;

import pentomino.ui.Input;
import pentomino.ui.UI;

public class Program {
	public static void main(String[] args) {

		// create a new board object and UI
		Board board = new Board();
		UI ui = new UI(35);

		// keep the game running as long as the top row isn't filled
		while (board.game_is_running()) {

			// create a new pentomino block object and display it
			Block block = new Block();
			ui.set_board(board.get_state(block));

			long prevTime = 0;
			// as long as the block hasn't reached the bottom or collides with other block
			while (board.bottom_check(block) && board.collision_check(block)) {
				//Process keyboard input
				if(Input.is_left_pressed()){
					block.move_left(board);
					ui.set_board(board.get_state(block));
				}
				if(Input.is_right_pressed()){
					block.move_right(board);
					ui.set_board(board.get_state(block));
				}

				long currTime = System.currentTimeMillis();
				if (currTime - prevTime > 700) {
					//move the block down by one row
					block.move_down();

					//display the new state
					ui.set_board(board.get_state(block));
					prevTime = currTime;
				}

			}

			// permanently add the block to the board once it has landed or collided
			board.update_board(block);
			block = null;
		}
	}

}
