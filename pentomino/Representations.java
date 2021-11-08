package pentomino;

public class Representations {
	static int[][][] shapes = { //
			{ { 0, 1, 0 }, { 1, 1, 1 }, { 0, 1, 0 } }, // X
			{ { 1 }, { 1 }, { 1 }, { 1 }, { 1 } }, // I
			{ { 0, 1, 1 }, { 0, 1, 0 }, { 1, 1, 0 } }, // Z
			{ { 1, 1, 1 }, { 0, 1, 0 }, { 0, 1, 0 } }, // T
			{ { 1, 1 }, { 1, 0 }, { 1, 1 } }, // U
			{ { 1, 1, 1 }, { 1, 0, 0 }, { 1, 0, 0 } }, // V
			{ { 0, 0, 1 }, { 0, 1, 1 }, { 1, 1, 0 } }, // W
			{ { 1, 0 }, { 1, 1 }, { 1, 0 }, { 1, 0 } }, // Y
			{ { 1, 0 }, { 1, 0 }, { 1, 0 }, { 1, 1 } }, // L
			{ { 1, 1 }, { 1, 1 }, { 1, 0 } }, // P
			{ { 0, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 } }, // N
			{ { 0, 1, 1 }, { 1, 1, 0 }, { 0, 1, 0 } } // F
	};

	public static int[][] get_shape(int id) {
		return shapes[id];
	}
}