package ass1.src;

/**
* @author Yana Sidanych 323537779
* @version 1.0
* @since 2020-03-18 */

/**
 * A primes average finder (between 2 and a given number)
 * The class has a Main function to communicate with the command line.
 * It is implemented using static functions only.
 * find the average of all primes between 2 and a given number
 */
class AveragePrimes {
    /**
     * Checks if the given number (num) is prime.
     * @param num Number to check if prime.
     * @return true if number is prime, false if not.
     */
    public static boolean isPrime(int num) {
        // Iterating through all the possible divisors on num.
        for (int i = 2; i <= num / 2; i++) {
            // In the case a divisor was found the number is not prime.
            if (num % i == 0) {
                return false;
            }
        }
        // No divisor was found, the number is prime.
        return true;
    }
    /**
     * Returns the average of all primes between 2 and num.
     * @param num Number to sum up to.
     * @return Average of primes between 2 and num.
     */
    public static double getAveragePrimes(int num) {
        // Sum of the prime numbers between 2 and num
        int sum = 0;
        // Number of prime numbers between 2 and num
        int counter = 0;

        // Iterating through all the numbers between 2 and num.
        for (int i = 2; i <= num; i++) {
            // In the case the number is prime, take it into account.
            if (isPrime(i)) {
                sum += i;
                counter++;
            }
        }
        // Divide the sum by the number of primes found (cast to double as average may not be an integar).
        return (double) sum / counter;
    }

    /**
     * The Main function.
     * Expects one integar (bigger than one) as an argument.
     * Prints the average of all primes up to the given integar.
     * @param args command line arguments -> one argumet expected.
     */
    public static void main(String[] args) {
        // Validates exactly one argument is given.
        if (args.length != 1) {
            System.out.println("Invalid value");
            return;
        }

        int num = Integer.parseInt(args[0]);

        // Validates argument's value is bigger than 1.
        if (num <= 1) {
            System.out.println("Invalid value");
            return;
        }

        System.out.println(getAveragePrimes(num));
    }
}