/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 10, 2021
 *
 * Description: BoardManager Module (Library)
 */

package src.controller;

import java.util.ArrayList;
import java.util.Random;

import src.model.BoardT;
import src.model.DirectionT;

/**
 * @brief A library containing useful functions for manipulating BoardT objects.
 * @details Note: All methods do not change the state of the BoardT object
 *          passed in. Instead, a new BoardT object is returned.
 */
public abstract class BoardManager {

    /**
     * @brief Return a new BoardT object such that for every pair of duplicate
     *        tiles, the value of the tile in "front" is doubled and the value of
     *        the tile "behind" is set to 0 (i.e empty).
     * @param board     The board to be merged in the given direction.
     * @param direction The direction that the board is being merged in.
     * @return A new BoardT object representing the board merged in the given
     *         direction.
     */
    public static BoardT merge(BoardT board, DirectionT direction) {
        BoardT newBoard = board.copy();
        if (direction == DirectionT.LEFT) {
            int[] x_s = range(0, board.SIZE - 1);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeHorizontal(board, x_s, i, j)) {
                        newBoard.setTile(i, j, board.getTile(i, j) + board.getTile(i + 1, j));
                    } else if (!shouldMergeHorizontal(board, x_s, i, j)
                            && shouldMergeHorizontal(board, x_s, i - 1, j)) {
                        newBoard.setTile(i, j, 0);
                    }
                }
            }
        } else if (direction == DirectionT.RIGHT) {
            int[] x_s = range(board.SIZE - 1, 0);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeHorizontal(board, x_s, i, j)) {
                        newBoard.setTile(board.SIZE - 1 - i, j,
                                board.getTile(board.SIZE - 1 - i, j) + board.getTile(board.SIZE - 1 - i - 1, j));
                    } else if (!shouldMergeHorizontal(board, x_s, i, j)
                            && shouldMergeHorizontal(board, x_s, i - 1, j)) {
                        newBoard.setTile(board.SIZE - 1 - i, j, 0);
                    }
                }
            }
        } else if (direction == DirectionT.UP) {
            int[] y_s = range(0, board.SIZE - 1);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeVertical(board, y_s, i, j)) {
                        newBoard.setTile(i, j, board.getTile(i, j) + board.getTile(i, j + 1));
                    } else if (!shouldMergeVertical(board, y_s, i, j) && shouldMergeVertical(board, y_s, i, j - 1)) {
                        newBoard.setTile(i, j, 0);
                    }
                }
            }
        } else if (direction == DirectionT.DOWN) {
            int[] y_s = range(board.SIZE - 1, 0);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeVertical(board, y_s, i, j)) {
                        newBoard.setTile(i, board.SIZE - 1 - j,
                                board.getTile(i, board.SIZE - 1 - j) + board.getTile(i, board.SIZE - 1 - j - 1));
                    } else if (!shouldMergeVertical(board, y_s, i, j) && shouldMergeVertical(board, y_s, i, j - 1)) {
                        newBoard.setTile(i, board.SIZE - 1 - j, 0);
                    }
                }
            }
        }
        return newBoard;
    }

    /**
     * @brief Calculate the points accumulated from merging the given board in the
     *        given direction.
     * @details For every pair of duplicate tiles, the amount of points accumulated
     *          is equal to sum of the value of both tiles (or double the value of
     *          the tile).
     * @param board     The board to be merged in the given direction.
     * @param direction The direction that the board is being merged in.
     * @return The number of points accumulated from merging the given board in the
     *         given direction.
     */
    public static int scoreFromMerge(BoardT board, DirectionT direction) {
        int score = 0;
        if (direction == DirectionT.LEFT) {
            int[] x_s = range(0, board.SIZE - 1);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeHorizontal(board, x_s, i, j)) {
                        score += Math.abs(board.getTile(i, j)) + Math.abs(board.getTile(i + 1, j));
                    }
                }
            }
        } else if (direction == DirectionT.RIGHT) {
            int[] x_s = range(board.SIZE - 1, 0);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeHorizontal(board, x_s, i, j)) {
                        score += Math.abs(board.getTile(board.SIZE - 1 - i, j))
                                + Math.abs(board.getTile(board.SIZE - 1 - i - 1, j));
                    }
                }
            }
        } else if (direction == DirectionT.UP) {
            int[] y_s = range(0, board.SIZE - 1);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeVertical(board, y_s, i, j)) {
                        score += Math.abs(board.getTile(i, j)) + Math.abs(board.getTile(i, j + 1));
                    }
                }
            }
        } else if (direction == DirectionT.DOWN) {
            int[] y_s = range(board.SIZE - 1, 0);
            for (int i = 0; i < board.SIZE; i++) {
                for (int j = 0; j < board.SIZE; j++) {
                    if (shouldMergeVertical(board, y_s, i, j)) {
                        score += Math.abs(board.getTile(i, board.SIZE - 1 - j))
                                + Math.abs(board.getTile(i, board.SIZE - 1 - j - 1));
                    }
                }
            }
        }
        return score;
    }

    /**
     * @brief Return a new BoardT object such that the every tile in the board is
     *        aligned in the given direction.
     * @param board     The board to be aligned in the given direction.
     * @param direction The direction that the board is being aligned in.
     * @return A new BoardT object representing the board aligned in the given
     *         direction.
     */
    public static BoardT align(BoardT board, DirectionT direction) {
        BoardT newBoard = new BoardT();
        if (direction == DirectionT.LEFT) {
            for (int j = 0; j < board.SIZE; j++) {
                int[] nonEmptyRowIs = nonEmptyRowIndices(board, j);
                for (int i = 0; i < board.SIZE; i++) {
                    if (i < nonEmptyRowIs.length) {
                        int value = board.getTile(nonEmptyRowIs[i], j);
                        newBoard.setTile(i, j, value);
                    }
                }
            }
        } else if (direction == DirectionT.RIGHT) {
            for (int j = 0; j < board.SIZE; j++) {
                int[] nonEmptyRowIs = nonEmptyRowIndices(board, j);
                for (int i = 0; i < board.SIZE; i++) {
                    if (i >= board.SIZE - nonEmptyRowIs.length) {
                        int i_ = i - (board.SIZE - nonEmptyRowIs.length);
                        int value = board.getTile(nonEmptyRowIs[i_], j);
                        newBoard.setTile(i, j, value);
                    }
                }
            }
        } else if (direction == DirectionT.UP) {
            for (int i = 0; i < board.SIZE; i++) {
                int[] nonEmptyRowJs = nonEmptyColumnIndices(board, i);
                for (int j = 0; j < board.SIZE; j++) {
                    if (j < nonEmptyRowJs.length) {
                        int value = board.getTile(i, nonEmptyRowJs[j]);
                        newBoard.setTile(i, j, value);
                    }
                }
            }
        } else if (direction == DirectionT.DOWN) {
            for (int i = 0; i < board.SIZE; i++) {
                int[] nonEmptyRowJs = nonEmptyColumnIndices(board, i);
                for (int j = 0; j < board.SIZE; j++) {
                    if (j >= board.SIZE - nonEmptyRowJs.length) {
                        int j_ = j - (board.SIZE - nonEmptyRowJs.length);
                        int value = board.getTile(i, nonEmptyRowJs[j_]);
                        newBoard.setTile(i, j, value);
                    }
                }
            }
        }
        return newBoard;
    }

    /**
     * @brief Return a copy of the given BoardT object with a random tile added to a
     *        random empty position of the board.
     * @details Note: The probability of the value of the random tile is 90% chance
     *          of a 2 and 10% chance of a 4.
     * @param board The board to add a random tile to.
     * @return A new BoardT object with a random tile added to a random position of
     *         the given board.
     * @throws IllegalStateException If the given board is full.
     */
    public static BoardT addRandomTile(BoardT board) {
        if (board.isFull()) {
            throw new IllegalStateException("Cannot add random tile when board is full.");
        }

        Random rand = new Random();
        int i = rand.nextInt(board.SIZE);
        int j = rand.nextInt(board.SIZE);
        while (!board.isTileEmpty(i, j)) {
            i = rand.nextInt(board.SIZE);
            j = rand.nextInt(board.SIZE);
        }

        BoardT copy = board.copy();
        double chance = rand.nextDouble();
        if (chance < 0.9) {
            copy.setTile(i, j, 2);
        } else {
            copy.setTile(i, j, 4);
        }
        return copy;
    }

    private static boolean shouldMergeHorizontal(BoardT board, int[] x_s, int i, int j) {
        if (i < 0 || i >= board.SIZE - 1) {
            return false;
        }

        if (board.getTile(x_s[i], j) == board.getTile(x_s[i + 1], j)) {
            if (i == 0) {
                return true;
            }
            return board.getTile(x_s[i], j) != board.getTile(x_s[i - 1], j)
                    || shouldMergeHorizontal(board, x_s, i - 2, j);
        }
        return false;
    }

    private static boolean shouldMergeVertical(BoardT board, int[] y_s, int i, int j) {
        if (j < 0 || j >= board.SIZE - 1) {
            return false;
        }

        if (board.getTile(i, y_s[j]) == board.getTile(i, y_s[j + 1])) {
            if (j == 0) {
                return true;
            }
            return board.getTile(i, y_s[j]) != board.getTile(i, y_s[j - 1])
                    || shouldMergeVertical(board, y_s, i, j - 2);
        }
        return false;
    }

    private static int[] nonEmptyRowIndices(BoardT board, int j) {
        ArrayList<Integer> nonEmptyIs = new ArrayList<>();
        for (int i = 0; i < board.SIZE; i++) {
            if (!board.isTileEmpty(i, j)) {
                nonEmptyIs.add(i);
            }
        }
        int[] output = new int[nonEmptyIs.size()];
        for (int index = 0; index < output.length; index++) {
            output[index] = nonEmptyIs.get(index);
        }
        return output;
    }

    private static int[] nonEmptyColumnIndices(BoardT board, int i) {
        ArrayList<Integer> nonEmptyJs = new ArrayList<>();
        for (int j = 0; j < board.SIZE; j++) {
            if (!board.isTileEmpty(i, j)) {
                nonEmptyJs.add(j);
            }
        }
        int[] output = new int[nonEmptyJs.size()];
        for (int index = 0; index < output.length; index++) {
            output[index] = nonEmptyJs.get(index);
        }
        return output;
    }

    private static int[] range(int start, int stop) {
        int length = Math.abs(stop - start) + 1;
        int[] seq = new int[length];

        if (start < stop) {
            int x = start;
            for (int i = 0; i < length; i++) {
                seq[i] = x;
                x++;
            }
        } else if (stop < start) {
            int x = start;
            for (int i = 0; i < length; i++) {
                seq[i] = x;
                x--;
            }
        }
        return seq;
    }

}
