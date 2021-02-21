import java.util.List;
import java.util.Map;

/**
 * UnaryExpression is an abstract class that extends the BaseExpression.
 * It implements the logic of all the binary Expressions functions:
 * - getVariables
 * - evaluate
 * - simplify
 * The implemetation uses internal functions which are abstract,
 * to be implemented by inheriting classes.
 */
abstract class UnaryExpression extends BaseExpression {
    private Expression exp1;

    /**
     * Constructor of the unary expression class.
     * @param exp1 expression
     */
    UnaryExpression(Expression exp1) {
        this.exp1 = exp1;
    }

    /**
     * Getter of the expression.
     * @return this.exp1
     */
    protected Expression getExp1() {
        return this.exp1;
    }

    /**
     * an abstract function the extending classes will implement.
     * an internal calculate function, that recieves a double and calculates it
     * according to the inheriting class operation.
     * @param d1 the expression's value.
     * @return calculation of d1.
     */
    protected abstract double calculate(double d1);

    /**
     * an abstract function the extending classes will implement.
     * Create a new instance of the inheriting class with the new parameter.
     * @param expression1 expression.
     * @return a new Expression with expression1 as internal expression.
     */
    protected abstract Expression clone(Expression expression1);

    /**
     * an abstract function the extending classes will implement.
     * If the extending class have a special case where a special simplify occurs,
     * The simplify expression wil be returned, else null is returned.
     * @param expression1 simplified expression
     * @return Special new simplified expression, or null
     * @throws Exception in the case the special simplify fails to complete.
     */
    protected abstract Expression specialCaseSimplify(Expression expression1) throws Exception;

    /**
     * Implementation of the Expression interface getVariables function.
     * Returns the list of variables in the Expression.
     * @return all variables in the Expression.
     */
    public List<String> getVariables() {
        return this.exp1.getVariables();
    }

    /**
     * Implementation of the Expression interface evaluate function.
     * @param assignment map of variables to assign
     * @return the evaluated expression
     * @throws Exception only if internal evalute throws
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.calculate(this.exp1.evaluate(assignment));
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return the evaluated expression.
     * @throws Exception only if internal evalute throws.
     */
    public double evaluate() throws Exception {
        return this.calculate(this.exp1.evaluate());
    }

    /**
     * Implementation of the Expression interface assign function.
     * replace the variable correlating with "var" with "expression"
     * @param var Variable to replace.
     * @param expression the new expression to replace var with.
     * @return the new assigned expression.
     */
    public Expression assign(String var, Expression expression) {
        return this.clone(this.exp1.assign(var, expression));
    }

    /**
     * Implementation of the Expression interface simplify function.
     * Uses the internal abstract function specialCaseSimplify.
     * It tries to use the special case.
     * If no special case then it tries to evaluate.
     * If both fails, return a cloned expression using the simplified internal expressions.
     * @return the Simplified Expression.
     */
    public Expression simplify() {

        Expression simpleEx1 = this.exp1.simplify();
        try {

            // Try special Simplify.
            Expression retExpression = this.specialCaseSimplify(simpleEx1);
            if (retExpression != null) {
                return retExpression;
            }

            // No special simplify, try to evaluate normally if no variables.
            if (this.getVariables().isEmpty()) {
                return new Num(this.evaluate());
            }
        } catch (Exception ex) {
            // Something failed, just clone the using the simple expression.
            return clone(simpleEx1);
        }

        // Nothing special, just clone the object using the simple expression.
        return clone(simpleEx1);
    }
}