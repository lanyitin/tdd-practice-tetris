package tetris.impl;

import java.util.ArrayList;

public class Board {

    private final int rows;
    private final int columns;
    private ArrayList<Character> baseState;
	private ArrayList<Character> currentState;
	private ArrayList<Character> nextState;
	private Piece fallingBlock;
	private int blockX;
	private int blockY;

    public Board(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.fallingBlock = null;
        this.baseState = new ArrayList<Character>();
        this.blockX = 0;
        this.blockY = 0;
        
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                baseState.add('.');
            }
        }
        
        currentState = (ArrayList<Character>) baseState.clone();
    }

    public String toString() {
        String s = "";
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
            	s += currentState.get(convertLocationToIndex(row, col));
            }
            s += "\n";
        }
        return s;
    }

	private char getCharFromFallingBlock(int row, int col) {
		int index = (row - blockY) * (fallingBlock.getWidth()) + col - blockX;
		return fallingBlock.getChars()[index];
	}

	private int convertLocationToIndex(int row, int col) {
		return row * columns + col;
	}

	public boolean hasFalling() {
		return this.fallingBlock != null;
	}

	public void drop(Piece tShape) {
		if (!hasFalling()) {
			blockX = columns/2 - tShape.getWidth() / 2;
			this.fallingBlock = tShape;
			currentState = generateState();
		} else {
			throw new IllegalStateException("already falling");
		}
		
	}

	public void tick() {
		blockY++;
        nextState = generateState();
        
        if (hasCollision()) {
        	baseState = currentState;
        	fallingBlock = null;
        	blockX = blockY = 0;
        } else {
        	currentState = nextState;
        }
	}

	private ArrayList<Character> generateState() {
		ArrayList<Character> newState = (ArrayList<Character>) baseState.clone();
		for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
            	if (hasFalling() && col >= blockX && col <= blockX + (fallingBlock.getWidth() - 1) && row >= blockY && row <= blockY + (fallingBlock.getHeight() - 1)) {
            		newState.set(convertLocationToIndex(row, col), getCharFromFallingBlock(row, col));
            	}
            }
        }
		return newState;
	}

	private boolean hasCollision() {
		int currentStateDotNumber = 0, nextStateDotNumber = 0;
		for (char c : nextState) {
			if (c == '.') {
				nextStateDotNumber++;
			}
		}
		for (char c : currentState) {
			if (c == '.') {
				currentStateDotNumber++;
			}
		}
		
		return nextStateDotNumber > currentStateDotNumber;
	}
}
