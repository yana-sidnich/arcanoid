// ID: 323537779
package geometry;

/**
 * The class represents a Point in a 2 dimentional surface.
 * A Point is represented by an x, y values.
 */
public class Point {
    private double x;
    private double y;

    /**
     * Constructor of the Point class.
     * @param x Represents the x value of the Point.
     * @param y Represents the y value of the Point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Calculates the distance between this Point and the other given Point.
     * distance = sqrt((x1-x2)^2 + (y1-y2)^2).
     * @param other Point to find distance to.
     * @return Distance to the Point given as a parameter.
     */
    public double distance(Point other) {
        double deltaX = this.x - other.x;
        double deltaY = this.y - other.y;
        // Calculating the distance between the two Points.
        double pointsDistance = Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));

        return pointsDistance;
    }

    /**
     * Check if the two Points are identical.
     * Identical Points are Points that have the same x and y values.
     * @param other Point to compare to.
     * @return true if the Points are identical, else false.
     */
    public boolean equals(Point other) {
        return ((this.x == other.x) && (this.y == other.y));
    }

    /**
     * Getter of the x value.
     * @return x value of the Point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Getter of the y value.
     * @return y value of the Point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * Setter of the x value.
     * @param xValue x value to assign to the Point's x value.
     */
    public void setX(double xValue) {
        this.x = xValue;
    }

    /**
     * Setter of the y value.
     * @param yValue y value to assign to the Point's y value.
     */
    public void setY(double yValue) {
        this.y = yValue;
    }

    /**
     * Returns a printable string that represents the Point.
     * @return Point as "(x, y)".
     */
    public String toString() {
        return String.format("(%f, %f)", this.x, this.y);
    }
}