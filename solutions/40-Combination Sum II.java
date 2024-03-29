// LeetCode Question URL: https://leetcode.com/problems/combination-sum-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/combination-sum-ii/discuss/1546439/Java-or-Backtracking-optimized-for-duplicate-candidates-(Detailed-TC-explanation-added)

import java.util.*;

/**
 * Backtracking
 *
 * <pre>
 * Time Complexity:
 * 1. The length of the potential combinations can vary from 1 to k where k = min(T/M , N).
 * 2. Total number of combinations of size k is C(N,k) and time to add each such combination in the result list is O(K).
 * Therefore the total time complexity will be O(1*C(N,1) + 2*C(N,2) + ... + k*C(N,k))
 *                                             = (i = 1 -> k) ∑ (i * C(N, i)).
 * If k = N, then above time complexity becomes O(N * 2^(N-1))
 * Refer https://stackoverflow.com/a/20711498/2905022
 * </pre>
 *
 * Space Complexity: O(min(T/M , N))
 *
 * N = Length of input array. T = Target. M = Minimum value in the input array.
 */
class Solution1 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null) {
            throw new IllegalArgumentException("Input candidates array is null");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0) {
            return result;
        }

        Arrays.sort(candidates);
        combinationSum2Helper(candidates, 0, target, result, new ArrayList<>());
        return result;
    }

    private void combinationSum2Helper(int[] candidates, int start, int target, List<List<Integer>> result,
            List<Integer> tempList) {
        if (target == 0) {
            result.add(new ArrayList<>(tempList));
            // Remove the following return statement if input array can contain negative
            // numbers.
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            // Skipping duplicate numbers.
            if (i > start && candidates[i - 1] == candidates[i]) {
                continue;
            }
            // Remove the following if-block if input array can contain negative numbers.
            if (candidates[i] > target) {
                break;
            }

            tempList.add(candidates[i]);
            combinationSum2Helper(candidates, i + 1, target - candidates[i], result, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}

/**
 * Optimized Backtracking by using a Count Map and Unique Num List
 *
 * Time & Space Complexity explanation is same as above. We will only need to
 * add time & space required for creating the count map and a sorted list of
 * unique candidates.
 */
class Solution2 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates == null) {
            throw new IllegalArgumentException("Input candidates array is null");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (candidates.length == 0) {
            return result;
        }

        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int c : candidates) {
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        List<Integer> uniqueNumList = new ArrayList<>(countMap.keySet());
        Collections.sort(uniqueNumList);

        combinationSum2Helper(countMap, uniqueNumList, 0, target, new ArrayList<>(), result);
        return result;
    }

    private void combinationSum2Helper(HashMap<Integer, Integer> countMap, List<Integer> uniqueNumList, int start,
            int target, List<Integer> tempList, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(tempList));
            // Remove the following return statement if input array can contain negative
            // numbers.
            return;
        }

        for (int i = start; i < uniqueNumList.size(); i++) {
            int num = uniqueNumList.get(i);
            int count = countMap.get(num);
            if (count == 0) {
                continue;
            }
            // Remove the following if-block if input array can contain negative numbers.
            if (num > target) {
                break;
            }

            tempList.add(num);
            countMap.put(num, count - 1);
            combinationSum2Helper(countMap, uniqueNumList, i, target - num, tempList, result);
            tempList.remove(tempList.size() - 1);
            countMap.put(num, count);
        }
    }
}
