/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 10, 2021
 *
 * Description: BoardT Module (Abstract Data Type)
 */

package src.model;

/**
 * @brief An ADT that implements the board for the 2048 game using a matrix
 *        (2-dimensional array) of integer values to represent the state of the
 *        board.
 * @details Assume the top-left position of the board is (i, j) = (0, 0) and
 *          assume the bottom-right position of the board is (i, j) = (SIZE-1,
 *          SIZE-1).
 */
public class BoardT {
    /**
     * @brief The width/height of the board.
     */
    public final int SIZE = 4;

    private int[][] tiles;

    /**
     * @brief Initializes a BoardT object with all tiles set to 0 (i.e. empty).
     */
    public BoardT() {
        tiles = new int[SIZE][SIZE];
    }

    /**
     * @brief Get the value stored at the tile at positon i, j of the board.
     * @param i The horizontal index of the tile.
     * @param j The vertical index of the tile.
     * @return The value stored in the tile at position i, j of the board.
     * @throws IndexOutOfBoundsException If the value of i or j is not within the bounds of [0, SIZE).
     */
    public int getTile(int i, int j) {
        if (i < 0 || i >= SIZE || j < 0 || j >= SIZE) {
            throw new IndexOutOfBoundsException("The indices i and j must be in the range of [0..SIZE).");
        }
        return tiles[i][j];
    }

    /**
     * @brief Set the value to be stored at the tile at position i, j of the board.
     * @param i The horizontal index of the tile.
     * @param j The vertical index of the tile.
     * @param value The value to be stored in the tile at positition i, j of the board.
     * @throws IndexOutOfBoundsException If the value of i or j is not within the bounds of [0, SIZE).
     */
    public void setTile(int i, int j, int value) {
        if (i < 0 || i >= SIZE || j < 0 || j >= SIZE) {
            throw new IndexOutOfBoundsException("The indices i and j must be in the range of [0..SIZE).");
        }
        tiles[i][j] = value;
    }

    /**
     * @brief Check if the value stored at the tile at position i, j of the board is 0 (i.e. empty).
     * @param i The horizontal index of the tile.
     * @param j The vertical index of the tile.
     * @return True if the value of the tile is 0.
     * @throws IndexOutOfBoundsException If the value of i or j is not within the bounds of [0, SIZE).
     */
    public boolean isTileEmpty(int i, int j) {
        if (i < 0 || i >= SIZE || j < 0 || j >= SIZE) {
            throw new IndexOutOfBoundsException("The indices i and j must be in the range of [0..SIZE).");
        }
        return tiles[i][j] == 0;
    }

    /**
     * @brief Check if the board is full.
     * @return True if none of the tiles in the board are empty.
     */
    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (isTileEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @brief Check if board is equal to another given board.
     * @param other The board to compare equivalence with.
     * @return True if every tile in the board has the same value as very tile in other board.
     */
    public boolean equals(BoardT other) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (getTile(i, j) != other.getTile(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * @brief Make and return a copy of the current BoardT object.
     * @return A BoardT object that is equal to the current BoardT object
     */
    public BoardT copy() {
        BoardT myCopy = new BoardT();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                myCopy.setTile(i, j, getTile(i, j));
            }
        }
        return myCopy;
    }
}
