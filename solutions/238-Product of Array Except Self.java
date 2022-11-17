// LeetCode Question URL: https://leetcode.com/problems/product-of-array-except-self/
// LeetCode Discuss URL:

import java.util.*;

/**
 * 2 Pass solution
 *
 * Refer:
 * https://leetcode.com/problems/product-of-array-except-self/discuss/65622/Simple-Java-solution-in-O(n)-without-extra-space/67603
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1) -> Excluding the result space. O(N) -> Including the
 * result space.
 *
 * N = Length of the input array.
 */
class Solution {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        int[] result = new int[len];
        if (len == 0) {
            return result;
        }

        result[0] = 1;
        if (len == 1) {
            return result;
        }

        for (int i = 1; i < len; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        int product = 1;
        for (int i = len - 2; i >= 0; i--) {
            product *= nums[i + 1];
            result[i] *= product;
        }

        return result;
    }
}

class Solution2 {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new int[0];
        }

        int len = nums.length;
        int[] result = new int[len];

        int product = 1;
        for (int i = 0; i < len; i++) {
            result[i] = product;
            product *= nums[i];
        }

        product = 1;
        for (int i = len - 1; i >= 0; i--) {
            result[i] *= product;
            product *= nums[i];
        }

        return result;
    }
}

/**
 * If asked in interview to code a One-Pass solution code this. The number of
 * operations in the for loop are same as above. Thus not truly one-pass
 * solution. Also we have to fill the array with 1s.
 */
class Solution3 {
    public int[] productExceptSelf(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        int[] result = new int[len];
        if (len == 0) {
            return result;
        }

        Arrays.fill(result, 1);
        if (len == 1) {
            return result;
        }

        int prefix = 1;
        int suffix = 1;
        for (int i = 0; i < len; i++) {
            result[i] *= prefix;
            prefix *= nums[i];
            result[len - 1 - i] *= suffix;
            suffix *= nums[len - 1 - i];
        }

        return result;
    }
}
