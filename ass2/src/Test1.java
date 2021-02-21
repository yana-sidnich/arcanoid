
public class Test1 {
    public static void main(String[] args) {
        Point p1 = new Point(1, 1); 
        Point p2 = new Point(0, 0); 
        Point p3 = new Point(-1, -1); 
        Point p4 = new Point(0, 1); 
        Point p5 = new Point(1, 0); 
        Line l1 = new Line(p1, p2);
        Line l2 = new Line(p2, p1);
        Line l3 = new Line(p2, p3);
        Line l4 = new Line(p3, p2);
        LineEquation le1 = new LineEquation(p1, p2);
        LineEquation le2 = new LineEquation(p2, p3);
        LineEquation le3 = new LineEquation(p2, p4);
        LineEquation le4 = new LineEquation(p2, p5);

        System.out.println(p1.toString());
        System.out.println(p2.toString());
        System.out.println(p3.toString());
        System.out.println(p4.toString());
        System.out.println(p5.toString());

        System.out.println(l1.toString());
        System.out.println(l2.toString());
        System.out.println(l3.toString());
        System.out.println(l4.toString());
        
        System.out.println(le1.toString());
        System.out.println(le2.toString());
        System.out.println(le3.toString());
        System.out.println(le4.toString());

        System.out.println(l1.intersectionWith(l2) == null);
        System.out.println(l1.intersectionWith(l1) == null);
        System.out.println(l1.intersectionWith(l3));

    
    }
}