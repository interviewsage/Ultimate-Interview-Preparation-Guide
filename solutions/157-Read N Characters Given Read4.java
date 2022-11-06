// LeetCode Question URL: https://leetcode.com/problems/read-n-characters-given-read4/
// LeetCode Discuss URL: https://leetcode.com/problems/read-n-characters-given-read4/discuss/1496437/Java-or-TC:-O(N)-or-SC:-O(1)-or-Clean-and-concise-solution

/**
 * The read4 API is defined in the parent class Reader4.
 *
 * int read4(char[] buf);
 */

/**
 * Refer:
 * https://leetcode.com/problems/read-n-characters-given-read4/discuss/49496/Another-accepted-Java-solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1). Uses constant space.
 *
 * N = input n.
 */
class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        if (buf == null || n < 0 || buf.length < n) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int finalCharCount = 0;
        char[] tempBuf = new char[4];

        while (finalCharCount < n) {
            int read4CharCount = read4(tempBuf);
            for (int i = 0; i < read4CharCount && finalCharCount < n; i++) {
                buf[finalCharCount++] = tempBuf[i];
            }
            if (read4CharCount < 4) {
                break;
            }
        }

        return finalCharCount;
    }
}
