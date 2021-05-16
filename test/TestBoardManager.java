/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 12, 2021
 *
 * Description: Unit test for the BoardManager module using JUnit
 */

package test;

import static org.junit.Assert.*;

import org.junit.*;
import src.controller.BoardManager;
import src.model.BoardT;
import src.model.DirectionT;

public class TestBoardManager {

  BoardT boardEmpty, boardFull, boardH1, boardH2, boardV1, boardV2, boardDiagonal;

  @Before
  public void setUp() {
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

    boardDiagonal =
      UnitTests.generateBoard(
        new int[] { 2, 0, 0, 0 },
        new int[] { 0, 4, 0, 0 },
        new int[] { 0, 0, 6, 0 },
        new int[] { 0, 0, 0, 8 }
      );
  }

  @After
  public void tearDown() {
    boardEmpty = null;
    boardFull = null;
    boardH1 = null;
    boardH2 = null;
    boardV1 = null;
    boardV2 = null;
    boardDiagonal = null;
  }

  @Test
  public void test_mergeLEFT1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 2, 0, 2, 0 },
      new int[] { 4, 0, 0, 0 },
      new int[] { 0, 6, 0, 0 },
      new int[] { 0, 0, 8, 0 }
    );
    assertTrue(x.equals(BoardManager.merge(boardH1, DirectionT.LEFT)));
  }

  @Test
  public void test_mergeLEFT2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 11, 12, 10 },
      new int[] { 21, 40, 0, 21 },
      new int[] { 30, 0, 60, 0 },
      new int[] { 80, 0, 0, 40 }
    );
    assertTrue(x.equals(BoardManager.merge(boardH2, DirectionT.LEFT)));
  }

  @Test
  public void test_mergeLEFTFull() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 4, 0, 4, 0 },
      new int[] { 4, 0, 4, 0 },
      new int[] { 4, 0, 4, 0 },
      new int[] { 4, 0, 4, 0 }
    );
    assertTrue(x.equals(BoardManager.merge(boardFull, DirectionT.LEFT)));
  }

  @Test
  public void test_mergeRIGHT1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 0, 2, 0, 2 },
      new int[] { 0, 4, 0, 0 },
      new int[] { 0, 0, 6, 0 },
      new int[] { 0, 0, 0, 8 }
    );
    assertTrue(x.equals(BoardManager.merge(boardH1, DirectionT.RIGHT)));
  }

  @Test
  public void test_mergeRIGHT2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 11, 12, 10 },
      new int[] { 21, 0, 40, 21 },
      new int[] { 30, 0, 0, 60 },
      new int[] { 0, 80, 0, 40 }
    );
    assertTrue(x.equals(BoardManager.merge(boardH2, DirectionT.RIGHT)));
  }

  @Test
  public void test_mergeRIGHTFull() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 0, 4, 0, 4 },
      new int[] { 0, 4, 0, 4 },
      new int[] { 0, 4, 0, 4 },
      new int[] { 0, 4, 0, 4 }
    );
    assertTrue(x.equals(BoardManager.merge(boardFull, DirectionT.RIGHT)));
  }

  public void test_mergeUP1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 2, 4, 0, 0 },
      new int[] { 0, 0, 6, 0 },
      new int[] { 2, 0, 0, 8 },
      new int[] { 0, 0, 0, 0 }
    );
    assertTrue(x.equals(BoardManager.merge(boardV1, DirectionT.UP)));
  }

  @Test
  public void test_mergeUP2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 21, 30, 80 },
      new int[] { 11, 40, 0, 0 },
      new int[] { 12, 0, 60, 0 },
      new int[] { 10, 21, 0, 40 }
    );
    assertTrue(x.equals(BoardManager.merge(boardV2, DirectionT.UP)));
  }

  @Test
  public void test_mergeUPFull() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 4, 4, 4, 4 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 4, 4, 4, 4 },
      new int[] { 0, 0, 0, 0 }
    );
    assertTrue(x.equals(BoardManager.merge(boardFull, DirectionT.UP)));
  }

  public void test_mergeDOWN1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 0, 0, 0, 0 },
      new int[] { 2, 4, 0, 0 },
      new int[] { 0, 0, 6, 0 },
      new int[] { 2, 0, 0, 8 }
    );
    assertTrue(x.equals(BoardManager.merge(boardV1, DirectionT.DOWN)));
  }

  @Test
  public void test_mergeDOWN2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 21, 30, 0 },
      new int[] { 11, 0, 0, 80 },
      new int[] { 12, 40, 0, 0 },
      new int[] { 10, 21, 60, 40 }
    );
    assertTrue(x.equals(BoardManager.merge(boardV2, DirectionT.DOWN)));
  }

  @Test
  public void test_mergeDOWNFull() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 0, 0, 0, 0 },
      new int[] { 4, 4, 4, 4 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 4, 4, 4, 4 }
    );
    assertTrue(x.equals(BoardManager.merge(boardFull, DirectionT.DOWN)));
  }

  public void test_scoreFromMergeLEFT1() {
    assertEquals(22, (BoardManager.scoreFromMerge(boardH1, DirectionT.LEFT)));
  }

  @Test
  public void test_scoreFromMergeLEFT2() {
    assertEquals(180, (BoardManager.scoreFromMerge(boardH2, DirectionT.LEFT)));
  }

  @Test
  public void test_scoreFromMergeLEFTFull() {
    assertEquals(32, (BoardManager.scoreFromMerge(boardFull, DirectionT.LEFT)));
  }

  public void test_scoreFromMergeRIGHT1() {
    assertEquals(22, (BoardManager.scoreFromMerge(boardH1, DirectionT.RIGHT)));
  }

  @Test
  public void test_scoreFromMergeRIGHT2() {
    assertEquals(180, (BoardManager.scoreFromMerge(boardH2, DirectionT.RIGHT)));
  }

  @Test
  public void test_scoreFromMergeRIGHTFull() {
    assertEquals(32, BoardManager.scoreFromMerge(boardFull, DirectionT.RIGHT));
  }

  @Test
  public void test_scoreFromMergeUP1() {
    assertEquals(22, (BoardManager.scoreFromMerge(boardV1, DirectionT.UP)));
  }

  @Test
  public void test_scoreFromMergeUP2() {
    assertEquals(180, (BoardManager.scoreFromMerge(boardV2, DirectionT.UP)));
  }

  @Test
  public void test_scoreFromMergeUPFull() {
    assertEquals(32, (BoardManager.scoreFromMerge(boardFull, DirectionT.UP)));
  }

  @Test
  public void test_scoreFromMergeDOWN1() {
    assertEquals(22, (BoardManager.scoreFromMerge(boardV1, DirectionT.DOWN)));
  }

  @Test
  public void test_scoreFromMergeDOWN2() {
    assertEquals(180, (BoardManager.scoreFromMerge(boardV2, DirectionT.DOWN)));
  }

  @Test
  public void test_scoreFromMergeDOWNFull() {
    assertEquals(32, (BoardManager.scoreFromMerge(boardFull, DirectionT.DOWN)));
  }

  @Test
  public void test_alignLEFT1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 1, 1, 1, 1 },
      new int[] { 2, 2, 0, 0 },
      new int[] { 3, 3, 0, 0 },
      new int[] { 4, 4, 0, 0 }
    );
    assertTrue(x.equals(BoardManager.align(boardH1, DirectionT.LEFT)));
  }

  @Test
  public void test_alignLEFT2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 11, 12, 10 },
      new int[] { 21, 20, 20, 21 },
      new int[] { 30, 30, 30, 0 },
      new int[] { 40, 40, 40, 0 }
    );
    assertTrue(x.equals(BoardManager.align(boardH2, DirectionT.LEFT)));
  }

  @Test
  public void test_alignLEFTDiagonal() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 2, 0, 0, 0 },
      new int[] { 4, 0, 0, 0 },
      new int[] { 6, 0, 0, 0 },
      new int[] { 8, 0, 0, 0 }
    );
    assertTrue(x.equals(BoardManager.align(boardDiagonal, DirectionT.LEFT)));
  }

  @Test
  public void test_alignRIGHT1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 1, 1, 1, 1 },
      new int[] { 0, 0, 2, 2 },
      new int[] { 0, 0, 3, 3 },
      new int[] { 0, 0, 4, 4 }
    );
    assertTrue(x.equals(BoardManager.align(boardH1, DirectionT.RIGHT)));
  }

  @Test
  public void test_alignRIGHT2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 11, 12, 10 },
      new int[] { 21, 20, 20, 21 },
      new int[] { 0, 30, 30, 30 },
      new int[] { 0, 40, 40, 40 }
    );
    assertTrue(x.equals(BoardManager.align(boardH2, DirectionT.RIGHT)));
  }

  @Test
  public void test_alignRIGHTDiagonal() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 0, 0, 0, 2 },
      new int[] { 0, 0, 0, 4 },
      new int[] { 0, 0, 0, 6 },
      new int[] { 0, 0, 0, 8 }
    );
    assertTrue(x.equals(BoardManager.align(boardDiagonal, DirectionT.RIGHT)));
  }

  @Test
  public void test_alignUP1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 1, 2, 3, 4 },
      new int[] { 1, 2, 3, 4 },
      new int[] { 1, 0, 0, 0 },
      new int[] { 1, 0, 0, 0 }
    );
    assertTrue(x.equals(BoardManager.align(boardV1, DirectionT.UP)));
  }

  @Test
  public void test_alignUP2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 21, 30, 40 },
      new int[] { 11, 20, 30, 40 },
      new int[] { 12, 20, 30, 40 },
      new int[] { 10, 21, 0, 0 }
    );

    assertTrue(x.equals(BoardManager.align(boardV2, DirectionT.UP)));
  }

  @Test
  public void test_alignUPDiagonal() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 2, 4, 6, 8 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 }
    );
    assertTrue(x.equals(BoardManager.align(boardDiagonal, DirectionT.UP)));
  }

  @Test
  public void test_alignDOWN1() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 1, 0, 0, 0 },
      new int[] { 1, 0, 0, 0 },
      new int[] { 1, 2, 3, 4 },
      new int[] { 1, 2, 3, 4 }
    );
    assertTrue(x.equals(BoardManager.align(boardV1, DirectionT.DOWN)));
  }

  @Test
  public void test_alignDOWN2() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 10, 21, 0, 0 },
      new int[] { 11, 20, 30, 40 },
      new int[] { 12, 20, 30, 40 },
      new int[] { 10, 21, 30, 40 }
    );

    assertTrue(x.equals(BoardManager.align(boardV2, DirectionT.DOWN)));
  }

  @Test
  public void test_alignDOWNDiagonal() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 0, 0, 0, 0 },
      new int[] { 2, 4, 6, 8 }
    );
    assertTrue(x.equals(BoardManager.align(boardDiagonal, DirectionT.DOWN)));
  }

  @Test
  public void test_addRandomTile1() {
    BoardT x = BoardManager.addRandomTile(boardH1);
    int[][] differences = UnitTests.getDifferences(boardH1, x);
    int[] tile = differences[0];
    assertTrue(
      differences.length == 1 &&
      (x.getTile(tile[0], tile[1]) == 2 || x.getTile(tile[0], tile[1]) == 4)
    );
  }

  @Test
  public void test_addRandomTile2() {
    BoardT x = BoardManager.addRandomTile(boardV1);
    int[][] differences = UnitTests.getDifferences(boardV1, x);
    int[] tile = differences[0];
    assertTrue(
      differences.length == 1 &&
      (x.getTile(tile[0], tile[1]) == 2 || x.getTile(tile[0], tile[1]) == 4)
    );
  }

  @Test
  public void test_addRandomTileEmpty() {
    BoardT x = BoardManager.addRandomTile(boardEmpty);
    int[][] differences = UnitTests.getDifferences(boardEmpty, x);
    int[] tile = differences[0];
    assertTrue(
      differences.length == 1 &&
      (x.getTile(tile[0], tile[1]) == 2 || x.getTile(tile[0], tile[1]) == 4)
    );
  }

  @Test(expected = IllegalStateException.class)
  public void test_addRandomTileException() {
    BoardT x = BoardManager.addRandomTile(boardFull);
  }
}
