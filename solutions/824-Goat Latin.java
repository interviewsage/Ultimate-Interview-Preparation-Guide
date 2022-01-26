// LeetCode Question URL: https://leetcode.com/problems/goat-latin/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Time Complexity:
 * 1. Each char is maximum visited twice = O(2*L)
 * 2. Append "ma" = O(2*N)
 * 3. Build Suffix = O(N)
 * 4. Append suffix = O(N^2)
 * 5. toString:
 *      a. Original String = O(L)
 *      b. "ma" = O(2*N)
 *      c. "a"s = O(N^2)
 * Total Time Complexity: O(3*L + 5*N + 2*N^2) = O(L + N + N^2)
 *
 * Space Complexity:
 * 1. suffix sb = O(N)
 * 2. result sb = O(L + 2*N + N^2)
 * Total Space Complexity: O(L + 3*N + N^2) = O(L + N + N^2)
 * </pre>
 *
 * L = Length of the input string. N = Number of words in the input string.
 */
class Solution1 {

    Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

    public String toGoatLatin(String sentence) {
        if (sentence == null || sentence.length() == 0) {
            return "";
        }

        int len = sentence.length();
        StringBuilder sb = new StringBuilder();
        StringBuilder suffix = new StringBuilder();

        int i = 0;

        while (i < len) {
            if (sentence.charAt(i) == ' ') {
                i++;
                continue;
            }
            if (sb.length() > 0) {
                sb.append(' ');
            }

            int start = i;
            int sbStart = sb.length();
            while (i < len && sentence.charAt(i) != ' ') {
                sb.append(sentence.charAt(i));
                i++;
            }

            char firstChar = sentence.charAt(start);
            if (!vowels.contains(firstChar)) {
                sb.deleteCharAt(sbStart);
                sb.append(firstChar);
            }

            sb.append("ma");
            suffix.append('a');
            sb.append(suffix);
        }

        return sb.toString();
    }
}

/**
 * Time Complexity: O(3*L + N^2) = O(Split + Substring + Adding word to sb +
 * Adding suffix to sb)
 *
 * Space Complexity: O(L + N^2 + N) = O(Input string + Suffix for all words +
 * Suffix sb)
 *
 * L = Length of the input string. N = Number of words in the input string.
 */
class Solution2 {
    private static final HashSet<Character> vowels = new HashSet<>(
            Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));

    public String toGoatLatin(String S) {
        if (S == null || S.length() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        StringBuilder suffix = new StringBuilder("a");

        for (String w : S.split(" ")) {
            if (sb.length() != 0) {
                sb.append(" ");
            }

            char fChar = w.charAt(0);
            if (vowels.contains(fChar)) {
                sb.append(w);
            } else {
                sb.append(w.substring(1));
                sb.append(fChar);
            }
            sb.deleteCharAt(index)

            sb.append("ma").append(suffix);

            suffix.append("a");
        }

        return sb.toString();
    }
}
