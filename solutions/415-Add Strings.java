// LeetCode Question URL: https://leetcode.com/problems/add-strings/

/**
 * Time Complexity: O(3 * max(M, N))
 *
 * Space Complexity: O(max(M, N))
 *
 * M = Length of num1 string. N = Length of num2 string.
 *
 * Clarifying questions:
 *
 * Can the length of number be less than one?
 */
class Solution1 {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len1 = num1.length();
        int len2 = num2.length();
        if (len1 == 0 || "0".equals(num1)) {
            return len2 == 0 ? "0" : num2;
        }
        if (len2 == 0 || "0".equals(num2)) {
            return num1;
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while (len1 > 0 || len2 > 0 || carry > 0) {
            int sum = carry;
            if (len1 > 0) {
                sum += num1.charAt(--len1) - '0';
            }
            if (len2 > 0) {
                sum += num2.charAt(--len2) - '0';
            }

            sb.append(sum % 10);
            carry = sum / 10;
        }

        return sb.reverse().toString();
    }
}

/**
 * Time Complexity: O(max(M, N))
 *
 * Space Complexity: O(max(M, N))
 *
 * M = Length of num1 string. N = Length of num2 string.
 *
 * Clarifying questions:
 *
 * Can the length of number be less than one?
 */
class Solution2 {
    public String addStrings(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return "";
        }
        if (num1.length() == 0 || num1.equals("0")) {
            return num2.length() == 0 ? "0" : num2;
        }
        if (num2.length() == 0 || num2.equals("0")) {
            return num1;
        }

        StringBuilder sb = new StringBuilder();
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        int carry = 0;

        while (i >= 0 || j >= 0) {
            int sum = carry;
            if (i >= 0) {
                sum += num1.charAt(i) - '0';
                i--;
            }
            if (j >= 0) {
                sum += num2.charAt(j) - '0';
                j--;
            }
            sb.append(sum % 10);
            carry = sum / 10;
        }

        if (carry > 0) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }
}
