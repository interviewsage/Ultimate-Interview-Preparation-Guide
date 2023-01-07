// LeetCode URL: https://leetcode.com/problems/reorder-list/

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
 * Iterative - Break the list into 2 halves. Left Half will also be reversed
 * during the same process. Add extra element next to dummy in case of odd
 * number of nodes. Now pick one element from right and then from left and add
 * them next to dummy. Make sure to set dummy.next to null at the end to avoid
 * memory leak.
 *
 * Time Complexity: O(2 * N/2) = O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the list.
 */
class Solution1 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;
        ListNode pre = null;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode nextSlowNode = slow.next;
            slow.next = pre;
            pre = slow;
            slow = nextSlowNode;
        }

        ListNode dummy = new ListNode();
        if (fast != null) {
            dummy.next = slow;
            slow = slow.next;
            dummy.next.next = null;
        }

        // Slow --> Right-half head
        // Pre --> Left-Half Reversed head

        while (slow != null) {
            // ListNode nextSlowNode = slow.next;
            // ListNode nextToPreNode = pre.next;
            // slow.next = dummy.next;
            // pre.next = slow;
            // dummy.next = pre;
            // slow = nextSlowNode;
            // pre = nextToPreNode;
            slow = addNodeAfterDummy(dummy, slow);
            pre = addNodeAfterDummy(dummy, pre);
        }

        dummy.next = null;
    }

    private ListNode addNodeAfterDummy(ListNode dummy, ListNode toBeAdded) {
        ListNode nextNode = toBeAdded.next;
        toBeAdded.next = dummy.next;
        dummy.next = toBeAdded;
        return nextNode;
    }
}

/**
 * Iterative - Break the list into 2 halves with left half larger than right
 * half. Reverse the right half and merge the 2 lists node by node.
 *
 * Time Complexity: O(3 * N/2)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the list.
 */
class Solution2 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode rev = slow.next;
        slow.next = null;

        ListNode pre = null;
        while (rev != null) {
            ListNode next = rev.next;
            rev.next = pre;
            pre = rev;
            rev = next;
        }

        rev = pre;
        ListNode cur = head;

        while (rev != null) {
            ListNode revNext = rev.next;
            rev.next = cur.next;
            cur.next = rev;
            cur = rev.next;
            rev = revNext;
        }
    }
}

/**
 * Recursive
 *
 * Refer:
 * https://leetcode.com/problems/reorder-list/discuss/45113/Share-a-consise-recursive-solution-in-C++/846063
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the list.
 */
class Solution2 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        reorderListHelper(head, new ListNode[] { head }, new boolean[] { false });
    }

    private void reorderListHelper(ListNode curNode, ListNode[] insertToMyNext, boolean[] stop) {
        if (curNode == null) {
            return;
        }
        reorderListHelper(curNode.next, insertToMyNext, stop);

        if (!stop[0]) {
            ListNode next = insertToMyNext[0].next;
            insertToMyNext[0].next = curNode;
            curNode.next = next;
            insertToMyNext[0] = next;
        }

        if (insertToMyNext[0] != null && insertToMyNext[0].next == curNode) {
            insertToMyNext[0].next = null;
            stop[0] = true;
        }
    }
}

/**
 * Recursive
 *
 * Refer:
 * https://leetcode.com/problems/reorder-list/discuss/45113/Share-a-consise-recursive-solution-in-C++
 *
 * Time Complexity: O(N + N/2). O(N) to find length. O(N/2) Recursion to find
 * tail.
 *
 * Space Complexity: O(N/2) - Recursion stack.
 *
 * N = Length of the list.
 */
class Solution3 {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return;
        }

        reorderListHelper(head, getLengthOfLinkedList(head));
    }

    private ListNode reorderListHelper(ListNode curNode, int length) {
        if (length == 0) {
            return null;
        }
        if (length == 1) {
            return curNode;
        }
        if (length == 2) {
            return curNode.next;
        }

        // Tail of the final list
        ListNode tail = reorderListHelper(curNode.next, length - 2);

        // Free the node to be added next to curNode
        ListNode tmp = tail.next;
        tail.next = tail.next.next;
        // Add the node next to curNode
        tmp.next = curNode.next;
        curNode.next = tmp;

        return tail;
    }

    private int getLengthOfLinkedList(ListNode head) {
        int count = 0;
        while (head != null) {
            count++;
            head = head.next;
        }
        return count;
    }
}
