// LeetCode Question URL: https://leetcode.com/problems/binary-search-tree-iterator-ii/
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
 * Using Stack to perform In-order traversal of BST to get next nodes. Also
 * maintaining the list of nodes returned in past to get the previous.
 *
 * Refer:
 * https://leetcode.com/problems/binary-search-tree-iterator-ii/discuss/851112/C++-Stack-and-List
 *
 * Time Complexity: All Functions are amortized O(1)
 * Let's look at the complexities one by one:
 * O(1) for the constructor.
 * O(1) for hasPrev.
 * O(1) for prev.
 * O(1) for hasNext.
 * O(N) for next. In the worst-case of the skewed tree one has to parse the
 * entire tree, all N nodes.
 *
 * However, the important thing to note here is that it's the worst-case time
 * complexity. We only make such a call for the nodes which we've not yet
 * parsed. We save all parsed nodes in a list, and then re-use them if we need
 * to return next from the already parsed area of the tree.
 *
 * Thus, the amortized (average) time complexity for the next call would still
 * be O(1).
 *
 * Space Complexity: Both stack and list are disjoint. Thus they will never have
 * overlapping elements. Total space complexity: O(N)
 *
 * N = Number of nodes in the tree.
 */
class BSTIterator implements Iterator<Integer> {

    Deque<TreeNode> stack;
    List<TreeNode> seenList;
    int curPos;

    public BSTIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        seenList = new ArrayList<>();
        curPos = -1;
        exploreLeftNodes(root);
    }

    private void exploreLeftNodes(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty() || curPos + 1 < seenList.size();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No next element");
        }

        curPos++;

        if (curPos < seenList.size()) {
            return seenList.get(curPos).val;
        }

        TreeNode next = stack.pop();
        exploreLeftNodes(next.right);
        seenList.add(next);
        return next.val;
    }

    public boolean hasPrev() {
        return curPos > 0;
    }

    public Integer prev() {
        if (!hasPrev()) {
            throw new NoSuchElementException("No prev element");
        }

        return seenList.get(--curPos).val;
    }
}

/**
 * Other solutions:
 *
 * 1. Here using 2nd stack instead of list. This does not save on space:
 * https://leetcode.com/problems/binary-search-tree-iterator-ii/discuss/859209/C++-Super-short-Two-Stacks
 *
 * 2. Here we are using 2 stacks. But this saves space buy only saving the path:
 * https://leetcode.com/problems/binary-search-tree-iterator-ii/discuss/1966563/Java-or-O(H-tree-height)-space-or-O(N)-time
 *
 * 3. Using only one stack. (Very complicated):
 * https://leetcode.com/problems/binary-search-tree-iterator-ii/discuss/854702/Python3-no-pre-calculation-no-array-for-prev()-extension-of-binary-search-tree-iterator-1
 */
