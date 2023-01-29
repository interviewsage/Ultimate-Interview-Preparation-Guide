// LeetCode Question URL: https://leetcode.com/problems/maximum-population-year/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using HashMap with max and min range
 *
 * Time Complexity: O(N + range(min, max))
 *
 * Space Complexity: O(4*N) = O(N)
 *
 * N = Number of logs. range(min, max) = Range of years in the logs.
 */
class Solution1 {
    public int maximumPopulation(int[][] logs) {
        if (logs == null || logs.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = logs.length;
        if (len == 1) {
            return logs[0][0];
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;
        for (int[] l : logs) {
            countMap.put(l[0], countMap.getOrDefault(l[0], 0) + 1);
            countMap.put(l[1], countMap.getOrDefault(l[1], 0) - 1);
            minYear = Math.min(minYear, l[0]);
            maxYear = Math.max(maxYear, l[1]);
        }

        int maxPop = 0;
        int maxPopYear = minYear;
        int curPop = 0;
        for (int i = minYear; i <= maxYear; i++) {
            curPop += countMap.getOrDefault(i, 0);
            if (curPop > maxPop) {
                maxPop = curPop;
                maxPopYear = i;
            }
        }

        return maxPopYear;
    }
}

/**
 * Using HashMap with TreeSet
 *
 * Time Complexity: O(N + U*log(U) + U)
 *
 * Space Complexity: O(4*N + U)
 *
 * N = Number of logs. U = Unique number of years in the logs.
 */
class Solution2 {
    public int maximumPopulation(int[][] logs) {
        if (logs == null || logs.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = logs.length;
        if (len == 1) {
            return logs[0][0];
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int[] l : logs) {
            countMap.put(l[0], countMap.getOrDefault(l[0], 0) + 1);
            countMap.put(l[1], countMap.getOrDefault(l[1], 0) - 1);
        }

        Set<Integer> sortedYears = new TreeSet<>(countMap.keySet());
        int maxPop = 0;
        int maxPopYear = 0;
        int curPop = 0;

        for (int y : sortedYears) {
            curPop += countMap.get(y);
            if (curPop > maxPop) {
                maxPop = curPop;
                maxPopYear = y;
            }
        }

        return maxPopYear;
    }
}
