package tetris.impl;

public class Piece{
	private int width;
	private int height;
	private char[] string;
	
	public Piece(String inputString) {	
		String[] lines = inputString.split("\n");
		String data = new String();
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
		String data = new String();
		height = 0;
		for(int i = 0; i < string.length; i++) {
			data += string[i];
		}	
		return data;
	}
}
