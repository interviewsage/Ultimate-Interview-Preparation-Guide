// LeetCode Question URL: https://leetcode.com/problems/add-binary/

/**
 * Very similar to Add Two Numbers (LinkedList)
 *
 * Time Complexity: O(max(A, B))
 *
 * Space Complexity: O(max(A, B))
 *
 * A = Length of string A. B = Length of string B.
 */
class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int lenA = a.length();
        int lenB = b.length();

        if (lenA == 0 || "0".equals(a)) {
            return lenB == 0 ? "0" : b;
        }
        if (lenB == 0 || "0".equals(b)) {
            return a;
        }

        StringBuilder sb = new StringBuilder();
        int carry = 0;

        while (lenA > 0 || lenB > 0 || carry > 0) {
            int sum = carry;
            if (lenA > 0) {
                sum += a.charAt(--lenA) - '0';
            }
            if (lenB > 0) {
                sum += b.charAt(--lenB) - '0';
            }
            sb.append(sum % 2);
            carry = sum / 2;
        }

        return sb.reverse().toString();
    }
}

/**
 * Very similar to Add Two Numbers (LinkedList)
 *
 * Time Complexity: O(max(A, B))
 *
 * Space Complexity: O(max(A, B))
 *
 * A = Length of string A. B = Length of string B.
 */
class Solution2 {
    public String addBinary(String a, String b) {
        if (a == null | b == null) {
            throw new IllegalArgumentException("Invalid Input");
        }

        if (a.length() == 0 || a.equals("0")) {
            return b.length() == 0 ? "0" : b;
        }

        if (b.length() == 0 || b.equals("0")) {
            return a;
        }

        StringBuilder sb = new StringBuilder();
        int len1 = a.length() - 1;
        int len2 = b.length() - 1;
        int carry = 0;

        while (len1 >= 0 || len2 >= 0) {
            int sum = carry;
            if (len1 >= 0) {
                sum += a.charAt(len1) - '0';
                len1--;
            }
            if (len2 >= 0) {
                sum += b.charAt(len2) - '0';
                len2--;
            }

            sb.append(sum % 2);
            carry = sum / 2;
        }

        if (carry > 0) {
            sb.append(carry);
        }

        return sb.reverse().toString();
    }
}