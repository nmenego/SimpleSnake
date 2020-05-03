package com.snake;

import javax.swing.*;
import java.awt.*;

public class SnakeGame extends JFrame {

	public SnakeGame() {
		add(new SnakeGamePanel());

		setResizable(false);
		pack();

		setTitle("com.snake.SnakeGame Game");
		setLocationRelativeTo(null);
		setSize(1000, 1000);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(() -> {
			JFrame ex = new SnakeGame();
			ex.setVisible(true);
		});
	}
}
