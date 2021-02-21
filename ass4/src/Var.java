// ID: 323537779

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class representing Math Variable (like "x", "y", etc.).
 * implements Expression.
 */
class Var implements Expression {

    private String var;

    /**
     * Constructor of the Var class.
     * @param var the variable string representing the Var.
     */
    public Var(String var) {
        this.var = var;
    }

    /**
     * Implementation of the Expression's evaluate function.
     * @param assignment a map of variables to assign vars to.
     * @return the object's evaluated var if possible.
     * @throws Exception When a var correlating with the assignment does not exist.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {

        // Checking if the variable can be assigned. If not throw Exception.
        if (!assignment.containsKey(this.var)) {
            throw new RuntimeException(String.format("assignment does not contain key [%s]", this.var));
        }
        // The var can be assigned, return the var's value.
        return assignment.get(this.var);
    }

    /**
     * Implementation of the Expression's evaluate function.
     * @return never - var is never assigned, and cannot be evaluated.
     * @throws Exception - always - var is never assigned.
     */
    public double evaluate() throws Exception {
        throw new RuntimeException("evaluate must recieve an assigment");
    }

    /**
     * Implementation of the Expression's getVariables function.
     * @return A list containing the variable's var field.
     */
    public List<String> getVariables() {
        // Create a list of size 1.
        List<String> returnList = new ArrayList<String>(1);
        returnList.add(this.var);
        return returnList;
    }

    /**
     * Implementation of the Expression's toString function.
     * @return the objects var field.
     */
    public String toString() {
        return this.var;
    }

    /**
     * Implementation of the Expression's assign function.
     *
     * @param variable a variable to assign.
     * @param expression Expression to replace the variable with.
     * @return expression if the variable can be assigned, if not the object itself.
     */
    public Expression assign(String variable, Expression expression) {
        /* Check if variable correlates with the this.var, if it is, we "replace" var with expression
        by returning expression. if variable does not correlate, return this, as no assignment occurs */
        if (variable.equals(this.var)) {
            return expression;
        }
        return this;
    }

    /**
     * Implementation of the Expression's differentiate function.
     * @param variable variable to differentiate according to.
     * @return Num(0) if variable is not equal to this.var (meaning this.var is like a constant value),
     * Num(1) if it is equal (f(x) = x ---> f'(x) = 1).
     */
    public Expression differentiate(String variable) {
        if (variable.equals(this.var)) {
            return new Num(1);
        }
        return new Num(0);
    }

    /**
     * Implementation of the Expression's simplify function.
     * @return the object itself.
     */
    public Expression simplify() {
        return this;
    }
}