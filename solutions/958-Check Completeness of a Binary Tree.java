// LeetCode Question URL: https://leetcode.com/problems/check-completeness-of-a-binary-tree/
// LeetCode Discuss URL:

import java.util.*;

// Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

/**
 * DFS.
 *
 * <pre>
 * Refer to DFS Solution here:
 * https://leetcode.com/problems/check-completeness-of-a-binary-tree/discuss/205682/JavaC%2B%2BPython-BFS-Solution-and-DFS-Soluiton
 *
 * Looking back to this problem, my first intuition is actually a dfs if root is
 * a complete tree , dfs(root) return the count of nodes in a tree, otherwise it
 * will return -1
 *
 * Before jumping to explanation, if x = 2^h-1 ..  then x & (x+1) == 0.
 *
 * Also, If a tree is a perfect tree, it must have 1,3,7,15,31.. nodes, which is
 * pow of 2 minus 1.
 *
 * Lets say:
 *      l = count of nodes in left subtree
 *      r = count of nodes in right subtree
 *
 * For the whole tree to be complete, each of its subtree should be complete.
 *
 * For a complete tree, it must satisfy at least one of the following condition:
 *
 * 1. If left subtree is a perfect tree with l nodes and height h.
 *      Then total no. of nodes in left subtree = l = 2^(h+1) - 1
 *
 *      Minimum no. of nodes in right subtree = Nodes in perfect subtree with height (h-1)
 *
 *      l - 1 = 2^(h+1) - 2
 *      (l-1)/2 = 2^h -1 = which should be the minimum number of nodes required in the right subtree.
 *
 *      Thus, (l-1)/2 <= r <= l
 *
 * 2. If right subtree is a perfect tree with r nodes and height h.
 *      Then total no. of nodes in right subtree = r = 2^(h+1) - 1
 *
 *      Maximum no. of nodes in left subtree = Nodes in perfect subtree with height (h+1)
 *
 *      r + (r+1) = 2^(h+1) - 1 + (2^(h+1) - 1 + 1)
 *                = 2*2^(h+1) - 1 = which should be the maximum number of nodes required in the left subtree.
 *
 *      Thus, r <= l <= 2*r+1
 *
 *
 * Time O(N), Space O(height)
 *
 * N = Number of nodes in the tree.
 * </pre>
 */
class Solution1 {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        return dfsHelper(root) != -1;
    }

    private int dfsHelper(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftCount = dfsHelper(node.left);
        if (leftCount == -1) {
            return -1;
        }
        int rightCount = dfsHelper(node.right);
        if (rightCount == -1) {
            return -1;
        }

        if (((leftCount & (leftCount + 1)) == 0 && (leftCount - 1) / 2 <= rightCount && rightCount <= leftCount)
                || ((rightCount & (rightCount + 1)) == 0 && rightCount <= leftCount
                        && leftCount <= (2 * rightCount + 1))) {
            return leftCount + rightCount + 1;
        }

        return -1;
    }
}

/**
 * Level Order Traversal.
 *
 * After first while loop, all leaf nodes will be left in the queue. If NOT then
 * the result is false.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W) = O(N)
 *
 * N = Number of nodes in the tree. W = Width of the tree.
 */
class Solution2 {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();

            if (cur.left != null) {
                queue.offer(cur.left);
            } else {
                if (cur.right != null) {
                    return false;
                }
                break;
            }

            if (cur.right != null) {
                queue.offer(cur.right);
            } else {
                break;
            }
        }

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null || cur.right != null) {
                return false;
            }
        }

        return true;
    }
}

class Solution3 {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.right != null) {
                if (cur.left == null) {
                    return false;
                }
                queue.offer(cur.left);
                queue.offer(cur.right);
            } else {
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                break;
            }
        }

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (cur.left != null || cur.right != null) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Level Order Traversal.
 *
 * After first while loop, there should be all null. If NOT then the result is
 * false.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(2*W) = O(N)
 *
 * N = Number of nodes in the tree. W = Width of the tree.
 */
class Solution4 {
    public boolean isCompleteTree(TreeNode root) {
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (queue.peek() != null) {
            TreeNode cur = queue.poll();
            queue.offer(cur.left);
            queue.offer(cur.right);
        }

        while (!queue.isEmpty() && queue.peek() == null) {
            queue.poll();
        }

        return queue.isEmpty();
    }
}
