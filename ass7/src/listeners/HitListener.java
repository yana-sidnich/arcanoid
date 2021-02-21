// ID: 323537779
package listeners;

import game.Block;
import game.Ball;

/**
 * an interface of an hit listener.
 * Once thereis a hit, the listerner is notified and acts accordingly.
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * @param beingHit the block which the ball collided with
     * @param hitter the colliding ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}