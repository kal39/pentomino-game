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

	public void rotate(Board board){
		int oldLength = shape.length;
		int oldWidth = shape[0].length;
		int oldX = xPosition;
		int oldY = yPosition;
		int[][] boardShape = board.get_board();

		rotationId++;
		if(rotationId == 4){
			rotationId = 0;
		}

		shape = Representations.basicDatabase[blockId - 1][rotationId];

		if(oldLength > shape.length){
			yPosition += (oldLength - shape.length) / 2;
		}
		else if(shape.length > oldLength){
			yPosition -= (shape.length - oldLength) / 2;
		}

		if(oldWidth > shape[0].length){
			xPosition += (oldWidth - shape[0].length) / 2;
		}
		else if(shape[0].length > oldWidth){
			xPosition -= (shape[0].length - oldWidth) / 2;
		}

		if(xPosition < 0){
			xPosition += Math.abs(xPosition);
		}
		else if(xPosition + (shape[0].length - 1) > 4){
			xPosition -= (xPosition + (shape[0].length - 1)) - 4;
		}

		boolean check = false;
		for(int r = 0; r < shape.length; r ++){
			for(int c = 0; c < shape[0].length; c++){
				if(shape[r][c] != 0 && boardShape[r + yPosition][c + xPosition] != 0){
					check = true;
				}
			}
		}

		if(check){
			rotationId--;
			if(rotationId < 0){
				rotationId = 3;
			}
			shape = Representations.basicDatabase[blockId - 1][rotationId];
			xPosition = oldX;
			yPosition = oldY;
		}



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