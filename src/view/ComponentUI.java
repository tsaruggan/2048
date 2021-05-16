/**
 * Author: Saruggan Thiruchelvan (thirus6)
 * Revised: April 11, 2021
 *
 * Description: ComponentUI Module (Interface Module)
 */

package src.view;

/**
 * @brief An interface for UI components.
 * @details UI components are modules that extend a Java Swing JComponent class
 *          that need to be updated regularly and/or have values that change
 *          with each move in the 2048 game. UI component modules have the
 *          suffix "UI" in their names.
 */
public interface ComponentUI {
    /**
     * @brief The update method contains logic and functionality for updating the
     *        state of the UI component as well as updating the look of the UI
     *        component to reflect the changes.
     */
    public void update();
}
