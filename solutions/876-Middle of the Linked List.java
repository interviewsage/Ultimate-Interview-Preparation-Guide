// LeetCode Question URL: https://leetcode.com/problems/middle-of-the-linked-list/
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
 * Iterative: Using 2-pointer
 *
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input linked list.
 */
class Solution1 {
    public ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}

/**
 * Recursive: Using 2-pointer
 *
 * Time Complexity: O(N/2) = O(N)
 *
 * Space Complexity: O(N/2) = O(N)
 *
 * N = Length of the input linked list.
 */
class Solution2 {
    public ListNode middleNode(ListNode head) {
        return middleNodeHelper(head, head);
    }

    private ListNode middleNodeHelper(ListNode fast, ListNode slow) {
        if (fast == null || fast.next == null) {
            return slow;
        }
        return middleNodeHelper(fast.next.next, slow.next);
    }
}
