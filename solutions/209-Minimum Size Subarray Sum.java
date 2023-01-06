// LeetCode Question URL: https://leetcode.com/problems/minimum-size-subarray-sum/
// LeetCode Discuss URL: https://leetcode.com/problems/minimum-size-subarray-sum/discuss/1500877/Java-or-Both-O(N)-and-O(N-logN)-solutions-with-O(1)-space-or-Sliding-Window-and-Binary-Search-solutions

/**
 * This solution only works if there are only positive numbers. If there are
 * negative numbers too, then see solution for
 * https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/
 *
 * Time Complexity: O(N). Each element of array is visited maximum twice.
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0 || target <= 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        int minLen = len + 1;

        while (end < len) {
            target -= nums[end++];

            while (target <= 0) {
                minLen = Math.min(minLen, end - start);
                target += nums[start++];
            }
        }

        // return minLen % (len + 1);
        return minLen == len + 1 ? 0 : minLen;
    }
}

/**
 * This solution uses binary search method on window size and tries to find if a
 * window of size is available in the nums array or not.
 *
 * This solution only works if there are only positive numbers.
 *
 * T(k) = T(k/2) + O(N) ==> T(k) = O(N log(k)). Here k is N. Thus the time
 * complexity will be O(N log(N)).
 *
 * Time Complexity: O(N log(N))
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0 || target <= 0) {
            return 0;
        }

        int start = 1;
        int end = len;
        int minLen = len + 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            int foundWindowSize = doesWindowExists(nums, target, mid);
            if (foundWindowSize > 0) {
                end = foundWindowSize - 1;
                minLen = foundWindowSize;
            } else {
                start = mid + 1;
            }
        }

        // return minLen % (len + 1);
        return minLen == len + 1 ? 0 : minLen;
    }

    private int doesWindowExists(int[] nums, int target, int maxWindowSize) {
        int foundWindowSize = 0;
        for (int i = 0; i < nums.length; i++) {
            target -= nums[i];

            if (i >= maxWindowSize) {
                target += nums[i - maxWindowSize];
            } else {
                foundWindowSize++;
            }

            if (target <= 0) {
                return foundWindowSize;
            }
        }

        return -1;
    }
}
