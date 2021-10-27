// LeetCode Question URL: https://leetcode.com/problems/combination-sum-iii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Backtracking
 *
 * <pre>
 * Time complexity = InternalNodes in the RecursionTree   +   K * LeafNodes in RecursionTree
 *                 = (C(9,0) + C(9,1) + ... + C(9,K-1))   +   K * C(9,K)
 * In our solution, the worst case will happen when k = 8. Then Total Time Complexity = O(574) which is O(1)
 *
 * Space Complexity = O(k) -> Depth of Recursion tree + Size of TempList
 * </pre>
 *
 * K = Input size of each combination.
 */
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();

        if (k <= 0 || k > 9 || n <= 0 || n > 45 || (k == 1 && n > 9) || (k == 9 && n != 45) || (n < k * (k + 1) / 2)) {
            return result;
        }
        if (k == 1) {
            result.add(List.of(n));
            return result;
        }
        if (k == 9) {
            result.add(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));
            return result;
        }

        combinationSum3(1, k, n, new ArrayList<>(), result);
        return result;
    }

    private void combinationSum3(int start, int k, int n, List<Integer> tempList, List<List<Integer>> result) {
        if (k == 0) {
            if (n == 0) {
                result.add(new ArrayList<>(tempList));
            }
            return;
        }

        for (int i = start; i <= 9; i++) {
            if (i > n) {
                break;
            }
            tempList.add(i);
            combinationSum3(i + 1, k - 1, n - i, tempList, result);
            tempList.remove(tempList.size() - 1);
        }
    }
}
