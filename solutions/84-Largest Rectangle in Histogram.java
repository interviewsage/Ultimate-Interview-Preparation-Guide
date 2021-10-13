// LeetCode Question URL: https://leetcode.com/problems/largest-rectangle-in-histogram/
// LeetCode Discuss URL: https://leetcode.com/problems/largest-rectangle-in-histogram/discuss/1519257/Java-or-TC:-O(N)-or-SC:-O(N)-or-Optimal-Stack-solution

import java.util.*;

/**
 * Using stack to save the increasing height index.
 *
 * Time Complexity: O(N) --> Each element is visited maximum twice. (Once pushed
 * in stack and once popped for stack)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution {
    public int largestRectangleArea(int[] heights) {
        if (heights == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = heights.length;
        if (len == 0) {
            return 0;
        }
        if (len == 1) {
            return heights[0];
        }

        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= len; i++) {
            while (!stack.isEmpty() && (i == len || heights[stack.peek()] >= heights[i])) {
                int h = heights[stack.pop()];
                int left = stack.isEmpty() ? -1 : stack.peek();
                /**
                 * i-1 - left ==> This is calculating the width of the rectangle. Both ith and
                 * left positions are excluded.
                 */
                maxArea = Math.max(maxArea, (i - 1 - left) * h);
            }
            stack.push(i);
        }

        return maxArea;
    }
}
