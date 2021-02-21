package game.fill;

import java.io.File;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
// import java.image.BufferedImage;

import biuoop.DrawSurface;
import geometry.Rectangle;

public class ImageFill implements Fill {
    BufferedImage image;

    public ImageFill(String imagePath) throws Exception {
        this.image = ImageIO.read(new File(imagePath));
    }

    public void draw(DrawSurface d, Rectangle rect) {
        d.drawImage((int) rect.getUpperLeft().getX(), 
                    (int) rect.getUpperLeft().getY(), 
                    this.image);

    }   

}
