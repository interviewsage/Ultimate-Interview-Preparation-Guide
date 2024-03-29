// LeetCode Question URL: https://leetcode.com/problems/course-schedule/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using DFS
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
class Solution1 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (numCourses <= 1 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Set<Integer> toBeVisited = new HashSet<>(); // Nodes with zero in-degree
        // Initializing toBeVisited with all possible courses
        for (int i = 0; i < numCourses; i++) {
            toBeVisited.add(i);
            graph.put(i, new HashSet<>());
        }
        for (int[] p : prerequisites) {
            graph.get(p[0]).add(p[1]);
            toBeVisited.remove(p[1]);
        }

        Set<Integer> visited = new HashSet<>();

        for (int course : toBeVisited) {
            if (hasCycle(course, graph, new HashSet<>(), visited)) {
                return false;
            }
        }

        return visited.size() == numCourses;
    }

    private boolean hasCycle(int course, Map<Integer, Set<Integer>> graph, Set<Integer> visiting,
            Set<Integer> visited) {
        visiting.add(course);
        for (int pre : graph.get(course)) {
            if (visited.contains(pre)) {
                continue;
            }
            if (visiting.contains(pre) || hasCycle(pre, graph, visiting, visited)) {
                return true;
            }
        }

        visiting.remove(course);
        visited.add(course);
        return false;
    }
}

class Solution2 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) {
            throw new IllegalArgumentException("Invalid number of courses");
        }

        if (numCourses <= 1 || prerequisites == null || prerequisites.length == 0) {
            return true;
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
            if (hasCycle(course, graph, visiting, visited, resIdx)) {
                return false;
            }
        }

        return resIdx[0] == numCourses;
    }

    public boolean hasCycle(int course, Map<Integer, Set<Integer>> graph, Set<Integer> visiting, Set<Integer> visited,
            int[] resIdx) {
        if (visiting.contains(course)) {
            return true;
        }
        if (visited.contains(course)) {
            return false;
        }

        visiting.add(course);

        if (graph.containsKey(course)) {
            for (int n : graph.get(course)) {
                if (hasCycle(n, graph, visiting, visited, resIdx)) {
                    return true;
                }
            }
        }

        visiting.remove(course);
        visited.add(course);
        resIdx[0]++;

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
 * In worst case P = N * (N - 1) / 2 = N C 2. Thus worst case complexity: O(N^2)
 *
 * N = Number of Courses or Number of vertices. P = Number of prerequisites or
 * Number of edges.
 */
class Solution3 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (numCourses <= 1 || prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        Map<Integer, Set<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new HashSet<>());
        }
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
            inDegree[p[0]]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        int coursesCompleted = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();
            coursesCompleted++;
            for (int c : graph.get(course)) {
                if (--inDegree[c] == 0) {
                    queue.offer(c);
                }
            }
        }

        return coursesCompleted == numCourses;
    }
}
