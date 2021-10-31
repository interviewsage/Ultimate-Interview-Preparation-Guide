// LeetCode Question URL: https://leetcode.com/problems/combination-sum-iv/
// LeetCode Discuss URL: https://leetcode.com/problems/combination-sum-iv/discuss/1546467/Java-or-TC:-O(N*T)-or-SC:-O(T)-or-DP-(BottomUp-and-TopDown)-w-FollowUp

import java.util.*;

/**
 * Dynamic Programming Bottom Up - Iterative Approach
 *
 * Refer:
 * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
 *
 * DP[i] = Number of ways to achieve target i using all possible numbers in the
 * nums array.
 *
 * DP[i] = (j = 0 -> N-1) ∑ DP[i-nums[j]]. where i-nums[j] >= 0. DP[0] = 1
 *
 * Time Complexity: O(N * T)
 *
 * Space Complexity: O(T)
 *
 * N = Length of input array. T = Input target.
 */
class Solution1 {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }

        int[] dp = new int[target + 1];
        dp[0] = 1;

        for (int i = 1; i <= target; i++) {
            for (int n : nums) {
                if (i >= n) {
                    dp[i] += dp[i - n];
                }
            }
        }

        return dp[target];
    }
}

/**
 * Dynamic Programming Top Down - Recursive Approach
 *
 * Refer:
 * https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation
 *
 * Time Complexity: O(N * T)
 *
 * Space Complexity: O(T)
 *
 * N = Length of input array. T = Input target.
 */
class Solution2 {
    public int combinationSum4(int[] nums, int target) {
        if (nums == null || nums.length == 0 || target <= 0) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        return combinationSum4Helper(nums, target, map);
    }

    private int combinationSum4Helper(int[] nums, int target, HashMap<Integer, Integer> map) {
        if (map.containsKey(target)) {
            return map.get(target);
        }

        int count = 0;
        for (int n : nums) {
            if (target >= n) {
                count += combinationSum4Helper(nums, target - n, map);
            }
        }

        map.put(target, count);
        return count;
    }
}

/**
 * This solution is for Follow-Up where both Negative And Positive Numbers can
 * be given in the input nums array. Since we can have infinite length
 * combinations, we will restrict the search by setting a maximum length of the
 * combinations.
 *
 * Dynamic Programming Top Down - Recursive Approach
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/combination-sum-iv/discuss/85038/JAVA:-follow-up-using-recursion-and-memorization.
 * 2) (Incorrect. But Good Idea) https://leetcode.com/problems/combination-sum-iv/discuss/85041/7-liner-in-Python-and-follow-up-question
 * 3) https://leetcode.com/problems/combination-sum-iv/discuss/85036/1ms-Java-DP-Solution-with-Detailed-Explanation/153608
 * </pre>
 *
 * <pre>
 * DP(T,l) = Maximum number of ways T can be achieved in length from 1 to l
 * </pre>
 *
 * Time Complexity: O(N * M * T)
 *
 * Space Complexity: O(T * M)
 *
 * N = Length of input array. T = Input target. M = Max Length of the
 * permutation
 */
class SolutionFollowUp {
    public int combinationSum4(int[] nums, int target) {
        int min = nums[0];
        for (int n : nums) {
            min = Math.min(min, n);
        }
        return combinationSum4NegativePositiveNums(nums, target, target / min);
    }

    public int combinationSum4NegativePositiveNums(int[] nums, int target, int maxLen) {
        if (nums == null || nums.length == 0 || maxLen <= 0) {
            return 0;
        }
        HashMap<Integer, HashMap<Integer, Integer>> map = new HashMap<>();
        return combinationSum4NegativePositiveNumsHelper(target, map, nums, maxLen, maxLen);
    }

    private int combinationSum4NegativePositiveNumsHelper(int target, HashMap<Integer, HashMap<Integer, Integer>> map,
            int[] nums, int length, int maxLen) {
        if (length < 0) {
            return 0;
        }
        if (length == 0) {
            return target == 0 ? 1 : 0;
        }
        if (map.containsKey(target) && map.get(target).containsKey(length)) {
            return map.get(target).get(length);
        }

        int count = 0;
        if (target == 0 && length != maxLen) {
            count++;
        }
        for (int n : nums) {
            count += combinationSum4NegativePositiveNumsHelper(target - n, map, nums, length - 1, maxLen);
        }

        if (!map.containsKey(target)) {
            map.put(target, new HashMap<>());
        }
        map.get(target).put(length, count);
        return count;
    }
}
