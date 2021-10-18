// LeetCode Question URL: https://leetcode.com/problems/palindrome-permutation/
// LeetCode Discuss URL: https://leetcode.com/problems/palindrome-permutation/discuss/1527941/Java-or-TC:-O(N)-or-SC:-O(N)-or-Early-Exit-and-Space-Optimized-HashSet-solution

import java.util.*;

/**
 * Using HashSet to find chars occurring only once. Only saving the characters
 * appearing odd number of times in the HashSet. If the difference in size of
 * HashSet and the remaining characters grows more than 1, we can preemptively
 * return false.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N/2+2) = O(N). HashSet will grow maximum to N/2+2 size.
 *
 * N = Length of the input string.
 */
class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null) {
            return false;
        }
        if (s.length() <= 1) {
            return true;
        }

        HashSet<Character> set = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.add(c)) {
                set.remove(c);
            }
            // Below condition is a simplification of (set.size() - (s.length()-1-i)) >= 2
            if (set.size() > (s.length() - i)) {
                return false;
            }
        }

        return true;
    }
}
