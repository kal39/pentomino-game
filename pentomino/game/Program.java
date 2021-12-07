package pentomino.game;

import pentomino.ui.Input;
import pentomino.ui.Sound;
import pentomino.ui.UI;
import pentomino.bot.Bot1;
import pentomino.bot.Bot2;
import pentomino.leaderboard.LeaderBoard;

public class Program {
	final static int GRID_SIZE = 40;
	final static int GAME_SPEED = 500;
	final static int DROP_SPEED = 1;
	final static int FALL_SPEED = 50;
	final static int BOT_DROP_SPEED = 10;

	static LeaderBoard leaderBoard = new LeaderBoard();

	public static void main(String[] args) {

		UI ui = new UI(GRID_SIZE);

		while (true) {

			// start menu
			while (true) {
				ui.set_title();
				ui.redraw();
				if (Input.is_rotate_pressed()) {
					ui.set_game();
					human_game(ui);
					break;
				}
				if (Input.is_left_pressed()) {
					ui.set_game();
					bot_game(ui);
					break;
				}
				if (Input.is_right_pressed()) {
					ui.set_game();
					bot2_game(ui);
					break;
				}
				if (Input.is_fall_pressed()) {
					System.exit(0);
					break;
				}
			}

			// loose menu
			while (true) {
				ui.set_loose();
				ui.redraw();
				if (Input.is_rotate_pressed()) {
					break;
				}
			}
		}
	}

	private static void human_game(UI ui) {
		// Sound sound = new Sound();
		Board board = new Board();
		Block nextBlock = new Block();
		int score = 0;

		// keep the game running as long as the top row isn't filled
		while (board.game_is_running()) {

			// create a new pentomino block object and display it
			Block block = nextBlock;
			nextBlock = new Block();

			long prevTime = 0;
			boolean canFall = true;
			boolean drop = false;

			while (canFall) {
				long currTime = System.currentTimeMillis();

				// Process keyboard input
				if (!drop) {
					if (Input.is_left_pressed())
						block.move_left(board);

					if (Input.is_right_pressed())
						block.move_right(board);

					if (Input.is_drop_pressed())
						drop = true;

					if (Input.is_rotate_pressed()) {
						block.rotate(board);
						try {
							Sound.rotate_sound();
						} catch (Exception e) {
						}
					}

				}

				if (currTime - prevTime > (drop ? DROP_SPEED : Input.is_fall_down() ? FALL_SPEED : GAME_SPEED)) {
					// move the block down by one row
					canFall = block.move_down(board);

					// display the new state
					ui.set_board(board.get_state(block));
					prevTime = currTime;
				}

				if (!canFall) {
					drop = false;
				}

				int clearedLines = board.detect_line();

				if (clearedLines != 0) {
					score += clearedLines;
					Sound.clear_sound();
				}

				ui.set_board(board.get_state(block));
				ui.set_next_piece(nextBlock.id);
				ui.set_score(score);
				ui.set_leader_board(leaderBoard);
				ui.redraw();

			}

			Sound.fall_sound();

			// permanently add the block to the board once it has landed or collided
			board.update_board(block);
			block = null;
		}

		Sound.game_over_sound();
		leaderBoard.add_score(score);
	}

	private static void bot_game(UI ui) {
		Bot1 bot = new Bot1(new double[] { -0.90, -0.05, -0.38, 0.26, 0.73 });

		Board board = new Board();
		Block nextBlock = new Block();
		int score = 0;

		// keep the game running as long as the top row isn't filled
		while (board.game_is_running()) {

			// create a new pentomino block object and display it
			Block block = nextBlock;
			nextBlock = new Block();

			bot.optimal_move(board.get_board(), block.shape);

			long prevTime = 0;
			boolean canFall = true;
			boolean drop = false;

			while (canFall) {
				long currTime = System.currentTimeMillis();

				// Process keyboard input
				if (!drop) {
					if (bot.is_left_pressed()) {
						block.move_left(board);
					}
					if (bot.is_right_pressed()) {
						block.move_right(board);
					}
					if (bot.is_drop_pressed()) {
						drop = true;
					}
					if (bot.is_rotate_pressed()) {
						block.rotate(board);
					}
				}

				if (currTime - prevTime > (drop ? BOT_DROP_SPEED : GAME_SPEED)) {
					// move the block down by one row
					canFall = block.move_down(board);

					// display the new state
					ui.set_board(board.get_state(block));
					prevTime = currTime;
				}

				if (!canFall) {
					drop = false;
				}

				score += board.detect_line();

				ui.set_board(board.get_state(block));
				ui.set_next_piece(nextBlock.id);
				ui.set_score(score);
				ui.set_leader_board(leaderBoard);
				ui.redraw();

			}

			// permanently add the block to the board once it has landed or collided
			board.update_board(block);
			block = null;
		}

		leaderBoard.add_score(score);
	}

	private static void bot2_game(UI ui) {
		Bot2 bot = new Bot2(new double[] { -0.94, -0.30, 0.52, 0.32, -0.11 });

		Board board = new Board();
		Block nextBlock = new Block();
		int score = 0;

		// keep the game running as long as the top row isn't filled
		while (board.game_is_running()) {

			// create a new pentomino block object and display it
			Block block = nextBlock;
			nextBlock = new Block();

			bot.optimal_move(board.get_board(), block.shape, nextBlock.shape);

			long prevTime = 0;
			boolean canFall = true;
			boolean drop = false;

			while (canFall) {
				long currTime = System.currentTimeMillis();

				// Process keyboard input
				if (!drop) {
					if (bot.is_left_pressed()) {
						block.move_left(board);
					}
					if (bot.is_right_pressed()) {
						block.move_right(board);
					}
					if (bot.is_drop_pressed()) {
						drop = true;
					}
					if (bot.is_rotate_pressed()) {
						block.rotate(board);
					}
				}

				if (currTime - prevTime > (drop ? BOT_DROP_SPEED : GAME_SPEED)) {
					// move the block down by one row
					canFall = block.move_down(board);

					// display the new state
					ui.set_board(board.get_state(block));
					prevTime = currTime;
				}

				if (!canFall) {
					drop = false;
				}

				score += board.detect_line();

				ui.set_board(board.get_state(block));
				ui.set_next_piece(nextBlock.id);
				ui.set_score(score);
				ui.set_leader_board(leaderBoard);
				ui.redraw();

			}

			// permanently add the block to the board once it has landed or collided
			board.update_board(block);
			block = null;
		}

		leaderBoard.add_score(score);
	}

}
