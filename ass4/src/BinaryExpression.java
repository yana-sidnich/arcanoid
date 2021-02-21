import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * BinaryExpression is an abstract class that extends the BaseExpression.
 * It implements the logic of all the binary Expressions functions:
 * - evaluate
 * - assign
 * - simplify
 * The implementation uses internal functions which are abstract,
 * to be implemented by inheriting classes.
 */
abstract class BinaryExpression extends BaseExpression {
    private Expression exp1;
    private Expression exp2;

    /**
     * Constructor of the binary expression class.
     * @param exp1 first expression
     * @param exp2 second expression
     */
    BinaryExpression(Expression exp1, Expression exp2) {
        this.exp1 = exp1;
        this.exp2 = exp2;
    }

    /**
     * Getter of the first element.
     * @return exp1
     */
    protected Expression getExp1() {
        return this.exp1;
    }

    /**
     * Getter of the second element.
     * @return exp2
     */
    protected Expression getExp2() {
        return this.exp2;
    }

    /**
     * An abstract function the extending classes will implement.
     * An internal calculate function, that recieves two doubles and calculates them
     * according to the inheriting class operation.
     * @param d1 first expression's value.
     * @param d2 second expression's value.
     * @return calculation of d1, d2.
     * @throws Exception if internal calculation fails.
     */
    protected abstract double calculate(double d1, double d2) throws Exception;

    /**
     * an abstract function the extending classes will implement.
     * Create a new instance of the inheriting class with the new parameters.
     * @param expression1 first expression.
     * @param expression2 second expression.
     * @return a new Expression with expression1, expression2 as internal expressions.
     */
    protected abstract Expression clone(Expression expression1, Expression expression2);

    /**
     * An abstract function the extending classes will implement.
     * If the extending class have a special case where a special simplify occurs,
     * The simplify expression wil be returned, else null is returned
     * @param expression1 simplified first expression
     * @param expression2 simplified second expression
     * @return Special new simplified expression, or null
     * @throws Exception in the case the special simplify fails to complete.
     */
    protected abstract Expression specialCaseSimplify(Expression expression1,
                                                      Expression expression2) throws Exception;

    /**
     * Implementation of the Expression interface getVariables function.
     * Returns the list of variables in the Expression.
     * @return all variables in the Expression.
     */
    public List<String> getVariables() {

        List<String> variablesToReturn = new ArrayList<String>();
        // insert all the variables in the first expression to the returning list.
        variablesToReturn.addAll(exp1.getVariables());

        // Iterate through the variables of the second expression and insert only new elements.
        for (String var : exp2.getVariables()) {
            if (!variablesToReturn.contains(var)) {
                variablesToReturn.add(var);
            }
        }
        // Sort the variables for readability and keeping order.
        variablesToReturn.sort(Comparator.naturalOrder());

        return variablesToReturn;
    }

    /**
     * Implementation of the Expression interface evaluate function.
     * @param assignment map of variables to assign
     * @return the evaluated expression
     * @throws Exception only if internal evaluate calls throw.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.calculate(this.exp1.evaluate(assignment), this.exp2.evaluate(assignment));
    }

    /**
     * A convenience method. Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return the evaluated expression.
     * @throws Exception only if internal evaluate calls throw.
     */
    public double evaluate() throws Exception {
        return this.calculate(this.exp1.evaluate(), this.exp2.evaluate());
    }

    /**
     * Implementation of the Expression interface assign function.
     * replace the variable correlating with "var" with "expression".
     * @param var Variable to replace.
     * @param expression the new expression to replace var with.
     * @return the new assigned expression.
     */
    public Expression assign(String var, Expression expression) {
        // Clone a new object where the var is assigned in the internal expressions.
        return this.clone(exp1.assign(var, expression), exp2.assign(var, expression));
    }

    /**
     * Implementation of the Expression interface simplify function.
     * uses the internal abstract function specialCaseSimplify.
     * It first tries to use the special case simplify.
     * If no special case simplify, then it tries to evaluate.
     * If both fails, return a cloned expression using the simplified internal expressions.
     * @return the Simplified Expression
     */
    public Expression simplify() {
        Expression simpleEx1 = this.getExp1().simplify();
        Expression simpleEx2 = this.getExp2().simplify();

        try {
            // Try special Simplify.
            Expression retExpression = this.specialCaseSimplify(simpleEx1, simpleEx2);
            if (retExpression != null) {
                return retExpression;
            }
            // No special simplify, try to evaluate normally if no variables.
            if (this.getVariables().isEmpty()) {
                return new Num(this.evaluate());
            }
        } catch (Exception ex) {
            // Something failed, just clone the using the simple expressions.
            return clone(simpleEx1, simpleEx2);
        }

        // Nothing special, just clone the object using the simple expressions.
        return clone(simpleEx1, simpleEx2);
    }
}
