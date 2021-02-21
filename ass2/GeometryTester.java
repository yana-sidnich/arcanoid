
/**
 * This class does some simple tessting of the Point and Line classes.
 */
public class GeometryTester {
    /**
     * The method is in charge of testing the Point class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testPoint() {
        boolean mistake = false;
        Point p1 = new Point(12, 2);
        Point p2 = new Point(9, -2);

        if (p1.getX() != 12) {
            System.out.println("Test p1.getX() failed.");
            mistake = true;
        }
        if (p1.getY() != 2) {
            System.out.println("Test p1.getY() failed.");
            mistake = true;
        }
        if (p1.distance(p1) != 0) {
            System.out.println("Test distance to self failed.");
            mistake = true;
        }
        if (p1.distance(p2) != p2.distance(p1)) {
            System.out.println("Test distance symmetry failed.");
            mistake = true;
        }
        if (p1.distance(p2) != 5) {
            System.out.println("Test distance failed.");
            mistake = true;
        }
        if (!p1.equals(p1)) {
            System.out.println("Equality to self failed.");
            mistake = true;
        }
        if (!p1.equals(new Point(12, 2))) {
            System.out.println("Equality failed.");
            mistake = true;
        }
        if (p1.equals(p2)) {
            System.out.println("Equality failed -- should not be equal.");
            mistake = true;
        }

        return !mistake;
    }

    /**
     * The method is in charge of testing the Line class.
     *
     * @return true if not mistakes were found, false otherwise.
     */
    public boolean testLine() {
        boolean mistakes = false;
        Line l1 = new Line(10, 0, 0, 0);
        Line l2 = new Line(20, 0,0 , 0);
        //Line l3 = new Line(9, 2, 12, -2);

        if (!l1.isIntersecting(l2)) {
            System.out.println("Test isIntersecting failed (1).");
            mistakes = true;
        }
        if (l1.isIntersecting(new Line(0, 0, 1, 1))) {
            System.out.println("Test isIntersecting failed (2).");
            mistakes = true;
        }
        Point intersectL1L2 = l1.intersectionWith(l2);
        if (!l1.middle().equals(intersectL1L2)) {
            System.out.println("Test intersectionWith middle failed.");
            mistakes = true;
        }

        return !mistakes;
    }

    /**
     * Main method, running tests on both the point and the line classes.
     * @param args ignored.
     */
    public static void main(String[] args) {
        // GeometryTester tester = new GeometryTester();
        // tester.testPoint();
        // tester.testLine();
        // System.out.println("success");

        Line l1 = new Line(new Point(0.0,0.0), new Point(10.0,0.0));
        Line l2 = new Line(new Point(0.0,10.0), new Point(0.0,0.0));
        System.out.println(l1.intersectionWith(l2).toString());

        l1 = new Line(new Point(0.0,0.0), new Point(5.0,5.0));
        l2 = new Line(new Point(5.0,0.0), new Point(0.0,5.0));
        
        System.out.println(l1.intersectionWith(l2).toString());
        
        l1 = new Line(new Point(0.0,0.0), new Point(5.0,5.0));
        l2 = new Line(new Point(1.0,0.0), new Point(1.0,5.0));
        
        System.out.println(l1.intersectionWith(l2).toString());
        
        l1 = new Line(new Point(1.0,7.0), new Point(1.0,0.0));
        l2 = new Line(new Point(7.0,1.0), new Point(0.0,1.0));
        System.out.println(l1.intersectionWith(l2).toString());
    }
}