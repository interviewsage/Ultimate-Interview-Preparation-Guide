// LeetCode Question URL: https://leetcode.com/problems/longest-common-prefix/
// LeetCode Discuss URL:

/**
 * Vertical Scanning or Column Scanning. Here character at the same index is
 * compared. If the character at the same index in all input string is same, add
 * it to the prefix. Else, return the found prefix.
 *
 * Using substring instead of string builder to save on space.
 *
 * Time Complexity: O(N * minLen)
 *
 * Space Complexity: O(1)
 *
 * N = Number of strings in strs. minLen = Length of the smallest string.
 */
class Solution1 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int numStrs = strs.length;
        if (numStrs == 0 || "".equals(strs[0]) || "".equals(strs[numStrs - 1])) {
            return "";
        }

        String firstStr = strs[0];
        if (numStrs == 1) {
            return firstStr;
        }

        int prefixLen = 0;

        while (prefixLen < firstStr.length()) {
            char c = firstStr.charAt(prefixLen);
            for (int i = 1; i < numStrs; i++) {
                if (strs[i].length() <= prefixLen || strs[i].charAt(prefixLen) != c) {
                    return firstStr.substring(0, prefixLen);
                }
            }
            prefixLen++;
        }

        return firstStr.substring(0, prefixLen);
    }
}

/**
 * Using substring instead of string builder to save on space.
 *
 * Vertical Scanning or Column Scanning. Here character at the same index is
 * compared. If the character at the same index is same its added to the prefix.
 * Else, return the found prefix.
 *
 * Time Complexity: O(N * minLen + N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of strings in strs. minLen = Length of smallest string in strs.
 */
class Solution2 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int minLen = Integer.MAX_VALUE;
        for (String s : strs) {
            minLen = Math.min(minLen, s.length());
        }

        for (int i = 0; i < minLen; i++) {
            char c1 = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (c1 != strs[j].charAt(i)) {
                    return strs[0].substring(0, i);
                }
            }
        }

        return strs[0].substring(0, minLen);
    }
}

/**
 * Vertical Scanning or Column Scanning. Here character at the same index is
 * compared. If the character at the same index is same its added to the prefix.
 * Else, return the found prefix.
 *
 * Time Complexity: O(N * minLen + N)
 *
 * Space Complexity: O(minLen)
 *
 * N = Number of strings in strs. minLen = Length of smallest string in strs.
 */
class Solution3 {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        int minLen = Integer.MAX_VALUE;
        for (String s : strs) {
            minLen = Math.min(minLen, s.length());
        }

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < minLen; i++) {
            char c1 = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (c1 != strs[j].charAt(i)) {
                    return sb.toString();
                }
            }
            sb.append(c1);
        }

        return sb.toString();
    }
}
