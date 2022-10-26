// LeetCode Question URL: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/
// LeetCode Discuss URL:

import java.util.*;

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
 * Recursive Solution
 *
 * Time Complexity: O(N). Each element in the array is visited once.
 *
 * Space Complexity: O(Height of the tree) = O(log N) for a balanced tree.
 *
 * N = Length of the input array.
 */
class Solution1 {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        return sortedArrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBSTHelper(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(nums[start]);
        }

        int mid = start + (end - start) / 2;
        TreeNode node = new TreeNode(nums[mid]);
        node.left = sortedArrayToBSTHelper(nums, start, mid - 1);
        node.right = sortedArrayToBSTHelper(nums, mid + 1, end);
        return node;
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(N). Each element in the array is visited once.
 *
 * Space Complexity: O(Height of the tree) = O(log N) for a balanced tree.
 *
 * N = Length of the input array.
 */
class Solution2 {
    public class Node {
        TreeNode node;
        int start;
        int mid;
        int end;

        public Node(TreeNode node, int start, int mid, int end) {
            this.node = node;
            this.start = start;
            this.mid = mid;
            this.end = end;
        }
    }

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = nums.length;
        if (len == 0) {
            return null;
        }
        if (len == 1) {
            return new TreeNode(nums[0]);
        }

        Deque<Node> stack = new ArrayDeque<>();
        int mid = (len - 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        stack.push(new Node(root, 0, mid, len - 1));

        while (!stack.isEmpty()) {
            Node cur = stack.pop();
            if (cur.start < cur.mid) {
                mid = cur.start + (cur.mid - 1 - cur.start) / 2;
                cur.node.left = new TreeNode(nums[mid]);
                if (cur.start < cur.mid - 1) {
                    stack.push(new Node(cur.node.left, cur.start, mid, cur.mid - 1));
                }
            }
            if (cur.mid < cur.end) {
                mid = cur.mid + 1 + (cur.end - (cur.mid + 1)) / 2;
                cur.node.right = new TreeNode(nums[mid]);
                if (cur.mid + 1 < cur.end) {
                    stack.push(new Node(cur.node.right, cur.mid + 1, mid, cur.end));
                }
            }
        }

        return root;
    }
}
