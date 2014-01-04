// Copyright (c) 2008-2012  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris.test;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;
import tetris.impl.Board;
import tetris.impl.CantDropTetrominoException;
import tetris.impl.Tetromino;

@RunWith(NestedJUnit.class)
public class MovingAFallingPieceTest extends Assert {

    // Step 5: It's your turn now
    // - Remove the @Ignore annotation from this class
    // - The test names have been provided, you just need to fill in the test body
    // - Next step: RotatingAFallingPieceTest
	private final Board board = new Board(8, 6);
	
    public class A_new_board {

        @Test
        public void is_empty() {
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
        public void has_no_falling_blocks() {
            assertFalse(board.hasFalling());
        }
    }
    public class Moving_left_a_falling_piece {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void the_block_is_falling() {
            assertTrue(board.hasFalling());
        }
        
        @Test
        public void it_starts_from_the_top_middle() {
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
        public void it_moves_down_one_row_per_tick() {
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
        public void moveLeft() {
        	board.tick();
            board.moveLeft();
            assertEquals("" +
                    "......\n" +
                    "..T...\n" +
                    ".TTT..\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n", board.toString());
        }
    }
    public class Moving_right_a_falling_piece {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void the_block_is_falling() {
            assertTrue(board.hasFalling());
        }
        
        @Test
        public void it_starts_from_the_top_middle() {
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
        public void it_moves_down_one_row_per_tick() {
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
        public void moveLeft() {
        	board.tick();
            board.moveRight();
            assertEquals("" +
                    "......\n" +
                    "....T.\n" +
                    "...TTT\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n", board.toString());
        }
    }
    public class Moving_down_a_falling_piece {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void the_block_is_falling() {
            assertTrue(board.hasFalling());
        }
        
        @Test
        public void it_starts_from_the_top_middle() {
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
        public void it_moves_down_one_row_per_tick() {
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
        public void moveLeft() {
        	board.tick();
            board.moveDown();
            assertEquals("" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    "..TTT.\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n", board.toString());
        }
    }
    public class it_will_not_move_left_over_over_the_board {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void the_block_is_falling() {
            assertTrue(board.hasFalling());
        }
        
        @Test
        public void it_starts_from_the_top_middle() {
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
        public void it_moves_down_one_row_per_tick() {
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
        public void moveLeft() {
        	board.tick();
            board.moveLeft();
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
            assertTrue(board.hasFalling());
        }
    }
    public class it_will_not_move_right_over_over_the_board {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void the_block_is_falling() {
            assertTrue(board.hasFalling());
        }
        
        @Test
        public void it_starts_from_the_top_middle() {
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
        public void it_moves_down_one_row_per_tick() {
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
        public void moveRight() {
        	board.tick();
            board.moveRight();
            board.moveRight();
            board.moveRight();
            assertEquals("" +
                    "......\n" +
                    "....T.\n" +
                    "...TTT\n" +
                    "......\n" +
                    "......\n" +
            		"......\n" +
                    "......\n" +
                    "......\n", board.toString());
            assertTrue(board.hasFalling());
        }
    }
    public class it_will_not_move_donw_over_over_the_board_and_stop_falling {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
        }

        @Test
        public void the_block_is_falling() {
            assertTrue(board.hasFalling());
        }
        
        @Test
        public void it_starts_from_the_top_middle() {
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
        public void it_moves_down_one_row_per_tick() {
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
        public void moveDown() {
        	board.tick();
            board.moveDown();
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
            assertFalse(board.hasFalling());
        }
    }
    public class it_can_not_be_moved_left_if_another_piece_is_in_the_way {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
            board.moveLeft();
            board.moveLeft();
            assertEquals("" +
                    ".T....\n" +
                    "TTT...\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n", board.toString());
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            assertEquals("" +
            		"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    ".T....\n" +
                    "TTT...\n", board.toString());
        }
    	@Test
    	public void dropAnotherPiece() throws CantDropTetrominoException {
    		board.drop(Tetromino.T_SHAPE);
    		assertEquals("" +
    				"...T..\n" +
                    "..TTT.\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    ".T....\n" +
                    "TTT...\n", board.toString());
    		board.tick();
    		board.tick();
    		board.tick();
    		board.tick();
    		board.tick();
    		assertTrue(board.hasFalling());
    		assertEquals("" +
    				"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    ".TTTT.\n" +
                    "TTT...\n", board.toString());
    		board.moveLeft();
    		assertEquals("" +
    				"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    ".TTTT.\n" +
                    "TTT...\n", board.toString());
    		board.tick();
    		assertFalse(board.hasFalling());
    	}
    }
    public class it_can_not_be_moved_right_if_another_piece_is_in_the_way {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
            board.drop(Tetromino.T_SHAPE);
            board.moveRight();
            board.moveRight();
            board.moveRight();
            assertEquals("" +
                    "....T.\n" +
                    "...TTT\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n", board.toString());
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            assertEquals("" +
            		"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "....T.\n" +
                    "...TTT\n", board.toString());
        }
    	@Test
    	public void dropAnotherPiece() throws CantDropTetrominoException {
    		board.drop(Tetromino.T_SHAPE);
    		assertEquals("" +
    				"...T..\n" +
                    "..TTT.\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "....T.\n" +
                    "...TTT\n", board.toString());
    		board.moveLeft();
    		board.tick();
    		board.tick();
    		board.tick();
    		board.tick();
    		board.tick();
    		assertTrue(board.hasFalling());
    		assertEquals("" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
    				"......\n" +
                    "..T...\n" +
                    ".TTTT.\n" +
                    "...TTT\n", board.toString());
    		board.moveRight();
    		assertEquals("" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
    				"......\n" +
                    "..T...\n" +
                    ".TTTT.\n" +
                    "...TTT\n", board.toString());
    		board.tick();
    		assertFalse(board.hasFalling());
    	}
    }
    public class it_can_not_be_moved_down_if_another_piece_is_in_the_way_stop_falling {
    	@Before
        public void dropTetromino() throws CantDropTetrominoException {
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
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            board.tick();
            assertEquals("" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    "..TTT.\n", board.toString());
        }
    	@Test
    	public void dropAnotherPiece() throws CantDropTetrominoException {
    		board.drop(Tetromino.T_SHAPE);
    		assertEquals("" +
    				"...T..\n" +
                    "..TTT.\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    "..TTT.\n", board.toString());
    		board.moveDown();
    		board.moveDown();
    		board.moveDown();
    		board.moveDown();
    		assertTrue(board.hasFalling());
    		assertEquals("" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    "..TTT.\n" +
                    "...T..\n" +
                    "..TTT.\n", board.toString());
    		board.moveDown();
    		assertEquals("" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    "..TTT.\n" +
                    "...T..\n" +
                    "..TTT.\n", board.toString());

    		board.tick();
    		assertFalse(board.hasFalling());
    	}
    }
}
