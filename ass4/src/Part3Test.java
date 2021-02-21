
public class Part3Test {

    public static void simplifyTest(Expression e) {
        System.out.println(String.format("SRC [%s], SIMPLE [%s]", e.toString(), e.simplify().toString()));
    }

    public static void diffSimplifyTest(Expression e, String var) {
        System.out.println(String.format("SRC [%s], SIMPLE [%s]", e.toString(), e.simplify().toString()));
        System.out.println(String.format("DIFF BY [%s] - [%s], SIMPLE_DIFF [%s]", var, e.differentiate(var).toString(), e.differentiate(var).simplify().toString()));
    }

    public static void main(String[] args) {

        var x = new Var("x");
        var y = new Var("y");
        var one = new Num(1);
        var zero = new Num(0);

        System.out.println("----------------------- MUST START ---------------------------");
        // x + 0 = x
        simplifyTest(new Plus(x, zero));
        simplifyTest(new Plus(zero, x));
        // 0 - X = -X
        simplifyTest(new Minus(zero, x));
        // X - 0 = X
        simplifyTest(new Minus(x, zero));
        // X - X = 0
        simplifyTest(new Minus(x, x));
        // x * 0 = 0
        simplifyTest(new Mult(zero, x));
        simplifyTest(new Mult(x, zero));
        // x * 1 = x
        simplifyTest(new Mult(one, x));
        simplifyTest(new Mult(x, one));

        // X / 1 = X
        simplifyTest(new Div(x, one));
        // x / x = 1
        simplifyTest(new Div(x, x));
        // log(x, x) = 1
        simplifyTest(new Log(x, x));
        System.out.println("----------------------- MUST END ---------------------------");

        simplifyTest(new Log(x, one));

        simplifyTest(new Neg(x));
        simplifyTest(new Neg(one));

        simplifyTest(new Sin(x));
        simplifyTest(new Sin(zero));
        simplifyTest(new Sin(one));

        simplifyTest(new Cos(x));
        simplifyTest(new Cos(zero));
        simplifyTest(new Cos(one));


        diffSimplifyTest(new Cos(x), "x");
        diffSimplifyTest(new Cos(y), "y");

        diffSimplifyTest(new Sin(x), "x");
        diffSimplifyTest(new Sin(y), "y");

        diffSimplifyTest(new Plus(x, y), "x");
        diffSimplifyTest(new Plus(x, y), "y");

        diffSimplifyTest(new Div(x, y), "x");
        diffSimplifyTest(new Div(x, y), "y");

        simplifyTest(new Div(x, zero));
        simplifyTest(new Div(one, zero));
        simplifyTest(new Div(zero, one));

    }
}