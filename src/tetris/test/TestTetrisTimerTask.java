package tetris.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tetris.impl.GameController;
import tetris.impl.TetrisTimerTask;

public class TestTetrisTimerTask {

	@Test
	public void testRun() {
		GameController g1 = new GameController();
		TetrisTimerTask t1 = new TetrisTimerTask(g1);
		t1.run();
		assertTrue(g1.getBoard().hasFalling());
	}

	@Test
	public void testTetrisTimerTask() {
		GameController g1 = new GameController();
		TetrisTimerTask t1 = new TetrisTimerTask(g1);
		assertTrue(true);
	}

}
