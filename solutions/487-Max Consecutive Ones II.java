// LeetCode Question URL: https://leetcode.com/problems/max-consecutive-ones-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/max-consecutive-ones-ii/discuss/1508045/Java-or-TC:-O(N)-or-SC:-O(1)-or-Four-solutions-with-Follow-up-handled

/**
 * Keep Track of Previous Count and Current Count
 *
 * This solution CAN handle the follow-up where the input is an infinite stream.
 * We do not need to store the numbers as we are not looking back at the
 * previous values.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array
 */
class Solution1 {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int prevCount = 0;
        int curCount = 0;
        int maxCount = 0;

        for (int n : nums) {
            if (n == 1) {
                curCount++;
            } else {
                // Including the zero number in the count of previous block
                prevCount = curCount + 1;
                curCount = 0;
            }
            maxCount = Math.max(maxCount, prevCount + curCount);
        }

        return maxCount;
    }
}

/**
 * Sliding Window: Keep track of [start, end] window with at most one zero. Also
 * track previous zero index.
 *
 * This solution CAN handle the follow-up where the input is an infinite stream.
 * We do not need to store the numbers as we are not looking back at previous
 * values.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array
 */
class Solution2 {
    public int findMaxConsecutiveOnes(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int prevZeroIdx = -1;
        int maxCount = 0;

        for (int end = 0; end < nums.length; end++) {
            if (nums[end] == 0) {
                start = prevZeroIdx + 1;
                prevZeroIdx = end;
            }
            maxCount = Math.max(maxCount, end - start + 1);
        }

        return maxCount;
    }
}

/**
 * One-Pass Optimized Sliding Window
 *
 * This solution CANNOT handle the follow-up where the input is an infinite
 * stream.
 *
 * <pre>
 * If A[start] ~ A[end] has zeros <= 1, we continue to increment end.
 * (Reason: The window is still valid and window size can be increased.)
 *
 * If A[start] ~ A[end] has zeros > 1, we increment both start & end.
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
class Solution3 {
    public int findMaxConsecutiveOnes(int[] nums) {
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
            if (zeros > 1) {
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
 * This solution CANNOT handle the follow-up where the input is an infinite
 * stream.
 *
 * Time Complexity: O(2*N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array
 */
class Solution4 {
    public int findMaxConsecutiveOnes(int[] nums) {
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
            while (zeros > 1) {
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
