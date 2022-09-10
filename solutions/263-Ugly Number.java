// LeetCode Question URL: https://leetcode.com/problems/ugly-number/
// LeetCode Discuss URL:

/**
 * Keep diving by each factor till cannot divide further or zero reached.
 *
 * <pre>
 * Refer: https://leetcode.com/problems/ugly-number/discuss/69214/2-4-lines-every-language
 *
 * Time Complexity:
 * floor(log30 N) + floor(log15 (N / 30^floor(log30 N))) + ... (similarly for all factors)
 *
 * In worst case, if the number if power of two, then the complexity will be O(log2 N)
 *
 * Space Complexity: O(1)
 * </pre>
 */
class Solution {
    public boolean isUgly(int n) {
        if (n <= 0) {
            return false;
        }
        if (n <= 6) {
            return true;
        }

        int[] factors = { 30, 15, 10, 6, 5, 3, 2 };

        for (int i = 0; i < factors.length && n > 1; i++) {
            while (n % factors[i] == 0) {
                n /= factors[i];
            }
        }

        return n == 1;
    }
}
