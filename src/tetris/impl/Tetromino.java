package tetris.impl;

public class Tetromino extends Piece{
	public Tetromino(String inputString) {
		super(inputString);
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
}
