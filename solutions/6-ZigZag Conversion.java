// LeetCode Question URL: https://leetcode.com/problems/zigzag-conversion/
// LeetCode Discuss URL: https://leetcode.com/problems/zigzag-conversion/discuss/1539220/Java-or-TC:-O(N)-or-SC:-O(N)-or-Optimized-Row-by-Row-solution

/**
 * Visit Row by Row
 *
 * Refer: Approach 2 in Solutions Tab
 * https://leetcode.com/problems/zigzag-conversion/solution/
 *
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(N)
 *
 * R = Number of rows. N = Length of input string
 */
class Solution1 {
    public String convert(String s, int numRows) {
        if (s == null || numRows <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (numRows == 1 || len <= numRows) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int cycleLen = 2 * numRows - 2;

        /**
         * <pre>
         * First Row:   0,      2N-2,               4N-4,               ...
         * Last Row:    N-1,    2N-2+(N-1),         5N-5+(N-1),         ...
         * ith Row:     i,      2N-2-i, 2N-2+i,     4N-4-i, 4N-4+i,     ...
         * </pre>
         */

        for (int j = 0; j < numRows; j++) {
            for (int i = 0; i + j < len; i += cycleLen) {
                sb.append(s.charAt(i + j));
                if (j != 0 && j != numRows - 1 && i + cycleLen - j < len) {
                    sb.append(s.charAt(i + cycleLen - j));
                }
            }
        }

        return sb.toString();
    }
}

/**
 * Using string buffer for each row. Simulating ZigZag Traversal
 *
 * Refer:
 * https://leetcode.com/problems/zigzag-conversion/discuss/3403/Easy-to-understand-Java-solution
 *
 * Time Complexity: O(R + 3*N) = O(R+N)
 *
 * Space Complexity: O(R + N)
 *
 * R = Number of rows. N = Length of input string
 */
class Solution2 {
    public String convert(String s, int numRows) {
        if (s == null || numRows <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (numRows == 1 || len <= numRows) {
            return s;
        }

        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int j = 0; j < numRows; j++) {
            sbs[j] = new StringBuilder();
        }

        int i = 0;
        while (i < len) {
            for (int j = 0; j < numRows && i < len; j++) {
                sbs[j].append(s.charAt(i++));
            }
            for (int j = numRows - 2; j >= 1 & i < len; j--) {
                sbs[j].append(s.charAt(i++));
            }
        }

        for (int j = 1; j < numRows; j++) {
            sbs[0].append(sbs[j]);
        }

        return sbs[0].toString();
    }
}

/**
 * Visit by Row by Row. Verbose Solution.
 *
 * Refer: Approach 2 in Solutions Tab
 * https://leetcode.com/problems/zigzag-conversion/solution/
 *
 * Time Complexity: O(2 * N)
 *
 * Space Complexity: O(N)
 *
 * R = Number of rows. N = Length of input string
 */
class Solution3 {
    public String convert(String s, int numRows) {
        if (s == null || numRows <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (numRows == 1 || len <= numRows) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int cycleLen = 2 * numRows - 2;

        // First Row
        for (int i = 0; i < len; i += cycleLen) {
            sb.append(s.charAt(i));
        }

        // Middle Rows
        for (int j = 1; j < numRows - 1; j++) {
            int i = j;
            boolean even = true;
            while (i < len) {
                sb.append(s.charAt(i));
                i += even ? cycleLen - 2 * j : 2 * j;
                even = !even;
            }
        }

        // Last Row
        for (int i = numRows - 1; i < len; i += cycleLen) {
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
