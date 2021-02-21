// ID : 323537779

/**
 * This class represents the binary expression substraction(minus).
 * It extends the BinaryExpression class and implements the Expression interface.
 */
class Minus extends BinaryExpression implements Expression {

    /**
     * Constructor of the Minus class.
     * Internally Constructs BinaryExpression.
     * @param exp1 first expression
     * @param exp2 second expression
     */
    public Minus(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("(%s - %s)", this.getExp1().toString(), this.getExp2().toString());
    }


    /**
     * Implementation of the abstract function calculate.
     * @param d1 first expression's value.
     * @param d2 second expression's value.
     * @return calculation of d1, d2.
     * @throws Exception when an arithematic error occurs and the calculation is no possible.
     */
    protected double calculate(double d1, double d2) throws Exception {
        return d1 - d2;
    }

    /**
     * Implementation of the abstract function clone.
     * @param exp1 first expression.
     * @param exp2 second expression.
     * @return a new Minus Expression with exp1, exp2 as internal expressions.
     */
    protected Expression clone(Expression exp1, Expression exp2) {
        return new Minus(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * @param var a variable to differntiate according to.
     * @return the differentiation of the Minus Expression according to "var".
     */
    public Expression differentiate(String var) {
        Expression diff1 = this.getExp1().differentiate(var);
        Expression diff2 = this.getExp2().differentiate(var);
        return new Minus(diff1, diff2);
    }

    /**
     * Implementation of the abstract function specialCaseSimplify.
     * Implements the special cases of the Minus class.
     * @param exp1 simplified first expression.
     * @param exp2 simplified second expression.
     * @return Special new simplified expression, or null.
     * @throws Exception only if internal function calls throw.
     */
    protected Expression specialCaseSimplify(Expression exp1, Expression exp2) throws Exception {

        // 0 - x = -x
        if (exp1.getVariables().isEmpty() && exp1.evaluate() == 0) {
            return new Neg(exp2);
        }
        // x - 0 = x
        if (exp2.getVariables().isEmpty() && exp2.evaluate() == 0) {
            return exp1;
        }
        // x - x = 0
        if (exp1.toString().equals(exp2.toString())) {
            return new Num(0);
        }

        return null;
    }

}