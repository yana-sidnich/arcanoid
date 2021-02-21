// ID: 323537779
package game;

import java.awt.Color;

import biuoop.DrawSurface;
import geometry.Rectangle;

/**
 * this class will indicate and display the score of the game
 * implements the sprite interface.
 */
public class ScoreIndicator implements Sprite {

    private Counter score;
    private Rectangle scoreRectangle;
    private int fontSize;

    /**
     * Constructor f the score indicator class.
     * @param score a rfernece to the score.
     * @param scoreRectangle the score rectangle frame.
     * @param fontSize the size of the text.
     */
    public ScoreIndicator(Counter score, Rectangle scoreRectangle, int fontSize) {

        this.score = score;
        this.scoreRectangle = scoreRectangle;
        this.fontSize = fontSize;
    }
    /**
    * Draw the Sprite on the DrawSurface.
    * @param d DrawSurface to draw the Sprite on.
    */
    public void drawOn(DrawSurface d) {
        // draw score in a white frame
        this.scoreRectangle.drawOn(d, Color.WHITE);
        // score representation
        String toDraw = String.format("Score: %d", this.score.getValue());
        int upperLeftX = (int) (this.scoreRectangle.getUpperLeft().getX() + this.scoreRectangle.getWidth() / 2);
        // moce upperleft a little to the left in order to centelize the score.
        upperLeftX -= toDraw.length() * this.fontSize / 4;
        int upperLeftY = (int) (this.scoreRectangle.getUpperLeft().getY() + this.scoreRectangle.getHeight());
        // draw the score
        d.setColor(Color.BLACK);
        d.drawText(upperLeftX,
                   upperLeftY,
                   toDraw,
                   this.fontSize);
    }

    /**
     * Notify the Sprite that time has passed.
     */
    public void timePassed() {
        // empty
    }

    /**
     * Add ScoreIndicator to the game.
     * @param game Game to add the sprite to.
     */
    public void addToGame(Game game) {
        game.addSprite(this);
    }
}