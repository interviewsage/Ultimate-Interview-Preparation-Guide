// LeetCode Question URL: https://leetcode.com/problems/one-edit-distance/
// LeetCode Discuss URL: https://leetcode.com/problems/one-edit-distance/discuss/1519249/Java-or-TC:-O(min(ST))-or-SC:-O(min(ST))-or-Optimal-One-Pass-solution

/**
 * Time & Space Complexity:
 * If diff in len of S & T > 1 .. then both Time & Space complexity is O(1)
 * If diff in len of S & T <= 1 .. then both Time & Space complexity is O(N)
 *
 * N = Length of input string S. String T can have length from N-1 to N+1.
 */
class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Input string is null");
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

        return sLen != tLen;
    }
}
