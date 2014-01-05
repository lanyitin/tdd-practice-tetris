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
	private BoardEventListener listener;

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
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
            	s += currentState.get(convertLocationToIndex(row, col));
            }
            s += "\n";
        }
        return s;
    }

	public boolean hasFalling() {
		return this.fallingBlock != null;
	}

	public void drop(Piece tShape) throws CantDropTetrominoException{
		if (hasFalling()) {
			throw new IllegalStateException("already falling");
		}
		blockX = columns/2 - tShape.getWidth() / 2;
		this.fallingBlock = tShape;
		int currentIndex = 0;
		char[] shapeChars = tShape.getChars();
		while (currentIndex < shapeChars.length) {
			if (shapeChars[currentIndex] != '.') {
				blockY -= (currentIndex / columns);
				break; 
			}
			currentIndex++;
		}
		
		generateNextState();
		
		if (hasCollision()) {
			throw new CantDropTetrominoException();
		}
		this.moveToNextState();
		if (listener != null) {
			listener.onDrop(tShape);
		}
		
	}

	public void tick() {
		fallingTetromino();
		if (listener != null) {
			listener.onTick();
		}
	}
	public void moveLeft() {
		shiftTetromino(-1);
		if (listener != null) {
			listener.onMoveLeft();
		}
	}

	public void moveRight() {
		shiftTetromino(1);
		if (listener != null) {
			listener.onMoveRight();
		}
	}
	
	public void moveDown() {
		fallingTetromino();
		if (listener != null) {
			listener.onMoveDown();
		}
	}
	
	public void rotateRight() {
		switchTetromino(fallingBlock.rotateRight());
        if (listener != null) {
			listener.onRotateRight();
		}
	}

	public void rotateLeft() {
		switchTetromino(fallingBlock.rotateLeft());
        if (listener != null) {
			listener.onRotateLeft();
		}
	}
	
    public void setListener(BoardEventListener listener) {
		this.listener = listener;
	}
    
	private void switchTetromino(Piece piece) {
		if (fallingBlock == null) {
			return;
		}
		Piece tmpPiece = fallingBlock;
		fallingBlock = piece;
		
		generateNextState();
		
        if (hasCollision()) {
        	fallingBlock = tmpPiece;
        } else {
        	moveToNextState();
        }
	}

	private void shiftTetromino(int offset) {
		int originX = blockX;
		if (!hasFalling()) {
			return;
		}
		blockX+= offset;
        generateNextState();
        
        if (hasCollision()) {
        	blockX = originX;
        } else {
        	moveToNextState();
        }
	}
	
	private void fallingTetromino() {
		if (!hasFalling()) {
			return;
		}
		blockY++;
        generateNextState();
        
        if (hasCollision()) {
        	baseState = currentState;
        	fallingBlock = null;
        	blockX = blockY = 0;
        	cleanLinesIfNeed();
        } else {
        	moveToNextState();
        }
	}

	@SuppressWarnings("unchecked")
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

	private void moveToNextState() {
		currentState = nextState;
	}

	private void generateNextState() {
		nextState = generateState();
	}
	
	@SuppressWarnings({ "unchecked", "serial" })
	private void cleanLinesIfNeed() {
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
		if (clearedLines > 0) {
			tmpState.removeAll(new ArrayList<Character>(){{add('*');}});
			if (tmpState.size() < baseState.size()) {
				for (int i = 0; i < clearedLines * getColumns(); i++) {
					tmpState.add(0, '.');
				}
				baseState = currentState = (ArrayList<Character>) tmpState.clone();
			}
		}
        if (listener != null) {
			listener.onClean(clearedLines);
		}
	}
	

	private char getCharFromFallingBlock(int row, int col) {
		int index = (row - blockY) * (fallingBlock.getWidth()) + col - blockX;
		return fallingBlock.getChars()[index];
	}

	private int convertLocationToIndex(int row, int col) {
		return row * columns + col;
	}

}
