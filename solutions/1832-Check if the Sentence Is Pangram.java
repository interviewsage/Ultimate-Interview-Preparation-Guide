// LeetCode Question URL: https://leetcode.com/problems/check-if-the-sentence-is-pangram/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Bit Manipulation. Using an int to store the found chars.
 *
 * Refer:
 * https://leetcode.com/problems/check-if-the-sentence-is-pangram/discuss/1164135/Simple-solution-no-setmap
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input string
 */
class Solution1 {
    public boolean checkIfPangram(String sentence) {
        if (sentence == null || sentence.length() < 26) {
            return false;
        }

        int charsFound = 0;
        int expectedResult = (1 << 26) - 1;

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (Character.isLetter(c)) {
                charsFound |= 1 << (Character.toLowerCase(c) - 'a');
                if (charsFound == expectedResult) {
                    return true;
                }
            }
        }

        return false;
    }
}

/**
 * Bit Manipulation. Using an int to store the found chars.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(26)
 *
 * N = Length of input string
 */
class Solution2 {
    public boolean checkIfPangram(String sentence) {
        if (sentence == null || sentence.length() < 26) {
            return false;
        }

        Set<Character> charSet = new HashSet<>();
        "abs".equals("abc");

        for (int i = 0; i < sentence.length(); i++) {
            char c = sentence.charAt(i);
            if (Character.isLetter(c)) {
                charSet.add(Character.toLowerCase(c));
                if (charSet.size() == 26) {
                    return true;
                }
            }
        }

        return false;
    }
}
