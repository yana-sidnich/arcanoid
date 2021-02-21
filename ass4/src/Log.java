// ID : 323537779

/**
 * This class represents the binary expression log.
 * It extends the BinaryExpression class and implements the Expression interface.
 */
class Log extends BinaryExpression implements Expression {

    /**
     * Constructor of the Log class.
     * Internally Constructs BinaryExpression.
     * @param exp1 first expression
     * @param exp2 second expression
     */
    public Log(Expression exp1, Expression exp2) {
        super(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("log(%s, %s)", this.getExp1().toString(), this.getExp2().toString());
    }

    /**
     * Implementation of the abstract function calculate.
     * @param d1 first expression's value.
     * @param d2 second expression's value.
     * @return calculation of d1, d2.
     * @throws Exception when an arithematic error occurs and the calculation is not possible.
     */
    protected double calculate(double d1, double d2) throws Exception {
        if (d1 < 0 || d2 < 0 || d1 == 1) {
            throw new Exception("cannot calculate log");
        }
        //  LOGa(B) = (LOGcB / LOGcA)
        return java.lang.Math.log(d2) / java.lang.Math.log(d1);
    }

    /**
     * Implementation of the abstract function clone.
     * @param exp1 first expression.
     * @param exp2 second expression.
     * @return a new Log Expression with exp1, exp2 as internal expressions.
     */
    protected Expression clone(Expression exp1, Expression exp2) {
        return new Log(exp1, exp2);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * This function assumes the base does not depend on var.
     * The differentiation of a log:
     * (LOGa(b))' = b' / b * ln(a)
     * @param var a variable to differntiate according to.
     * @return the differentiation of the Log Expression according to "var".
     */
    public Expression differentiate(String var) {

        // b'
        Expression nominator = this.getExp2().differentiate(var);
        // ln(a) * b
        Expression denominator = new Mult(new Log(Const.EXPONENT, this.getExp1()), this.getExp2());
        // b' / (ln(a) * b)
        Expression returnExpression = new Div(nominator, denominator);

        return returnExpression;
    }

    /**
     * Implementation of the abstract function specialCaseSimplify.
     * Implements the special cases of the Log class.
     * @param exp1 simplified first expression.
     * @param exp2 simplified second expression.
     * @return Special new simplified expression, or null.
     * @throws Exception only if internal function calls throw.
     */
    protected Expression specialCaseSimplify(Expression exp1, Expression exp2) throws Exception {

        // LOGa(1) = 0
        if (exp2.getVariables().isEmpty() && exp2.evaluate() == 1) {
            return new Num(0);
        }
        // LOGa(a) = 1
        if (exp1.toString().equals(exp2.toString())) {
            return new Num(1);
        }

        return null;
    }
}