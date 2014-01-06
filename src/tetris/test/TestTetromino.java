package tetris.test;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import tetris.impl.Board;
import tetris.impl.CantDropTetrominoException;
import tetris.impl.Tetromino;

@RunWith(NestedJUnit.class)
public class TestTetromino extends Assert {

	@Test
	public void testRotateRight() throws CantDropTetrominoException {
		//Test H -> I
		Board board = new Board(8,6);
		board.drop(Tetromino.H_SHAPE);
		board.rotateRight(); board.tick(); board.tick(); board.tick();
		assertEquals("" +
		            "......\n" +
		            "......\n" +
		            "......\n" +
		            "......\n" +
		            ".IIII.\n" +
		            "......\n" +
		            "......\n" +
		            "......\n", board.toString());
		
		//Test I -> H
		board.rotateRight();
		assertEquals("" +
		            "......\n" +
		            "......\n" +
		            "...I..\n" +
		            "...I..\n" +
		            "...I..\n" +
		            "...I..\n" +
		            "......\n" +
		            "......\n", board.toString());
		
		//Test O-right
		Board board2 = new Board(8,6);
		board2.drop(Tetromino.O_SHAPE);
		board2.rotateRight(); board2.tick(); board2.tick(); board2.tick(); board2.tick();
		board2.rotateRight();
		assertEquals("" +
		            "......\n" +
		            "......\n" +
		            "......\n" +
		            "......\n" +
		            "...OO.\n" +
		            "...OO.\n" +
		            "......\n" +
		            "......\n", board2.toString());
		
		//Test T
		Board board3 = new Board(8,6);
		board3.drop(Tetromino.T_SHAPE);
		board3.tick(); board3.tick(); board3.tick(); board3.tick(); board3.moveLeft();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "..T...\n" +
	            ".TTT..\n" +
	            "......\n" +
	            "......\n", board3.toString());
		
		//Test T2-right
		board3.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "..T...\n" +
	            "..TT..\n" +
	            "..T...\n" +
	            "......\n", board3.toString());
		
		//Test T3-right
		board3.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            ".TTT..\n" +
	            "..T...\n" +
	            "......\n", board3.toString());
		
		//Test T4-right
		board3.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "..T...\n" +
	            ".TT...\n" +
	            "..T...\n" +
	            "......\n", board3.toString());
		
		//Test Z
		Board board4 = new Board(8,6);
		board4.drop(Tetromino.Z_SHAPE);
		board4.tick(); board4.tick(); board4.tick(); board4.tick(); board4.tick(); board4.moveLeft();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            ".ZZ...\n" +
	            "..ZZ..\n" +
	            "......\n", board4.toString());
		
		//Test Z-right
		board4.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "...Z..\n" +
	            "..ZZ..\n" +
	            "..Z...\n", board4.toString());
		
		//Test ReZ
		Board board5 = new Board(8,6);
		board5.drop(Tetromino.ReZ_SHAPE);
		board5.tick(); board5.tick();  board5.tick(); board5.tick(); board5.tick(); board5.moveLeft();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "..ZZ..\n" +
	            ".ZZ...\n" +
	            "......\n", board5.toString());
		
		//Test ReZ-right
		board5.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "..Z...\n" +
	            "..ZZ..\n" +
	            "...Z..\n", board5.toString());
		
		//Test L
		Board board6 = new Board(8,6);
		board6.drop(Tetromino.L_SHAPE);
		board6.tick(); board6.tick();  board6.tick(); board6.tick();
		System.out.print(board6.toString());
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "..L...\n" +
	            "..L...\n" +
	            "..LL..\n" +
	            "......\n", board6.toString());
		
		//Test L2-right
		board6.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "..LLL.\n" +
	            "..L...\n" +
	            "......\n" +
	            "......\n", board6.toString());
		
		//Test L3-right
		board6.rotateRight();
		System.out.print(board6.toString());
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "...LL.\n" +
	            "....L.\n" +
	            "....L.\n" +
	            "......\n", board6.toString());
		
		//Test L4-right
		board6.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "....L.\n" +
	            "..LLL.\n" +
	            "......\n", board6.toString());
		
		//Test ReL
		Board board7 = new Board(8,6);
		board7.drop(Tetromino.ReL_SHAPE);
		board7.tick(); board7.tick();  board7.tick(); board7.tick(); board7.moveLeft();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "...L..\n" +
	            "...L..\n" +
	            "..LL..\n" +
	            "......\n", board7.toString());
		
		//Test ReL2-right
		board7.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            ".L....\n" +
	            ".LLL..\n" +
	            "......\n", board7.toString());
		
		//Test ReL3-right
		board7.rotateRight();
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            ".LL...\n" +
	            ".L....\n" +
	            ".L....\n" +
	            "......\n", board7.toString());
		
		//Test ReL4-right
		board7.rotateRight();
		System.out.print(board7.toString());
		assertEquals("" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            "......\n" +
	            ".LLL..\n" +
	            "...L..\n" +
	            "......\n" +
	            "......\n", board7.toString());
		
	}

	@Test
	public void testRotateLeft() throws CantDropTetrominoException {
		//Test I -> H
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
		board.tick(); board.tick(); 
		board.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".IIII.\n" +
                "......\n" +
                "......\n" +
                "......\n", board.toString());
		
		//Test H -> I
		board.rotateLeft();
		assertEquals("" +
		            "......\n" +
		            "......\n" +
		            "...I..\n" +
		            "...I..\n" +
		            "...I..\n" +
		            "...I..\n" +
		            "......\n" +
		            "......\n", board.toString());
				
		//Test O
			Board board2 = new Board(8,6);
			board2.drop(Tetromino.O_SHAPE);
			board2.tick(); board2.tick(); board2.tick(); board2.tick();
			board2.rotateLeft();
			assertEquals("" +
	                "......\n" +
	                "......\n" +
	                "......\n" +
	                "......\n" +
	                "...OO.\n" +
	                "...OO.\n" +
	                "......\n" +
	                "......\n", board2.toString());
			
		//Test T1
		Board board3 = new Board(8,6);
		board3.drop(Tetromino.T_SHAPE);
		board3.moveLeft(); board3.tick(); board3.tick(); board3.tick(); board3.tick(); 
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..T...\n" +
                ".TTT..\n" +
                "......\n" +
                "......\n", board3.toString());
		
		//Test T2_left
		board3.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..T...\n" +
                ".TT...\n" +
                "..T...\n" +
                "......\n", board3.toString());
		
		//Test T3_left
		board3.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".TTT..\n" +
                "..T...\n" +
                "......\n", board3.toString());
		
		//Test T4_left
		board3.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..T...\n" +
                "..TT..\n" +
                "..T...\n" +
                "......\n", board3.toString());
		
		//Test Z
		Board board4 = new Board(8,6);
		board4.drop(Tetromino.Z_SHAPE);
		board4.tick(); board4.tick(); board4.tick(); board4.tick(); board4.tick(); board4.moveLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".ZZ...\n" +
                "..ZZ..\n" +
                "......\n", board4.toString());
		
		//Test N
		board4.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..Z...\n" +
                ".ZZ...\n" +
                ".Z....\n", board4.toString());
		
		//Test ReZ
		Board board5 = new Board(8,6);
		board5.drop(Tetromino.ReZ_SHAPE);
		board5.tick(); board5.tick(); board5.tick(); board5.tick(); board5.tick(); board5.moveLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..ZZ..\n" +
                ".ZZ...\n" +
                "......\n", board5.toString());
		
		//Test ReZ-left
		board5.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".Z....\n" +
                ".ZZ...\n" +
                "..Z...\n", board5.toString());
		
		//Test L
		Board board6 = new Board(8,6);
		board6.drop(Tetromino.L_SHAPE);
		board6.tick(); board6.tick(); board6.tick(); board6.tick();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..L...\n" +
                "..L...\n" +
                "..LL..\n" +
                "......\n", board6.toString());
		
		//Test L2-left
		board6.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "....L.\n" +
                "..LLL.\n" +
                "......\n", board6.toString());
		
		//Test L3-left
		board6.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "...LL.\n" +
                "....L.\n" +
                "....L.\n" +
                "......\n", board6.toString());
		
		//Test L4-left
		board6.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "..LLL.\n" +
                "..L...\n" +
                "......\n" +
                "......\n", board6.toString());
		
		//Test ReL-left
		Board board7 = new Board(8,6);
		board7.drop(Tetromino.ReL_SHAPE);
		board7.tick(); board7.tick(); board7.tick(); board7.tick(); board7.moveLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "...L..\n" +
                "...L..\n" +
                "..LL..\n" +
                "......\n", board7.toString());
		
		//Test ReL2-left
		board7.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".LLL..\n" +
                "...L..\n" +
                "......\n" +
                "......\n", board7.toString());
		
		//Test ReL3-left
		board7.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".LL...\n" +
                ".L....\n" +
                ".L....\n" +
                "......\n", board7.toString());
		
		//Test ReL4-left
		board7.rotateLeft();
		assertEquals("" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                "......\n" +
                ".L....\n" +
                ".LLL..\n" +
                "......\n", board7.toString());
	}
	
}
