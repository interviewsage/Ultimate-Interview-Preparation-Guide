// LeetCode Question URL: https://leetcode.com/problems/jump-game/
// LeetCode Discuss URL:

/**
 * Greedy Solution
 *
 * Going forwards. `reach` tells the maximum index we can reach so far.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        // `reach` tells the maximum index we can reach so far.
        int reach = nums[0];
        if (reach >= len - 1) {
            return true;
        }
        if (len > 1 && reach == 0) {
            // Since first element is zero, we cannot reach the end.
            return false;
        }

        int i = 1;
        while (i <= reach && reach < len - 1) {
            reach = Math.max(reach, i + nums[i]);
            i++;
        }

        return reach >= len - 1;
    }
}

class Solution2 {
    public boolean canJump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return false;
        }

        int len = nums.length;
        if (nums[0] >= len - 1) {
            return true;
        }
        if (len > 1 && nums[0] == 0) {
            // Since first element is zero, we cannot reach the end.
            return false;
        }

        // `reach` tells the maximum index we can reach so far.
        int range = 0;

        for (int i = 0; i <= Math.min(len - 1, range); i++) {
            range = Math.max(range, i + nums[i]);
            if (range >= len - 1) {
                return true;
            }
        }

        return false;
    }
}
