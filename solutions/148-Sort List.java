// LeetCode Question URL: https://leetcode.com/problems/sort-list/
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
 * Bottom-Up Merge Sort - Iterative
 *
 * Refer:
 * https://leetcode.com/problems/sort-list/discuss/46712/Bottom-to-up(not-recurring)-with-o(1)-space-complextity-and-o(nlgn)-time-complextity
 *
 * Outer for loop runs for O(log N) times. Inner while loop runs for O(2 * N)
 * times
 *
 * T(N) = 2 * T(N/2) + O(2*N)
 * T(N/2) = 2 * T(N/4) + O(2*N/2)
 * ...
 * T(1) = O(1)
 *
 * T(N) = O(2*N * log N)
 *
 * Time Complexity: O(N + 2N*logN) = O(N * log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution1 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        int len = getListLength(head);

        ListNode dummy = new ListNode();
        dummy.next = head;

        for (int offset = 1; offset < len; offset *= 2) {
            ListNode cur = dummy.next;
            ListNode prevTail = dummy;

            while (cur != null) {
                ListNode left = cur;
                ListNode right = moveByOffsetAndSplit(left, offset);
                cur = moveByOffsetAndSplit(right, offset);
                prevTail = mergeTwoSortedList(prevTail, left, right);
            }
        }

        return dummy.next;
    }

    private int getListLength(ListNode head) {
        int i = 0;
        while (head != null) {
            head = head.next;
            i++;
        }
        return i;
    }

    private ListNode moveByOffsetAndSplit(ListNode node, int offset) {
        while (offset-- > 1 && node != null) {
            node = node.next;
        }

        if (node == null) {
            return null;
        }

        ListNode next = node.next;
        node.next = null;
        return next;
    }

    private ListNode mergeTwoSortedList(ListNode head, ListNode l1, ListNode l2) {
        while (l1 != null & l2 != null) {
            if (l1.val <= l2.val) {
                head.next = l1;
                l1 = l1.next;
            } else {
                head.next = l2;
                l2 = l2.next;
            }
            head = head.next;
        }

        head.next = l1 != null ? l1 : l2;

        while (head.next != null) {
            head = head.next;
        }

        return head;
    }
}

/**
 * Top-Down Merge Sort - Recursive
 *
 * Find the length one and then use length to perform merge sort.
 *
 * T(N) = 2 * T(N/2) + O(N)
 * T(N/2) = 2 * T(N/4) + O(N/2)
 * ...
 * T(1) = O(1)
 *
 * T(N) = O(N * log N)
 *
 * Time Complexity: O(N + N*logN) = O(N * log N)
 *
 * Space Complexity: O(log N)
 *
 * N = Length of the input list.
 */
class Solution2 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        return mergeSort(new ListNode[] { head }, 0, getListLength(head) - 1);
    }

    private ListNode mergeSort(ListNode[] head, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            ListNode node = head[0];
            head[0] = head[0].next;
            node.next = null;
            return node;
        }

        int mid = start + (end - start) / 2;
        ListNode left = mergeSort(head, start, mid);
        ListNode right = mergeSort(head, mid + 1, end);
        return mergeTwoSortedList(left, right);
    }

    private ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null & l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    private int getListLength(ListNode head) {
        int i = 0;
        while (head != null) {
            head = head.next;
            i++;
        }
        return i;
    }
}

/**
 * Top-Down Merge Sort - Recursive
 *
 * Before every merge, divide the list into 2 parts using slow & fast pointers.
 *
 * T(N) = 2 * T(N/2) + O(3N/2)
 * T(N/2) = 2 * T(N/4) + O(3N/4)
 * ...
 * T(1) = O(1)
 *
 * T(N) = O(3*N/2 * log N)
 *
 * Time Complexity: O(3*N/2*logN) = O(N * log N)
 *
 * Space Complexity: O(log N)
 *
 * N = Length of the input list.
 */
class Solution3 {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        ListNode pre = null;

        while (fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        pre.next = null;

        return mergeTwoSortedList(sortList(head), sortList(slow));
    }

    private ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null & l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }

        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }
}
