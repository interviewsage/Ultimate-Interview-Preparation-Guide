// LeetCode Question URL: https://leetcode.com/problems/n-th-tribonacci-number/
// LeetCode Discuss URL:

/**
 * Refer https://leetcode.com/problems/n-th-tribonacci-number/discuss/1482728/C++Python-2-solutions:-DP-Matrix-exponential-Picture-explained-Clean-and-Concise
 *
 * For alternate approach which solves this question in O(log N)
 */

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int tribonacci(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n <= 2) {
            return n == 0 ? 0 : 1;
        }

        int n1 = 1;
        int n2 = 1;
        int n3 = 2;

        for (int i = 4; i <= n; i++) {
            int next = n1 + n2 + n3;
            n1 = n2;
            n2 = n3;
            n3 = next;
        }

        return n3;
    }
}
