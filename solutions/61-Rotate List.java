// LeetCode Question URL: https://leetcode.com/problems/rotate-list/
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
 * Find the length. Find the number of nodes from the start of current list that
 * will become the tail.
 *
 * Time Complexity: O(N + N - k%N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution1 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Input k is invalid");
        }
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int len = 0;
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null) {
            len++;
            pre = cur;
            cur = cur.next;
        }

        k = k % len;
        if (k == 0) {
            return head;
        }

        k = len - k;
        cur = head;
        while (--k > 0) {
            cur = cur.next;
        }

        pre.next = head;
        ListNode newHead = cur.next;
        cur.next = null;

        return newHead;
    }
}

/**
 * Iterative. Time is less than before
 *
 * Refer:
 * https://leetcode.com/problems/rotate-list/discuss/22735/My-clean-C++-code-quite-standard-(find-tail-and-reconnect-the-list)/22242
 *
 * Time Complexity: O(N) if k < N. O(2*N) if k > N.
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution2 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Input k is invalid");
        }
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        int l = 1;
        ListNode p1 = head;
        while (l <= k) {
            if (p1.next != null) {
                l++;
                p1 = p1.next;
                continue;
            }

            k = k % l;
            if (k == 0) {
                return head;
            }
            p1 = head;
            l = 1;
        }

        ListNode p2 = head;
        while (p1.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }

        ListNode newHead = p2.next;
        p2.next = null;
        p1.next = head;

        return newHead;
    }
}

/**
 * Recursive
 *
 * Refer:
 * https://leetcode.com/problems/rotate-list/discuss/522078/Simple-java-recursive-solution
 *
 * Time Complexity: O(N).
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input list.
 */
class Solution3 {
    public ListNode rotateRight(ListNode head, int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Input k is invalid");
        }
        if (head == null || head.next == null || k == 0) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;

        rotateRightHelper(head, dummy, k, 0);

        return dummy.next;
    }

    private int rotateRightHelper(ListNode node, ListNode head, int k, int idx) {
        if (node == null) {
            return k % idx;
        }

        k = rotateRightHelper(node.next, head, k, idx + 1);
        if (k > 0) {
            node.next = head.next;
            head.next = node;
        } else if (k == 0) {
            node.next = null;
        }

        return k - 1;
    }
}
