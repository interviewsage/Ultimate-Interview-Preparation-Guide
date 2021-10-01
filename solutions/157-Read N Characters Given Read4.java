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
        if (buf == null) {
            throw new IllegalArgumentException("Input buffer is null");
        }
        if (n == 0) {
            return 0;
        }

        int totalRead = 0;
        char[] buf4 = new char[4];

        while (totalRead < n) {
            int read = read4(buf4);
            for (int i = 0; i < read && totalRead < n; i++, totalRead++) {
                buf[totalRead] = buf4[i];
            }
            if (read < 4) {
                break;
            }
        }

        return totalRead;
    }
}