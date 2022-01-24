// LeetCode Question URL: https://leetcode.com/problems/course-schedule-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using DFS
 *
 * Time Complexity: O(Vertices + Edges) = O(N + P).
 *
 * <pre>
 * Time Complexity:
 * 1. Initialize toBeVisited --> O(N)
 * 2. build graph --> O(P). Here P is bounded by O(N * (N-1))
 * 3. DFS --> O(V + E) = O(N + P)
 *
 * Total Time Complexity: O(2 * (N + P)) = O(N + P)
 *
 * Space Complexity:
 * 1. Graph --> 2 * P --> All edges in the graph
 * 2. toBeVisited --> O(N)
 * 3. visited + visiting --> O(N)
 * 4. DFS Recursion Depth --> O(min(N, P))
 *
 * Total Space Complexity: O(2P + 2N + min(N, P)) = O(P + N + min(N, P))
 * </pre>
 *
 * In worst case (in DAG) P = N * (N - 1) / 2. Thus worst case complexity:
 * O(N^2). Refer: https://stackoverflow.com/a/11699123
 *
 * N = Number of Courses or Number of vertices. P = Number of prerequisites or
 * Number of edges.
 */
class Solution1 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) {
            throw new IllegalArgumentException("Invalid number of courses");
        }

        int[] result = new int[numCourses];

        if (numCourses <= 1 || prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> toBeVisited = new HashSet<>(); // Nodes with zero in-degree
        // Initializing toBeVisited with all possible courses
        for (int i = 0; i < numCourses; i++) {
            toBeVisited.add(i);
        }
        for (int[] p : prerequisites) {
            graph.putIfAbsent(p[0], new HashSet<>());
            graph.get(p[0]).add(p[1]);
            toBeVisited.remove(p[1]);
        }

        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        int[] resIdx = { 0 };

        for (int course : toBeVisited) {
            if (hasCycle(course, graph, visiting, visited, result, resIdx)) {
                return new int[0];
            }
        }

        return resIdx[0] != numCourses ? new int[0] : result;
    }

    public boolean hasCycle(int course, Map<Integer, Set<Integer>> graph, Set<Integer> visiting, Set<Integer> visited,
            int[] result, int[] resIdx) {
        if (visiting.contains(course)) {
            return true;
        }
        if (visited.contains(course)) {
            return false;
        }

        visiting.add(course);

        if (graph.containsKey(course)) {
            for (int n : graph.get(course)) {
                if (hasCycle(n, graph, visiting, visited, result, resIdx)) {
                    return true;
                }
            }
        }

        visiting.remove(course);
        visited.add(course);
        result[resIdx[0]++] = course;

        return false;
    }
}

/**
 * Using BFS
 *
 * Time Complexity: O(Vertices + Edges) = O(N + P).
 *
 * Space Complexity: O(Vertices + Edges) = O(N + P).
 *
 * In worst case (in DAG) P = N * (N - 1) / 2. Thus worst case complexity:
 * O(N^2). Refer: https://stackoverflow.com/a/11699123
 *
 * N = Number of Courses or Number of vertices. P = Number of prerequisites or
 * Number of edges.
 */
class Solution2 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }
        int[] result = new int[numCourses];
        if (numCourses == 1 || prerequisites == null || prerequisites.length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        HashMap<Integer, HashSet<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        buildGraph(numCourses, prerequisites, graph, inDegree);

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int count = 0;

        while (!queue.isEmpty()) {
            int course = queue.poll();
            result[count] = course;
            count++;
            for (int n : graph.get(course)) {
                inDegree[n]--;
                if (inDegree[n] == 0) {
                    queue.offer(n);
                }
            }
        }

        return count == numCourses ? result : new int[0];
    }

    private void buildGraph(int numCourses, int[][] prerequisites, HashMap<Integer, HashSet<Integer>> graph,
            int[] inDegree) {
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }
    }
}
