// LeetCode Question URL: https://leetcode.com/problems/swap-nodes-in-pairs/
// LeetCode Discuss URL:

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
 * Iterative Solution
 *
 * Time Complexity: O(N). Each Node is visited once.
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;

        while (cur != null && cur.next != null) {
            pre.next = cur.next;
            cur.next = cur.next.next;
            pre.next.next = cur;
            pre = cur;
            cur = cur.next;

            // Another approach
            // ListNode next = cur.next.next;
            // pre.next = cur.next;
            // cur.next.next = cur;
            // cur.next = next;
            // pre = cur;
            // cur = next;
        }

        return dummy.next;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N). Each Node is visited once.
 *
 * Space Complexity: O(N/2)
 */
class Solution2 {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode newHead = head.next;
        head.next = swapPairs(head.next.next);
        newHead.next = head;

        return newHead;
    }
}
