// LeetCode Question URL: https://leetcode.com/problems/delete-and-earn/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Dynamic Programming Solution.
 *
 * Find the possible points for each value and then do the House Robber
 * solution.
 *
 * <pre>
 * delete[i] = noDelete[i-1] + val
 * noDelete[i] = Math.max(delete[i-1], noDelete[i-1])
 *
 * Refer:
 * 1. https://leetcode.com/problems/delete-and-earn/discuss/109895/JavaC++-Clean-Code-with-Explanation
 * 2. https://leetcode.com/problems/delete-and-earn/discuss/109871/Awesome-Python-4-liner-with-explanation-Reduce-to-House-Robbers-Question-U0001f31d
 * </pre>
 *
 * Time Complexity: O(N + Range)
 *
 * Space Complexity: O(Unique Numbers in nums array)
 */
class Solution1 {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> sumMap = new HashMap<>();
        int maxNum = nums[0];
        int minNum = nums[0];
        for (int n : nums) {
            sumMap.put(n, sumMap.getOrDefault(n, 0) + n);
            maxNum = Math.max(maxNum, n);
            minNum = Math.min(minNum, n);
        }

        int delete = 0;
        int noDelete = 0;

        for (int i = minNum; i <= maxNum; i++) {
            Integer val = sumMap.get(i);
            if (val == null) {
                val = 0;
            }

            int prevDelete = delete;
            delete = noDelete + val;
            noDelete = Math.max(prevDelete, noDelete);
        }

        return Math.max(delete, noDelete);
    }
}

class Solution2 {
    public int deleteAndEarn(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        int maxNum = nums[0];
        int minNum = nums[0];
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
            maxNum = Math.max(maxNum, n);
            minNum = Math.min(minNum, n);
        }

        int delete = 0;
        int noDelete = 0;
        boolean numMissing = false;

        for (int i = minNum; i <= maxNum; i++) {
            Integer count = countMap.get(i);
            if (count == null) {
                numMissing = true;
                continue;
            }

            int prevDelete = delete;
            if (numMissing) {
                delete = Math.max(delete, noDelete) + i * count;
            } else {
                delete = noDelete + i * count;
            }
            noDelete = Math.max(prevDelete, noDelete);
            numMissing = false;
        }

        return Math.max(delete, noDelete);
    }
}
