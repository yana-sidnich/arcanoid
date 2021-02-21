import java.util.Map;
import java.util.TreeMap;

public class Part1Test {
    public static void main(String[] args) {
        Expression e = new Sin(
                     new Pow(
                        new Mult(
                           new Plus(
                              new Mult(new Num(2), new Var("x")),
                              new Var("y")),
                           new Num(4)),
                     new Var("x")));
        System.out.println(e);

        Expression exp2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));

        System.out.println(exp2);

        Expression e3 = exp2.assign("y", exp2);

        System.out.println(e3);
        // (x + ((x + y)^2))^2
        System.out.println(e3.getVariables());
        e3 = e3.assign("x", new Num(1));
        System.out.println(e3);
        // (1 + ((1 + y)^2))^2
        Map<String, Double> assignment = new TreeMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 4.0);
        try {
            double value = exp2.evaluate(assignment);
            System.out.println("The result is: " + value);
        } catch (Exception ex) {
            System.out.println(String.format("Exception thrown: [%s]", ex.getMessage()));
        }

    }
}