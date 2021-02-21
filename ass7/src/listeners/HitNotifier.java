// ID: 323537779
package listeners;

/**
 * An interface of a hitNotifier.
 * the interface has two functions, add and remove listener.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl HitListener to add.
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl HitListener to remove.
     */
    void removeHitListener(HitListener hl);
}