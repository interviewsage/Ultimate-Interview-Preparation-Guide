// LeetCode Question URL: https://leetcode.com/problems/remove-linked-list-elements/

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int x) {
        val = x;
    }
}

/**
 * Iterative Solution without using a Previous Pointer.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input linked list.
 */
class Solution1 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;

        while (cur.next != null) {
            if (cur.next.val == val) {
                cur.next = cur.next.next;
                // Here cannot move cur to cur.next as we need to validate the next node.
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}

/**
 * Iterative Solution using a Previous Pointer.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input linked list.
 */
class Solution2 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;

        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }

        return dummy.next;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input linked list.
 */
class Solution3 {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        // Once removeElements call is done, right side of the list is solved.
        ListNode rightSideHead = removeElements(head.next, val);
        if (head.val == val) {
            return rightSideHead;
        }
        head.next = rightSideHead;
        return head;
    }
}
