package tetris.impl;

public class Piece extends Tetromino {
	public Piece(String inputString) {
		super(inputString);
		// TODO Auto-generated constructor stub
	}

	public Piece rotateRight() {
		String newData = "";
		for (int col = 0; col < getWidth(); col++) {
			for (int row = getHeight() - 1; row >= 0; row--) {
				newData += getChars()[row * getWidth() + col];
			}
			newData += "\n";
		}
		return new Piece(newData);
	}

	public Piece rotateLeft() {
		String newData = "";
		for (int col = getWidth() - 1; col >= 0; col--) {
			for (int row = 0; row < getHeight(); row++) {
				newData += getChars()[row * getWidth() + col];
			}
			newData += "\n";
		}
		return new Piece(newData);
	}
}
