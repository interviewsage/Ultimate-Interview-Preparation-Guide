// LeetCode Question URL: https://leetcode.com/problems/reverse-vowels-of-a-string/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Two Pointers
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N) -> char array.
 *
 * N = Length of input string.
 */
class Solution {
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public String reverseVowels(String s) {
        if (s == null || s.length() <= 1) {
            return s;
        }

        int left = 0;
        int right = s.length() - 1;
        char[] charArr = s.toCharArray();

        while (left < right) {
            // This check will help in case there are very less / no vowels in the input
            // string.
            if (!VOWELS.contains(charArr[left]) && !VOWELS.contains(charArr[right])) {
                left++;
                right--;
                continue;
            }
            if (!VOWELS.contains(charArr[left])) {
                left++;
                continue;
            }
            if (!VOWELS.contains(charArr[right])) {
                right--;
                continue;
            }

            char temp = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = temp;
            left++;
            right--;
        }

        return new String(charArr);
    }
}
