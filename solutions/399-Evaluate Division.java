// LeetCode Question URL: https://leetcode.com/problems/evaluate-division/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Build a graph of equations and then perform DFS to find the result of each
 * query
 *
 * Imagine a/b = k as a link between node a and b, the value of link from a to b
 * is k, the reverse link is 1/k. Query is to find a path between two nodes.
 *
 * <pre>
 * Time Complexity:
 * 1. Build Graph = O(E)
 * 2. Iterate on Queries = O(Q)
 * 3. Each Iteration can invoke DFS = O(Vertex + Edges) = O(E)
 * ==> Vertexes = 2*E
 * ==> Edges = E (Only during recursion we only use E instead of 2*E edges saved in graph)
 *
 * Thus, Total time complexity: O(E + Q*E)
 *
 * Space Complexity:
 * 1. Graph = O(V + E) = O(2E + 2E*2) = O(E)
 *      We are using a memoization to add newly found edges. Sp there can be V * (V-1) edges in the whole graph.
 * 2. Recursion depth = O(E)
 * 3. Visited Set = O(Vertexes) = O(2*E) = O(E)
 *
 * Thus, Total space complexity: O(E + Newly added edges due to memoization)
 * </pre>
 *
 * E = length of equations or values. Q = length of queries
 */
class Solution1 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (equations == null || values == null || queries == null || equations.size() != values.length) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int numQueries = queries.size();
        int numEquations = equations.size();
        double[] result = new double[numQueries];

        if (numEquations == 0 || numQueries == 0) {
            Arrays.fill(result, -1.0);
            return result;
        }

        Map<String, Map<String, Double>> graph = new HashMap<>();
        for (int i = 0; i < numEquations; i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);

            graph.putIfAbsent(a, new HashMap<>());
            graph.get(a).put(b, values[i]);

            graph.putIfAbsent(b, new HashMap<>());
            graph.get(b).put(a, 1 / values[i]);
        }

        for (int i = 0; i < numQueries; i++) {
            String a = queries.get(i).get(0);
            String b = queries.get(i).get(1);

            Map<String, Double> numeratorMap = graph.get(a);
            if (numeratorMap == null || !graph.containsKey(b)) {
                result[i] = -1.0;
            } else if (a.equals(b)) {
                result[i] = 1.0;
            } else {
                Double val = numeratorMap.get(b);
                result[i] = val != null ? val : calcEquationDfsHelper(graph, a, b, new HashSet<>());
            }
        }

        return result;
    }

    private double calcEquationDfsHelper(Map<String, Map<String, Double>> graph, String src, String dest,
            Set<String> visited) {
        Map<String, Double> numeratorMap = graph.get(src);
        Double destVal = numeratorMap.get(dest);

        if (destVal != null) {
            return destVal;
        }

        visited.add(src);
        double result = -1.0;

        for (String next : numeratorMap.keySet()) {
            double nextVal = numeratorMap.get(next);
            if (visited.contains(next) || nextVal == -1.0) {
                continue;
            }
            double nextResult = calcEquationDfsHelper(graph, next, dest, visited);
            if (nextResult != -1) {
                result = nextVal * nextResult;
                break;
            }
        }

        // if (result != -1.0) {
        graph.get(src).put(dest, result);
        graph.get(dest).put(src, 1 / result);
        // }

        return result;
    }
}

/**
 * Build a graph of equations and then perform DFS to find the result of each
 * query
 *
 * Imagine a/b = k as a link between node a and b, the value of link from a to b
 * is k, the reverse link is 1/k. Query is to find a path between two nodes.
 *
 * Time Complexity: BuildGraph + DFS * NoOfQueries. BuildGraph = O(E). DFS =
 * (NoOfNodes + NoOfSelfEdges + NoOfEdges) = O(2*E + 2*E + 2*E) = O(6*E).
 *
 * Thus total complexity = O(E + 6*E*Q) = O(E*Q).
 *
 * Space Complexity: Space taken by graph + Recursion Depth = O(E).
 *
 * E = length of equations or values. Q = length of queries
 */
class Solution2 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        if (queries == null || queries.size() == 0) {
            return new double[0];
        }

        HashMap<String, HashMap<String, Double>> graph = buildGraph(equations, values);

        double[] result = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            List<String> q = queries.get(i);
            String src = q.get(0);
            String dest = q.get(1);

            if (!graph.containsKey(src) || !graph.containsKey(dest)) {
                result[i] = -1.0;
            } else if (graph.get(src).containsKey(dest)) {
                result[i] = graph.get(src).get(dest);
            } else {
                result[i] = dfsHelper(graph, src, dest, new HashSet<>());
            }
        }

        return result;
    }

    private double dfsHelper(HashMap<String, HashMap<String, Double>> graph, String src, String dest,
            HashSet<String> visited) {

        if (visited.contains(src)) {
            return -1.0;
        }

        if (graph.get(src).containsKey(dest)) {
            return graph.get(src).get(dest);
        }

        visited.add(src);
        double res = -1;
        HashMap<String, Double> neighbors = graph.get(src);

        for (String n : neighbors.keySet()) {
            res = dfsHelper(graph, n, dest, visited);
            if (res != -1.0) {
                res *= neighbors.get(n);
                break;
            }
        }

        if (res != -1) {
            graph.get(src).put(dest, res);
            graph.get(dest).put(src, 1 / res);
        }

        return res;
    }

    private HashMap<String, HashMap<String, Double>> buildGraph(List<List<String>> equations, double[] values) {

        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();
        if (equations == null || equations.size() == 0 || values == null || equations.size() != values.length) {
            return graph;
        }

        for (int i = 0; i < values.length; i++) {
            List<String> e = equations.get(i);
            String src = e.get(0);
            String dest = e.get(1);

            if (!graph.containsKey(src)) {
                graph.put(src, new HashMap<>());
                graph.get(src).put(src, 1.0);
            }
            if (!graph.containsKey(dest)) {
                graph.put(dest, new HashMap<>());
                graph.get(dest).put(dest, 1.0);
            }

            graph.get(src).put(dest, values[i]);
            graph.get(dest).put(src, 1 / values[i]);
        }

        return graph;
    }
}
