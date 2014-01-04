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

    @SuppressWarnings("unchecked")
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

    public int getRows() {
		return rows;
	}

	public int getColumns() {
		return columns;
	}

	public String toString() {
        String s = "";
        for (int row = 0; row < getRows(); row++) {
            for (int col = 0; col < getColumns(); col++) {
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
			String[] blockLines = this.fallingBlock.toString().split("\n");
			for (int i = 0; i < blockLines.length; i++) {
				if (blockLines[i].replace(".", "").length() == 0) {
					blockY--;
				}
			}
			currentState = generateState();
		} else {
			throw new IllegalStateException("already falling");
		}
		
	}

	public void tick() {
		moveDown();
	}

	private ArrayList<Character> generateState() {
		ArrayList<Character> newState = (ArrayList<Character>) baseState.clone();
		for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
            	if (hasFalling() && col >= blockX && col <= blockX + (fallingBlock.getWidth() - 1) && row >= blockY && row <= blockY + (fallingBlock.getHeight() - 1) && getCharFromFallingBlock(row, col) != '.') {
            		newState.set(convertLocationToIndex(row, col), getCharFromFallingBlock(row, col));
            	}
            }
        }
		return newState;
	}

	private boolean hasCollision() {
		int currentStateDotNumber = 0, nextStateDotNumber = 0;

		for (int i = 0; i < currentState.size(); i++) {
			if (currentState.get(i) == '.' || nextState.get(i) == '.') {
				if (currentState.get(i) == '.') {
					currentStateDotNumber++;
				}
				if (nextState.get(i) == '.') {
					nextStateDotNumber++;
				}			
			} else {
				if (currentState.get(i) != nextState.get(i)) {
					return true;
				}
			}

		}
		return nextStateDotNumber > currentStateDotNumber;
	}

	public void moveLeft() {
		blockX--;
        nextState = generateState();
        
        if (hasCollision()) {
        	blockX++;
        } else {
        	currentState = nextState;
        }
	}
	public void moveDown() {
		blockY++;
        nextState = generateState();
        
        if (hasCollision()) {
        	baseState = currentState;
        	fallingBlock = null;
        	blockX = blockY = 0;
        	cleanLines();
        } else {
        	currentState = nextState;
        }
	}
	private void cleanLines() {
		ArrayList<Character> tmpState = (ArrayList<Character>) baseState.clone();
		int clearedLines = 0;
		for (int row = 0; row < getRows(); row++) {
			int nonDotCount = 0;
			for (int col = 0; col < getColumns(); col++) {
				if (baseState.get(convertLocationToIndex(row, col)) != '.') {
					nonDotCount++;
				}
			}
			if (nonDotCount == getColumns()) {
				for (int col = 0; col < getColumns(); col++) {
					tmpState.set(row * getColumns() + col, '*');
				}
				clearedLines++;
			}
		}
		tmpState.removeAll(new ArrayList<Character>(){{add('*');}});
		if (tmpState.size() < baseState.size()) {
			for (int i = 0; i < clearedLines * getColumns(); i++) {
				tmpState.add(0, '.');
			}
			baseState = (ArrayList<Character>) tmpState.clone();
		}
	}

	public void moveRight() {
		blockX++;
        nextState = generateState();
        
        if (hasCollision()) {
        	blockX--;
        } else {
        	currentState = nextState;
        }
	}

	public void rotateRight() {
		if (fallingBlock == null) {
			return;
		}
		Piece tmpPiece = fallingBlock;
		fallingBlock = fallingBlock.rotateRight();
		
		nextState = generateState();
		
        if (hasCollision()) {
        	fallingBlock = tmpPiece;
        } else {
        	currentState = nextState;
        }
	}

	public void rotateLeft() {
		if (fallingBlock == null) {
			return;
		}
		Piece tmpPiece = fallingBlock;
		fallingBlock = fallingBlock.rotateLeft();
		
		nextState = generateState();
		
        if (hasCollision()) {
        	fallingBlock = tmpPiece;
        } else {
        	currentState = nextState;
        }	
		
	}

}
