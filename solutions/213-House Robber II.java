// LeetCode Question URL: https://leetcode.com/problems/house-robber-ii/
// LeetCode Discuss URL:

/**
 * DP Iterative Solution
 *
 * Refer:
 * https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation/61023
 *
 * Here we are returning maximum of two cases: 1. Can Rob first house. 2. Cannot
 * rob first house
 *
 * rob[i] = noRob[i-1] + nums[i] = Maximum money robbed from houses 0-i, if
 * robbing house i
 *
 * noRob[i] = Math.max(rob[i-1], noRob[i-1]) = Maximum money robbed from houses
 * 0-i, if not robbing house i
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input nums array.
 */
class Solution {
    public int rob(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        return Math.max(robHelper(nums, 0, len - 2), robHelper(nums, 1, len - 1));
    }

    private int robHelper(int[] nums, int start, int end) {
        int robHere = nums[start];
        int notRobHere = 0;
        for (int i = start + 1; i <= end; i++) {
            int prevRob = robHere;
            robHere = notRobHere + nums[i];
            notRobHere = Math.max(notRobHere, prevRob);
        }
        return Math.max(notRobHere, robHere);
    }
}

/**
 * THIS SOLUTION IS CONFUSING
 *
 * Iterative Solution
 *
 * Refer:
 * https://leetcode.com/problems/house-robber-ii/discuss/59934/Simple-AC-solution-in-Java-in-O(n)-with-explanation/61023
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input nums array.
 */
class Solution2 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        return Math.max(rob(nums, 0, len - 2), rob(nums, 1, len - 1));
    }

    private int rob(int[] nums, int start, int end) {
        int curMax = nums[start];
        int prevMax = 0;
        for (int i = start + 1; i <= end; i++) {
            int temp = curMax;
            curMax = Math.max(curMax, nums[i] + prevMax);
            prevMax = temp;
        }
        return curMax;
    }
}
