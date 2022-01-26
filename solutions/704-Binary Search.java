// LeetCode Question URL: https://leetcode.com/problems/binary-search/
// LeetCode Discuss URL:

/**
 * Refer this post Binary Search 101:
 * https://leetcode.com/problems/binary-search/discuss/423162/Binary-Search-101
 *
 * <pre>
 * Time Complexity:
 * T(N) = T(N/2) + O(1)
 * T(N/2) = T(N/4) + O(1)
 * T(N/4) = T(N/8) + O(1)
 * ...
 * T(2) = T(1) + O(1)
 * T(1) = O(1)
 *
 * This forms a binary tree with log N height.
 * Thus, T(N) = log(N) * O(1) = log(N)
 * </pre>
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
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
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }
}
