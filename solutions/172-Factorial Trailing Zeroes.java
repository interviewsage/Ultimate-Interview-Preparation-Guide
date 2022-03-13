// LeetCode Question URL: https://leetcode.com/problems/factorial-trailing-zeroes/
// LeetCode Discuss URL:

/**
 * Iterative Solution
 *
 * <pre>
 * Refer:
 * 1. Approach 3: Counting Factors of 5 Efficiently - https://leetcode.com/problems/factorial-trailing-zeroes/solution/
 * 2. https://leetcode.com/problems/factorial-trailing-zeroes/discuss/52371/My-one-line-solutions-in-3-languages
 * </pre>
 *
 * Time Complexity: O(log5 N)
 *
 * Space Complexity: O(1)
 *
 * N = Input number.
 */
class Solution1 {
    public int trailingZeroes(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int result = 0;
        while (n >= 5) {
            n /= 5;
            result += n;
        }

        return result;
    }
}

/**
 * Recursive Solution
 *
 * <pre>
 * Refer:
 * 1. Approach 3: Counting Factors of 5 Efficiently - https://leetcode.com/problems/factorial-trailing-zeroes/solution/
 * 2. https://leetcode.com/problems/factorial-trailing-zeroes/discuss/52371/My-one-line-solutions-in-3-languages
 * </pre>
 *
 * Time Complexity: O(log5 N)
 *
 * Space Complexity: O(1)
 *
 * N = Input number.
 */
class Solution2 {
    public int trailingZeroes(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n < 5) {
            return 0;
        }
        n /= 5;
        return n + trailingZeroes(n);
    }
}
