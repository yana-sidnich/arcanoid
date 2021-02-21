// ID: 323537779

package game.animation;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;
/**
 * Implementation of the Menu interface which interacts with the keyboard.
 * 
 * @param <T> type of the selection options.
 */
public class MenuAnimation<T> implements Menu<T> {
    
    private String name;
    private KeyboardSensor keyboard;
    private List<Selection<T>> selections;
    private Selection<T> chosenSelection;
    private boolean stop = false;

    /**
     * Constructor of the MenuAnimation class.
     * @param name menu name.
     * @param keyboard keyboard sensor.
     */
    public MenuAnimation(String name, KeyboardSensor keyboard) {
        this.name = name;
        this.keyboard = keyboard;
        this.selections = new ArrayList<Selection<T>>();
        this.chosenSelection = null;
        this.stop = false;
    }

    public void addSelection(String key, String message, T returnVal) {
        this.selections.add(new Selection<T>(key, message, returnVal));
    }

    /**
     * Get the value correlating with the last pressed key.
     * @return 
     */
    public T getStatus() {
        return this.chosenSelection.getVal();
    }

    /**
     * this function will draw the current state of the animation.
     * it will move on step over the animation after drawing it.
     * @param d DrawSurface to draw the animation on.
     */
    public void doOneFrame(DrawSurface d) {
        this.draw(d);
        for (Selection<T> s : selections) {
            if (this.keyboard.isPressed(s.getKey())) {
                this.stop = true;
                this.chosenSelection = s;
                break;
            }
        }
    }
    /**
     * Check if the game is done and the animation should stop.
     * @return true if the game shoud stop, else false.
     */
    public boolean shouldStop() {
        if (this.stop) {
            // you should stop only once, after you should stop, reset state.            
            this.stop = false;
            return true;
        } 

        return false;
    }

    private void draw(DrawSurface d) {
        String text = String.format("%s:\r\n", this.name);
        int lines = 1 + selections.size();
        int height = d.getHeight() / Math.max(8, lines);
        int curLine = 1;

        d.drawText(d.getWidth() / 4, height, text, height);

        for (Selection<T> s : selections) {
            String line = String.format("%s: %s\r\n", s.getKey(), s.getMessage());

            text += String.format("%s: %s\r\n", s.getKey(), s.getMessage());
            d.drawText(d.getWidth() / 4 , d.getHeight() / 8 + height * curLine, line, height);
            curLine++;
        }
    }
}