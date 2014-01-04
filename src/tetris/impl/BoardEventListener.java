package tetris.impl;

public interface BoardEventListener {

	public void onDrop(Piece tShape);

	public void onTick();

	public void onMoveDown();

	public void onMoveRight();

	public void onMoveLeft();

	public void onRotateRight();

	public void onRotateLeft();

	public void onClean(int clearedLines);

}
