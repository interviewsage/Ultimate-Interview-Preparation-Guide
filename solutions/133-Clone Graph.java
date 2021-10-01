// LeetCode Question URL: https://leetcode.com/problems/clone-graph/
// LeetCode Discuss URL:

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
 * BFS - Iterative
 *
 * Time Complexity: O(V + E)
 *
 * Space Complexity: O(V). Both Queue and HashMap will take O(V) space
 *
 * V = Number of nodes. E = Number of edges in the graph.
 */
class Solution1 {
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        HashMap<Node, Node> visited = new HashMap<>();
        Node newNode = new Node(node.val);
        visited.put(node, newNode);
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            List<Node> newNeighbors = visited.get(cur).neighbors;
            for (Node n : cur.neighbors) {
                if (!visited.containsKey(n)) {
                    visited.put(n, new Node(n.val));
                    queue.offer(n);
                }
                newNeighbors.add(visited.get(n));
            }
        }

        return newNode;
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
class Solution2 {
    public Node cloneGraph(Node node) {
        return cloneGraphDFSHelper(node, new HashMap<>());
    }

    private Node cloneGraphDFSHelper(Node cur, HashMap<Node, Node> visited) {
        if (cur == null) {
            return null;
        }
        if (visited.containsKey(cur)) {
            return visited.get(cur);
        }

        Node newNode = new Node(cur.val);
        visited.put(cur, newNode);

        for (Node n : cur.neighbors) {
            newNode.neighbors.add(cloneGraphDFSHelper(n, visited));
        }

        return newNode;
    }
}
