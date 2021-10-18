// LeetCode Question URL: https://leetcode.com/problems/permutations-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/permutations-ii/discuss/1527937/Java-or-TC:-O(N*N!)-or-SC:-O(N)-or-Recursive-Backtracking-and-Iterative-Solutions

import java.util.*;

/**
 * Recursive Backtracking using countMap.
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
 * Space Complexity: O(N). Recursion stack + countMap + tempList
 *
 * N = Length of input array.
 */
class Solution1 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        permuteUniqueHelper(result, nums.length, countMap, new ArrayList<>());

        return result;
    }

    private void permuteUniqueHelper(List<List<Integer>> result, int inputLength, HashMap<Integer, Integer> countMap,
            List<Integer> tempList) {
        if (tempList.size() == inputLength) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int num : countMap.keySet()) {
            int count = countMap.get(num);
            if (count == 0) {
                continue;
            }
            countMap.put(num, count - 1);
            tempList.add(num);
            permuteUniqueHelper(result, inputLength, countMap, tempList);
            tempList.remove(tempList.size() - 1);
            countMap.put(num, count);
        }
    }
}

/**
 * Iterative
 *
 * Refer for Solution:
 * https://leetcode.com/problems/permutations-ii/discuss/18602/9-line-python-solution-with-1-line-to-handle-duplication-beat-99-of-others-:-)
 *
 * Refer for Explanation:
 * https://leetcode.com/problems/permutations-ii/discuss/18602/9-line-python-solution-with-1-line-to-handle-duplication-beat-99-of-others-:-)/18714
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
    public List<List<Integer>> permuteUnique(int[] nums) {
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

                    // If number just added is same as the number at jth index of cur list. Then we
                    // can break to avoid duplicates.
                    if (j < i && cur.get(j) == nums[i]) {
                        break;
                    }
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
 * Time Complexity: O(N * N! + NlogN). Number of permutations = P(N,N) = N!.
 * Each permutation takes O(N) to construct
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
 * Space Complexity: O(N). Recursion stack + visited array + Sorting + tempList
 *
 * N = Length of input array.
 */
class Solution3 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }

        Arrays.sort(nums);

        permuteUniqueHelper(result, nums, new ArrayList<>(), new boolean[nums.length]);

        return result;
    }

    private void permuteUniqueHelper(List<List<Integer>> result, int[] nums, List<Integer> tempList,
            boolean[] visited) {
        if (tempList.size() == nums.length) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            /**
             * !visited[i - 1] is making sure that duplicate results are not added. Since
             * nums[i-1] and nums[i] are same, nums[i] can only be used if nums[i-1] is
             * currently in use.
             *
             * Refer:
             * https://leetcode.com/problems/permutations-ii/discuss/18594/Really-easy-Java-solution-much-easier-than-the-solutions-with-very-high-vote/18560
             */
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }

            visited[i] = true;
            tempList.add(nums[i]);
            permuteUniqueHelper(result, nums, tempList, visited);
            tempList.remove(tempList.size() - 1);
            visited[i] = false;
        }
    }
}
