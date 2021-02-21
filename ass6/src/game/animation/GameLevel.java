// ID: 323537779
package game.animation;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import game.AnimationRunner;
import game.Ball;
import game.Block;
import game.Collidable;
import game.Counter;
import game.GameEnvironment;
import game.Paddle;
import game.ScoreIndicator;
import game.Sprite;
import game.SpriteCollection;
import game.level.LevelInformation;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;

import java.awt.Color;

/**
 * a class to represent a GameLevel.
 * a game contains a gui, a game environment and a collection of sprites.
 * the game can be initialized using the initialize function, and started by the run function.
 */
public class GameLevel implements Animation {
    // Constants to represent the Games sizes.
    public static final int GAME_GUI_WIDTH = 800;
    public static final int GAME_GUI_HEIGHT = 600;
    public static final int GAME_FPS = 60;

    // Constants to represent the Paddle sizes.
    static final int PADDLE_HEIGHT = 10;
    static final int PADDLE_SPEED = 10;

    // Constants to represent the Ball sizes.
    static final int BALL_RADIUS = 5;

    // Constants to represent the Side Blocks' sizes.
    public static final int SCREEN_BLOCK_THICKNESS = 10;

    // Constants to represent the Score Indicator's sizes.
    public static final int SCORE_INDICATOR_HEIGHT = 30;
    public static final int SCORE_INDICATOR_TEXT_SIZE = 20;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter blocksToRemove;
    private Counter remainingBalls;
    private BallRemover ballRemover;
    private BlockRemover blockRemover;
    private Counter gameScore;
    private ScoreTrackingListener scoreTracker;
    private AnimationRunner runner;
    private boolean running;
    private boolean win;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * Constructor of the game environment.
     * Initializes the SpriteCollection, GameEnvirinment and GUI needed by the game.
     *
     * @param levelInfo level info
     * @param ar animation runner
     * @param ks keyboard sensor
     * @param gameScore the current score
     */
    public GameLevel(LevelInformation levelInfo, AnimationRunner ar, KeyboardSensor ks, Counter gameScore) {

        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = ks;
        this.blocksToRemove = new Counter();
        this.remainingBalls = new Counter();
        this.ballRemover = new BallRemover(this, this.remainingBalls);
        this.blockRemover = new BlockRemover(this, this.blocksToRemove);
        this.gameScore = gameScore;
        this.scoreTracker = new ScoreTrackingListener(this.gameScore);
        this.running = false;
        this.win = false;
        this.runner = ar;
        this.levelInfo = levelInfo;
        // background shoud be the first sprite displayed.
        this.sprites.addSprite(this.levelInfo.getBackground());
    }

    /**
     * Add a collidable to the game.
     * @param c collidable to add.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Remove the given collidable from the game.
     * @param c Collidable to remove.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add a Sprite to the game.
     * @param s Sprite to add.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove a sprite from the game.
     * @param s A Sprite to remove from the game.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * This function initializes the GameLevel's Paddle.
     */
    private void initializePaddle() {
        // Calculates the upper left point of the paddle.
        double paddleUpperLeftX = (GAME_GUI_WIDTH - this.levelInfo.paddleWidth()) / 2;
        double paddleUpperLeftY = GAME_GUI_HEIGHT - SCREEN_BLOCK_THICKNESS - PADDLE_HEIGHT;
        Point paddleUpperLeft = new Point(paddleUpperLeftX, paddleUpperLeftY);

        // Create the paddles's shape (Rectangle).
        Rectangle paddleShape = new Rectangle(paddleUpperLeft, this.levelInfo.paddleWidth(), PADDLE_HEIGHT);
        double paddleLeftLimit = SCREEN_BLOCK_THICKNESS;
        double paddleRightLimit = GAME_GUI_WIDTH - SCREEN_BLOCK_THICKNESS;

        Paddle paddle = new Paddle(paddleShape, Color.ORANGE, keyboard,
                                   paddleLeftLimit, paddleRightLimit, PADDLE_SPEED);
        // Adding the paddle to the game.
        paddle.addToGame(this);
    }

    /**
     * This function initializes the GameLevel's Score Indicator.
     */
    private void initializeScoreIndicator() {
        Rectangle scoreindicatorRect = new Rectangle(new Point(0, 0),
                                                     GAME_GUI_WIDTH, SCORE_INDICATOR_HEIGHT);
        ScoreIndicator si = new ScoreIndicator(this.gameScore, this.levelInfo.levelName(),
                                               scoreindicatorRect, SCORE_INDICATOR_TEXT_SIZE);
        si.addToGame(this);
    }

    /**
     * This function initializes all the Balls in the game.
     * The game contains two balls.
     */
    private void initializeGameBalls() {
        // Calculate the Ball's location and Color.

        int ballsStartPointX = GAME_GUI_WIDTH / 2;
        int ballsStartPointY = GAME_GUI_HEIGHT - SCREEN_BLOCK_THICKNESS - PADDLE_HEIGHT - BALL_RADIUS;
        Color ballColor = Color.white;

        for (Velocity v : this.levelInfo.initialBallVelocities()) {
            Ball ball = new Ball(new Point(ballsStartPointX , ballsStartPointY), BALL_RADIUS, ballColor);
            ball.setVelocity(v);
            ball.setGameEnvironment(this.environment);
            ball.addToGame(this);
            this.remainingBalls.increase(1);
        }
    }

    /**
     * This function initializes all the screen Blocks.
     * This Blocks will stop the Balls from going out of game bounds.
     */
    private void initializeScreenBlocks() {
        // Calculate the side block height
        int sideBlockHeight = GAME_GUI_HEIGHT - SCREEN_BLOCK_THICKNESS;

        /* Adding all the screen blocks using the addBlockRow function,
        with a single block. */

        // Initialize the upperside block.
        Point upperBlockupperLeft = new Point(0, SCORE_INDICATOR_HEIGHT);
        addBlockRow(GAME_GUI_WIDTH, SCREEN_BLOCK_THICKNESS, upperBlockupperLeft, 1, Color.BLACK, null);

        /* Initialize the lowerside block,
        this block also recieves the BallRemover listener, as hitting it is forbidden. */
        HitListener[] killerBlockListeners = {this.ballRemover};
        Point lowerBlockupperLeft = new Point(0, GAME_GUI_HEIGHT);
        addBlockRow(GAME_GUI_WIDTH, SCREEN_BLOCK_THICKNESS, lowerBlockupperLeft, 1, Color.BLACK, killerBlockListeners);

        // Initialize the rightside block.
        Point rightBlockupperLeft = new Point(GAME_GUI_WIDTH - SCREEN_BLOCK_THICKNESS,
                                              SCREEN_BLOCK_THICKNESS + SCORE_INDICATOR_HEIGHT);
        addBlockRow(SCREEN_BLOCK_THICKNESS, sideBlockHeight, rightBlockupperLeft, 1, Color.BLACK, null);

        // Initialize the leftside block.
        Point leftBlockupperLeft = new Point(0, SCREEN_BLOCK_THICKNESS + SCORE_INDICATOR_HEIGHT);
        addBlockRow(SCREEN_BLOCK_THICKNESS, sideBlockHeight, leftBlockupperLeft, 1, Color.BLACK, null);
    }

    /**
     * This function initializes all the blocks inside the game.
     */
    private void initializeBlocks() {

        for (Block b : this.levelInfo.blocks()) {
            b.addHitListener(this.blockRemover);
            b.addHitListener(this.scoreTracker);
            b.addToGame(this);
        }

        blocksToRemove.increase(this.levelInfo.numberOfBlocksToRemove());
    }

    /**
     * Initialize a new game:
     * Reset the game envirinment and sprite collection.
     * Initialize:
     * 1. GameLevel blocks
     * 2. GameLevel Balls.
     * 3. GameLevel side blocks.
     * 4. GameLevel Paddle.
     */
    public void initialize() {

        // Initialize all the GameLevel components.
        this.initializeBlocks();
        this.initializeGameBalls();
        this.initializeScreenBlocks();
        this.initializePaddle();
        this.initializeScoreIndicator();
    }

    /**
     * Add a raw of blocks to the game.
     * @param width the width of a block.
     * @param height the height of a block.
     * @param startPoint the strat point of the block raw.
     * @param numBlocks number of blocks.
     * @param color the colorof the blocks to add.
     * @param hls hit listeners to add to the row.
     */
    private void addBlockRow(int width, int height, Point startPoint,
                             int numBlocks, Color color, HitListener[] hls) {

        for (int i = 0; i < numBlocks; ++i) {
            Point curUpperPoint = new Point(startPoint.getX() + i * width, startPoint.getY());
            Rectangle shape = new Rectangle(curUpperPoint, width, height);
            Block block = new Block(shape, color);
            if (hls != null) {
                for (HitListener hl : hls) {
                    block.addHitListener(hl);
                }
            }
            block.addToGame(this);
        }
    }


    /**
     * should the animation stop.
     * @return true if animation should stop
     */
    public boolean shouldStop() {
        return !this.running;
    }
    /**
     * did the level end in a winning state.
     * @return this.win
     */
    public boolean win() {
        return this.win;
    }

    /**
     * Iterate one time over all the GameLevel objects.
     * Draw all the needed objects on the given surface.
     * check for special cases like pressing p.
     * Notify all the sprites time passed.
     * @param surface drawing surface.
     */
    public void doOneFrame(DrawSurface surface) {

        // if the key 'p' is pressed, we use create a pause screen.
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard,
                                                           KeyboardSensor.SPACE_KEY,
                                                           new PauseScreen()));
        }

        // checking if level is cleared
        if (this.blocksToRemove.getValue() == 0) {
            gameScore.increase(100);
            this.running = false;
            this.win = true;
        }

        // checking if level is lost
        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
            this.win = false;
        }

        this.sprites.notifyAllTimePassed();


        // Call the SpritesCollection function to draw all the sprites.
        this.sprites.drawAllOn(surface);

        // Notify all the sprites to move.
    }

    /**
     * Initialize and run the animation.
     */
    public void run() {
        Animation goAnimation =  new CountdownAnimation(1, 3, this.sprites);
        this.runner.run(goAnimation);
        this.running = true;
        // use our runner to run the current animation -- which is one turn of
        // the game.
        this.runner.run(this);
   }
}