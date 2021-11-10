package pentomino.leaderboard;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class HomeScreen {

	public static void main(String[] args) {
		MakeHomeFrame(5);
	}

	public static void MakeHomeFrame(int Score) {

		JFrame homeFrame = new JFrame("Title Menu");

		// make all the buttons, labels and textfield
		JButton QuitGame = new JButton("QUIT GAME");
		JButton PlayGame = new JButton("PLAY GAME");
		JButton simulatePlay = new JButton();
		JButton showLeaderboard = new JButton("LEADERBOARD");
		JButton submit = new JButton("SUBMIT");
		JLabel yourscore = new JLabel("");
		JLabel order = new JLabel("Enter your name and click on submit");
		JLabel highscoreText = new JLabel();
		JTextField fillName = new JTextField();

		/***
		 * for every attribute of the JFrame there is a method that will add it to the
		 * JFrame except for the highscore, because else it did not work
		 */
		AddTitle(homeFrame);
		AddPlayAndAStop(homeFrame, QuitGame, PlayGame);
		if (Score > 0) {
			Addscore(Score, homeFrame, yourscore);
			AddOrder(homeFrame, order);
			AddFillName(homeFrame, fillName);
			AddSubmit(homeFrame, submit);
		}
		AddLeaderboard(homeFrame, showLeaderboard);
		if (leaderboard.leaderboardNames[0] == null && Score == 0) {
			highscoreText.setText("There is no highscore yet");
			highscoreText.setBounds(60, 115, 200, 30);

		} else if (Score > leaderboard.leaderboardValues[0] || (leaderboard.leaderboardNames[0] == null && Score > 0)) {
			highscoreText.setText("NEW HIGHSCORE OF " + Score);
			highscoreText.setBounds(45, 115, 250, 30);
		} else {
			highscoreText.setText("Highscore is " + leaderboard.leaderboardValues[0] + " achieved by "
					+ leaderboard.leaderboardNames[0]);
			highscoreText.setBounds(30, 115, 250, 30);
		}
		highscoreText.setForeground(Color.red);
		highscoreText.setFont(new Font("serif", Font.PLAIN, 17));

		homeFrame.add(highscoreText);

		// thsis adds a simulate button
		Simulate(homeFrame, simulatePlay);

		// guits game if button is pressed
		QuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		// should play game if button is pressed
		PlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeFrame.setVisible(false);
				homeFrame.dispose();
			}
		});

		/***
		 * if pressed the "submit" button, first it will check if you have filled in a
		 * name if it is filled, it will set the textfield to uneiditable and update the
		 * leaderboard at last it changes your highscore text if you had achieved a new
		 * highscore
		 */
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String str = fillName.getText();
				if (str.equals("")) {

				} else {
					fillName.setEditable(false);
					leaderboard.check_For_Score(str, Score);
					fillName.setText("");
					if (Score > leaderboard.leaderboardValues[1]) {
						highscoreText.setText("Highscore is " + leaderboard.leaderboardValues[0] + " achieved by "
								+ leaderboard.leaderboardNames[0]);
						highscoreText.setBounds(30, 115, 250, 30);
					}
				}

			}
		});

		// if clicked it opens the leaderboard
		showLeaderboard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableMaking.make_Table();
			}
		});

		/***
		 * this simulates a game play, first it deletes this JFrame then it recalls this
		 * method with a random number as a new score
		 */
		simulatePlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeFrame.dispose();
				MakeHomeFrame(((int) (Math.random() * 20)));
			}
		});

		homeFrame.getContentPane().setBackground(Color.black);
		homeFrame.setSize(300, 350);
		homeFrame.setLayout(null);
		homeFrame.setLocationRelativeTo(null);
		homeFrame.setVisible(true);
		homeFrame.setDefaultCloseOperation(homeFrame.EXIT_ON_CLOSE);

		// if button is pressed quit the code
		QuitGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				homeFrame.dispose();
			}
		});

	}

	// creates the submit button
	public static void AddSubmit(JFrame homeFrame, JButton submit) {
		submit.setBounds(170, 210, 100, 40);
		submit.setForeground(Color.red);
		submit.setBackground(Color.black);
		submit.setBorderPainted(false);
		submit.setFont(new Font("serif", Font.BOLD, 15));
		submit.setFocusPainted(false);
		homeFrame.add(submit);
	}

	// creates the textfield where you fill in your name
	public static void AddFillName(JFrame homeFrame, JTextField fillName) {
		fillName.setBounds(10, 220, 150, 20);
		fillName.setForeground(Color.red);
		homeFrame.add(fillName);
	}

	// creates the sentence that you should fill in your name
	public static void AddOrder(JFrame homeFrame, JLabel order) {
		order.setBounds(20, 185, 300, 20);
		order.setFont(new Font("serif", Font.PLAIN, 17));
		order.setForeground(Color.red);
		homeFrame.add(order);
	}

	// creates a leaderboard button
	public static void AddLeaderboard(JFrame homeFrame, JButton showLeaderboard) {
		showLeaderboard.setBounds(40, 260, 200, 40);
		showLeaderboard.setForeground(Color.red);
		showLeaderboard.setBackground(Color.black);
		showLeaderboard.setBorderPainted(false);
		showLeaderboard.setFont(new Font("serif", Font.BOLD, 15));
		showLeaderboard.setFocusPainted(false);
		homeFrame.add(showLeaderboard);
	}

	// creates the label that says your score
	public static void Addscore(int Score, JFrame homeFrame, JLabel yourscore) {
		yourscore.setBounds(75, 155, 150, 20);
		yourscore.setFont(new Font("serif", Font.PLAIN, 17));
		yourscore.setText("Your score was: " + Score);
		yourscore.setForeground(Color.red);
		homeFrame.add(yourscore);
	}

	// creates a little button in the left up corner that will be deleted in the
	// final game
	public static void Simulate(JFrame homeFrame, JButton simulatePlay) {
		simulatePlay.setBounds(10, 10, 10, 10);

		homeFrame.add(simulatePlay);
	}

	// create the play and quit button
	public static void AddPlayAndAStop(JFrame homeFrame, JButton QuitGame, JButton PlayGame) {
		PlayGame.setBounds(5, 70, 125, 40);
		PlayGame.setForeground(Color.red);
		PlayGame.setBackground(Color.black);
		PlayGame.setBorderPainted(false);
		PlayGame.setFont(new Font("serif", Font.BOLD, 15));
		PlayGame.setFocusPainted(false);
		homeFrame.add(PlayGame);
		QuitGame.setBounds(145, 70, 125, 40);
		QuitGame.setForeground(Color.red);
		QuitGame.setBackground(Color.black);
		QuitGame.setBorderPainted(false);
		QuitGame.setFont(new Font("serif", Font.BOLD, 15));
		QuitGame.setFocusPainted(false);
		homeFrame.add(QuitGame);
	}

	// creates the title
	public static void AddTitle(JFrame homeFrame) {
		JLabel title = new JLabel("YOU DIED");
		title.setBounds(40, 10, 200, 40);
		title.setFont(new Font("serif", Font.BOLD, 30));
		title.setHorizontalAlignment(JLabel.CENTER);
		title.setForeground(Color.red);
		homeFrame.add(title);
		title.setVisible(true);
	}

}
