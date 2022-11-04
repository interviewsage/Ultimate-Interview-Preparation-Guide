// LeetCode Question URL: https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/
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
 * Iterate over the list and remove the next node if it has the same value. Then
 * if duplicate values were found, then remove the current node too.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;

        while (cur != null && cur.next != null) {
            if (cur.val != cur.next.val) {
                pre = cur;
                cur = cur.next;
                continue;
            }

            int val = cur.val;
            while (cur != null && cur.val == val) {
                cur = cur.next;
            }
            pre.next = cur;
            // Here we will not move cur as we want to check if new cur.val appears in the
            // list again or not.
        }

        return dummy.next;
    }
}

class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = head;
        ListNode pre = dummy;

        while (cur != null) {
            while (cur.next != null && cur.val == cur.next.val) {
                cur = cur.next;
            }

            if (pre.next != cur) {
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
 * Recursive
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input list.
 */
class Solution3 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rightSideHead = deleteDuplicates(head.next);
        if (head.val == head.next.val) {
            return rightSideHead != null && head.val == rightSideHead.val ? rightSideHead.next : rightSideHead;
        }
        head.next = rightSideHead;
        return head;
    }
}
