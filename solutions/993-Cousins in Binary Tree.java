// LeetCode Question URL: https://leetcode.com/problems/cousins-in-binary-tree/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Iterative Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W) = O(N/2) in worst case of full binary tree.
 *
 * W = Width of the tree. N = Number of nodes in tree.
 */
class Solution1 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode parentX = null;
        TreeNode parentY = null;
        boolean foundOne = false;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    if (cur.left.val == x) {
                        if (parentY != null) {
                            return parentY != cur;
                        }
                        parentX = cur;
                        foundOne = true;
                    } else if (cur.left.val == y) {
                        if (parentX != null) {
                            return parentX != cur;
                        }
                        parentY = cur;
                        foundOne = true;
                    }
                    if (!foundOne) {
                        queue.offer(cur.left);
                    }
                }
                if (cur.right != null) {
                    if (cur.right.val == x) {
                        if (parentY != null) {
                            return parentY != cur;
                        }
                        parentX = cur;
                        foundOne = true;
                    } else if (cur.right.val == y) {
                        if (parentX != null) {
                            return parentX != cur;
                        }
                        parentY = cur;
                        foundOne = true;
                    }
                    if (!foundOne) {
                        queue.offer(cur.right);
                    }
                }
            }
            if (foundOne) {
                return false;
            }
        }

        return false;
    }
}

/**
 * Iterative Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(W) = O(N/2) in worst case of full binary tree.
 *
 * W = Width of the tree. N = Number of nodes in tree.
 */
class Solution2 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y) {
            return false;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode pX = null;
        TreeNode pY = null;

        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int i = 0; i < len; i++) {
                TreeNode cur = queue.poll();
                if (cur.left != null) {
                    if (cur.left.val == x) {
                        if (pY != null) {
                            return pY != cur;
                        }
                        pX = cur;
                    } else if (cur.left.val == y) {
                        if (pX != null) {
                            return pX != cur;
                        }
                        pY = cur;
                    }
                    if (pX == null && pY == null) {
                        queue.offer(cur.left);
                    }
                }
                if (cur.right != null) {
                    if (cur.right.val == x) {
                        if (pY != null) {
                            return pY != cur;
                        }
                        pX = cur;
                    } else if (cur.right.val == y) {
                        if (pX != null) {
                            return pX != cur;
                        }
                        pY = cur;
                    }
                    if (pX == null && pY == null) {
                        queue.offer(cur.right);
                    }
                }
            }
            if (pX != null || pY != null) {
                return false;
            }
        }

        return false;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H) = O(N) in worst case of skewed tree.
 *
 * H = Height of the tree. N = Number of nodes in tree.
 */
class Solution3 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y) {
            return false;
        }

        TreeNode[] parents = new TreeNode[2];
        int[] depths = { -1, -1 };
        return dfsHelper(root, x, y, parents, depths, 0);
    }

    private boolean dfsHelper(TreeNode cur, int x, int y, TreeNode[] parents, int[] depths, int curDepth) {
        boolean[] result = { false };
        if (cur.left != null) {
            Boolean finalResult = compareNode(cur, cur.left, x, y, parents, depths, curDepth, result);
            if (finalResult != null) {
                return finalResult;
            }
        }
        if (cur.right != null) {
            Boolean finalResult = compareNode(cur, cur.right, x, y, parents, depths, curDepth, result);
            if (finalResult != null) {
                return finalResult;
            }
        }
        return result[0];
    }

    private Boolean compareNode(TreeNode parent, TreeNode child, int x, int y, TreeNode[] parents, int[] depths,
            int curDepth, boolean[] result) {
        if (child.val == x) {
            if (parents[1] != null) {
                return depths[1] == curDepth && parents[1] != parent;
            }
            depths[0] = curDepth;
            parents[0] = parent;
        } else if (child.val == y) {
            if (parents[0] != null) {
                return depths[0] == curDepth && parents[0] != parent;
            }
            depths[1] = curDepth;
            parents[1] = parent;
        } else if (depths[0] != curDepth && depths[1] != curDepth) {
            result[0] |= dfsHelper(child, x, y, parents, depths, curDepth + 1);
        }
        return null;
    }
}

/**
 * Recursive Solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(H) = O(N) in worst case of skewed tree.
 *
 * H = Height of the tree. N = Number of nodes in tree.
 */
class Solution4 {
    public boolean isCousins(TreeNode root, int x, int y) {
        if (root == null || root.val == x || root.val == y) {
            return false;
        }

        TreeNode[] parents = new TreeNode[2];
        int[] depths = { -1, -1 };

        return dfsHelper(root, x, y, parents, depths, 0);
    }

    private boolean dfsHelper(TreeNode cur, int x, int y, TreeNode[] parents, int[] depths, int curDepth) {
        boolean result = false;

        if (cur.left != null) {
            if (cur.left.val == x) {
                if (parents[1] != null) {
                    return depths[1] == curDepth && parents[1] != cur;
                }
                depths[0] = curDepth;
                parents[0] = cur;
            } else if (cur.left.val == y) {
                if (parents[0] != null) {
                    return depths[0] == curDepth && parents[0] != cur;
                }
                depths[1] = curDepth;
                parents[1] = cur;
            } else {
                // if ((depths[0] == -1 && depths[1] == -1) || (depths[0] != -1 && curDepth <
                // depths[0]) || (depths[1] != -1 && curDepth < depths[1])) {
                if (depths[0] != curDepth && depths[1] != curDepth) {
                    result |= dfsHelper(cur.left, x, y, parents, depths, curDepth + 1);
                }
            }
        }
        if (cur.right != null) {
            if (cur.right.val == x) {
                if (parents[1] != null) {
                    return depths[1] == curDepth && parents[1] != cur;
                }
                depths[0] = curDepth;
                parents[0] = cur;
            } else if (cur.right.val == y) {
                if (parents[0] != null) {
                    return depths[0] == curDepth && parents[0] != cur;
                }
                depths[1] = curDepth;
                parents[1] = cur;
            } else {
                // if ((depths[0] == -1 && depths[1] == -1) || (depths[0] != -1 && curDepth <
                // depths[0]) || (depths[1] != -1 && curDepth < depths[1])) {
                if (depths[0] != curDepth && depths[1] != curDepth) {
                    result |= dfsHelper(cur.right, x, y, parents, depths, curDepth + 1);
                }
            }
        }

        return result;
    }
}
