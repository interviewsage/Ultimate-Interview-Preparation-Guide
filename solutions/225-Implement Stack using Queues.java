// LeetCode Question URL: https://leetcode.com/problems/implement-stack-using-queues/
// LeetCode Discuss URL:

import java.util.*;

/**
 * One Queue. Push is O(1), Pop is O(N).
 *
 * Time Complexity: Push = O(1). Pop = O(N). Top = O(1). Empty = O(1)
 *
 * Space Complexity: O(N)
 *
 * N = Number of elements in the stack.
 */
class MyStack1 {

    Queue<Integer> queue;
    int top;

    public MyStack1() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.offer(x);
        top = x;
    }

    public int pop() throws NoSuchElementException {
        if (empty()) {
            throw new NoSuchElementException("Stack is Empty");
        }
        for (int i = 0; i < queue.size() - 1; i++) {
            top = queue.poll();
            queue.offer(top);
        }
        return queue.poll();
    }

    public int top() throws NoSuchElementException {
        if (empty()) {
            throw new NoSuchElementException("Stack is Empty");
        }
        return top;
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

/**
 * One Queue. Push is O(N), Pop is O(1).
 *
 * Time Complexity: Push = O(N). Pop = O(1). Top = O(1). Empty = O(1)
 *
 * Space Complexity: O(N)
 *
 * N = Number of elements in the stack.
 */
class MyStack2 {

    Queue<Integer> queue;

    public MyStack2() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        queue.offer(x);
        for (int i = 0; i < queue.size() - 1; i++) {
            queue.offer(queue.poll());
        }
    }

    public int pop() throws NoSuchElementException {
        if (empty()) {
            throw new NoSuchElementException("Stack is Empty");
        }
        return queue.poll();
    }

    public int top() throws NoSuchElementException {
        if (empty()) {
            throw new NoSuchElementException("Stack is Empty");
        }
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}

// Your MyStack object will be instantiated and called as such:
// MyStack obj = new MyStack();
// obj.push(x);
// int param_2 = obj.pop();
// int param_3 = obj.top();
// boolean param_4 = obj.empty();
