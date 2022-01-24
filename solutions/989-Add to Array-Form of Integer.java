// LeetCode Question URL: https://leetcode.com/problems/add-to-array-form-of-integer/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Iterate over the input array from end to start and keep adding K. This
 * solution handles the int overflow.
 *
 * This solution can be optimized with early exit if the input was a List -
 * https://leetcode.com/problems/add-to-array-form-of-integer/solution/393863
 *
 * Time Complexity: O(max(N, log10(K))) = O(max(N, 10))
 *
 * Space Complexity: O(1)
 *
 * N = Length of A. K = Number of digits in K
 */
class Solution1 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        if (num == null) {
            throw new IllegalArgumentException("input num is null");
        }

        LinkedList<Integer> result = new LinkedList<>();

        for (int i = num.length - 1; i >= 0; i--) {
            int sum = num[i] + k % 10;
            result.addFirst(sum % 10);
            k = k / 10 + sum / 10;
        }

        while (k > 0) {
            result.addFirst(k % 10);
            k = k / 10;
        }

        return result;
    }
}

/**
 * Iterate over the input array from end to start and keep adding K.
 *
 * This solution can be optimized with early exit if the input was a List -
 * https://leetcode.com/problems/add-to-array-form-of-integer/solution/393863
 *
 * Time Complexity: O(max(N, log10(K))) = O(max(N, 10))
 *
 * Space Complexity: O(1)
 *
 * N = Length of A. K = Number of digits in K
 */
class Solution2 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        if (num == null) {
            throw new IllegalArgumentException("input num is null");
        }

        LinkedList<Integer> result = new LinkedList<>();

        for (int i = num.length - 1; i >= 0; i--) {
            int sum = num[i] + k;
            result.addFirst(sum % 10);
            k = sum / 10;
        }

        while (k > 0) {
            result.addFirst(k % 10);
            k = k / 10;
        }

        return result;
    }
}
