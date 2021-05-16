/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 11, 2021
 *
 * Description: TileUI Module (Abstract Data Type)
 */

package src.view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 * @brief An abstract data type to display and update the view of an individual
 *        tile in the 2048 game board.
 * @details This module extends the Java Swing JLabel class and implements the
 *          ComponentUI interface. The value of the tile is set (by the BoardT
 *          module) and the background color, foreground color, and text is
 *          updated accordingly to resemble the look of the tiles in original
 *          2048 game. If the tile is "empty", its value is set to 0. Assume
 *          that the value of the tile is always positive and never greater than
 *          4 digits in length.
 */
public class TileUI extends JLabel implements ComponentUI {
    private int value;

    /**
     * @brief Initialize and setup the look of the JLabel as well as set its value
     *        and consequently its background color, foreground color and text
     *        accordingly.
     * @param initalValue The intial value of the tile. If the tile is "empty", the
     *                    initial value is set to 0.
     */
    public TileUI(int initalValue) {
        value = initalValue;

        setHorizontalAlignment(CENTER);
        setVerticalAlignment(CENTER);
        setFont(new Font("Helvetica", Font.BOLD, 28));
        setBorder(BorderFactory.createLineBorder(new Color(0xbbada0), 5));
        setOpaque(true);

        update();
    }

    /**
     * @brief Set the current value of the tile. If the tile is "empty", its value
     *        is set to 0.
     * @param newValue The value to set the tile to.
     */
    public void setValue(int newValue) {
        value = newValue;
    }

    /**
     * @brief Set the background color, foreground color, and text of the JLabel
     *        according to the current value of the tile.
     */
    @Override
    public void update() {
        if (value > 0) {
            setText(String.valueOf(value));
        } else {
            setText("");
        }
        setBackground(background());
        setForeground(foreground());
    }

    private Color foreground() {
        switch (value) {
        case 0:
            return new Color(0xe6e3e0);
        case 2:
            return new Color(0x776e65);
        case 4:
            return new Color(0x776e65);
        case 8:
            return new Color(0xf9f6f2);
        case 16:
            return new Color(0xf9f6f2);
        case 32:
            return new Color(0xf9f6f2);
        case 64:
            return new Color(0xf9f6f2);
        case 128:
            return new Color(0xf9f6f2);
        case 256:
            return new Color(0xf9f6f2);
        case 512:
            return new Color(0xf9f6f2);
        case 1024:
            return new Color(0xf9f6f2);
        case 2048:
            return new Color(0xf9f6f2);
        default:
            return new Color(0xf9f6f2);
        }
    }

    private Color background() {
        switch (value) {
        case 0:
            return new Color(0xe6e3e0);
        case 2:
            return new Color(0xeee4da);
        case 4:
            return new Color(0xeee1c9);
        case 8:
            return new Color(0xf3b27a);
        case 16:
            return new Color(0xf69664);
        case 32:
            return new Color(0xf77c5f);
        case 64:
            return new Color(0xf75f3b);
        case 128:
            return new Color(0xedd073);
        case 256:
            return new Color(0xedcc62);
        case 512:
            return new Color(0xedc950);
        case 1024:
            return new Color(0xedc53f);
        case 2048:
            return new Color(0xedc22e);
        default:
            return new Color(0x5eda92);
        }
    }

}
