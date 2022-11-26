// LeetCode Question URL: https://leetcode.com/problems/house-robber/
// LeetCode Discuss URL:

/**
 * DP Iterative Solution
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
 * N = Length of input nums array
 */
class Solution {
    public int rob(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int robHere = 0;
        int notRobHere = 0;
        for (int n : nums) {
            int prevRob = robHere;
            robHere = notRobHere + n;
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
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input nums array
 */
class Solution2 {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int curMax = nums[0];
        int prevMax = 0;

        for (int i = 1; i < nums.length; i++) {
            int temp = curMax;
            curMax = Math.max(prevMax + nums[i], curMax);
            prevMax = temp;
        }

        return curMax;
    }
}
