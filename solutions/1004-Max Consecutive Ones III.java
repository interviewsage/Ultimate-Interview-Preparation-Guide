// LeetCode Question URL: https://leetcode.com/problems/max-consecutive-ones-iii/
// LeetCode Discuss URL: https://leetcode.com/problems/max-consecutive-ones-iii/discuss/1508044/Java-or-TC:-O(N)-or-SC:-O(1)-or-One-Pass-Optimized-Sliding-Window

/**
 * One-Pass Optimized Sliding Window
 *
 * <pre>
 * If A[start] ~ A[end] has zeros <= K, we continue to increment end.
 * (Reason: The window is still valid and window size can be increased.)
 *
 * If A[start] ~ A[end] has zeros > K, we increment both start & end.
 * (Reason: The window contains extra zeros thus it is not valid any more, and we
 * will increment both start & end to keep the window size same.)
 *
 * Refer:
 * 1) https://leetcode.com/problems/max-consecutive-ones-iii/solution/
 * 2) https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/JavaC++Python-Sliding-Window
 * 3) https://leetcode.com/problems/max-consecutive-ones-iii/discuss/247564/JavaC++Python-Sliding-Window/326294
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array
 */
class Solution1 {
    public int longestOnes(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len <= k) {
            return len;
        }

        int start = 0;
        int end = 0;

        while (end < len) {
            if (nums[end++] == 0) {
                k--;
            }
            if (k < 0) {
                if (nums[start++] == 0) {
                    k++;
                }
            }
        }

        return end - start;
    }
}

/**
 * Sliding Window: Keep track of [start, end] window with at most one zero.
 *
 * Time Complexity: O(2*N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array
 */
class Solution2 {
    public int longestOnes(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len <= k) {
            return len;
        }

        int start = 0;
        int end = 0;
        int result = 0;

        while (end < len) {
            if (nums[end++] == 0) {
                k--;
            }
            while (k < 0) {
                if (nums[start++] == 0) {
                    k++;
                }
            }
            result = Math.max(result, end - start);
        }

        return result;
    }
}

/**
 * Another way to do the first solution.
 */
class Solution3 {
    public int longestOnes(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len <= k) {
            return len;
        }

        int start = 0;
        int end = 0;

        for (; end < len; end++) {
            if (nums[end] == 0) {
                k--;
            }
            if (k < 0) {
                if (nums[start++] == 0) {
                    k++;
                }
            }
        }

        return end - start;
    }
}
