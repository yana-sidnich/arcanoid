// ID: 323537779
package game;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

import biuoop.DrawSurface;
import game.animation.GameLevel;
import geometry.Line;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import listeners.HitListener;
import listeners.HitNotifier;

/**
 * A class to represent a Block, a block is a Rectangular game object, that implement both
   Sprite and Collidable object.
 * Sprite - as it can be drawn on the screen.
 * Collidable - as it can be collided with.
 * The Block has a rectangle and a color as members.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle shape;
    private Color color;
    private List<HitListener> hitListeners;

    /**
     * Constructor of the Block class.
     * @param shape the Block's shape.
     * @param color the Block's color.
     */
    public Block(Rectangle shape, Color color) {
        this.shape = shape;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
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
       Notifies all the listeners about the occuring hit.
     * @param collisionPoint  the collision point
     * @param currentVelocity the current velocity of the colliding object.
     * @param hitter The hitting ball.
     * @return The new velocity after te hit.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {

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

        // notify all the listeners about the occuring hit before returning.
        this.notifyHit(hitter);
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
     * Implementing the Sprite interface `ssed function.
     * Currently does nothing.
     */
    public void timePassed() {
        // empty
    }

    /**
     * Implementing the Collidable/Sprite interface addToGame function
     * add the object as both a collidable and sprite.
     * @param game GameLevel to add the block to.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }

    /**
     * Remove the block from the game.
     * @param game the GameLevel to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }


    /**
     * Add hl as a listener to hit events.
     * @param hl HitListener to add.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl HitListener to remove.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


    /**
     * Notify all listeners about a hit event.
     * @param hitter the ball colliding with the block
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}