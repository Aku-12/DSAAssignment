import java.util.TreeSet;

public class MovieTheaterSeat {
    public boolean canFriendsSitTogether(int[] nums, int indexDiff, int valueDiff) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        TreeSet<Integer> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) {
            // Finding the smallest number in the set that is >= nums[i] - valueDiff
            Integer ceiling = set.ceiling(nums[i] - valueDiff);
            if (ceiling != null && ceiling <= nums[i] + valueDiff) {
                return true;
            }

            set.add(nums[i]);

            // Maintaining the sliding window
            if (i >= indexDiff) {
                set.remove(nums[i - indexDiff]);
            }
        }

        return false;
    }

    public static void main(String[] args) {
        MovieTheaterSeat m = new MovieTheaterSeat();

        int[] nums = {1, 2, 4, 6, 7};
        int indexDiff = 2;
        int valueDiff = 1;

        System.out.println(m.canFriendsSitTogether(nums, indexDiff, valueDiff)); // Output: true
    }
}
