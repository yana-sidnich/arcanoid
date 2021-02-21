package game.fill;

import biuoop.DrawSurface;
import geometry.Rectangle;

public interface Fill {
    void draw(DrawSurface d, Rectangle rect);
}