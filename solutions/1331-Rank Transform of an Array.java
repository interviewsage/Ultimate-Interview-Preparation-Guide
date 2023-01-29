// LeetCode Question URL: https://leetcode.com/problems/rank-transform-of-an-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Alternate Solutions:
 * 1. https://leetcode.com/problems/rank-transform-of-an-array/discuss/489753/JavaC++Python-HashMap
 * 2. https://leetcode.com/problems/rank-transform-of-an-array/discuss/490843/Simple-Java-Solution
 */

/**
 * IndexMap + TreeSet
 *
 * <pre>
 * Time Complexity:
 * 1. build indexMap: O(N)
 * 2. create TreeSet: O(U * log(U))
 * 3. Populate ranks: O(N)
 *
 * Total time complexity = O(2*N +U*log(U))
 *
 * Space Complexity:
 * 1. indexMap = O(U + N)
 * 2. sortedUniqueNums = O(U)
 *
 * Total space complexity = O(2*U + N)
 *
 *  N = Number of elements in input array. U = Number of unique elements. U = O(N)
 * </pre>
 */
class Solution1 {
    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int len = arr.length;
        if (len == 1) {
            arr[0] = 1;
            return arr;
        }

        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            indexMap.putIfAbsent(arr[i], new ArrayList<>());
            indexMap.get(arr[i]).add(i);
        }

        Set<Integer> sortedUniqueNums = new TreeSet<>(indexMap.keySet());
        int rank = 1;
        for (int n : sortedUniqueNums) {
            for (int idx : indexMap.get(n)) {
                arr[idx] = rank;
            }
            rank++;
        }

        return arr;
    }
}

/**
 * IndexMap + TreeSet
 *
 * <pre>
 * Time Complexity:
 * 1. build indexMap: O(N)
 * 2. create TreeSet: O(U * log(U))
 * 3. Populate ranks: O(N)
 *
 * Total time complexity = O(2*N +U*log(U))
 *
 * Space Complexity:
 * 1. indexMap = O(U + N)
 * 2. sortedUniqueNums = O(U)
 *
 * Total space complexity = O(2*U + N)
 *
 *  N = Number of elements in input array. U = Number of unique elements. U = O(N)
 * </pre>
 */
class Solution2 {
    public int[] arrayRankTransform(int[] arr) {
        if (arr == null || arr.length == 0) {
            return arr;
        }

        int len = arr.length;
        if (len == 1) {
            arr[0] = 1;
            return arr;
        }

        Map<Integer, List<Integer>> indexMap = new HashMap<>();
        int maxVal = arr[0];
        int minVal = arr[0];
        for (int i = 0; i < len; i++) {
            indexMap.putIfAbsent(arr[i], new ArrayList<>());
            indexMap.get(arr[i]).add(i);

            maxVal = Math.max(maxVal, arr[i]);
            minVal = Math.min(minVal, arr[i]);
        }

        int rank = 1;
        for (int i = minVal; i <= maxVal; i++) {
            List<Integer> indexList = indexMap.get(i);
            if (indexList == null) {
                continue;
            }

            for (int idx : indexList) {
                arr[idx] = rank;
            }
            rank++;
        }

        return arr;
    }
}
