// ID: 323537779

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * This class represents the the game Paddle.
 * The Paddle is the player in the game.
 * It is a rectangle that is controlled by the arrow keys, and moves according to the player key presses.
 * The Paddle implements the Sprite and the Collidable interfaces.
 * The paddle Moves left and right according to the users arrow key presses,
 * and according to the screen limits.
 *
 */
public class Paddle implements Sprite, Collidable {
    private KeyboardSensor keyboard;
    private Rectangle shape;
    private Color color;
    private double rightLimit;
    private double leftLimit;
    private double movementSpeed;

    /**
     * Constructor of the Paddle class.
     *
     * @param shape the shape of the Paddleparam shape
     * @param color the color of the Paddle
     * @param keyboard the sensor the Paddle will use to detect key presses.
     * @param leftLimit the leftest place the paddle can reach.
     * @param rightLimit the rightest place the paddle can reach.
     * @param movementSpeed how much the paddle move in each movement.
     */
    public Paddle(Rectangle shape, Color color, KeyboardSensor keyboard,
                  double leftLimit, double rightLimit, double movementSpeed) {
        this.shape = shape;
        this.color = color;
        this.keyboard = keyboard;
        this.rightLimit = rightLimit;
        this.leftLimit = leftLimit;
        this.movementSpeed = movementSpeed;
    }

    /**
     * Moves the Paddle to the left according to its current place.
     * This function takes into consideration the screen limits.
     */
    public void moveLeft() {
        double newUpperLeftX = this.shape.getUpperLeft().getX() - movementSpeed;
        // if new place is out of screen limits, move the paddle just up to the limit.
        if (newUpperLeftX < leftLimit) {
            newUpperLeftX = leftLimit;
        }

        Point newUpperLeft = new Point(newUpperLeftX, this.shape.getUpperLeft().getY());
        this.shape.setUpperleft(newUpperLeft);
    }

    /**
     * Moves the Paddle to the right according to its current place.
     * This function takes into consideration the screen limits.
     */
    public void moveRight() {
        double newUpperLeftX = this.shape.getUpperLeft().getX() + movementSpeed;
        // if new place is out of screen limits, move the paddle just up to the limit.
        if (newUpperLeftX + this.shape.getWidth() > rightLimit) {
            newUpperLeftX = rightLimit - this.shape.getWidth();
        }

        Point newUpperLeft = new Point(newUpperLeftX, this.shape.getUpperLeft().getY());
        this.shape.setUpperleft(newUpperLeft);
    }

    /**
     * Implementing the Sprite interface timePassed function.
     * If the LEFT key is pressed move left.
     * If the RIGHT key is pressed move right.
     */
    public void timePassed() {
        // If both keys are pressed, we do not move.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY) && keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            return;
        }

        // Check if any key is pressed at all and move accordingly.
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }

        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draw the Paddle on the given draw surface.
     * @param d DrawSurface to to draw the Paddle on.
     */
    public void drawOn(DrawSurface d) {
        this.shape.drawOn(d, this.color);
    }

    /**
     * Implements the Collidable interface of getCollisionRectangle.
     * @return the Paddle shape.
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Implementing the Collidable interface hit function.
     * The paddle is divided to 5 parts.
     * We are checking which part of the Paddle there was a hit, and act accordingly.
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the colliding object.
     * @return The new velocity after te hit.
     *
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        double distanceFromStartOnX = collisionPoint.getX() - this.shape.getUpperLeft().getX();
        // between 0 and 1.
        double ratioFromStart = distanceFromStartOnX / this.shape.getWidth();

        // Finding which part we are hitting (by multiplying by 5, and rounding down).
        int part = (int) (ratioFromStart * 5);
        Velocity newVelocity = null;
        switch (part) {
            case 0: // first fifth of the Paddle (left) - return in 300 degrees angle.
                newVelocity = Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
                break;
            case 1: // second fifth of the Paddle (middle-left) - return in 330 degrees angle.
                newVelocity = Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
                break;
            case 2: // third fifth of the Paddle (middle) - flip the y axis speed.
                newVelocity = new Velocity(currentVelocity.getX(), -currentVelocity.getY());
                break;
            case 3: // fourth fifth of the Paddle (middle-right) - return in 30 degrees angle.
                newVelocity = Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
                break;
            case 4: // fifth fifth of the Paddle (right) - return in 60 degrees angle.
            case 5: // if exactly on end point. Return in 60 degrees angle.
                newVelocity = Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
                break;

            default:
                // we should not reach here
                throw new RuntimeException("hit point is not on the rectangle");
        }

        return newVelocity;
    }

    /**
     * Implementing the Collidable/Sprite interface addToGame function.
     * add the object as both a collidable and sprite.
     * @param game Game to add the block to.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}