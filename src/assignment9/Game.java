package assignment9;

import java.awt.event.KeyEvent;

import edu.princeton.cs.introcs.StdDraw;

public class Game {
	private Snake snake;
	private Food food;
	private int score = 0;
	public Game() {
		StdDraw.enableDoubleBuffering();
		snake = new Snake();
		food = new Food();
		//FIXME - construct new Snake and Food objects
	}
	
	public void play() {
		// Intro screen
		StdDraw.clear();
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(0.5, 0.6, "Welcome to Snake!");
		StdDraw.text(0.5, 0.5, "Use W A S D to move");
		StdDraw.text(0.5, 0.4, "Press SPACE to start");
		StdDraw.show();
	

		// Wait until space is pressed
		while (!StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
			StdDraw.pause(10); 
		}
		
		//Game starts here
		//Game ends when snake is no longer inbound. (break while loop)
		while (snake.isInbounds()) { // Game continues while snake is in bounds
			int dir = getKeypress();

			// Change direction if valid key was pressed
			if (dir != -1) {
				snake.changeDirection(dir);
			}

			snake.move();

			// Check if the snake eats the food
			if (snake.eatFood(food)) {
				score++; 
				food = new Food(); // Spawn new food
			}

			updateDrawing();
		
		}
		//Game over title screen
		StdDraw.clear();
		StdDraw.text(0.5, 0.5, "Game Over!");
		StdDraw.show();
	}
	
	private int getKeypress() {
		if(StdDraw.isKeyPressed(KeyEvent.VK_W)) {
			return 1;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_S)) {
			return 2;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_A)) {
			return 3;
		} else if (StdDraw.isKeyPressed(KeyEvent.VK_D)) {
			return 4;
		} else {
			return -1;
		}
	}
	
	/**
	 * Clears the screen, draws the snake and food, pauses, and shows the content
	 */
	private void updateDrawing() {
		//FIXME
		StdDraw.clear();
		snake.draw();
		food.draw();
		
		// Draw score at the top-left corner
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.textLeft(0.02, 0.98, "Score: " + score);

		StdDraw.pause(50);
		StdDraw.show();

	}
	
	public static void main(String[] args) {
		Game g = new Game();
		g.play();
	}
}
