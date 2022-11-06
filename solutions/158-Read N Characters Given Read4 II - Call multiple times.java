// LeetCode Question URL: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/
// LeetCode Discuss URL: https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/1496462/Java-or-TC:-O(N)-or-SC:-O(1)-or-Clean-and-concise-solution

/**
 * The read4 API is defined in the parent class Reader4.
 *
 * int read4(char[] buf);
 */

/**
 * Google Follow-Up:
 * https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/188293/Google-follow-up-question.-Speed-up-the-copy.
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
class Solution extends Reader4 {

    int read4BufPointer;
    int read4BuffSize;
    char[] read4Buf;

    public Solution() {
        read4BufPointer = 0;
        read4BuffSize = 0;
        read4Buf = new char[4];
    }

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return The number of actual characters read
     */
    public int read(char[] buf, int n) {
        if (buf == null || n < 0 || buf.length < n) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int totalRead = 0;
        while (totalRead < n) {
            if (read4BufPointer == read4BuffSize) {
                read4BuffSize = read4(read4Buf);
                read4BufPointer = 0;
            }
            while (read4BufPointer < read4BuffSize && totalRead < n) {
                buf[totalRead++] = read4Buf[read4BufPointer++];
            }
            if (read4BuffSize < 4) {
                break;
            }
        }

        return totalRead;
    }
}
