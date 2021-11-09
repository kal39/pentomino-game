package pentomino.leaderboard;

public class leaderboard {
	/**
	 * makes two arrays, one that strores Strings for the leaderboard and one that
	 * stores integers as the scores
	 */
	public static String[] leaderboardNames = new String[5];
	public static int[] leaderboardValues = new int[5];

	/*
	 * checks if the score you have achieved is higher then any on the leaderboard
	 */
	public static void check_For_Score(String name, int newScore) {
		int place = -1;
		for (int i = 0; i < leaderboardValues.length; i++) {
			if (newScore > leaderboardValues[i]) {
				place = i;
				update_Leaderboard(name, newScore, place);
				break;
			}
		}
	}

	/*
	 * Updates the leaderboard by moving the values down and placing the new value
	 * in
	 */
	public static void update_Leaderboard(String name, int newScore, int place) {
		for (int i = (leaderboardValues.length - 2); i >= place; i--) {
			leaderboardValues[i + 1] = leaderboardValues[i];
			leaderboardNames[i + 1] = leaderboardNames[i];
		}
		leaderboardNames[place] = name;
		leaderboardValues[place] = newScore;
	}
}