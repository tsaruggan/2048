/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 12, 2021
 *
 * Description: Unit test for the ScoreT module using JUnit
 */

package test;

import static org.junit.Assert.*;

import org.junit.*;
import src.model.ScoreT;

public class TestScoreT {

  @Before
  public void setUp() {
    ScoreT.initialize(0, 0);
  }

  @After
  public void tearDown() {
    ScoreT.initialize(0, 0);
  }

  @Test
  public void test_initial0() {
    assertTrue(ScoreT.getScore() == 0 && ScoreT.getHighScore() == 0);
  }

  @Test
  public void test_initialize() {
    ScoreT.initialize(2020, 1234568);
    assertTrue(ScoreT.getScore() == 2020 && ScoreT.getHighScore() == 1234568);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_initializeExceptionLessThanZero() {
    ScoreT.initialize(-1000, 1000);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_initializeExceptionHighScoreLessThanScore() {
    ScoreT.initialize(10000, 1000);
  }

  @Test
  public void test_updateScoreNonInitialized() {
    ScoreT.updateScore(1000);
    assertTrue(ScoreT.getScore() == 1000 && ScoreT.getHighScore() == 1000);
  }

  @Test
  public void test_updateScoreInitialized() {
    ScoreT.initialize(64, 10000);
    ScoreT.updateScore(1000);
    assertTrue(ScoreT.getScore() == 1064 && ScoreT.getHighScore() == 10000);
  }

  @Test
  public void test_updateScoreUpdatesHighScore() {
    ScoreT.initialize(280, 400);
    ScoreT.updateScore(220);
    assertTrue(ScoreT.getScore() == 500 && ScoreT.getHighScore() == 500);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_updateScoreException() {
    ScoreT.updateScore(-2048);
  }

  @Test
  public void test_getScoreNonInitialized() {
    assertEquals(ScoreT.getScore(), 0);
  }

  @Test
  public void test_getScoreInitialized() {
    ScoreT.initialize(4444, 8888);
    assertEquals(ScoreT.getScore(), 4444);
  }

  @Test
  public void test_getScoreAfterUpdate() {
    ScoreT.initialize(4444, 8888);
    ScoreT.updateScore(2);
    assertEquals(ScoreT.getScore(), 4446);
  }

  @Test
  public void test_getScoreAfterReset() {
    ScoreT.initialize(1010, 2020);
    ScoreT.resetScore();
    assertEquals(ScoreT.getScore(), 0);
  }

  @Test
  public void test_getHighScoreNonInitialized() {
    assertEquals(ScoreT.getHighScore(), 0);
  }

  @Test
  public void test_getHighScoreInitialized() {
    ScoreT.initialize(2222, 6666);
    assertEquals(ScoreT.getHighScore(), 6666);
  }

  @Test
  public void test_getHighScoreAfterUpdateUnchanged() {
    ScoreT.initialize(1122, 70088);
    ScoreT.updateScore(2);
    assertEquals(ScoreT.getHighScore(), 70088);
  }

  @Test
  public void test_getHighScoreAfterUpdateChanged() {
    ScoreT.initialize(366, 406);
    ScoreT.updateScore(42);
    assertEquals(ScoreT.getHighScore(), 408);
  }

  @Test
  public void test_getHighScoreAfterReset() {
    ScoreT.initialize(8888, 9998246);
    ScoreT.resetScore();
    assertEquals(ScoreT.getHighScore(), 9998246);
  }

  @Test
  public void test_resetNonInitialized() {
    ScoreT.resetScore();
    assertTrue(ScoreT.getScore() == 0 && ScoreT.getHighScore() == 0);
  }

  @Test
  public void test_resetInitialized() {
    ScoreT.initialize(2000000, 2000000000);
    ScoreT.resetScore();
    assertTrue(ScoreT.getScore() == 0 && ScoreT.getHighScore() == 2000000000);
  }
}
