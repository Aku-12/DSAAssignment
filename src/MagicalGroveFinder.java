class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

public class MagicalGroveFinder {
    public static void main(String[] args) {
        // Example tree: [1, 4, 3, 2, 4, 2, 5, null, null, null, null, null, null, 4, 6]
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(4);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(5);
        root.right.right.left = new TreeNode(4);
        root.right.right.right = new TreeNode(6);

        MagicalGroveFinder finder = new MagicalGroveFinder();
        int maxSum = finder.largestMagicalGrove(root);

        System.out.println("Largest Magical Grove Sum: " + maxSum);
    }

    public int largestMagicalGrove(TreeNode root) {
        if (root == null) return 0;

        // Use an array to hold the maximum sum found so far (passing by reference)
        int[] maxSum = new int[1];
        maxSum[0] = Integer.MIN_VALUE;

        // Recursively find the maximum sum of BST subtrees
        findMaxSum(root, maxSum);

        return maxSum[0];
    }

    private Result findMaxSum(TreeNode node, int[] maxSum) {
        if (node == null) {
            // Return a result with true (null node is considered a valid BST) and sum of 0
            return new Result(true, 0, 0, 0);
        }

        // Recursively find results for left and right subtrees
        Result leftResult = findMaxSum(node.left, maxSum);
        Result rightResult = findMaxSum(node.right, maxSum);

        // Check if current node forms a BST using results from left and right subtrees
        if (leftResult.isValid && rightResult.isValid &&
                (node.left == null || node.val > leftResult.maxValue) &&
                (node.right == null || node.val < rightResult.minValue)) {

            // Calculate the sum of the current BST subtree
            int currentSum = node.val + leftResult.sum + rightResult.sum;

            // Update maxSum if currentSum is larger
            maxSum[0] = Math.max(maxSum[0], currentSum);

            // Return a result indicating the current node forms a valid BST,
            // with the minimum and maximum values in this subtree, and the sum of values
            return new Result(true,
                    Math.min(node.val, leftResult.minValue),
                    Math.max(node.val, rightResult.maxValue),
                    currentSum);
        } else {
            // If the current node does not form a BST, return a result with isValid false
            return new Result(false, 0, 0, 0);
        }
    }

    private class Result {
        boolean isValid;
        int minValue;
        int maxValue;
        int sum;

        Result(boolean isValid, int minValue, int maxValue, int sum) {
            this.isValid = isValid;
            this.minValue = minValue;
            this.maxValue = maxValue;
            this.sum = sum;
        }
    }
}
