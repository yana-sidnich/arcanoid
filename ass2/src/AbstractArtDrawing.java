import biuoop.GUI;
import biuoop.DrawSurface;

import java.util.Random;
import java.awt.Color;

/**
 * The class represents an abstract art draw of 10 random lines.
 * Any intesection of the lines is represented a small blue circle.
 * All the middle points are represented by small red circles.
 * This class has no members.
 */
public class AbstractArtDrawing {
    // Consts to represent the screen's size and a Point radius.
    private static final int GUI_WIDTH = 400;
    private static final int GUI_LENGTH = 300;
    private static final int POINT_DRAW_RADIUS = 3;

    /**
     * Generates a new random line within the screen limits.
     * @return A new generated random line.
     */
    public Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        double startX = GUI_WIDTH * rand.nextDouble();
        double startY = GUI_LENGTH * rand.nextDouble();
        double endX = GUI_WIDTH * rand.nextDouble();
        double endY = GUI_LENGTH * rand.nextDouble();

        return new Line(startX, startY, endX, endY);
    }

    /**
     * Draw a given line.
     * @param line Line to draw
     * @param drawSurface Surface to draw on.
     */
    public void drawLine(Line line, DrawSurface drawSurface) {
        drawSurface.setColor(Color.BLACK);
        drawSurface.drawLine((int) line.start().getX(),
                   (int) line.start().getY(),
                   (int) line.end().getX(),
                   (int) line.end().getY());
    }

    /**
     * Draw a given Point.
     * A point is drawn as a circle with a radius of POINT_DRAW_RADIUS.
     * @param point The point to draw.
     * @param drawSurface Surface to draw on.
     * @param color color of the point to draw.
     */
    private void drawPoint(Point point, DrawSurface drawSurface, Color color) {
        drawSurface.setColor(color);
        drawSurface.fillCircle((int) point.getX(), (int) point.getY(), POINT_DRAW_RADIUS);

    }

    /**
     * Draws all the intersection points of a given Line array.
     * @param lines an array of Lines to find intesections between.
     * @param drawSurface Surface to draw on.
     */
    public void drawIntersectionPoints(Line[] lines, DrawSurface drawSurface) {
        // Iterating through all the given Lines.
        for (int i = 0; i < lines.length; ++i) {
            // Iterating only from current line forward, in order to avoid recheck of line pairs.
            for (int j = i + 1; j < lines.length; ++j) {
                if (lines[i].isIntersecting(lines[j])) {
                    // Draw only intersecting lines.
                    drawPoint(lines[i].intersectionWith(lines[j]), drawSurface, Color.BLUE);
                }
            }
        }
    }

    /**
     * This function generates and draws 10 random lines and their intersection + middle points.
     */
    public void drawRandomLines() {
        /* Create a window with the title "Random Lines Example"
           which is 400 pixels wide and 300 pixels high.*/
        GUI gui = new GUI("Random Lines Example", GUI_WIDTH, GUI_LENGTH);
        // Create the lines array.
        Line[] drawnLines = new Line[10];
        DrawSurface drawSurface = gui.getDrawSurface();
        /* Generating random lines to:
            1. Insert into the drawnLines array.
            2. Draw Lines.
            3. Draw middle point.
        */
        for (int i = 0; i < drawnLines.length; ++i) {
            drawnLines[i] = generateRandomLine();
            drawLine(drawnLines[i], drawSurface);
            drawPoint(drawnLines[i].middle(), drawSurface, Color.RED);
        }
        // Find and draw all the instersections.
        drawIntersectionPoints(drawnLines, drawSurface);

        gui.show(drawSurface);
    }

    /**
     * The main function. It is needed in order to call this class from the command line.
     * Creates and draws and abstract art drawing.
     * @param args commandline arguments, currently unused.
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}