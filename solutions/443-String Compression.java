// LeetCode Question URL: https://leetcode.com/problems/string-compression/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(2 * N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int compress(char[] chars) {
        if (chars == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = chars.length;
        if (len <= 1) {
            return len;
        }

        int insertPos = 0;
        int i = 0;

        while (i < len) {
            int start = i++;
            while (i < len && chars[start] == chars[i]) {
                i++;
            }

            chars[insertPos++] = chars[start];
            int count = i - start;
            if (count > 1) {
                String numStr = Integer.toString(count);
                for (int j = 0; j < numStr.length(); j++) {
                    chars[insertPos++] = numStr.charAt(j);
                }
            }
        }

        return insertPos;
    }
}
