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
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = 0;
        int zeros = 0;

        while (end < nums.length) {
            if (nums[end] == 0) {
                zeros++;
            }
            end++;
            if (zeros > k) {
                if (nums[start] == 0) {
                    zeros--;
                }
                start++;
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
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = 0;
        int zeros = 0;
        int maxCount = 0;

        while (end < nums.length) {
            if (nums[end] == 0) {
                zeros++;
            }
            end++;
            while (zeros > k) {
                if (nums[start] == 0) {
                    zeros--;
                }
                start++;
            }
            maxCount = Math.max(maxCount, end - start);
        }

        return maxCount;
    }
}
