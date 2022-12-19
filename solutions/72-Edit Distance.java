// LeetCode Question URL: https://leetcode.com/problems/edit-distance/
// LeetCode Discuss URL: https://leetcode.com/problems/edit-distance/discuss/1519242/Java-or-TC:-O(MN)-or-SC:-O(min(MN))-or-Space-Optimized-DP-Solution

/**
 * Similar Questions:
 *
 * 72. Edit Distance: https://leetcode.com/problems/edit-distance/
 *
 * 712. Minimum ASCII Delete Sum for Two Strings:
 * https://leetcode.com/problems/minimum-ascii-delete-sum-for-two-strings/
 *
 * 583. Delete Operation for Two Strings:
 * https://leetcode.com/problems/delete-operation-for-two-strings/
 *
 * 221. Maximal Square: https://leetcode.com/problems/maximal-square/
 */

/**
 * Dynamic Programming:
 *
 * Refer: 1)
 * https://leetcode.com/problems/edit-distance/discuss/25846/C++-O(n)-space-DP
 * 2) For better explanation of logic
 * https://leetcode.com/problems/edit-distance/discuss/25846/C++-O(n)-space-DP/24826
 *
 * dp[i][j] = the minimum number of operations to convert word1[0 .. i-1] to
 * word2[0 .. j-1]
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity: O(min(M, N)).
 *
 * M = Length string word1. N = Length of string word2.
 */
class Solution1 {
    private static final int INSERT_COST = 1;
    private static final int DELETE_COST = 1;
    private static final int REPLACE_COST = 1;

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input string is invalid");
        }

        // Below condition can be used only if Insert & Delete costs are same.
        if (word1.length() < word2.length()) {
            return minDistanceSolverWithCostReversed(word2, word1, DELETE_COST, INSERT_COST);
        } else {
            return minDistanceSolverWithCostReversed(word1, word2, INSERT_COST, DELETE_COST);
        }
    }

    private int minDistanceSolverWithCostReversed(String bigWord, String smallWord, int insertCost, int deleteCost) {
        int l1 = bigWord.length();
        int l2 = smallWord.length();

        if (l2 == 0) {
            return l1 * deleteCost;
        }

        int[] dp = new int[l2 + 1];

        // Setting DP array for when bigWord is empty. We have to insert all chars of
        // smallWord
        for (int j = 1; j <= l2; j++) {
            dp[j] = dp[j - 1] + insertCost;
        }

        for (int i = 1; i <= l1; i++) {
            int pre = dp[0];
            dp[0] += deleteCost; // smallWord is blank, thus deleting this bigWord char.
            char bChar = bigWord.charAt(i - 1);

            for (int j = 1; j <= l2; j++) {
                int cur = dp[j];
                char sChar = smallWord.charAt(j - 1);

                if (bChar == sChar) {
                    // Both chars are same, so the distance will also remain same as dp[i-1][j-1]
                    dp[j] = pre;
                } else {
                    // Replace l1[i - 1] by l2[j - 1] ==> dp[i][j] = dp[i - 1][j - 1] + 1
                    // Delete l1[i-1] from l1[0..i-1] ==> dp[i-1][j] + 1
                    // Insert l2[j-1] into l1[0..i-1] ==> dp[i][j-1] + 1
                    dp[j] = Math.min(dp[j - 1] + insertCost, Math.min(pre + REPLACE_COST, cur + deleteCost));
                }

                pre = cur;
            }
        }

        return dp[l2];
    }
}

class Solution2 {
    private static final int INSERT_COST = 1;
    private static final int DELETE_COST = 1;
    private static final int REPLACE_COST = 1;

    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input string is invalid");
        }

        int l1 = word1.length();
        int l2 = word2.length();

        // Below condition can be used only if Insert & Delete costs are same.
        if (l1 < l2) {
            return minDistance(word2, word1);
        }
        if (l2 == 0) {
            return l1 * DELETE_COST;
        }

        int[] dp = new int[l2 + 1];

        // Setting DP array for when word1 is empty. We have to insert all chars of
        // word2
        for (int j = 1; j <= l2; j++) {
            dp[j] = dp[j - 1] + INSERT_COST;
        }

        for (int i = 1; i <= l1; i++) {
            int pre = dp[0];
            dp[0] += DELETE_COST; // word2 is blank, thus deleting this word1 char.
            char w1Char = word1.charAt(i - 1);

            for (int j = 1; j <= l2; j++) {
                int cur = dp[j];
                char w2Char = word2.charAt(j - 1);

                if (w1Char == w2Char) {
                    // Both chars are same, so the distance will also remain same as dp[i-1][j-1]
                    dp[j] = pre;
                } else {
                    // Replace l1[i - 1] by l2[j - 1] ==> dp[i][j] = dp[i - 1][j - 1] + 1
                    // Delete l1[i-1] from l1[0..i-1] ==> dp[i-1][j] + 1
                    // Insert l2[j-1] into l1[0..i-1] ==> dp[i][j-1] + 1
                    dp[j] = Math.min(dp[j - 1] + INSERT_COST, Math.min(pre + REPLACE_COST, cur + DELETE_COST));
                }

                pre = cur;
            }
        }

        return dp[l2];
    }
}

class SolutionIgnore {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input strings are null");
        }

        int insertCost = 1;
        int deleteCost = 1;
        int replaceCost = 1;
        int l1 = word1.length();
        int l2 = word2.length();

        if (l1 == 0) {
            return l2 * insertCost;
        }
        if (l2 == 0) {
            return l1 * deleteCost;
        }
        if (l2 < l1) {
            // return solveIfWord2IsSmall(word1, word2, insertCost, deleteCost,
            // replaceCost);

            // return minDistanceSolver(word2, word1, false, insertCost, deleteCost,
            // replaceCost);

            return minDistanceSolverWithCostReversed(word2, word1, deleteCost, insertCost, replaceCost);
            // return minDistance(word2, word1); // --> This line will only work if all
            // three costs are same.
        } else {
            // return solveIfWord1IsSmall(word1, word2, insertCost, deleteCost,
            // replaceCost);

            // return minDistanceSolver(word1, word2, true, insertCost, deleteCost,
            // replaceCost);

            return minDistanceSolverWithCostReversed(word1, word2, insertCost, deleteCost, replaceCost);
        }

    }

    private int minDistanceSolverWithCostReversed(String small, String big, int updatedInsertCost,
            int updatedDeleteCost, int replaceCost) {

        int sLen = small.length();
        int bLen = big.length();
        int[] dp = new int[sLen + 1];

        for (int i = 1; i <= sLen; i++) {
            dp[i] = dp[i - 1] + updatedDeleteCost;
        }

        for (int j = 1; j <= bLen; j++) {
            int prev = dp[0];
            dp[0] += updatedInsertCost;
            char c2 = big.charAt(j - 1);
            for (int i = 1; i <= sLen; i++) {
                char c1 = small.charAt(i - 1);
                int temp = dp[i];
                if (c1 == c2) {
                    dp[i] = prev;
                } else {
                    dp[i] = Math.min(prev + replaceCost,
                            Math.min(dp[i - 1] + updatedDeleteCost, dp[i] + updatedInsertCost));
                }
                prev = temp;
            }
        }
        return dp[sLen];
    }

    private int minDistanceSolver(String small, String big, boolean isWord1Smaller, int insertCost, int deleteCost,
            int replaceCost) {

        int sLen = small.length();
        int bLen = big.length();
        int[] dp = new int[sLen + 1];

        for (int i = 1; i <= sLen; i++) {
            dp[i] = dp[i - 1] + (isWord1Smaller ? deleteCost : insertCost);
        }

        for (int j = 1; j <= bLen; j++) {
            int prev = dp[0];
            dp[0] += (isWord1Smaller ? insertCost : deleteCost);
            char c2 = big.charAt(j - 1);
            for (int i = 1; i <= sLen; i++) {
                char c1 = small.charAt(i - 1);
                int temp = dp[i];
                if (c1 == c2) {
                    dp[i] = prev;
                } else {
                    dp[i] = Math.min(prev + replaceCost,
                            Math.min(dp[i - 1] + (isWord1Smaller ? deleteCost : insertCost),
                                    dp[i] + (isWord1Smaller ? insertCost : deleteCost)));
                }
                prev = temp;
            }
        }
        return dp[sLen];
    }

    private int solveIfWord1IsSmall(String word1, String word2, int insertCost, int deleteCost, int replaceCost) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[] dp = new int[l1 + 1];
        for (int i = 1; i <= l1; i++) {
            dp[i] = dp[i - 1] + deleteCost;
        }

        for (int j = 1; j <= l2; j++) {
            int prev = dp[0];
            dp[0] += insertCost;
            char c2 = word2.charAt(j - 1);
            for (int i = 1; i <= l1; i++) {
                char c1 = word1.charAt(i - 1);
                int temp = dp[i];
                if (c1 == c2) {
                    dp[i] = prev;
                } else {
                    dp[i] = Math.min(prev + replaceCost, Math.min(dp[i - 1] + deleteCost, dp[i] + insertCost));
                }
                prev = temp;
            }
        }
        return dp[l1];
    }

    private int solveIfWord2IsSmall(String word1, String word2, int insertCost, int deleteCost, int replaceCost) {
        int l1 = word1.length();
        int l2 = word2.length();
        int[] dp = new int[l2 + 1];
        for (int i = 1; i <= l2; i++) {
            dp[i] = dp[i - 1] + insertCost;
        }

        for (int j = 1; j <= l1; j++) {
            int prev = dp[0];
            dp[0] += deleteCost;
            char c2 = word1.charAt(j - 1);
            for (int i = 1; i <= l2; i++) {
                char c1 = word2.charAt(i - 1);
                int temp = dp[i];
                if (c1 == c2) {
                    dp[i] = prev;
                } else {
                    dp[i] = Math.min(prev + replaceCost, Math.min(dp[i - 1] + insertCost, dp[i] + deleteCost));
                }
                prev = temp;
            }
        }
        return dp[l2];
    }
}
