package tetris.impl;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GameController extends JFrame implements BoardEventListener {

	private static final long serialVersionUID = -5592605328768740246L;
	private GameLoop gameLoop;
	private GamePanel panel;
	private Board board;
	private ScoreCounter counter;
	private Timer timer;
	private Stack<Runnable> action_stack;

	public GameController(final int width, final int height) throws HeadlessException {
		super();
		panel = new GamePanel();
		
		this.add(panel);
		this.setSize(width, height);

		board = new Board(14, 7);
		board.setListener(this);
		counter = new ScoreCounter();
		
		action_stack = new Stack<Runnable>();
		
		timer = new Timer();
		timer.schedule(new TimerTask(){

			@Override
			public void run() {
				try {
					if (!board.hasFalling()) {
						int randomNumber = (int) (Math.random() * Tetromino.Tetrominos.length);
						board.drop(Tetromino.Tetrominos[randomNumber]);
					} else {
						board.tick();
					}
				} catch (CantDropTetrominoException e) {
					GameController.this.gameLoop.stop();
					GameController.this.timer.cancel();
					JOptionPane.showMessageDialog(GameController.this, "Game over!!");
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
				if (GameController.this.hasAction()) {
					GameController.this.popAction().run();
				}
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
				doubleBufferGraphics.setColor(Color.GRAY);
				doubleBufferGraphics.fillRect(0, 0, board.getColumns() * 20, board.getRows() * 20);
				for (int row = 0; row < board.getRows(); row++) {
					for (int col = 0; col < board.getColumns(); col++) {
						char c = board.toString().replace("\n", "").charAt(row * board.getColumns() + col);
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
				char[] scoreChar = ("Score: " + Integer.toString(counter.getScore())).toCharArray();
				char[] comboChar = ("Combo: " + Integer.toString(counter.getCurrentComboCount())).toCharArray();
				char[] maxComboChar = ("Max Combo: " + Integer.toString(counter.getMaxAccumulatedComboCount())).toCharArray();
				doubleBufferGraphics.drawChars(scoreChar, 0, scoreChar.length, (board.getColumns() + 1) * 20, 20);
				doubleBufferGraphics.drawChars(comboChar, 0, comboChar.length, (board.getColumns() + 1) * 20, 40);
				doubleBufferGraphics.drawChars(maxComboChar, 0, maxComboChar.length, (board.getColumns() + 1) * 20, 60);
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
				System.exit(0);
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
					action_stack.add(new Runnable() {
						@Override
						public void run() {
							board.moveDown();
						}
					});
					action_stack.setSize(1);
				} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
					action_stack.add(new Runnable() {
						@Override
						public void run() {
							board.moveLeft();
						}
					});
					action_stack.setSize(1);
				} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
					action_stack.add(new Runnable() {
						@Override
						public void run() {
							board.moveRight();
						}
					});
					action_stack.setSize(1);
				} else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
					action_stack.add(new Runnable() {
						@Override
						public void run() {
							board.rotateRight();
						}
					});
					action_stack.setSize(1);
				} else if (arg0.getKeyCode() == KeyEvent.VK_SHIFT) {
					action_stack.add(new Runnable() {
						@Override
						public void run() {
							board.rotateLeft();
						}
					});
					action_stack.setSize(1);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {}

			@Override
			public void keyTyped(KeyEvent arg0) {}
		});
	}

	protected Runnable popAction() {
		return this.action_stack.pop();
	}

	protected boolean hasAction() {
		return this.action_stack.size() > 0;
	}

	public static void main(String[] args) {
		GameController controller = new GameController(300, 400);
		controller.setVisible(true);
	}

	@Override
	public void onDrop(Piece tShape) {}

	@Override
	public void onTick() {}

	@Override
	public void onMoveDown() {}

	@Override
	public void onMoveRight() {}

	@Override
	public void onMoveLeft() {}

	@Override
	public void onRotateRight() {}

	@Override
	public void onRotateLeft() {}

	@Override
	public void onClean(int clearedLines) {
		counter.receiveNumberOfClearedLines(clearedLines);
	}

}
