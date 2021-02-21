// ID: 323537779

import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The class represents an animation with two frames.
 * The frames has known limits and colors of ((50,500),(50,500), gray) and ((450,600), (450,600), yellow).
 * The class recieves the balls radiuses from the command line,
 * and draw the balls with the given sizes in random places, and colors,
 * with each half of the balls in a different frame.
 * When an odd number of balls is given from the command line, the yellow frame will have one extra ball.
 */
class MultipleFramesBouncingBallsAnimation {

    // Consts to represent screen sizes.
    private static final int GUI_WIDTH = 800;
    private static final int GUI_LENGTH = 800;

    // Consts to represent a large ball size and speed.
    private static final double LARGE_BALL_SIZE = 50;
    private static final double LARGE_BALL_SPEED = 2;

    // Consts to represent frame 1 limits.
    private static final int FRAME1_STARTX = 50;
    private static final int FRAME1_ENDX = 500;
    private static final int FRAME1_STARTY = 50;
    private static final int FRAME1_ENDY = 500;

    // Consts to represent frame 2 limits.
    private static final int FRAME2_STARTX = 450;
    private static final int FRAME2_ENDX = 600;
    private static final int FRAME2_STARTY = 450;
    private static final int FRAME2_ENDY = 600;

    /**
     * This function creates a random Point where the x value and y value are limited by the paramters.
     * @param startX lower limit of x value of point.
     * @param endX upper limit of x value of point.
     * @param startY lower limit of y value of point.
     * @param endY upper limit of y value of point.
     * @return A random Point in given limits.
     */
    public static Point createRandomPoint(int startX, int endX, int startY, int endY) {
        Random rand = new Random();
        // Get a random center point within limits.
        int pointX = startX == endX ? startX : startX + rand.nextInt(endX - startX);
        int pointY = startY == endY ? startY : startY + rand.nextInt(endY - startY);
        return new Point(pointX, pointY);
    }

    /**
     * Create a random color.
     * This is done by randomizing the amount of red blue and green in the color.
     * @return a random Color.
     */
    public static Color createRandomColor() {
        Random rand = new Random();
        float red = rand.nextFloat();
        float green = rand.nextFloat();
        float blue = rand.nextFloat();
        return new Color(red, green, blue);
    }

    /**
     * Calculate the speed the ball needs according to its radius.
     * The smaller the baller the higher its speed.
     * Circles larger than LARGE_BALL_SIZE will have the same speed.
     * @param radius the Ball's radius.
     * @return the Ball's total speed.
     */
    public static double calculateBallSpeed(int radius) {
        double speed = radius > LARGE_BALL_SIZE
                       ? LARGE_BALL_SPEED
                       : LARGE_BALL_SPEED * LARGE_BALL_SIZE / radius;
        return speed;
    }

    /**
     * Create a Velocity instance, according to the Ball's radius.
     * the direction (angle in degrees) is randomized.
     * @param radius the Ball's radius.
     * @return the Ball's Velocity.
     */
    public static Velocity createRandomBallVelocity(int radius) {
        Random rand = new Random();
        // Creating a random angle.
        double angle = rand.nextDouble() * 360;
        double speed = calculateBallSpeed(radius);

        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * The function creates a new Ball instance with given parameters.
     * @param radius the Ball's radius.
     * @param startX the frame's starting x value.
     * @param endX the frame's ending x value.
     * @param startY the frame's starting y value.
     * @param endY the frame's end y value.
     * @return a random Ball with given radius within given frame limits.
     */
    public static Ball createRandomBallInframe(int radius, int startX, int endX, int startY, int endY) {

        /* Creating a random center Point (when the limits are the screen limit minus the balls radius),
           and using it to create the ball with the given radius and a random color and setting it
           with a random velocity. */
        Point center = createRandomPoint(startX + radius, endX - radius, startY + radius, endY - radius);
        Ball ballToReturn = new Ball(center, radius, createRandomColor());
        Velocity velocity = createRandomBallVelocity(radius);
        ballToReturn.setVelocity(velocity);

        return ballToReturn;
    }

    /**
     * The function draws and moves the given balls on given frame limits.
     * @param surface surface to draw on.
     * @param balls balls to draw and move.
     * @param startX the frame's starting x value.
     * @param endX the frame's ending x value.
     * @param startY the frame's starting y value.
     * @param endY the frame's end y value.
     */
    private static void drawBalls(DrawSurface surface, Ball[] balls, int startX, int endX, int startY, int endY) {

        // Iterating through the balls, drawing each one on the surface, and moving them using moveOneStep.
        for (int i = 0; i < balls.length; ++i) {
            balls[i].drawOn(surface);
            balls[i].moveOneStep(startX, endX, startY, endY);
        }
    }

    /**
     * The function recieves two ball arrays and draws them in their frames (Frame 1 and 2).
     * @param firstHalf First half of balls.
     * @param secondHalf Sirst half of balls.
     */
    private static void drawAnimation(Ball[] firstHalf, Ball[] secondHalf) {
        // Create the GUI window.
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", GUI_WIDTH, GUI_LENGTH);
        Sleeper sleeper = new Sleeper();

        // Main animation loop, runs until program is closed.
        while (true) {
            DrawSurface d = gui.getDrawSurface();

            // Drawing the first frame (gray frame).
            int frameHeight = FRAME1_ENDY - FRAME1_STARTY;
            int frameLength = FRAME1_ENDX - FRAME1_STARTX;
            d.setColor(Color.GRAY);
            d.fillRectangle(FRAME1_STARTX, FRAME1_STARTY, frameLength, frameHeight);
            // Draw frame 1 balls.
            drawBalls(d, firstHalf, FRAME1_STARTX, FRAME1_ENDX, FRAME1_STARTY, FRAME1_ENDY);

            // Drawing the first frame (yellow frame).
            frameHeight = FRAME2_ENDY - FRAME2_STARTY;
            frameLength = FRAME2_ENDX - FRAME2_STARTX;
            // Draws rectangle frame 2.
            d.setColor(Color.YELLOW);
            d.fillRectangle(FRAME2_STARTX, FRAME2_STARTY, FRAME2_ENDX - FRAME2_STARTX, FRAME2_ENDY - FRAME2_STARTY);
            // Draw frame 2 balls.
            drawBalls(d, secondHalf, FRAME2_STARTX, FRAME2_ENDX, FRAME2_STARTY, FRAME2_ENDY);

            gui.show(d);
            sleeper.sleepFor(50);  // Wait for 50 milliseconds.
        }
     }

     /**
      * The main function. Parses given args and create two arrays of Balls.
      * The sizes are determined from the command line arguments.
      * @param args string array expected to be ints. Represents the ball sizes.
      */
     public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Invalid Input: args length must at least 2");
            return;
        }
        // Creating both arrays, each will contain half of the balls.
        Ball[] firstHalf = new Ball[args.length / 2];
        Ball[] secondHalf = new Ball[args.length - args.length / 2];

        // Creating the first half of balls with the given radiuses from the command line.
        for (int i = 0; i < firstHalf.length; ++i) {
            int radius = Integer.parseInt(args[i]);
            firstHalf[i] = createRandomBallInframe(radius, FRAME1_STARTX, FRAME1_ENDX, FRAME1_STARTY, FRAME1_ENDY);
        }

        // Creating the second half of balls with the given radiuses from the command line.
        for (int i = 0; i < secondHalf.length; ++i) {
            int radius = Integer.parseInt(args[i + firstHalf.length]);
            secondHalf[i] = createRandomBallInframe(radius, FRAME2_STARTX, FRAME2_ENDX, FRAME2_STARTY, FRAME2_ENDY);
        }

        drawAnimation(firstHalf, secondHalf);
     }
}