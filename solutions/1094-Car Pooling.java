// LeetCode Question URL: https://leetcode.com/problems/employee-free-time/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using TreeMap
 *
 * Time Complexity: O(2N * log(2N) + 2N)
 *
 * Space Complexity: O(4*N) = O(N)
 */
class Solution1 {
    public boolean carPooling(int[][] trips, int capacity) {
        if (capacity < 0 || trips == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        if (trips.length == 0) {
            return true;
        }

        Map<Integer, Integer> map = new TreeMap<>();
        for (int[] t : trips) {
            map.put(t[1], map.getOrDefault(t[1], 0) + t[0]);
            map.put(t[2], map.getOrDefault(t[2], 0) - t[0]);
        }

        for (int val : map.values()) {
            capacity -= val;
            if (capacity < 0) {
                return false;
            }
        }

        return true;
    }
}

/**
 * Using HashMap with max and min range
 *
 * Time Complexity: O(2N + range(min, max))
 *
 * Space Complexity: O(4*N) = O(N)
 */
class Solution2 {
    public boolean carPooling(int[][] trips, int capacity) {
        if (capacity < 0 || trips == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        if (trips.length == 0) {
            return true;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int[] t : trips) {
            map.put(t[1], map.getOrDefault(t[1], 0) + t[0]);
            map.put(t[2], map.getOrDefault(t[2], 0) - t[0]);
            min = Math.min(min, t[1]);
            max = Math.max(max, t[2]);
        }

        for (int i = min; i <= max; i++) {
            Integer val = map.get(i);
            if (val == null) {
                continue;
            }
            capacity -= val;
            if (capacity < 0) {
                return false;
            }
        }

        return true;
    }
}
