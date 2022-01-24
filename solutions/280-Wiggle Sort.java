// LeetCode Question URL: https://leetcode.com/problems/wiggle-sort/
// LeetCode Discuss URL:

/**
 * Refer: https://leetcode.com/problems/wiggle-sort/solution/
 *
 * Compare ith index with (i+1)th index.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        for (int i = 0; i < nums.length - 1; i++) {
            if (i % 2 == 0) {
                // Even Index numbers are smaller or equal to its neighbors.
                if (nums[i] > nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            } else {
                // Even Index numbers are larger or equal to its neighbors.
                if (nums[i] < nums[i + 1]) {
                    swap(nums, i, i + 1);
                }
            }

            // boolean isEven = i % 2 == 0;
            // if ((isEven && nums[i] > nums[i+1]) || (!isEven && nums[i] < nums[i+1])) {
            // swap(nums, i, i+1);
            // }
        }
    }

    private void swap(int[] nums, int a, int b) {
        if (a != b && nums[a] != nums[b]) {
            int t = nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }
    }
}
