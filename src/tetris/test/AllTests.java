package tetris.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestBoard.class, TestGameCotroller.class,
		TestScoreCounter.class, TestTetrisTimerTask.class, TestTetromino.class })
public class AllTests {

}
