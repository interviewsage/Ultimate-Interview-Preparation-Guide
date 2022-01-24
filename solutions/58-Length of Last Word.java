// LeetCode Question URL: https://leetcode.com/problems/length-of-last-word/
// LeetCode Discuss URL:

/**
 * Iterate in reverse order till you find a word.
 *
 * Time Complexity: O(N) --> In worst case whole array has to be searched.
 *
 * Space Complexity: O(1).
 *
 * N = Length of input array.
 */
class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int i = s.length() - 1;
        int result = 0;

        while (i >= 0) {
            if (s.charAt(i) == ' ') {
                i--;
                continue;
            }
            while (i >= 0 && s.charAt(i) != ' ') {
                result++;
                i--;
            }
            return result;
        }

        return 0;
    }
}
