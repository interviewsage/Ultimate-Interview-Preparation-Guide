// LeetCode Question URL: https://leetcode.com/problems/permutations/
// LeetCode Discuss URL: https://leetcode.com/problems/permutations/discuss/1527929/Java-or-TC:-O(N*N!)-or-SC:-O(N)-or-Recursive-Backtracking-and-Iterative-Solutions

import java.util.*;

/**
 * Recursive Backtracking. In this solution passing the index of the nums that
 * needs to be set in the current recursion.
 *
 * Time Complexity: O(N * N!). Number of permutations = P(N,N) = N!. Each
 * permutation takes O(N) to construct
 *
 * <pre>
 * T(n) = n*T(n-1) + O(n)
 * T(n-1) = (n-1)*T(n-2) + O(n-1)
 * ...
 * T(2) = (2)*T(1) + O(2)
 * T(1) = O(N) -> To convert the nums array to ArrayList.
 *
 * Above equations can be added together to get:
 * T(n) = n + n*(n-1) + n*(n-1)*(n-2) + ... + (n....2) + (n....1) * n
 *      = P(n,1) + P(n,2) + P(n,3) + ... + P(n,n-1) + n*P(n,n)
 *      = (P(n,1) + ... + P(n,n)) + (n-1)*P(n,n)
 *      = Floor(e*n! - 1) + (n-1)*n!
 *      = O(N * N!)
 * Refer: https://math.stackexchange.com/questions/2019675/sum-of-permutations-from-0-to-n
 * </pre>
 *
 * Space Complexity: O(N). Recursion stack.
 *
 * N = Length of input array.
 */
class Solution1 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        permutationsHelper(result, nums, 0);

        return result;
    }

    private void permutationsHelper(List<List<Integer>> result, int[] nums, int start) {
        if (start == nums.length - 1) {
            List<Integer> list = new ArrayList<>();
            for (int n : nums) {
                list.add(n);
            }
            result.add(list);
            return;
        }

        for (int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            permutationsHelper(result, nums, start + 1);
            swap(nums, start, i);
        }
    }

    private void swap(int[] nums, int x, int y) {
        int t = nums[x];
        nums[x] = nums[y];
        nums[y] = t;
    }
}

/**
 * Iterative
 *
 * Refer:
 * https://leetcode.com/problems/permutations/discuss/18237/My-AC-simple-iterative-javapython-solution
 *
 * The idea is to add the nth number in every possible position of each
 * permutation of the first n-1 numbers.
 *
 * Time Complexity: O(N * N!). Number of permutations = P(N,N) = N!. Each
 * permutation takes O(N) to construct
 *
 * <pre>
 * T(n) = (x=2->n) ∑ (x-1)!*x(x+1)/2
 *      = (x=1->n-1) ∑ (x)!*x(x-1)/2
 *      = O(N * N!)
 *
 * Refer: https://www.geeksforgeeks.org/sum-series-12-22-nn/
 * </pre>
 *
 * Space Complexity: O((N-1) * (N-1)!) = O(N * N!). All permutations of the
 * first n-1 numbers.
 *
 * N = Length of input array.
 */
class Solution2 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        result.add(Arrays.asList(nums[0]));

        for (int i = 1; i < nums.length; i++) {
            List<List<Integer>> newResult = new ArrayList<>();
            for (List<Integer> cur : result) {
                for (int j = 0; j <= i; j++) {
                    List<Integer> newCur = new ArrayList<>(cur);
                    newCur.add(j, nums[i]);
                    newResult.add(newCur);
                }
            }
            result = newResult;
        }

        return result;
    }
}

/**
 * Recursive Backtracking using visited.
 *
 * Time Complexity: O(N * N!). Number of permutations = P(N,N) = N!. Each
 * permutation takes O(N) to construct
 *
 * <pre>
 * T(n) = n*T(n-1) + O(n)
 * T(n-1) = (n-1)*T(n-2) + O(n)
 * ...
 * T(2) = (2)*T(1) + O(n)
 * T(1) = O(n)
 *
 * Above equations can be added together to get:
 * T(n) = n (1 + n + n*(n-1) + ... + (n....2) + (n....1))
 *      = n (P(n,0) + P(n,1) + P(n,1) + ... + P(n,n-1) + P(n,n))
 *      = n * Floor(e*n!)
 *      = O(N * N!)
 * Refer: https://math.stackexchange.com/questions/2019675/sum-of-permutations-from-0-to-n
 * </pre>
 *
 * Space Complexity: O(N). Recursion stack + visited array
 *
 * N = Length of input array.
 */
class Solution3 {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) {
            return result;
        }

        helper(result, new ArrayList<>(), nums, new boolean[nums.length]);

        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> temp, int[] nums, boolean[] visited) {
        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            temp.add(nums[i]);
            visited[i] = true;

            helper(result, temp, nums, visited);

            visited[i] = false;
            temp.remove(temp.size() - 1);
        }
    }
}
