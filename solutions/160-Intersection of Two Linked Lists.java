// LeetCode Question URL: https://leetcode.com/problems/intersection-of-two-linked-lists/
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
 * Time Complexity: O(A + B + X)
 *
 * Space Complexity: O(1)
 *
 * A = Length of Non-Overlapping part of list1. B = Length of Non-Overlapping
 * part of list2. X = Length of overlapping part of both lists.
 */
class Solution1 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode p1 = headA;
        ListNode p2 = headB;

        while (p1 != p2) {
            p1 = p1 == null ? headB : p1.next;
            p2 = p2 == null ? headA : p2.next;
        }

        return p1;
    }
}

/**
 * Time Complexity: O(2 * (A+B+C)) = O(A+B+C)
 *
 * Space Complexity: O(1)
 *
 * A = Length of Non-Overlapping part of list A. B = Length of Non-Overlapping
 * part of list B. C = Length of overlapping part of both lists.
 */
class Solution2 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        ListNode A = headA;
        ListNode B = headB;

        while (A != B) {
            A = A.next;
            B = B.next;

            // Both will traverse X+Y length.. Thus will become null together if they do not
            // intersect.
            if (A == null && B == null) {
                return null;
            }
            if (A == null) {
                A = headB;
            } else if (B == null) {
                B = headA;
            }
        }

        return A;
    }
}

/**
 * DO NOT USE THIS SOLUTION
 *
 * Time Complexity: O(A + B + max(A+B)) = O(A + B)
 *
 * Space Complexity: O(1)
 *
 * A = Length of list A. B = Length of list B.
 */
class Solution3 {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }

        int lenA = getLength(headA);
        int lenB = getLength(headB);

        if (lenA != lenB) {
            if (lenA > lenB) {
                int k = lenA - lenB;
                while (k > 0) {
                    headA = headA.next;
                    k--;
                }
            } else {
                int k = lenB - lenA;
                while (k > 0) {
                    headB = headB.next;
                    k--;
                }
            }
        }

        while (headA != null && headB != null && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }

        return len;
    }
}
