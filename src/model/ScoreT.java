/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 10, 2021
 *
 * Description: ScoreT Module (Abstract Object)
 */

package src.model;

/**
 * @brief An abstract object that maintains the score and high score of the
 *        game.
 */
public abstract class ScoreT {

  private static int score = 0;
  private static int highScore = 0;

  /**
   * @brief Initialize the score and high score of the game.
   * @details By default, the score and high score are set to 0 but this is used
   *          for purposes of resuming a previous game.
   * @param initalScore      The initial score of the game.
   * @param initialHighScore The initial high score of the game.
   * @throws IllegalArgumentException If the initial score or initial high score
   *                                  is less than 0 or if the initial high score
   *                                  is less than the initial score.
   */
  public static void initialize(int initialScore, int initialHighScore) {
    if (initialScore < 0 || initialHighScore < 0) {
      throw new IllegalArgumentException(
        "The initial score and initial high score must be greater than zero."
      );
    }
    if (initialHighScore < initialScore) {
      throw new IllegalArgumentException(
        "The initial high score must not be less than the initial score."
      );
    }

    score = initialScore;
    highScore = initialHighScore;
  }

  /**
   * @brief Add the given number of points to the current score and update the
   *        high score if necessary.
   * @param points The number of points to be added to current score.
   * @throws IllegalArgumentException If number of points being added is less than
   *                                  0.
   */
  public static void updateScore(int points) {
    if (points < 0) {
      throw new IllegalArgumentException(
        "The value of points must be greater than zero."
      );
    }
    score = score + points;
    if (score > highScore) {
      highScore = score;
    }
  }

  /**
   * @brief Get the value of the current score of the game.
   * @return The current score of the game.
   */
  public static int getScore() {
    return score;
  }

  /**
   * @brief Get the value of the current high score of the game.
   * @return The current high score of the game.
   */
  public static int getHighScore() {
    return highScore;
  }

  /**
   * @brief Reset the current score of the game back to 0.
   */
  public static void resetScore() {
    score = 0;
  }
}
