// LeetCode Question URL: https://leetcode.com/problems/backspace-string-compare/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(S + T)
 *
 * Space Complexity: O(1)
 *
 * S = Length of input string S. T = Length of input string T.
 */
class Solution {
    public boolean backspaceCompare(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Input in invalid");
        }

        int sIdx = s.length() - 1;
        int tIdx = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (sIdx >= 0 || tIdx >= 0) {
            while (sIdx >= 0) {
                if (s.charAt(sIdx) == '#') {
                    skipS++;
                } else if (skipS > 0) {
                    skipS--;
                } else {
                    break;
                }
                sIdx--;
            }

            while (tIdx >= 0) {
                if (t.charAt(tIdx) == '#') {
                    skipT++;
                } else if (skipT > 0) {
                    skipT--;
                } else {
                    break;
                }
                tIdx--;
            }

            if (sIdx >= 0 && tIdx >= 0 && s.charAt(sIdx) == t.charAt(tIdx)) {
                sIdx--;
                tIdx--;
            } else {
                break;
            }
        }

        return sIdx == -1 && tIdx == -1;
    }
}
