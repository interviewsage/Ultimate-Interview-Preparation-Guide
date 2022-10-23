// LeetCode Question URL: https://leetcode.com/problems/total-hamming-distance/
// LeetCode Discuss URL:

/**
 * Say for any particular bit position, count the number of elements with this
 * bit ON (i.e. this particular bit is 1). Let this count be k. Hence the number
 * of elements with this bit OFF (i.e. 0) is (n - k) (in an n element array).
 *
 * Certainly unique pairs of elements exists where one element has this
 * particular bit ON while the other element has this OFF (i.e. this particular
 * bit differs for the two elements of this pair).
 *
 * We can argue that every such pair contributes one unit to the Hamming
 * Distance for this particular bit.
 *
 * We know that the count of such unique pairs is C(k, 1) * C(n-k, 1) = k*(n−k)
 * for this particular bit. Hence Hamming Distance for this particular bit is
 * k*(n−k).
 *
 * Time Complexity: O(32 * N) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int totalHammingDistance(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = nums.length;
        if (len <= 1) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            int setBits = 0;
            int allZeroBits = 0;
            int allOneBits = 0;
            for (int n : nums) {
                n >>= i;
                setBits += n & 1;
                // This is for positive numbers
                if (n == 0) {
                    allZeroBits++;
                }
                // This check if needed only if negative numbers are allowed in input array.
                // Check with Interviewer first.
                if (n == -1) {
                    allOneBits++;
                }
            }
            if (allZeroBits == len || allOneBits == len) {
                break;
            }
            result += setBits * (len - setBits);
        }

        return result;
    }
}

class Solution2 {
    public int totalHammingDistance(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = nums.length;
        if (len <= 1) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < 32; i++) {
            int mask = 1 << i;
            int setBits = 0;
            for (int n : nums) {
                if ((n & mask) != 0) {
                    setBits++;
                }
            }
            result += setBits * (len - setBits);
        }

        return result;
    }
}
