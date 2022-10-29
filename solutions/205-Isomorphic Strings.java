// LeetCode Question URL: https://leetcode.com/problems/isomorphic-strings/
// LeetCode Discuss URL: https://leetcode.com/problems/isomorphic-strings/discuss/1555761/Java-or-TC:-O(N)-or-SC:-O(N)-or-Simple-One-Pass-solution-using-a-Map-and-Set

import java.util.*;

/**
 * One-Pass solution using HashMap and HashSet
 *
 * <pre>
 * Time Complexity:
 * - O(1) --> This will be in case S & T are of different lengths
 * - O(N) --> If S & T are of same length, say N, the code will check the Isomorphic property in one-pass.
 *
 * Space Complexity:
 * - O(1) --> This will be in case S & T are of different lengths
 * - O(3*U) = O(U) --> If S & T are of same length, say N, the code will require 3 * U space for HashMap and HashSet.
 * U = Unique chars
 * </pre>
 */
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null) {
            throw new IllegalArgumentException("Input strings are null");
        }
        int len = s.length();
        if (len != t.length()) {
            return false;
        }
        if (len == 0) {
            return true;
        }

        Map<Character, Character> map = new HashMap<>();
        Set<Character> visitedT = new HashSet<>();

        for (int i = 0; i < len; i++) {
            char sChar = s.charAt(i);
            char tChar = t.charAt(i);
            // Finding the old character of T that maps to current character of S.
            Character matchingChar = map.get(sChar);

            if (matchingChar == null) {
                // Since the current char of S does not map to any older char of T, try adding
                // tChar to the visitedT set to verify if tChar is already seen before or not.
                if (!visitedT.add(tChar)) {
                    return false;
                }
                map.put(sChar, tChar);
            } else if (matchingChar != tChar) {
                return false;
            }
        }

        return true;
    }
}
