// LeetCode Question URL: https://leetcode.com/problems/lru-cache/
// LeetCode Discuss URL:

import java.util.*;

/**
 * HashMap + Doubly linked list
 *
 * Refer:
 * https://leetcode.com/problems/lru-cache/discuss/45911/Java-Hashtable-+-Double-linked-list-(with-a-touch-of-pseudo-nodes)
 *
 * Time Complexity: O(1) of all operations
 *
 * Space Complexity: O(C)
 *
 * C = Capacity of cache.
 */
class LRUCache {

    public class Node {
        int key;
        int val;
        Node pre;
        Node next;

        public Node() {
        }

        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }

    Map<Integer, Node> keyNodeMap;
    Node head, tail;
    int maxCapacity;

    public LRUCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Input capacity is invalid");
        }

        keyNodeMap = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        maxCapacity = capacity;
    }

    public int get(int key) {
        Node node = keyNodeMap.get(key);
        if (node == null) {
            return -1;
        }

        removeNodeFromList(node);
        addNodeAtHead(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (get(key) != -1) {
            keyNodeMap.get(key).val = value;
            return;
        }

        if (keyNodeMap.size() == maxCapacity) {
            keyNodeMap.remove(tail.pre.key);
            removeNodeFromList(tail.pre);
        }

        Node newNode = new Node(key, value);
        keyNodeMap.put(key, newNode);
        addNodeAtHead(newNode);
    }

    private void removeNodeFromList(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
        node.next = null;
        node.pre = null;
    }

    private void addNodeAtHead(Node node) {
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }
}

// Your LRUCache object will be instantiated and called as such:
// LRUCache obj = new LRUCache(capacity);
// int param_1 = obj.get(key);
// obj.put(key,value);
