// LeetCode Question URL: https://leetcode.com/problems/find-all-duplicates-in-an-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/discuss/92387/Java-Simple-Solution
 *
 * For every number i, flip the number at position i-1 to negative. if the
 * number at position i-1 is already negative, i is the number that occurs
 * twice.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return result;
        }

        for (int n : nums) {
            int pos = Math.abs(n) - 1;
            if (nums[pos] < 0) {
                result.add(pos + 1);
            } else {
                nums[pos] *= -1;
            }
        }

        return result;
    }
}

/**
 * Time Complexity: O(3 * N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return result;
        }

        int len = nums.length;

        // Below loop will take worst 2 * N time for the below input
        // [8, 1, 2, 3, 4, 5, 6, 7]. Here when i = 0, while loop will place all numbers
        // at its correct index. For loop will still continue but it will never enter
        // while loop as all numbers are already at correct index.
        for (int i = 0; i < len; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                // Placing nums[i] to its correct index, which is nums[i]-1.
                // Each number will be placed at its correct index once. Thus this loop will
                // never run for infinite time.
                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        for (int i = 0; i < len; i++) {
            if (nums[i] != i + 1) {
                result.add(nums[i]);
            }
        }

        return result;
    }
}
