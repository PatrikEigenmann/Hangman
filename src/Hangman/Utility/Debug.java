/* ----------------------------------------------------------------------------------------
 * Debug.java - This utility class provides a simple debugging and logging mechanism. It is
 * designed to give a developer the ability to look behind the scenes of the application
 * and track the flow of execution with errors, warnings and informational messages either
 * printed to the console or written to a log file. The logging can be controlled via a
 * bitmask flag system, allowing the developer to specify which types of message should be
 * shown or logged.
 * ----------------------------------------------------------------------------------------
 * Author:      Patrik Eigenmann
 * eMail:       p.eigenmann@gmx.net
 * GitHub:      https://www.github.com/PatrikEigenmann/Hangman
 * ----------------------------------------------------------------------------------------
 * Change Log:
 * Sun 2025-07-13 File created.                                              Version: 00.01
 * Sun 2025-07-13 Removed the test methods because the class is stable now.  Version: 00.02
 * ---------------------------------------------------------------------------------------- */
package Hangman.Utility;

// Standard Java imports
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Debug.java - This utility class provides a simple debugging and logging mechanism. It is
 * designed to give a developer the ability to look behind the scenes of the application
 * and track the flow of execution with errors, warnings and informational messages either
 * printed to the console or written to a log file. The logging can be controlled via a
 * bitmask flag system, allowing the developer to specify which types of message should be
 * shown or logged.
 */
public class Debug {

    /** This bitmask represents Errors  */
    public static final int ERR     = 0b0001;
    
    /** This bitmask represents Warnings  */
    public static final int WARN    = 0b0010;
    
    /** This bitmask represents Infos  */
    public static final int INFO    = 0b0100;
    
    /** This bitmask represents writing to a log file  */
    public static final int FILE    = 0b1000;

    /** This bitmask represents no debugging at all, mostly for release.  */
    public static final int NONE    = 0b0000;
    
    /** This bitmask represent tracking all (Error, Warnings, and Info)  */
    public static final int ALL     = ERR | WARN | INFO;

    /** Default mask is set to ALL  */
    private static int mask = ALL;
    
    /** This is the file path to the log file.  */
    private static final String FILE_PATH = "debug.log";
    
    /** Every debug entry gets a timestamp. Common practice.  */
    private static final DateTimeFormatter timestamp =
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * Here we initialize the debug utility with a specific mask. It is completely
     * up to the developer to decide which messages should be logged or printed. Combinations
     * of the constants defined above can be used to fine-tune the output.
     * 
     * Examples:
     * <pre>
     * Debug.init(Debug.ERR | Debug.WARN); // Only errors and warnings will be logged.
     * Debug.init(Debug.ALL | Debug.FILE); // All messages will be logged in the file.
     * Debug.init(Debug.NONE);             // No messages will be logged or printed.
     * </pre>
     * 
     * @param debugMask The bitmask that defines which types of messages should be logged or printed.
     */
    public static void init(int debugMask) {
        mask = debugMask;
    }

    /**
     * Calls the core multiplexer for this debugging utility, allowing the developer to log messages
     * with a specific log level and class name. This method formats the message with the class name
     * and the message itself, ensuring that the output is clear and consistent.
     * 
     * @param level The log level (ERR, WARN, INOF) to catogorize the message.
     * @param classname The name of the class from which the message originates.
     * @param msg The actual message to print or log.
     */
    public static void log(int level, String classname, String msg) {
        
        // Marry the class name with the message for consistency in the log output.
        String message = formatClassName(classname) + " " + msg;
        log(level, message);
    }

    /**
     * Acts as the core output multiplexer for this debugging utility. Based on a bitmask configuration,
     * this method conditionally routes messages to the console or a log file (debug.log), enriched
     * with a timestamp and log level. The output respects strict level masking, ensuring only qualified
     * messages are emitted through the selected channel.
     *
     * Developers should call this method directly with the appropriate log level:
     * {@code Debug.log(Debug.ERR, "Message")}, {@code Debug.log(Debug.INFO, "Message")}, etc.
     * 
     * @param level The log level (ERROR, WARN, INFO) to categorize the message.
     * @param msg The actual message to print or log.
     */
    public static void log(int level, String msg) {
        // Only proceed if level is enabled in the mask
        if ((mask & level) == 0) return;

        String output = timestamp.format(LocalDateTime.now()) + " " + formatLabel(level) + " - " + msg;

        if ((mask & FILE) != 0) {
            try (FileWriter writer = new FileWriter(FILE_PATH, true)) {
                writer.write(output + System.lineSeparator());
            } catch (IOException e) {
                // Use the own Debug class to log the error occurred while writing to the log file.
                // Debug.log(Debug.ERR, Debug.class.getSimpleName(), "Failed to write to debug.log: " + e.getMessage());
                // No, I can't do that here, I would create a infinite loop. Fall back to System.out.println();
                System.out.println("Failed to write to debug.log: " + e.getMessage());
            }
        } else {
            System.out.println(output);
        }
    }

    /**
     * Labeling the log message with the appropriate level to ensure clarity in the output.
     * This method is private and used internally to format the log messages.
     * @param level The log level (ERROR, WARN, INFO) to categorize the message.
     * @return A formatted string representing the log level.
     */
    private static String formatLabel(int level) {
        if (level == ERR) return "[ERROR]";
        if (level == WARN) return "[WARN] ";
        if (level == INFO) return "[INFO] ";
        return "[DEBUG]";
    }

    /**
     * Formats the class name for logging purposes. This method is used to ensure
     * the class name is presented in a consistent format in the log messages.
     * 
     * @param classname The name of the class to format.
     * @return A formatted string representing the class name, or a default
     *         value if the input is null or empty.
     */
    private static String formatClassName(String className) {
        // If the class name is null or empty, return a default value
        // to avoid NullPointerException or empty output.
        if (className == null || className.isEmpty()) {
            return "[UnknownClass]";
        }

        // Extract the simple class name from the fully qualified name
        // This is useful to avoid cluttering the log with package names.
        int lastDot = className.lastIndexOf(".");
        
        // If there is no dot, return the class name as is; otherwise, return the
        // substring after the last dot. This ensures we only get the simple class
        // name without the package. This is useful for cleaner log output.
        return "[" + ((lastDot != -1) ? className.substring(lastDot + 1) : className) + "]";
    }
}