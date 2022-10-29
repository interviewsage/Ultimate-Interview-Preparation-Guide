// LeetCode Question URL: https://leetcode.com/problems/buddy-strings/
// LeetCode Discuss URL:

import java.util.*;

/**
 * One-Pass solution.
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/buddy-strings/discuss/141780/Easy-Understood
 * 2) https://leetcode.com/problems/buddy-strings/discuss/891469/C++JavaPython-One-pass-Clean-and-Concise-O(N)
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(min(U, Size of Character Set)). If Character Set = 26..
 * then it will be O(1)
 *
 * N = Length of input string. U = Unique chars in S.
 */
class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s == null || goal == null) {
            throw new IllegalArgumentException("Input strings are slower");
        }
        int sLen = s.length();
        if (sLen <= 1 || sLen != goal.length()) {
            return false;
        }

        Set<Character> foundChars = new HashSet<>();
        Integer swapIdx = null;
        boolean swapComplete = false;

        for (int i = 0; i < sLen; i++) {
            char sChar = s.charAt(i);
            char goalChar = goal.charAt(i);
            if (sChar != goalChar) {
                if (swapIdx == null) {
                    swapIdx = i;
                } else if (!swapComplete && s.charAt(swapIdx) == goalChar && goal.charAt(swapIdx) == sChar) {
                    swapComplete = true;
                } else {
                    return false;
                }
            } else if (swapIdx == null && foundChars.size() == i) {
                foundChars.add(sChar);
            }
        }

        return swapIdx != null ? swapComplete : foundChars.size() < sLen;
    }
}

class Solution2 {
    public boolean buddyStrings(String s, String goal) {
        if (s == null || goal == null || s.length() != goal.length() || s.length() <= 1) {
            return false;
        }

        Set<Character> uniqueChars = new HashSet<>();
        List<Integer> diffIdx = new ArrayList<>(2);
        int len = s.length();

        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            char c2 = goal.charAt(i);

            if (c1 != c2) {
                if (diffIdx.size() == 2) {
                    return false;
                }
                diffIdx.add(i);
            } else if (diffIdx.size() == 0 && uniqueChars.size() == i) {
                uniqueChars.add(c1);
            }
        }

        if (diffIdx.size() == 0) {
            return uniqueChars.size() < len;
        }

        return diffIdx.size() == 2 && s.charAt(diffIdx.get(0)) == goal.charAt(diffIdx.get(1))
                && s.charAt(diffIdx.get(1)) == goal.charAt(diffIdx.get(0));
    }
}
