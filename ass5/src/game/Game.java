// ID: 323537779
package game;

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.HitListener;
import listeners.ScoreTrackingListener;

import java.awt.Color;

/**
 * a class to represent a Game.
 * a game contains a gui, a game environment and a collection of sprites.
 * the game can be initialized using the initialize function, and started by the run function.
 */
public class Game {
    // Constants to represent the Games sizes.
    static final int GAME_GUI_WIDTH = 800;
    static final int GAME_GUI_HEIGHT = 600;
    static final int GAME_FPS = 60;

    // Constants to represent a Block's sizes.
    static final int NORMAL_BLOCK_WIDTH = 50;
    static final int NORMAL_BLOCK_HEIGHT = 20;

    // Constants to represent the Paddle sizes.
    static final int PADDLE_WIDTH = 100;
    static final int PADDLE_HEIGHT = 10;
    static final int PADDLE_SPEED = 10;

    // Constants to represent the Ball sizes.
    static final int BALL_SPEED = 5;
    static final int BALL_RADIUS = 5;

    // Constants to represent the Side Blocks' sizes.
    static final int SCREEN_BLOCK_THICKNESS = 10;

    // Constants to represent the Score Indicator's sizes.
    static final int SCORE_INDICATOR_HEIGHT = 30;
    static final int SCORE_INDICATOR_TEXT_SIZE = 30;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private BallRemover ballRemover;
    private BlockRemover blockRemover;
    private Counter gameScore;
    private ScoreTrackingListener scoreTracker;

    /**
     * Constructor of the game environment.
     * Initializes the SpriteCollection, GameEnvirinment and GUI needed by the game.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.gui = new GUI("Arkanoid Game", GAME_GUI_WIDTH, GAME_GUI_HEIGHT);
        this.remainingBlocks = new Counter();
        this.remainingBalls = new Counter();
        this.ballRemover = new BallRemover(this, this.remainingBalls);
        this.blockRemover = new BlockRemover(this, this.remainingBlocks);
        this.gameScore = new Counter();
        this.scoreTracker = new ScoreTrackingListener(this.gameScore);
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
     * This function initializes the Game's Paddle.
     */
    private void initializePaddle() {
        // Calculates the upper left point of the paddle.
        double paddleUpperLeftX = (GAME_GUI_WIDTH - PADDLE_WIDTH) / 2;
        double paddleUpperLeftY = GAME_GUI_HEIGHT - SCREEN_BLOCK_THICKNESS - PADDLE_HEIGHT;
        Point paddleUpperLeft = new Point(paddleUpperLeftX, paddleUpperLeftY);

        // Create the paddles's shape (Rectangle).
        Rectangle paddleShape = new Rectangle(paddleUpperLeft, PADDLE_WIDTH, PADDLE_HEIGHT);
        double paddleLeftLimit = SCREEN_BLOCK_THICKNESS;
        double paddleRightLimit = GAME_GUI_WIDTH - SCREEN_BLOCK_THICKNESS;

        Paddle paddle = new Paddle(paddleShape, Color.ORANGE, this.gui.getKeyboardSensor(),
                                   paddleLeftLimit, paddleRightLimit, PADDLE_SPEED);
        // Adding the paddle to the game.
        paddle.addToGame(this);
    }

    /**
     * This function initializes the Game's Score Indicator.
     */
    private void initializeScoreIndicator() {
        Rectangle scoreindicatorRect = new Rectangle(new Point(0, 0),
                                                     GAME_GUI_WIDTH, SCORE_INDICATOR_HEIGHT);
        ScoreIndicator si = new ScoreIndicator(this.gameScore,
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
        Color ballColor = Color.cyan;

        // Create ball1 and set its speed, then add it to the game.
        Ball ball1 = new Ball(new Point(ballsStartPointX , ballsStartPointY), BALL_RADIUS, ballColor);
        Velocity v1 = Velocity.fromAngleAndSpeed(300, BALL_SPEED);
        ball1.setVelocity(v1);
        ball1.setGameEnvironment(this.environment);
        ball1.addToGame(this);

        // Create ball2 and set its speed, then add it to the game.
        Ball ball2 = new Ball(new Point(ballsStartPointX , ballsStartPointY), BALL_RADIUS, ballColor);
        Velocity v2 = Velocity.fromAngleAndSpeed(0, BALL_SPEED);
        ball2.setVelocity(v2);
        ball2.setGameEnvironment(this.environment);
        ball2.addToGame(this);

        // Create ball3 and set its speed, then add it to the game.
        Ball ball3 = new Ball(new Point(ballsStartPointX , ballsStartPointY), BALL_RADIUS, ballColor);
        Velocity v3 = Velocity.fromAngleAndSpeed(60, BALL_SPEED);
        ball3.setVelocity(v3);
        ball3.setGameEnvironment(this.environment);
        ball3.addToGame(this);

        // add the three balls to the remainng balls in the game.
        this.remainingBalls.increase(3);
    }

    /**
     * This function initializes all the screen Blocks.
     * This Blocks will stop the Balls from going out of game bounds.
     */
    private void initializeScreenBlocks() {
        // Calculate the side block height
        int sideBlockHeight = GAME_GUI_HEIGHT - 2 * SCREEN_BLOCK_THICKNESS;

        /* Adding all the screen blocks using the addBlockRow function,
        with a single block. */

        // Initialize the upperside block.
        Point upperBlockupperLeft = new Point(0, SCORE_INDICATOR_HEIGHT);
        addBlockRow(GAME_GUI_WIDTH, SCREEN_BLOCK_THICKNESS, upperBlockupperLeft, 1, Color.BLACK, null);

        /* Initialize the lowerside block,
        this block also recieves the BallRemover listener, as hitting it is forbidden. */
        HitListener[] killerBlockListeners = {this.ballRemover};
        Point lowerBlockupperLeft = new Point(0, GAME_GUI_HEIGHT - SCREEN_BLOCK_THICKNESS);
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

        // An array of the colors to initialize the block Raws at.
        Color[] lineColors = {Color.magenta, Color.red, Color.yellow, Color.blue, Color.pink, Color.green};

        int numBlocksInLine = 1;
        int startPointX = GAME_GUI_WIDTH - SCREEN_BLOCK_THICKNESS - numBlocksInLine * NORMAL_BLOCK_WIDTH;
        int startPointY = 100;
        HitListener[] listeners = {this.blockRemover, this.scoreTracker};
        /* Adding the block rows to the game.
           The upper row of the blocks contains 11 blocks.
           each of the next rows contains one less block that the row above it.*/
        for (int i = 0; i < lineColors.length && numBlocksInLine > 0; ++i) {
            Point rowStart = new Point(startPointX , startPointY);
            /* adding a block row containing numBlocksInLine starting from rowStart.
                this blocks also has a blockremover as a listener.
                after adding the blocks to the game we add them to the counter.
            */
            addBlockRow(NORMAL_BLOCK_WIDTH, NORMAL_BLOCK_HEIGHT, rowStart,
                        numBlocksInLine, lineColors[i], listeners);
            remainingBlocks.increase(numBlocksInLine);
            // moving the rowStart one block down and one block left.
            startPointY += NORMAL_BLOCK_HEIGHT;
            startPointX += NORMAL_BLOCK_WIDTH;
            numBlocksInLine -= 1;
        }
    }

    /**
     * Initialize a new game:
     * Reset the game envirinment and sprite collection.
     * Initialize:
     * 1. Game blocks
     * 2. Game Balls.
     * 3. Game side blocks.
     * 4. Game Paddle.
     */
    public void initialize() {

        // Restart the game environment and sprite collection.
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();

        // Initialize all the Game components.
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
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / GAME_FPS;
        while (this.remainingBlocks.getValue() > 0 && this.remainingBalls.getValue() > 0) {
            this.iterate(sleeper, millisecondsPerFrame);
        }

        if (this.remainingBlocks.getValue() == 0) {
            this.gameScore.increase(100);
        }
        this.iterate(sleeper, 2000);
        gui.close();
    }

    /**
     * One iteration of the main logic.
     * Draw all the current sprites, and notify all time passed.
     * When done sleep for sleepTime using sleeper.
     * @param sleeper Sleeper to sleep.
     * @param sleepTime how long to sleep in this iteration.
     */
    void iterate(Sleeper sleeper, long sleepTime) {

        long startTime = System.currentTimeMillis();
        DrawSurface surface = gui.getDrawSurface();

        // Draw the background.
        Rectangle backGround = new Rectangle(new Point(0, 0), GAME_GUI_WIDTH, GAME_GUI_HEIGHT);
        backGround.drawOn(surface, Color.darkGray);

        // Call the SpritesCollection function to draw all the sprites.
        this.sprites.drawAllOn(surface);

        gui.show(surface);
        // Notify all the sprites to move.
        this.sprites.notifyAllTimePassed();

        // Timing the animation.
        long usedTime = System.currentTimeMillis() - startTime;
        long milliSecondLeftToSleep = sleepTime - usedTime;
        if (milliSecondLeftToSleep > 0) {
            sleeper.sleepFor(milliSecondLeftToSleep);
        }

    }
}