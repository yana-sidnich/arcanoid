// ID: 323537779

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class representing Math constants, such as e (exponent).
 * implements Expression.
 */
final class Const implements Expression {
    // the Exponent constant
    public static final Const EXPONENT = new Const(Math.E, "e");

    private double value;
    private String representation;

    /**
     * Constructor of the Const class.
     * @param value the constant's value.
     * @param representation the constant's string representation.
     */
    private Const(double value, String representation) {
        this.value = value;
        this.representation = representation;
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
        return this.value;
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
     * @return the object's representation
     */
    public String toString() {
        return this.representation;
    }

    /**
     * Implementation of the Expression's assign function.
     * @param var a variable to assign
     * @param expression Expression to replace the variable with.
     * @return self, Const contain no variables
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
