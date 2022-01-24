// LeetCode Question URL: https://leetcode.com/problems/maximum-binary-tree/
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
 * Refer :
 * https://leetcode.com/problems/maximum-binary-tree/discuss/106156/Java-worst-case-O(N)-solution/143674
 *
 * Time Complexity: O(N). Each TreeNode will be created once, and will be put
 * into the stack and move out of the stack once for worst case. So the time
 * complexity is O(N).
 *
 * Space Complexity: O(H) = O(N). In worst case, if the array is in descending
 * order.
 *
 * N = Length of input array. H = Height of the output tree.
 */
class Solution1 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.addFirst(new TreeNode(nums[0]));

        for (int i = 1; i < nums.length; i++) {
            TreeNode cur = new TreeNode(nums[i]);
            while (!deque.isEmpty() && deque.getFirst().val < nums[i]) {
                cur.left = deque.removeFirst();
            }
            if (!deque.isEmpty()) {
                deque.getFirst().right = cur;
            }
            deque.addFirst(cur);
        }

        return deque.getLast();
    }
}

class Solution2 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null) {
            return null;
        }

        Stack<TreeNode> stack = new Stack<>();

        for (int n : nums) {
            TreeNode cur = new TreeNode(n);

            while (!stack.isEmpty() && stack.peek().val <= n) {
                cur.left = stack.pop();
            }

            if (!stack.isEmpty()) {
                stack.peek().right = cur;
            }

            stack.push(cur);
        }

        return stack.isEmpty() ? null : stack.get(0);
    }
}

/**
 * Brute Force Iterative Solution
 *
 * Time Complexity: O(N * H) = O(N^2). Each TreeNode will be created once, and
 * will be put into the stack and move out of the stack once for worst case. So
 * the time complexity is O(N).
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array. H = Height of the output tree.
 */
class Solution3 {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        TreeNode root = new TreeNode(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            TreeNode newNode = new TreeNode(nums[i]);
            if (nums[i] > root.val) {
                newNode.left = root;
                root = newNode;
                continue;
            }

            TreeNode prev = root;
            TreeNode cur = root.right;
            while (cur != null && nums[i] < cur.val) {
                prev = cur;
                cur = cur.right;
            }
            newNode.left = cur;
            prev.right = newNode;
        }

        return root;
    }
}
