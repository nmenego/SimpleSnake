package com.snake;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Snake {
	int segmentSize;
	List<SnakeSegment> segments;


	public Snake(int segmentSize, SnakeSegment... snakeSegments) {
		this.segmentSize = segmentSize;
		this.segments = new LinkedList<>();
		this.segments.addAll(Arrays.asList(snakeSegments));
	}

	public SnakeSegment getSnakeHead() {
		return segments.get(segments.size() - 1);
	}

	public void moveSnake(Direction direction) {
		for (int index = 0; index < segments.size(); index++) {
			if (index + 1 == segments.size()) {
				segments.get(index).setDirection(direction);
			} else {
				segments.get(index).setDirection(segments.get(index + 1).getDirection());
			}

			switch (segments.get(index).getDirection()) {
				case UP:
					segments.get(index).y += -segmentSize;
					break;
				case RIGHT:
					segments.get(index).x += segmentSize;
					break;
				case DOWN:
					segments.get(index).y += segmentSize;
					break;
				case LEFT:
					segments.get(index).x += -segmentSize;
					break;
			}
		}
	}

	public boolean canEat(Point point) {
		int distX = Math.abs(point.x - getSnakeHead().x);
		int distY = Math.abs(point.y - getSnakeHead().y);
		// change threshold?
		return distX == 0 && distY == 0;
	}

	public void eat(Point food) {
		SnakeSegment tail = segments.get(0);
		SnakeSegment newTail = null;
		switch (tail.getDirection()) {
			case UP:
				newTail = new SnakeSegment(tail.x, tail.y + segmentSize, tail.getDirection());
				break;
			case RIGHT:
				newTail = new SnakeSegment(tail.x - segmentSize, tail.y, tail.getDirection());
				break;
			case DOWN:
				newTail = new SnakeSegment(tail.x, tail.y - segmentSize, tail.getDirection());
				break;
			case LEFT:
				newTail = new SnakeSegment(tail.x + segmentSize, tail.y, tail.getDirection());
				break;
		}
		segments.add(0, newTail);
	}

	public boolean eatsItself() {
		for (SnakeSegment snakeSegment: segments.subList(0, segments.size() - 2)) {
			if (canEat(snakeSegment)) return true;
		}
		return false;
	}
}
