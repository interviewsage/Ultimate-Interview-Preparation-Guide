// LeetCode Question URL: https://leetcode.com/problems/build-array-from-permutation/
// LeetCode Discuss URL:

/**
 * Using Bit Manipulation
 *
 * Refer:
 * https://leetcode.com/problems/build-array-from-permutation/discuss/1315480/Java-or-O(1)-Space-O(n)-Time
 *
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int[] buildArray(int[] nums) {
        if (nums == null || nums.length > Math.pow(2, 16)) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = nums.length;
        if (len <= 1) {
            return nums;
        }

        for (int i = 0; i < len; i++) {
            nums[i] |= (nums[nums[i]] << 16);
        }

        for (int i = 0; i < len; i++) {
            nums[i] >>>= 16;
        }

        return nums;
    }
}

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/build-array-from-permutation/discuss/1316500/Java-solution-using-O(1)-space-with-explanation
 * 2. https://leetcode.com/problems/build-array-from-permutation/discuss/1315926/Python-O(n)-Time-O(1)-Space-w-Full-Explanation
 * </pre>
 *
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int[] buildArray(int[] nums) {
        if (nums == null || nums.length > Math.pow(2, 16)) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = nums.length;
        if (len <= 1) {
            return nums;
        }

        for (int i = 0; i < len; i++) {
            nums[i] += (nums[nums[i]] % len) * len;
        }

        for (int i = 0; i < len; i++) {
            nums[i] /= len;
        }

        return nums;
    }
}
