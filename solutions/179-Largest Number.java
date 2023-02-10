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
 * T(N) = 2 * T(N/2) + O(10*L*N) ==> here 10*L represents the operations inside the comparator.
 * Thus, T(N) = 10*L*N * logN
 *
 * Time Complexity: O(N*L + 10*L*N*logN + N*L + N*L) = O(L*N*logN). Here L is bounded
 * by 10, thus TC = O(N * log N)
 *
 * Space Complexity: O(N*L + N*L + Space required for Sorting) = O(N) as L is bounded by 10.
 * </pre>
 *
 * N = Length of input array. L = Average number of digits in each number. This
 * is bounded by 10 for int.
 */
class Solution {
    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input nums array is null or empty");
        }

        int len = nums.length;
        if (len == 1) {
            return String.valueOf(nums[0]);
        }

        String[] numsStr = new String[len];
        for (int i = 0; i < len; i++) {
            numsStr[i] = String.valueOf(nums[i]);
        }

        Arrays.sort(numsStr, (a, b) -> {
            String n1 = new StringBuilder(a).append(b).toString();
            String n2 = new StringBuilder(b).append(a).toString();
            // (b+a) < (a+b)
            return n2.compareTo(n1);
        });

        if ("0".equals(numsStr[0])) {
            return "0";
        }

        StringBuilder result = new StringBuilder();
        for (String s : numsStr) {
            result.append(s);
        }

        return result.toString();
    }
}
