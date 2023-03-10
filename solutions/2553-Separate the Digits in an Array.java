// LeetCode Question URL: https://leetcode.com/problems/separate-the-digits-in-an-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Iterate from reverse and then split the numbers. Finally reverse to get the
 * result
 *
 * Time Complexity: O(2 * Number of digits) = O(2 * N * 10) = O(N)
 *
 * Space Complexity: O(Number of Digits) = O(N * 10) = O(N)
 *
 * N = Length of input array.
 */
class Solution {
    public int[] separateDigits(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return new int[0];
        }

        List<Integer> list = new ArrayList<>();
        for (int i = len - 1; i >= 0; i--) {
            do {
                list.add(nums[i] % 10);
                nums[i] /= 10;
            } while (nums[i] > 0);
        }

        int resLen = list.size();
        int[] result = new int[resLen];
        for (int i = 0; i < resLen; i++) {
            result[i] = list.get(resLen - 1 - i);
        }

        return result;
    }
}
