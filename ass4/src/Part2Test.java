import java.util.Map;
import java.util.TreeMap;


public class Part2Test {
    public static boolean testCaseDiff(Expression e, String src, String diff, String diffBy) {
        boolean b = true;
        if (!e.toString().equals(src)) {
            System.out.println(String.format("Expression [%s] does not match predict [%s]", e.toString(), src));
            System.out.println();
            b = false;
        }

        var dif = e.differentiate(diffBy);
        if (!dif.toString().equals(diff)) {
            System.out.println(String.format("Diffrentiation [%s] does not match predict [%s] when diff is by [%s]", dif.toString(), diff, diffBy));
            b = false;
        }

        return b;
    }

    public static boolean testCaseString(Expression e, String src) {
        boolean b = true;
        if (!e.toString().equals(src)) {
            System.out.println(String.format("Expression [%s] does not match predict [%s]", e.toString(), src));
            b = false;
        }

        return b;
    }

    public static void main(String[] args) {

        var varX = new Var("x");
        var varY = new Var("y");

        var plus1 = new Plus(varX, varY);

        testCaseString(plus1, "(x + y)");
        testCaseString(plus1.assign("x", new Num(3)), "(3.0 + y)");
        testCaseString(plus1.assign("y", new Num(3)), "(x + 3.0)");
        testCaseDiff(plus1, "(x + y)", "(1.0 + 0.0)", "x");
        testCaseDiff(plus1, "(x + y)", "(0.0 + 1.0)", "y");

        var minus1 = new Minus(varX, varY);

        testCaseString(minus1, "(x - y)");
        testCaseString(minus1.assign("x", new Num(3)), "(3.0 - y)");
        testCaseString(minus1.assign("y", new Num(3)), "(x - 3.0)");
        testCaseDiff(minus1, "(x - y)", "(1.0 - 0.0)", "x");
        testCaseDiff(minus1, "(x - y)", "(0.0 - 1.0)", "y");

        var div1 = new Div(varX, varY);
        testCaseDiff(div1, "(x / y)", "(((1.0 * y) - (0.0 * x)) / (y * y))", "x");


        // Expression e = new Sin(
        //              new Pow(
        //                 new Mult(
        //                    new Plus(
        //                       new Mult(new Num(2), new Var("x")),
        //                       new Var("y")),
        //                    new Num(4)),
        //              new Var("x")));
        // System.out.println(e);

        // Expression exp2 = new Pow(new Plus(new Var("x"), new Var("y")), new Num(2));

        // System.out.println(exp2);

        // Expression e3 = exp2.assign("y", exp2);

        // System.out.println(e3);
        // // (x + ((x + y)^2))^2
        // System.out.println(e3.getVariables());
        // e3 = e3.assign("x", new Num(1));
        // System.out.println(e3);
        // // (1 + ((1 + y)^2))^2
        // Map<String, Double> assignment = new TreeMap<String, Double>();
        // assignment.put("x", 2.0);
        // assignment.put("y", 4.0);
        // try {
        //     double value = exp2.evaluate(assignment);
        //     System.out.println("The result is: " + value);
        // } catch (Exception ex) {
        //     System.out.println(String.format("Exception thrown: [%s]", ex.getMessage()));
        // }

    }
}