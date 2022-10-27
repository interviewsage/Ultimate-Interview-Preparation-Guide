// LeetCode Question URL: https://leetcode.com/problems/longest-palindromic-substring/
// LeetCode Discuss URL: https://leetcode.com/problems/longest-palindromic-substring/discuss/1539190/Java-or-TC:-O(N2)-or-SC:-O(1)-or-Two-Optimized-solutions-with-Early-Exit-Condition

/**
 * Optimized Solution for continuous repeating characters. Expand around center.
 *
 * Time Complexity:
 * - In worst case we will try to use each char as center.
 * - extendPalindrome can take max N/2 time.
 * - Substring take N time to create the result string.
 *
 * Total Time Complexity: O(N * N / 2 + N) = O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string s.
 */
class Solution {
    public String longestPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }
        int len = s.length();
        if (len <= 1) {
            return s;
        }

        int[] maxStartOffset = new int[] { 0, 1 };
        int i = 0;
        while (i < len) {
            int start = i++;
            while (i < len && s.charAt(i) == s.charAt(start)) {
                i++;
            }

            // Expanding palindrome around the center defined by [start, i-1]
            extendPalindrome(s, start - 1, i, maxStartOffset);

            // Early Exit Condition
            // Remaining Characters = len - i - 1
            // Longest palindrome possible using remaining char = 1 + (len - i - 1) * 2
            // Centered at i ^^^
            // We can safely exit if current maxStartOffset[1] >= 1 + (len - i - 1) * 2
            if (maxStartOffset[1] >= 1 + (len - i - 1) * 2) {
                break;
            }
        }

        return s.substring(maxStartOffset[0], maxStartOffset[0] + maxStartOffset[1]);
    }

    private void extendPalindrome(String s, int left, int right, int[] maxStartOffset) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        int newLength = right - left - 1;
        if (maxStartOffset[1] < newLength) {
            maxStartOffset[0] = left + 1;
            maxStartOffset[1] = newLength;
        }
    }
}

/**
 * DO NOT REFER TO SOLUTIONS MENTIONED BELOW
 *
 * DO NOT REFER TO SOLUTIONS MENTIONED BELOW
 */

/**
 * Expand around center. Passing an int array of size two to keep track of max
 * length.
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string s.
 */
class Solution2 {
    public String longestPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        int[] maxStartOffset = new int[] { 0, 1 };

        for (int i = 0; i < len - 1; i++) {
            expandPalindrome(s, i, i, maxStartOffset);
            expandPalindrome(s, i, i + 1, maxStartOffset);

            // Early Exit Condition
            // Remaining Characters = len - i - 1
            // Longest palindrome possible using remaining chars = (len - i - 1) * 2
            // We can safely exit if current maxStartOffset[1] >= (len - i - 1) * 2
            if (maxStartOffset[1] >= (len - i - 1) * 2) {
                break;
            }
        }

        return s.substring(maxStartOffset[0], maxStartOffset[0] + maxStartOffset[1]);
    }

    private void expandPalindrome(String s, int left, int right, int[] maxStartOffset) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        if (right - left - 1 > maxStartOffset[1]) {
            maxStartOffset[0] = left + 1;
            maxStartOffset[1] = right - left - 1;
        }
    }
}

/**
 * Optimized Solution for continuous repeating characters. Expand around center.
 * Helper function returns the length of the palindrome.
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string s.
 */
class Solution3 {
    public String longestPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        int maxStart = 0;
        int maxLen = 0;

        int i = 0;
        while (i < len) {
            int start = i++;
            while (i < len && s.charAt(i) == s.charAt(start)) {
                i++;
            }
            // Expanding palindrome around the center defined by [start, i-1]
            int newLen = expandPalindrome(s, start - 1, i);
            if (newLen > maxLen) {
                maxLen = newLen;
                // Center of the palindrome: (start + i - 1) / 2
                // Subtracting half of newLen from center to get the start index.
                maxStart = (start + i - 1) / 2 - (newLen - 1) / 2;
                // maxStart = start + ((i - 1) - start) / 2 - (newLen - 1) / 2;
                // maxStart = (start + i - newLen) / 2;
            }

            // Early Exit Condition
            // Remaining Characters = len - i - 1
            // Longest palindrome possible using remaining chars = (len - i - 1) * 2
            // We can safely exit if current maxLen >= (len - i - 1) * 2
            if (maxLen >= (len - i - 1) * 2) {
                break;
            }
        }

        return s.substring(maxStart, maxStart + maxLen);
    }

    private int expandPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

/**
 * Expand around center. Helper function returns the length of the palindrome.
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input string s.
 */
class Solution4 {
    public String longestPalindrome(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        int maxStart = 0;
        int maxLen = 0;

        for (int i = 0; i < len - 1; i++) {
            int len1 = expandPalindrome(s, i, i);
            int len2 = expandPalindrome(s, i, i + 1);

            int newLen = Math.max(len1, len2);
            if (newLen > maxLen) {
                maxLen = newLen;
                maxStart = i - (newLen - 1) / 2;
            }

            // Early Exit Condition
            // Remaining Characters = len - i - 1
            // Longest palindrome possible using remaining chars = (len - i - 1) * 2
            // We can safely exit if current maxLen >= (len - i - 1) * 2
            if (maxLen >= (len - i - 1) * 2) {
                break;
            }
        }

        return s.substring(maxStart, maxStart + maxLen);
    }

    private int expandPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }
}

/**
 * Expand around center. Helper function returns the length of the palindrome.
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input string s.
 */
class SolutionDoNotSolveInInterview {
    public String longestPalindrome(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() < 2) {
            return s;
        }

        String max = "";
        for (int i = 0; i < s.length() - 1; i++) {
            String s1 = extendPalindrome(s, i, i);
            String s2 = extendPalindrome(s, i, i + 1);
            if (s1.length() > max.length()) {
                max = s1;
            }
            if (s2.length() > max.length()) {
                max = s2;
            }
        }

        return max;
    }

    private String extendPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return s.substring(left + 1, right);
    }
}
