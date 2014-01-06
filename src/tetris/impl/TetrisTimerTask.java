package tetris.impl;

import java.util.TimerTask;

import javax.swing.JOptionPane;

public class TetrisTimerTask extends TimerTask {
	private GameController controller;

	public TetrisTimerTask(GameController gameController) {
		this.controller = gameController;
	}

	@Override
	public void run() {
		try {
			if (!controller.getBoard().hasFalling()) {
				int randomNumber = (int) (Math.random() * Tetromino.Tetrominos.length);
				controller.getBoard().drop(Tetromino.Tetrominos[randomNumber]);
			} else {
				controller.getBoard().tick();
			}
		} catch (CantDropTetrominoException e) {
			controller.getGameLoop().stop();
			JOptionPane.showMessageDialog(controller, "Game over!!");
			this.cancel();
		}
	}

}
