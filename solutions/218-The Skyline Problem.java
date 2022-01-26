// LeetCode Question URL: https://leetcode.com/problems/the-skyline-problem/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sort and use tree map
 *
 * Time Complexity: List has 2*N elements. Thus sorting takes O(2N*log(2N)).
 * TreeMap will have maximum N elements. Each height is added once and removed
 * once. Thus add and remove operations in TreeMap take O(2N*log(N)). firstKey
 * function takes O(log(N)) time. Thus total time complexity is O(2N*log(2N)
 * 2N*log(N)) = O(N*logN)
 *
 * Space Complexity: O(2N + N) = O(N)
 *
 * N = Number of buildings.
 */
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        List<List<Integer>> result = new ArrayList<>();
        if (buildings == null || buildings.length == 0) {
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

        Collections.sort(heights, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));

        // Key = Height
        // Value = Count
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put(0, 1);
        int curMax = 0;

        for (int[] h : heights) {
            if (h[1] < 0) {
                map.put(-h[1], map.getOrDefault(-h[1], 0) + 1);
            } else {
                int count = map.get(h[1]);
                if (count == 1) {
                    map.remove(h[1]);
                } else {
                    map.put(h[1], count - 1);
                }
            }
            int newMax = map.firstKey();
            if (newMax != curMax) {
                result.add(List.of(h[0], newMax));
                curMax = newMax;
            }
        }

        return result;
    }
}
