// LeetCode Question URL: https://leetcode.com/problems/find-the-middle-index-in-array/
// LeetCode Discuss URL:

/**
 * Two pass using total Sum
 *
 * leftSum = rightSum (Excluding the pivot point)
 *
 * rightSum = TotalSum - leftSum - nums[pivotPoint]
 *
 * Time Complexity: O(2*N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input nums array.
 */
class Solution {
    public int findMiddleIndex(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        if (len == 0) {
            return -1;
        }
        if (len == 1) {
            return 0;
        }

        int totalSum = 0;
        for (int n : nums) {
            totalSum += n;
        }

        int leftSum = 0;
        for (int i = 0; i < len; i++) {
            if (leftSum == totalSum - leftSum - nums[i]) {
                return i;
            }
            leftSum += nums[i];
        }

        return -1;
    }
}
