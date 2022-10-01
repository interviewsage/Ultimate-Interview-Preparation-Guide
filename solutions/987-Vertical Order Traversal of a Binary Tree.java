// LeetCode Question URL: https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree

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
 * BFS - Iterative
 *
 * <pre>
 * Time Complexity: Between O(N) & O(N log N)
 * 1. Normal Level Order Traversal will take O(N) Time
 * 2. To add each levelMap into colMap will:
 *      - In Best case when the tree does not have overlapping nodes -> O(N)
 *      - In Worst case of Perfect Binary Tree, it will result in Pascal Triangle.
 *        Each Overlapping node set will have to be sorted.
 *        The time complexity will be much less than O(N * log N) as only overlapping nodes will be sorted.
 * 3. To populate the result list -> O(N)
 *
 * Thus, Total Time Complexity will be between O(N) & O(N log N). Note it will be much less than O(N log N).
 * </pre>
 *
 * Space Complexity: O(N)
 *
 * N = Number nodes in the tree.
 */
class Solution1 {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Map<Integer, List<Integer>> colMap = new HashMap<>();
        int minCol = 0;
        int maxCol = 0;

        Queue<Pair<TreeNode, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root, 0));

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Map<Integer, List<Integer>> levelMap = new HashMap<>();

            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> cur = queue.poll();
                TreeNode curNode = cur.getKey();
                int col = cur.getValue();

                levelMap.putIfAbsent(col, new ArrayList<>());
                levelMap.get(col).add(curNode.val);

                if (curNode.left != null) {
                    queue.offer(new Pair<>(curNode.left, col - 1));
                    minCol = Math.min(minCol, col - 1);
                }
                if (curNode.right != null) {
                    queue.offer(new Pair<>(curNode.right, col + 1));
                    maxCol = Math.max(maxCol, col + 1);
                }
            }

            for (int col : levelMap.keySet()) {
                List<Integer> colVals = levelMap.get(col);
                if (colVals.size() > 1) {
                    Collections.sort(colVals);
                }
                colMap.putIfAbsent(col, new ArrayList<>());
                colMap.get(col).addAll(colVals);
            }
        }

        for (int i = minCol; i <= maxCol; i++) {
            result.add(colMap.get(i));
        }

        return result;
    }
}

/**
 * BFS - Iterative
 *
 * Time Complexity: O(N log N)
 *
 * Space Complexity: O(N)
 *
 * N = Number nodes in the tree.
 */
class Solution2 {
    class Node implements Comparable<Node> {
        TreeNode node;
        int x;
        int y;

        public Node(TreeNode _n, int _x, int _y) {
            node = _n;
            x = _x;
            y = _y;
        }

        @Override
        public int compareTo(Node n) {
            if (y != n.y) {
                return n.y - y;
            } else {
                return node.val - n.node.val;
            }
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();
        Queue<Node> queue = new LinkedList<>();
        int min = 0;
        int max = 0;

        queue.offer(new Node(root, 0, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            if (!map.containsKey(cur.x)) {
                map.put(cur.x, new PriorityQueue<Node>());
            }

            map.get(cur.x).offer(cur);

            if (cur.node.left != null) {
                queue.offer(new Node(cur.node.left, cur.x - 1, cur.y - 1));
                min = Math.min(min, cur.x - 1);
            }
            if (cur.node.right != null) {
                queue.offer(new Node(cur.node.right, cur.x + 1, cur.y - 1));
                max = Math.max(max, cur.x + 1);
            }
        }

        for (int i = min; i <= max; i++) {
            PriorityQueue<Node> pq = map.get(i);
            List<Integer> temp = new ArrayList<>();
            while (!pq.isEmpty()) {
                temp.add(pq.poll().node.val);
            }

            result.add(temp);
        }

        return result;
    }
}

/**
 * DFS
 *
 * Time Complexity: O(N log N)
 *
 * Space Complexity: O(N + H) = O(N)
 *
 * N = Number nodes in the tree. H = Height of the tree.
 */
class Solution3 {

    class Node implements Comparable<Node> {
        TreeNode node;
        int y;

        public Node(TreeNode _n, int _y) {
            node = _n;
            y = _y;
        }

        @Override
        public int compareTo(Node n) {
            if (y != n.y) {
                return n.y - y;
            } else {
                return node.val - n.node.val;
            }
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        HashMap<Integer, PriorityQueue<Node>> map = new HashMap<>();
        int[] max = new int[1];
        int[] min = new int[1];
        dfs(map, root, 0, 0, max, min);

        for (int i = min[0]; i <= max[0]; i++) {
            PriorityQueue<Node> pq = map.get(i);
            List<Integer> temp = new ArrayList<>();
            while (!pq.isEmpty()) {
                temp.add(pq.poll().node.val);
            }

            result.add(temp);
        }

        return result;
    }

    private void dfs(HashMap<Integer, PriorityQueue<Node>> map, TreeNode node, int x, int y, int[] max, int[] min) {
        if (node == null) {
            return;
        }

        if (!map.containsKey(x)) {
            map.put(x, new PriorityQueue<Node>());
        }
        map.get(x).offer(new Node(node, y));
        max[0] = Math.max(max[0], x);
        min[0] = Math.min(min[0], x);

        dfs(map, node.left, x - 1, y - 1, max, min);
        dfs(map, node.right, x + 1, y - 1, max, min);
    }
}
