// LeetCode Question URL: https://leetcode.com/problems/longest-repeating-character-replacement/
// LeetCode Discuss URL:

/**
 * Refer: 1)
 * https://leetcode.com/problems/longest-repeating-character-replacement/discuss/91271/Java-12-lines-O(n)-sliding-window-solution-with-explanation/95833
 *
 * "Can we increase the window size?" at every step. If yes, then increase,
 * otherwise simply slide the window right.
 *
 * Time Complexity: O(N). Each character is visited maximum twice.
 *
 * Space Complexity: O(26). This is require for each uppercase alphabet.
 *
 * N = Length of the input array.
 */
class Solution {
    public int characterReplacement(String s, int k) {
        if (s == null || k < 0) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = s.length();
        // len <= 1 is added for the case when k == 0.
        if (len <= 1 || len <= k) {
            return len;
        }

        int[] count = new int[26];
        int start = 0;
        int end = 0;
        int maxCount = 0;

        while (end < len) {
            int eChar = s.charAt(end++) - 'A';
            maxCount = Math.max(maxCount, ++count[eChar]);

            if (end - start > maxCount + k) {
                int sChar = s.charAt(start++) - 'A';
                count[sChar]--;
            }
        }

        return len - start;
    }
}
