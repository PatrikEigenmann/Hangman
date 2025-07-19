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

// Standard Java imports
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Set;
import java.util.stream.Collectors;

// Hangman application imports
import Hangman.Control.GamePlay;
import Hangman.Utility.Debug;

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
     * The GamePlay instance that manages the game logic and state.
     * This is the core of the Hangman game, handling word selection, guesses,
     * and game status.
     */
    private final GamePlay gamePlay;

    /**
     * The menu bar for the main window, containing game controls and options.
     * It provides a structured way to access game functionalities like starting a new game,
     * accessing help, or special features.
     */
    private final JMenuBar menuBar = new JMenuBar();
    
    /**
     * Labels, buttons, and panels for the main window.
     * These components form the user interface, allowing interaction with the game.
     * They include input fields for guesses, display areas for the word and guessed letters,
     * and a gallows panel to visualize the game state.
     */
    private final JLabel guessedLettersLabel = new JLabel("Guessed: ");
    
    /**
     * The button to submit guesses.
     * This button is enabled when the game is active and allows the user to submit their letter
     * guesses. It is disabled when the game is over to prevent further input.
     */
    private final JButton goButton = new JButton("Go");
    
    /**
     * The input field for letter guesses.
     * This text field allows the user to enter their guesses. It is designed for single-character
     * input, and is enabled only when the game is active. It provides a simple way
     * for users to interact with the game by entering letters they think are in the word.
     * It is limited to 1 character to keep the input focused and straightforward.
     */
    private final JTextField guessInputField = new JTextField(2); // narrow, 1-char input
    
    /**
     * Label to display the current word being guessed.
     * This label shows the word with correctly guessed letters revealed and unguessed letters
     * represented by underscores. It updates dynamically as the user makes guesses,
     * providing visual feedback on their progress in the game.
     * The label is centered and uses a larger font for better visibility.
     * It is a key part of the user interface, allowing players to see how many letters
     * they have correctly guessed and how many remain to be discovered.
     * It is updated after each guess to reflect the current state of the word.
     * The label is set to a default text of "WORD" until the game starts,
     * at which point it will display the actual word to guess with underscores for unguessed
     * letters and the correct letters filled in.
     */
    private final JLabel wordDisplayLabel = new JLabel("WORD", SwingConstants.CENTER);
    
    /**
     * Panel to display the gallows and hangman figure.
     * This panel visually represents the game state, showing the gallows and the hangman figure
     * as the player makes incorrect guesses. It updates dynamically based on the number of wrong
     * guesses made, providing a visual cue of the player's progress and the stakes of the game
     */
    private final JPanel hangmanPanel = new JPanel(new GridLayout(3, 3));
    
    /**
     * Label to display messages to the user.
     * This label provides feedback to the player, such as instructions, game status updates,
     * and error messages. It is used to communicate important information about the game,
     * such as when a new game starts, when the player wins or loses, or if
     * the input is invalid.
     */
    private final JLabel messageLabel = new JLabel("Welcome to Hangman!", SwingConstants.CENTER);

    /**
     * Panel to display the gallows and hangman figure.
     * This panel is responsible for rendering the gallows and hangman figure based on the
     * current game state. It listens to the GamePlay instance for updates and redraws itself
     * whenever the game state changes, such as when a guess is made or the game is
     * over.
     */
    private GallowsPanel gallowsPanel;

    /**
     * Constructor for the main window. Sets up the user interface: the frequency slider,
     * buttons, and control logic. Wires up the interactions, lays out the components,
     * and initializes defaults. This is where everything visible (and some invisible)
     * gets wired together.
     */
    public MainFrame() {
        super("Hangman");

        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Initializing main frame...");
        gamePlay = new GamePlay();

        setupMenuBar();
        setupLayout();
        setupEvents();

        refreshUI();

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 400);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /**
     * Sets up the menu bar for the main window.
     * This method creates the game menu with options like "New Game" and adds it to
     * the menu bar. It also sets up keyboard shortcuts for quick access to these options.
     * The menu bar provides a structured way for users to interact with the game,
     * allowing them to start a new game, access help, or use special features.
     * The "New Game" option resets the game state and enables the input field and button
     * for user interaction. It also updates the message label to inform the user that a new
     * game has started. The menu bar is set to the JFrame, making it visible at
     * the top of the window.
     */
    private void setupMenuBar() {
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Setting up menu bar...");
        JMenu gameMenu = new JMenu("Game");
        JMenuItem newItem = new JMenuItem("New");
        newItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        newItem.addActionListener(e -> {
            gamePlay.resetGame();
            goButton.setEnabled(true);
            guessInputField.setEnabled(true);
            messageLabel.setText("New game started!");
            refreshUI();
        });

        gameMenu.add(newItem);
        menuBar.add(gameMenu);
        menuBar.add(new JMenu("Special")); // Placeholder
        menuBar.add(new JMenu("Help"));    // Placeholder

        setJMenuBar(menuBar);
    }

    /**
     * Sets up the layout for the main window.
     * This method organizes the components in a grid layout, placing the word display,
     * guessed letters, input field, and gallows panel in a structured manner.
     * It uses GridBagLayout for flexible positioning and ensures that the UI is responsive
     * and visually appealing. The layout is designed to provide a clear separation between
     * the interaction area (left side) and the gallows panel (right side).
     * The word display is prominently shown at the top, followed by the guessed letters,
     * the input field with the "Go" button, and a message label at the bottom.
     * The gallows panel is placed on the right side, visually representing the game state.
     * The layout is designed to be intuitive and user-friendly, allowing players to easily
     * see their progress and interact with the game.
     */
    private void setupLayout() {
        
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Setting up layout...");
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 15, 10, 15);

        // 📌 LEFT SIDE (Column 0) — Interaction Area
        // Word Display (Top)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.NONE;

        wordDisplayLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
        wordDisplayLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(wordDisplayLabel, gbc);
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Word display label set up.");

        // Guessed Letters (Middle)
        gbc.gridy++;
        guessedLettersLabel.setFont(new Font("Monospaced", Font.PLAIN, 16));
        guessedLettersLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(guessedLettersLabel, gbc);
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Guessed letters label set up.");

        // Input Field + Go Button (Bottom)
        gbc.gridy++;
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
        guessInputField.setPreferredSize(new Dimension(40, 25));
        goButton.setPreferredSize(new Dimension(60, 25));
        inputPanel.add(new JLabel("Guess:"));
        inputPanel.add(guessInputField);
        inputPanel.add(goButton);
        add(inputPanel, gbc);
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Input field and button set up.");

        // Message from the Machine (Very Bottom)
        gbc.gridy++;
        gbc.gridwidth = 1;
        messageLabel.setFont(new Font("SansSerif", Font.ITALIC, 13));
        messageLabel.setVerticalAlignment(SwingConstants.TOP);
        messageLabel.setPreferredSize(new Dimension(280, 60));
        messageLabel.setText("<html>Welcome to Hangman.<br>Guess wisely.</html>");
        messageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        add(messageLabel, gbc);
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Message label set up.");

        // 🎨 RIGHT SIDE (Column 1) — Gallows Panel
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;

        gallowsPanel = new GallowsPanel(gamePlay);
        gallowsPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));
        add(gallowsPanel, gbc);
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Gallows panel set up.");
    }

    /**
     * Sets up event listeners for user interactions.
     * This method binds actions to the "Go" button and the guess input field,
     * allowing the user to submit their guesses. It defines the behavior when the user
     * clicks the "Go" button or presses Enter in the input field.
     * The event handling logic processes the user's guess, updates the game state,
     * and refreshes the UI to reflect the current game status.
     * It ensures that the game responds to user input in a timely manner, providing
     * feedback on the guess and updating the display accordingly.
     * The "Go" button is enabled when the game starts, allowing the user to submit
     * their guesses. The guess input field is also enabled, allowing the user to type
     * their guesses directly. If the input is valid (a single letter), the guess is
     * processed, and the UI is updated to show the current state of the game.
     * If the input is invalid, an error message is displayed to guide the user.
     * This method is crucial for ensuring that the user can interact with the game
     * effectively and receive immediate feedback on their actions.
     * It also ensures that the game logic is executed correctly in response to user input.
     * It is called during the initialization of the main frame to set up the necessary
     * event handling for user interactions.
     */
    private void setupEvents() {
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Setting up event listeners...");
        ActionListener submitAction = e -> submitGuess();
        goButton.addActionListener(submitAction);
        guessInputField.addActionListener(submitAction);
    }

    /**
     * Processes the user's guess from the input field.
     * This method retrieves the input from the guess input field, validates it,
     * and submits it to the game logic for processing. It checks if the input is a
     * single letter and whether it is valid. If the input is valid, it processes the
     * guess by calling the appropriate method in the GamePlay instance.
     * It updates the message label to inform the user of their guess and refreshes
     * the UI to reflect the current game state.
     * If the input is invalid (not a single letter), it displays an error message
     * prompting the user to enter a valid letter. This method is called when the user
     * clicks the "Go" button or presses Enter in the guess input field.
     * It ensures that the game responds to user input correctly and provides feedback
     * to the user about their guess.
     * The method clears the input field after processing the guess to prepare for the next input.
     * It is essential for maintaining the flow of the game, allowing users to make guesses
     * and see the results immediately.
     */
    private void submitGuess() {
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Submitting guess...");
        String input = guessInputField.getText().trim().toUpperCase();
        if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
            gamePlay.processGuess(input.charAt(0));
            messageLabel.setText("Guess submitted: " + input);
            refreshUI();
            Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Guess processed: " + input);
        } else {
            messageLabel.setText("Invalid input. Please enter a single letter.");
            Debug.log(Debug.WARN, MainFrame.class.getSimpleName(), "Invalid input: " + input);
        }
        guessInputField.setText("");
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Input field cleared.");
    }

    /**
     * Refreshes the user interface to reflect the current game state.
     * This method updates the displayed word, guessed letters, gallows panel,
     * and message label based on the current state of the game. It retrieves the
     * guessed letters and the visible word from the GamePlay instance and updates
     * the corresponding labels. It also repaints the gallows panel to reflect the
     * current number of wrong guesses and the hangman figure.
     * If the game is over, it displays a message indicating whether the player won or lost
     * and disables the "Go" button and guess input field to prevent further input.
     * The method ensures that the user interface is always in sync with the game state,
     * providing a clear and up-to-date view of the game progress. It is called after
     * each guess to update the display and provide feedback to the user.
     */
    private void refreshUI() {
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Refreshing UI...");
        Set<Character> guessed = gamePlay.getGuessedLetters();

        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Guessed letters: " + guessed.stream().map(String::valueOf).collect(Collectors.joining(", ")));
        if (guessed.isEmpty()) {
            // Reserve vertical space but hide the label text before first guess.
            guessedLettersLabel.setText("<html><br><br><br></html>");
            Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "No letters guessed yet.");
        } else {
            StringBuilder html = new StringBuilder("<html>Already guessed letter:<br>");
            int count = 0;
            for (Character c : guessed) {
                html.append(c).append(" ");
                count++;
                if (count % 10 == 0) html.append("<br>");
            }
            Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Guessed letters HTML: " + html.toString());
            
            // Ensure label stays at minimum 3 lines tall
            int lines = (int) Math.ceil(guessed.size() / 10.0);
            for (int i = lines; i < 3; i++) html.append("<br>");

            html.append("</html>");
            Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Setting guessed letters label text.");
            guessedLettersLabel.setText(html.toString());
        }

        wordDisplayLabel.setText("Word: " + gamePlay.getVisibleWord());
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Word display updated: " + gamePlay.getVisibleWord());
        
        gallowsPanel.repaint();
        Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Gallows panel repainted.");

        if (gamePlay.isGameOver()) {
            Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Game Over! Wrong guesses: " + gamePlay.getWrongGuessCount());
            if(gamePlay.isGameWon()) {
                messageLabel.setText("🎉 Hurray, you guessed the word: " + gamePlay.getWordToGuess());
                Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Player won the game with word: " + gamePlay.getWordToGuess());
            } else {
                messageLabel.setText("💀 Game Over! The word was: " + gamePlay.getWordToGuess());
                Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Player lost the game. The word was: " + gamePlay.getWordToGuess());
            }
            goButton.setEnabled(false);
            guessInputField.setEnabled(false);
            Debug.log(Debug.INFO, MainFrame.class.getSimpleName(), "Input field and button disabled after game over.");
        }
    }

    // #region Testing and Debugging
    public static void main(String[] args) {
        Debug.init(Debug.ALL); // Diagnostic mode
        SwingUtilities.invokeLater(MainFrame::new);
    }
    // #endregion Testing and Debugging
}
