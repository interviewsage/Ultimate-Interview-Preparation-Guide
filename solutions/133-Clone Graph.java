// LeetCode Question URL: https://leetcode.com/problems/clone-graph/
// LeetCode Discuss URL: https://leetcode.com/problems/clone-graph/discuss/1496599/Java-or-TC:-O(V+E)-or-SC:-O(V)-or-Both-BFS-and-DFS-Solutions

import java.util.*;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

/**
 * DFS - Recursive
 *
 * Time Complexity: O(V + E)
 *
 * Space Complexity: O(V). Both Recursion Stack and HashMap will take O(V) space
 *
 * V = Number of nodes. E = Number of edges in the graph.
 */
class Solution1 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }
        if (node.neighbors.size() == 0) {
            return new Node(node.val);
        }

        return cloneGraphDfsHelper(node, new HashMap<>());
    }

    private Node cloneGraphDfsHelper(Node node, Map<Node, Node> cloneMap) {
        Node clone = cloneMap.get(node);
        if (clone != null) {
            return clone;
        }

        clone = new Node(node.val);
        cloneMap.put(node, clone);

        for (Node n : node.neighbors) {
            clone.neighbors.add(cloneGraphDfsHelper(n, cloneMap));
        }

        return clone;
    }
}

/**
 * BFS - Iterative
 *
 * Time Complexity: O(V + E)
 *
 * Space Complexity: O(V). Both Queue and HashMap will take O(V) space
 *
 * V = Number of nodes. E = Number of edges in the graph.
 */
class Solution2 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Node newNode = new Node(node.val);
        if (node.neighbors.size() == 0) {
            return newNode;
        }

        Map<Node, Node> cloneMap = new HashMap<>();
        Queue<Node> queue = new ArrayDeque<>();
        cloneMap.put(node, newNode);
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            List<Node> newNeighbors = cloneMap.get(cur).neighbors;
            for (Node n : cur.neighbors) {
                Node clone = cloneMap.get(n);
                if (clone == null) {
                    clone = new Node(n.val);
                    cloneMap.put(n, clone);
                    if (n.neighbors.size() > 0) {
                        queue.offer(n);
                    }
                }
                newNeighbors.add(clone);
            }
        }

        return newNode;
    }
}
