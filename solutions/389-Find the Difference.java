// LeetCode Question URL: https://leetcode.com/problems/find-the-difference/
// LeetCode Discuss URL:

/**
 * XOR all characters. You will be left with extra char.
 *
 * Time Complexity: O(S). Where S is the length of string s.
 *
 * Space Complexity: O(1)
 */
class Solution {
    public char findTheDifference(String s, String t) {
        if (s == null || t == null || t.length() != s.length() + 1) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        char xor = t.charAt(len);
        for (int i = 0; i < len; i++) {
            xor ^= s.charAt(i) ^ t.charAt(i);
        }

        return xor;
    }
}
