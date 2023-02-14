// LeetCode Question URL: https://leetcode.com/problems/unique-binary-search-trees/
// LeetCode Discuss URL:

/**
 * Dynamic Programming
 *
 * Refer to this link for awesome explanation:
 * https://leetcode.com/problems/unique-binary-search-trees/discuss/31666/DP-Solution-in-6-lines-with-explanation.-F(i-n)-G(i-1)-*-G(n-i)
 *
 * Refer to explanation for empty trees:
 * https://1drv.ms/u/s!As-WcqEPhQRDgrh0xekUn4t2PNrzPg?e=Iyz8TC
 *
 * G(n): the number of unique BST for a sequence of length n.
 *
 * F(i, n), 1 <= i <= n: the number of unique BST, where the number i is the
 * root of BST, and the sequence ranges from 1 to n.
 *
 * G(n) = F(1, n) + F(2, n) + ... + F(n, n).
 *
 * G(0)=1, G(1)=1.
 *
 * F(i, n) = G(i-1) * G(n-i) 1 <= i <= n
 *
 * G(n) = G(0) * G(n-1) + G(1) * G(n-2) + … + G(n-1) * G(0)
 *
 * Time Complexity: O(2 * n/2 * (n/2 + 1) / 2) = O(N^2)
 *
 * Space Complexity: O(N)
 *
 * N = Input number n.
 */
class Solution {
    public int numTrees(int n) {
        // Checking for Invalid Input
        if (n < 0) {
            throw new IllegalArgumentException("Input number of nodes in invalid");
        }
        // For n == 0, empty tree is a valid BST.
        // For n == 1, valid BST can have only one node.
        if (n <= 1) {
            return 1;
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            // We only need to do half as dp[i] is symmetrical.
            // For example, when i = 5:
            // dp[i] = dp[0]*dp[4] + dp[1]*dp[3] + dp[2]*dp[2] + dp[3]*dp[1] + dp[4]*dp[0]
            // Here except dp[2]*dp[2] all others are appearing twice.
            for (int j = 0; j < i / 2; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
            dp[i] *= 2;

            // Only add i/2 * i/2 for odd numbers.
            if ((i & 1) != 0) {
                dp[i] += dp[i / 2] * dp[i / 2];
            }
        }

        return dp[n];
    }
}
