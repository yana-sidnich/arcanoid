// ID : 323537779

/**
 * This class represents the binary expression multiplication.
 * It extends the BinaryExpression class and implements the Expression interface.
 */
class Mult extends BinaryExpression implements Expression {

    /**
     * Constructor of the Mult class.
     * Internally Constructs BinaryExpression.
     * @param exp1 first expression
     * @param exp2 second expression
     */
    public Mult(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("(%s * %s)", this.getExp1().toString(), this.getExp2().toString());
    }
    /**
     * Implementation of the abstract function calculate.
     * @param d1 first expression's value.
     * @param d2 second expression's value.
     * @return calculation of d1, d2.
     * @throws Exception when an arithematic error occurs and the calculation is no possible.
     */
    protected double calculate(double d1, double d2) throws Exception {
        return d1 * d2;
    }

    /**
     * Implementation of the abstract function clone.
     * @param exp1 first expression.
     * @param exp2 second expression.
     * @return a new Mult Expression with exp1, exp2 as internal expressions.
     */
    protected Expression clone(Expression exp1, Expression exp2) {
        return new Mult(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * The differentiation of a multiplication:
     * (f*g)' = f' * g + f * g'

     * @param var a variable to differntiate according to.
     * @return the differentiation of the Mult Expression according to "var".
     */

    public Expression differentiate(String var) {
        Expression diff1 = this.getExp1().differentiate(var);
        Expression diff2 = this.getExp2().differentiate(var);
        // f' * g
        Expression mult1 = new Mult(diff1, this.getExp2());
        // f * g'
        Expression mult2 = new Mult(this.getExp1(), diff2);
        // f' * g + f * g'
        return new Plus(mult1, mult2);
    }

    /**
     * Implementation of the abstract function specialCaseSimplify.
     * Implements the special cases of the Mult class.
     * @param simpleExp1 simplified first expression.
     * @param simpleExp2 simplified second expression.
     * @return Special new simplified expression, or null.
     * @throws Exception only if internal function calls throw.
     */
    protected Expression specialCaseSimplify(Expression simpleExp1, Expression simpleExp2) throws Exception {

        if (simpleExp1.getVariables().isEmpty()) {
            double simpleExp1Val = simpleExp1.evaluate();
            // 0 * x = 0
            if (simpleExp1Val == 0) {
                return new Num(0);
            }
            // 1 * x = x
            if (simpleExp1Val == 1) {
                return simpleExp2;
            }
        }

        if (simpleExp2.getVariables().isEmpty()) {
            double simpleExp2Val = simpleExp2.evaluate();
            // x * 0 = 0
            if (simpleExp2Val == 0) {
                return new Num(0);
            }
            // x * 1 = x
            if (simpleExp2Val == 1) {
                return simpleExp1;
            }
        }

        return null;
    }
}