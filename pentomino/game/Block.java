package pentomino.game;

import java.util.Random;

public class Block {
	private static Random rand = new Random();

	int[][] shape;
	int blockId;
	int xPosition;
	int yPosition;
	private static int boardWidth = 5;

	public Block() {
		blockId = rand.nextInt(12) + 1;
		shape = Representations.basicDatabase[blockId - 1];
		xPosition = ((5 - shape[0].length) / 2);
		yPosition = 5 - shape.length;
	}

	public void move_down() {
		yPosition++;
	}

	/*
	 * Infrastructure
	 * 
	 * 
	 * 
	 * 
	 */

	public void move_right(){
		if(xPosition + shape[0].length != boardWidth){
			xPosition++;
		}
	}

	public void move_left(){
		if(xPosition != 0){
			xPosition--;
		}
	}

	public int[][] get_shape() {
		return shape;
	}

	public int get_blockId() {
		return blockId;
	}

	public int get_xPos() {
		return xPosition;
	}

	public int get_yPos() {
		return yPosition;
	}

	public int get_length() {
		return shape.length;
	}

	public int get_width() {
		return shape[0].length;
	}

}