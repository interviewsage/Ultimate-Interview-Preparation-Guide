// LeetCode URL: https://leetcode.com/problems/partition-list/

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
 * Iterative
 *
 * Refer: 1)
 * https://leetcode.com/problems/partition-list/discuss/29183/Concise-java-code-with-explanation-one-pass
 * 2)
 * https://leetcode.com/problems/partition-list/discuss/29185/Very-concise-one-pass-solution/28241
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input list
 */
class Solution1 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode smallHead = new ListNode();
        ListNode largeHead = new ListNode();
        ListNode small = smallHead;
        ListNode large = largeHead;
        ListNode cur = head;

        while (cur != null) {
            if (cur.val < x) {
                small.next = cur;
                small = small.next;
            } else {
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }

        large.next = null;
        small.next = largeHead.next;
        return smallHead.next;
    }
}

/**
 * Recursive
 *
 * Refer:
 * https://leetcode.com/problems/partition-list/discuss/349660/Simple-recursive-solution-runtime-and-memory-both-100-with-detailed-explanations
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input list
 */
class Solution2 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        return partitionHelper(head, x, new ListNode[] { null });
    }

    private ListNode partitionHelper(ListNode node, int x, ListNode[] smallListTail) {
        if (node == null) {
            return node;
        }

        node.next = partitionHelper(node.next, x, smallListTail);

        if (smallListTail[0] == null) {
            if (node.val < x) {
                smallListTail[0] = node;
            }
            return node;
        }

        if (node.val >= x) {
            ListNode next = node.next;
            node.next = smallListTail[0].next;
            smallListTail[0].next = node;
            return next;
        }

        return node;
    }
}

/**
 * Iterative With one Dummy pointer
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input list
 */
class Solution3 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;
        ListNode insertPos = dummy;

        while (cur != null) {
            if (cur.val < x) {
                if (insertPos == pre) {
                    insertPos = cur;
                    pre = cur;
                    cur = cur.next;
                } else {
                    pre.next = cur.next;
                    cur.next = insertPos.next;
                    insertPos.next = cur;
                    insertPos = insertPos.next;
                    cur = pre.next;
                }
            } else {
                pre = cur;
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}

/**
 * Iterative With one Dummy pointer. Not using pre pointer.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input list
 */
class Solution4 {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode();
        dummy.next = head;
        ListNode cur = dummy;
        ListNode insertPos = dummy;

        while (cur.next != null) {
            if (cur.next.val < x) {
                if (insertPos == cur) {
                    insertPos = cur.next;
                    cur = cur.next;
                } else {
                    insertAfter(insertPos, removeNextNode(cur));
                    insertPos = insertPos.next;

                    // ListNode temp = cur.next.next;
                    // cur.next.next = insertPos.next;
                    // insertPos.next = cur.next;
                    // insertPos = insertPos.next;
                    // cur.next = temp;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }

    private ListNode removeNextNode(ListNode node) {
        ListNode next = node.next;
        node.next = next.next;
        return next;
    }

    private void insertAfter(ListNode node, ListNode toBeInserted) {
        toBeInserted.next = node.next;
        node.next = toBeInserted;
    }
}
