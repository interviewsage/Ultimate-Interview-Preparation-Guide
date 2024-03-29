// LeetCode Question URL: https://leetcode.com/problems/brick-wall/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Visit each line and record the count of cumulative length of each edge.
 *
 * Time Complexity: O(Number of Bricks)
 *
 * Space Complexity: O(min(Number of Bricks, Width of the wall))
 */
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        if (wall == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int rows = wall.size();
        if (rows == 0) {
            return 0;
        }
        if (rows == 1) {
            return wall.get(0).size() == 1 ? 1 : 0;
        }

        // This map stores the count of all cumulative lengths
        // Count of each end location
        Map<Integer, Integer> map = new HashMap<>();
        int maxEdges = 0;

        for (List<Integer> row : wall) {
            int prefixSum = 0;
            /**
             * Skipping the last edge of each line as every line will end there and its not
             * a valid answer.
             */
            for (int i = 0; i < row.size() - 1; i++) {
                prefixSum += row.get(i);
                int count = map.getOrDefault(prefixSum, 0) + 1;
                map.put(prefixSum, count);
                maxEdges = Math.max(maxEdges, count);
            }
        }

        return rows - maxEdges;
    }
}
