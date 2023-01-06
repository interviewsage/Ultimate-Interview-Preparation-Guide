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
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        List<String> resultList = new ArrayList<>();
        letterCasePermutationHelper(resultList, s.toCharArray(), 0);
        return resultList;
    }

    private void letterCasePermutationHelper(List<String> resultList, char[] chArr, int idx) {
        if (idx == chArr.length) {
            resultList.add(new String(chArr));
            return;
        }

        letterCasePermutationHelper(resultList, chArr, idx + 1);
        if (Character.isLetter(chArr[idx])) {
            chArr[idx] = Character.isUpperCase(chArr[idx]) ? Character.toLowerCase(chArr[idx])
                    : Character.toUpperCase(chArr[idx]);
            letterCasePermutationHelper(resultList, chArr, idx + 1);
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
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        List<String> resultList = new ArrayList<>();
        letterCasePermutationHelper(resultList, s.toCharArray(), 0);
        return resultList;
    }

    private void letterCasePermutationHelper(List<String> resultList, char[] chArr, int idx) {
        if (idx == chArr.length) {
            resultList.add(new String(chArr));
            return;
        }

        letterCasePermutationHelper(resultList, chArr, idx + 1);
        if (Character.isLetter(chArr[idx])) {
            chArr[idx] ^= (1 << 5);
            letterCasePermutationHelper(resultList, chArr, idx + 1);
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
class Solution3 {
    public List<String> letterCasePermutation(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        List<StringBuilder> resultList = new ArrayList<>();
        resultList.add(new StringBuilder());

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int curSize = resultList.size();
            if (Character.isLetter(c)) {
                // char toggledC = Character.isUpperCase(c) ? Character.toLowerCase(c) :
                // Character.toUpperCase(c);
                char toggledC = (char) (c ^ (1 << 5));
                for (int j = 0; j < curSize; j++) {
                    resultList.add(new StringBuilder(resultList.get(j)));
                    resultList.get(j).append(c);
                    resultList.get(curSize + j).append(toggledC);
                }
            } else {
                for (int j = 0; j < curSize; j++) {
                    resultList.get(j).append(c);
                }
            }
        }

        List<String> result = new ArrayList<>();
        for (StringBuilder sb : resultList) {
            result.add(sb.toString());
        }

        return result;
    }
}

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
class Solution4 {
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
