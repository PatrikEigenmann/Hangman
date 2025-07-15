/* ----------------------------------------------------------------------------------------
 * WordBank.java - WordBank is a centralized vocabulary vault for Hangman gameplay. It
 * stores a curated list of 6–8 letter uppercase words and delivers randomized selections
 * for each new game session. Designed as a singleton, it ensures consistent access and
 * easy expansion, whether through manual additions or future encrypted imports. This class
 * embodies modular clarity, runtime efficiency, and gameplay integrity.
 * ----------------------------------------------------------------------------------------
 * Author:      Patrik Eigenmann
 * eMail:       p.eigenmann@gmx.net
 * GitHub:      https://www.github.com/PatrikEigenmann/Hangman
 * ----------------------------------------------------------------------------------------
 * Change Log:
 * Sun 2025-07-13 File created.                                              Version: 00.01
 * Sun 2025-07-13 Import the class Debug to track system layer messages.     Version: 00.02
 * ---------------------------------------------------------------------------------------- */

package Hangman.Model;

// Standard Java imports
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

// Hangman Debugging Utility import
import Hangman.Utility.Debug;

/**
 * The class WordBank is a centralized vocabulary vault for Hangman gameplay. It
 * stores a curated list of 6–8 letter uppercase words and delivers randomized selections
 * for each new game session. Designed as a singleton, it ensures consistent access and
 * easy expansion, whether through manual additions or future encrypted imports. This class
 * embodies modular clarity, runtime efficiency, and gameplay integrity. 
 */
public class WordBank {

    /**
     * Singleton instance of WordBank. This ensures that only one instance
     * of WordBank exists throughout the application, providing a consistent
     * source of words for the game.
     */
    private static WordBank instance;
    
    /**
     * The list of available words, the vocabulary vault for the game.
     * This list contains words that are 6 to 8 letters long, all in uppercase
     */
    private final List<String> words = new ArrayList<>();
    
    /**
     * Random instance for selecting words randomly from the list.
     * This allows for dynamic word selection each time a new game starts,
     * enhancing replayability and variety.
     */
    private final Random random = new Random();

    /**
     * Private constructor to initialize the word bank with a set of
     * predefined words. This ensures that the word bank is populated
     * with a consistent set of words that can be used in the game.
     */
    private WordBank() {
        
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Initializing the Wordbank with predefined words.");

        // Initialize the word bank with a set of predefinded words.
        words.add("ICECREAM");
        words.add("CAPTURE");
        words.add("BALANCE");
        words.add("HOLIDAY");
        words.add("VINTAGE");
        words.add("FURNACE");
        words.add("PENCIL");
        words.add("REWRITE");
        words.add("CAPTURE");
        words.add("FEATHER");
        words.add("HANGMAN");
        words.add("GALLOWS");
        words.add("ROTATES");
        words.add("FRAGMENT");
        words.add("EXPLORE");
        words.add("STRANGE");
        words.add("MODULES");
        words.add("PROCESS");
        words.add("SECURED");
        words.add("TWISTED");
        words.add("TOGGLED");
        words.add("VERSION");
        words.add("DESPAIR");
        words.add("MYSTERY");
        words.add("BALANCE");
        words.add("JOURNAL");
        words.add("DISPLAY");
        words.add("DECRYPT");
        words.add("ENIGMAS");
        words.add("REFLECT");
        words.add("TENSION");
        words.add("EXACTLY");
        words.add("STAGING");
        words.add("VINTAGE");
        words.add("TEXTURE");
        words.add("STORIES");
        words.add("PUNCHED");
        words.add("CONTEXT");
        words.add("CAPITAL");
        words.add("WRAPPED");
        words.add("CONTROL");
        words.add("ROUTINE");
        words.add("PACKAGE");
        words.add("DEBUGGY");
        words.add("CONSOLE");
        words.add("ELEMENT");
        words.add("BORDERS");
        words.add("MESSAGE");
        words.add("VERSION");
        words.add("COMMAND");
        words.add("UTILITY");
        words.add("DRAWING");
        words.add("PROJECT");
        words.add("LOADING");
        words.add("SURVIVE");
        words.add("CLEANUP");
        words.add("REPLIED");
        words.add("VORTEXY");
        words.add("SPLITUP");
        words.add("FORWARD");
        words.add("GHOSTLY");
        words.add("RETROED");
        words.add("FINALLY");
        words.add("TANGLED");
        words.add("FALLING");
        words.add("DRAWERS");
        words.add("VANISHY");
        words.add("BUILDER");
        words.add("TRIGGER");
        words.add("REPAINT");
        words.add("FOCUSER");
        words.add("JAVAFUN");
        words.add("TEMPLATE");
        words.add("SCALING");
        words.add("ALIGNER");
        words.add("SKIPPED");
        words.add("LOOPING");
        words.add("WRAPPER");
        words.add("STYLING");
        words.add("EMOTION");
        words.add("STRUCTS");
        words.add("PROCESS");
        words.add("DEFAULT");
        words.add("HANDLER");
        words.add("TEXTUAL");
        words.add("FRACTAL");
        words.add("DEEPER");
        words.add("LIMITED");
        words.add("SHADOWS");
        words.add("EXPRESS");
        words.add("TYPICAL");
        words.add("BUTTONS");
        words.add("CONSOLE");
        words.add("HINTING");
        words.add("ORBITAL");
        words.add("RESOLVE");
        words.add("GRAZING");
        words.add("CRUSHED");
        words.add("STUBBED");
        words.add("STARTER");
        words.add("FEEDING");
        words.add("REVERSE");
        words.add("EXPIRED");
        words.add("INTENTS");
        words.add("REPLACE");
        words.add("EXTENDS");
        words.add("SEGMENT");
        words.add("PROVIDE");
        words.add("WAITING");
        words.add("REVIVAL");
        words.add("ENABLED");
        words.add("ARCHIVE");
        words.add("LOGGING");
        words.add("RESCUE");
        words.add("TRICKED");
        words.add("CONVERT");
        words.add("REFINED");
        words.add("DARKLET");
        words.add("SCRIPTS");
        words.add("NEUTRAL");
        words.add("HACKING");
        words.add("WEIRDLY");
        words.add("DIGITAL");
        words.add("MARKING");
        words.add("GRIPPED");
        words.add("FLUTTER");
        words.add("WHISPED");
        words.add("WANDERS");
        words.add("INSIGHT");
        words.add("REACHED");
        words.add("STUTTER");
        words.add("SORTING");
        words.add("COMMAND");
        words.add("STICKER");
        words.add("LENGTHY");
        words.add("INDEXED");
        words.add("UNCOVER");
        words.add("RESONATE");
        words.add("TRAPPED");
        words.add("RETHINK");
        words.add("COLLIDE");
        words.add("UNCLEAN");
        words.add("CASCADY");
        words.add("OBSCURE");
        words.add("FUSIONS");
        words.add("RHYTHMS");
        words.add("FOLLOWS");
        words.add("BLURRED");
        words.add("DROPPED");
        words.add("MIRRORS");
        words.add("DANGLED");
        words.add("MISSING");
        words.add("UNKNOWN");
        words.add("CONNECT");
        words.add("WALKING");
        words.add("RELIANT");
        words.add("WILDING");
        words.add("NOTABLE");
        words.add("CRACKED");
        words.add("GLANCES");
        words.add("INVOKED");
        words.add("CHANNEL");
        words.add("NETWORK");
        words.add("BROWSER");
        words.add("BACKEND");
        words.add("DEFINED");
        words.add("MANAGED");
        words.add("SUSPEND");
        words.add("AWAKENS");
        words.add("DISPLAY");
        words.add("VIRTUAL");
        words.add("WRITTEN");

    }

    /**
     * Returns the singleton instance of WordBank. If the instance does not
     * exist, it creates a new one. This method ensures that there is only
     * one instance of WordBank throughout the application, providing a
     * consistent source of words for the game.
     * @return The singleton instance of WordBank.
     */
    public static WordBank getInstance() {
        
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Returning the singleton instance of WordBank.");
        
        // Singleton pattern to ensure only one instance of WordBank exists
        if (instance == null)
            instance = new WordBank();
        
        // Return the single instance
        return instance;
    }

    /**
     * Returns a random word from the word bank. This method selects a word
     * randomly from the list of available words, ensuring that each game
     * session can have a different word to guess, enhancing the gameplay
     * experience.
     * @return A random word from the word bank, or "DEFAULT" if the list is empty.
     */
    public String getWord() {
        
        if (words.isEmpty()) {
            Debug.log(Debug.WARN, WordBank.class.getSimpleName(), "The word bank is empty. Returning 'DEFAULT'.");
            return "DEFAULT";
        }

        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Returning a random word from the word bank.");
        return words.get(random.nextInt(words.size()));
    }

    /**
     * Returns the size of the word bank, i.e., the number of words available.
     * This method provides the count of words currently stored in the word bank,
     * which can be useful for determining the variety of words available for
     * gameplay.
     * @return The number of words in the word bank.
     */
    public int getSize() {
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Returning the size of the word bank: " + words.size());
        return words.size();
    }

    /**
     * Adds a new word to the word bank. The word must be in uppercase and
     * between 6 and 8 letters long. If the word is valid and not already
     * present in the word bank, it is added to the list. This method allows
     * for dynamic expansion of the word bank, enabling users to customize
     * the vocabulary used in the game.
     * @param word The word to be added to the word bank. It must be in uppercase
     *             and between 6 and 8 letters long.
     */
    public void addWord(String word) {
        
        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Attempting to add a new word: " + word);
        
        // Validate the word: must be uppercase.
        word = word.toUpperCase();

        Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "Validating the word: " + word);

        // Check if the word is between 6 and 8 letters long and not already in the list.
        if (word.matches("[A-Z]{6,8}") && !words.contains(word)) {
            Debug.log(Debug.INFO, WordBank.class.getSimpleName(), "The word is valid and will be added to the word bank.");            
            // Add the word to the list if it meets the criteria.
            words.add(word);
        }
    }
}