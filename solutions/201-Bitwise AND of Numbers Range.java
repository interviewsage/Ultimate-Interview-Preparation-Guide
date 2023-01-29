// LeetCode Question URL: https://leetcode.com/problems/bitwise-and-of-numbers-range/
// LeetCode Discuss URL:

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/56729/Bit-operation-solution(JAVA)
 * </pre>
 *
 * Time Complexity: O(32)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public int rangeBitwiseAnd(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int leftShiftBits = 0;
        while (left != 0 && left != right) {
            left >>= 1;
            right >>= 1;
            leftShiftBits++;
        }

        return left << leftShiftBits;
    }
}

/**
 * <pre>
 * Refer:
 * 1. Approach 2: Brian Kernighan's Algorithm in https://leetcode.com/problems/bitwise-and-of-numbers-range/solution/
 * 2.https://leetcode.com/problems/bitwise-and-of-numbers-range/discuss/593317/Simple-3-line-Java-solution-faster-than-100
 * </pre>
 *
 * Time Complexity: O(Number of set bits in right) = O(32)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public int rangeBitwiseAnd(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (left == 0 || left == right) {
            return left;
        }

        while (left < right) {
            right &= (right - 1);
        }

        return left & right;
    }
}

/**
 * Modified to handle negative numbers. Similar to first solution.
 *
 * Time Complexity: O(32)
 *
 * Space Complexity: O(1)
 */
class Solution3 {
    public int rangeBitwiseAnd(int left, int right) {
        if (left > right) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (left <= 0 && right >= 0) {
            return 0;
        }

        int leftShiftBits = 0;
        while (left != 0 && left != right) {
            left >>>= 1;
            right >>>= 1;
            leftShiftBits++;
        }

        return left << leftShiftBits;
    }
}
