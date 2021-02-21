// ID: 323537779
package game.animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import game.SpriteCollection;
/**
 * The CountdownAnimation will display the given gameScreen,
 * for numOfSeconds seconds, and on top of them it will show
 * a countdown from countFrom back to 1, where each number will
 * appear on the screen for (numOfSeconds / countFrom) seconds, before
 * it is replaced with the next one.
 */
public class CountdownAnimation implements Animation {
    private Sleeper sleeper;
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private boolean stop;
    private boolean firstTime;
    /**
     * Constructor of the CountdownAnimation.
     * @param numOfSeconds how much to wait between numbers
     * @param countFrom starting count value.
     * @param gameScreen screen to draw.
     */
    public CountdownAnimation(double numOfSeconds,
                              int countFrom,
                              SpriteCollection gameScreen) {
        this.sleeper = new Sleeper();
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.stop = false;
        this.firstTime = true;
    }
    /**
     * do one frame of the countdown, draw the screen + countdown on it.
     * @param d DrawSurface to draw on
     */
    public void doOneFrame(DrawSurface d) {
        // draw the animation
        this.gameScreen.drawAllOn(d);
        this.drawCount(d);

        // stop when count is sub zero
        if (this.countFrom < 0) {
            this.stop = true;
        }
        // when just intialized do not sleep, not delaying the animation.
        if (this.firstTime) {
            this.firstTime = false;
        } else {
            this.sleeper.sleepFor((long) (numOfSeconds * 1000));
        }
        this.countFrom--;
    }

    /**
     * should the animation stop.
     * @return this.stop
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * draws the text itself according to count.
     * if bigger than 0 then the number else 'GO!'' is written.
     * @param d DrawSurface to draw on
     */
    private void drawCount(DrawSurface d) {
        String s = Integer.toString(this.countFrom);
        // in case we finish count print "Go!"
        if (this.countFrom <= 0) {
            s = "Go!";
        }
        //calculate text place.
        int height = d.getHeight();
        int width = d.getWidth();
        int moveby = Math.min(width, height) / 16;
        int xStart = width / 2 - moveby;
        int yStart =  height / 2 + moveby;
        d.drawText(xStart, yStart, s, 4 * moveby);
    }
}