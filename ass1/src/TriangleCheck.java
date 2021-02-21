package ass1.src;

/**
* @author Yana Sidanych 323537779
* @version 1.0
* @since 2020-03-18 */

/**
 * A triangle checker.
 * The class has a Main function to communicate with the command line.
 * It is implemented using static functions only.
 * Checks the typeof triangle depending on its sides length.
 */
class TriangleCheck {
    /**
     * Recieves three sides of a triangle and prints:
     * 1. "not triangle" if this is not a valid triangle.
     * 2. "triangle" if it is valid.
     * 3. If the triangle is also right angled "right angled!" is also printed.
     * @param sideA Side 1 of the triangle.
     * @param sideB Side 2 of the triangle.
     * @param sideC Side 3 of the triangle.
     */
    public static void performCheck(double sideA, double sideB, double sideC) {
        // Checks if one side is bigger than the sum of the two others, if it is this is not a triangle
        if (sideA + sideB <= sideC || sideA + sideC <= sideB || sideB + sideC <= sideA) {
            System.out.println("not triangle");
            return;
        }

        System.out.println("triangle!");

        // Correcting precision loss in double.
        double epsilon = Math.pow(10, -14);
        // Squaring all sides in order to use pythagoras sentence.
        double aSquared = sideA * sideA;
        double bSquared = sideB * sideB;
        double cSquared = sideC * sideC;

        // Checks if sideA, sideB, sideC is a Pythagorean triplet, with precision loss of epsilon.
        if (Math.abs(aSquared + bSquared - cSquared) < epsilon
        ||  Math.abs(aSquared + cSquared - bSquared) < epsilon
        ||  Math.abs(bSquared + cSquared - cSquared) < epsilon) {
            System.out.println("right angled!");
        }
    }
    /**
     * The Main function.
     * Expects three positive Doubles as arguments (more will be ignored).
     * Calls PerformCheck with given doubles.
     * @param args command line arguments -> Three Doubles expected.
     */
    public static void main(String[] args) {
        // Validates at least 3 arguments are given.
        if (args.length < 3) {
            System.out.println("Invalid input");
            return;
        }

        double sideA = Double.parseDouble(args[0]);
        double sideB = Double.parseDouble(args[1]);
        double sideC = Double.parseDouble(args[2]);

        // Validates all three sides given are valid.
        if (sideA <= 0 || sideB <= 0 || sideC <= 0) {
            System.out.println("Invalid input");
            return;
        }

        performCheck(sideA, sideB, sideC);
    }
}