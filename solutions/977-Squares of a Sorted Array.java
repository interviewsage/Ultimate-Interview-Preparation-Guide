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

class Solution1 {
    public int[] sortedSquares(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int[] result = new int[len];
        int idx = len - 1;

        while (left <= right) {
            result[idx--] = Math.abs(nums[left]) >= Math.abs(nums[right])
                    ? nums[left] * nums[left++]
                    : nums[right] * nums[right--];
        }

        return result;
    }
}

class Solution2 {
    public int[] sortedSquares(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        int left = 0;
        int right = len - 1;
        int[] result = new int[len];
        int idx = len - 1;

        while (left <= right) {
            if (Math.abs(nums[left]) >= Math.abs(nums[right])) {
                result[idx--] = nums[left] * nums[left++];
            } else {
                result[idx--] = nums[right] * nums[right--];
            }
        }

        return result;
    }
}
