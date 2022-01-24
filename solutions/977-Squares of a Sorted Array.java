// LeetCode Question URL: https://leetcode.com/problems/squares-of-a-sorted-array/
// LeetCode Discuss URL:

/**
 * Two Pointer
 *
 * Refer:
 * https://leetcode.com/problems/squares-of-a-sorted-array/discuss/221922/Java-two-pointers-O(N)
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1) (Excluding the result)
 *
 * N = Length of the input array.
 */
class Solution {
    public int[] sortedSquares(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int[] result = new int[len];
        int resIdx = len - 1;

        while (left <= right) {
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                result[resIdx--] = nums[left] * nums[left];
                left++;
            } else {
                result[resIdx--] = nums[right] * nums[right];
                right--;
            }
        }

        return result;
    }
}
