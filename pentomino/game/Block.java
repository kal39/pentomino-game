package pentomino.game;

import java.util.Random;

public class Block {
	private static Random rand = new Random();

	int[][] shape;
	int blockId;
	int xPosition;
	int yPosition;
	int rotationId;
	private static int boardWidth = 5;

	public Block() {
		rotationId = rand.nextInt(4);
		blockId = rand.nextInt(12) + 1;
		shape = Representations.basicDatabase[blockId - 1][rotationId];
		xPosition = ((5 - shape[0].length) / 2);
		yPosition = 5 - shape.length;
	}

	public void rotate(){
		//rotate the block here
	}

	public void move_down() {
		yPosition++;
	}

	public void move_right(Board board){
		if(xPosition + shape[0].length != boardWidth && board.perpendicular_collision_right(shape.length, shape[0].length, xPosition, yPosition, shape)){
			xPosition++;
		}
	}

	public void move_left(Board board){
		if(xPosition != 0 && board.perpendicular_collision_left(shape.length, shape[0].length, xPosition, yPosition, shape)){
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