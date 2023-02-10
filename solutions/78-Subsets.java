// LeetCode Question URL: https://leetcode.com/problems/subsets/
// LeetCode Discuss URL: https://leetcode.com/problems/subsets/discuss/1549657/Java-or-TC:-O(N*2N)-or-SC:-O(1)-or-Constant-Space-Iterative-and-Backtracking-solutions

import java.util.*;

/**
 * Backtracking (Recursion)
 *
 * <pre>
 * S(n) = (0 × (n C 0) + 1 × (n C 1) + 2 × (n C 2) + … + n × (n C n))
 * Note that (n C k) = (n C n-k). Therefore:
 * S(n) = 0 × (n C n) + 1 × (n C n-1) + 2 × (n C n-2) + … + n × (n C 0)
 * If we add these two together, we get
 * 2S(n) = n × (n C 0) + n × (n C 1) + … + n × (n C n)
 *       = n × (n C 0 + n C 1 + … + n C n)
 *
 * As per binomial theorem, (n C 0 + n C 1 + … + n C n) = 2^n,
 * Here we have 2 options for each number. Either we can include the number or exclude the number.
 * Thus (n C 0 + n C 1 + … + n C n) = 2^n
 *
 * Thus, :
 * 2*S(n) = n * 2^n => S(n) = n * 2^(n-1)
 *
 * Refer https://stackoverflow.com/a/20711498
 * </pre>
 *
 * Time Complexity: O(S(N) + n C 0) = O(N * 2^(N-1) + 1) = O(N * 2^N)
 *
 * Space Complexity: O(N) (Recursion Depth + TempList)
 *
 * N = Length of input nums array
 */
class Solution1 {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        List<List<Integer>> result = new ArrayList<>();
        subsetsHelper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private void subsetsHelper(int[] nums, int start, List<Integer> cur, List<List<Integer>> result) {
        result.add(new ArrayList<>(cur));

        for (int i = start; i < nums.length; i++) {
            cur.add(nums[i]);
            subsetsHelper(nums, i + 1, cur, result);
            cur.remove(cur.size() - 1);
        }
    }
}

/**
 * Constant Space Iterative Solution
 *
 * Time Complexity: O(N * 2 ^ N) Refer to above explanation
 *
 * Space Complexity: O(1) (Excluding the result space)
 *
 * N = Length of input nums array
 */
class Solution2 {
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());

        for (int n : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> cur = new ArrayList<>(result.get(i));
                cur.add(n);
                result.add(cur);
            }
        }

        return result;
    }
}
