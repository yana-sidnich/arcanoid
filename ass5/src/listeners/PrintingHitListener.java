// ID: 323537779
package listeners;

import game.Ball;
import game.Block;
/**
 * this class implements the hit listener, and prints each time there is a hit.
 */
public class PrintingHitListener implements HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * Prints to screen on a hit.
     * @param beingHit the block which the ball collided with
     * @param hitter the colliding ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
       System.out.println("A Block was hit.");
    }
}