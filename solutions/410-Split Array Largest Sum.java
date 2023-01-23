// LeetCode Question URL: https://leetcode.com/problems/split-array-largest-sum/
// LeetCode Discuss URL:

/**
 * Binary Search + Greedy
 *
 * Refer:
 * https://leetcode.com/problems/split-array-largest-sum/discuss/89817/Clear-Explanation:-8ms-Binary-Search-Java
 *
 * The answer is between maximum value of input array numbers and sum of those
 * numbers.
 *
 * Use binary search to approach the correct answer. We have l = max number of
 * array; r = sum of all numbers in the array; Every time we do mid = (l + r) /
 * 2;
 *
 * Use greedy to narrow down left and right boundaries in binary search.
 *
 * 1. Cut the array from left.
 *
 * 2. Try our best to make sure that the sum of numbers between each two cuts
 * (inclusive) is large enough but still less than mid.
 *
 * 3. We'll end up with two results: either we can divide the array into more
 * than k subarrays or we cannot.
 *
 * If we can, it means that the mid value we pick is too small because we've
 * already tried our best to make sure each part holds as many non-negative
 * numbers as we can but we still have numbers left. So, it is impossible to cut
 * the array into k parts and make sure each parts is no larger than mid. We
 * should increase mid. This leads to l = mid + 1;
 *
 * If we can't, it is either we successfully divide the array into k parts and
 * the sum of each part is less than mid, or we used up all numbers before we
 * reach k. Both of them mean that we should lower mid because we need to find
 * the minimum one. This leads to r = mid;
 *
 * Why this works? Why this result sum will exist in the array? --> This is the
 * first point at which this partitioning is possible. Any point before this we
 * will have more than k partitions.
 *
 * Time Complexity: O(N + N * log(Sum-Max)) = O(N * log S)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array. S = Sum of all numbers in the array.
 */
class Solution {
    public int splitArray(int[] nums, int k) {
        if (nums == null || k <= 0 || nums.length < k) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }

        if (k == 1) {
            return sum;
        }
        if (k == len) {
            return max;
        }

        int start = max;
        int end = sum;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (canCutIntoAtMostKSubsets(nums, k, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private boolean canCutIntoAtMostKSubsets(int[] nums, int k, int maxSum) {
        int sum = 0;

        for (int n : nums) {
            if (sum + n > maxSum) {
                if (--k == 0) {
                    return false;
                }
                sum = n;
            } else {
                sum += n;
            }
        }

        return true;
    }
}
