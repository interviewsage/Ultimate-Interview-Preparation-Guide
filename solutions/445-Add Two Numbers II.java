// LeetCode Question URL: https://leetcode.com/problems/add-two-numbers-ii/
// LeetCode Discuss URL:

import java.util.*;

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
 * Iterative (No Extra Space)
 *
 * Time Complexity: O(3 * max(M, N))
 *
 * Space Complexity: O(1)
 *
 * M = Number of nodes in l1. N = Number of nodes in l2.
 */
class Solution1 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int len1 = 0;
        int len2 = 0;
        while (cur1 != null || cur2 != null) {
            if (cur1 != null) {
                len1++;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                len2++;
                cur2 = cur2.next;
            }
        }

        ListNode cur = null;
        while (len1 > 0 || len2 > 0) {
            int sum = 0;
            if (len1 > len2) {
                sum += l1.val;
                l1 = l1.next;
                len1--;
            } else if (len1 < len2) {
                sum += l2.val;
                l2 = l2.next;
                len2--;
            } else {
                sum += l1.val + l2.val;
                l1 = l1.next;
                l2 = l2.next;
                len1--;
                len2--;
            }
            ListNode newNode = new ListNode(sum);
            newNode.next = cur;
            cur = newNode;
        }

        ListNode pre = null;
        int carry = 0;
        while (cur != null) {
            carry += cur.val;
            cur.val = carry % 10;
            carry /= 10;

            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        if (carry != 0) {
            ListNode newNode = new ListNode(carry);
            newNode.next = pre;
            return newNode;
        }
        return pre;
    }
}

/**
 * Using stack.
 *
 * Time Complexity: O(2 * max(M, N))
 *
 * Space Complexity: O(M + N)
 *
 * M = Number of nodes in l1. N = Number of nodes in l2.
 */
class Solution2 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1 != null || l2 != null) {
            if (l1 != null) {
                stack1.push(l1.val);
                l1 = l1.next;
            }
            if (l2 != null) {
                stack2.push(l2.val);
                l2 = l2.next;
            }
        }

        ListNode dummyHead = new ListNode(1);
        int carry = 0;

        while (!stack1.isEmpty() || !stack2.isEmpty()) {
            int sum = carry;
            sum += !stack1.isEmpty() ? stack1.pop() : 0;
            sum += !stack2.isEmpty() ? stack2.pop() : 0;

            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;

            node.next = dummyHead.next;
            dummyHead.next = node;
        }

        if (carry == 1) {
            return dummyHead;
        }
        return dummyHead.next;
    }
}

/**
 * Recursive Solution - Returns Head of solved list with 1d carry array.
 *
 * Time Complexity: O(2 * max(M, N))
 *
 * Space Complexity: O(max(M, N))
 *
 * M = Number of nodes in l1. N = Number of nodes in l2.
 */
class Solution3 {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        ListNode cur1 = l1;
        ListNode cur2 = l2;
        int len1 = 0;
        int len2 = 0;
        while (cur1 != null || cur2 != null) {
            if (cur1 != null) {
                len1++;
                cur1 = cur1.next;
            }
            if (cur2 != null) {
                len2++;
                cur2 = cur2.next;
            }
        }

        ListNode head = addTwoNumbersHelper(l1, len1, l2, len2);
        if (head.val >= 10) {
            ListNode newHead = new ListNode(head.val / 10);
            head.val %= 10;
            newHead.next = head;
            return newHead;
        }
        return head;
    }

    private ListNode addTwoNumbersHelper(ListNode l1, int len1, ListNode l2, int len2) {
        if (len1 == 1 && len2 == 1) {
            return new ListNode(l1.val + l2.val);
        }

        int sum = 0;
        if (len1 > len2) {
            sum += l1.val;
            l1 = l1.next;
            len1--;
        } else if (len1 < len2) {
            sum += l2.val;
            l2 = l2.next;
            len2--;
        } else {
            sum += l1.val + l2.val;
            l1 = l1.next;
            l2 = l2.next;
            len1--;
            len2--;
        }

        ListNode head = addTwoNumbersHelper(l1, len1, l2, len2);
        sum += head.val / 10;
        head.val %= 10;

        ListNode newHead = new ListNode(sum);
        newHead.next = head;
        return newHead;
    }
}
