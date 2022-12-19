// LeetCode Question URL: https://leetcode.com/problems/delete-operation-for-two-strings/
// LeetCode Discuss URL:

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
 * Dynamic Programming
 *
 * Refer:
 * https://leetcode.com/problems/delete-operation-for-two-strings/discuss/103217/Java-DP-Solution-same-as-Edit-Distance/113510
 *
 * Time Complexity: O(min(L1, L2) + L1 * L2)
 *
 * Space Complexity: O(min(L1, L2))
 *
 * L1 = Length string word1. L2 = Length of string word2.
 */
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int l1 = word1.length();
        int l2 = word2.length();

        if (l1 < l2) {
            return minDistance(word2, word1);
        }
        if (l2 == 0) {
            return l1;
        }

        int[] dp = new int[l2 + 1];
        // Setting DP array for when word1 is empty. We have to delete all chars of
        // word2
        for (int j = 1; j <= l2; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= l1; i++) {
            int pre = dp[0]++;
            char w1Char = word1.charAt(i - 1);

            for (int j = 1; j <= l2; j++) {
                int cur = dp[j];

                // Both chars are same, so the distance will also remain same as dp[i-1][j-1]
                if (w1Char == word2.charAt(j - 1)) {
                    dp[j] = pre;
                } else {
                    // Delete l1[i-1] ==> dp[i-1][j] + 1
                    // Delete l2[j-1] ==> dp[i][j-1] + 1
                    dp[j] = Math.min(cur, dp[j - 1]) + 1;
                }

                pre = cur;
            }
        }

        return dp[l2];
    }
}
