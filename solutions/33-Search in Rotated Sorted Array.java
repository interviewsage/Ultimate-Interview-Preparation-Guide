// LeetCode Question URL: https://leetcode.com/problems/search-in-rotated-sorted-array/
// LeetCode Discuss URL: https://leetcode.com/problems/search-in-rotated-sorted-array/discuss/1529302/Java-or-TC:-O(logN)-or-SC:-O(1)-or-Modified-Binary-Search-optimal-solution

/**
 * Modified binary search. This solution cannot handle duplicates in the input
 * array.
 *
 * Refer to part II
 * (https://leetcode.com/problems/search-in-rotated-sorted-array-ii/) to handle
 * duplicates
 *
 * Time Complexity: O(log N) --> Search space is reduced by half in every
 * iteration.
 *
 * Space Complexity: O(1)
 *
 * N = length of input array.
 */
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }

            // Left side is sorted and Right side is unsorted.
            if (nums[start] <= nums[mid]) {
                if (target >= nums[start] && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // Left side is unsorted and Right side is sorted.
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return -1;
    }
}
