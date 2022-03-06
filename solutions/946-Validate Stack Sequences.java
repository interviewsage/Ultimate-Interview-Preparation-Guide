// LeetCode Question URL: https://leetcode.com/problems/validate-stack-sequences
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer:
 * https://leetcode.com/problems/validate-stack-sequences/discuss/197685/C++JavaPython-Simulation-O(1)-Space
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = length of the pushed or popped array.
 */
class Solution1 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }

        int i = 0;
        int j = 0;
        for (int p : pushed) {
            pushed[i++] = p;
            while (i > 0 && pushed[i - 1] == popped[j]) {
                i--;
                j++;
            }
        }

        return i == 0;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/validate-stack-sequences/discuss/197685/C++JavaPython-Simulation-O(1)-Space
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = length of the pushed or popped array.
 */
class Solution2 {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed == null || popped == null || pushed.length != popped.length) {
            return false;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        for (int p : pushed) {
            stack.push(p);
            while (!stack.isEmpty() && stack.peek() == popped[i]) {
                stack.pop();
                i++;
            }
        }

        return stack.isEmpty();
    }
}
