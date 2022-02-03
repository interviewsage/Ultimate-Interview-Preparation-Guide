// LeetCode Question URL: https://leetcode.com/problems/sum-of-unique-elements/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/sum-of-unique-elements/discuss/1052455/C++-7-Solutions
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input array.
 */
class Solution {
    public int sumOfUnique(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        Set<Integer> nonUniqueNums = new HashSet<>();
        Set<Integer> uniqueNums = new HashSet<>();
        int sum = 0;

        for (int n : nums) {
            if (nonUniqueNums.contains(n)) {
                continue;
            }

            if (uniqueNums.add(n)) {
                sum += n;
            } else {
                uniqueNums.remove(n);
                sum -= n;
                nonUniqueNums.add(n);
            }
        }

        return sum;
    }
}
