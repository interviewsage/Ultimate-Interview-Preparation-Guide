// LeetCode Question URL: https://leetcode.com/problems/letter-case-permutation/
// LeetCode Discuss URL:

import java.util.ArrayList;
import java.util.List;

/**
 * DFS (Using char array)
 *
 * Refer:
 * https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation
 *
 * Time Complexity: O(N * 2^N). Each char in string has 2 options. thus 2^N
 * permutations.
 *
 * Space Complexity: O(N)
 *
 * N = length of input string.
 */
class Solution1 {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        if (s.length() == 0) {
            result.add(s);
            return result;
        }

        letterCasePermutationHelper(s.toCharArray(), 0, result);
        return result;
    }

    private void letterCasePermutationHelper(char[] chArr, int idx, List<String> result) {
        if (idx == chArr.length) {
            result.add(new String(chArr));
            return;
        }

        char c = chArr[idx];
        if (!Character.isLetter(c)) {
            letterCasePermutationHelper(chArr, idx + 1, result);
        } else {
            chArr[idx] = Character.toLowerCase(c);
            letterCasePermutationHelper(chArr, idx + 1, result);

            chArr[idx] = Character.toUpperCase(c);
            letterCasePermutationHelper(chArr, idx + 1, result);
        }
    }
}

/**
 * Another awesome solution suing Bit manipulation to toggle between cases
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/letter-case-permutation/discuss/115515/C%2B%2B-backtrack-solution-w-trick
 * 2. https://www.includehelp.com/cpp-programs/change-case-of-a-character-using-bit-manipulation.aspx
 * </pre>
 */
class Solution2 {
    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        if (s.length() == 0) {
            result.add(s);
            return result;
        }

        letterCasePermutationHelper(s.toCharArray(), 0, result);
        return result;
    }

    private void letterCasePermutationHelper(char[] chArr, int idx, List<String> result) {
        if (idx == chArr.length) {
            result.add(new String(chArr));
            return;
        }

        letterCasePermutationHelper(chArr, idx + 1, result);
        char c = chArr[idx];
        if (Character.isLetter(chArr[idx])) {
            // Toggle character's case
            chArr[idx] ^= (1 << 5);
            letterCasePermutationHelper(chArr, idx + 1, result);
        }
    }
}

/**
 * Iterative Solution
 *
 * <pre>
 * Refer:
 * 1. Approach #1: Recursion [Accepted] - https://leetcode.com/problems/letter-case-permutation/solution/
 * </pre>
 */

/**
 * DFS (Using string builder)
 *
 * Refer:
 * https://leetcode.com/problems/letter-case-permutation/discuss/115485/Java-Easy-BFS-DFS-solution-with-explanation
 *
 * Time Complexity: O(N * 2^N). Each char in string has 2 options. thus 2^N
 * permutations.
 *
 * Space Complexity: O(N)
 *
 * N = length of input string.
 */
class Solution3 {
    public List<String> letterCasePermutation(String S) {
        List<String> result = new ArrayList<>();
        if (S == null) {
            return result;
        }

        helper(result, new StringBuilder(), S, 0);

        return result;
    }

    private void helper(List<String> result, StringBuilder sb, String S, int index) {
        if (index == S.length()) {
            result.add(sb.toString());
            return;
        }

        char c = S.charAt(index);
        int len = sb.length();
        if (!Character.isLetter(c)) {
            sb.append(c);
            helper(result, sb, S, index + 1);
            sb.setLength(len);
        } else {
            sb.append(Character.toLowerCase(c));
            helper(result, sb, S, index + 1);
            sb.setLength(len);

            sb.append(Character.toUpperCase(c));
            helper(result, sb, S, index + 1);
            sb.setLength(len);
        }
    }
}