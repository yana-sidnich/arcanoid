// ID: 323537779
package game.animation;

import biuoop.DrawSurface;

/**
 * a class that represent the animation, contains the main logix that runs the game.
 */
public interface Animation {
    /**
     * this function will draw the current state of the animation.
     * it will move on step over the animation after drawing it.
     * @param d DrawSurface to draw the animation on.
     */
    void doOneFrame(DrawSurface d);
    /**
     * Check if the game is done and the animation should stop.
     * @return true if the game shoud stop, else false.
     */
    boolean shouldStop();
}