// LeetCode Question URL: https://leetcode.com/problems/implement-strstr/
// LeetCode Discuss URL: https://leetcode.com/problems/implement-strstr/discuss/1545167/Java-or-BruteForce-greater-TC:O(M*N)-SC:-O(1)-or-KMP-greater-TC:-O(M+N)-SC:O(N)or-Two-Solutions

/**
 * Brute Force string matching.
 *
 * Time Complexity: O(M * N)
 *
 * Space Complexity: O(1)
 *
 * M = Length of haystack string. N = length of needle string.
 */
class Solution1 {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            throw new IllegalArgumentException("Input strings are null");
        }

        int hLen = haystack.length();
        int nLen = needle.length();
        if (nLen == 0) {
            return 0;
        }
        if (hLen < nLen) {
            return -1;
        }

        for (int i = 0; i <= hLen - nLen; i++) {
            int j = 0;
            while (j < nLen && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == nLen) {
                return i;
            }
        }

        return -1;
    }
}

/**
 * Using KMP Algorithm
 *
 * Time Complexity: O(M + N). O(N) to create lookup table. O(M) to find the
 * needle in haystack.
 *
 * Space Complexity: O(N). This is required to save the lookup table.
 *
 * M = Length of haystack string. N = length of needle string.
 */
class Solution2 {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        int nLen = needle.length();
        int hLen = haystack.length();
        if (nLen == 0) {
            return 0;
        }
        if (hLen == 0) {
            return -1;
        }

        int[] table = kmpLookupTable(needle);
        int i = 0;
        int j = 0;
        while (i < hLen && j < nLen) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                if (j > 0) {
                    j = table[j - 1];
                } else {
                    i++;
                }
            }
        }

        if (j == nLen) {
            return i - j;
        }
        return -1;
    }

    private int[] kmpLookupTable(String s) {
        int[] table = new int[s.length()];
        int i = 1;
        int index = 0;
        while (i < s.length()) {
            if (s.charAt(i) == s.charAt(index)) {
                table[i] = index + 1;
                index++;
                i++;
            } else {
                if (index > 0) {
                    index = table[index - 1];
                } else {
                    table[i] = 0;
                    i++;
                }
            }
        }
        return table;
    }
}
