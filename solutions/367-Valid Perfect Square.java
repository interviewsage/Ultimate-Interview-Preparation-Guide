// LeetCode Question URL: https://leetcode.com/problems/valid-perfect-square/
// LeetCode Discuss URL:

/**
 * Binary Search
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/valid-perfect-square/discuss/83874/A-square-number-is-1+3+5+7+...-JAVA-code/266910
 * 2) https://leetcode.com/problems/valid-perfect-square/discuss/83874/A-square-number-is-1+3+5+7+...-JAVA-code/88293
 * 3) https://leetcode.com/problems/valid-perfect-square/discuss/622268/Java-Binary-Search-Clean-code-logN/932250
 *
 * Time Complexity: O(log2(N))
 * Space Complexity: O(1)
 * </pre>
 */
class Solution1 {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        if (num <= 1) {
            return true;
        }

        int start = 1;
        int end = num / 2;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            // Here `num % mid == 0` can be moved to return statement. But we need to find a
            // proof why that works.
            if (num / mid == mid && num % mid == 0) {
                return true;
            }
            if (mid < num / mid) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return false;
    }
}

/**
 * Math Solution
 *
 * <pre>
 * 1 = 1
 * 4 = 1 + 3
 * 9 = 1 + 3 + 5
 * 16 = 1 + 3 + 5 + 7
 * 25 = 1 + 3 + 5 + 7 + 9
 * 36 = 1 + 3 + 5 + 7 + 9 + 11
 * ....
 * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = n^2
 *
 * Refer:
 * 1) https://leetcode.com/problems/valid-perfect-square/discuss/83874/A-square-number-is-1+3+5+7+...-JAVA-code
 * 2) https://leetcode.com/problems/valid-perfect-square/discuss/83874/A-square-number-is-1+3+5+7+...-JAVA-code/88308
 * 3) https://leetcode.com/problems/valid-perfect-square/discuss/83874/A-square-number-is-1+3+5+7+...-JAVA-code/88293
 *
 * Time Complexity: O(sqrt(N))
 * Space Complexity: O(1)
 * </pre>
 */
class Solution2 {
    public boolean isPerfectSquare(int num) {
        if (num < 0) {
            return false;
        }
        if (num <= 1) {
            return true;
        }

        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }
}
