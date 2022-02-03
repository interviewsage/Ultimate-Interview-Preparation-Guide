// LeetCode Question URL: https://leetcode.com/problems/decode-string/
// LeetCode Discuss URL:

/**
 * Recursive One Pass solution
 *
 * <pre>
 * Time Complexity: O(N + 2*O)
 *
 * k[a k[a k[a ....]]]
 * Time taken:
 * 1. Every character of S will be visited. Which will be O(N)
 * 2. to create output = O(O) = k + k^2 + k^3 + ... + k^RecursionDepth = O(K^(RecursionDepth-1)) ~ O(k^RecursionDepth). This is similar to O(O)
 * 3. toString = O(O)
 *
 * Space Complexity: Depth of the recursion stack can maximum grow upto N/4. Minimum configuration example - 2[a]
 * StringBuilder can grow upto the size of the result.
 *
 * Total Space Complexity: O(N/4 + O)
 * </pre>
 *
 * N = Length of the input string. O = Length of the output string.
 */
class Solution {
    public String decodeString(String s) {
        if (s == null || s.length() < 4) {
            return s;
        }

        return decodeStringHelper(s, new int[1]).toString();
    }

    private StringBuilder decodeStringHelper(String s, int[] idx) {
        StringBuilder sb = new StringBuilder();

        while (idx[0] < s.length() && s.charAt(idx[0]) != ']') {
            char c = s.charAt(idx[0]);
            if (Character.isLetter(c)) {
                sb.append(c);
                idx[0]++;
                continue;
            }

            int val = 0;
            while (Character.isDigit(s.charAt(idx[0]))) {
                val = val * 10 + (s.charAt(idx[0]) - '0');
                idx[0]++;
            }

            idx[0]++;
            StringBuilder next = decodeStringHelper(s, idx);
            idx[0]++;

            while (val-- > 0) {
                sb.append(next);
            }
        }

        return sb;
    }
}
