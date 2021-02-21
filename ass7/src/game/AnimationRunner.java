//ID: 323537779
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import game.animation.Animation;

/**
 * This class runs the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * Constructor of the AnimationRunner class.
     * @param gui GUI to show.
     * @param fps Frame per second.
     */
    public AnimationRunner(GUI gui, int fps) {
        this.gui = gui;
        this.framesPerSecond = fps;
        this.sleeper = new Sleeper();
    }
    /**
     * This function will repeatedly call the doOneFrame Animation's function.
     * This will happen framesPerSecond times per second, until shouldStop returns true.
     * @param animation Animation to run.
     */
    public void run(Animation animation) {
       int millisecondsPerFrame = 1000 / framesPerSecond;
       while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
       }
    }

}