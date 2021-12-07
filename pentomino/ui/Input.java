package pentomino.ui;

public class Input {
	static boolean left_pressed = false;
	static boolean right_pressed = false;
	static boolean rotate_pressed = false;
	static boolean drop_pressed = false;
	static boolean fall_pressed = false;

	static boolean left_cooldown = false;
	static boolean right_cooldown = false;
	static boolean rotate_cooldown = false;
	static boolean drop_cooldown = false;
	static boolean fall_cooldown = false;

	static public boolean is_left_pressed() {
		if (left_pressed && !left_cooldown) {
			left_cooldown = true;
			return true;
		} else {
			return false;
		}
	}

	static public boolean is_right_pressed() {
		if (right_pressed && !right_cooldown) {
			right_cooldown = true;
			return true;
		} else {
			return false;
		}
	}

	static public boolean is_rotate_pressed() {
		if (rotate_pressed && !rotate_cooldown) {
			rotate_cooldown = true;
			return true;
		} else {
			return false;
		}
	}

	static public boolean is_drop_pressed() {
		if (drop_pressed && !drop_cooldown) {
			drop_cooldown = true;
			return true;
		} else {
			return false;
		}
	}

	static public boolean is_fall_pressed() {
		if (fall_pressed && !fall_cooldown) {
			fall_cooldown = true;
			return true;
		} else {
			return false;
		}
	}

	static public boolean is_fall_down() {
		return fall_pressed;
	}

	static public void press_left() {
		left_pressed = true;
	}

	static public void press_right() {
		right_pressed = true;
	}

	static public void press_rotate() {
		rotate_pressed = true;
	}

	static public void press_drop() {
		drop_pressed = true;
	}

	static public void press_fall() {
		fall_pressed = true;
	}

	static public void release_left() {
		left_pressed = false;
		left_cooldown = false;
	}

	static public void release_right() {
		right_pressed = false;
		right_cooldown = false;
	}

	static public void release_rotate() {
		rotate_pressed = false;
		rotate_cooldown = false;
	}

	static public void release_drop() {
		drop_pressed = false;
		drop_cooldown = false;
	}

	static public void release_fall() {
		fall_pressed = false;
		fall_cooldown = false;
	}
}
