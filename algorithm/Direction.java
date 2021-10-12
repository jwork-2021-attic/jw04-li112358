package algorithm;

public enum Direction {
	LEFT,RIGHT,UP,DOWN,DEFAULT;
	
	public Direction directionBack() {
		switch(this) {
		case LEFT:return RIGHT;
		case RIGHT:return LEFT;
		case UP:return DOWN;
		case DOWN:return UP;
		case DEFAULT:return DEFAULT;
		}
		return DEFAULT;
	}
}
