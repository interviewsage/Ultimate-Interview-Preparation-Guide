// LeetCode Question URL: https://leetcode.com/problems/swapping-nodes-in-a-linked-list/
// LeetCode Discuss URL:

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * Swapping Nodes
 *
 * Refer:
 * https://leetcode.com/problems/swapping-nodes-in-a-linked-list/discuss/1054370/Python-3-or-Swapping-NODES-or-Swapping-Values-or-One-Pass-or-Fully-explained
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of List.
 */
class Solution1 {
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        ListNode preFirst = dummy;
        while (k-- > 1 && preFirst != null) {
            preFirst = preFirst.next;
        }

        if (preFirst == null || preFirst.next == null) {
            return head;
        }

        ListNode temp = preFirst.next;
        ListNode preSecond = dummy;
        while (temp.next != null) {
            temp = temp.next;
            preSecond = preSecond.next;
        }

        if (preFirst == preSecond) {
            return head;
        }

        ListNode first = preFirst.next;
        ListNode second = preSecond.next;

        preFirst.next = second;
        preSecond.next = first;

        temp = first.next;
        first.next = second.next;
        second.next = temp;

        return dummy.next;
    }
}

/**
 * Swapping Nodes
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of List.
 */
class Solution2 {
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode first = dummy;

        while (k-- > 1 && first != null) {
            first = first.next;
        }

        if (first == null || first.next == null) {
            return head;
        }

        ListNode temp = first.next;
        ListNode second = dummy;
        while (temp.next != null) {
            temp = temp.next;
            second = second.next;
        }

        if (first == second) {
            return head;
        }

        if (first.next == second) {
            first.next = second.next;
            second.next = first.next.next;
            first.next.next = second;
            return dummy.next;
        }
        if (second.next == first) {
            second.next = first.next;
            first.next = second.next.next;
            second.next.next = first;
            return dummy.next;
        }

        temp = first.next.next;
        first.next.next = second.next.next;
        second.next.next = temp;

        temp = first.next;
        first.next = second.next;
        second.next = temp;

        return dummy.next;
    }
}

/**
 * Swapping Values
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of List.
 */
class Solution3 {
    public ListNode swapNodes(ListNode head, int k) {
        if (head == null || head.next == null || k <= 0) {
            return head;
        }

        ListNode first = head;
        while (k-- > 1 && first != null) {
            first = first.next;
        }

        if (first == null) {
            return head;
        }

        ListNode temp = first.next;
        ListNode second = head;
        while (temp != null) {
            temp = temp.next;
            second = second.next;
        }

        if (first != second) {
            int t = first.val;
            first.val = second.val;
            second.val = t;
        }

        return head;
    }
}
