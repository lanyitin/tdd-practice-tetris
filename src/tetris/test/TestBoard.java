package tetris.test;

import static org.junit.Assert.*;

import org.junit.Test;

import tetris.impl.Board;
import tetris.impl.CantDropTetrominoException;
import tetris.impl.Tetromino;

public class TestBoard {

	@Test
	public void testBoard() {
		Board board = new Board(8,6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		assertTrue(true);
	}

	@Test
	public void testGetRows() {
		Board board = new Board(8,6);
		assertEquals(8,board.getRows());
	}

	@Test
	public void testGetColumns() {
		Board board = new Board(8,6);
		assertEquals(6,board.getColumns());
	}

	@Test
	public void testToString() throws CantDropTetrominoException {
		Board board = new Board(8,6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
	}

	@Test
	public void testHasFalling() throws CantDropTetrominoException {
		/*!hasFalling*/
		Board board = new Board(8,6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.drop(Tetromino.T_SHAPE);
		board.moveLeft(); board.moveLeft();
		board.tick(); board.tick(); board.tick(); board.tick(); board.tick(); board.tick();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".T....\n" +
                "TTT...\n", board.toString());
		
		board.tick();
		board.drop(Tetromino.T_SHAPE);
		board.moveRight();
		board.tick(); board.tick(); board.tick(); board.tick(); board.tick(); board.tick();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".T..T.\n" +
                "TTTTTT\n", board.toString());
		/*hasFalling*/
		Board board1 = new Board(8,6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		try {
			board1.drop(Tetromino.T_SHAPE);
		} catch (CantDropTetrominoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board1.moveLeft(); board1.moveLeft();
		board1.tick(); board1.tick(); board1.tick(); board1.tick(); board1.tick(); board1.tick();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".T....\n" +
                "TTT...\n", board1.toString());
		
		board1.tick();
		try {
			board1.drop(Tetromino.T_SHAPE);
		} catch (CantDropTetrominoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		board1.moveRight();
		board1.tick(); board1.tick(); board1.tick(); board1.tick(); board1.tick(); board1.tick();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".T..T.\n" +
                "TTTTTT\n", board1.toString());
		
		board1.tick();
		try {
			board1.drop(Tetromino.I_SHAPE);
		} catch (CantDropTetrominoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		board1.moveRight();
		board1.tick(); board1.tick(); board1.tick();
		assertEquals("" +
				"......\n" +
                "......\n" +
                "......\n" +
                "....I.\n" +
                "....I.\n" +
                "....I.\n" +
                "....I.\n" +
                ".T..T.\n" , board1.toString());
		 assertTrue(board1.hasFalling());
		 try {
				board1.drop(Tetromino.T_SHAPE);
				assertFalse("wronggggg",true);
			} catch (CantDropTetrominoException | IllegalStateException e) {
				assertFalse(false);
			}
	}

	@Test
	public void testDrop() throws CantDropTetrominoException {
		Board board = new Board(8,6);
		board.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
	}

	@Test
	public void testTick() throws CantDropTetrominoException {
		Board board = new Board(8,6);
		board.drop(Tetromino.T_SHAPE);
		board.tick();
		assertEquals("" +
				"......\n" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
	}

	@Test
	public void testMoveLeft() throws CantDropTetrominoException {
		/*can't move left*/
		Board board = new Board(8, 6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.tick();
		board.moveLeft();
		board.moveLeft();
		assertEquals("" +
                "......\n" +
                ".T....\n" +
                "TTT...\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.moveLeft();
		assertEquals("" +
                "......\n" +
                ".T....\n" +
                "TTT...\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		/* move left*/
		Board board1 = new Board(8, 6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		board1.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		board1.tick();
		board1.moveLeft();
		assertEquals("" +
                "......\n" +
                "..T...\n" +
                ".TTT..\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		board1.moveLeft();
		assertEquals("" +
                "......\n" +
                ".T....\n" +
                "TTT...\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
	}

	@Test
	public void testMoveRight() throws CantDropTetrominoException {
		/*can't move right*/
		Board board = new Board(8, 6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.tick();
		board.tick();
		board.moveRight();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "....T.\n" +
                "...TTT\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.moveRight();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "....T.\n" +
                "...TTT\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		/*move right*/
		Board board1 = new Board(8, 6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		board1.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		board1.tick();
		board1.moveRight();
		assertEquals("" +
                "......\n" +
                "....T.\n" +
                "...TTT\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
	}

	@Test
	public void testMoveDown() throws CantDropTetrominoException {
		/*can't move down*/
		Board board = new Board(8, 6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.moveDown();
		board.moveDown();
		board.moveDown();
		board.moveDown();
		board.moveDown();
		board.moveDown();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "...T..\n" +
                "..TTT.\n", board.toString());
		/* move down*/
		Board board1 = new Board(8, 6);
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		board1.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
		board1.moveLeft();
		board1.moveDown();
		assertEquals("" +
                "......\n" +
                "..T...\n" +
                ".TTT..\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board1.toString());
	}

	@Test
	public void testRotateRight() throws CantDropTetrominoException {
		/*can't rotate right*/
		Board board = new Board(8, 6);
		board.drop(Tetromino.I_SHAPE);
		assertEquals("" +
                "...I..\n" +
                "...I..\n" +
                "...I..\n" +
                "...I..\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.moveLeft();
		board.tick();
		board.tick();
		board.tick();
		board.tick();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" , board.toString());
		board.tick();
		assertFalse(board.hasFalling());
		board.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" , board.toString());
		board.rotateRight();
		assertEquals("" +
                "...T..\n" +
                "...TT.\n" +
                "...T..\n" +
                "......\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" , board.toString());
		board.moveLeft();
		board.moveLeft();
		board.moveLeft();
		board.tick();
		board.tick();
		board.tick();
		board.tick();
		assertEquals("" +
				"......\n" +
				"......\n" +                
				"......\n" +                    
				"......\n" +                    
				"T.I...\n" +                    
				"TTI...\n" +                    
				"T.I...\n" +                    
				"..I...\n", board.toString());
		board.rotateRight();
		assertEquals("" +
				"......\n" +
				"......\n" +                
				"......\n" +                    
				"......\n" +                    
				"T.I...\n" +                    
				"TTI...\n" +                    
				"T.I...\n" +                    
				"..I...\n", board.toString());
		/*rotate right */
		Board board1 = new Board(8, 6);
		board1.drop(Tetromino.T_SHAPE);
		board1.moveLeft();
		board1.tick();
		assertEquals("" +
				"......\n" + 
				"..T...\n" +
				".TTT..\n" + 
				"......\n" +  
				"......\n" +   
				"......\n" +       
				"......\n" +      
				"......\n", board1.toString());
		board1.rotateRight();
		assertEquals("" +
				"......\n" + 
				"..T...\n" +
				"..TT..\n" + 
				"..T...\n" +  
				"......\n" +   
				"......\n" +       
				"......\n" +      
				"......\n", board1.toString());
	}

	@Test
	public void testRotateLeft() throws CantDropTetrominoException {
		/*can't rotate left*/
		Board board = new Board(8, 6);
		board.drop(Tetromino.I_SHAPE);
		assertEquals("" +
                "...I..\n" +
                "...I..\n" +
                "...I..\n" +
                "...I..\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		board.moveLeft();
		board.tick();
		board.tick();
		board.tick();
		board.tick();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" , board.toString());
		board.tick();
		assertFalse(board.hasFalling());
		board.drop(Tetromino.T_SHAPE);
		assertEquals("" +
                "...T..\n" +
                "..TTT.\n" +
                "......\n" +
                "......\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" , board.toString());
		board.rotateLeft();
		board.rotateLeft();
		board.rotateLeft();
		assertEquals("" +
                "...T..\n" +
                "...TT.\n" +
                "...T..\n" +
                "......\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" +
                "..I...\n" , board.toString());
		board.moveLeft();
		board.moveLeft();
		board.moveLeft();
		board.tick();
		board.tick();
		board.tick();
		board.tick();
		assertEquals("" +
				"......\n" +
				"......\n" +                
				"......\n" +                    
				"......\n" +                    
				"T.I...\n" +                    
				"TTI...\n" +                    
				"T.I...\n" +                    
				"..I...\n", board.toString());
		board.rotateLeft();
		assertEquals("" +
				"......\n" +
				"......\n" +                
				"......\n" +                    
				"......\n" +                    
				"T.I...\n" +                    
				"TTI...\n" +                    
				"T.I...\n" +                    
				"..I...\n", board.toString());
		/*rotate left */
		Board board1 = new Board(8, 6);
		board1.drop(Tetromino.T_SHAPE);
		board1.moveLeft();
		board1.tick();
		assertEquals("" +
				"......\n" + 
				"..T...\n" +
				".TTT..\n" + 
				"......\n" +  
				"......\n" +   
				"......\n" +       
				"......\n" +      
				"......\n", board1.toString());
		board1.rotateLeft();
		assertEquals("" +
				"......\n" + 
				"..T...\n" +
				".TT...\n" + 
				"..T...\n" +  
				"......\n" +   
				"......\n" +       
				"......\n" +      
				"......\n", board1.toString());
	}

	@Test
	public void testState() throws CantDropTetrominoException {
		
		Board b = new Board(6,8);
		b.drop(Tetromino.I_SHAPE);
		b.rotateLeft();
		b.moveLeft(); b.moveLeft();
		
		assertTrue(b.hasFalling()) ; 
		b.tick();
		b.tick();
		b.tick();
		b.tick();
		b.tick();
		
		assertEquals("........\n"
					+"........\n"
					+"........\n"
					+"........\n"
					+"........\n"
					+"IIII....\n", b.toString()) ;
		assertFalse(b.hasFalling()) ;
		b.drop(Tetromino.I_SHAPE);
		b.rotateLeft();
		b.moveRight(); b.moveRight();
		b.tick(); b.tick(); b.tick();
		b.tick();
		
		assertEquals("........\n"
					+"........\n"
					+"........\n"
					+"........\n"
					+"........\n"
					+"........\n", b.toString()) ;
		
		//GameController g = new GameController() ;
		//TetrisTimerTask t = new TetrisTimerTask(g) ;

		
	}

}
