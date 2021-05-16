/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 12, 2021
 *
 * Description: Demo Module (Run this file by using the command: make demo)
 */

package src;

import src.controller.Controller;
import src.model.BoardT;
import src.view.View;

/**
 * @brief A library containing a single method for assembling the model, view and controller
 *        modules and launching the playable 2048 application. Please
 *        comment/ uncomment one of the two sections in the main method to either
 *        start a new game or restore a previous game.
 */
public class Demo {

  /**
   * @brief Initialize the model, view and controller objects, and launch the
   *        application.
   * @details Choose to either initialize a new game of 2048 or resume/continue a
   *          previous game based on a user-defined configuration of the board
   *          (must explicitly comment/uncomment selection).
   * @param args
   */
  public static void main(String[] args) {
    /**
     * 1) Initialize and display a new game of 2048.
     */

    Controller.newGame();
    View.display();
    /**
     * 2) Resume/restore a previous game of 2048.
     */

    // BoardT board = new BoardT(); // set up a configuration of the board
    // board.setTile(0, 0, 1024);
    // board.setTile(1, 0, 1024);
    // board.setTile(2, 0, 512);
    // board.setTile(3, 0, 256);
    // board.setTile(0, 1, 512);
    // board.setTile(1, 1, 256);
    // board.setTile(2, 1, 128);
    // board.setTile(0, 2, 16);
    // board.setTile(0, 3, 2);
    // Controller.resumeGame(board, 23184, 46368); // this is actually my high score
    // (no big deal)
    // View.display(); // initialize and display the graphical user interface (GUI)

  }
}
