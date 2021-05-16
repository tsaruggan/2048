/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 12, 2021
 *
 * Description: Unit test for the BoardT module using JUnit
 */

package test;

import static org.junit.Assert.*;

import org.junit.*;
import src.model.BoardT;

public class TestBoardT {

  private BoardT board1, board2, board3, board4;

  @Before
  public void setUp() {
    board1 =
      UnitTests.generateBoard(
        new int[] { 0, 2, 4, 8 },
        new int[] { 0, 0, 0, 0 },
        new int[] { 2, 2, 2, 2 },
        new int[] { 16, 0, 32, 0 }
      );
    board2 =
      UnitTests.generateBoard(
        new int[] { 8, 0, 0, 2 },
        new int[] { 4, 8, 0, 0 },
        new int[] { 0, 4, 8, 0 },
        new int[] { 2, 0, 0, 8 }
      );
    board3 =
      UnitTests.generateBoard(
        new int[] { 16, 2, 4, 2 },
        new int[] { 4, 8, 64, 2 },
        new int[] { 32, 4, 8, 2 },
        new int[] { 2, 2, 16, 8 }
      );
    board4 = new BoardT();
  }

  @After
  public void tearDown() {
    board1 = null;
    board2 = null;
    board3 = null;
    board4 = null;
  }

  @Test
  public void test_getTile1() {
    int value = board1.getTile(0, 3);
    assertEquals(value, 16);
  }

  @Test
  public void test_getTile2() {
    int value = board2.getTile(2, 0);
    assertEquals(value, 0);
  }

  @Test
  public void test_getTileEmpty() {
    int value = board4.getTile(2, 2);
    assertEquals(value, 0);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void test_getTileException() {
    int value = board1.getTile(1, 4);
  }

  @Test
  public void test_setTile1() {
    board1.setTile(3, 0, 64);
    int value = board1.getTile(3, 0);
    assertEquals(value, 64);
  }

  @Test
  public void test_setTile2() {
    board2.setTile(1, 2, 8);
    int value = board2.getTile(1, 2);
    assertEquals(value, 8);
  }

  @Test
  public void test_setTileEmpty() {
    board4.setTile(0, 0, 1024);
    int value = board4.getTile(0, 0);
    assertEquals(value, 1024);
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void test_setTileException() {
    board1.setTile(-1, 0, 64);
  }

  @Test
  public void test_isTileEmptyTrue() {
    assertTrue(board1.isTileEmpty(0, 0));
  }

  @Test
  public void test_isTileEmptyFalse() {
    assertFalse(board2.isTileEmpty(3, 3));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void test_isTileEmptyException() {
    board1.isTileEmpty(4, 3);
  }

  @Test
  public void test_isFullTrue() {
    assertTrue(board3.isFull());
  }

  @Test
  public void test_isFullFalse() {
    assertFalse(board2.isFull());
  }

  @Test
  public void test_equalsTrue() {
    BoardT x = UnitTests.generateBoard(
      new int[] { 8, 0, 0, 2 },
      new int[] { 4, 8, 0, 0 },
      new int[] { 0, 4, 8, 0 },
      new int[] { 2, 0, 0, 8 }
    );
    assertTrue(board2.equals(x));
  }

  @Test
  public void test_equalsFalse() {
    assertFalse(board1.equals(board3));
  }

  @Test
  public void test_copy() {
    BoardT x = board1.copy();
    assertTrue(board1.equals(x));
  }

  @Test
  public void test_copyNoAliasing() {
    BoardT x = board2.copy();
    x.setTile(0, 0, 2048);
    assertFalse(board2.equals(x));
  }
  
}
