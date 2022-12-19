// LeetCode Question URL: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
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

/*
 * Preorder
 *
 * Serialize function
 *
 * Time Complexity: O(M * K) = O(M * 11) = O(M)
 *
 * Space Complexity: O(M * K) = O(M * 11) = O(M)
 *
 *
 * Deserialize function
 *
 * Time Complexity: O(N + N + M) = O(Split + parseInt + Recursions) = O(N + M)
 *
 * Space Complexity: O(N + M) = O(Queue Size + Recursion depth)
 *
 * N = Length of the input string. M = Number of Nodes. K = Average number of
 * digits in each node bounded by 11
 *
 * Note: For whole class, time complexity is O(Number of nodes) as every other
 * value is proportional to the number of nodes.
 *
 * In prefect tree, if there are N leaf nodes, then there will be (N-1) non-leaf
 * nodes. Thus the tree will be have total 2N-1 nodes.
 *
 * In this case including nulls (#), we will have 11*N + 2*N + (N+1) = Size
 * nodes + Number of commas + Number of nulls.
 */
class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode node, StringBuilder sb) {
        if (sb.length() != 0) {
            sb.append(',');
        }
        if (node == null) {
            sb.append("#");
        } else {
            sb.append(node.val);
            serializeHelper(node.left, sb);
            serializeHelper(node.right, sb);
        }
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input string data is null");
        }
        if (data.length() == 0) {
            return null;
        }
        return deserializeHelper(new ArrayDeque<>(Arrays.asList(data.split(","))));
    }

    private TreeNode deserializeHelper(Deque<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }

        String cur = queue.poll();
        if ("#".equals(cur)) {
            return null;
        }
        TreeNode node = new TreeNode(Integer.parseInt(cur));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);

        return node;
    }

}

/**
 * This is using Level Order traversal
 */
class Codec2 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            boolean atLeastOneNonNull = false;
            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                if (sb.length() != 0) {
                    sb.append(',');
                }
                if (cur == null) {
                    sb.append('#');
                    continue;
                }
                sb.append(cur.val);

                if (cur.left != null || cur.right != null) {
                    atLeastOneNonNull = true;
                }

                queue.offer(cur.left);
                queue.offer(cur.right);
            }
            if (!atLeastOneNonNull) {
                break;
            }
        }

        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null) {
            throw new IllegalArgumentException("Input string data is null");
        }
        if (data.length() == 0) {
            return null;
        }

        String[] dataArr = data.split(",");
        List<TreeNode> nodeList = new ArrayList<>();
        for (String d : dataArr) {
            nodeList.add("#".equals(d) ? null : new TreeNode(Integer.parseInt(d)));
        }

        TreeNode root = nodeList.get(0);

        int idx = 1;
        int numNodes = nodeList.size();
        for (TreeNode node : nodeList) {
            if (node != null && idx < numNodes) {
                node.left = nodeList.get(idx++);
                node.right = idx < numNodes ? nodeList.get(idx++) : null;
            }
            if (idx == numNodes) {
                break;
            }
        }

        return root;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));