package pentomino.leaderboard;

import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class tableMaking {
	JFrame f;

	/*
	 * makes a table and opens it in a different JFrame
	 * 
	 */
	tableMaking() {
		f = new JFrame("Leaderboard");
		String data[][] = { { leaderboard.leaderboardNames[0], (leaderboard.leaderboardValues[0] + "") },
				{ leaderboard.leaderboardNames[1], (leaderboard.leaderboardValues[1] + "") },
				{ leaderboard.leaderboardNames[2], (leaderboard.leaderboardValues[2] + "") },
				{ leaderboard.leaderboardNames[3], (leaderboard.leaderboardValues[3] + "") },
				{ leaderboard.leaderboardNames[4], (leaderboard.leaderboardValues[4] + "") }, };
		String column[] = { "Name", "Score" };
		JTable jt = new JTable(data, column);
		jt.setBounds(30, 40, 200, 300);
		jt.setBackground(Color.black);
		jt.setForeground(Color.red);
		jt.setGridColor(Color.black);
		jt.setFont(new Font("serif", Font.PLAIN, 15));
		JScrollPane sp = new JScrollPane(jt);
		f.add(sp);
		f.setSize(300, 150);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
	}

	public static void make_Table() {
		new tableMaking();
	}
}