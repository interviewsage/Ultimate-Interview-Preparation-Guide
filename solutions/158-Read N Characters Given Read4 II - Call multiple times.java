// LeetCode Question URL: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
// LeetCode Discuss URL: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/1496462/Java-or-TC:-O(N)-or-SC:-O(1)-or-Clean-and-concise-solution

/**
 * The read4 API is defined in the parent class Reader4.
 *
 * int read4(char[] buf);
 */

/**
 * Refer:
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/49598/A-simple-Java-code
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1). Uses constant space.
 *
 * N = input n.
 */
public class Solution extends Reader4 {

    char[] buf4;
    int bufferSize;
    int bufferPointer;

    public Solution() {
        buf4 = new char[4];
        bufferSize = 0;
        bufferPointer = 0;
    }

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        if (buf == null) {
            throw new IllegalArgumentException("Input buff is null");
        }
        if (n == 0) {
            return 0;
        }

        int totalRead = 0;

        while (totalRead < n) {
            if (bufferPointer == bufferSize) {
                bufferSize = read4(buf4);
                bufferPointer = 0;
            }

            while (bufferPointer < bufferSize && totalRead < n) {
                buf[totalRead++] = buf4[bufferPointer++];
            }

            if (bufferSize < 4) {
                break;
            }
        }

        return totalRead;
    }
}
