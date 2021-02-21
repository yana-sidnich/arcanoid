// ID: 323537779
package game.animation;

import biuoop.DrawSurface;
import game.Counter;

/**
 * This class represents a win screen animation.
 * it Implemets the Animation interface.
 */
public class HighScoreAnimation implements Animation {
    private Counter score;

    /**
     * Constructor of the HighScoreAnimation class.
     * @param score the current score.
     */
    public HighScoreAnimation(Counter score) {
       this.score = score;
    }

    /**
     * Implements the doOneFrame Animation interface.
     * @param d surface to draw the screen on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, this.getString(), 32);
    }

    /**
     * should the animation stop.
     * @return this.stop.
     */
    public boolean shouldStop() {
        return false;
    }

    /**
     * create the string to show in screen.
     * @return string to show
     */
    private String getString() {
        return String.format("Your highest score is %d", this.score.getValue());
    }
}