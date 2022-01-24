// LeetCode Question URL: https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
 *
 * The basic idea is that we iterate through the input array and mark elements
 * as negative using nums[nums[i] -1] = -nums[nums[i]-1]. In this way all the
 * numbers that we have seen will be marked as negative. In the second
 * iteration, if a value is not marked as negative, it implies we have never
 * seen that index before, so just add it to the return list.
 *
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length <= 1) {
            return result;
        }

        for (int n : nums) {
            int pos = Math.abs(n) - 1;
            if (nums[pos] > 0) {
                nums[pos] *= -1;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                result.add(i + 1);
            } else {
                nums[i] *= -1;
            }
        }

        return result;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/1583736/C++Python-All-6-Solutions-w-Explanations-or-Binary-Search+-Hashset-+-2x-O(1)-Space-Approach
 *
 * This solution involves placing all possible elements at their right index
 * place. By that, I mean every possible index i should be occupied by correct
 * element i+1, i.e, num[i] = i+1. This allows us to check if a number j from
 * range [1, n] exists in nums or not.
 *
 * Time Complexity: O(3 * N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
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
                result.add(i + 1);
            }
        }

        return result;
    }
}
