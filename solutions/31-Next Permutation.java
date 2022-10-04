// LeetCode Question URL: https://leetcode.com/problems/next-permutation/

/**
 * Refer: 1)
 * https://leetcode.com/problems/next-permutation/discuss/13867/C++-from-Wikipedia
 * 2)
 * https://leetcode.com/articles/next-permutation/#approach-2-single-pass-approach
 *
 * 1. Find the largest index k such that nums[k] < nums[k + 1]. If no such index
 * exists, just reverse nums and done.
 *
 * 2. Find the largest index l > k such that nums[k] < nums[l].
 *
 * 3. Swap nums[k] and nums[l].
 *
 * 4. Reverse the sub-array nums[k + 1:].
 *
 * Time Complexity: O(3 * N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public void nextPermutation(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return;
        }

        int pivot = len - 2;
        while (pivot >= 0 && nums[pivot] >= nums[pivot + 1]) {
            pivot--;
        }

        // Numbers are in increasing order.. like 5, 4, 3, 2, 1
        // just reverse and return.
        if (pivot == -1) {
            reverse(nums, 0);
            return;
        }

        /**
         * <pre>
         *
         * int idx = pivot + 1;
         * while (idx < len && nums[pivot] < nums[idx]) {
         *     idx++;
         * }
         *
         * </pre>
         */

        int idx = len - 1;
        while (nums[pivot] >= nums[idx]) {
            idx--;
        }

        swap(nums, pivot, idx);
        reverse(nums, pivot + 1);
    }

    private void swap(int[] nums, int a, int b) {
        if (a != b) {
            int t = nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }
    }

    private void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
