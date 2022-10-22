// LeetCode Question URL: https://leetcode.com/problems/palindrome-linked-list/
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
 * Using 2 Pointers divide the list in 2 halves.
 *
 * In this solution we are restoring the list while checking for palindrome.
 *
 * Time Complexity: O(N/2 + N/2) = O(N).
 *
 * Space Complexity: O(1)
 *
 * N = Number of nodes in the list.
 */
class Solution1 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        ListNode pre = null;
        ListNode cur = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        // Temp is used to restore the list.
        ListNode temp = cur;
        // pre = Left list head.
        // cur = Right list head.

        if (fast != null) {
            cur = cur.next;
        }

        // This result var is only needed if we have to revert the changes made in the
        // list.
        boolean result = true;
        while (pre != null) {
            if (result && pre.val != cur.val) {
                result = false;
            }

            // This section is only needed if we have to revert the changes made in the
            // list.
            ListNode pNext = pre.next;
            pre.next = temp;
            temp = pre;
            pre = pNext;

            if (result) {
                cur = cur.next;
            }
        }

        return result;
    }
}

/**
 * Recursive Solution.
 *
 * Find the head of the right half. Once found, start comparing while returning
 * back.
 *
 * Time Complexity: O(N/2) --> isPalindromeHelper only called N/2 times.
 *
 * Space Complexity: O(N/2) - Recursion Depth
 *
 * N = Number of nodes in the list.
 */
class Solution2 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        return isPalindromeHelper(head, head, head) == null;
    }

    private ListNode isPalindromeHelper(ListNode fast, ListNode cur, ListNode slow) {
        if (fast == null) {
            return slow;
        }
        if (fast.next == null) {
            return slow.next;
        }

        slow = isPalindromeHelper(fast.next.next, cur.next, slow.next);
        return cur.val != slow.val ? slow : slow.next;
    }
}

/**
 * Recursive Solution.
 *
 * Find the head of the right half. Once found, start comparing while returning
 * back.
 *
 * Time Complexity: O(N/2) --> isPalindromeHelper only called N/2 times.
 *
 * Space Complexity: O(N/2) - Recursion Depth
 *
 * N = Number of nodes in the list.
 */
class Solution3 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        return isPalindromeHelper(head, head, new ListNode[] { head });
    }

    private boolean isPalindromeHelper(ListNode fast, ListNode cur, ListNode[] slow) {
        if (fast == null) {
            return true;
        }
        if (fast.next == null) {
            slow[0] = slow[0].next;
            return true;
        }

        slow[0] = slow[0].next;

        if (!isPalindromeHelper(fast.next.next, cur.next, slow) || cur.val != slow[0].val) {
            return false;
        }
        slow[0] = slow[0].next;
        return true;
    }
}

/**
 * Recursive Solution.
 *
 * Pass head node. Start comparing with head and tail once reached end of list.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N) - Recursion Depth
 *
 * N = Number of nodes in the list.
 */
class Solution4 {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }

        return isPalindromeHelper(new ListNode[] { head }, head);
    }

    private boolean isPalindromeHelper(ListNode[] head, ListNode node) {
        if (node == null) {
            return true;
        }

        if (!isPalindromeHelper(head, node.next)) {
            return false;
        }

        if (head[0].val != node.val) {
            return false;
        }

        head[0] = head[0].next;
        return true;
    }
}
