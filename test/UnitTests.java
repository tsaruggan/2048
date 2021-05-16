/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 12, 2021
 *
 * Description: Unit tests for Assignment 4 Model and Controller modules using JUnit
 */

package test;

import java.util.ArrayList;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import src.controller.Controller;
import src.model.BoardT;
import src.model.ScoreT;

@RunWith(Suite.class)
@Suite.SuiteClasses(
  {
    TestBoardT.class,
    TestScoreT.class,
    TestBoardManager.class,
    TestController.class,
  }
)
public class UnitTests {

  public static BoardT generateBoard(
    int[] row0,
    int[] row1,
    int[] row2,
    int[] row3
  ) {
    BoardT board = new BoardT();
    int[] row = new int[4];
    for (int j = 0; j < board.SIZE; j++) {
      switch (j) {
        case 0:
          row = row0;
          break;
        case 1:
          row = row1;
          break;
        case 2:
          row = row2;
          break;
        case 3:
          row = row3;
          break;
      }

      for (int i = 0; i < board.SIZE; i++) {
        board.setTile(i, j, row[i]);
      }
    }
    return board.copy();
  }

  public static int[][] getDifferences(BoardT board1, BoardT board2) {
    ArrayList<int[]> differences = new ArrayList<>();

    for (int i = 0; i < board1.SIZE; i++) {
      for (int j = 0; j < board1.SIZE; j++) {
        if (board1.getTile(i, j) != board2.getTile(i, j)) {
          int[] diff = new int[] { i, j, board2.getTile(i, j) };
          differences.add(diff);
        }
      }
    }

    int[][] output = new int[differences.size()][3];
    for (int index = 0; index < output.length; index++) {
      output[index] = differences.get(index);
    }
    return output;
  }

  public static boolean controllerTest(
    BoardT expectedBoard,
    int expectedScore,
    int expectedHighScore,
    int expectedNumRandomTiles
  ) {
    boolean output = true;
    BoardT resultBoard = Controller.getBoard();
    int resultScore = ScoreT.getScore();
    int resultHighScore = ScoreT.getHighScore();
    output = output && expectedScore == resultScore;
    output = output && expectedHighScore == resultHighScore;
    int[][] differences = UnitTests.getDifferences(expectedBoard, resultBoard);
    for (int[] tile : differences) {
      output = output && expectedBoard.isTileEmpty(tile[0], tile[1]);
    }
    output = output && differences.length == expectedNumRandomTiles;
    return output;
  }
}
