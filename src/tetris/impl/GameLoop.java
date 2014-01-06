package tetris.impl;

import java.util.concurrent.atomic.AtomicBoolean;

public abstract class GameLoop implements Runnable{
	private static final int NUM_DELAY_PER_YIELD = 100;
	private static final int FPS = 1;
	private static final long MAX_FRAME_SKIPS = 5L;
	private final long period = (long) (1000.0 / FPS);
	private long gameStartTime;
	private volatile AtomicBoolean running;
	protected int framesSkipped;
	private Thread animateThread;


	abstract public void printScreen();

	abstract public void gameRender();

	abstract public void gameUpdate();

	public void start() {
		if (animateThread == null) {
			animateThread = new Thread(this);
			running.set(true);
			animateThread.start();
			animateThread.setName("Game Main Loop");
		}
	}

	public void stop() {
		running.set(false);
		try {
			animateThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		// the loop for drawing
		long beforeTime, afterTime, timeDiff, sleepTime;
		long overSleepTime = 0L;
		int numOfDelays = 0;
		long excess = 0L;
		gameStartTime = System.nanoTime();
		//prevStatsTime = gameStartTime;
		beforeTime = gameStartTime;
		
		while (running.get()) {
			gameUpdate();
			gameRender();
			printScreen();
			
			afterTime = System.nanoTime();
			timeDiff = afterTime - beforeTime;
			sleepTime = (period  - timeDiff) - overSleepTime;
			
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime / 100000L);
				} catch (InterruptedException e) { }
				overSleepTime = (System.nanoTime() - afterTime) - sleepTime;
			} else {
				excess = Math.abs(sleepTime);
				overSleepTime = 0L;
				numOfDelays++;
				
				if (numOfDelays >= NUM_DELAY_PER_YIELD) {
					Thread.yield(); //give another thread chance to run
					numOfDelays = 0;
				}
			}
			
			beforeTime = System.nanoTime();
			
			// If frame animation is taking too long, update the game state without rendering it, to get the updates/sec nearer to the required FPS
			int skips = 0;
			while ((excess > period) && (skips < MAX_FRAME_SKIPS)) {
				excess -= period;
				gameUpdate();
				skips++;
			}
			framesSkipped = skips;
		}
	}

	public GameLoop() {
		super();
		running = new AtomicBoolean(false);
	}

}