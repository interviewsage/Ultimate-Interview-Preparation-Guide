// LeetCode Question URL: https://leetcode.com/problems/max-stack/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using 2 stacks
 *
 * Refer:
 * https://leetcode.com/articles/max-stack/#approach-1-two-stacks-accepted
 *
 * Time Complexity: O(N) for popMax.. and O(1) for all other operations
 *
 * Space Complexity: O(N).
 *
 * N = Number of items in stack.
 */
class MaxStack1 {

    Deque<Integer> stack;
    Deque<Integer> maxStack;

    public MaxStack1() {
        stack = new ArrayDeque<>();
        maxStack = new ArrayDeque<>();
    }

    public void push(int x) {
        stack.push(x);
        if (maxStack.isEmpty() || x >= peekMax()) {
            maxStack.push(x);
        }
    }

    public int pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        int val = stack.pop();
        if (val == peekMax()) {
            maxStack.pop();
        }
        return val;
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.peek();
    }

    public int peekMax() {
        if (maxStack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return maxStack.peek();
    }

    public int popMax() {
        if (maxStack.isEmpty()) {
            throw new NoSuchElementException();
        }

        Deque<Integer> tempStack = new ArrayDeque<>();
        while (top() != peekMax()) {
            tempStack.push(stack.pop());
        }

        int val = pop();
        while (!tempStack.isEmpty()) {
            push(tempStack.pop());
        }

        return val;
    }
}

/**
 * Using TreeMap and DoublyLinkedList
 *
 * Refer:
 * https://leetcode.com/articles/max-stack/#approach-2-double-linked-list-treemap-accepted
 *
 * Time Complexity: O(log N) for all operations
 *
 * Space Complexity: O(N).
 *
 * N = Number of items in stack.
 */
class MaxStack2 {

    public class Node {
        int val;
        Node next;
        Node pre;

        public Node() {
        }

        public Node(int x) {
            val = x;
        }
    }

    public class DLLStack {
        Node head;
        Node tail;

        public DLLStack() {
            head = new Node();
            tail = new Node();
            head.next = tail;
            tail.pre = head;
        }

        public boolean isEmpty() {
            return head == tail.pre;
        }

        public void push(Node n) {
            tail.pre.next = n;
            n.pre = tail.pre;
            tail.pre = n;
            n.next = tail;
        }

        public Node pop() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            Node n = tail.pre;
            remove(n);
            return n;
        }

        public void remove(Node n) {
            n.pre.next = n.next;
            n.next.pre = n.pre;
            n.pre = null;
            n.next = null;
        }

        public Node peek() {
            if (isEmpty()) {
                throw new NoSuchElementException();
            }
            return tail.pre;
        }
    }

    TreeMap<Integer, List<Node>> map;
    DLLStack stack;

    public MaxStack2() {
        map = new TreeMap<>();
        stack = new DLLStack();
    }

    public void push(int x) {
        Node n = new Node(x);
        stack.push(n);
        map.putIfAbsent(x, new ArrayList<>());
        map.get(x).add(n);
    }

    public int pop() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }

        int val = stack.pop().val;
        List<Node> list = map.get(val);

        if (list.size() == 1) {
            map.remove(val);
        } else {
            list.remove(list.size() - 1);
        }

        return val;
    }

    public int top() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return stack.peek().val;
    }

    public int peekMax() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }
        return map.lastKey();
    }

    public int popMax() {
        if (stack.isEmpty()) {
            throw new NoSuchElementException();
        }

        Map.Entry<Integer, List<Node>> maxEntry = map.lastEntry();
        int maxVal = maxEntry.getKey();
        List<Node> list = maxEntry.getValue();

        stack.remove(list.remove(list.size() - 1));
        if (list.isEmpty()) {
            map.remove(maxVal);
        }

        return maxVal;
    }
}

// Your MaxStack object will be instantiated and called as such:
// MaxStack obj = new MaxStack();
// obj.push(x);
// int param_2 = obj.pop();
// int param_3 = obj.top();
// int param_4 = obj.peekMax();
// int param_5 = obj.popMax();
