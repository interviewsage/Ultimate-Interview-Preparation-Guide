// LeetCode Question URL: https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Use a StringBuilder to keep track of result string. Use a stack to keep track
 * of count of last seen characters.
 *
 * Update count or pop the count from stack if the current and previous
 * characters are same in the StringBuilder
 *
 * Time Complexity: O(N + N) = O(N) -> Iterate over string + sb.toString()
 *
 * Space Complexity: O(N + N) = O(N) -> Stack + StringBuilder
 *
 * N = Length of input string.
 */
class Solution1 {
    public String removeDuplicates(String s, int k) {
        if (s == null || k < 1) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (k == 1) {
            return "";
        }
        int len = s.length();
        if (len < k) {
            return s;
        }

        // This stack is used to keep track of count of last seen characters.
        Deque<Integer> countStack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // Append the current character and get the index of the current character in
            // StringBuilder
            sb.append(c);
            int lastIdx = sb.length() - 1;

            // If the current and previous character are same in the StringBuilder, update
            // count or pop the count from stack
            if (lastIdx > 0 && sb.charAt(lastIdx - 1) == c) {
                int count = countStack.pop() + 1;
                if (count == k) {
                    sb.setLength(sb.length() - k);
                } else {
                    countStack.push(count);
                }
            } else {
                countStack.push(1);
            }
        }

        return sb.toString();
    }
}

/**
 * Use a stack to keep track of characters with their count.
 *
 * Update count or pop the character from stack if the current and previous
 * characters are same.
 *
 * Time Complexity: O(N + N + N + N) = O(N) -> Iterate over string + Create
 * StringBuilder + sb.reverse() + sb.toString()
 *
 * Space Complexity: O(N + N) = O(N) -> Stack + StringBuilder
 *
 * N = Length of input string.
 */
class Solution2 {
    public String removeDuplicates(String s, int k) {
        if (s == null || k <= 1) {
            // Ask Interviewer about this base case. You can also return null here.
            throw new IllegalArgumentException("Invalid Input");
        }

        int len = s.length();
        if (len < k) {
            return s;
        }

        // This stack is used to keep track of last seen character with their count.
        Deque<Map.Entry<Character, Integer>> stack = new ArrayDeque<>();

        for (int i = 0; i < len; i++) {
            char curChar = s.charAt(i);
            if (stack.isEmpty() || stack.peek().getKey() != curChar) {
                // If the stack is empty OR the current character is not same as the top
                // character in stack, then push new pair in stack.
                stack.push(new AbstractMap.SimpleEntry<>(curChar, 1));
                continue;
            }

            // Now current character is same as the top of the stack. So update count or pop
            // the element from stack.
            int count = stack.peek().getValue();
            if (count + 1 < k) {
                stack.peek().setValue(count + 1);
            } else {
                stack.pop();
            }
        }

        // Create the result string
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Map.Entry<Character, Integer> cur = stack.pop();
            for (int i = 0; i < cur.getValue(); i++) {
                sb.append(cur.getKey());
            }
        }

        return sb.reverse().toString();
    }
}
