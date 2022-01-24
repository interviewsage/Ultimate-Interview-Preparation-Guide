// LeetCode Question URL: https://leetcode.com/problems/alien-dictionary/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using DFS and topological sort
 *
 * Refer: https://leetcode.com/problems/alien-dictionary/solution/
 *
 * <pre>
 * Time Complexity:
 * 1. processWordsArray --> All characters are visited = O(N * L) = O(C)
 * 2. DFS --> O(V + E) = O(U + min(U^2, N-1))
 *
 * Space Complexity:
 * 1. Graph --> 2 * min(U^2, N-1) --> All edges in teh graph
 * 2. toBeVisited --> O(U)
 * 3. visited + visiting --> O(U)
 * 4. DFS Recursion Depth --> O(min(U, min(U^2, N-1))) = O(min(U, N-1))
 * </pre>
 */
class Solution1 {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> toBeVisited = new HashSet<>();
        Set<Character> nodesWithNonZeroInDegree = new HashSet<>();
        StringBuilder order = new StringBuilder();

        if (!processWordsArray(words, graph, toBeVisited, nodesWithNonZeroInDegree)) {
            return "";
        }

        if (words.length == 1) {
            for (char c : toBeVisited) {
                order.append(c);
            }
            return order.toString();
        }

        Set<Character> visited = new HashSet<>();
        Set<Character> visiting = new HashSet<>();

        int numUniqueNodes = toBeVisited.size();
        toBeVisited.removeAll(nodesWithNonZeroInDegree);

        for (char c : toBeVisited) {
            if (hasCycle(graph, visited, visiting, order, c)) {
                return "";
            }
        }

        return order.length() < numUniqueNodes ? "" : order.toString();
    }

    private boolean processWordsArray(String[] words, Map<Character, Set<Character>> graph, Set<Character> toBeVisited,
            Set<Character> nodesWithNonZeroInDegree) {
        for (int i = 0; i < words[0].length(); i++) {
            toBeVisited.add(words[0].charAt(i));
        }

        for (int i = 1; i < words.length; i++) {
            String w1 = words[i - 1];
            String w2 = words[i];
            int w1Len = w1.length();
            int w2Len = w2.length();
            int minLen = Math.min(w1Len, w2Len);
            int j = 0;

            while (j < minLen) {
                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);
                if (c1 != c2) {
                    graph.putIfAbsent(c2, new HashSet<>());
                    graph.get(c2).add(c1);
                    toBeVisited.add(c2);
                    nodesWithNonZeroInDegree.add(c1);
                    break;
                }
                j++;
            }

            if (j == minLen && w1Len > w2Len) {
                return false;
            }

            while (j < w2Len) {
                toBeVisited.add(w2.charAt(j++));
            }
        }

        return true;
    }

    private boolean hasCycle(Map<Character, Set<Character>> graph, Set<Character> visited, Set<Character> visiting,
            StringBuilder order, char c) {
        if (visiting.contains(c)) {
            return true;
        }
        if (visited.contains(c)) {
            return false;
        }

        visiting.add(c);

        if (graph.containsKey(c)) {
            for (char n : graph.get(c)) {
                if (hasCycle(graph, visited, visiting, order, n)) {
                    return true;
                }
            }
        }

        visiting.remove(c);
        visited.add(c);
        order.append(c);

        return false;
    }
}

/**
 * Using DFS and topological sort
 *
 * Time Complexity: O(Vertices + Edges). Here number of edges is N-1, where N is
 * the no of words in the input array. Number of vertices equal to the unique
 * number of characters in input array. Thus Time Complexity: O(N * L + (N-1)) =
 * O(N * L). Where L is the average length of each character. In case the
 * character range is a..z then maximum number of unique characters will be 26.
 * In this can the time Complexity will become O(N).
 *
 * Space Complexity: O(Vertices + Edges). Refer to the above explanation of time
 * complexity.
 */
class Solution2 {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }

        Map<Character, Set<Character>> graph = buildGraph(words);
        Set<Character> visited = new HashSet<>();
        Set<Character> visiting = new HashSet<>();
        StringBuilder order = new StringBuilder();

        for (char c : graph.keySet()) {
            if (!visited.contains(c) && hasCycle(graph, c, visited, visiting, order)) {
                return "";
            }
        }

        return order.toString();
    }

    private Map<Character, Set<Character>> buildGraph(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        for (String s : words) {
            for (char c : s.toCharArray()) {
                graph.put(c, new HashSet<>());
            }
        }

        for (int i = 1; i < words.length; i++) {
            char[] w1 = words[i - 1].toCharArray();
            char[] w2 = words[i].toCharArray();

            for (int j = 0; j < Math.min(w1.length, w2.length); j++) {
                if (w1[j] != w2[j]) {
                    graph.get(w2[j]).add(w1[j]);
                    break;
                }
            }
        }

        return graph;
    }

    private boolean hasCycle(Map<Character, Set<Character>> graph, char c, Set<Character> visited,
            Set<Character> visiting, StringBuilder order) {

        if (visited.contains(c)) {
            return false;
        }
        if (visiting.contains(c)) {
            return true;
        }

        visiting.add(c);

        for (char n : graph.get(c)) {
            if (hasCycle(graph, n, visited, visiting, order)) {
                return true;
            }
        }

        visiting.remove(c);
        visited.add(c);
        order.append(c);
        return false;
    }
}