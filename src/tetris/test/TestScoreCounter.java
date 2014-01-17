package tetris.test;

import org.junit.*;
import tetris.impl.CantDropTetrominoException;
import tetris.impl.ScoreCounter;


public class TestScoreCounter extends Assert{

	//TODO: num = 0 -> 1
	@Test 
	public void testReceiveNumberOfClearedLines() throws CantDropTetrominoException {
	    ScoreCounter S = new ScoreCounter();
	    S.receiveNumberOfClearedLines(0);
	    assertEquals(0,S.getCurrentComboCount());
	    assertEquals(0,S.getScore());
	    assertEquals(0,S.getCurrentComboCount());
	    
	    S.receiveNumberOfClearedLines(1);
	    assertEquals(1,S.getCurrentComboCount());
	    assertEquals(10,S.getScore());
	    assertEquals(1,S.getCurrentComboCount());

	}
	
	//TODO: num = 1 -> 0
	@Test 
	public void testReceiveNumberOfClearedLines2() throws CantDropTetrominoException {
	    ScoreCounter S = new ScoreCounter();
	    S.receiveNumberOfClearedLines(1);
	    assertEquals(1,S.getCurrentComboCount());
	    assertEquals(10,S.getScore());
	    assertEquals(1,S.getCurrentComboCount());
	    
	    S.receiveNumberOfClearedLines(0);
	    assertEquals(0,S.getCurrentComboCount());
	    assertEquals(10,S.getScore());
	    assertEquals(0,S.getCurrentComboCount());
	}
	
	// Test case based on state machine
	@Test
	public void testState() {
		ScoreCounter S = new ScoreCounter();
		
		S.receiveNumberOfClearedLines(0);
	    assertEquals(0,S.getCurrentComboCount());
	    assertEquals(0,S.getScore());
		
	    S.receiveNumberOfClearedLines(1);
	    assertEquals(1,S.getCurrentComboCount());
	    assertEquals(10,S.getScore());
	    
	    S.receiveNumberOfClearedLines(1);
	    assertEquals(2,S.getCurrentComboCount());
	    assertEquals(20,S.getScore());
	    
	    S.receiveNumberOfClearedLines(1);
	    assertEquals(3,S.getCurrentComboCount());
	    assertEquals(30,S.getScore());
	    assertEquals(3, S.getMaxAccumulatedComboCount());
	    
	    S.receiveNumberOfClearedLines(0);
	    assertEquals(0,S.getCurrentComboCount());
	    assertEquals(30,S.getScore());
	    assertEquals(3, S.getMaxAccumulatedComboCount());
	}
}
