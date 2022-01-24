// LeetCode Question URL: https://leetcode.com/problems/largest-number/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sorting via Custom Comparator
 *
 * Sort in descending order of (s1+s2), (s2+s1)
 *
 * Refer:
 * https://leetcode.com/problems/largest-number/discuss/53158/My-Java-Solution-to-share
 *
 * <pre>
 * Merge Sort Complexity Equation:
 * T(N) = 2 * T(N/2) + O(2L*N) ==> here 2L represents the length of a+b
 * Thus, T(N) = 2L*N * logN
 *
 * Time Complexity: O(N*L + 2*L*N*logN + N*L) = O(L*N*logN). Here L is bounded
 * by 10, thus TC = O(N * log N)
 *
 * Space Complexity: O(2*N*L + Space required for Sorting) = O(N) as L is bounded by 10.
 * </pre>
 *
 * N = Length of input array. L = Average number of digits in each number. This
 * is bounded by 10 for int.
 */
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            return "0";
        }

        int len = nums.length;
        if (len == 1) {
            return String.valueOf(nums[0]);
        }

        String[] numStrs = new String[len];
        for (int i = 0; i < len; i++) {
            numStrs[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numStrs, (a, b) -> {
            String x = new StringBuilder(a).append(b).toString();
            String y = new StringBuilder(b).append(a).toString();
            // (b+a) < (a+b)
            return y.compareTo(x);
        });

        if ("0".equals(numStrs[0])) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for (String str : numStrs) {
            sb.append(str);
        }
        return sb.toString();
    }
}
