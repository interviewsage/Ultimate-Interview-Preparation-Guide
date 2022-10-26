// LeetCode Question URL: https://leetcode.com/problems/next-greater-element-i/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Use HashMap to store the next greater element of all numbers in nums2 array.
 * This can be achieved using a Stack that maintains the list of numbers in
 * descending order.
 *
 * Refer:
 * https://leetcode.com/problems/next-greater-element-i/discuss/97595/Java-10-lines-linear-time-complexity-O(n)-with-explanation
 *
 * Time Complexity: O(2*N2 + N1)
 *
 * Space Complexity: O(2*N2) --> Here Keys in Map and Stack combined will always
 * be N2.
 *
 * N1 = Length of nums1. N2 = Length of nums2.
 */
class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len1 = nums1.length;
        int[] result = new int[len1];
        if (len1 == 0) {
            return result;
        }
        int len2 = nums2.length;
        if (len2 == 0) {
            Arrays.fill(result, -1);
            return result;
        }

        Map<Integer, Integer> nextGreaterElementMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        for (int n : nums2) {
            while (!stack.isEmpty() && stack.peek() < n) {
                nextGreaterElementMap.put(stack.pop(), n);
            }
            stack.push(n);
        }

        for (int i = 0; i < len1; i++) {
            result[i] = nextGreaterElementMap.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}
