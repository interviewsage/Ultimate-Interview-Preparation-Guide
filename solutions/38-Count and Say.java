// LeetCode Question URL: https://leetcode.com/problems/count-and-say/
// LeetCode Discuss URL: https://leetcode.com/problems/count-and-say/discuss/1546382/Java-or-Simple-Iterative-solution-generating-all-states-from-1-to-N.

/**
 * Find each state starting from 1st to nth.
 *
 * Time Complexity: O(L(1) + L(2) + L(3) + ... + L(N-1) + L(N))
 *
 * Space Complexity: O(L(N-1) + L(N))
 *
 * N = Input number. L(N) = Length of Nth state
 *
 * <pre>
 * DISCARD THIS COMPLEXITY ANALYSIS
 * Each successive number can have at most twice as many digits as the previous
 * number and this happens when all digits are different [21 -> 1211]. Thus nth
 * value will be of length 2^(N-1) in worst case.
 *
 * 1 -> "1" -> length = 1 = 2^0
 *
 * 2 -> "11" -> length = 2 = 2^1
 *
 * Assume 3 -> "1211" -> length = 4 = 2^2
 *
 * Time Complexity: 2^0 + 2^1 + 2^2 + ... + 2^(N-2) = 2^(N-1) = O(2^N).
 * </pre>
 */
class Solution1 {
    public String countAndSay(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        StringBuilder sb = new StringBuilder("1");

        while (--n > 0) {
            StringBuilder next = new StringBuilder();
            int sbLen = sb.length();
            int idx = 0;
            while (idx < sbLen) {
                char d = sb.charAt(idx++);
                int count = 1;
                while (idx < sbLen && d == sb.charAt(idx)) {
                    count++;
                    idx++;
                }
                next.append(count).append(d);
            }
            sb = next;
        }

        return sb.toString();
    }
}

class Solution2 {
    public String countAndSay(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        StringBuilder sb = new StringBuilder("1");
        for (int i = 2; i <= n; i++) {
            sb = getNextState(sb);
        }
        return sb.toString();
    }

    private StringBuilder getNextState(StringBuilder sb) {
        StringBuilder nextSb = new StringBuilder();
        char c = sb.charAt(0);
        int count = 1;
        for (int i = 1; i < sb.length(); i++) {
            char t = sb.charAt(i);
            if (c == t) {
                count++;
            } else {
                nextSb.append(count).append(c);
                c = t;
                count = 1;
            }
        }
        nextSb.append(count).append(c);
        return nextSb;
    }
}
