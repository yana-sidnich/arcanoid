// ID : 323537779

/**
 * This class represents the unary expression cosinus.
 * It extends the UnaryExpression class and implements the Expression interface.
 */
public class Cos extends UnaryExpression implements Expression {

    /**
     * Constructor of the Cos class.
     * Internally Constructs UnaryExpression.
     * @param exp1 single expression.
     */
    public Cos(Expression exp1) {
        super(exp1);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("cos(%s)", this.getExp1().toString());
    }

    /**
     * Implementation of the abstract function calculate.
     * @param d1 expression's value.
     * @return calculation of d1.
     */
    protected double calculate(double d1) {
        return java.lang.Math.cos(java.lang.Math.toRadians(d1));
    }

    /**
     * Implementation of the abstract function clone.
     * @param exp1 expression.
     * @return a new Cos Expression with exp1 as internal expressions.
     */
    protected Expression clone(Expression exp1) {
        return new Cos(exp1);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * The differentiation of a cos(a):
     * -(a' * sin(a))
     * @param var a variable to differntiate according to.
     * @return the differentiation of the Cos Expression according to "var".
     */
    public Expression differentiate(String var) {

        Expression diff1 = this.getExp1().differentiate(var);
        Expression sin = new Sin(this.getExp1());
        Expression returnExpression = new Neg(new Mult(diff1, sin));
        return returnExpression;
    }

    /**
     * Implementation of the abstract function specialCaseSimplify.
     * Implements the special cases of the Cos class.
     * @param exp1 simplified expression.
     * @return Special new simplified expression, or null
     * @throws Exception only if internal function calls throw.
     */
    protected Expression specialCaseSimplify(Expression exp1) throws Exception {
        // cos(0) = 1
        if (exp1.getVariables().isEmpty() && exp1.evaluate() == 0) {
            return new Num(1);
        }
        // simplified version of anything that is not 0 is cos(exp)
        return this.clone(exp1);
    }
}