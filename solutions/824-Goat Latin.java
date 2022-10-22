// LeetCode Question URL: https://leetcode.com/problems/goat-latin/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Time Complexity:
 *      - Whole String = O(N)
 *      - Append "ma" = O(2*W)
 *      - Append "a" suffixes = O(W*(W+1)/2)
 *      - Time for toString = O(N + 2*W + W*(W+1)/2 + W-1)
 * Total Time Complexity: O(N + W^2)
 *
 * Space Complexity:
 *      - Length of result = O(N + 2*W + W*(W+1)/2 + W-1)
 *      - Length of aStr = O(W)
 * Total Space Complexity: O(N + W^2)
 * </pre>
 *
 * N = Length of the input string. W = Number of words in the input string.
 */
class Solution {

    private static final Set<Character> VOWELS = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public String toGoatLatin(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("Input sentence is null");
        }

        int len = sentence.length();
        if (len == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        StringBuilder aStr = new StringBuilder();
        int idx = 0;

        while (idx < len) {
            char firstChar = sentence.charAt(idx++);
            if (firstChar == ' ') {
                continue;
            }

            if (result.length() != 0) {
                result.append(' ');
            }

            boolean isVowel = VOWELS.contains(firstChar);
            if (isVowel) {
                result.append(firstChar);
            }

            while (idx < len && sentence.charAt(idx) != ' ') {
                result.append(sentence.charAt(idx++));
            }
            if (!isVowel) {
                result.append(firstChar);
            }

            result.append("ma");
            aStr.append("a");
            result.append(aStr);
            idx++;
        }

        return result.toString();
    }
}
