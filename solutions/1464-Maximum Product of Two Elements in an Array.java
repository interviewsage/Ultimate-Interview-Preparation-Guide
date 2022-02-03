// LeetCode Question URL: https://leetcode.com/problems/maximum-product-of-two-elements-in-an-array/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity; O(1)
 */
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int max1 = nums[0];
        int max2 = Integer.MIN_VALUE;

        int min1 = nums[0];
        int min2 = Integer.MAX_VALUE;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max1) {
                max2 = max1;
                max1 = nums[i];
            } else if (nums[i] > max2) {
                max2 = nums[i];
            }

            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (nums[i] < min2) {
                min2 = nums[i];
            }
        }

        return Math.max((max1 - 1) * (max2 - 1), (min1 - 1) * (min2 - 1));
    }
}
