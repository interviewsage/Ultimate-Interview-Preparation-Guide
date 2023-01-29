// LeetCode Question URL: https://leetcode.com/problems/calculate-amount-paid-in-taxes/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(B)
 *
 * Space Complexity: O(1)
 *
 * B = Number of Brackets
 */
class Solution1 {
    public double calculateTax(int[][] brackets, int income) {
        if (brackets == null || brackets.length == 0 || income < 0 || brackets[brackets.length - 1][0] < income) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int prevBracket = 0;
        double totalTax = 0;
        int idx = 0;

        while (idx < brackets.length) {
            totalTax += (Math.min(income, brackets[idx][0]) - prevBracket) * brackets[idx][1] / 100.0;
            if (income <= brackets[idx][0]) {
                break;
            }
            prevBracket = brackets[idx++][0];
        }

        return totalTax;
    }
}

/**
 * Pre-Compute the prefixTaxSum and then do Binary Search
 *
 * Time Complexity: O(B + log B)
 *
 * Space Complexity: O(B)
 *
 * B = Number of Brackets
 */
class Solution2 {
    public double calculateTax(int[][] brackets, int income) {
        if (brackets == null || brackets.length == 0 || income < 0 || brackets[brackets.length - 1][0] < income) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = brackets.length;
        double[] prefixTaxSum = getPrefixTaxSumArray(brackets);

        int start = 0;
        int end = len - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (income > brackets[mid][0]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (start == 0) {
            return income * brackets[start][1] / 100.0;
        }
        return prefixTaxSum[start - 1] + (income - brackets[start - 1][0]) * brackets[start][1] / 100.0;
    }

    private double[] getPrefixTaxSumArray(int[][] brackets) {
        int len = brackets.length;
        double[] prefixTaxSum = new double[len];
        double preTaxSum = 0;
        int prevBracket = 0;

        for (int i = 0; i < len; i++) {
            prefixTaxSum[i] = preTaxSum + (brackets[i][0] - prevBracket) * brackets[i][1] / 100.0;
            preTaxSum = prefixTaxSum[i];
            prevBracket = brackets[i][0];
        }

        return prefixTaxSum;
    }
}
