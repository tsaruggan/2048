/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 11, 2021
 *
 * Description: View Module (Abstract Object)
 */

package src.view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import src.controller.Controller;
import src.model.DirectionT;

/**
 * @brief An abstract object for displaying the the 2048 game using a graphical
 *        user interface (GUI).
 * @details This module makes use of the Java Swing toolkit to generate GUI
 *          components and the Java AWT Event package to handle user
 *          interactions. The View module is represented by a host of UI
 *          component modules (that are updated regularly) such as BoardUI,
 *          ScoreUI,and MessageUI and some non-UI components such as a JFrame
 *          for the window and a JLabel for the title among others.
 */
public class View implements ActionListener, KeyListener {
    private static final int WIDTH = 400;
    private static final int HEIGHT = 500;

    // Non-UI Components
    private static JFrame window;
    private static JLabel title;
    private static JButton newGameButton;

    // UI Components (need to be updated)
    private static BoardUI board;
    private static ScoreUI score;
    private static MessageUI message;

    private View() {

        // initialize window and add KeyListener
        window = new JFrame("2048");
        window.addKeyListener(this);
        window.setSize(WIDTH, HEIGHT);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setLayout(null);

        // initialize title and add to window
        title = new JLabel("2048");
        title.setBounds(16, 12, 164, 48);
        title.setFont(new Font("Helvetica", Font.BOLD, 48));
        title.setForeground(new Color(0x776e65));
        window.add(title);

        // initialize newGameButton and add ActionListener and add to window
        newGameButton = new JButton("New Game");
        newGameButton.setBounds((int) (WIDTH * 0.60), 75, 120, 36);
        newGameButton.setFont(new Font("Helvetica", Font.BOLD, 14));
        newGameButton.setOpaque(true);
        newGameButton.setBackground(new Color(0x8f7a66));
        newGameButton.setForeground(new Color(0xf9f6f2));
        newGameButton.setFocusable(false);
        newGameButton.setBorderPainted(false);
        newGameButton.setFocusPainted(false);
        newGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGameButton.addActionListener(this);
        window.add(newGameButton, BorderLayout.CENTER);

        // initialize score and add to window
        score = new ScoreUI();
        score.setBounds(180, 12, 200, 40);
        window.add(score, BorderLayout.CENTER);

        // initialize message and add to window
        message = new MessageUI();
        message.setBounds((int) (WIDTH * 0.1), 75, 200, 36);
        window.add(message);

        // initialize board and add to window
        board = new BoardUI();
        board.setBounds((int) (WIDTH * 0.1), 120, (int) (WIDTH * 0.8), (int) (WIDTH * 0.8));
        window.add(board, BorderLayout.CENTER);
    }

    /**
     * @brief Initialize and display the GUI window.
     * @details Assume this method is called after initializing the 2048 game using
     *          either newGame or resumeGame in the Controller module. A Java Swing
     *          JFrame acts a GUI window to display and update the BoardUI, ScoreUI,
     *          and MessageUI modules among other non-UI components.
     */
    public static void display() {
        new View();
        window.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (!Controller.isGameOver()) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
            case KeyEvent.VK_LEFT:
                Controller.move(DirectionT.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                Controller.move(DirectionT.RIGHT);
                break;
            case KeyEvent.VK_UP:
                Controller.move(DirectionT.UP);
                break;
            case KeyEvent.VK_DOWN:
                Controller.move(DirectionT.DOWN);
                break;
            }
            score.update();
            board.update();
            message.update();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Controller.newGame();
        score.update();
        board.update();
        message.update();
    }
}
