// LeetCode Question URL: https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/
// LeetCode Discuss URL:

/**
 * This solution will handle negative numbers by by converting negative to
 * positive number.
 *
 * For Integer.MIN_VALUE, we have a special handling as absolute of
 * Integer.MIN_VALUE is Integer.MIN_VALUE. Thus we convert it to
 * Integer.MAX_VALUE and later correct the product and sum.
 *
 * Time Complexity: O(log10 N)
 *
 * Space Complexity: O(1)
 *
 * N = Input number.
 */
class Solution {
    public int subtractProductAndSum(int n) {
        if (n == 0) {
            return 0;
        }

        boolean isIntegerMin = false;
        if (n == Integer.MIN_VALUE) {
            n = Integer.MAX_VALUE;
            isIntegerMin = true;
        } else {
            n = Math.abs(n);
        }

        int product = 1;
        int sum = 0;

        while (n > 0) {
            int d = n % 10;
            n /= 10;
            product *= d;
            sum += d;
        }

        return isIntegerMin ? (product / 7 * 8) - (sum + 1) : product - sum;
    }
}
