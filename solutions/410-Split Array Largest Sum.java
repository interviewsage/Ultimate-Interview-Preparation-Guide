// LeetCode URL: https://leetcode.com/problems/split-array-largest-sum/

package leetcode;

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
 * than m subarrays or we cannot.
 *
 * If we can, it means that the mid value we pick is too small because we've
 * already tried our best to make sure each part holds as many non-negative
 * numbers as we can but we still have numbers left. So, it is impossible to cut
 * the array into m parts and make sure each parts is no larger than mid. We
 * should increase mid. This leads to l = mid + 1;
 *
 * If we can't, it is either we successfully divide the array into m parts and
 * the sum of each part is less than mid, or we used up all numbers before we
 * reach m. Both of them mean that we should lower mid because we need to find
 * the minimum one. This leads to r = mid;
 *
 * Time Complexity: O(N + N * log(Sum-Max)) = O(N * log S)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array. S = Sum of all numbers in the array.
 */
class Solution {
    public int splitArray(int[] nums, int m) {
        if (nums == null || m <= 0 || nums.length < m) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        int max = nums[0];
        int sum = nums[0];
        for (int i = 1; i < len; i++) {
            max = Math.max(max, nums[i]);
            sum += nums[i];
        }

        if (m == 1) {
            return sum;
        }
        if (m == len) {
            return max;
        }

        int start = max;
        int end = sum;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (isValid(nums, m, mid)) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private boolean isValid(int[] nums, int m, int maxSum) {
        int sum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (sum + nums[i] > maxSum) {
                if (m == 1) {
                    return false;
                }
                m--;
                sum = nums[i];
            } else {
                sum += nums[i];
            }
        }
        return true;
    }
}
