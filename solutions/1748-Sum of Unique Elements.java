// LeetCode Question URL: https://leetcode.com/problems/sum-of-unique-elements/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/sum-of-unique-elements/discuss/1052455/C++-7-Solutions
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(U)
 *
 * N = Length of input array. U = Unique numbers in the array.
 */
class Solution {
    public int sumOfUnique(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        Set<Integer> uniqueNums = new HashSet<>();
        Set<Integer> duplicateNums = new HashSet<>();
        int sum = 0;

        for (int n : nums) {
            if (duplicateNums.contains(n)) {
                continue;
            }

            if (uniqueNums.add(n)) {
                sum += n;
            } else {
                uniqueNums.remove(n);
                sum -= n;
                duplicateNums.add(n);
            }
        }

        return sum;
    }
}
