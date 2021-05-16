/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 11, 2021
 *
 * Description: ScoreUI Module (Abstract Data Type)
 */

package src.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;

import src.model.ScoreT;

/**
 * @brief An abstract object to display and update the view of the current score
 *        and high score of the 2048 game from the ScoreT module.
 * @details This module extends Java Swing JPanel class and implements the
 *          ComponentUI interface. The score and high score are displayed using
 *          two JLabels and their values are accessed directly from the ScoreT
 *          module.
 */
public class ScoreUI extends JPanel implements ComponentUI {
    private JLabel score;
    private JLabel highScore;

    /**
     * @brief Initialize and setup the look of the JPanel as well as access the
     *        score/high score from the ScoreT module to display it using two
     *        JLabels.
     */
    public ScoreUI() {
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        score = new JLabel();
        score.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        score.setFont(new Font("Helvetica", Font.BOLD, 14));
        score.setBackground(new Color(0xbbada0));
        score.setForeground(new Color(0xFFFFFF));
        score.setOpaque(true);
        score.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(score, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(5, 0)));
        highScore = new JLabel();
        highScore.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        highScore.setFont(new Font("Helvetica", Font.BOLD, 14));
        highScore.setBackground(new Color(0xbbada0));
        highScore.setForeground(new Color(0xFFFFFF));
        highScore.setOpaque(true);
        highScore.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(highScore, BorderLayout.CENTER);

        update();
    }

    /**
     * @brief Access the current score/high score from the ScoreT module and update
     *        the text of the two JLabels.
     */
    @Override
    public void update() {
        score.setText(String.format("<html><center>SCORE<br>%s</center></html>", ScoreT.getScore()));
        highScore.setText(String.format("<html><center>BEST<br>%s</center></html>", ScoreT.getHighScore()));
    }
}
