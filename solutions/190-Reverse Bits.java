// LeetCode Question URL: https://leetcode.com/problems/reverse-bits/
// LeetCode Discuss URL: https://leetcode.com/problems/reverse-bits/discuss/1555748/Java-or-TC:-O(1)-or-SC:-O(1)-or-Two-Simple-Approaches-using-Bit-Manipulation

/**
 * Mask and Shift
 *
 * Refer Approach 3: https://leetcode.com/problems/reverse-bits/solution/
 *
 * Time Complexity: O(1)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) {
            return 0;
        }

        // Swap 16 bits <--> 16 bits
        // n = ((n & 0xffff0000) >>> 16) | ((n & 0x0000ffff) << 16);
        n = (n >>> 16) | (n << 16);
        // Swap 8 bits <--> 8 bits
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        // Swap 4 bits <--> 4 bits
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        // Swap 2 bits <--> 2 bits
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        // Swap 1 bits <--> 1 bits
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);

        return n;
    }
}

/**
 * Bit by Bit
 *
 * Refer Approach 1: https://leetcode.com/problems/reverse-bits/solution/
 *
 * Time Complexity: O(32) = O(1)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) {
            return 0;
        }

        int result = 0;
        int power = 31;

        while (n != 0) {
            result |= (n & 1) << power;
            n >>>= 1;
            power--;
        }

        return result;
    }
}

/**
 * Bit by Bit. This is not optimized as we will always run the loop 32 times.
 *
 * Time Complexity: O(32) = O(1)
 *
 * Space Complexity: O(1)
 */
class Solution3 {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        if (n == 0) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            result = (result << 1) | (n & 1);
            n >>>= 1;
        }

        return result;
    }
}
