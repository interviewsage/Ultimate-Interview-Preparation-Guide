// LeetCode Question URL: https://leetcode.com/problems/search-insert-position/
// LeetCode Discuss URL: https://leetcode.com/problems/search-insert-position/discuss/1529360/Java-or-TC:-O(logN)-or-SC:-O(1)-or-Optimized-Binary-Search-w-Early-Exits

/**
 * Binary Search
 *
 * Refer for Binary Search Basics:
 * https://leetcode.com/problems/search-insert-position/discuss/423166/Binary-Search-101
 *
 * We are trying to find the index of number equal to target or index of number
 * just larger than target.
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums arrays is null");
        }
        int len = nums.length;
        if (len == 0 || nums[0] >= target) {
            return 0;
        }
        if (nums[len - 1] == target) {
            return len - 1;
        }
        if (nums[len - 1] < target) {
            return len;
        }

        /*
         * Here the search space is from 0 to len. Since we have already handled 0 and
         * len in above base conditions, we can reduce the search space to 1 -> len-1.
         */
        int start = 1;
        int end = nums.length - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}

/**
 * Binary Search
 *
 * We are trying to find the index of number equal to target or index of number
 * just larger than target.
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public int searchInsert(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0 || target <= nums[0]) {
            return 0;
        }
        if (target == nums[len - 1]) {
            return len - 1;
        }
        if (target > nums[len - 1]) {
            return len;
        }

        int start = 0;
        int end = len; // We need to include the len index in search range as the number can be
                       // inserted after all the numbers

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (target == nums[mid]) {
                return mid;
            }
            if (target < nums[mid]) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
