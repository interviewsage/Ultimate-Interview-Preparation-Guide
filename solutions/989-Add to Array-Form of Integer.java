// LeetCode Question URL: https://leetcode.com/problems/add-to-array-form-of-integer/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Iterate over the input array from end to start and keep adding K. This
 * solution handles the int overflow.
 *
 * This solution can be optimized with early exit if the input was a List and
 * can be modified in-place -
 * https://leetcode.com/problems/add-to-array-form-of-integer/solution/393863
 *
 * Time Complexity: O(max(N, log10(K))) = O(max(N, 10)) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of A. K = Number of digits in K
 */
class Solution1 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        if (num == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        LinkedList<Integer> result = new LinkedList<>();
        int carry = 0;
        int idx = num.length - 1;

        while (idx >= 0 || k > 0 || carry > 0) {
            if (k > 0) {
                carry += k % 10;
                k /= 10;
            }
            if (idx >= 0) {
                carry += num[idx--];
            }
            result.addFirst(carry % 10);
            carry /= 10;
        }

        return result;
    }
}

/**
 * NO NEED TO SOLVE BELOW ANSWERS IN THE INTERVIEW
 */

/**
 * Refer to above solution for explanation
 */
class Solution2 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        if (num == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
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
 * Time Complexity: O(max(N, log10(K))) = O(max(N, 10)) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of A. K = Number of digits in K
 */
class Solution3 {
    public List<Integer> addToArrayForm(int[] num, int k) {
        if (num == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        LinkedList<Integer> result = new LinkedList<>();
        int idx = num.length - 1;
        while (idx >= 0 || k > 0) {
            k += idx >= 0 ? num[idx--] : 0;
            result.addFirst(k % 10);
            k = k / 10;
        }

        return result;
    }
}

/**
 * Refer to above solution for explanation
 */
class Solution4 {
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
