// ID: 323537779
package game.animation;

/**
 * This class represent a single selection
 * @param <T> type of the selection value;
 */
public class Selection<T> {
    private String key;
    private String message;
    private T val;
    /**
     * Constructor of the selection class.
     * @param key key
     * @param message message
     * @param returnVal value
     */
    Selection(String key, String message, T returnVal) {
        this.key = key;
        this.message = message;
        this.val = returnVal;
    }
    /**
     * Getter of the key;
     * @return key
     */
    String getKey() {
        return this.key;
    }
    /**
     * Getter of the message;
     * @return message
     */
    String getMessage() {
        return this.message;
    }
    /**
     * Getter of the value;
     * @return val
     */
    T getVal() {
        return this.val;
    }
}
