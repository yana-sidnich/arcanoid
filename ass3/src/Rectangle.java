// ID: 323537779

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;

/**
 * A class to Represent a Rectangle.
 * Rectangle is represented by its upper left point, it width and its height.
 */
public class Rectangle {

    // Constants to enumarate the Rectangle different sides.
    public static final int SIDE_UPPER = 0;
    public static final int SIDE_LOWER = 1;
    public static final int SIDE_LEFT = 2;
    public static final int SIDE_RIGHT = 3;
    public static final int NUM_SIDES = 4;

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor of the Reactangle class.
     * @param upperLeft The rectangle's upper left point.
     * @param width The rectangle's width.
     * @param height The rectangle's height.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Getter of the Rectangles's width.
     * @return the Rectangles's width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * Getter of the Rectangles's height.
     * @return the Rectangles's height.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Getter of the Rectangles's upperLeft Point.
     * @return the Rectangles's upperLeft Point.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Getter of the Rectangles's upperLeft Point.
     * @param newUpperLeft new point to assign the rectangle.
     */
    public void setUpperleft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }

    /**
     * Returns an array of the 4 Lines of the rectangle,
     * by the places indicated by the SIDE constants.
     * @return an array of size 4 of the Rectangle lines (sides).
     */
    public Line[] getRectangleLines() {
        Line[] lines = new Line[NUM_SIDES];

        // Calculate the Rectangle corners
        Point upperRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
        Point lowerRight = new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
        Point lowerLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);

        // Create the Rectangle Sides/Lines by the corners.
        lines[SIDE_UPPER] = new Line(upperLeft, upperRight);
        lines[SIDE_LEFT] = new Line(upperLeft, lowerLeft);
        lines[SIDE_RIGHT] = new Line(upperRight, lowerRight);
        lines[SIDE_LOWER] = new Line(lowerLeft, lowerRight);

        return lines;
    }

    /**
     * Returns a list of all the intersection points of the line with the rectangle.
     * The sides are recieved from the getRectangleLines function.
     * @param line line to check intersection points with
     * @return a list of all the intersection points.
     */
    public List<Point> intersectionPoints(Line line) {
        Line[] lines = getRectangleLines();
        List<Point> intersectionPoints = new ArrayList<Point>();
        // Iterating the Recatngle's lines and finding all the intersection point.
        for (Line l : lines) {
            if (l.isIntersecting(line)) {
                // Intersection point found, add it to the returning points.
                intersectionPoints.add(l.intersectionWith(line));
            }
        }
        return intersectionPoints;
    }

    /**
     * Draw the rectangle on the draw surface, withblack sides and filled with the gicen color.
     * @param surface DrawSurface to draw the rectangle on.
     * @param color Color of the rectangle to draw.
     */
    public void drawOn(DrawSurface surface, Color color) {
        surface.setColor(color);
        surface.fillRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                              (int) this.width, (int) this.height);

        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) this.upperLeft.getX(), (int) this.upperLeft.getY(),
                            (int) this.width, (int) this.height);

    }
}