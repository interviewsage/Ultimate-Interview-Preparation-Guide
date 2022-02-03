// LeetCode Question URL: https://leetcode.com/problems/remove-k-digits/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N + K)
 *
 * Space Complexity; O(N)
 *
 * N = Length of the input string.
 */
class Solution1 {
    public String removeKdigits(String num, int k) {
        if (num == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (k == 0) {
            return num;
        }

        int len = num.length();
        if (len <= k) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        sb.append(num.charAt(0));
        for (int i = 1; i < len; i++) {
            char c = num.charAt(i);
            while (k > 0 && sb.length() > 0 && sb.charAt(sb.length() - 1) > c) {
                sb.setLength(sb.length() - 1);
                k--;
            }

            if (sb.length() == 1 && sb.charAt(0) == '0') {
                sb.setCharAt(0, c);
            } else {
                sb.append(c);
            }
        }

        if (k >= sb.length()) {
            return "0";
        }
        if (k > 0) {
            sb.setLength(sb.length() - k);
        }

        return sb.toString();
    }
}

class Solution2 {
    public String removeKdigits(String num, int k) {
        if (num == null || k < 0) {
            throw new IllegalArgumentException("Invalid");
        }

        if (k == 0) {
            return num;
        }

        int len = num.length();
        if (len <= k) {
            return "0";
        }

        StringBuilder sb = new StringBuilder().append(num.charAt(0));
        for (int i = 1; i < len; i++) {
            char c = num.charAt(i);
            int sbLen = sb.length();
            while (k > 0 && sbLen > 0 && sb.charAt(sbLen - 1) > c) {
                sbLen--;
                k--;
            }
            if (sbLen == 1 && sb.charAt(0) == '0') {
                sbLen = 0;
            }
            sb.setLength(sbLen);
            sb.append(c);
        }

        if (k >= sb.length()) {
            return "0";
        }

        if (k > 0) {
            sb.setLength(sb.length() - k);
        }

        return sb.toString();
    }
}
