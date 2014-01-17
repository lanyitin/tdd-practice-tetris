package tetris.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tetris.impl.Board;
import tetris.impl.GameController;

public class TestGameCotroller {

	@Test
	public void testGameController() {
		GameController g1 = new GameController();
		assertTrue(true);
	}

	@Test
	public void testGetBoard() {
		GameController g1 = new GameController();
		Board b1 = new Board(14, 7);
		assertEquals(b1.getRows(),g1.getBoard().getRows());
		assertEquals(b1.getColumns(),g1.getBoard().getColumns());
	}

	@Test
	public void testOnClean() {
		GameController g1 = new GameController();
		g1.onClean(1);
		assertTrue(true);
	}

}
