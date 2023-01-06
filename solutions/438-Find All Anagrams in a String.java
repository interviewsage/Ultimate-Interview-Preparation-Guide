// LeetCode Question URL: https://leetcode.com/problems/find-all-anagrams-in-a-string/
// LeetCode Discuss URL: https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/1500039/Java-or-TC:-O(S+P)-or-SC:-O(1)-or-Sliding-window-solution

import java.util.*;

/**
 * Sliding window (In this solution windows of size = p.length() is always
 * maintained.)
 *
 * Time Complexity: O(S + P)
 *
 * Space Complexity: O(M, P). We only save unique characters in P. It will be
 * O(1) as there are only 26 alphabets.
 *
 * S = Length of input string s. P = Length of input string p. M = Range of
 * character set which is 26 in this case.
 */
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        if (s == null || p == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int sLen = s.length();
        int pLen = p.length();
        List<Integer> result = new ArrayList<>();
        // (Ask Interviewer) If we have to handle this, then add all indexes using a for
        // loop and then return it.
        // if (pLen == 0) {
        // return true;
        // }
        if (sLen < pLen) {
            return result;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < pLen; i++) {
            char c = p.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        int toBeMatched = countMap.size();

        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            Integer count = countMap.get(c);
            if (count != null) {
                if (count == 1) {
                    toBeMatched--;
                }
                countMap.put(c, count - 1);
            }

            if (i >= pLen) {
                c = s.charAt(i - pLen);
                count = countMap.get(c);
                if (count != null) {
                    if (count == 0) {
                        toBeMatched++;
                    }
                    countMap.put(c, count + 1);
                }
            }

            if (toBeMatched == 0) {
                result.add(i - pLen + 1);
            }
        }

        return result;
    }
}
