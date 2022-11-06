// LeetCode Question URL: https://leetcode.com/problems/linked-list-cycle/
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
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != null && fast.next != null && fast != slow);

        return fast == slow;
    }
}
