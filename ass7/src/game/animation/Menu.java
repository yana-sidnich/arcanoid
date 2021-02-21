// ID: 323537779

package game.animation;

/**
 * This class represents a menu of choices to choose from.
 * extends the Animation interface.
 * @param <T> type of the selection options.
 */
public interface Menu<T> extends Animation {
    /**
     * Add a selection option.
     * @param key Key to trigger selection.
     * @param message message about this key.
     * @param returnVal a value returned when the key is pressed.
     */
    void addSelection(String key, String message, T returnVal);
    /**
     * Get the value correlating with the last pressed key.
     * @return
     */
    T getStatus();
 
    
}