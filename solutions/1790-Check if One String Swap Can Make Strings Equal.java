// LeetCode Question URL: https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/
// LeetCode Discuss URL:

import java.util.*;

/**
 * One-Pass solution.
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/check-if-one-string-swap-can-make-strings-equal/discuss/1108610/My-Java-solution-0ms
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input string.
 */
class Solution {
    public boolean areAlmostEqual(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length()) {
            return false;
        }

        int len = s1.length();
        if (len == 0) {
            return true;
        }

        List<Integer> diffIdx = new ArrayList<>(2);

        for (int i = 0; i < len; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                if (diffIdx.size() == 2) {
                    return false;
                }
                diffIdx.add(i);
            }
        }

        return diffIdx.isEmpty() || (diffIdx.size() == 2 && s1.charAt(diffIdx.get(0)) == s2.charAt(diffIdx.get(1))
                && s1.charAt(diffIdx.get(1)) == s2.charAt(diffIdx.get(0)));
    }
}
