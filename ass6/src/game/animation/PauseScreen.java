// ID: 323537779
package game.animation;

import biuoop.DrawSurface;

/**
 * This class represents a pause screen animation.
 * it Implemets the Animation interface.
 */
public class PauseScreen implements Animation {
    /**
     * Constructor of the PauseScreen class.
     */
    public PauseScreen() {
    }

    /**
     * Implements the doOneFrame Animation interface.
     * @param d surface to draw the screen on.
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * should the animation stop.
     * @return this.stop.
     */
    public boolean shouldStop() {
        return false;
    }
}