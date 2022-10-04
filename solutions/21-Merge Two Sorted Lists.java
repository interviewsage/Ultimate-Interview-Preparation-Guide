// LeetCode URL: https://leetcode.com/problems/add-two-numbers/

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int x) {
        val = x;
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(M + N). In worst case you will end up visiting all nodes
 * in both list
 *
 * Space Complexity: O(1)
 *
 * M = Length of the input list l1. N = Length of the input list l2.
 */
class Solution1 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                cur.next = list1;
                list1 = list1.next;
            } else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }

        cur.next = list1 == null ? list2 : list1;
        return dummy.next;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(M + N). In worst case you will end up visiting all nodes
 * in both list
 *
 * Space Complexity: O(M + N). Recursion Stack.
 *
 * M = Length of the input list l1. N = Length of the input list l2.
 */
class Solution2 {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }

        if (list1.val <= list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
