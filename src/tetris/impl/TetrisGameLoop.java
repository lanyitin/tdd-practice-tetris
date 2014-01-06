package tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class TetrisGameLoop extends GameLoop {
		private Image doubleBufferImage;
		private Graphics doubleBufferGraphics;
		private GameController controller;
		public TetrisGameLoop(GameController controller) {
			this.controller = controller;
		}
		@Override
		public void printScreen() {
			controller.getPanel().getGraphics().drawImage(doubleBufferImage, 0, 0, null);
		}

		@Override
		public void gameRender() {
			if (doubleBufferImage == null) {
				doubleBufferImage = controller.getPanel().createImage(controller.getPanel().getWidth(), controller.getPanel().getHeight());
			}
			if (doubleBufferImage == null) {
				return;
			} else {
				doubleBufferGraphics = doubleBufferImage.getGraphics();
			}
			doubleBufferGraphics.setColor(Color.BLACK);
			doubleBufferGraphics.fillRect(0, 0, controller.getPanel().getWidth(), controller.getPanel().getHeight());
			doubleBufferGraphics.setColor(Color.GRAY);
			doubleBufferGraphics.fillRect(0, 0, controller.getBoard().getColumns() * 20, controller.getBoard().getRows() * 20);
			for (int row = 0; row < controller.getBoard().getRows(); row++) {
				for (int col = 0; col < controller.getBoard().getColumns(); col++) {
					char c = controller.getBoard().toString().replace("\n", "").charAt(row * controller.getBoard().getColumns() + col);
					if (c != '.') {
						switch(c) {
							case 'T':
								doubleBufferGraphics.setColor(Color.YELLOW);
								break;
							case 'O':
								doubleBufferGraphics.setColor(Color.ORANGE);
								break;
							case 'Z':
								doubleBufferGraphics.setColor(Color.RED);
								break;
							case 'L':
								doubleBufferGraphics.setColor(Color.LIGHT_GRAY);
								break;
							default:
								doubleBufferGraphics.setColor(Color.BLUE);
						}
						doubleBufferGraphics.fillRect(col * 20,  row * 20, 20, 20);
					}
				}
			}

			doubleBufferGraphics.setColor(Color.MAGENTA);
			char[] scoreChar = ("Score: " + Integer.toString(controller.getCounter().getScore())).toCharArray();
			char[] comboChar = ("Combo: " + Integer.toString(controller.getCounter().getCurrentComboCount())).toCharArray();
			char[] maxComboChar = ("Max Combo: " + Integer.toString(controller.getCounter().getMaxAccumulatedComboCount())).toCharArray();
			doubleBufferGraphics.drawChars(scoreChar, 0, scoreChar.length, (controller.getBoard().getColumns() + 1) * 20, 20);
			doubleBufferGraphics.drawChars(comboChar, 0, comboChar.length, (controller.getBoard().getColumns() + 1) * 20, 40);
			doubleBufferGraphics.drawChars(maxComboChar, 0, maxComboChar.length, (controller.getBoard().getColumns() + 1) * 20, 60);
		}

		@Override
		public void gameUpdate() {
			controller.performActionIfQueueIsNotEmpty();
		}

}
