// LeetCode Question URL: https://leetcode.com/problems/remove-duplicates-from-sorted-list/
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
 * Iterate over the list and remove the next node if it has the same value.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input list.
 */
class Solution1 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode cur = head;
        while (cur != null && cur.next != null) {
            if (cur.val == cur.next.val) {
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }

        return head;
    }
}

/**
 * Recursive Solution - Get solved right side list. If cur.val == rightside.val
 * -> return rightside; else return head;
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input list.
 */
class Solution2 {
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode rightSideHead = deleteDuplicates(head.next);

        if (head.val == rightSideHead.val) {
            return rightSideHead;
        }
        head.next = rightSideHead;
        return head;
    }
}
