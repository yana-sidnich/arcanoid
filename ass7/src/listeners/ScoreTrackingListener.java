// ID: 323537779
package listeners;
import game.Counter;
import game.Ball;
import game.Block;

/**
 * This class tracks the score in a game, each hit wil increase the score by 5.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * Constructor of the ScoreTrackingListener class.
     * @param scoreCounter a reference to a counter which indicates the score.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
       this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the Ball that's doing the hitting.
     * Increase the score by 5.
     * @param beingHit the block which the ball collided with
     * @param hitter the colliding ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}