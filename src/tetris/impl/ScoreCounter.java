package tetris.impl;

public class ScoreCounter {

	private int score;
	private int currentComboCount;
	private int maxAccumulatedComboCount;
	
	public ScoreCounter() {
		score = currentComboCount = maxAccumulatedComboCount = 0;
	}
	
	public void receiveNumberOfClearedLines(int num) {
		if (num > 0) {
			currentComboCount += num;
			score += currentComboCount * 10;
		} else {
			currentComboCount = 0;
		}
		maxAccumulatedComboCount = Math.max(maxAccumulatedComboCount, currentComboCount);
	}
	
	public int getScore() {
		return score;
	}

	public int getCurrentComboCount() {
		return currentComboCount;
	}

	public int getMaxAccumulatedComboCount() {
		return maxAccumulatedComboCount;
	}
	
}
