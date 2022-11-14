// LeetCode Question URL: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// LeetCode Discuss URL: https://leetcode.com/problems/letter-combinations-of-a-phone-number/discuss/1545143/Java-or-TC:-O(4N)-or-SC:-O(N)-or-Backtracking-and-Iterative-Solutions

import java.util.*;

/**
 * Backtracking Solution
 *
 * Refer:
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/solution/
 *
 * Time Complexity: O(4^N * N)
 *
 * T(n) = 4*T(n-1) + O(4)
 *
 * Space Complexity: O(N) -> Recursion stack space + StringBuilder. O(4^N) for
 * storing the result.
 *
 * N = Length of input digits string.
 */
class Solution1 {
    private static final Map<Character, List<Character>> DIGIT_MAP = Map.of(
            '1', List.of(),
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z'),
            '0', List.of());

    public List<String> letterCombinations(String digits) {
        if (digits == null) {
            throw new IllegalArgumentException("Input is null");
        }

        List<String> result = new ArrayList<>();
        if (digits.length() == 0) {
            return result;
        }

        dfsHelper(result, digits, 0, new StringBuilder());
        return result;
    }

    private void dfsHelper(List<String> result, String digits, int idx, StringBuilder sb) {
        if (idx == digits.length()) {
            result.add(sb.toString());
            return;
        }

        for (char letter : DIGIT_MAP.get(digits.charAt(idx))) {
            sb.append(letter);
            dfsHelper(result, digits, idx + 1, sb);
            sb.setLength(sb.length() - 1);
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
    private static final Map<Character, List<Character>> DIGIT_MAP = Map.of(
            '1', List.of(),
            '2', List.of('a', 'b', 'c'),
            '3', List.of('d', 'e', 'f'),
            '4', List.of('g', 'h', 'i'),
            '5', List.of('j', 'k', 'l'),
            '6', List.of('m', 'n', 'o'),
            '7', List.of('p', 'q', 'r', 's'),
            '8', List.of('t', 'u', 'v'),
            '9', List.of('w', 'x', 'y', 'z'),
            '0', List.of());

    public List<String> letterCombinations(String digits) {
        if (digits == null) {
            throw new IllegalArgumentException("Input is null");
        }

        LinkedList<String> result = new LinkedList<>();
        int len = digits.length();
        if (len == 0) {
            return result;
        }

        result.add("");

        while (result.getFirst().length() < len) {
            String cur = result.removeFirst();
            for (char c : DIGIT_MAP.get(digits.charAt(cur.length()))) {
                result.add(cur + c);
            }
        }

        return result;
    }
}
