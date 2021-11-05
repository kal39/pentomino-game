package pentomino.ui;

public class Runner {
	public static void main(String[] args) {
		UI ui = new UI(50);

		int[][] BOARD = new int[15][5];

		int i = 0;
		long prevTime = 0;

		while (true) {
			long currTime = System.currentTimeMillis();

			if (currTime - prevTime > 100) {
				board[i / 5][i % 5] = i % 12 + 1;
				i++;
				prevTime = currTime;
			}

			ui.set_board(board);
			ui.set_score(i * 100);
			ui.set_high_score(i * i);
			ui.set_next_piece(i % 12 + 1);
		}

	}
}
