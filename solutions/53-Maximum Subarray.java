// LeetCode Question URL: https://leetcode.com/problems/maximum-subarray/
// LeetCode Discuss URL: https://leetcode.com/problems/maximum-subarray/discuss/1520747/Java-or-TC:-O(N)-or-SC:-O(1)-or-Kadane's-Algorithm-or-Optimal-DP-solution

/**
 * Kadane's Algorithm.
 *
 * <pre>
 * Refer for explanation:
 * 1) https://leetcode.com/problems/maximum-subarray/discuss/20193/DP-solution-and-some-thoughts
 * 2) https://leetcode.com/problems/maximum-subarray/discuss/20211/Accepted-O(n)-solution-in-java
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int maxEndingHere = nums[0];
        int maxSoFar = nums[0];

        for (int i = 1; i < nums.length; i++) {
            /**
             * <pre>
             * maxEndingHere + nums[i] --> Adding the current number to previous SubArray
             * nums[i] -> Starting a new SubArray with just this element. This will be max in case above value is negative or zero
             *
             * maxSubArray(A, i) = maxSubArray(A, i - 1) > 0 ? maxSubArray(A, i - 1) : 0 + A[i];
             * </pre>
             */
            maxEndingHere = Math.max(maxEndingHere + nums[i], nums[i]);
            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }

        return maxSoFar;
    }
}
