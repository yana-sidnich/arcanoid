// ID: 323537779

package game.level;

import java.util.List;

import game.Block;
import game.Sprite;
import geometry.Velocity;


/**
 * This interface represent all the information about a level in the game.
 */
public interface LevelInformation {

    /**
     * Get the number of balls.
     * @return number of balls in the game.
     */
    int numberOfBalls();

    /**
     * Get the intial velocity of all the balls in the game.
     * @return initial speed of all the balls.
     */
    List<Velocity> initialBallVelocities();

    /**
     * get the paddle's speed.
     * @return the paddle's speed.
     */
    int paddleSpeed();

    /**
     * get the paddle's width.
     * @return the paddle's width.
     */
    int paddleWidth();

    /**
     * the level name will be displayed at the top of the screen.
     * @return level name.
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     * @return background of the level.
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return the level's blocks
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size()
     * @return Number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}