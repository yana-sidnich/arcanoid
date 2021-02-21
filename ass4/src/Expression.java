// ID: 323537779

import java.util.List;
import java.util.Map;

/**
 * This Interface represents an arithematic expression.
 * each implementer has to implement the the functions:
 * evaluate, assign, getVariables, toString, differentiate and simplify.
 */
public interface Expression {

    /**
     * Evaluate the expression using the variable values provided in the assignment, and return the result.
     * If the expression contains a variable which is not in the assignment, an exception is thrown.
     * @param assignment a map of variables to assign values to.
     * @return the value of the final expression (when exception is not thrown)
     * @throws Exception when evaluation failed.
     */
    double evaluate(Map<String, Double> assignment) throws Exception;


    /**
     * A convenience method.
     * Like the `evaluate(assignment)` method above, but uses an empty assignment.
     * @return the value of the final expression (when exception is not thrown)
     * @throws Exception when evaluation failed.
     */
    double evaluate() throws Exception;

    /**
     * Returns a list of the variables in the expression.
     * @return list of the variables in the expression.
     */
    List<String> getVariables();

    /**
     * Returns a nice string representation of the expression.
     * @return representation of the expression.
     */
    String toString();

    /**
     * Assign expression into var.
     * Returns a new expression in which all occurrences of the variable
     * var are replaced with the provided expression (Does not modify the current expression).
     * @param var a variable to assign.
     * @param expression Expression to replace the variable with.
     * @return a new Expression where var was replaced with expression.
     */
    Expression assign(String var, Expression expression);

    /**
     * Returns the expression resulting from differentiating the current expression relative to variable `var`.
     * @param var variable to differentiate according to.
     * @return the differentiate of the expression according to var
     */
    Expression differentiate(String var);

    /**
     * Return a simplified version of the current expression.
     * @return simplified version of the current expression.
     */
    Expression simplify();
}