// LeetCode Question URL: https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/
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
 * Iterative Approach (BFS).
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W)
 *
 * N = Number of nodes in the tree.
 */
class Solution1 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> curLevel = new ArrayDeque<>();
        curLevel.offerFirst(root);

        while (!curLevel.isEmpty()) {
            boolean evenDepth = result.size() % 2 == 0;
            Deque<TreeNode> nextLevel = new ArrayDeque<>();
            List<Integer> levelList = new ArrayList<>();

            while (!curLevel.isEmpty()) {
                TreeNode cur = curLevel.pollFirst();
                levelList.add(cur.val);
                if (evenDepth) {
                    if (cur.left != null) {
                        nextLevel.offerFirst(cur.left);
                    }
                    if (cur.right != null) {
                        nextLevel.offerFirst(cur.right);
                    }
                } else {
                    if (cur.right != null) {
                        nextLevel.offerFirst(cur.right);
                    }
                    if (cur.left != null) {
                        nextLevel.offerFirst(cur.left);
                    }
                }
            }
            curLevel = nextLevel;
            result.add(levelList);
        }

        return result;
    }
}

class Solution2 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.add(root);

        while (!deque.isEmpty()) {
            int levelSize = deque.size();
            boolean evenDepth = result.size() % 2 == 0;
            List<Integer> levelList = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode cur;
                if (evenDepth) {
                    cur = deque.pollFirst();
                    if (cur.left != null) {
                        deque.offerLast(cur.left);
                    }
                    if (cur.right != null) {
                        deque.offerLast(cur.right);
                    }
                } else {
                    cur = deque.pollLast();
                    if (cur.right != null) {
                        deque.offerFirst(cur.right);
                    }
                    if (cur.left != null) {
                        deque.offerFirst(cur.left);
                    }
                }
                levelList.add(cur.val);
            }

            result.add(levelList);
        }

        return result;
    }
}

class Solution3 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean evenDepth = result.size() % 2 == 0;
            List<Integer> levelList = new LinkedList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                if (evenDepth) {
                    levelList.add(cur.val);
                } else {
                    levelList.add(0, cur.val);
                }

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
            result.add(levelList);
        }

        return result;
    }
}

/**
 * Recursive Approach (DFS).
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Number of nodes in the tree.
 */
class Solution4 {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        zigzagLevelOrderHelper(result, root, 0);
        return result;
    }

    private void zigzagLevelOrderHelper(List<List<Integer>> result, TreeNode node, int depth) {
        if (node == null) {
            return;
        }

        if (result.size() == depth) {
            result.add(new LinkedList<>());
        }
        if (depth % 2 == 0) {
            result.get(depth).add(node.val);
        } else {
            result.get(depth).add(0, node.val);
        }

        zigzagLevelOrderHelper(result, node.left, depth + 1);
        zigzagLevelOrderHelper(result, node.right, depth + 1);
    }
}
