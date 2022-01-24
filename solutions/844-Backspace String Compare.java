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
            return false;
        }

        int i = s.length() - 1;
        int j = t.length() - 1;
        int skipS = 0;
        int skipT = 0;

        while (i >= 0 || j >= 0) {
            while (i >= 0) {
                if (s.charAt(i) == '#') {
                    skipS++;
                } else if (skipS > 0) {
                    skipS--;
                } else {
                    break;
                }
                i--;
            }

            while (j >= 0) {
                if (t.charAt(j) == '#') {
                    skipT++;
                } else if (skipT > 0) {
                    skipT--;
                } else {
                    break;
                }
                j--;
            }

            if (i >= 0 && j >= 0 && s.charAt(i) == t.charAt(j)) {
                i--;
                j--;
            } else {
                break;
            }
        }

        return (i == -1) && (j == -1);
    }
}
