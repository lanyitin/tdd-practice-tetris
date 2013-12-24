package tetris.impl;

public class Piece{
	private int width;
	private int height;
	private char[] string;
	
	public Piece(String inputString) {	
		String[] lines = inputString.split("\n");
		height = lines.length;
		
		width = lines[0].length();
		this.string = inputString.replaceAll("\n", "").toCharArray();
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public char[] getChars() {
		return string.clone();
	}
	
	@Override
	public String toString() {
		String string = new String();
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				string += this.string[row * width + col];
			}
			string += "\n";
		}
		
		return string;
	}

	public Piece rotateRight() {
		String newData = "";
		for (int col = 0; col < width; col++) {
			for (int row = height - 1; row >= 0; row--) {
				newData += this.string[row * width + col];
			}
			newData += "\n";
		}
		return new Piece(newData);
	}

	public Piece rotateLeft() {
		String newData = "";
		for (int col = width - 1; col >= 0; col--) {
			for (int row = 0; row < height; row++) {
				newData += this.string[row * width + col];
			}
			newData += "\n";
		}
		return new Piece(newData);
	}
}
