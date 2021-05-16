/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 11, 2021
 *
 * Description: MessageUI Module (Abstract Data Type)
 */

package src.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

import src.controller.Controller;

/**
 * @brief An abstract object to display and update the view of the current state
 *        the 2048 game from the Controller module with respect to whether the
 *        player has achieved the 2048 tile or if the game is over.
 * @details This module extends Java Swing JLabel class and implements the
 *          ComponentUI interface. The state of the game is accessed directly
 *          from the Controller module and displays it alongside basic
 *          instructions for the game.
 */
public class MessageUI extends JLabel implements ComponentUI {

    /**
     * @brief Initialize and setup the look of the JLabel as well as access the
     *        current state of the game from the Controller module to set the text
     *        accordingly.
     */
    public MessageUI() {
        setFont(new Font("Helvetica", Font.BOLD, 13));
        setForeground(new Color(0x776e65));

        update();
    }

    /**
     * @brief Access the current state of the game from the Controller module and
     *        update the text accordingly.
     * @details The message displayed varies according to the state of the game. If
     *          the game is over, it prompts the player to start a new game or exit.
     *          Else, it prompts the player to use the arrow keys to a make a move
     *          to get the 2048 tile. If the 2048 tile has been achieved, it
     *          displays the next tile to get.
     */
    @Override
    public void update() {
        if (Controller.isGameOver()) {
            setText("Game over!");
        } else {
            if (Controller.nextTileToGet() == 2048) {
                setText("Join the tiles, get to 2048!");
            } else {
                setText("You won! Try to get to " + Controller.nextTileToGet() + "!");
            }
        }
    }

}
