// LeetCode Question URL: https://leetcode.com/problems/reverse-vowels-of-a-string/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Two Pointers
 *
 * Time Complexity: O(N)
 *
 * Space Complexity:
 * 1. O(N/2) -> if all chars are consonants or all are vowels
 * 2. O(N) --> Worst Case
 *
 * N = Length of input string.
 */
class Solution1 {
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public String reverseVowels(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        char[] chArr = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            boolean isLeftNotVowel = !VOWELS.contains(chArr[left]);
            boolean isRightNotVowel = !VOWELS.contains(chArr[right]);

            // This check will help in case there are very less / no vowels in the input
            // string.
            if (isLeftNotVowel && isRightNotVowel) {
                left++;
                right--;
            } else if (isLeftNotVowel) {
                left++;
            } else if (isRightNotVowel) {
                right--;
            } else if (left != right) {
                char t = chArr[left];
                chArr[left++] = chArr[right];
                chArr[right--] = t;
            }
        }

        return new String(chArr);
    }
}

class Solution2 {
    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public String reverseVowels(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        char[] chArr = s.toCharArray();
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !VOWELS.contains(chArr[left])) {
                left++;
            }
            while (left < right && !VOWELS.contains(chArr[right])) {
                right--;
            }
            if (left != right) {
                char t = chArr[left];
                chArr[left++] = chArr[right];
                chArr[right--] = t;
            }
        }

        return new String(chArr);
    }
}
