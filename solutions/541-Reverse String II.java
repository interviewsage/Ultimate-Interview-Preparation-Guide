// LeetCode Question URL: https://leetcode.com/problems/reverse-string-ii/
// LeetCode Discuss URL:

/**
 * <pre>
 * Time Complexity: O(N + N/(2*K)*K + N) = O(N)
 * - toCharArray --> O(N)
 * - For Loop + Reverse --> O(N/(2*K) * K)
 * - New String --> O(N)
 * </pre>
 *
 * Space Complexity: O(N)
 *
 * N = Length of Input array
 */
class Solution {
    public String reverseStr(String s, int k) {
        if (s == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        char[] chArr = s.toCharArray();
        for (int i = 0; i < len; i += 2 * k) {
            reverse(chArr, i, Math.min(len - 1, i + k - 1));
        }

        return new String(chArr);
    }

    private void reverse(char[] chArr, int start, int end) {
        while (start < end) {
            char t = chArr[start];
            chArr[start++] = chArr[end];
            chArr[end--] = t;
        }
    }
}
