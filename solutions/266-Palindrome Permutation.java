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

        int len = s.length();
        if (len <= 1) {
            return true;
        }

        Set<Character> set = new HashSet<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (!set.add(c)) {
                set.remove(c);
            } else {
                // Below condition can be simplified to `set.size() > (s.length() - i)`
                if ((set.size() - (len - i - 1)) > 1) {
                    return false;
                }
            }
        }

        return true;
    }
}
