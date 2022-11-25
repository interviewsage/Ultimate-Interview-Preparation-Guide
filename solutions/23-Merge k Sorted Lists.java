// LeetCode Question URL: https://leetcode.com/problems/add-two-numbers/
// LeetCode Discuss URL:

import java.util.*;

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Iterative - Using Priority Queue
 *
 * Time Complexity: O(N log K)
 *
 * Space Complexity: O(K)
 *
 * N = Total number of all nodes in all lists. K = Number of lists.
 */
class Solution1 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = lists.length;
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            return lists[0];
        }

        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));

        for (ListNode list : lists) {
            if (list != null) {
                pq.offer(list);
            }
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (!pq.isEmpty()) {
            cur.next = pq.poll();
            cur = cur.next;
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }

        return dummy.next;
    }
}

/**
 * Iterative - Merge with Divide & Conquer
 *
 * Time Complexity: Divide and Merge operation forms a tree with height log K.
 * Each level will handle N nodes. Thus the total time complexity is O(N * log
 * K)
 *
 * Space Complexity: O(1)
 *
 * N = Total number of all nodes in all lists. K = Number of lists.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution2 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = lists.length;
        if (len == 0) {
            return null;
        }

        int jump = 1;
        while (jump < len) {
            for (int i = 0; i + jump < len; i += 2 * jump) {
                lists[i] = merge2Lists(lists[i], lists[i + jump]);
            }
            jump *= 2;
        }

        return lists[0];
    }

    private ListNode merge2Lists(ListNode a, ListNode b) {
        if (a == null) {
            return b;
        }
        if (b == null) {
            return a;
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (a != null & b != null) {
            if (a.val <= b.val) {
                cur.next = a;
                a = a.next;
            } else {
                cur.next = b;
                b = b.next;
            }
            cur = cur.next;
        }

        cur.next = a != null ? a : b;
        return dummy.next;
    }
}

/**
 * Recursive - MergeSort technique
 *
 * Time Complexity: Divide and Merge operation forms a tree with height log K.
 * Each level will handle N nodes. Thus the total time complexity is O(N * log
 * K)
 *
 * Space Complexity: O(log k)
 *
 * N = Total number of all nodes in all lists. K = Number of lists.
 */
class Solution3 {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        if (lists.length == 0) {
            return null;
        }

        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) {
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;

        ListNode l1 = mergeKListsHelper(lists, start, mid);
        ListNode l2 = mergeKListsHelper(lists, mid + 1, end);

        return mergeTwoLists(l1, l2);
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode();
        ListNode cur = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }

            cur = cur.next;
        }

        cur.next = l1 == null ? l2 : l1;

        return dummy.next;
    }
}
