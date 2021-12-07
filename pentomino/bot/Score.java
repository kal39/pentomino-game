package pentomino.bot;

public class Score {
	public static double calculate_Score(int[][] Matrix, double weight2, double weight3, double weight4,
			double weight5) {
		double newscore = 0;
		newscore += (weight2 * height(Matrix));
		newscore += (weight3 * totalHeight(Matrix));
		newscore += (weight4 * bumpyness(Matrix));
		newscore += (weight5 * Holes_Counter_1(Matrix));

		return newscore;
	}

	private static double totalHeight(int[][] Matrix) {
		double h = 0;
		for (int j = 0; j < Matrix[0].length; j++) {
			for (int i = 0; i < Matrix.length; i++) {
				if (Matrix[i][j] != 0) {
					h += (Matrix.length - i);
					break;
				}
			}
		}
		return h;
	}

	private static double bumpyness(int[][] Matrix) {
		double bumpy = 0;
		int[] arr = new int[Matrix[0].length];
		for (int j = 0; j < Matrix[0].length; j++) {
			for (int i = 0; i < Matrix.length; i++) {
				if (Matrix[i][j] != 0) {
					arr[j] = (Matrix.length - i);
					break;
				}
			}
		}
		for (int k = 0; k < arr.length - 1; k++) {
			int a = arr[k] - arr[k + 1];
			bumpy += Math.abs(a);
		}

		return bumpy;
	}

	private static double height(int[][] M) {
		for (int r = 0; r < M.length; r++) {
			for (int c = 0; c < M[0].length; c++) {
				if (M[r][c] != 0)
					return (M.length - r);
			}
		}
		return 0;
	}

	private static double Holes_Counter_1(int[][] Matrix) {
		double points = 0;
		for (int j = 0; j < Matrix[0].length; j++) {
			for (int i = 0; i < Matrix.length; i++) {
				if (Matrix[i][j] != 0) {
					points += Search_For_Holes_1(Matrix, i, j);
					break;
				}
			}
		}
		return points;
	}

	private static double Search_For_Holes_1(int[][] Matrix, int i, int j) {
		double hole = 0;
		for (int k = i + 1; k < Matrix.length; k++) {
			if (Matrix[k][j] == 0)
				hole++;
		}
		return hole;
	}

}
