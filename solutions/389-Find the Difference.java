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
        if (s == null || t == null || t.length() - s.length() != 1) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int sLen = s.length();
        char result = t.charAt(sLen);
        for (int i = 0; i < sLen; i++) {
            result ^= s.charAt(i) ^ t.charAt(i);
        }

        return result;
    }
}
