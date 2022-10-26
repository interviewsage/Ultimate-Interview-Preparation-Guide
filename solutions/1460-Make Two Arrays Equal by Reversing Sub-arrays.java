// LeetCode Question URL: https://leetcode.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Facebook Follow-Up: What if the question is asking, what is the minimum number of swaps?
 * https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/discuss/660615/C++Python-1-lines/560969
 * https://leetcode.com/discuss/interview-question/1137426/Facebook-or-Minimizing-Permutations
 */

/**
 * CountMap
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/make-two-arrays-equal-by-reversing-subarrays/discuss/661521/Java-O(n)-time-and-space
 * </pre>
 *
 * Time Complexity: O(2 * N)
 *
 * Space Complexity: O(N)
 */
class Solution1 {
    public boolean canBeEqual(int[] target, int[] arr) {
        if (target == null || arr == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = target.length;
        if (len != arr.length) {
            return false;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            countMap.put(target[i], countMap.getOrDefault(target[i], 0) + 1);
            countMap.put(arr[i], countMap.getOrDefault(arr[i], 0) - 1);
            if (countMap.size() > len) {
                return false;
            }
        }
        for (int val : countMap.values()) {
            if (val != 0) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Bit Manipulation + Product
 *
 * THIS SOLUTION IS INCORRECT AND DOESN'T WORK FOR ALL TEST CASES ANYMORE. Refer
 * to following example:
 * [1,2,2,8,8,16]
 * [1,4,4,4,4,16]
 * Here, xor = 0
 * prodTarget = 2^12
 * prodArr = 2^12
 * Here both prodTarget & prodArr are equal and xor is zero.. but the arrays are
 * not same. Thus this solution will not work for all cases.
 *
 * NOTE: This solution only works for positive numbers.
 *
 * Refer: Fastest solution in submissions
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public boolean canBeEqual(int[] target, int[] arr) {
        if (target == null || arr == null || target.length != arr.length) {
            return false;
        }

        int len = target.length;
        if (len == 0) {
            return true;
        }

        int xor = 0;
        int prodTarget = 1;
        int prodArr = 1;
        for (int i = 0; i < len; i++) {
            xor ^= target[i] ^ arr[i];
            prodTarget *= target[i];
            prodArr *= arr[i];
        }

        return prodTarget == prodArr && xor == 0;
    }
}
