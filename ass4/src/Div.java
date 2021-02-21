// ID : 323537779

/**
 * This class represents the binary expression division.
 * It extends the BinaryExpression class and implements the Expression interface.
 */
class Div extends BinaryExpression implements Expression {

    /**
     * Constructor of the Div class.
     * Internally Constructs BinaryExpression.
     * @param exp1 first expression
     * @param exp2 second expression
     */
    public Div(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("(%s / %s)", this.getExp1().toString(), this.getExp2().toString());
    }

    /**
     * Implementation of the abstract function calculate.
     * @param d1 first expression's value.
     * @param d2 second expression's value.
     * @return calculation of d1, d2.
     * @throws Exception when an arithematic error occurs and the calculation is no possible.
     */
    protected double calculate(double d1, double d2) throws Exception {
        if (d2 == 0) {
            throw new Exception("Cannot divide by 0");
        }
        return d1 / d2;
    }

    /**
     * Implementation of the abstract function clone.
     * @param exp1 first expression.
     * @param exp2 second expression.
     * @return a new Div Expression with exp1, exp2 as internal expressions.
     */
    protected Expression clone(Expression exp1, Expression exp2) {
        return new Div(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * The differentiation of a division:
     * (f/g)' = ((f' * g - g' * f) /  (g * g))
     * @param var a variable to differntiate according to.
     * @return the differentiation of the Div Expression according to "var".
     */
    public Expression differentiate(String var) {
        Expression diff1 = this.getExp1().differentiate(var);
        Expression diff2 = this.getExp2().differentiate(var);

        // f' * g
        Expression nominatorLeft = new Mult(diff1, this.getExp2());
        // g' * f
        Expression nominatorRight = new Mult(diff2, this.getExp1());
        // (f' * g - g' * f)
        Expression nominator = new Minus(nominatorLeft, nominatorRight);
        // g * g
        Expression denominator = new Mult(this.getExp2(), this.getExp2());
        // whole expression
        Expression newDiff = new Div(nominator, denominator);

        return newDiff;
    }

    /**
     * Implementation of the abstract function specialCaseSimplify.
     * Implements the special cases of the Div class.
     * @param exp1 simplified first expression.
     * @param exp2 simplified second expression.
     * @return Special new simplified expression, or null.
     * @throws Exception only if internal function calls throw.
     */
    protected Expression specialCaseSimplify(Expression exp1, Expression exp2) throws Exception {
        // x / 1 = x
        if (exp2.getVariables().isEmpty() && exp2.evaluate() == 1) {
            return exp1;
        }
        // x / x = 1
        if (exp1.toString().equals(exp2.toString())) {
            return new Num(1);
        }
        // 0 / x = 0
        if (exp1.getVariables().isEmpty() && exp1.evaluate() == 0) {
            return new Num(0);
        }

        return null;
    }
}