package tetris.impl;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentLinkedQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GameController extends JFrame implements BoardEventListener, KeyListener, WindowListener {

	private static final long serialVersionUID = -5592605328768740246L;
	private GameLoop gameLoop;
	private GamePanel panel;
	private Board board;
	private ScoreCounter counter;
	private Timer timer;
	private Queue<Runnable> action_queue;

	public GameController() throws HeadlessException {
		super();
		panel = new GamePanel();
		board = new Board(14, 7);
		board.setListener(this);
		counter = new ScoreCounter();
		action_queue = new ConcurrentLinkedQueue<Runnable>();
		timer = new Timer();
		gameLoop = new TetrisGameLoop(this);
		
		this.add(panel);
		this.setSize(300, 400);

		timer.schedule(new TetrisTimerTask(this), 0, 1000);

		this.addWindowListener(this);
		this.addKeyListener(this);
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public Board getBoard() {
		return this.board;
	}

	public ScoreCounter getCounter() {
		return this.counter;
	}
	
	public GameLoop getGameLoop() {
		return this.gameLoop;
	}


	protected Runnable popAction() {
		return this.action_queue.poll();
	}

	protected boolean hasAction() {
		return this.action_queue.size() > 0;
	}

	@Override
	public void onClean(int clearedLines) {
		counter.receiveNumberOfClearedLines(clearedLines);
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if (arg0.getKeyCode() == KeyEvent.VK_DOWN) {
			action_queue.add(new Runnable() {
				@Override
				public void run() {
					board.moveDown();
				}
			});
		} else if (arg0.getKeyCode() == KeyEvent.VK_LEFT) {
			action_queue.add(new Runnable() {
				@Override
				public void run() {
					board.moveLeft();
				}
			});
		} else if (arg0.getKeyCode() == KeyEvent.VK_RIGHT) {
			action_queue.add(new Runnable() {
				@Override
				public void run() {
					board.moveRight();
				}
			});
		} else if (arg0.getKeyCode() == KeyEvent.VK_CONTROL) {
			action_queue.add(new Runnable() {
				@Override
				public void run() {
					board.rotateRight();
				}
			});
		} else if (arg0.getKeyCode() == KeyEvent.VK_SHIFT) {
			action_queue.add(new Runnable() {
				@Override
				public void run() {
					board.rotateLeft();
				}
			});
		}
	}
	@Override
	public void windowClosing(WindowEvent arg0) {
		gameLoop.stop();
		System.exit(0);
	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		gameLoop.start();
	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void keyReleased(KeyEvent arg0) {}

	@Override
	public void keyTyped(KeyEvent arg0) {}

	@Override
	public void windowActivated(WindowEvent arg0) {}

	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowClosed(WindowEvent arg0) {}
	
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
	
	public static void main(String[] args) {
		GameController controller = new GameController();
		controller.setVisible(true);
	}

}
