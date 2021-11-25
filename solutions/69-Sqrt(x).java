// LeetCode Question URL: https://leetcode.com/problems/sqrtx
// LeetCode Discuss URL:

/**
 * Optimized Bit Manipulation (With Early Exit)
 *
 * <pre>
 * Number of bits in Int.MAX = log(Int.MAX) = 31 bits
 * Number of bits in sqrt(Int.MAX) = log(sqrt(Int.MAX)) = 16 bits.
 *
 * Thus we need to create a mask with 16 bits, where the 15th bit is set to 1 & rest are set to zero.
 *
 * In this solution we are setting each bit and checking if the desired result is less than sqrt(X),
 * else we do not set the bit in the result.
 * </pre>
 *
 * Time Complexity: O(16) = O(1)
 *
 * Space Complexity: O(1)
 *
 * N = Input number x.
 */
class Solution1 {
    public int mySqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Input number is negative. Only positive numbers are supported");
        }
        if (x < 2) {
            return x;
        }

        int result = 0;
        int mask = 1 << 15;

        while (mask > 0) {
            // result |= mask;
            // if (result == x / result) {
            // return result;
            // }
            // if (result > x / result) {
            // result ^= mask;
            // }
            // mask >>= 1;

            int next = result | mask;
            if (next == x / next) {
                return next;
            }
            if (next < x / next) {
                result = next;
            }
            mask >>= 1;
        }

        return result;
    }
}

/**
 * Optimized binary search
 *
 * <pre>
 * We can return:
 * -> Exception if N < 0
 * -> 0 if N = 0
 * -> 1 if 1 <= N <= 3
 * For all other values we will perform binary search.
 * </pre>
 *
 * Time Complexity: O(log(N/2)). In case of Int.MAX, time complexity can maximum
 * be O(30) = O(1)
 *
 * Space Complexity: O(1)
 *
 * N = Input number x.
 */
class Solution2 {
    public int mySqrt(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Input number is negative. Only positive numbers are supported");
        }

        // Base case to handle Input <= 3.
        if (x <= 3) {
            return x == 0 ? 0 : 1;
        }

        // Binary Search space will start from 2 to x/2.
        // Since we have already handled upto x=3 in the base case, 2 will be correct
        // answer for next possible input x=4.
        // For x>=4, Square root is always less than x/2. Thus end will be x/2.
        int start = 2;
        int end = x / 2;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            // x == (mid * mid). To Avoid Integer Overflow, we will do (x / mid) == mid.
            if (mid == x / mid) {
                return mid;
            }
            if (mid > x / mid) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        // Since at this point start > end, start will start pointing to a value greater
        // than the square root of the input value. We will return end as it will point
        // to the correct int value.
        return end;
    }
}
