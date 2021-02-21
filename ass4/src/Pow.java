// ID : 323537779

/**
 * This class represents the binary expression power.
 * It extends the BinaryExpression class and implements the Expression interface.
 */
class Pow extends BinaryExpression implements Expression {

    /**
     * Constructor of the Pow class.
     * Internally Constructs BinaryExpression.
     * @param exp1 first expression
     * @param exp2 second expression
     */
    public Pow(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("(%s^%s)", this.getExp1().toString(), this.getExp2().toString());
    }

    /**
     * Implementation of the abstract function calculate.
     * @param d1 first expression's value.
     * @param d2 second expression's value.
     * @return calculation of d1, d2.
     * @throws Exception when an arithematic error occurs and the calculation is no possible.
     */
    protected double calculate(double d1, double d2) throws Exception {
        return java.lang.Math.pow(d1, d2);
    }

    /**
     * Implementation of the abstract function clone.
     * @param exp1 first expression.
     * @param exp2 second expression.
     * @return a new Pow Expression with exp1, exp2 as internal expressions.
     */
    protected Expression clone(Expression exp1, Expression exp2) {
        return new Pow(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * The differentiation of a power:
     * (f^g)' = (f^g) * (((f' * g) / f) + g' * ln(f))
     * @param var a variable to differntiate according to.
     * @return the differentiation of the Pow Expression according to "var".
     */
    public Expression differentiate(String var) {
        Expression diff1 = this.getExp1().differentiate(var);
        Expression diff2 = this.getExp2().differentiate(var);

        //  f^g
        Expression exp1 = new Pow(this.getExp1(), this.getExp2());
        // ((f' * g) / f)
        Expression exp2 = new Div(new Mult(diff1, this.getExp2()), this.getExp1());
        // g ' * ln(f)
        Expression exp3 = new Mult(diff2, new Log(Const.EXPONENT, this.getExp1()));

        // all the expressions together.
        Expression finalExpression = new Mult(exp1, new Plus(exp2, exp3));
        return finalExpression;
    }

   /**
     * Implementation of the abstract function specialCaseSimplify.
     * Implements the special cases of the Pow class.
     * @param exp1 simplified first expression.
     * @param exp2 simplified second expression.
     * @return Special new simplified expression, or null.
     * @throws Exception only if internal function calls throw.
     */
    protected Expression specialCaseSimplify(Expression exp1, Expression exp2) throws Exception {

        // 1^x = 1
        if (exp1.getVariables().isEmpty() && exp1.evaluate() == 1) {
            return new Num(1);
        }

        if (exp2.getVariables().isEmpty()) {
            double exp2Val = exp2.evaluate();
            // x^0 = 1
            if (exp2Val == 0) {
                return new Num(1);
            }
            // x^1 = x
            if (exp2Val == 1) {
                return exp1;
            }
        }

        return null;
    }
}