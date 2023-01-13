// LeetCode Question URL: https://leetcode.com/problems/combination-sum/
// LeetCode Discuss URL: https://leetcode.com/problems/combination-sum/discuss/1546400/Java-or-Backtracking-w-Early-Exit-(Detailed-Time-Complexity-explanation-added)

import java.util.*;

/**
 * In this solution we are sorting the input array, which allows us to early
 * exit from the recursion if the current number is greater than the remaining
 * target.
 *
 * Add the Time & Space Complexity of Sorting to below explanation.
 */

/**
 * Since this question allows same number to be used unlimited times, candidates
 * array cannot have zero and negative numbers. As it will lead to infinite
 * number of combinations. For example: candidates = [-1, 0, 1] and target = 1.
 * This case will result in infinite solutions if same number can be used
 * multiple times.
 *
 * <pre>
 * Time Complexity:
 * This problem can be converted to Combination Sum II. For example, candidates = [2,3,5,6] and target = 12.
 * Then we can create an array = [2,2,2,2,2,2, 3,3,3,3, 5,5, 6,6].
 * Now from this array we can use each number only once in the combination.
 * Therefore total number of elements in this array N' equal to floor(T/n1) + floor(T/n2) + ... + floor(T/nN)
 *
 * 1. The length of the potential combinations can vary from 1 to k where k = T/M.
 * 2. Total number of combinations of size k is C(N',k) and time to add each such combination in the result list is O(K).
 * Therefore the total time complexity will be O(1*C(N',1) + 2*C(N',2) + ... + k*C(N',k))
 *                                             = (i = 1 -> k) ∑ (i * C(N', i)).
 * If k = N', then above time complexity becomes O(N' * 2^(N'-1))
 * Refer https://stackoverflow.com/a/20711498/2905022
 *
 * Space Complexity: O(T/M)
 *
 * N = Length of input array. T = Target. M = Minimum value in the input array.
 * </pre>
 *
 * <pre>
 * Another explanation which is more upper bound
 * Time Complexity:
 * - Here DFS is creating a N-ary Tree. (Since each number can be used multiple times).
 * - Total Number of Nodes in a N-ary Tree of height h = (N^(h+1) - 1) / (N-1)
 * - Number of Non-Leaf (Internal) Nodes = (N^h - 1) / (N-1)
 * - Number of Leaf Nodes = N^h
 *
 * At each non-leaf node we are taking O(1) time to process.
 * At each leaf node we are taking O(h) time to make a copy of the combination.
 *
 * Thus Total Time Complexity = O(h * N^h + (N^h - 1) / (N-1))
 *
 * Here h = Target / Minimum Value in the Candidates Array.
 *
 * Space Complexity: O(target / min value in candidates) -> Excluding the result
 * space. This is used by recursion stack and temp list.
 * </pre>
 *
 * <pre>
 * Another explanation which is incorrect
 * Time Complexity: O(M * N^M)
 * There can be maximum M spaces. Each space can have one of the N values.
 * N = Length of input array. M = target / min value in candidates array.
 * </pre>
 */
class Solution1 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null) {
            throw new IllegalArgumentException("Input candidates array is null");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0 || target <= 0) {
            return result;
        }

        // Only benefit of sorting is that we can break early in the recursion if the
        // current number is greater than the remaining target.
        Arrays.sort(candidates);
        combinationSumHelper(candidates, 0, target, result, new ArrayList<>());
        return result;
    }

    private void combinationSumHelper(int[] candidates, int start, int target, List<List<Integer>> result,
            List<Integer> tempList) {
        if (target == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }

            tempList.add(candidates[i]);
            combinationSumHelper(candidates, i, target - candidates[i], result, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}

/**
 * In this solution we are not sorting the input array. Thus we will have to
 * loop through the whole array.
 *
 * Time & Space Complexity explanation is same as above.
 */
class Solution2 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null || candidates.length == 0 || target <= 0) {
            return result;
        }

        combinationSumHelper(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void combinationSumHelper(int[] candidates, int start, int target, List<Integer> tempList,
            List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > target) {
                continue;
            }
            tempList.add(candidates[i]);
            combinationSumHelper(candidates, i, target - candidates[i], tempList, result);
            tempList.remove(tempList.size() - 1);
        }
    }
}
