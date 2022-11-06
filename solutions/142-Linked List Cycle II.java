// LeetCode Question URL: https://leetcode.com/problems/linked-list-cycle-ii/
// LeetCode Discuss URL:

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}

/**
 * Tortoise and Hare algorithm
 *
 * Proof: Fast will never jump over slow if there is a cycle.
 *
 * F -> S... in this case they will meet at next step
 *
 * F -> Node -> S .. in this case, after one move they will be like previous
 * case.
 *
 * Thus fast point can never jump over slow pointer. Thus slow pointer never
 * completes more than one full loop.
 *
 * f = Distance traveled by fast. s = Distance traveled by slow. x = Distance
 * from head to cycle point. a = Distance from cycle point to meeting point. n =
 * Number of loops fast pointer takes. c = Number of nodes in the cycle.
 *
 * f = 2*s. f = x + n*c + a. s = x + a.
 *
 * x + n*c + a = 2x + 2a ==> x = n*c - a ==> x = (n-1)*c + (c-a).
 *
 * Time Complexity: O(2 * N) = First loop can run max N times and similarly
 * second loop can run max N times.
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != null && fast.next != null && fast != slow);

        if (fast != slow) {
            return null;
        }
        if (slow == slow.next) {
            return slow;
        }

        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
}

class Solution2 {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        if (head == head.next) {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (slow == fast) {
                break;
            }
        }

        if (slow != fast) {
            return null;
        }

        ListNode cur = head;
        while (slow != cur) {
            slow = slow.next;
            cur = cur.next;
        }

        return slow;
    }
}
