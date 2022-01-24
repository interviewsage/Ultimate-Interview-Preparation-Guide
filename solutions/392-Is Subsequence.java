// LeetCode Question URL: https://leetcode.com/problems/is-subsequence/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using 2 indexes. This solution is good for cases when every time string t is
 * different. If T is same and different s strings are passed, check the below
 * binary search function.
 *
 * Refer: https://leetcode.com/problems/is-subsequence/solution/
 *
 * Time Complexity: O(T)
 *
 * Space Complexity: O(1)
 *
 * T = length of string t
 */
class Solution1 {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null || s.length() > t.length()) {
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0) {
            return true;
        }

        int sIdx = 0;
        for (int i = 0; i < tLen; i++) {
            // Early Exit if not enough char are left in t.
            if (tLen - i < sLen - sIdx) {
                return false;
            }
            if (s.charAt(sIdx) == t.charAt(i)) {
                if (sIdx == sLen - 1) {
                    return true;
                }
                sIdx++;
            }
        }

        return false;
    }
}

/**
 * Pre-process T and use Binary Search to check for isSubsequence. (Follow-Up)
 *
 * Refer: https://leetcode.com/problems/is-subsequence/solution/
 *
 * Preprocessing Time Complexity: O(T)
 *
 * Time Complexity of isSubsequence: O(S log(T))
 *
 * Space Complexity: O(T)
 *
 * T = length of string t
 */
class Solution2 {
    public boolean isSubsequence(String s, String t) {
        if (s == null || t == null || s.length() > t.length()) {
            return false;
        }

        int sLen = s.length();
        int tLen = t.length();
        if (sLen == 0) {
            return true;
        }

        Map<Character, List<Integer>> tMap = new HashMap<>();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            tMap.putIfAbsent(c, new ArrayList<>());
            tMap.get(c).add(i);
        }

        // Explore T string from tIdx onwards
        int tIdx = 0;
        for (int i = 0; i < sLen; i++) {
            // Early Exit if not enough char are left in T.
            if (tLen - tIdx < sLen - i) {
                return false;
            }

            char c = s.charAt(i);
            List<Integer> charIndexes = tMap.get(c);
            if (charIndexes == null) {
                return false;
            }

            int foundIdx = Collections.binarySearch(charIndexes, tIdx);
            if (foundIdx < 0) {
                foundIdx = -foundIdx - 1;
            }
            if (foundIdx == charIndexes.size()) {
                return false;
            }
            tIdx = charIndexes.get(foundIdx) + 1;
        }

        return true;
    }
}

/**
 * Using BinarySearch + Trie for follow-up
 *
 * Refer:
 * https://leetcode.com/problems/is-subsequence/discuss/87302/Binary-search-solution-for-follow-up-with-detailed-comments/92266
 */