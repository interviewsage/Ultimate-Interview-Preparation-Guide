// LeetCode Question URL: https://leetcode.com/problems/unique-number-of-occurrences/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N + U)
 *
 * Space Complexity: O(2U + U) = O(U)
 *
 * N = Number of elements in arr. U = Number of unique numbers in arr.
 */
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input is null");
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int n : arr) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }

        Set<Integer> set = new HashSet<>();
        for (int v : map.values()) {
            if (!set.add(v)) {
                return false;
            }
        }
        return true;
    }
}
