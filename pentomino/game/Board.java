package pentomino.game;

public class Board {
	private int board[][];

	public Board() {
		board = new int[20][5];
	}

	public int[][] get_state(Block block){
		int[][] newBoard = ArrayUtils.copy(board);
		temporarily_update_board(block, newBoard);
		int[][] returnBoard = new int[15][5];

		for(int r = 0; r < returnBoard.length; r++){
			for(int c = 0; c < returnBoard[0].length; c++){
				returnBoard[r][c] = newBoard[r + 5][c];
			}
		}
		return returnBoard;
	}

	private void temporarily_update_board(Block block, int[][] matrix) {
		for (int i = 0; i < block.get_length(); i++) {
			for (int j = 0; j < block.get_width(); j++) {
				if (block.get_shape()[i][j] == 1) {
					matrix[block.get_yPos() + i][block.get_xPos() + j] = block.get_blockId();
				}

			}
		}
	}

	public void update_board(Block block) {
		for (int i = 0; i < block.get_length(); i++) {
			for (int j = 0; j < block.get_width(); j++) {
				if (block.get_shape()[i][j] == 1) {
					board[block.get_yPos() + i][block.get_xPos() + j] = block.get_blockId();
				}

			}
		}
	}

	public boolean collision_check(Block block) {
		for (int r = 0; r < block.get_length(); r++) {
			for (int c = 0; c < block.get_width(); c++) {
				if (block.get_shape()[r][c] == 1 && board[block.get_yPos() + r + 1][block.get_xPos() + c] != 0) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean bottom_check(Block block) {
		if (block.get_yPos() != board.length - block.get_length()) {
			return true;
		}
		return false;
	}

	public boolean game_is_running() {
		for (int i = 0; i < board[0].length; i++) {
			if (board[5][i] != 0) {
				return false;
			}
		}
		return true;
	}

	/*
	 * Infrastructure
	 * 
	 * private int[] rows_to_delete(){ int[] rows = new int[0]; for(int r = 0; r <
	 * board.length; r++){ boolean check = true; for(int c = 0; c < board[0].length;
	 * c++){ if(board[r][c] == 0){ check = false; } } if(check == true){ rows =
	 * ArrayUtils.add_element(rows, r); } } return rows; }
	 * 
	 * public int delete_full_rows(){ int[] rows = rows_to_delete();
	 * 
	 * if(rows.length > 0){ board = ArrayUtils.remove_row(board, rows); int[][]
	 * refill = new int[rows.length][5]; board = ArrayUtils.add(refill, board); }
	 * 
	 * return rows.length; }
	 */

}
