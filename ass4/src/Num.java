// ID: 323537779

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class representing a Number.
 * Implements Expression.
 */
class Num implements Expression {

    private double value;

    /**
     * Constructor of the Num class.
     * @param value the number's value
     */
    public Num(double value) {
        this.value = value;
    }

    /**
     * Implementation of the Expression's evaluate function.
     * @param assignment a map of variables to assign values to.
     * @return the object's value.
     * @throws Exception - never.
     */
    public double evaluate(Map<String, Double> assignment) throws Exception {
        return this.value;
    }

    /**
     * Implementation of the Expression's evaluate function.
     * @return the object's value.
     * @throws Exception - never.
     */
    public double evaluate() throws Exception {
        return value;
    }

    /**
     * Implementation of the Expression's getVariables function.
     * @return an empty list.
     */
    public List<String> getVariables() {
        return new ArrayList<String>();
    }

    /**
     * Implementation of the Expression's toString function.
     * @return the object's value as a String.
     */
    public String toString() {
        return Double.toString(this.value);
    }

    /**
     * Implementation of the Expression's assign function.
     * @param var a variable to assign.
     * @param expression Expression to replace the variable with.
     * @return self, Num contain no variables.
     */
    public Expression assign(String var, Expression expression) {
        return this;
    }

    /**
     * Implementation of the Expression's differentiate function.
     * @param var variable to differentiate according to.
     * @return Num(0), as c' = 0 when c is a number.
     */
    public Expression differentiate(String var) {
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