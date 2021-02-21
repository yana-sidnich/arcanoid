// ID: 323537779
package game.animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * this class represents a wrapper to animation, in order to allow displaying it until a key is pressed.
 */
public class KeyPressStoppableAnimation implements Animation {

    private Animation animation;
    private String key;
    private KeyboardSensor keyboard;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Constructor of the KeyPressStoppableAnimation class.
     * @param sensor keyborad sensor
     * @param key key to wait until pressed to continue.
     * @param animation animation to draw.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.keyboard = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Implements the doOneFrame Animation interface.
     * @param d surface to draw the screen on.
     */
    public void doOneFrame(DrawSurface d) {
        // do inner animation frame.
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * should animation stop.
     * @return this.shouldStop
     */
    public boolean shouldStop() {
        return this.stop;
    }
}