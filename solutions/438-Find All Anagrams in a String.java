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
            throw new IllegalArgumentException("Input string is null");
        }

        List<Integer> result = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length();
        if (sLen * pLen == 0 || sLen < pLen) {
            return result;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            map.put(p.charAt(i), map.getOrDefault(p.charAt(i), 0) + 1);
        }

        int toBeMatched = map.size();
        int start = 0;
        int end = 0;

        while (end < sLen) {
            char eChar = s.charAt(end);
            if (map.containsKey(eChar)) {
                int count = map.get(eChar);
                if (count == 1) {
                    toBeMatched--;
                }
                map.put(eChar, count - 1);
            }
            end++;

            if (end - start > pLen) {
                char sChar = s.charAt(start);
                if (map.containsKey(sChar)) {
                    int count = map.get(sChar);
                    if (count == 0) {
                        toBeMatched++;
                    }
                    map.put(sChar, count + 1);
                }
                start++;
            }

            if (toBeMatched == 0) {
                result.add(start);
            }
        }

        return result;
    }
}
