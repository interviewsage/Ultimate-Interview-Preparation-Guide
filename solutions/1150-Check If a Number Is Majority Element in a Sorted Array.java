// LeetCode Question URL: https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/
// LeetCode Discuss URL:

/**
 * Binary Search
 *
 * Refer:
 * https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/discuss/358130/Java-just-one-binary-search-O(logN))-0ms-beats-100
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        if (nums == null || nums.length == 0 || nums[nums.length / 2] != target) {
            return false;
        }

        int len = nums.length;
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

        return nums[start] == target && (start + len / 2 < len) && nums[start + len / 2] == target;
    }
}
