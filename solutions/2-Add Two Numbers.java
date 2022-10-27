// LeetCode Question URL: https://leetcode.com/problems/add-two-numbers/
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
 * Time Complexity:
 * - Best Case: O(min(M, N))
 * - Worst Case: O(max(M, N))
 *
 * Space Complexity: O(1)
 *
 * M = Length of list l1. N = Length of list l2
 */
class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode dummy = new ListNode();
        int carry = 0;
        ListNode cur = dummy;

        while ((l1 != null && l2 != null) || carry != 0) {
            int sum = carry;
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            carry = sum / 10;
        }

        if (l1 != null) {
            cur.next = l1;
        } else if (l2 != null) {
            cur.next = l2;
        }

        return dummy.next;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity:
 * - Best Case: O(min(M, N))
 * - Worst Case: O(max(M, N))
 *
 * Space Complexity:
 * - Best Case: O(min(M, N))
 * - Worst Case: O(max(M, N))
 *
 * M = Length of list l1. N = Length of list l2
 */
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }

    private ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        if ((l1 == null || l2 == null) && carry == 0) {
            return l1 == null ? l2 : l1;
        }
        if (l1 == null && l2 == null) {
            return new ListNode(carry);
        }

        if (l1 != null) {
            carry += l1.val;
            l1 = l1.next;
        }
        if (l2 != null) {
            carry += l2.val;
            l2 = l2.next;
        }

        ListNode cur = new ListNode(carry % 10);
        cur.next = addTwoNumbersHelper(l1, l2, carry / 10);
        return cur;
    }
}
