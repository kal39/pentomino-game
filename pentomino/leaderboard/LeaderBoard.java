package pentomino.leaderboard;

import java.util.Collections;
import java.util.ArrayList;

public class LeaderBoard {
	ArrayList<Integer> scores = new ArrayList<Integer>(0);

	public void add_score(int score) {
		scores.add(score);
		Collections.sort(scores);
	}

	public int get_score(int idx) {
		if (idx > scores.size() - 1)
			return 0;
		return scores.get(scores.size() - idx - 1);
	}
}
