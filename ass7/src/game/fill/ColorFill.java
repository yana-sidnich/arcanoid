package game.fill;

import biuoop.DrawSurface;
import geometry.Rectangle;
import java.awt.Color;

public class ColorFill implements Fill {

    Color color;
    public ColorFill(Color color) {
        this.color = color;
    }

    public void draw(DrawSurface d, Rectangle rect) {
        rect.drawOn(d, this.color);

    }   
}