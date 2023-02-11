// LeetCode Question URL: https://leetcode.com/problems/reverse-nodes-in-k-group/
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
 * Recursive Solution - In this solution doing the reverse of each group using
 * recursion
 *
 * Time Complexity: O(N). Each node is visited twice.
 *
 * Space Complexity: O(N/k + k)
 */
class Solution1 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("Input k is null");
        }
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode[] successor = new ListNode[] { null };
        ListNode newHead = reverseKNodes(head, k, successor);
        if (newHead == null) {
            return head;
        }

        head.next = reverseKGroup(successor[0], k);
        return newHead;
    }

    private ListNode reverseKNodes(ListNode head, int k, ListNode[] successor) {
        if (head == null) {
            return null;
        }
        if (k == 1) {
            successor[0] = head.next;
            return head;
        }

        ListNode newHead = reverseKNodes(head.next, k - 1, successor);
        if (newHead != null) {
            head.next.next = head;
            head.next = null;
        }

        return newHead;
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(N). Each node is visited twice.
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("Input k is null");
        }
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            cur = cur.next;
            count++;
        }

        if (count < k) {
            return head;
        }

        int numGroups = count / k;
        ListNode dummy = new ListNode();
        dummy.next = head;
        cur = head;
        ListNode pre = dummy;

        while (numGroups-- > 0) {
            ListNode preGroupTail = pre;
            ListNode start = cur;

            for (int j = 0; j < k; j++) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            // start points to tail of the reversed list
            // pre points to head of reversed sub list
            // cur points to head of next sub list
            preGroupTail.next = pre;
            start.next = cur;
            pre = start;
        }

        return dummy.next;
    }
}

/**
 * Recursive Solution - In this solution doing the reverse of each group
 * iteratively
 *
 * Time Complexity: O(2 * N). Each node is visited twice.
 *
 * Space Complexity: O(N/k)
 */
class Solution3 {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("Input k is null");
        }
        if (head == null || head.next == null || k == 1) {
            return head;
        }

        ListNode cur = head;
        int count = 0;
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }

        if (count < k) {
            return head;
        }

        cur = head;
        ListNode pre = null;

        while (count-- > 0) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        head.next = reverseKGroup(cur, k);
        return pre;
    }
}
