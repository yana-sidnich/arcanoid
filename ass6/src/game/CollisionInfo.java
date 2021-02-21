// ID: 323537779
package game;

import geometry.Point;

/**
 * a class to Represent the information needed at a collision.
 * Has the collision point and the collidable object.
 */
public class CollisionInfo {
    private Point point;
    private Collidable object;
    /**
     * Constructor of the CollisionInfo class.
     * @param point the collsion point.
     * @param collidable the colldiable object.
     */
    public CollisionInfo(Point point, Collidable collidable) {
        this.point = point;
        this.object = collidable;
    }

    /**
     * Getter of the collsion point.
     * @return the collision point.
     */
    public Point collisionPoint() {
        return point;
    }

    /**
     * Getter of the collidable object.
     * @return the collidable object.
     */
    public Collidable collisionObject() {
        return object;
    }
}