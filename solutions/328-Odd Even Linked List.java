// LeetCode Question URL: https://leetcode.com/problems/odd-even-linked-list/
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
 * One Pass.
 *
 * Original list head is odd list head. Create an even list head.
 *
 * Time Complexity: O(N/2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution1 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode oddCur = head;
        ListNode evenCur = head.next;
        ListNode evenHead = evenCur;

        // Solved till even. Check if next odd is available or not.
        while (evenCur != null && evenCur.next != null) {
            oddCur.next = evenCur.next;
            evenCur.next = evenCur.next.next;
            oddCur = oddCur.next;
            evenCur = evenCur.next;
        }

        oddCur.next = evenHead;

        return head;
    }
}

/**
 * One Pass Recursive Solution
 *
 * Time Complexity: O(N/2)
 *
 * Space Complexity: O(N/2) - Recursion Depth
 *
 * N = Length of the input list.
 */
class Solution2 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode[] oddCur = new ListNode[] { head };
        ListNode evenHead = head.next;

        oddEvenListHelper(oddCur, evenHead);

        oddCur[0].next = evenHead;
        return head;
    }

    public void oddEvenListHelper(ListNode[] oddCur, ListNode evenCur) {
        if (evenCur == null || evenCur.next == null) {
            return;
        }

        oddCur[0].next = evenCur.next;
        evenCur.next = evenCur.next.next;
        oddCur[0] = oddCur[0].next;
        evenCur = evenCur.next;

        oddEvenListHelper(oddCur, evenCur);
    }
}

/**
 * One Pass Iterative Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution3 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode curOdd = head;
        ListNode curEven = head.next;
        ListNode evenHead = head.next;
        ListNode cur = curEven.next;

        // cur points to odd. cur.next points to even
        while (cur != null && cur.next != null) {
            curOdd.next = cur;
            curEven.next = cur.next;
            curOdd = curOdd.next;
            curEven = curEven.next;
            cur = cur.next.next;
        }

        if (cur != null) {
            curOdd.next = cur;
            curOdd = curOdd.next;
            curEven.next = null;
        }

        curOdd.next = evenHead;

        return head;
    }
}

/**
 * One Pass Iterative Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution4 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        ListNode curOdd = head;
        ListNode curEven = head.next;
        ListNode cur = curEven.next;

        // cur points to odd. cur.next points to even
        while (cur != null && cur.next != null) {
            curEven.next = cur.next;
            curEven = curEven.next;

            cur.next = curOdd.next;
            curOdd.next = cur;
            curOdd = cur;

            cur = curEven.next;
        }

        if (cur != null) {
            curEven.next = null;

            cur.next = curOdd.next;
            curOdd.next = cur;
        }

        return head;
    }
}

/**
 * One Pass Recursive Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N/2) - Recursion Depth
 *
 * N = Length of the input list.
 */
class Solution5 {
    public ListNode oddEvenList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }

        oddEvenListHelper(head.next.next, head, head.next);

        return head;
    }

    private void oddEvenListHelper(ListNode cur, ListNode oddCur, ListNode evenCur) {
        if (cur == null) {
            return;
        }
        if (cur.next == null) {
            evenCur.next = null;
            cur.next = oddCur.next;
            oddCur.next = cur;
            return;
        }

        evenCur.next = cur.next;
        evenCur = evenCur.next;

        cur.next = oddCur.next;
        oddCur.next = cur;

        oddEvenListHelper(evenCur.next, cur, evenCur);
    }
}
