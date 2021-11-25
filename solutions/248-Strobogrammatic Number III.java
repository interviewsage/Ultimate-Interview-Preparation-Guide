// LeetCode Question URL: https://leetcode.com/problems/strobogrammatic-number-iii/
// LeetCode Discuss URL:

/**
 * Construct strobogrammatic numbers from low.length() to high.length()
 *
 * <pre>
 * Time Complexity:
 * Below equations are for low and high length
 * T(N)   = 5 * T(N-2) + O(5)
 * T(N-2) = 5 * T(N-4) + O(5)
 * T(N-4) = 5 * T(N-6) + O(5)
 * ...
 * T(2)   = 5 * T(0) + O(5)
 * T(0) = O(N) //  This is for compare
 *
 * T(N) = 5 + 5^2 + 5^3 + ... + 5^(N/2) + 5^(N/2) * N
 * T(N) = 5 * (5^(N/2 + 1) - 1) / (5 - 1) + 5^(N/2) * N
 * T(N) = 5^(N/2) * (N + 25/4) - 5/4
 * T(N) ~ O(N * 5^(N/2))
 *
 * ----
 *
 * For length > low and < high
 * T(N)   = 5 * T(N-2) + O(5)
 * T(N-2) = 5 * T(N-4) + O(5)
 * T(N-4) = 5 * T(N-6) + O(5)
 * ...
 * T(2)   = 5 * T(0) + O(5)
 * T(0) = O(1) // No compare is done.
 *
 * T(N) = 5 + 5^2 + 5^3 + ... + 5^(N/2) + 5^(N/2)
 * T(N) = 5 * (5^(N/2 + 1) - 1) / (5 - 1) + 5^(N/2)
 * T(N) = 5^(N/2) * (1 + 25/4) - 5/4
 * T(N) ~ O(5^(N/2 + 1))
 *
 * Total Time Complexity = O(L * 5^(L/2)
 *                           + (i -> L+1 to H-1) ∑ (5^(i/2 + 1))
 *                           + H * 5^(H/2))
 * </pre>
 *
 * Space Complexity: O(H + H/2). Size of temp char array + Recursion depth
 *
 * L = length of low string. H = length of high string.
 */
class Solution {
    private static final char[][] PAIRS = new char[][] { { '0', '0' }, { '1', '1' }, { '6', '9' }, { '8', '8' },
            { '9', '6' } };

    public int strobogrammaticInRange(String low, String high) {
        if (low == null || high == null) {
            return 0;
        }
        int lLen = low.length();
        int hLen = high.length();
        if (lLen == 0 || hLen == 0 || lLen > hLen || (lLen == hLen && low.compareTo(high) > 0)) {
            return 0;
        }

        int count = 0;
        for (int i = lLen; i <= hLen; i++) {
            count += strobogrammaticInRangeHelper(low, high, 0, new char[i]);
        }
        return count;
    }

    private int strobogrammaticInRangeHelper(String low, String high, int left, char[] charArr) {
        int len = charArr.length;
        int right = len - 1 - left;

        if (left > right) {
            return ((len == low.length() && compareCharArrWithString(charArr, low) < 0)
                    || (len == high.length() && compareCharArrWithString(charArr, high) > 0)) ? 0 : 1;
        }

        int count = 0;
        for (char[] pair : PAIRS) {
            if (left == 0 && pair[0] == '0' && len > 1) {
                continue;
            }
            if (left == right && pair[0] != pair[1]) {
                continue;
            }

            charArr[left] = pair[0];
            charArr[right] = pair[1];

            count += strobogrammaticInRangeHelper(low, high, left + 1, charArr);
        }
        return count;
    }

    private int compareCharArrWithString(char[] charArr, String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (charArr[i] != c) {
                return charArr[i] - c;
            }
        }
        return 0;
    }
}
