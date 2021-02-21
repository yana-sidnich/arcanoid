// ID: 323537779

import biuoop.GUI;
import biuoop.Sleeper;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The class represents an animation of multiple bouncing Balls.
 * The class recieves the balls radiuses from the command line,
 * and draws the balls with the given sizes in random places, and colors.
 * The class has no members.
 */
public class MultipleBouncingBallsAnimation {
    // Consts to represent a large ball's size and a large ball's speed.
    private static final double LARGE_BALL_SIZE = 50;
    private static final double LARGE_BALL_SPEED = 2;
    // Consts to represent screen sizes.
    private static final int GUI_WIDTH = 800;
    private static final int GUI_LENGTH = 800;

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
     * Calculate the speed of the ball according to its radius.
     * The smaller the ball the higher its speed.
     * Circles larger than LARGE_BALL_SIZE will have the same speed (LARGE_BALL_SPEED).
     * @param radius The Ball's radius.
     * @return The Ball's total speed.
     */
    public static double calculateBallSpeed(int radius) {
        double speed = radius > LARGE_BALL_SIZE
                       ? LARGE_BALL_SPEED
                       : LARGE_BALL_SPEED * LARGE_BALL_SIZE / radius;
        return speed;
    }

    /**
     * Create a Velocity instance, according to the Ball's radius.
     * The direction (angle in degrees) is randomized.
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
     * This function creates a random ball in the given frame.
     * The whole ball will be inside the frame.
     * The Ball's color, direction and center point are randomized.
     * @param radius The Ball's radius.
     * @param width The frame Width.
     * @param length The frame Length.
     * @return A new Ball instance inside the frame.
     */
    public static Ball createRandomBallInframe(int radius, int width, int length) {

        /* Creating a random center Point (when the limits are the screen limit minus the balls radius),
           and using it to create the ball with the given radius and a random color and setting it
           with a random velocity. */
        Point center = createRandomPoint(radius, width - radius, radius, length - radius);
        Ball ballToReturn = new Ball(center, radius, createRandomColor());
        Velocity velocity = createRandomBallVelocity(radius);
        ballToReturn.setVelocity(velocity);

        return ballToReturn;
    }

    /**
     * This function creates an array of Balls according to the given String argument.
     * @param args Represents The Balls' radiuses.
     * @return array with an args.length size of random balls according to the given radiuses.
     */
    public static Ball[] prepareAnimation(String[] args) {
        // Creating the ball array.
        Ball[] balls = new Ball[args.length];

        // Iterating through all the given arguments.
        for (int i = 0; i < args.length; ++i) {
            // Creating the random ball according to the argument.
            int radius = Integer.parseInt(args[i]);
            balls[i] = createRandomBallInframe(radius, GUI_WIDTH, GUI_LENGTH);
        }

        return balls;
    }

    /**
     * This function creates the draw surface and draws the given balls on it.
     * @param balls an array of Balls to draw.
     */
    private static void drawAnimation(Ball[] balls) {

        // Create the GUI window.
        GUI gui = new GUI("MultipleBouncingBallsAnimation", GUI_WIDTH, GUI_LENGTH);
        Sleeper sleeper = new Sleeper();
        // Main animation loop, runs until program is closed.
        while (true) {
            DrawSurface surface = gui.getDrawSurface();
            // Iterating through all the given Balls.
            for (int i = 0; i < balls.length; ++i) {
                // Drawing each ball and moving it using the moveOneStep function.
                balls[i].drawOn(surface);
                balls[i].moveOneStep(0, GUI_WIDTH, 0, GUI_LENGTH);
            }
            gui.show(surface);
            sleeper.sleepFor(50);  // Wait for 50 milliseconds.
        }
    }

    /**
     * The Main function.
     * It prepares and runs the animation with the ball sizes recived from command line.
     * @param args string array expected to be ints. Represents the ball sizes.
     */
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Invalid Input: args length must at least 1");
            return;
        }

        Ball[] balls = prepareAnimation(args);

        drawAnimation(balls);
    }
}