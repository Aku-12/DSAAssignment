import java.util.Arrays;
import java.util.Scanner;

public class PassengerBoardingOptimization {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Setting a sentinel value for continuing or stopping the input loop
        boolean continueInput = true;

        while (continueInput) {
            // User input for the number of passengers
            System.out.print("Enter the number of passengers (or enter 0 to stop): ");
            int n = scanner.nextInt();

            // Checking if the user wants to stop
            if (n == 0) {
                continueInput = false;
                break;
            }

            // User input for the passenger head array
            int[] head = new int[n];
            System.out.print("Enter the passenger sequence: ");
            for (int i = 0; i < n; i++) {
                head[i] = scanner.nextInt();
            }

            // User input for the value of k
            System.out.print("Enter the value of k: ");
            int k = scanner.nextInt();

            // Optimizing the boarding sequence
            int[] optimizedSequence = optimizeBoarding(head, k);
            System.out.println("Optimized Sequence: " + Arrays.toString(optimizedSequence));
        }

        scanner.close();
    }

    public static int[] optimizeBoarding(int[] head, int k) {
        int n = head.length;

        // Loop through the array in chunks of size k
        for (int i = 0; i < n; i += k) {
            int left = i;
            int right = Math.min(i + k - 1, n - 1); // Ensure the right index does not go out of bounds

            // Reversing the elements in the current chunk
            for (; left < right; left++, right--) {
                int temp = head[left];
                head[left] = head[right];
                head[right] = temp;
            }
        }

        return head;
    }
}
