package tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class GameController extends JFrame {
	private GameLoop gameLoop;
	private GamePanel panel;
	private Board board;
	private Timer timer;

	public GameController(final int width, final int height) throws HeadlessException {
		super();
		panel = new GamePanel();
		
		this.add(panel);
		this.setSize(width, height);

		board = new Board(height / 100, width / 100);
		
		timer = new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				if (!board.hasFalling()) {
					board.drop(Tetromino.I_SHAPE);
				} else {
					board.tick();
				}
			}}, 0, 1000);

		gameLoop = new GameLoop() {
			private Image doubleBufferImage;
			private Graphics doubleBufferGraphics;

			@Override
			public void printScreen() {
				panel.getGraphics().drawImage(doubleBufferImage, 0, 0, null);
			}

			@Override
			public void gameRender() {
				if (doubleBufferImage == null) {
					doubleBufferImage = panel.createImage(width, height);
				}
				if (doubleBufferImage == null) {
					return;
				} else {
					doubleBufferGraphics = doubleBufferImage.getGraphics();
				}
				doubleBufferGraphics.setColor(Color.BLACK);
				doubleBufferGraphics.fillRect(0, 0, width, height);
				for (int row = 0; row < board.getRows(); row++) {
					for (int col = 0; col < board.getColumns(); col++) {
						char c = board.toString().replace("\n", "").charAt(row * board.getColumns() + col);
						if (c != '.') {
							doubleBufferGraphics.setColor(Color.BLUE);
							doubleBufferGraphics.fillRect(col * 100,  row * 100, 100, 100);
						}
					}
				}

				doubleBufferGraphics.setColor(Color.BLACK);
			}

			@Override
			public void gameUpdate() {
				// System.out.println("Game is running!!");
			}
		};
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {}

			@Override
			public void windowDeactivated(WindowEvent arg0) {}

			@Override
			public void windowClosed(WindowEvent arg0) {}

			@Override
			public void windowClosing(WindowEvent arg0) {
				gameLoop.stop();
			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {}

			@Override
			public void windowIconified(WindowEvent arg0) {}

			@Override
			public void windowOpened(WindowEvent arg0) {
				gameLoop.start();
			}
		});
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyPressed(KeyEvent arg0) {
				if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
					board.moveDown();
				} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					board.moveLeft();
				} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					board.moveRight();
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
	}

	public static void main(String[] args) {
		GameController controller = new GameController(800, 600);
		controller.setVisible(true);
	}

}
