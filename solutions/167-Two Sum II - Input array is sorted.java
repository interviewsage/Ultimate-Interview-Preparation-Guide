// LeetCode Question URL: https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Two pointers
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if (numbers == null) {
            throw new IllegalArgumentException("Input numbers array is invalid");
        }

        int left = 0;
        int right = numbers.length - 1;
        while (left < right) {
            // Long is only needed if there can be an overflow.
            // Ask Interviewer
            long sum = (long) numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] { left + 1, right + 1 };
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        throw new NoSuchElementException("Two numbers with sum to " + target + " not found");
    }
}
