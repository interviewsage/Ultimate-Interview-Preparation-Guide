// LeetCode Question URL: https://leetcode.com/problems/combinations/
// LeetCode Discuss URL: https://leetcode.com/problems/combinations/discuss/1549641/Java-or-TC:O(K*C(NK))-or-SC:O(K)-or-Optimized-Iterative-and-Backtracking-solutions

import java.util.*;

/**
 * Backtracking
 *
 * <pre>
 * Here we will only recurse on valid nodes / paths that will lead to a valid combination.
 * Time complexity = Time taken to build each combination + Time taken to add each combination in result
 *                 = O(K * C(N,K))    +     O(K * C(N,K))
 *                 = O(2 * K * C(N,K))
 *
 * Space Complexity = O(k) -> Depth of Recursion tree + Size of TempList
 * </pre>
 *
 * N, K -> Input numbers.
 */
class Solution1 {
    public List<List<Integer>> combine(int n, int k) {
        if (n < 0 || k < 0 || n < k) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (n == 0 || k == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        combineHelper(result, n, k, 1, new ArrayList<>());
        return result;
    }

    private void combineHelper(List<List<Integer>> result, int n, int k, int start, ArrayList<Integer> tempList) {
        if (k == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        // We do not need to go beyond n-k+1 as the numbers in each subset will be in
        // ascending order.
        for (int i = start; i <= n - k + 1; i++) {
            tempList.add(i);
            combineHelper(result, n, k - 1, i + 1, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}

/**
 * Backtracking
 *
 * <pre>
 * Here we will only recurse on valid nodes / paths that will lead to a valid combination.
 * Time complexity = Time taken to build each combination + Time taken to add each combination in result
 *                 = O(K * C(N,K))    +     O(K * C(N,K))
 *                 = O(2 * K * C(N,K))
 *
 * Space Complexity = O(k) -> Depth of Recursion tree + Size of TempList
 * </pre>
 *
 * N, K -> Input numbers.
 */
class Solution2 {
    public List<List<Integer>> combine(int n, int k) {
        if (n < 0 || k < 0 || n < k) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (n == 0 || k == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        combineHelper(result, n, k, new ArrayList<>());
        return result;
    }

    private void combineHelper(List<List<Integer>> result, int n, int k, ArrayList<Integer> tempList) {
        if (k == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        // We do not need to go below k as the numbers in each subset will be in
        // decreasing order.
        for (int i = n; i >= k; i--) {
            tempList.add(i);
            combineHelper(result, i - 1, k - 1, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}

/**
 * NO NEED TO SOLVE THIS IN INTERVIEW. BUT REMEMBER FOR EXPLANATION
 *
 * Backtracking
 *
 * <pre>
 * Time complexity = InternalNodes in the RecursionTree   +   K * LeafNodes in RecursionTree
 *                 = (C(N,0) + C(N,1) + ... + C(N,K-1))   +   K * C(N,K)
 *
 * Space Complexity = O(k) -> Depth of Recursion tree + Size of TempList
 * </pre>
 *
 * N, K -> Input numbers.
 */
class Solution3 {
    public List<List<Integer>> combine(int n, int k) {
        if (n < 0 || k < 0 || n < k) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (n == 0 || k == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        combineHelper(result, n, k, new ArrayList<>());
        return result;
    }

    private void combineHelper(List<List<Integer>> result, int n, int k, ArrayList<Integer> tempList) {
        if (k == 0) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = n; i >= 1; i--) {
            tempList.add(i);
            combineHelper(result, i - 1, k - 1, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}

/**
 * Iterative Solution
 *
 * <pre>
 * Here each combination is sorted. Thus it sets the first lowest possible value
 * in the last column. Then it starts filling all possible values in the
 * previous column and so-on.
 * For N = 5, K = 3:
 * 1, 2, 3(Here 3 is lowest possible value for the last (third) column).
 *
 * We also add a N+1 value in the end so that we do not go over N while generating the combinations.
 *
 * 1, 2, 3, 6 --> In next combination we will increment 3 to 4 as 1,2 are in correct place if 3 is in the third column
 * 1, 2, 4, 6 --> Now we can fill 2 & 3 in the column before 4.
 * 1, 3, 4, 6 --> Keep it sorted, we cannot increase 3 to 5. So we move on to first column and increment it to 2.
 * 2, 3, 4, 6 --> Now we have exhausted the possible combinations with 4 in the third column.
 * Now we will increase 4 to 5. And reset the first 2 columns
 * With 5 in the third column, second column can have 2, 3, 4
 * 1, 2, 5, 6 ->
 * 1, 3, 5, 6 -> First C column can take 1, 2
 * 2, 3, 5, 6 -> We cannot increment first column beyond 2 with 3 in second column. So we will increase second column to 4.
 * 1, 4, 5, 6 -> Now first column can have 1, 2, 3
 * 2, 4, 5, 6
 * 3, 4, 5, 6 -> Exhausted all possible combinations. Exit now.
 * </pre>
 *
 * Refer: Approach 2 in Solutions Tab
 * https://leetcode.com/problems/combinations/solution/
 *
 * <pre>
 * Time complexity = 2*K * Total number of possible combinations
 *                 = O(2 * K * C(N, K))
 *
 * Space Complexity = O(K+1) -> Size of TempList
 * </pre>
 *
 * N, K -> Input numbers.
 */
class Solution4 {
    public List<List<Integer>> combine(int n, int k) {
        if (n < 0 || k < 0 || n < k) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        if (n == 0 || k == 0) {
            result.add(new ArrayList<>());
            return result;
        }

        List<Integer> tempList = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            tempList.add(i);
        }
        tempList.add(n + 1);

        int i = 0;
        while (i < k) {
            result.add(new ArrayList<>(tempList.subList(0, k)));
            i = 0;
            while (i < k && tempList.get(i) + 1 == tempList.get(i + 1)) {
                tempList.set(i, i + 1);
                i++;
            }
            tempList.set(i, tempList.get(i) + 1);
        }

        return result;
    }
}
