package tetris.impl;

public class Tetromino{
	private int width;
	private int height;
	private char[] string;
	
	public Tetromino (String inputString) {	
		String[] lines = inputString.split("\n");
		height = lines.length;
		
		width = lines[0].length();
		this.string = inputString.replaceAll("\n", "").toCharArray();
	}
	
	public Tetromino rotateRight() {
		String newData = "";
		for (int col = 0; col < getWidth(); col++) {
			for (int row = getHeight() - 1; row >= 0; row--) {
				newData += getChars()[row * getWidth() + col];
			}
			newData += "\n";
		}
		return new Tetromino(newData);
	}
	
	@Override
	public String toString() {
		String string = new String();
		for (int row = 0; row < getHeight(); row++) {
			for (int col = 0; col < getWidth(); col++) {
				string += this.string[row * getWidth() + col];
			}
			string += "\n";
		}
		
		return string;
	}

	public Tetromino rotateLeft() {
		String newData = "";
		for (int col = getWidth() - 1; col >= 0; col--) {
			for (int row = 0; row < getHeight(); row++) {
				newData += getChars()[row * getWidth() + col];
			}
			newData += "\n";
		}
		return new Tetromino(newData);
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
	
	static class H_Tetromino extends Tetromino{

		public H_Tetromino() {
			super(".....\n.....\nIIII.\n.....\n.....\n");
		}
		
		@Override
		public Tetromino rotateLeft() {
			return new I_Tetromino();
		}
		@Override
		public Tetromino rotateRight() {
			return new I_Tetromino();
		}
		
	}
	
	static class I_Tetromino extends Tetromino{

		public I_Tetromino() {
			super("..I..\n..I..\n..I..\n..I..\n.....\n");
		}
		
		@Override
		public Tetromino rotateLeft() {
			return new H_Tetromino();
		}
		@Override
		public Tetromino rotateRight() {
			return new H_Tetromino();
		}
		
		
	}
	static class O_Tetromino extends Tetromino{

		public O_Tetromino() {
			super(".OO\n.OO\n...\n");
		}
		
		@Override
		public Tetromino rotateLeft() {
			return this;
		}
		
		@Override
		public Tetromino rotateRight() {
			return this;
		}
		
	}

	public static final Tetromino T_SHAPE = new Tetromino(".T.\nTTT\n...");
	public static final Tetromino I_SHAPE = new I_Tetromino();
	public static final Tetromino H_SHAPE = new H_Tetromino();
	public static final Tetromino O_SHAPE = new O_Tetromino();
	public static final Tetromino Z_SHAPE = new Tetromino("ZZ.\n.ZZ\n...");
	public static final Tetromino ReZ_SHAPE = new Tetromino(".ZZ\nZZ.\n...");
	public static final Tetromino L_SHAPE = new Tetromino("L..\nL..\nLL.");
	public static final Tetromino ReL_SHAPE = new Tetromino("..L\n..L\n.LL");
	
	public static final Tetromino[] Tetrominos = new Tetromino[] {T_SHAPE, I_SHAPE, H_SHAPE, O_SHAPE, Z_SHAPE, ReZ_SHAPE, L_SHAPE, ReL_SHAPE}; 
}
