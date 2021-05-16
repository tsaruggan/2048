/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 12, 2021
 *
 * Description: Unit test for the Controller module using JUnit
 */

package test;

import static org.junit.Assert.*;

import org.junit.*;
import src.controller.Controller;
import src.model.BoardT;
import src.model.DirectionT;
import src.model.ScoreT;

public class TestController {

  BoardT boardEmpty, boardFull, boardH1, boardH2, boardV1, boardV2, boardCheckered, boardGameOver;

  @Before
  public void setUp() {
    Controller.newGame();
    ScoreT.initialize(0, 0);

    boardEmpty = new BoardT();
    boardFull =
      UnitTests.generateBoard(
        new int[] { 2, 2, 2, 2 },
        new int[] { 2, 2, 2, 2 },
        new int[] { 2, 2, 2, 2 },
        new int[] { 2, 2, 2, 2 }
      );
    boardH1 =
      UnitTests.generateBoard(
        new int[] { 1, 1, 1, 1 },
        new int[] { 2, 2, 0, 0 },
        new int[] { 0, 3, 3, 0 },
        new int[] { 0, 0, 4, 4 }
      );
    boardH2 =
      UnitTests.generateBoard(
        new int[] { 10, 11, 12, 10 },
        new int[] { 21, 20, 20, 21 },
        new int[] { 30, 0, 30, 30 },
        new int[] { 40, 40, 0, 40 }
      );

    boardV1 =
      UnitTests.generateBoard(
        new int[] { 1, 2, 0, 0 },
        new int[] { 1, 2, 3, 0 },
        new int[] { 1, 0, 3, 4 },
        new int[] { 1, 0, 0, 4 }
      );

    boardV2 =
      UnitTests.generateBoard(
        new int[] { 10, 21, 30, 40 },
        new int[] { 11, 20, 0, 40 },
        new int[] { 12, 20, 30, 0 },
        new int[] { 10, 21, 30, 40 }
      );

    boardCheckered =
      UnitTests.generateBoard(
        new int[] { 1, 0, 1, 0 },
        new int[] { 0, 1, 0, 1 },
        new int[] { 1, 0, 1, 0 },
        new int[] { 0, 1, 0, 1 }
      );

    boardGameOver =
      UnitTests.generateBoard(
        new int[] { 2, 4, 6, 8 },
        new int[] { 3, 5, 7, 9 },
        new int[] { 2, 4, 6, 8 },
        new int[] { 3, 5, 7, 9 }
      );
  }

  @After
  public void tearDown() {
    Controller.newGame();
    ScoreT.initialize(0, 0);
    boardEmpty = null;
    boardFull = null;
    boardH1 = null;
    boardH2 = null;
    boardV1 = null;
    boardV2 = null;
  }

  @Test
  public void test_newGame() {
    Controller.newGame();
    assertTrue(UnitTests.controllerTest(boardEmpty, 0, 0, 2));
  }

  @Test
  public void test_newGameAfterMoves() {
    Controller.move(DirectionT.LEFT);
    Controller.move(DirectionT.RIGHT);
    Controller.move(DirectionT.UP);
    Controller.move(DirectionT.DOWN);
    Controller.newGame();
    assertTrue(
      UnitTests.controllerTest(boardEmpty, 0, ScoreT.getHighScore(), 2)
    );
  }

  @Test
  public void test_resumeGame1() {
    Controller.resumeGame(boardH1, 1000, 2000);
    assertTrue(UnitTests.controllerTest(boardH1, 1000, 2000, 0));
  }

  @Test
  public void test_resumeGame2() {
    Controller.resumeGame(boardV1, 9876, 34567);
    assertTrue(UnitTests.controllerTest(boardV1, 9876, 34567, 0));
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_resumeGameException() {
    Controller.resumeGame(boardEmpty, 1000, -1000);
  }

  @Test
  public void test_moveLEFT1() {
    Controller.resumeGame(boardCheckered, 0, 0);
    Controller.move(DirectionT.LEFT);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 2, 0, 0, 0 },
      new int[] { 2, 0, 0, 0 },
      new int[] { 2, 0, 0, 0 },
      new int[] { 2, 0, 0, 0 }
    );
    assertTrue(UnitTests.controllerTest(expected, 8, 8, 1));
  }

  @Test
  public void test_moveLEFT2() {
    Controller.resumeGame(boardH2, 0, 100);
    Controller.move(DirectionT.LEFT);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 10, 11, 12, 10 },
      new int[] { 21, 40, 21, 0 },
      new int[] { 60, 30, 0, 0 },
      new int[] { 80, 40, 0, 0 }
    );
    assertTrue(UnitTests.controllerTest(expected, 180, 180, 1));
  }

  @Test
  public void test_moveRIGHT1() {
    Controller.resumeGame(boardCheckered, 0, 0);
    Controller.move(DirectionT.RIGHT);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 0, 0, 0, 2 },
      new int[] { 0, 0, 0, 2 },
      new int[] { 0, 0, 0, 2 },
      new int[] { 0, 0, 0, 2 }
    );
    assertTrue(UnitTests.controllerTest(expected, 8, 8, 1));
  }

  @Test
  public void test_moveRIGHT2() {
    Controller.resumeGame(boardH2, 0, 1000);
    Controller.move(DirectionT.RIGHT);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 10, 11, 12, 10 },
      new int[] { 0, 21, 40, 21 },
      new int[] { 0, 0, 30, 60 },
      new int[] { 0, 0, 40, 80 }
    );
    assertTrue(UnitTests.controllerTest(expected, 180, 1000, 1));
  }

  @Test
  public void test_moveUP1() {
    Controller.resumeGame(boardCheckered, 234, 567);
    Controller.move(DirectionT.UP);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 2, 2, 2, 2 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 }
    );
    assertTrue(UnitTests.controllerTest(expected, 242, 567, 1));
  }

  @Test
  public void test_moveUP2() {
    Controller.resumeGame(boardV2, 0, 0);
    Controller.move(DirectionT.UP);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 10, 21, 60, 80 },
      new int[] { 11, 40, 30, 40 },
      new int[] { 12, 21, 0, 0 },
      new int[] { 10, 0, 0, 0 }
    );
    assertTrue(UnitTests.controllerTest(expected, 180, 180, 1));
  }

  @Test
  public void test_moveDOWN1() {
    Controller.resumeGame(boardCheckered, 0, 7);
    Controller.move(DirectionT.DOWN);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 2, 2, 2, 2 }
    );
    assertTrue(UnitTests.controllerTest(expected, 8, 8, 1));
  }

  @Test
  public void test_moveDOWN2() {
    Controller.resumeGame(boardV2, 0, 0);
    Controller.move(DirectionT.DOWN);
    BoardT expected = UnitTests.generateBoard(
      new int[] { 10, 0, 0, 0 },
      new int[] { 11, 21, 0, 0 },
      new int[] { 12, 40, 30, 40 },
      new int[] { 10, 21, 60, 80 }
    );
    assertTrue(UnitTests.controllerTest(expected, 180, 180, 1));
  }

  @Test
  public void test_canMoveLEFTTrue() {
    Controller.resumeGame(boardH1, 0, 0);
    assertTrue(Controller.canMove(DirectionT.LEFT));
  }

  @Test
  public void test_canMoveLEFTFalse() {
    Controller.resumeGame(boardGameOver, 0, 0);
    assertFalse(Controller.canMove(DirectionT.LEFT));
  }

  @Test
  public void test_canMoveRIGHTTrue() {
    Controller.resumeGame(boardH1, 0, 0);
    assertTrue(Controller.canMove(DirectionT.RIGHT));
  }

  @Test
  public void test_canMoveRIGHTFalse() {
    Controller.resumeGame(boardGameOver, 0, 0);
    assertFalse(Controller.canMove(DirectionT.RIGHT));
  }

  @Test
  public void test_canMoveUPTrue() {
    Controller.resumeGame(boardV1, 0, 0);
    assertTrue(Controller.canMove(DirectionT.UP));
  }

  @Test
  public void test_canMoveUPFalse() {
    Controller.resumeGame(boardGameOver, 0, 0);
    assertFalse(Controller.canMove(DirectionT.UP));
  }

  @Test
  public void test_canMoveDOWNTrue() {
    Controller.resumeGame(boardV1, 0, 0);
    assertTrue(Controller.canMove(DirectionT.DOWN));
  }

  @Test
  public void test_canMoveDOWNFalse() {
    Controller.resumeGame(boardGameOver, 0, 0);
    assertFalse(Controller.canMove(DirectionT.DOWN));
  }

  @Test
  public void test_isGameOverTrue() {
    Controller.resumeGame(boardGameOver, 0, 0);
    assertTrue(Controller.isGameOver());
  }

  @Test
  public void test_isGameOverFalse() {
    Controller.resumeGame(boardV1, 0, 0);
    assertFalse(Controller.isGameOver());
  }

  @Test
  public void test_nextTileToGet2048() {
    Controller.newGame();
    assertEquals(Controller.nextTileToGet(), 2048);
  }

  @Test
  public void test_nextTileToGet4096() {
    boardFull.setTile(0, 0, 2048);
    Controller.resumeGame(boardFull, 1000, 1000);
    assertEquals(Controller.nextTileToGet(), 4096);
  }

  @Test
  public void test_nextTileToGet8192() {
    boardFull.setTile(0, 0, 2048);
    boardFull.setTile(1, 0, 4096);
    Controller.resumeGame(boardFull, 1000, 1000);
    assertEquals(Controller.nextTileToGet(), 8192);
  }

  @Test
  public void test_getBoardEmpty() {
    Controller.resumeGame(boardEmpty, 0, 0);
    assertTrue(boardEmpty.equals(Controller.getBoard()));
  }

  @Test
  public void test_getBoardResume() {
    Controller.resumeGame(boardFull, 1000, 1000);
    assertTrue(boardFull.equals(Controller.getBoard()));
  }
}
