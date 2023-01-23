// LeetCode Question URL: https://leetcode.com/problems/employee-free-time/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using TreeMap
 *
 * Time Complexity: O(4N*log(2N) + 2N) = O(N * log N)
 *
 * Space Complexity: O(4*N) = O(N)
 */
class Solution1 {
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || capacity < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        if (trips.length == 0) {
            return true;
        }

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int[] t : trips) {
            map.put(t[1], map.getOrDefault(t[1], 0) + t[0]);
            map.put(t[2], map.getOrDefault(t[2], 0) - t[0]);
        }

        for (int p : map.values()) {
            if (capacity < p) {
                return false;
            }
            capacity -= p;
        }

        return true;
    }
}

/**
 * Using HashMap with max and min range
 *
 * Time Complexity: O(4*N + range(min, max)) = O(N + range(min, max))
 *
 * Space Complexity: O(4*N) = O(N)
 */
class Solution2 {
    public boolean carPooling(int[][] trips, int capacity) {
        if (trips == null || capacity < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        if (trips.length == 0) {
            return true;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int minDist = Integer.MAX_VALUE;
        int maxDist = 0;
        for (int[] t : trips) {
            map.put(t[1], map.getOrDefault(t[1], 0) + t[0]);
            map.put(t[2], map.getOrDefault(t[2], 0) - t[0]);
            minDist = Math.min(minDist, t[1]);
            maxDist = Math.max(maxDist, t[1]);
        }

        for (int i = minDist; i <= maxDist; i++) {
            Integer p = map.get(i);
            if (p == null) {
                continue;
            }
            if (capacity < p) {
                return false;
            }
            capacity -= p;
        }

        return true;
    }
}
