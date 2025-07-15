/* ----------------------------------------------------------------------------------------
 * GallowsPanel is a custom Swing component responsible for rendering the visual structure
 * of the Hangman gallows and the hanged figure as the game progresses. Built on top of
 * JPanel, it overrides the paintComponent method to draw the gallows, rope, stabilizing
 * beam, and the hangman's body parts using Graphics2D primitives.
 * 
 * The class is structured to reflect both architectural clarity and narrative consequence:
 * the gallows stands as a static stage, while the figure emerges progressively based on
 * incorrect guesses.
 * 
 * Internally, it tracks the player's failure count (`wrongGuessCount`) and responds by
 * revealing anatomical segments in sequence—head, torso, limbs—each stroke a metaphor
 * for rising tension.
 * 
 * The gallows itself is rendered in rich brown tones, with visual offsets and thickness
 * carefully calibrated to suggest permanence, weight, and emotional grounding. The rope
 * is differentiated by color and stroke weight to enhance contrast and realism, and is
 * anchored just beneath the top beam for structural believability.
 * 
 * This component functions independently from game logic, receiving updates via
 * `setWrongGuessCount(int)` and redrawing itself on demand. It pairs well with a
 * Model–View–Controller architecture, providing visual consequence to abstract game state.
 * ----------------------------------------------------------------------------------------
 * Author:      Patrik Eigenmann
 * eMail:       p.eigenmann@gmx.net
 * GitHub:      https://www.github.com/PatrikEigenmann/Hangman
 * ----------------------------------------------------------------------------------------
 * Change Log:
 * Mon 2025-07-14 File created.                                              Version: 00.01
 * ---------------------------------------------------------------------------------------- */
package Hangman.Gui;

// Standard Java imports
import javax.swing.*;
import java.awt.*;

// Hangman specific imports
import Hangman.Control.GamePlay;
import Hangman.Utility.Debug;

/**
 * GallowsPanel is a custom Swing component responsible for rendering the visual structure
 * of the Hangman gallows and the hanged figure as the game progresses. Built on top of
 * JPanel, it overrides the paintComponent method to draw the gallows, rope, stabilizing
 * beam, and the hangman's body parts using Graphics2D primitives.
 * 
 * The class is structured to reflect both architectural clarity and narrative consequence:
 * the gallows stands as a static stage, while the figure emerges progressively based on
 * incorrect guesses.
 * 
 * Internally, it tracks the player's failure count (`wrongGuessCount`) and responds by
 * revealing anatomical segments in sequence—head, torso, limbs—each stroke a metaphor
 * for rising tension.
 * 
 * The gallows itself is rendered in rich brown tones, with visual offsets and thickness
 * carefully calibrated to suggest permanence, weight, and emotional grounding. The rope
 * is differentiated by color and stroke weight to enhance contrast and realism, and is
 * anchored just beneath the top beam for structural believability.
 * 
 * This component functions independently from game logic, receiving updates via
 * `setWrongGuessCount(int)` and redrawing itself on demand. It pairs well with a
 * Model–View–Controller architecture, providing visual consequence to abstract game state. 
 */
public class GallowsPanel extends JPanel {

    /**
     * The GamePlay instance that provides the current game state,
     * specifically the number of wrong guesses.
     */
    private GamePlay gamePlay;

    /**
     * Constructs a GallowsPanel with the specified GamePlay instance.
     * This constructor initializes the panel's size and background color,
     * and sets up the gallows for rendering.
     * 
     * @param gamePlayIn The GamePlay instance that provides the current game state.
     */
    public GallowsPanel(GamePlay gamePlayIn) {
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Creating GallowsPanel for the first time.");        
        this.gamePlay = gamePlayIn;
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "GamePlay instance set: " + gamePlayIn);
        setPreferredSize(new Dimension(240, 300));
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Setting preferred size to 240x300 pixels.");
        setBackground(Color.BLACK);
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Setting background color to black.");
    }

    /**
     * Paints the gallows and hangman figure based on the current game state.
     * This method overrides the default paintComponent method of JPanel
     * to draw the gallows structure, the rope, and the hangman figure
     * based on the number of wrong guesses.
     * 
     * @param g The Graphics object used for drawing.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Painting GallowsPanel...");
        Graphics2D g2 = (Graphics2D) g;

        // Color tweak: rich brown for wood
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Setting color to SaddleBrown for gallows structure.");
        g2.setColor(new Color(139, 69, 19)); // SaddleBrown
        g2.setStroke(new BasicStroke(10)); // bold wooden beams

        int w = getWidth();
        int h = getHeight();
        int offsetX = 40; // your shifted post offset
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Width: " + w + ", Height: " + h + ", OffsetX: " + offsetX);

        // Base platform — remains unshifted
        g2.drawLine(w / 5, h - 30, w - w / 5, h - 30);

        // Vertical post — shifted
        g2.drawLine(w / 5 + offsetX, h - 30, w / 5 + offsetX, h / 6);

        // Top beam — shifted
        g2.drawLine(w / 5 + offsetX, h / 6, w / 2 + offsetX, h / 6);

        // Stabilizer beam — offset down + right by 3 pixels
        g2.drawLine(
            w / 5 + offsetX + 3,        // start X
            h / 6 + 20 + 3,             // start Y
            w / 5 + offsetX + 30 + 3,   // end X
            h / 6 + 3                   // end Y
        );
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing gallows structure with offset and stabilizer beam.");

        // Hanging rope — thinner and lighter, now starts at bottom of beam
        int beamY = h / 6;
        int ropeStartY = beamY + 8; // slightly below the beam to appear built-in
        int ropeEndY = h / 4;
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing rope from Y: " + ropeStartY + " to Y: " + ropeEndY);

        g2.setColor(new Color(184, 134, 11)); // DarkGoldenrod, rope tone
        g2.setStroke(new BasicStroke(4));
        g2.drawLine(w / 2 + offsetX, ropeStartY, w / 2 + offsetX, ropeEndY);

        // Replace this with your game logic source
        int wrongGuessCount = gamePlay.getWrongGuessCount(); 
        drawHangman(g2, wrongGuessCount);
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing hangman figure for wrong guess count: " + wrongGuessCount);
    }

    /**
     * Draws the hangman figure based on the number of wrong guesses.
     * This method uses Graphics2D to draw the head, torso, arms, and legs
     * of the hangman figure, revealing each part sequentially as the
     * wrong guess count increases.
     * 
     * @param g2 The Graphics2D object used for drawing.
     * @param count The number of wrong guesses, determining how many parts to draw.
     */
    private void drawHangman(Graphics2D g2, int count) {
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing hangman figure with count: " + count);
        g2.setColor(Color.LIGHT_GRAY); // pale tone for contrast
        g2.setStroke(new BasicStroke(4));
        
        int offsetX = 40;
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Calculating hangman figure position with offset: " + offsetX);
        
        int x = getWidth() / 2 + offsetX;
        int topY = getHeight() / 4; // rope bottom
        int headRadius = 20;
        Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Hangman figure position: X: " + x + ", Top Y: " + topY + ", Head Radius: " + headRadius);
   
        if (count >= 1) {
            // Head
            g2.drawOval(x - headRadius, topY, headRadius * 2, headRadius * 2);
            Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing head at X: " + (x - headRadius) + ", Y: " + topY + ", Radius: " + headRadius);
        }
        if (count >= 2) {
            // Torso
            g2.drawLine(x, topY + headRadius * 2, x, topY + headRadius * 2 + 40);
            Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing torso from Y: " + (topY + headRadius * 2) + " to Y: " + (topY + headRadius * 2 + 40));
        }
        if (count >= 3) {
            // Left Arm
            g2.drawLine(x, topY + headRadius * 2 + 10, x - 20, topY + headRadius * 2 + 30);
            Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing left arm from X: " + x + ", Y: " + (topY + headRadius * 2 + 10) + " to X: " + (x - 20) + ", Y: " + (topY + headRadius * 2 + 30));
        }
        if (count >= 4) {
            // Right Arm
            g2.drawLine(x, topY + headRadius * 2 + 10, x + 20, topY + headRadius * 2 + 30);
            Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing right arm from X: " + x + ", Y: " + (topY + headRadius * 2 + 10) + " to X: " + (x + 20) + ", Y: " + (topY + headRadius * 2 + 30));
        }
        if (count >= 5) {
            // Left Leg
            g2.drawLine(x, topY + headRadius * 2 + 40, x - 20, topY + headRadius * 2 + 60);
            Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing left leg from X: " + x + ", Y: " + (topY + headRadius * 2 + 40) + " to X: " + (x - 20) + ", Y: " + (topY + headRadius * 2 + 60));
        }
        if (count >= 6) {
            // Right Leg
            g2.drawLine(x, topY + headRadius * 2 + 40, x + 20, topY + headRadius * 2 + 60);
            Debug.log(Debug.INFO, GallowsPanel.class.getSimpleName(), "Drawing right leg from X: " + x + ", Y: " + (topY + headRadius * 2 + 40) + " to X: " + (x + 20) + ", Y: " + (topY + headRadius * 2 + 60));
        }
    }
}