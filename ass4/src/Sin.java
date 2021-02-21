// ID : 323537779

/**
 * This class represents the unary expression sinus.
 * It extends the UnaryExpression class and implements the Expression interface.
 */
public class Sin extends UnaryExpression implements Expression {

    /**
     * Constructor of the Sin class.
     * Internally Constructs UnaryExpression.
     * @param exp1 single expression.
     */
    public Sin(Expression exp1) {
        super(exp1);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("sin(%s)", this.getExp1().toString());
    }

    /**
     * Implementation of the abstract function calculate.
     * @param d1 expression's value.
     * @return calculation of d1.
     */
    protected double calculate(double d1) {
        return java.lang.Math.sin(java.lang.Math.toRadians(d1));
    }

     /**
     * Implementation of the abstract function clone.
     * @param exp1 expression.
     * @return a new Sin Expression with exp1 as internal expressions.
     */
    protected Expression clone(Expression exp1) {
        return new Sin(exp1);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * The differentiation of a sin(a):
     * a' * cos(a)
     * @param var a variable to differntiate according to.
     * @return the differentiation of the Sin Expression according to "var".
     */
    public Expression differentiate(String var) {

        Expression diff1 = this.getExp1().differentiate(var);
        Expression cos = new Cos(this.getExp1());
        Expression returnExpression = new Mult(diff1, cos);
        return returnExpression;
    }

    /**
     * Implementation of the abstract function specialCaseSimplify.
     * Implements the special cases of the Sin class.
     * @param exp1 simplified first expression
     * @return Special new simplified expression, or null
     * @throws Exception only if internal function calls throw.
     */
    protected Expression specialCaseSimplify(Expression exp1) throws Exception {
        // sin(0) = 0
        if (exp1.getVariables().isEmpty() && exp1.evaluate() == 0) {
            return new Num(0);
        }

        // simplified version of anything that is not 0 is sin(exp).
        return this.clone(exp1);
    }
}