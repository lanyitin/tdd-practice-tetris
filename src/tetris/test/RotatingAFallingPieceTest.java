// Copyright (c) 2008-2012  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris.test;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import tetris.impl.Board;
import tetris.impl.Tetromino;

@RunWith(NestedJUnit.class)
public class RotatingAFallingPieceTest extends Assert {
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
    // Step 6: Training wheels off
    // - Remove the @Ignore annotation from this class
    // - You're now responsible for covering all corner cases
    // - Next step: see the README for details

    // TODO: a falling piece can be rotated clockwise
	public class a_falling_piece_can_be_rotated_clockwise {
    	@Before
        public void dropTetromino() {
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
        public void rotate_right() {
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
            board.rotateRight();
            assertEquals("" +
                    "......\n" +
                    "...T..\n" +
                    "...TT.\n" +
                    "...T..\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n", board.toString());
        }
	}
    // TODO: a falling piece can be rotated counter-clockwise
	public class a_falling_piece_can_be_rotated_counter_clockwise {
    	@Before
        public void dropTetromino() {
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
        public void rotate_right() {
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
            board.rotateLeft();
            assertEquals("" +
                    "......\n" +
                    "...T..\n" +
                    "..TT..\n" +
                    "...T..\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n", board.toString());
        }
	}
    // TODO: it can not be rotated when there is no room to rotate (left wall, right wall, other pieces...)
	public class it_can_not_be_rotated_when_there_is_no_room_to_rotate {
    	@Before
        public void dropTetromino() {
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
    	public void dropAnotherPiece() {
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
    		board.rotateRight();
    		assertEquals("" +
    				"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    ".T.TT.\n" +
                    "TTTT..\n", board.toString());
    		board.rotateLeft();
    		assertEquals("" +
    				"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "...T..\n" +
                    ".TTTT.\n" +
                    "TTT...\n", board.toString());
    		board.moveRight();
    		board.tick();
    		assertEquals("" +
    				"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    ".T..T.\n" +
                    "TTTTTT\n", board.toString());
    		board.rotateRight();
    		assertEquals("" +
    				"......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    "......\n" +
                    ".T..T.\n" +
                    "TTTTTT\n", board.toString());
    	}
    }
    // TODO: when piece is up against a wall (or piece) and it is rotated (no room to rotate), move it away from the wall ("wallkick")
    // See: http://bsixcentdouze.free.fr/tc/tgm-en/tgm.html
    // http://bsixcentdouze.free.fr/tc/tgm-en/img/wallkick1.png
    // http://bsixcentdouze.free.fr/tc/tgm-en/img/wallkick2.png
    // http://bsixcentdouze.free.fr/tc/tgm-en/img/wallkick3.png
}
