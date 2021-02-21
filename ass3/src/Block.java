// ID: 323537779

import java.awt.Color;
import biuoop.DrawSurface;

/**
 * A class to represent a Block, a block is a Rectangular game object, that implement both
   Sprite and Collidable object.
 * Sprite - as it can be drawn on the screen.
 * Collidable - as it can be collided with.
 * The Bloack has a rectangle and a clor as members.
 */
public class Block implements Collidable, Sprite {
    private Rectangle shape;
    private Color color;

    /**
     * Constructor of the Block class.
     * @param shape the Block's shape.
     * @param color the Block's color.
     */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    /**
     * Implementing the Collidable interface getCollisionRectangle function. Return
     * the "collision shape" of the object.
     * @return the Block's shape
     */
    public Rectangle getCollisionRectangle() {
        return this.shape;
    }

    /**
     * Getter of the Block's color.
     * @return the Block's color.
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * Setter of the Block's color.
     * @param newColor Color to assign to the block.
     */
    public void setColor(Color newColor) {
        this.color = newColor;
    }

    /**
     * Implementing the Collidable interface hit function.
     * Check for each side of the Rectangle if the collisionPoint was on it, and change
       the velocity accordingly.
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the colliding object.
     * @return The new velocity after te hit.
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {

        // Recieving the block's rectangle lines.
        Line[] lines = this.shape.getRectangleLines();
        double retXvelocity = currentVelocity.getX();
        double retYvelocity = currentVelocity.getY();

        /* For each line (side) of the rectangle, check if the collision is on it.
           If it is, check if the collision should affect the the velocity (for example
           a Ball moving from left to right and just touching the right side,
           should not affect the Ball's horizontal velocity). */

        // Check for horizontal sides (left + right).
        if (lines[Rectangle.SIDE_LEFT].isPointOnLine(collisionPoint)) {
            retXvelocity = retXvelocity < 0 ? retXvelocity : -retXvelocity;
        } else if (lines[Rectangle.SIDE_RIGHT].isPointOnLine(collisionPoint)) {
            retXvelocity = retXvelocity > 0 ? retXvelocity : -retXvelocity;
        }

        // Check for vertical sides (left + right).
        if (lines[Rectangle.SIDE_UPPER].isPointOnLine(collisionPoint)) {
            retYvelocity = retYvelocity < 0 ? retYvelocity : -retYvelocity;

        } else if (lines[Rectangle.SIDE_LOWER].isPointOnLine(collisionPoint)) {
            retYvelocity = retYvelocity > 0 ? retYvelocity : -retYvelocity;
        }

        // Create the final Velocity after the hit.
        Velocity returnVelocity = new Velocity(retXvelocity, retYvelocity);

        return returnVelocity;
    }

    /**
     * Implementing the Collidable/Sprite interface drawOn function.
     * @param surface Surface to draw the block on.
     */
    public void drawOn(DrawSurface surface) {
        this.shape.drawOn(surface, this.color);
    }

    /**
     * Implementing the Sprite interface timePassed function.
     * Currently does nothing.
     */
    public void timePassed() {
        // empty
    }

    /**
     * Implementing the Collidable/Sprite interface addToGame function
     * add the object as both a collidable and sprite.
     * @param game Game to add the block to.
     */
    public void addToGame(Game game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
}