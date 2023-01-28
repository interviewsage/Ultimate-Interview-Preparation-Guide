// LeetCode Question URL: https://leetcode.com/problems/min-stack/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using 2 Stacks
 *
 * Use one stack to store all incoming values. Use another stack to store
 * minimum values received till now.
 *
 * Time Complexity: O(1) of all operations
 *
 * Space Complexity: O(2 * N) = O(N)
 *
 * N = Number of elements added to the stack.
 */
class MinStack1 {

    Deque<Integer> stack;
    Deque<Integer> minStack;

    public MinStack1() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }

    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= getMin()) {
            minStack.push(val);
        }
    }

    public void pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (stack.pop() == getMin()) {
            minStack.pop();
        }
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.peek();
    }

    public int getMin() {
        if (minStack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return minStack.peek();
    }
}

/**
 * Using 1 Stack
 *
 * Refer:
 * https://leetcode.com/problems/min-stack/discuss/49014/Java-accepted-solution-using-one-stack
 *
 * Time Complexity: O(1) of all operations
 *
 * Space Complexity: O(2 * N) = O(N)
 *
 * N = Number of elements added to the stack.
 */
class MinStack2 {

    Stack<Integer> stack;
    int minVal;

    public MinStack2() {
        stack = new Stack<>();
        minVal = Integer.MAX_VALUE;
    }

    public void push(int x) {
        if (x <= minVal) {
            stack.push(minVal);
            minVal = x;
        }
        stack.push(x);
    }

    public int pop() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }

        int cur = stack.pop();
        if (cur == minVal) {
            minVal = stack.pop();
        }
        return cur;
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return stack.peek();
    }

    public int getMin() {
        if (stack.isEmpty()) {
            throw new EmptyStackException();
        }
        return minVal;
    }
}

/**
 * If there a lot of duplicates, this solution is good to.
 *
 * LeetCode Solutions - Approach 3: Improved Two Stacks
 *
 * URL:
 * https://leetcode.com/problems/min-stack/solution/#approach-3-improved-two-stacks
 */

// Your MinStack object will be instantiated and called as such:
// MinStack obj = new MinStack();
// obj.push(x);
// obj.pop();
// int param_3 = obj.top();
// int param_4 = obj.getMin();
