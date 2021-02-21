// ID: 323537779
package listeners;

import game.Block;
import game.Ball;
import game.Counter;
import game.Game;
/**
 * a BallRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private Game game;
    private Counter remainingBalls;

    /**
     * Constructor of the BallRemover class.
     * @param game the game to remove blocks from
     * @param remainingBalls a refernece to the counting of the balls.
     */
    public BallRemover(Game game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Blocks that are hit are removed from the game.
     * @param beingHit the block ehich the ball collided with.
     * @param hitter the hitting ball.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        remainingBalls.decrease(1);
    }
}