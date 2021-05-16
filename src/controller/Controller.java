/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 11, 2021
 *
 * Description: Controller Module (Abstract Object)
 */

package src.controller;

import src.model.BoardT;
import src.model.DirectionT;
import src.model.ScoreT;

/**
 * @brief An abstract object that represents a controller used to make moves and
 *        check the state of the 2048 game.
 * @details The state of the board is stored in a BoardT object and the score is
 *          maintained using the ScoreT module. Either newGame or resumeGame is
 *          called before using the controller.
 */
public abstract class Controller {
    private static BoardT board;

    /**
     * @brief Reset the board, add two random tiles and reset the score.
     */
    public static void newGame() {
        board = BoardManager.addRandomTile(BoardManager.addRandomTile(new BoardT()));
        ScoreT.resetScore();
    }

    /**
     * @brief Resume a previous/existing game given a board, score and high score.
     * @details This is to be used to restore the state of the game and continue to
     *          play.
     * @param resumeBoard      The board to resume/continue the game from.
     * @param initialScore     The current score to resume/continue the game from
     *                         (initialized using the ScoreT module).
     * @param initialHighScore The high score to resume/continue the game from
     *                         (initialized using the ScoreT module).
     * @throws IllegalArgumentException If the initial score or initial high score
     *                                  is less than 0 or if the initial high score
     *                                  is less than the initial score.
     */
    public static void resumeGame(BoardT resumeBoard, int initialScore, int initialHighScore) {
        if (initialScore < 0 || initialHighScore < 0) {
            throw new IllegalArgumentException("The initial score and initial highscore must be greater than zero.");
        }
        if (initialHighScore < initialScore) {
            throw new IllegalArgumentException("The initial highscore must not be less than the initial score.");
        }
        board = resumeBoard.copy();
        ScoreT.initialize(initialScore, initialHighScore);
    }

    /**
     * @brief Make a move in the game in the given direction.
     * @details Assuming canMove is true, the board is aligned, merged and aligned
     *          in the given direction and the score is updated accordingly (using
     *          the ScoreT module).
     * @param direction The direction to be moved in.
     * @throws IllegalStateException If the game is over.
     * @see canMove
     * @see isGameOver
     */
    public static void move(DirectionT direction) {
        if (isGameOver()) {
            throw new IllegalStateException("Cannot move when game is over.");
        }

        if (canMove(direction)) {
            BoardT alignedBoard = BoardManager.align(board, direction);

            int points = BoardManager.scoreFromMerge(alignedBoard, direction);
            ScoreT.updateScore(points);

            board = BoardManager.merge(alignedBoard, direction);
            board = BoardManager.align(board, direction);
            board = BoardManager.addRandomTile(board);
        }
    }

    /**
     * @brief Check if a move in the given direction changes the board.
     * @param direction The direction to be moved in.
     * @return True if the board resulting from making a move in the given direction
     *         is not equal to the board prior to the move.
     * @see move
     */
    public static boolean canMove(DirectionT direction) {
        BoardT alignedBoard = BoardManager.align(board, direction);
        if (board.equals(alignedBoard)) {
            BoardT mergedBoard = BoardManager.merge(alignedBoard, direction);
            if (board.equals(mergedBoard)) {
                return false;
            }
        }
        return true;
    }

    /**
     * @brief Check if the game is over.
     * @return True if one cannot move in all four directions.
     */
    public static boolean isGameOver() {
        return !canMove(DirectionT.LEFT) && !canMove(DirectionT.RIGHT) && !canMove(DirectionT.UP)
                && !canMove(DirectionT.DOWN);
    }

    /**
     * @brief Get the next highest tile to be achieved in the game.
     * @details At the start of a new game, the next tile to be achieved is 2048.
     *          After the 2048 tile is achieved then the next tile is 4096, then
     *          8192, etc.
     * @return The value of the next highest tile to be achieved in the game.
     */
    public static int nextTileToGet() {
        int maxTile = 0;
        for (int i = 0; i < board.SIZE; i++) {
            for (int j = 0; j < board.SIZE; j++) {
                if (board.getTile(i, j) >= maxTile) {
                    maxTile = board.getTile(i, j);
                }
            }
        }

        if (maxTile < 2048) {
            return 2048;
        } else {
            return maxTile * 2;
        }
    }

    /**
     * @brief Return a copy of the current board.
     * @return A BoardT object equal to the current board.
     */
    public static BoardT getBoard() {
        return board.copy();
    }
}
