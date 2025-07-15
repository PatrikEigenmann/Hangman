/* ----------------------------------------------------------------------------------------
 * GamePlay.java — Manages core game logic for Hangman. Tracks player state, including wrong
 * guesses, and updates the visual representation of the hanged figure through a grid-based
 * model. Encapsulates progression rules and determines game completion.
 * ----------------------------------------------------------------------------------------
 * Author:      Patrik Eigenmann
 * eMail:       p.eigenmann@gmx.net
 * GitHub:      https://www.github.com/PatrikEigenmann/Hangman
 * ----------------------------------------------------------------------------------------
 * Change Log:
 * Sun 2025-07-13 File created.                                              Version: 00.01
 * Sun 2025-07-13 Changed initializeGrid to initializeGame.                  Version: 00.02
 * Sun 2025-07-13 Added wordToGuess with sette/getter.                       Version: 00.03
 * Sun 2025-07-13 Added guessedLetters to track letters already guessed.     Version: 00.04
 * Sun 2025-07-13 Import the class Debug to track system layer messages.     Version: 00.05
 * Sun 2025-07-13 Added methods processGuess and getVisibleWord.             Version: 00.06
 * Mon 2025-07-14 Added isGameWon and extended the method isGameOver.        Version: 00.07
 * ---------------------------------------------------------------------------------------- */
package Hangman.Control;

// Standard Java imports
import java.util.Set;
import java.util.LinkedHashSet;

// Hangman Debugging Utility import
import Hangman.Utility.Debug;

/**
 * GamePlay.java — Manages core game logic for Hangman. Tracks player state, including wrong
 * guesses, and updates the visual representation of the hanged figure through a grid-based
 * model. Encapsulates progression rules and determines game completion. 
 */
public class GamePlay {

    /**
     * The word to guess. The center of the game. This is the word the player
     * attempts to guess by suggesting letters. All words will be in uppercase
     * and stored in a run-time database which can be accessed by a random
     * selection mechanism. This allows for easy expansion and modification.
     */
    private String wordToGuess = "DEFAULT";

    /**
     * Sets the word to guess for the current game. This method allows the
     * game to set a specific word that the player will try to guess. It ensures
     * that the word is always in uppercase to maintain consistency with the game's
     * rules and to avoid case sensitivity issues during letter matching.
     * 
     * @param word The word to set as the word to guess. If null, it defaults to an empty string.
     */
    public void setWordToGuess(String word) {
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Setting the word to guess: " + (word != null ? word.toUpperCase() : "DEFAULT"));
        this.wordToGuess = word != null ? word.toUpperCase() : "DEFAULT";
    }

    /**
     * Returns the current word to guess. This method provides access to the
     * word that the player is trying to guess in the current game session. It
     * is essential for checking the player's guesses against the correct word.
     * 
     * @return The word that the player is trying to guess, in uppercase.
     */
    public String getWordToGuess() {
        // Log the word to guess for debugging purposes
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Returning the word to guess: " + wordToGuess);
        
        // Return the current word to guess
        return wordToGuess;
    }

    /**
     * Tracks the letters that have already been guessed by the player.
     * This set is used to ensure that the player does not guess the same letter
     * multiple times, which could lead to unnecessary wrong guesses or confusion.
     * It is initialized as an empty set and grows as the player makes guesses.
     */
    private Set<Character> guessedLetters = new LinkedHashSet<>();

    /**
     * Adds a letter to the set of guessed letters. This method is used to
     * track which letters the player has already guessed during the game. It
     * ensures that the letter is stored in uppercase to maintain consistency
     * with the game's rules. This helps in preventing duplicate guesses and
     * allows the game to check against the word to guess effectively.
     * 
     * @param letter The letter to be added to the guessed letters set. It
     *               will be converted to uppercase.
     */
    public void addGuessedLetter(char letter) {
        // Log the letter being added to the guessed letters set
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Adding guessed letter: " + Character.toUpperCase(letter));
        
        // Add the letter to the set of guessed letters
        guessedLetters.add(Character.toUpperCase(letter));
    }

    /**
     * Returns the set of letters that have been guessed by the player.
     * This method provides access to the current state of guessed letters,
     * allowing the game to check if a letter has already been guessed before
     * processing a new guess. It is essential for maintaining the integrity of
     * the guessing process and ensuring that the player receives accurate feedback
     * on their guesses.
     * 
     * @return A set of characters representing the letters that have been guessed by the player.
     *        This set is used to track the player's progress and avoid duplicate guesses.
     */
    public Set<Character> getGuessedLetters() {
        return guessedLetters;
    }


    /**
     * Tracks the number of incorrect guesses made by the player. Used to determine game
     * progression and trigger updates to the hanged figure's visual state. 
     */
    private int wrongGuessCount = 0;

    /**
     * Returns the current number of incorrect guesses made by the player.
     * This value reflects the progress of the game and drives visual
     * updates to the hangman figure.
     *  
     * @return The count of wrong guesses made by the player.
     */
    public int getWrongGuessCount() {
        return wrongGuessCount;
    }

    /**
     * Increases the internal wrongGuessCount by one and updates the hangedmanGrid
     * to reflect the newly added body part. This method drives the progression of
     * the visual game state in response to incorrect guesses. 
     */
    public void incrementWrongGuesses() {
        // First check if the game is already over
        if(isGameOver()) {
            Debug.log(Debug.WARN, GamePlay.class.getSimpleName(), "Game is already over. Cannot increment wrong guesses.");
            return;
        }
        
        // Increment the wrong guess count
        wrongGuessCount++;
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Incremented wrongGuessCount to: " + wrongGuessCount);
        
        // Update the hangedmanGrid to reflect the new state
        updateGameGrid();
    }

    /**
     * The number of guesses made by the player. This is used to track how many
     * letters the player has guessed, regardless of whether they were correct or not.
     * It is initialized to zero and increments with each guess.
     */
    private int guessCount = 0;
    
    /**
     * A 3×3 string matrix representing the visual layout of the hangman figure. Each
     * cell corresponds to a specific body part location—head, torso, limbs—and updates
     * as the player accumulates wrong guesses.
     */
    private final String[][] hangedmanGrid = new String[3][3];

    /**
     * The maximum number of wrong guesses allowed before the game is over.
     * This constant defines the limit for incorrect guesses, which triggers the
     * end of the game when reached. It is set to 6, corresponding to the
     * six body parts of the hangman figure.
     */
    private boolean isWon = false;

    /**
     * Checks if the game has been won by the player. This method evaluates
     * whether the player has successfully guessed all letters in the word
     * to guess, indicating a win condition.
     * 
     * @return True if the game has been won, false otherwise.
     */
    public boolean isGameWon() {
        return isWon;
    }

    /**
     * Initializes the game state by resetting the wrong guess counter and preparing
     * the hangedmanGrid with empty values. Ensures a clean slate at the start of
     * each game session.
     */
    public GamePlay() {
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Entering the constructor of GamePlay.");
        
        // Initialize the game state
        initializeGame();
    }

    /**
     * Resets all entries in the hangedmanGrid to empty strings. Ensures that the
     * visual model starts clean before any wrong guesses are applied.
     */
    private void initializeGame() {
        
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Initializing the class GamePlay.");
        
        // Clear the guessed letters set
        guessedLetters.clear();
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Property guessedLetters cleared!");

        // Reset the wrong guess count to zero
        wrongGuessCount = 0;
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Reset property wrongGuessCount to zero!");

        // Reset the guess count to zero
        guessCount = 0;
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Reset property guessCount to zero!");

        // Initialize the hangedmanGrid with empty strings
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                hangedmanGrid[i][j] = " ";
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Initialized the hangedmanGrid with empty strings!");

        // Set the default word to guess
        String word = Hangman.Model.WordBank.getInstance().getWord();
        setWordToGuess(word);
        System.out.println("Word to guess: " + wordToGuess);

        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Set the word to guess to: " + wordToGuess);
    }

    /**
     * Resets the game state to its initial conditions. This method is called
     * when the player chooses to start a new game or reset the current game.
     * It clears the guessed letters, resets the wrong guess count,
     * and reinitializes the hangedmanGrid. It also sets a new word to
     * guess from the WordBank singleton instance.
     */
    public void resetGame() {
        
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Resetting the game state.");
        
        // Reinitialize the game state
        initializeGame();

        isWon = false;
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Reset property isWon to false!");

        // Log the reset action
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Game state has been reset successfully.");
    }

    /**
     * Increments the guessCount by one each time a letter is guessed.
     * This method is called regardless of whether the guess is correct or not,
     * allowing the game to track the total number of guesses made by the player.
     * It is essential for maintaining the game's flow and providing feedback to the player.
     */
    public void incrementGuesses() {

        // First check if the game is already over
        if(isGameOver()) {
            Debug.log(Debug.WARN, GamePlay.class.getSimpleName(), "Game is already over. Cannot increment guesses.");
            return;
        }

        // Increment the wrong guess count
        guessCount++;

        // Log the incremented guess count
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Incremented guessCount to: " + guessCount);        
    }

    /**
     * Applies the next body part to the hangedmanGrid based on the current
     * wrongGuessCount. Each call updates a specific cell in the 3×3 grid to
     * reflect progressive stages of the hangman figure—from head to
     * limbs—driven by incorrect guesses.
     */
    private void updateGameGrid() {
        
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Updating the hangedmanGrind according to the wrong guess count: " + wrongGuessCount + "!");
        
        // Validate the wrong guess count to ensure it is within the expected range.
        // This prevents invalid states and ensures the game logic remains consistent.
        if(wrongGuessCount < 1 || wrongGuessCount > 6) {
            Debug.log(Debug.ERR, GamePlay.class.getSimpleName(), "If wrongGuessCount is not between 1 and 6, an IllegalArgumentException will be thrown!");
            throw new IllegalArgumentException("Wrong guess count must be between 1 and 6.");
        }

        // Update the hangedmanGrid based on the current wrong guess count.
        switch (wrongGuessCount) {
            case 1 -> hangedmanGrid[0][1] = "O";     // Head
            case 2 -> hangedmanGrid[1][1] = "|";     // Torso
            case 3 -> hangedmanGrid[1][0] = "/";     // Left arm
            case 4 -> hangedmanGrid[1][2] = "\\";    // Right arm
            case 5 -> hangedmanGrid[2][0] = "/";     // Left leg
            case 6 -> hangedmanGrid[2][2] = "\\";    // Right leg — Game over
        }

        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Updating the hangedmanGrid is done!");
    }

    /**
     * Returns the current 3×3 representation of the hanged figure. This
     * grid reflects the visual game state and can be used by the view
     * layer to render the updated ASCII or graphical layout.
     * 
     * @return The current state of the hangedman grid.
     */
    public String[][] getHangedmanGrid() {
        // Return the current state of the hangedman grid
        return hangedmanGrid;
    }

    /**
     * Evaluates the current game state and returns true if the maximum number of
     * incorrect guesses has been reached. Indicates whether the player has lost
     * the game by completing the hangman figure.
     * 
     * @return True if the game is over (6 wrong guesses), false otherwise.
     */
    public boolean isGameOver() {
        
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Checking if the game is over with a triumph.");
        
        if (!getVisibleWord().contains("_")) {
            isWon = true;
        }
        
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Or checking if the game is over through a defeat.");

        return isWon || wrongGuessCount >= 6;
    }

    /**
     * Processes a player's guess by normalizing the input letter, checking if the
     * game is already over, and updating the guessed letters and wrong guess count.
     * If the letter has already been guessed, it logs a warning and ignores the guess.
     * If the guess is correct, it updates the visible word; if incorrect, it increments
     * the wrong guess count and updates the hanged figure.
     * 
     * @param guess The letter guessed by the player. It will be normalized to uppercase.
     */
    public void processGuess(char guess) {
        char normalized = Character.toUpperCase(guess);

        if (isGameOver()) {
            Debug.log(Debug.WARN, GamePlay.class.getSimpleName(), "Game is already over. Guess ignored.");
            return;
        }

        if (guessedLetters.contains(normalized)) {
            Debug.log(Debug.WARN, GamePlay.class.getSimpleName(), "Letter already guessed: " + normalized);
            return;
        }

        addGuessedLetter(normalized);
        incrementGuesses();

        if (wordToGuess.indexOf(normalized) >= 0) {
            Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Correct guess: " + normalized);
            // You may want to increment correct guesses here if tracking it later
        } else {
            incrementWrongGuesses(); // already handles logging + grid update
        }

        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Visible word: " + getVisibleWord());
        Debug.log(Debug.INFO, GamePlay.class.getSimpleName(), "Guessed letters: " + guessedLetters);
    }

    /**
     * Returns a string representation of the word to guess, showing
     * correctly guessed letters and underscores for unguessed letters.
     * This method is used to display the current state of the word to the player,
     * allowing them to see their progress in guessing the word.
     * 
     * @return A string representing the visible word, with guessed letters shown
     *        and unguessed letters replaced by underscores.
     */
    public String getVisibleWord() {
        StringBuilder sb = new StringBuilder();
        for (char c : wordToGuess.toCharArray()) {
            sb.append(guessedLetters.contains(c) ? c : '_').append(' ');
        }
        return sb.toString().trim();
    }

}
