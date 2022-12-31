// LeetCode Question URL: https://leetcode.com/problems/first-missing-positive/
// LeetCode Discuss URL:

/**
 * Put each number at right place.
 *
 * Refer:
 * https://leetcode.com/problems/first-missing-positive/discuss/17071/My-short-c++-solution-O(1)-space-and-O(n)-time
 *
 * Time Complexity: O(N). We visit each number once, and each number will be put
 * in its right place at most once, so it is O(n) + O(n). Although there are two
 * nesting of cyclic (for and while), but its time complexity is still O(n).
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int firstMissingPositive(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Inout nums array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 1;
        }

        // Below loop will take worst 2 * N time for the below input
        // [8, 1, 2, 3, 4, 5, 6, 7]. Here when i = 0, while loop will place all numbers
        // at its correct index. For loop will still continue but it will never enter
        // while loop as all numbers are already at correct index.
        for (int i = 0; i < len; i++) {

            // Numbers <= 0 and > len are excluded from swap.
            while (nums[i] > 0 && nums[i] <= len && nums[i] != nums[nums[i] - 1]) {
                // Placing nums[i] to its correct index, which is nums[i]-1.
                // Each number will be placed at its correct index once. Thus this loop will
                // never run for infinite time.

                int t = nums[i];
                nums[i] = nums[t - 1];
                nums[t - 1] = t;

                // int t = nums[nums[i]-1];
                // nums[nums[i]-1] = nums[i];
                // nums[i] = t;
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return len + 1;
    }
}
