// LeetCode Question URL: https://leetcode.com/problems/bus-routes/
// LeetCode Discuss URL:

import java.util.*;

/**
 * BFS
 *
 * Build Graph --> Stop to Each Stop where you can reach by just 1 bus
 *
 * <pre>
 * Time Complexity:
 * 1. Build Graph = R * S
 * 2. BFS = O(V + E)
 *        --> V = S'
 *        --> E = R * S
 * Thus, Total time complexity: O(R*S + S' + R*S) = O(R*S)
 *
 * Space Complexity:
 * 1. Graph --> S' * R
 * 2. Queue --> S'
 * 3. visitedRoutes --> R
 * 4. visitedStops --> S'
 * Thus, Total time complexity: O(R*S' + S' + R + S') = O(R*S)
 *
 * R = Number of routes. S = Average number of stops in a route. S' = Number of
 * unique stops.
 * </pre>
 */
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (routes == null) {
            throw new IllegalArgumentException("Input routes array is null");
        }
        if (source == target) {
            return 0;
        }
        int numRoutes = routes.length;
        if (routes.length == 0) {
            return -1;
        }

        // Build Graph
        Map<Integer, Set<Integer>> stopToBusesMap = new HashMap<>();
        for (int i = 0; i < numRoutes; i++) {
            for (int s : routes[i]) {
                stopToBusesMap.putIfAbsent(s, new HashSet<>());
                stopToBusesMap.get(s).add(i);
            }
        }

        if (!stopToBusesMap.containsKey(source) || !stopToBusesMap.containsKey(target)) {
            return -1;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        Set<Integer> visitedRoutes = new HashSet<>();
        Set<Integer> visitedStops = new HashSet<>();
        queue.offer(source);
        visitedStops.add(source);
        int buses = 0;

        // BFS -> each route is visited once. each stop is visited once.
        while (!queue.isEmpty()) {
            int size = queue.size();
            buses++;
            for (int i = 0; i < size; i++) {
                int curStop = queue.poll();
                for (int bus : stopToBusesMap.get(curStop)) {
                    if (!visitedRoutes.add(bus)) {
                        continue;
                    }
                    for (int stop : routes[bus]) {
                        if (stop == target) {
                            return buses;
                        }
                        if (visitedStops.add(stop)) {
                            queue.offer(stop);
                        }
                    }
                }
            }
        }

        return -1;
    }
}
