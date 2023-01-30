// LeetCode Question URL: https://leetcode.com/problems/two-sum-iv-input-is-a-bst/
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
 * Using BST Iterator to get values in ascending and descending in-order. Then
 * use the pointer approach to find if teh pair exists.
 *
 * This solution will work for BST only.
 *
 * Time Complexity: O(N). BSTIterator's next is amortized O(1)
 *
 * Space Complexity: O(H + H) = O(H)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution1 {
    public class BstIterator implements Iterator<Integer> {
        Deque<TreeNode> stack;
        boolean ascending;

        public BstIterator(TreeNode root, boolean asc) {
            stack = new ArrayDeque<>();
            ascending = asc;
            exploreNodes(root);
        }

        private void exploreNodes(TreeNode node) {
            while (node != null) {
                stack.push(node);
                node = ascending ? node.left : node.right;
            }
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException("Next element not found");
            }
            TreeNode next = stack.pop();
            exploreNodes(ascending ? next.right : next.left);
            return next.val;
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        BstIterator left = new BstIterator(root, true);
        BstIterator right = new BstIterator(root, false);

        int l = left.next();
        int r = right.next();

        while (l < r) {
            int sum = l + r;
            if (sum == k) {
                return true;
            }

            if (sum < k) {
                l = left.next();
            } else {
                r = right.next();
            }
        }

        return false;
    }
}

/**
 * Normal Tree Traversal using BFS. Save each number in a set and keep checking
 * the diff if it exists in the set.
 *
 * This solution will work for any type of tree.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N + W) = O(N)
 *
 * N = Number of nodes in the tree. W = Width of the tree.
 */
class Solution2 {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        Set<Integer> set = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            if (set.contains(k - cur.val)) {
                return true;
            }
            set.add(cur.val);

            if (cur.left != null) {
                queue.offer(cur.left);
            }
            if (cur.right != null) {
                queue.offer(cur.right);
            }
        }

        return false;
    }
}

/**
 * Find Inorder of BST (Recursive) and then search in sorted list.
 *
 * This solution will work if we have to pre-process the BST so that we can make
 * multiple queries.
 *
 * Time Complexity: O(N + N) = O(N)
 *
 * Space Complexity: O(N + H) = O(N)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution3 {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        List<Integer> list = new ArrayList<>();
        inorder(root, list);

        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            int sum = list.get(start) + list.get(end);
            if (sum == k) {
                return true;
            }

            if (sum < k) {
                start++;
            } else {
                end--;
            }
        }

        return false;
    }

    private void inorder(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        inorder(node.left, list);
        list.add(node.val);
        inorder(node.right, list);
    }
}

/**
 * Find Inorder of BST (Iterative) and then search in sorted list.
 *
 * This solution will work if we have to pre-process the BST so that we can make
 * multiple queries.
 *
 * Time Complexity: O(N + N) = O(N)
 *
 * Space Complexity: O(N + H) = O(N)
 *
 * N = Number of nodes in the tree. H = Height of the tree.
 */
class Solution4 {
    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }

        List<Integer> list = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root;

        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            list.add(cur.val);
            cur = cur.right;
        }

        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            int sum = list.get(start) + list.get(end);
            if (sum == k) {
                return true;
            }

            if (sum < k) {
                start++;
            } else {
                end--;
            }
        }

        return false;
    }
}
