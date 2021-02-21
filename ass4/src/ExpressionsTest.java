import java.util.HashMap;
import java.util.Map;

/**
 * ExpressionsTest class - a test for all the differnt expression modules
 * contains the main function.
 * Does the following:
 * Create the expression (2x) + (sin(4y)) + (e^x).
 * Print the expression.
 * Print the value of the expression with (x=2,y=0.25,e=2.71). (Note: e is a const)
 * Print the differentiated expression according to x.
 * Print the value of the differentiated expression according to x with the assignment above.
 * Print the simplified differentiated expression.
 */
public class ExpressionsTest {
    /**
     * Main function.
     * @param args ignore.
     */
    public static void main(String[] args) {
        // Construct  (2x) + (sin(4y)) + (e^x).
        Expression exp1 = new Plus(
                            new Mult(
                                new Num(2),
                                new Var("x")
                               ),
                            new Plus(
                                new Sin(
                                    new Mult(
                                        new Num(4),
                                        new Var("y")
                                       )
                                    ),
                                new Pow(
                                    Const.EXPONENT,
                                    new Var("x")
                                    )
                                )
                            );

        Map<String, Double> assignment = new HashMap<String, Double>();
        assignment.put("x", 2.0);
        assignment.put("y", 0.25);
        // e is a Const.
        try {

            // System.out.println(exp1);
            // System.out.println(exp1.evaluate(assignment));
            // System.out.println(exp1.differentiate("x"));
            // System.out.println(exp1.differentiate("x").evaluate(assignment));
            // System.out.println(exp1.differentiate("x").simplify());

        } catch (Exception ex) {
            // for checkstyle only.
            ;
        }
        // (-((y / -(y / x)) * 0.0) + ---(((-2.0 - 0.0) - (-4.0)) + x))
        
        Var x = new Var("x");
        Var y = new Var("y");
        Num zero = new Num(0);
        Num m2 = new Num(-2);
        Num m4 = new Num(-4);

        Expression left =  new Neg(
            new Mult(new Div(y, new Neg(new Div(y,x))) , zero)
        );
        Expression right =  new Neg(new Neg(new Neg(
            new Plus(new Minus(new Minus(m2,zero), m4), x)
        )));
        
        Expression f = new Plus(left, right);
        System.out.println(f);
        System.out.println(f.simplify());
    }

}
