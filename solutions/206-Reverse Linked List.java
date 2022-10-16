// LeetCode Question URL: https://leetcode.com/problems/reverse-linked-list/
// LeetCode Discuss URL:

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

/**
 * Iterative solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the list
 */
class Solution1 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }
}

/**
 * Recursive solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N) - Recursion stack
 *
 * N = Number of nodes in the list
 */
class Solution2 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }
}
