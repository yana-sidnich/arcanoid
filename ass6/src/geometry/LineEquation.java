// ID: 323537779
package geometry;

/**
 * The class represents a line equation.
 * A Line equation is "y = mx + b".
 * slope represents the m in the equation.
 * yIntercept represents the intersection point with the y axis, the b in the line equation.
 * xIntercept represent the intersection point with the x axis.
 * If the line is parallel to y axis it's slope is POSITIVE_INFINITY.
 */
public class LineEquation {
    private double slope;
    private double yIntercept;
    private double xIntercept;

    /**
     * Constructor of the LineEquation class.
     * The equation is calculated using two point on the line.
     * @param startPoint first point of the line
     * @param endpPoint second point of the line
     */
    public LineEquation(Point startPoint, Point endpPoint) {
        double yDelta = startPoint.getY() - endpPoint.getY();
        double xDelta = startPoint.getX() - endpPoint.getX();

        /* In the case xDelta equals 0:
           slope = POSITIVE_INFINITY.
           yIntercept = POSITIVE_INFINITY.
           xIntercept = start.x.
           Else:
           slope = yDelta / xDelta.
           yIntercept = start.y - slope * start.x.
           xIntercept = INFINITY if slope is 0 else -yIntercept/slope. */
        if (xDelta == 0) {
            this.slope = Double.POSITIVE_INFINITY;
            this.yIntercept = Double.POSITIVE_INFINITY;
            this.xIntercept = startPoint.getX();
        } else {
            this.slope = yDelta / xDelta;
            this.yIntercept = startPoint.getY() - slope * startPoint.getX();
            this.xIntercept = this.slope == 0 ? Double.POSITIVE_INFINITY : -yIntercept / this.slope;
        }
    }

    /**
     * Checks if two lines are parallel (have the same slope).
     * @param other LineEquation to check parallel to.
     * @return true if parallel, else false.
     */
    public boolean isParallel(LineEquation other) {
        return this.slope == other.slope;
    }
    /**
     * Checks if LineEquation is parallel to the Y axis.
     * @return true if parallel, else false.
     */
    private boolean isParallelToY() {
        return this.slope == Double.POSITIVE_INFINITY;
    }

    /**
     * Getter of the LineEquation slope.
     * @return the LineEquation slope.
     */
    public double getSlope() {
        return this.slope;
    }

    /**
     * Getter of the LineEquation YIntercept.
     * @return the LineEquation YIntercept.
     */
    public double getYIntercept() {
        return this.yIntercept;
    }

    /**
     * Getter of the LineEquation XIntercept.
         * @return the LineEquation XIntercept.
     */
    public double getXIntercept() {
        return this.xIntercept;
    }

    /**
     * Returns the intersection point of two lines.
     * This function assumes the lines are not parallel, and calling this function
     * with parallel lines is not supported. Checking if lines are parallel can be done by
     * calling the isParallel function.
     * @param other LineEquation to find intersection to.
     * @return intersection with other LineEquation.
     */
    public Point intersection(LineEquation other) {
        // x and y values of the intersection point.
        double xValue = 0;
        double yValue = 0;
        /* In the case one of the lines is parallel to the y axis:
           xValue of the intersection point = xIntercept to this of parallel line.
           yValue is calculated from the non-parallel line equation: slope * xValue + yIntercept. */
        if (this.isParallelToY()) {
            xValue = this.xIntercept;
            yValue = other.slope * xValue + other.yIntercept;
        } else if (other.isParallelToY()) {
            xValue = other.xIntercept;
            yValue = this.slope * xValue + this.yIntercept;
        } else {
            /* Otherwise each value of the Point (x or y) is calculated by comparing the line
            equations and isolating the specific value we want to calculate (x or y). */
            xValue = -(this.yIntercept - other.yIntercept) / (this.slope - other.slope);
            yValue = this.slope * xValue + this.yIntercept;
        }

        return new Point(xValue, yValue);
    }

    /**
     * Returns a printable String that represents the LineEqution.
     * @return String that represents the LineEqution.
     */
    public String toString() {
        if (isParallelToY()) {
            return String.format("x = %f", this.xIntercept);
        }
        if (this.slope == 0) {
            return String.format("y = %f", this.yIntercept);
        }

        return String.format("y = %f * x + %f", this.slope, this.yIntercept);
    }
}