// LeetCode Question URL: https://leetcode.com/problems/permutation-sequence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer: Very detailed explanation
 * https://leetcode.com/problems/permutation-sequence/discuss/22507/"Explain-like-I'm-five"-Java-Solution-in-O(n)
 *
 * Time Complexity: O(N^2).
 *
 * Space Complexity: O(N).
 */
class Solution {
    public String getPermutation(int n, int k) {
        if (n <= 0 || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n == 1 && k == 1) {
            return "1";
        }

        List<Integer> nums = new ArrayList<>();
        int[] factorial = new int[n+1];
        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            nums.add(i);
            factorial[i] = i * factorial[i-1];
        }

        if (k > factorial[n]) {
            throw new IllegalArgumentException("Invalid k value");
        }

        k--;
        StringBuilder sb = new StringBuilder();

        for (int i = n-1; i >= 0; i--) {
            int idx = k / factorial[i];
            sb.append(nums.remove(idx));
            k %= factorial[i];
        }

        return sb.toString();
    }
}
