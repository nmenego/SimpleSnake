package com.snake;

public class SnakeSegment extends Point {
	private Direction direction;
	public SnakeSegment(int x, int y, Direction direction) {
		super(x, y);
		this.direction = direction;
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}
}
