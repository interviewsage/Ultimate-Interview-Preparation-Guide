// LeetCode URL: https://leetcode.com/problems/reverse-linked-list-ii/

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
 * Iterative One Pass Solution
 *
 * Time Complexity: O(R)
 *
 * Space Complexity: O(1)
 *
 * L = Length of the list (Total Number of nodes). R = input right index.
 */
class Solution1 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left <= 0 || left > right) {
            throw new IllegalArgumentException("Input left or right is invalid");
        }
        if (head == null || head.next == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        for (int i = 1; i < left; i++) {
            pre = cur;
            cur = cur.next;
        }

        ListNode nodeBeforeReverse = pre;

        for (int i = left; i <= right; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        nodeBeforeReverse.next.next = cur;
        nodeBeforeReverse.next = pre;

        return dummy.next;
    }
}

/**
 * Iterative One Pass Solution. This solution handles out of bound left & right
 * positions.
 *
 * Time Complexity: O(min(N, R))
 *
 * Space Complexity: O(1)
 *
 * N = Length of the list (Total Number of nodes). R = input right index.
 */
class Solution2 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }

        ListNode dummyHead = new ListNode();
        dummyHead.next = head;

        ListNode cur = head;
        ListNode pre = dummyHead;

        for (int i = 1; i < left && cur != null; i++) {
            pre = cur;
            cur = cur.next;
        }

        if (cur == null || cur.next == null) {
            return head;
        }

        ListNode beforeReverse = pre;

        for (int i = left; i <= right && cur != null; i++) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        beforeReverse.next.next = cur;
        beforeReverse.next = pre;

        return dummyHead.next;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(min(L, R))
 *
 * Space Complexity: O(min(L, R))
 *
 * L = Length of the list (Total Number of nodes). R = input right index.
 */
class Solution3 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (left <= 0 || left > right) {
            throw new IllegalArgumentException("Input left or right is invalid");
        }
        if (head == null || head.next == null || left == right) {
            return head;
        }

        if (left == 1) {
            return reverseBetweenHelper(head, right, new ListNode[] { null });
        }

        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    private ListNode reverseBetweenHelper(ListNode head, int n, ListNode[] rightSide) {
        if (n == 1) {
            rightSide[0] = head.next;
            return head;
        }

        ListNode newHead = reverseBetweenHelper(head.next, n - 1, rightSide);
        head.next.next = head;
        head.next = rightSide[0];

        return newHead;
    }
}

class Solution4 {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || head.next == null || left == right) {
            return head;
        }
        if (left == 1) {
            return reverseListHelper(head, right, new ListNode[] { null });
        }

        head.next = reverseBetween(head.next, left - 1, right - 1);
        return head;
    }

    private ListNode reverseListHelper(ListNode head, int n, ListNode[] successor) {
        // Check for head.next == null only if right can be more than the list size.
        if (n == 1 || head.next == null) {
            successor[0] = head.next;
            return head;
        }

        ListNode newHead = reverseListHelper(head.next, n - 1, successor);
        head.next.next = head;
        head.next = successor[0];

        return newHead;
    }
}
