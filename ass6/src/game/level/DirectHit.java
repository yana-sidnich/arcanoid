//ID: 323537779

package game.level;

import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import game.Block;
import game.animation.GameLevel;
import game.Sprite;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;

import java.awt.Color;

/**
 * This class represents the first level, it implements level information.
 */
public class DirectHit implements LevelInformation {

    private List<Velocity> velocities;
    private List<Block>    levelBlocks;

    /**
     * Constructor of the DirectHit class.
     */
    public DirectHit() {
        initializeVelocities();
        initializeBlocks();
    }
    /**
     * Get the number of balls.
     * @return number of balls in the game.
     */
    public int numberOfBalls() {
        return this.velocities.size();
    }

    /**
     * Get the intial velocity of all the balls in the game.
     * @return initial speed of all the balls.
     */
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }

    /**
     * get the paddle's speed.
     * @return the paddle's speed.
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * get the paddle's width.
     * @return the paddle's width.
     */
    public int paddleWidth() {
        return 60;
    }

    /**
     * the level name will be displayed at the top of the screen.
     * @return level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * represents the background of this pecific level.
     */
    class BackGround implements Sprite {
        private Rectangle rect;
        private Color c;

        /**
         * Constructor of the background.
         */
        BackGround() {
            this.rect = new Rectangle(new Point(0, 0), GameLevel.GAME_GUI_WIDTH, GameLevel.GAME_GUI_HEIGHT);
            this.c = Color.darkGray;
        }

        /**
         * Draw the Sprite on the DrawSurface.
         * @param d DrawSurface to draw the Sprite on.
         */
        public void drawOn(DrawSurface d) {
            rect.drawOn(d, c);
        }

        /**
         * Notify the Sprite that time has passed.
         */
        public void timePassed() {
            //empty
        }

        /**
         * A function to allow a Sprite to add itself to the game.
         * In this case the "GameLevel" does not need to know the object,
         * and how to add it, only that it can be added.
         * @param game GameLevel to add the sprite to.
         */
        public void addToGame(GameLevel game) {
            game.addSprite(this);
        }
    }
    /**
     * Returns a sprite with the background of the level.
     * @return background of the level.
     */
    public Sprite getBackground() {
        return new BackGround();
    }

    /**
     * The Blocks that make up this level, each block contains.
     * its size, color and location.
     * @return the level's blocks
     */
    public List<Block> blocks() {
        return this.levelBlocks;
    }

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size()
     * @return Number of blocks that should be removed.
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }

    /**
     * initialize the level's blocks.
     */
    private void initializeBlocks() {
        this.levelBlocks = new ArrayList<Block>();
        Point upperLeft = new Point((GameLevel.GAME_GUI_WIDTH - 40) / 2 ,
                                    GameLevel.SCORE_INDICATOR_HEIGHT + GameLevel.SCREEN_BLOCK_THICKNESS + 100);
        Rectangle rect = new Rectangle(upperLeft, 40, 40);
        this.levelBlocks.add(new Block(rect, Color.red));
    }

    /**
     * initialize the level's velocities.
     */
    private void initializeVelocities() {
        this.velocities = new ArrayList<Velocity>();
        velocities.add(Velocity.fromAngleAndSpeed(0, 6));
   }
}