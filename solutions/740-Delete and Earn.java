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
        if (nums == null) {
            throw new IllegalArgumentException("Input nums arrays is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return len == 0 ? 0 : nums[0];
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

        for (int i = minNum; i <= maxNum; i++) {
            int preDelete = delete;
            delete = noDelete + countMap.getOrDefault(i, 0) * i;
            noDelete = Math.max(preDelete, noDelete);
        }

        return Math.max(delete, noDelete);
    }
}

class Solution2 {
    public int deleteAndEarn(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums arrays is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return len == 0 ? 0 : nums[0];
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        List<Integer> uniqueNums = new ArrayList<>(countMap.keySet());
        Collections.sort(uniqueNums);
        int preNum = uniqueNums.get(0);
        int delete = preNum * countMap.get(preNum);
        int noDelete = 0;

        for (int i = 1; i < uniqueNums.size(); i++) {
            int n = uniqueNums.get(i);
            int preDelete = delete;

            if (uniqueNums.get(i - 1) == n - 1) {
                delete = noDelete + countMap.get(n) * n;
            } else {
                delete = Math.max(preDelete, noDelete) + countMap.get(n) * n;
            }

            noDelete = Math.max(preDelete, noDelete);
        }

        return Math.max(delete, noDelete);
    }
}

/**
 * Further Thoughts: Combined solution to get best of both solutions
 * https://leetcode.com/problems/delete-and-earn/solution/
 */
