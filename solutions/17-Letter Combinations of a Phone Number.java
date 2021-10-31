// LeetCode Question URL: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// LeetCode Discuss URL: https://leetcode.com/problems/letter-combinations-of-a-phone-number/discuss/1545143/Java-or-TC:-O(4N)-or-SC:-O(N)-or-Backtracking-and-Iterative-Solutions

import java.util.*;

/**
 * Backtracking Solution
 *
 * Refer:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/solution/
 *
 * Time Complexity: O(4^N)
 *
 * T(n) = 4*T(n-1) + O(4)
 *
 * Space Complexity: O(N) -> Recursion stack space. O(4^N) for storing the
 * result.
 *
 * N = Length of input digits string.
 */
class Solution1 {
    private static final Map<Character, List<Character>> digitMap = Map.of('0', List.of(), '1', List.of(), '2',
            List.of('a', 'b', 'c'), '3', List.of('d', 'e', 'f'), '4', List.of('g', 'h', 'i'), '5',
            List.of('j', 'k', 'l'), '6', List.of('m', 'n', 'o'), '7', List.of('p', 'q', 'r', 's'), '8',
            List.of('t', 'u', 'v'), '9', List.of('w', 'x', 'y', 'z'));

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        letterCombinationsHelper(digits, result, new StringBuilder());
        return result;
    }

    private void letterCombinationsHelper(String digits, List<String> result, StringBuilder sb) {
        int len = sb.length();
        if (len == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char c : digitMap.get(digits.charAt(len))) {
            sb.append(c);
            letterCombinationsHelper(digits, result, sb);
            sb.setLength(len);
        }
    }
}

/**
 * Iterative Solution using Queue
 *
 * Refer:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/discuss/8064/My-java-solution-with-FIFO-queue
 *
 * <pre>
 * Time Complexity: Total of below items
 * Poll will be called -> 1 + 4^1 + 4^2 + ... + 4*(N-1) = (4^(N-1) -1) times.
 * Time to create new string during concatenation: 1*4^1 + 2*4^2 + 3*4^3 + ... + N*4^N
 * </pre>
 *
 * Space Complexity: O(N * 4^N) for storing the portion of previous level +
 * portion of last level.
 *
 * N = Length of input digits string.
 */
class Solution2 {
    private static final Map<Character, List<Character>> digitMap = Map.of('0', List.of(), '1', List.of(), '2',
            List.of('a', 'b', 'c'), '3', List.of('d', 'e', 'f'), '4', List.of('g', 'h', 'i'), '5',
            List.of('j', 'k', 'l'), '6', List.of('m', 'n', 'o'), '7', List.of('p', 'q', 'r', 's'), '8',
            List.of('t', 'u', 'v'), '9', List.of('w', 'x', 'y', 'z'));

    public List<String> letterCombinations(String digits) {
        LinkedList<String> result = new LinkedList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        result.add("");

        while (result.peek().length() < digits.length()) {
            String cur = result.poll();
            for (char c : digitMap.get(digits.charAt(cur.length()))) {
                result.add(cur + c);
            }
        }

        return result;
    }
}
