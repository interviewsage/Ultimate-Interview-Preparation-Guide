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
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        int[] result = new int[] { -1, -1 };
        if (len == 0 || target < nums[0] || target > nums[len - 1]) {
            return result;
        }
        if (len == 1) {
            return new int[2];
        }

        int start = 0;
        int end = len - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        if (nums[start] != target) {
            return result;
        }

        result[0] = start;
        end = len - 1;
        while (start < end) {
            // Infinite loop issue only happens when diff is 1. This we add 1 while
            // calculating mid.
            int mid = start + (end - start + 1) / 2;
            if (nums[mid] > target) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        result[1] = end;
        return result;
    }
}
