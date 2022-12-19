// LeetCode Question URL: https://leetcode.com/problems/multiply-strings/

/**
 * Refer:
 * https://leetcode.com/problems/multiply-strings/discuss/17605/Easiest-JAVA-Solution-with-Graph-Explanation
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity: O(M+N)
 *
 * M = Length of nums1 string. N = Length of nums2 string.
 */
class Solution {
    public String multiply(String num1, String num2) {
        if (num1 == null || num2 == null || num1.length() == 0 || num2.length() == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if ("0".equals(num1) || "0".equals(num2)) {
            return "0";
        }
        if ("1".equals(num1)) {
            return num2;
        }
        if ("1".equals(num2)) {
            return num1;
        }

        int l1 = num1.length();
        int l2 = num2.length();
        // Result can be maximum of length M + N.
        // For example 99 * 99 = 9801 (Result is of length 4)
        int[] resultArr = new int[l1 + l2];

        for (int i = l1 - 1; i >= 0; i--) {
            for (int j = l2 - 1; j >= 0; j--) {
                int num = resultArr[i + j + 1] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                resultArr[i + j + 1] = num % 10;
                resultArr[i + j] += num / 10;
            }
        }

        // Generating the result String
        StringBuilder sb = new StringBuilder();
        for (int r : resultArr) {
            // Ignoring leading zeros
            if (sb.length() == 0 && r == 0) {
                continue;
            }
            sb.append(r);
        }

        return sb.toString();
    }
}
