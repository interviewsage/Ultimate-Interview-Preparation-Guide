// LeetCode Question URL: https://leetcode.com/problems/the-skyline-problem/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sort and use tree map
 *
 * The basic idea of this solutions is to find the critical points that change
 * the max height among the buildings on the left
 *
 * Time Complexity: List has 2*N elements. Thus sorting takes O(2N*log(2N)).
 * TreeMap will have maximum N elements. Each height is added once and removed
 * once. Thus add and remove operations in TreeMap take O(2N*log(N)). firstKey
 * function takes O(2N * log(N)) time.
 * Thus total time complexity is O(N + 2N*log(2N) + 2*2N*log(N)) = O(N*logN)
 * = Create Height array + Sort + TreeMap Operations (add + remove + firstKey)
 *
 * Space Complexity: O(2N + 2N + N) = O(N)
 * = Heights Array + Sorting + TreeMap
 *
 * N = Number of buildings.
 */
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        if (buildings == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        int numBuildings = buildings.length;
        if (numBuildings == 0) {
            return result;
        }
        if (numBuildings == 1) {
            result.add(List.of(buildings[0][0], buildings[0][2]));
            result.add(List.of(buildings[0][1], 0));
            return result;
        }

        List<int[]> heights = new ArrayList<>();
        for (int[] b : buildings) {
            if (b[2] == 0) {
                continue;
            }
            heights.add(new int[] { b[0], -b[2] });
            heights.add(new int[] { b[1], b[2] });
        }

        // Here keeping list sorted from left to right.
        // At any point, we first process Starting buildings from tallest to shortest
        // and then process Ending buildings from shortest to tallest.
        Collections.sort(heights, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));

        TreeMap<Integer, Integer> heightMap = new TreeMap<>(Collections.reverseOrder());
        heightMap.put(0, 1);
        int curMax = 0;

        for (int[] h : heights) {
            if (h[1] < 0) {
                heightMap.put(-h[1], heightMap.getOrDefault(-h[1], 0) + 1);
            } else {
                int count = heightMap.get(h[1]);
                if (count == 1) {
                    heightMap.remove(h[1]);
                } else {
                    heightMap.put(h[1], count - 1);
                }
            }

            // Highest height of the buildings in our current view.
            int newMax = heightMap.firstKey();
            // Only adding the point in result if the height in our current view changes.
            if (newMax != curMax) {
                result.add(List.of(h[0], newMax));
                curMax = newMax;
            }
        }

        return result;
    }
}
