// LeetCode Question URL: https://leetcode.com/problems/convert-sorted-list-to-binary-search-tree/
// LeetCode Discuss URL:

// Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Inorder Simulation
 *
 * Refer:
 * https://leetcode.com/articles/convert-sorted-list-to-binary-search-tree/#approach-3-inorder-simulation
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(log N). Since the tree is height balanced, height will be
 * log N.
 *
 * N = Number of nodes on the input list.
 */
class Solution1 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        return sortedListToBSTHelper(new ListNode[] { head }, 0, getListSize(head) - 1);
    }

    private int getListSize(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }

    private TreeNode sortedListToBSTHelper(ListNode[] curNode, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return getNextTreeNode(curNode);
        }

        int mid = start + (end - start) / 2;

        TreeNode left = sortedListToBSTHelper(curNode, start, mid - 1);
        TreeNode node = getNextTreeNode(curNode);
        node.left = left;
        node.right = sortedListToBSTHelper(curNode, mid + 1, end);

        return node;
    }

    private TreeNode getNextTreeNode(ListNode[] curNode) {
        TreeNode node = new TreeNode(curNode[0].val);
        curNode[0] = curNode[0].next;
        return node;
    }
}

/**
 * Recursion and finding the mid point of the list
 *
 * Time Complexity: O(N/2 * log N). T(n) = 2 * T(n/2) + O(n/2)
 *
 * Space Complexity: O(log N). Since the tree is height balanced, height will be
 * log N.
 *
 * N = Number of nodes on the input list.
 */
class Solution2 {
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return new TreeNode(head.val);
        }

        return sortedListToBSTHelper(head, null);
    }

    private TreeNode sortedListToBSTHelper(ListNode left, ListNode right) {
        if (left == right) {
            return null;
        }

        ListNode slow = left;
        ListNode fast = left;
        while (fast != right && fast.next != right) {
            fast = fast.next.next;
            slow = slow.next;
        }

        TreeNode node = new TreeNode(slow.val);
        node.left = sortedListToBSTHelper(left, slow);
        node.right = sortedListToBSTHelper(slow.next, right);

        return node;
    }
}
