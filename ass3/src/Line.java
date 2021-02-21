// ID: 323537779

import java.util.List;

/**
 * The class represents a line segment.
 * It is implemented using two Points, which represents the start and end of the segment.
 */
class Line {
    private Point startPoint;
    private Point endPoint;

    /**
     * Constructor of the Line class.
     * @param start Represents the start Point of the line.
     * @param end Represents the end Point of the line.
     */
    public Line(Point start, Point end) {
        this.startPoint = start;
        this.endPoint = end;
    }
    /**
     * Constructor of the Line class.
     * Creating two Points and assigning them to the start and end of the lines.
     * @param startX x value of the start Point.
     * @param startY y value of the start Point.
     * @param endX x value of the end Point.
     * @param endY y value of the end Point.
     */
    public Line(double startX, double startY, double endX, double endY) {
        Point start = new Point(startX, startY);
        Point end = new Point(endX, endY);
        this.startPoint = start;
        this.endPoint = end;
    }

    /**
     * Calculate the length of the Line.
     * This is done by calcualting the distance between start and end points.
     * distance is a function of the Point class.
     * @return distance between start and end.
     */
    public double length() {
        return this.startPoint.distance(this.endPoint);
    }

    /**
     * Calculating the middle Point of the line segment.
     * This is done by finding the middle x value and middle y value of the two Points.
     * @return middle Point of start and end.
     */
    public Point middle() {
        double xValue = (this.startPoint.getX() + this.endPoint.getX()) / 2;
        double yValue = (this.startPoint.getY() + this.endPoint.getY()) / 2;
        Point middle = new Point(xValue, yValue);
        return middle;
    }

    /**
     * Getter of the start Point.
     * @return start Point of the Line.
     */
    public Point start() {
        return this.startPoint;
    }

    /**
     * Getter of the end Point.
     * @return end Point of the Line.
     */
    public Point end() {
        return this.endPoint;
    }

    /**
     * Checks if the Line is single Point.
     * A Line is a single Point if start Point is equal to end Point.
     * @return true if start Point is equal to end Point, else false.
     */
    private boolean isSinglePoint() {
        return startPoint.equals(endPoint);
    }

    /**
     * Checks if two double are approximately equal, in order to avoid precision loss.
     * This is done by checking if the difference is smaller than a chosen epsilon (10^(-10)).
     * @param d1 first double.
     * @param d2 second double.
     * @return true if the doubles are approximately equal, else false.
     */
    private static boolean approximatelyEqual(double d1, double d2) {
        double epsilon = Math.pow(10, -10);
        return Math.abs(d1 - d2) < epsilon;
    }

    /**
     * Checks if the given point is on the line segment.
     * This is done by checking if:
     * d(from start to point to check) + d(from end to point to check) = d(start, end) when
     * d = distance.
     * @param toCheck Point to check if it's on the Line.
     * @return true if point is on Line, else false.
     */
    public boolean isPointOnLine(Point toCheck) {
        double dToStart = toCheck.distance(this.startPoint);
        double dToEnd = toCheck.distance(this.endPoint);

        return approximatelyEqual(dToStart + dToEnd, this.length());
    }

    /**
     * Check if two Lines intersect.
     * This is done by trying to find an intersection Point.
     * If Point found the Lines intersect
     * If not then the lines do not intersect.
     * @param other Line to check intersection with.
     * @return true if Lines intersect, else false.
     */
    public boolean isIntersecting(Line other) {
        return this.intersectionWith(other) != null;
    }

    /**
     * Checks if two Parrallel Lines share an intersection Point.
     * If they do the point is returned.
     * @param other other Line to check if has same point.
     * @return The shared Point on both Lines if exists, else none.
     */
    private Point findIntersectionParallelLines(Line other) {

        // Finding which line is the short one and which is the long one.
        Line longLine = this.length() > other.length() ? this : other;
        Line shortLine = this.length() > other.length() ? other : this;

        /* If one of the shortLine's points (start or end) is equal to one the longLine's points (start or end), we
        check to see if the second point (start or end) is on the long line, if it is, shortLine is actually a part of
        longLine, and they share INFINITY Points. */

        // Check for ShortLine.startPoint.
        if (shortLine.startPoint.equals(longLine.startPoint) || shortLine.startPoint.equals(longLine.endPoint)) {
            if (longLine.isPointOnLine(shortLine.endPoint)) {
                return null;
            }
            return shortLine.startPoint;
        }

        // Check for ShortLine.endPoint.
        if (shortLine.endPoint.equals(longLine.startPoint) || shortLine.endPoint.equals(longLine.endPoint)) {
            if (longLine.isPointOnLine(shortLine.startPoint)) {
                return null;
            }
            return shortLine.endPoint;
        }

        return null;
    }

    /**
     * Finds the intersection point with another line.
     * Lines are represented by the equation : y = mx + b where
     * m = slope, y = intercept with y axis.
     * If one of the lines is a single Point, we check if it's on the other line.
     * Else if two lines are parallel there is no intersection Point.
     * Else we find the intersection Point of the two lines by comparing both line equations.
     * After finding the intersection Point we validate it is on both line segments.
     * @param other Line to check intersection with.
     * @return Intersection Point if found, null if not.
     */
    public Point intersectionWith(Line other) {
        // In the case not both lines are real lines, meaning a single point.
        if (this.isSinglePoint() || other.isSinglePoint()) {
            if (this.isSinglePoint() && other.isPointOnLine(this.startPoint)) {
                return this.startPoint;
            }
            if (other.isSinglePoint() && this.isPointOnLine(other.startPoint)) {
                return other.startPoint;
            }

            return null;
        }

        /* Both lines are real lines (not single Points),
           so we create each line its own line equation instance.
        */
        LineEquation thisEquation = new LineEquation(this.startPoint, this.endPoint);
        LineEquation otherEquation = new LineEquation(other.startPoint, other.endPoint);

        // Check if two Lines are parallel.
        if (thisEquation.isParallel(otherEquation)) {
        // Check if two parallel Lines have an intersection point.
            return findIntersectionParallelLines(other);
        }
        // Finding the intersection point by calling LineEquation intersection function.
        Point intersection = thisEquation.intersection(otherEquation);

        // Validating that the intersection Point is on both line segments.
        if (this.isPointOnLine(intersection) && other.isPointOnLine(intersection)) {
            return intersection;
        }

        return null;
    }

    /**
     * Check if the two Lines are identical.
     * Identical Lines are Lines that have the same start and end Points.
     * @param other Line to compare to.
     * @return true if the Lines are identical, else false.
     */
    public boolean equals(Line other) {
        return this.startPoint.equals(other.startPoint)
               && this.endPoint.equals(other.endPoint);
    }

    /**
     * Find the closest intersection point with the rectangle to the start of line.
     * @param rect Rectangle to find the closest intersection point of it with the line (from startPoint of line)
     * @return closest intersection point of the given rectangle and line (from startPoint of line) or
     *         null if no intersection point exists.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {

        // Calculate the intersectionPoint list of the given rectangle with this line.
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        double minDistance = Double.POSITIVE_INFINITY;
        Point closestPoint = null;
        /* Iterating through all the intersection points and find the minimum.
           null is returned by default if there is no points at all */
        for (Point point : intersectionPoints) {
            double curDistance = this.startPoint.distance(point);
            // Check if the current point is closer than the minimal found.
            if (curDistance < minDistance) {
                // Updating to the new found minimum.
                minDistance = curDistance;
                closestPoint = point;
            }
        }

        return closestPoint;
    }

    /**
     * Returns a printable string that represents the Line.
     * @return Line as "((x1, y1), (x2, y2))".
     */
    public String toString() {
        return String.format("(%s, %s)", startPoint.toString(), endPoint.toString());
    }
}