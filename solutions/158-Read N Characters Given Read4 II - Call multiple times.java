// LeetCode URL: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/

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

    char[] tempBuf = new char[4];
    int tempBufPointer = 0;
    int tempBufSize = 0;
    boolean eof = false;

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) throws IllegalArgumentException {
        if (buf == null || n == 0) {
            return 0;
        }

        int totalRead = 0;

        while (!eof && totalRead < n) {
            if (tempBufPointer == tempBufSize) {
                tempBufPointer = 0;
                tempBufSize = read4(tempBuf);
                if (tempBufSize == 0) {
                    eof = true;
                    break;
                }
            }

            // optional condition in while-loop
            // totalRead < buf.length
            while (tempBufPointer < tempBufSize && totalRead < n && totalRead < buf.length) {
                buf[totalRead] = tempBuf[tempBufPointer];
                totalRead++;
                tempBufPointer++;
            }
        }

        return totalRead;
    }
}