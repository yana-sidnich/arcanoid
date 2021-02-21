// ID: 323537779

/**
 * The class represents a velocity.
 * the velocity is the amount that a Point moves in the x direction and y direction in each invocation.
 * It is implemented using two doubles:
 * dx which represents delta (or difference) on the x value of a point in each movement.
 * dy which represents delta (or difference) on the y value of a point in each movement.
 */
class Velocity {
    private double dx;
    private double dy;

    /**
     * Constructor of the Velocity class.
     * @param dx Represents the difference of x value in each movement.
     * @param dy Represents the difference of y value in each movement.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * Creates a new velocity instance from angle and speed.
     * @param angle the direction (in degrees) of the constructed velocity.
     * @param speed the total difference in distance in each movement.
     * @return Velocit corresponding to given angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // converting the angle to radians, as expected from sin and cos.
        double angleInRadians = angle * Math.PI / 180;
        // finding the difference in each axis (x and y).
        double dx = speed * Math.cos(angleInRadians);
        double dy = speed * Math.sin(angleInRadians);
        // retrun a new Velicity instance with found dx and dy.
        return new Velocity(dx, dy);
    }


    /**
     * Applies the velocity to the given Point.
     * @param point Point (x,y) to apply velocity on
     * @return new Point (x+dx, y+dy)
     */
    public Point applyToPoint(Point point) {
        return new Point(point.getX() + this.dx, point.getY() + this.dy);
    }

    /**
     * Getter of the difference in x.
     * @return difference in x.
     */
    public double getX() {
        return this.dx;
    }

    /**
     * Getter of the difference in y.
     * @return difference in x.
     */
    public double getY() {
        return this.dy;
    }
 }