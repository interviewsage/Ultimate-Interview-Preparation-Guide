// LeetCode Question URL: https://leetcode.com/problems/one-edit-distance/
// LeetCode Discuss URL: https://leetcode.com/problems/one-edit-distance/discuss/1519249/Java-or-TC:-O(min(ST))-or-SC:-O(min(ST))-or-Optimal-One-Pass-solution

/**
 * Time Complexity: O(min(S, T))
 *
 * Space Complexity: O(min(S, T))
 *
 * S = Length of string s. T = Length of string t;
 */
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();
        if (Math.abs(sLen - tLen) > 1) {
            return false;
        }

        int minLen = Math.min(sLen, tLen);
        for (int i = 0; i < minLen; i++) {
            if (s.charAt(i) != t.charAt(i)) {
                if (sLen == tLen) {
                    // Replace ith s char with t.charAt(i)
                    return s.substring(i + 1).equals(t.substring(i + 1));
                } else if (sLen < tLen) {
                    // Insert t.charAt(i) in s at i
                    return s.substring(i).equals(t.substring(i + 1));
                } else {
                    // Delete s.charAt(i)
                    return s.substring(i + 1).equals(t.substring(i));
                }
            }
        }

        return Math.abs(sLen - tLen) == 1;
    }
}
