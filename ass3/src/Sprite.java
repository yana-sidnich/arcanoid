// ID: 323537779

import biuoop.DrawSurface;

/**
 * An interface to represent a sprite.
 * In computer graphics and games, a Sprite is a game object that can be drawn
 * to the screen (and which is not just a background image).
 * Sprites can be drawn on the screen, and can be notified that time has passed
 * (so that they know to change their position / shape / appearance / etc).
 */
public interface Sprite {
    /**
    * Draw the Sprite on the DrawSurface.
    * @param d DrawSurface to draw the Sprite on.
    */
    void drawOn(DrawSurface d);

    /**
     * Notify the Sprite that time has passed.
     */
    void timePassed();

    /**
     * A function to allow a Sprite to add itself to the game.
     * In this case the "Game" does not need to know the object,
     * and how to add it, only that it can be added.
     * @param game Game to add the collidable to.
     */
    void addToGame(Game game);
}