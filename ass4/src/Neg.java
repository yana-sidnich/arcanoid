// ID : 323537779

/**
 * This class represents the unary expression negation.
 * It extends the UnaryExpression class and implements the Expression interface.
 */
public class Neg extends UnaryExpression implements Expression {

    /**
     * Constructor of the Neg class.
     * Internally Constructs UnaryExpression.
     * @param exp1 single expression.
     */
    public Neg(Expression exp1) {
        super(exp1);
    }

    /**
     * Implementation of the Expression interface toString function.
     * @return A formatted string representing the expression
     */
    public String toString() {
        return String.format("-%s", this.getExp1().toString());
    }

    /**
     * Implementation of the abstract function calculate.
     * @param d1 first expression's value.
     * @return calculation of d1.
     */
    protected double calculate(double d1) {
        return -d1;
    }

    /**
     * Implementation of the abstract function clone.
     * @param exp1 first expression.
     * @return a new Neg Expression with exp1 as internal expressions.
     */
    protected Expression clone(Expression exp1) {
        return new Neg(exp1);
    }

    /**
     * Implementation of the Expression interface differentiate function.
     * @param var a variable to differntiate according to.
     * @return the differentiation of the Neg Expression according to "var".
     */
    public Expression differentiate(String var) {
        Expression returnExpression = new Neg(this.getExp1().differentiate(var));
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
        // no special cases.
        return null;
    }
}