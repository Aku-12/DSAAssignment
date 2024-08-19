public class MaximumHikingSequence {

    public static int findMaxHikingSequence(int[] elevations, int maxElevationGain) {
        int maxSequenceLength = 0; // Tracking the maximum length of the hiking sequence
        int currentSequenceLength = 1; // Tracking the current length of a valid hiking sequence

        for (int i = 1; i < elevations.length; i++) {
            // Checking if the current segment is uphill and within the allowed elevation gain limit
            if (elevations[i] > elevations[i - 1] && elevations[i] - elevations[i - 1] <= maxElevationGain) {
                currentSequenceLength++; // Extend the current sequence length
            } else {
                // Reseting the current sequence length as it's no longer valid
                currentSequenceLength = 1;
            }
            // Updating the maximum sequence length found
            maxSequenceLength = Math.max(maxSequenceLength, currentSequenceLength);
        }

        return maxSequenceLength;
    }

    public static void main(String[] args) {
        int[] trail = {4, 2, 1, 4, 3, 4, 5, 8, 15};
        int maxElevationGain = 3;

        int maxHikingSequence = findMaxHikingSequence(trail, maxElevationGain);
        System.out.println("The longest continuous hiking sequence length is: " + maxHikingSequence); // Expected output: 5
    }
}
