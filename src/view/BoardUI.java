/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 11, 2021
 *
 * Description: BoardUI Module (Abstract Data Type)
 */

package src.view;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;

import src.controller.Controller;
import src.model.BoardT;

/**
 * @brief An abstract object to display and update the view of the current state
 *        of the 2048 game board from the Controller module.
 * @details This module extends the Java Swing JPanel class and implements the
 *          ComponentUI interface. The BoardT instance is accessed directly from
 *          the Controller module and displayed in a grid layout using TileUI
 *          components.
 */
public class BoardUI extends JPanel implements ComponentUI {
    private TileUI[][] tiles;
    private BoardT board;

    /**
     * @brief Initialize and setup the look of the JPanel as well as access the
     *        current BoardT instance from the Controller module to initialize the
     *        matrix (2-dimensional array) of TileUI components
     */
    public BoardUI() {
        board = Controller.getBoard();
        tiles = new TileUI[board.SIZE][board.SIZE];
        setLayout(new GridLayout(board.SIZE, board.SIZE));
        setBorder(BorderFactory.createLineBorder(new Color(0xbbada0), 5));
        setBackground(new Color(0xbbada0));
        for (int j = 0; j < board.SIZE; j++) {
            for (int i = 0; i < board.SIZE; i++) {
                tiles[i][j] = new TileUI(board.getTile(i, j));
                add(tiles[i][j]);
            }
        }
    }

    /**
     * @brief Access the current BoardT instance from the Controller module and
     *        set/update each TileUI component.
     */
    @Override
    public void update() {
        board = Controller.getBoard();
        for (int j = 0; j < board.SIZE; j++) {
            for (int i = 0; i < board.SIZE; i++) {
                tiles[i][j].setValue(board.getTile(i, j));
                tiles[i][j].update();
            }
        }
    }
}
