import java.util.HashMap;

public class MovieTheaterSeat {
    // Determining if any two friends can sit together based on the given conditions using a HashMap
    public boolean canFriendsSitTogether(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            for (int key : map.keySet()) {
                if (Math.abs(nums[i] - key) <= valueDiff && Math.abs(i - map.get(key)) <= indexDiff) {
                    return true;
                }
            }

            // Adding the current element and its index to the map
            map.put(nums[i], i);

            // Maintaining the sliding window of size indexDiff
            if (i >= indexDiff) {
                map.remove(nums[i - indexDiff]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MovieTheaterSeat m = new MovieTheaterSeat();

        int[] nums = {1, 2, 4, 6, 7};
        int indexDiff = 2;
        int valueDiff = 1;
        // Checking if any two friends can sit together based on the given conditions
        System.out.println(m.canFriendsSitTogether(nums, indexDiff, valueDiff));
    }
}
