// ID: 323537779

import biuoop.DrawSurface;
import java.awt.Color;

/**
 * The class represents a drawable ball (or circle).
 * A ball is represented by a center Point, radius, its Velocity, and its color.
 */
class Ball {

    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;

    /**
     * Constructor of the Ball class.
     * A Ball is constructed with a default velocity of (0,0).
     * @param center the Ball's center Point.
     * @param radius the Ball's radius.
     * @param color the Ball's color.
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Constructor of the Ball class.
     * A Ball is constructed with a default velocity of (0,0).
     *
     * @param centerX Represents the x value of the center point.
     * @param centerY Represents the y value of the center point.
     * @param radius Represents the Ball's radius.
     * @param color Represents the Ball's color.
     */
    public Ball(double centerX, double centerY, int radius, java.awt.Color color) {
        this.center = new Point(centerX, centerY);
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
    }

    /**
     * Getter of the center Point x value.
     * @return center Point x value.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * Getter of the center Point y value.
     * @return center Point y value.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * Getter of the size/radius of the Ball.
     * @return radius of the Ball.
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Getter of the color of the Ball.
     * @return color of the Ball.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Setter of the velocity of the Ball.
     * @param newVelocity the ball's new velocity to set.
     */
    public void setVelocity(Velocity newVelocity) {
        this.velocity = newVelocity;
    }

    /**
     * Setter of the velocity of the Ball.
     * @param dx difference in x value.
     * @param dy difference in y value.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Getter of the velocity of the Ball.
     * @return velocity of the Ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Draws the ball on the given surface.
     * This is done by the fillCircle function of the DrawSurface class.
     * @param surface Surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Moving one step without consideration of the screen's width and length.
     * This is done by using the applyToPoint function of the velocity class.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * Moving one step with consideration of the screen limits.
     * This is done by using the applyToPoint function of the velocity class.
     * If screen bound are reached, the Velocity and center Point are changed accordingly.
     *
     * @param frameStartX start of frame on x axis.
     * @param frameEndX end of frame on x axis.
     * @param frameStartY start of frame on y axis.
     * @param frameEndY end of frame on y axis.
     */
    public void moveOneStep(int frameStartX, int frameEndX, int frameStartY, int frameEndY) {

        // Apply the Velocity changes to the center of the Ball.
        this.center = this.getVelocity().applyToPoint(this.center);

        // Extracting the new x,y values of the center point.
        double finalX = this.center.getX();
        double finalY = this.center.getY();

        /* This conditions check if the ball is out of frame bounds.
           we add or subtract the radius from the new center point in order to find
           the biggest and smallest x and y values on the circumference (center.[x|y] +- radius)
           and check them against the the matching frame bounds.
           If it is, the velocity in the related axis changes direction,
           and the center of the ball is changed to be inside the screen limits.*/

        // Checking if the Ball is out of the left or right frame bounds.
        if (finalX - this.radius < frameStartX) {
            // Finding and applying the new location of the center.
            this.center.setX(frameStartX + this.radius);
            // Reversing the x axis velocity.
            this.velocity = new Velocity(-this.velocity.getX(), this.velocity.getY());
        } else if (finalX + this.radius > frameEndX) {
            // Finding and applying the new location of the center.
            this.center.setX(frameEndX - this.radius);
            // Reversing the x axis velocity.
            this.velocity = new Velocity(-this.velocity.getX(), this.velocity.getY());
        }

        // Checking if the Ball is out of the upper or lower frame bounds.
        if (finalY - this.radius < frameStartY) {
            // Finding and applying the new location of the center.
            this.center.setY(frameStartY + this.radius);
            // Reversing the y axis velocity.
            this.velocity = new Velocity((this.velocity.getX()), -this.velocity.getY());
        } else if (finalY + this.radius > frameEndY) {
            // Finding and applying the new location of the center.
            this.center.setY(frameEndY - this.radius);
            // Reversing the y axis velocity.
            this.velocity = new Velocity(this.velocity.getX(), -this.velocity.getY());
        }
    }
}