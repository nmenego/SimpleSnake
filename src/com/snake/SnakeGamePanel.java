package com.snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGamePanel extends JPanel implements ActionListener {

	public static final int PIXEL = 25;
	public static final int X_BOUND = 1000;
	public static final int Y_BOUND = 1000;

	private Point food;
	private Snake snake;
	private final int DELAY = 100;
	private Timer timer;
	private Direction snakeDirection = Direction.RIGHT;
	private int score = 0;
	private boolean isAlive = true;

	public SnakeGamePanel() {
		super();

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				int key = e.getKeyCode();

				if (key == KeyEvent.VK_UP) {
					snakeDirection = Direction.UP;
				} else if (key == KeyEvent.VK_RIGHT) {
					snakeDirection = Direction.RIGHT;
				} else if (key == KeyEvent.VK_DOWN) {
					snakeDirection = Direction.DOWN;
				} else if (key == KeyEvent.VK_LEFT) {
					snakeDirection = Direction.LEFT;
				}
			}
		});
		setFocusable(true);

		generateFoodIfNotThere();
		initializeSnake();

		timer = new Timer(DELAY, this);
		timer.start();
	}

	private void initializeSnake() {
		this.snake = new Snake(PIXEL, new SnakeSegment(0, 0, Direction.RIGHT), new SnakeSegment(1 * PIXEL, 0, Direction.RIGHT), new SnakeSegment(2 * PIXEL, 0, Direction.RIGHT), new SnakeSegment(3 * PIXEL, 0, Direction.RIGHT));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (isAlive) {
			// draw food
			g.setColor(Color.BLUE);
			g.fillRect(food.x, food.y, PIXEL, PIXEL);


			// draw snake
			g.setColor(Color.BLACK);
			for (SnakeSegment snakeSegment : snake.segments) {
				g.fillRect(snakeSegment.x, snakeSegment.y, PIXEL, PIXEL);
			}

			g.drawString("Score: " + score, 400, 25);
		} else {

			String msg = "Game Over :( Your score was: " + score;
			Font small = new Font("Helvetica", Font.BOLD, 14);
			FontMetrics metr = getFontMetrics(small);

			g.setColor(Color.white);
			g.setFont(small);
			g.setColor(Color.RED);
			g.drawString(msg, (X_BOUND - metr.stringWidth(msg)) / 2, Y_BOUND / 2);

		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		generateFoodIfNotThere();
		moveSnake();
		checkDeath();
		generateFoodIfNotThere();
		repaint();
	}

	private void generateFoodIfNotThere() {
		if (food == null) {
			int randX = (int) Math.floor(Math.random() * 39) * 25;
			int randY = (int) Math.floor(Math.random() * 39) * 25;
			food = new Point(randX, randY);
		}
	}

	private void moveSnake() {
		snake.moveSnake(snakeDirection);
		if (snake.canEat(food)) {
			snake.eat(food);
			score++;
			food = null;
		}
	}

	private void checkDeath() {
		if (snake.getSnakeHead().x > X_BOUND || snake.getSnakeHead().x < 0 || snake.getSnakeHead().y > Y_BOUND || snake.getSnakeHead().y < 0) {
			isAlive = false;
		}
		if (snake.eatsItself()) {
			isAlive = false;
		}
	}
}