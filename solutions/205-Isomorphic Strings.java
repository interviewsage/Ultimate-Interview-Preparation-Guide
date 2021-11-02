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
 * - O(3*N) = O(N) --> If S & T are of same length, say N, the code will require 3 * N space for HashMap and HashSet.
 * </pre>
 */
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s == null || t == null || s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> visitedT = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char curS = s.charAt(i);
            char curT = t.charAt(i);

            // Finding the old character of T that maps to current character of S.
            Character oldT = map.put(curS, curT);

            if (oldT == null) {
                // Since the current char of S does not map to any older char of T, try adding
                // curT to the visitedT set to verify if curT is already seen before or not.
                if (!visitedT.add(curT)) {
                    return false;
                }
            } else if (!oldT.equals(curT)) {
                return false;
            }
        }

        return true;
    }
}
