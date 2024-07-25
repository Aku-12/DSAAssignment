public class SecretRotatingDecorderRing {


    public static String decodeMessage(String s, int[][] shifts) {
        // Converting the input string to a character array for easy manipulation
        char[] message = s.toCharArray();

        // Iterating over each shift operation in the shifts array
        for (int[] shift : shifts) {
            // Extracting the start index, end index, and direction for the current shift
            int start = shift[0];
            int end = shift[1];
            int direction = shift[2];

            // Applying the shift to each character in the specified range
            for (int i = start; i <= end; i++) {
                message[i] = rotateChar(message[i], direction);
            }
        }

        // Converting the modified character array back to a string and return it
        return new String(message);
    }

    // Helper method to rotate a character by one position
    private static char rotateChar(char c, int direction) {
        // If the direction is 1 (clockwise), moving to the next character
        if (direction == 1) {
            return c == 'z' ? 'a' : (char) (c + 1);
        } else { // If the direction is 0 (counter-clockwise), move to the previous character
            return c == 'a' ? 'z' : (char) (c - 1);
        }
    }

    public static void main(String[] args) {
        // Input string to be decoded
        String s = "hell";
        // Array of shifts to be applied to the string
        int[][] shifts = {{0, 1, 1}, {2, 3, 0}, {0, 2, 1}};
        // Printing the decoded message
        System.out.println(decodeMessage(s, shifts));
    }
}
