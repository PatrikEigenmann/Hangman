/* ----------------------------------------------------------------------------------------
 * MainFrame.java - Provides the root window and primary GUI container for the application.
 * It manages layout, event routing, and user interaction across core components and is
 * intended to be reused across projects.
 *
 * By encapsulating the UI logic in this class, the application retains a clear separation
 * of concerns between interface and backend logic. This promotes modularity and allows
 * the UI to remain decoupled from specific functionality, making it suitable for Swing
 * applications or future frameworks.
 * ----------------------------------------------------------------------------------------
 * Author:      Patrik Eigenmann
 * eMail:       p.eigenmann@gmx.net
 * GitHub:      https://www.github.com/PatrikEigenmann/Hangman
 * ----------------------------------------------------------------------------------------
 * Change Log:
 * Sat 2025-07-12 File created.                                              Version: 00.01
 * ---------------------------------------------------------------------------------------- */

package Hangman.Gui;

import javax.swing.JFrame;

/**
 * MainFrame.java - Provides the root window and primary GUI container for the application.
 * It manages layout, event routing, and user interaction across core components and is
 * intended to be reused across projects.
 *
 * By encapsulating the UI logic in this class, the application retains a clear separation
 * of concerns between interface and backend logic. This promotes modularity and allows
 * the UI to remain decoupled from specific functionality, making it suitable for Swing
 * applications or future frameworks.
 */
public class MainFrame extends JFrame {

    /**
     * Constructor for the main window. Sets up the user interface: the frequency slider,
     * buttons, and control logic. Wires up the interactions, lays out the components,
     * and initializes defaults. This is where everything visible (and some invisible)
     * gets wired together.
     */
    public MainFrame() {
        super("Hangman");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
