// ID: 323537779

import java.util.ArrayList;
import java.util.List;

/**
 * The class represents the game enironment.
 * This class holds all the collidable objects that the game contains (kept in a list).
 */
public class GameEnvironment {

    private List<Collidable> collidables;

    /**
     * Constructor of the GameEnvironment class.
     */
    GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add the given collidable to the environment.
     * @param c Collidable to add.
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    /**
     * Finds the closest collision point of an object that moves from
     * trajecory.start() to trajectory.end().
     *
     * @param trajectory the line that we need to find the closest collision to its start.
     * @return info about the closest collision. null is returned if no collsion will occur.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        // If no collsion found null is returned.
        CollisionInfo infoToReturn = null;
        double minDistance = Double.POSITIVE_INFINITY;
        // Iterating through all the collidables
        for (Collidable collidable : collidables) {
            Point collisionPoint = trajectory.closestIntersectionToStartOfLine(collidable.getCollisionRectangle());
            // Check if a collision occurs.
            if (collisionPoint != null) {
                double distance = trajectory.start().distance(collisionPoint);
                // check if this collsion is the closest, if it is update the info.
                if (distance < minDistance) {
                    infoToReturn = new CollisionInfo(collisionPoint, collidable);
                    minDistance = distance;
                }
            }
        }

        return infoToReturn;
    }

 }