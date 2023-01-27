// LeetCode Question URL: https://leetcode.com/problems/maximum-product-of-three-numbers/
// LeetCode Discuss URL:

/**
 * Find out the three largest numbers and the two smallest numbers using one
 * pass.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 3) {
            return nums[0] * nums[1] * nums[2];
        }

        int max1 = nums[0];
        Integer max2 = null;
        Integer max3 = null;
        int min1 = nums[0];
        Integer min2 = null;

        for (int i = 1; i < len; i++) {
            if (nums[i] > max1) {
                max3 = max2;
                max2 = max1;
                max1 = nums[i];
            } else if (max2 == null || nums[i] > max2) {
                max3 = max2;
                max2 = nums[i];
            } else if (max3 == null || nums[i] > max3) {
                max3 = nums[i];
            }

            if (nums[i] < min1) {
                min2 = min1;
                min1 = nums[i];
            } else if (min2 == null || nums[i] < min2) {
                min2 = nums[i];
            }
        }

        if (min1 < 0 && min2 < 0) {
            return Math.max(max1 * max2 * max3, min1 * min2 * max1);
        }
        return max1 * max2 * max3;
    }
}

class Solution2 {
    public int maximumProduct(int[] nums) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Input is invalid");
        }

        if (nums.length == 3) {
            return nums[0] * nums[1] * nums[2];
        }

        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;

        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;

        for (int n : nums) {
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }

            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
        }

        if (min1 < 0 && min2 < 0) {
            return Math.max(max1 * max2 * max3, min1 * min2 * max1);
        }
        return max1 * max2 * max3;
    }
}
