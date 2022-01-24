// LeetCode Question URL: https://leetcode.com/problems/implement-queue-using-stacks/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Two stack method
 *
 * The loop in peek does the moving from input to output stack. Each element
 * only ever gets moved like that once, though, and only after we already spent
 * time pushing it, so the overall amortized cost for each operation is O(1).
 *
 * Time Complexity: MyQueue() - O(1), push() - O(1), pop() - Amortized O(1),
 * peek() - Amortized O(1), empty() - O(1)
 *
 * Space Complexity: O(N)
 *
 * N = Number elements in the queue.
 */
class MyQueue {

    Deque<Integer> stack1;
    Deque<Integer> stack2;

    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }

    public void push(int x) {
        stack1.push(x);
    }

    public int pop() {
        peek();
        return stack2.pop();
    }

    public int peek() {
        if (empty()) {
            throw new NoSuchElementException("Queue is empty");
        }
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.peek();
    }

    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

// Your MyQueue object will be instantiated and called as such:
// MyQueue obj = new MyQueue();
// obj.push(x);
// int param_2 = obj.pop();
// int param_3 = obj.peek();
// boolean param_4 = obj.empty();
