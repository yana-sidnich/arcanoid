// ID: 323537779

/**
 * An Interface to represent a collidable object.
 * A collidable is an object that can be collided with, and change
 * the velocity of the colliding object (a Ball in our case) accordingly.
 */
public interface Collidable {

    /**
     * Return the "collision shape" of the object.
     * @return "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param collisionPoint The Point the collision occured at.
     * @param currentVelocity The colliding object velocity.
     * @return Velocity after collision.
     */
    Velocity hit(Point collisionPoint, Velocity currentVelocity);

    /**
     * A function to allow a collidable to add itself to the game.
     * In this case the "Game" does not need to know the object,
     * and how to add it, only that it can be added.
     * @param game Game to add the collidable to.
     */
    void addToGame(Game game);
}