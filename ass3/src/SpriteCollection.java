// ID: 323537779

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * A class to reresent a collection of sprites.
 * The class has one member of the type List<Sprite>,
 * which holds all the Sprites in the collection.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Constructor of the SpriteCollection class.
     */
    public SpriteCollection() {
        sprites = new ArrayList<Sprite>();
    }

    /**
     * Add a sprite to the collection.
     * @param s A Sprite to add to the collection.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        for (Sprite sprite : sprites) {
            sprite.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     * @param d DrawSurface to draw the sprties on.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }

    /**
     * A Getter of the Sprite collection.
     * @return the Sprite collection.
     */
    public List<Sprite> getSprites() {
        return sprites;
    }
}