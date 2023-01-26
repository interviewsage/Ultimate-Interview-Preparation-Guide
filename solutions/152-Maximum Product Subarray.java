// LeetCode Question URL: https://leetcode.com/problems/maximum-product-subarray/
// LeetCode Discuss URL:

/**
 * Refer this link for Alternate Solution:
 * https://leetcode.com/problems/maximum-product-subarray/discuss/183483/JavaC++Python-it-can-be-more-simple
 * https://leetcode.com/problems/maximum-product-subarray/discuss/183483/JavaC++Python-it-can-be-more-simple/330117
 */

/**
 * At each new element, we could either add the new element to the existing
 * product, or start fresh the product from current index (wipe out previous
 * results).
 *
 * If we see a negative number, the "candidate" for max should instead become
 * the previous min product, because a bigger number multiplied by negative
 * becomes smaller, hence the swap()
 *
 * Refer:
 * https://leetcode.com/problems/maximum-product-subarray/discuss/48230/Possibly-simplest-solution-with-O(n)-time-complexity
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int maxProduct(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int maxHere = nums[0];
        int minHere = nums[0];
        int maxSoFar = nums[0];

        for (int i = 1; i < len; i++) {
            // multiplied by a negative makes big number smaller, small number bigger
            // so we redefine by swapping them
            if (nums[i] < 0) {
                int t = maxHere;
                maxHere = minHere;
                minHere = t;
            }

            // max/min product for the current number is either the current number itself
            // or the max/min by the previous number times the current one
            maxHere = Math.max(nums[i], maxHere * nums[i]);
            minHere = Math.min(nums[i], minHere * nums[i]);
            // the newly computed max value is a candidate for our global result
            maxSoFar = Math.max(maxSoFar, maxHere);
        }

        return maxSoFar;
    }
}

class Solution2 {
    public int maxProduct(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return nums[0];
        }

        int minHere = nums[0];
        int maxHere = nums[0];
        int maxSoFar = nums[0];

        for (int i = 1; i < len; i++) {
            int curMinHere = Math.min(nums[i], Math.min(minHere * nums[i], maxHere * nums[i]));
            maxHere = Math.max(nums[i], Math.max(minHere * nums[i], maxHere * nums[i]));
            minHere = curMinHere;
            maxSoFar = Math.max(maxSoFar, maxHere);
        }

        return maxSoFar;
    }
}
