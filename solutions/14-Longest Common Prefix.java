// LeetCode Question URL: https://leetcode.com/problems/longest-common-prefix/
// LeetCode Discuss URL: https://leetcode.com/problems/longest-common-prefix/discuss/1545132/Java-or-TC:-O(N*minLen)-or-SC:-O(1)-or-Constant-Space-Vertical-Scanning-solution

/**
 * Vertical Scanning or Column Scanning. Here character at the same index is
 * compared. If the character at the same index in all input strings is same,
 * add it to the prefix. Else, return the found prefix.
 *
 * Using substring instead of string builder to save on space.
 *
 * Time Complexity: O(N * minLen)
 *
 * Space Complexity: O(1)
 *
 * N = Number of strings in strs. minLen = Length of the smallest string.
 */
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int numStrs = strs.length;
        if (numStrs == 1) {
            return strs[0];
        }
        if (numStrs == 0 || "".equals(strs[0]) || "".equals(strs[numStrs - 1])) {
            return "";
        }

        int prefix = 0;
        // No need to find the minLen as this nested loop will exit when we reach the
        // end of minLen string.
        while (prefix < strs[0].length()) {
            char c = strs[0].charAt(prefix);
            for (int i = 1; i < numStrs; i++) {
                if (strs[i].length() == prefix || c != strs[i].charAt(prefix)) {
                    return strs[0].substring(0, prefix);
                }
            }
            prefix++;
        }

        return strs[0].substring(0, prefix);
    }
}
