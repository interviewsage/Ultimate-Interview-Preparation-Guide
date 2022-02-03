// LeetCode Question URL: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer: Also check discussion in below link
 * https://leetcode.com/problems/remove-duplicate-letters/discuss/76769/Java-solution-using-Stack-with-comments
 *
 * Time Complexity: O(N + 2*N + U)-> N for populating countMap, 2*N each
 * character is added once and can be removed once from stack, and U for
 * sb.toString()
 *
 * Space Complexity: O(4 * U) --> 2U in countMap, U in visited, and U in sb.
 *
 * N = Length of the input string. U = Unique Chars = min(26, N)
 */
class Solution {
    public String smallestSubsequence(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int len = s.length();
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        Set<Character> visited = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c1 = s.charAt(i);
            countMap.put(c1, countMap.get(c1) - 1);
            if (visited.contains(c1)) {
                continue;
            }

            int sbLen = sb.length();
            // If current character is smaller than the last character in StringBuilder that
            // also occurs later in the string -> last character can be removed and added
            // later. Example SB = "bc" remaining string "abc" then a can remove c & b as
            // they can be added later.
            while (sbLen > 0 && sb.charAt(sbLen - 1) > c1 && countMap.get(sb.charAt(sbLen - 1)) > 0) {
                visited.remove(sb.charAt(sbLen - 1));
                sbLen--;
            }

            sb.setLength(sbLen);
            sb.append(c1);
            visited.add(c1);
        }

        return sb.toString();
    }
}
