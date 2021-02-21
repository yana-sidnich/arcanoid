package ass1.src;

/**
* @author Yana Sidanych 323537779
* @version 1.0
* @since 2020-03-18 */

/**
 * A CHarCOunt.
 * The class has a Main function to communicate with the command line.
 * It is implemented using static functions only.
 * The class uses the last given argument (one charcter) as a seperator,
   and prints first the arguments containing the character, then those who do not.
 */
class CharCount {
    /**
     * Prints the elements in args, first those who contain charToFInd, then those who does not.
     * @param args Arguments to print in order by the existance of the charcter charToFInd.
     * @param size Number of elements to check.
     * @param charToFInd Character to find in args arguments.
     */
    public static void printByOrder(String[] args, int size, char charToFInd) {
        // Creating a temporary string array to contain all the arguments not containg charToFind
        String[] notAppearsArray = new String[size];
        int notAppearsSize = 0;

        // Iterating through the given arguments up to the @size placed element.
        for (int i = 0; i < size; ++i) {
            // Check if character appears in string
            if (args[i].indexOf(charToFInd) == -1) {
                // Inserting the string into notAppearsArray
                notAppearsArray[notAppearsSize] = args[i];
                ++notAppearsSize;
            } else {
                // Print the string if the charToFind appears in it
                System.out.println(args[i]);
            }
        }

        // Print all the strings that charToFind did not appear in.
        for (int i = 0; i < notAppearsSize; ++i) {
            System.out.println(notAppearsArray[i]);
        }
    }
    /**
    * The Main function.
    * Expects 2 or more arguments when the last is a single Character.
    * Calls PrintByOrder.
    * @param args command line arguments -> 2 or more arguments expected.
    */
    public static void main(String[] args) {
        // Validates at lease 2 arguments are given.
        if (args.length < 2) {
            System.out.println("Invalid input");
            return;
        }

        String last = args[args.length - 1];
        // Validates Last argument is a single character.
        if (last.length() != 1) {
            System.out.println("Invalid input");
            return;
        }

        printByOrder(args, args.length - 1 , last.charAt(0));
    }
}