// ID: 323537779
package game;

import geometry.Line;
import geometry.Point;
import geometry.Velocity;

import biuoop.DrawSurface;
import game.animation.GameLevel;

import java.awt.Color;

/**
 * The class represents a drawable ball (or circle).
 * A ball is represented by a center Point, radius, its Velocity, and its color.
 */
public class Ball implements Sprite {

    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;

    /**
     * Constructor of the Ball class.
     * A Ball is constructed with a default velocity of (0, 0), and an empty GameEnvironemt.
     * @param center the Ball's center Point.
     * @param radius the Ball's radius.
     * @param color the Ball's color.
     */
    public Ball(Point center, int radius, java.awt.Color color) {
        this.center = center;
        this.radius = radius;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }

    /**
     * Constructor of the Ball class.
     * A Ball is constructed with a default velocity of (0, 0), and an empty GameEnvironemt.
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
        this.gameEnvironment = new GameEnvironment();
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
     * Getter of the center Point.
     * @return center Point.
     */
    public Point getCenter() {
        return this.center;
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
     * Setter of the game environment of the Ball.
     * @param newGameEnvironment the ball's new GameEnvironment to set.
     */
    public void setGameEnvironment(GameEnvironment newGameEnvironment) {
        this.gameEnvironment = newGameEnvironment;
    }

    /**
     * Draws the ball on the given surface.
     * This is done by the fillCircle function of the DrawSurface class.
     * @param surface Surface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), this.getSize());
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Moving one step and check if any collision should occur.
     * If there is a collision, move change velocity accordigly.
     */
    public void moveOneStep() {

        // compute the ball trajectory
        Point endPoint = this.velocity.applyToPoint(this.center);
        Line trajectory = new Line(this.center, endPoint);
        // Check (using the game environment) if moving on this trajectory will hit anything.
        CollisionInfo info = this.gameEnvironment.getClosestCollision(trajectory);
        /* in the case there is no collision, move the ball to the end point,
        If there is a collision, move the ball to almost the hit point,
        and change the velocity according to thehit function of the collidable
        the ball collisded with. */
        if (info == null) {
            this.center = endPoint;
        } else {
            Line l = new Line(center, info.collisionPoint());
            this.center = l.middle();
            this.velocity = info.collisionObject().hit(this, info.collisionPoint(), this.velocity);
        }
    }

    /**
     * Implementing the Sprite interface timePassed function.
     * Moves the ball one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Implementing the Sprite interface addToGame function
     * add the object as a sprite.
     * @param game GameLevel to add the block to.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }

    /**
     * Remove the ball from the game.
     * @param game the GameLevel to remove the block from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}