// Copyright (c) 2008-2012  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris.test;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import tetris.impl.Block;
import tetris.impl.Board;
import tetris.impl.CantDropTetrominoException;
import tetris.impl.GameController;
import tetris.impl.Tetromino;

/**
 * @author Esko Luontola
 */
@RunWith(NestedJUnit.class)
public class GameControllerTest extends Assert {
	private GameController controller;

	@SuppressWarnings("deprecation")
	@Test
	public void testGameController() throws CantDropTetrominoException {
//		this.controller = new GameController();
//		controller.setVisible(true);
//		controller.getBoard().drop(Tetromino.O_SHAPE);
//		controller.dispatchEvent(new KeyEvent(controller, KeyEvent.KEY_PRESSED,
//				System.currentTimeMillis(), 0, KeyEvent.VK_LEFT));
//		controller.performActionIfQueueIsNotEmpty();
//		controller.dispatchEvent(new KeyEvent(controller, KeyEvent.KEY_PRESSED,
//				System.currentTimeMillis(), 0, KeyEvent.VK_LEFT));
//		System.out.println(controller.getBoard().toString());
	}
}