// LeetCode Question URL: https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/
// LeetCode Discuss URL:

/**
 * Perform Binary Search twice.
 *
 * Refer:
 * https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/discuss/14699/Clean-iterative-solution-with-two-binary-searches-(with-explanation)
 *
 * Time Complexity: O(2 * log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] range = new int[] { -1, -1 };

        if (nums == null || nums.length == 0) {
            return range;
        }

        int len = nums.length;
        int start = 0;
        int end = len - 1;

        // Finding range start point
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (nums[start] != target) {
            return range;
        }
        range[0] = start;

        end = len - 1;

        // Finding range end point
        while (start < end) {
            int mid = start + (end - start + 1) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        range[1] = start;
        return range;
    }
}
