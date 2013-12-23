// Copyright (c) 2008-2012  Esko Luontola <www.orfjackal.net>
// You may use and modify this source code freely for personal non-commercial use.
// This source code may NOT be used as course material without prior written agreement.

package tetris.test;

import net.orfjackal.nestedjunit.NestedJUnit;
import org.junit.*;
import org.junit.runner.RunWith;

import tetris.impl.Piece;

/**
 * @author Esko Luontola
 */
@RunWith(NestedJUnit.class)
public class RotatingPiecesOfBlocksTest extends Assert {

    // Step 2: Stepping stone for rotation algorithms
    // - Remove the @Ignore annotation from this class
    // - See README for motivation
    // - Next step: RotatingTetrominoesTest


    private Piece piece;


    public class A_piece_of_3x3_blocks {

        @Before
        public void createPiece() {
            piece = new Piece("" +
                    ".X." +
                    ".X." +
                    "...");
        }

        @Test
        public void consists_of_many_blocks() {
            assertEquals("" +
                    ".X." +
                    ".X." +
                    "...", piece.toString());
        }

//        @Test
//        public void can_be_rotated_right() {
//            piece = piece.rotateRight();
//            assertEquals("" +
//                    "..." +
//                    ".XX" +
//                    "...", piece.toString());
//        }

//        @Test
//        public void can_be_rotated_left() {
//            piece = piece.rotateLeft();
//            assertEquals("" +
//                    "..." +
//                    "XX." +
//                    "...", piece.toString());
//        }
    }


/*
    public class A_piece_of_5x5_blocks {

        @Before
        public void createPiece() {
            piece = new Piece("" +
                    "..XXX" +
                    "..XX." +
                    "..X.." +
                    "....." +
                    ".....");
        }

        @Test
        public void consists_of_many_blocks() {
            assertEquals("" +
                    "..XXX" +
                    "..XX." +
                    "..X.." +
                    "....." +
                    ".....", piece.toString());
        }

//        @Test
//        public void can_be_rotated_right() {
//            piece = piece.rotateRight();
//            assertEquals("" +
//                    "....." +
//                    "....." +
//                    "..XXX" +
//                    "...XX" +
//                    "....X", piece.toString());
//        }

//        @Test
//        public void can_be_rotated_left() {
//            piece = piece.rotateLeft();
//            assertEquals("" +
//                    "X...." +
//                    "XX..." +
//                    "XXX.." +
//                    "....." +
//                    ".....", piece.toString());
//        }
    }
*/
}
