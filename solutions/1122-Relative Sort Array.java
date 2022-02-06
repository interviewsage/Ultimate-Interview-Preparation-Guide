// LeetCode Question URL: https://leetcode.com/problems/relative-sort-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using CountMap and TreeSet
 *
 * <pre>
 * Time Complexity:
 * 1. Populate countMap = O(N1)
 * 2. Iterate over arr2 and populate arr1 = O(N2 + N1')
 * 3. Sort unique remaining and add them to arr1 = O(N1-N1' + (N1-N1')*log(N1-N1'))
 * Total time complexity = O(N1*2 + N2 + (N1-N1')*log(N1-N1')))
 *
 * Space Complexity: O(N1)
 * </pre>
 *
 * N1 = Length of arr1. N2 = Length of arr2. N1' = Number of elements in arr1
 * that are not in arr2.
 */
class Solution1 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length <= 1) {
            return arr1;
        }
        if (arr2 == null || arr2.length == 0) {
            Arrays.sort(arr1);
            return arr1;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int a : arr1) {
            countMap.put(a, countMap.getOrDefault(a, 0) + 1);
        }

        int idx = 0;
        for (int a : arr2) {
            Integer count = countMap.remove(a);
            if (count == null) {
                continue;
            }
            while (count-- > 0) {
                arr1[idx++] = a;
            }
        }

        // if (countMap.size() == 0) {
        // return arr1;
        // }

        Set<Integer> sortedSet = new TreeSet<>(countMap.keySet());
        for (int a : sortedSet) {
            int count = countMap.get(a);
            while (count-- > 0) {
                arr1[idx++] = a;
            }
        }

        return arr1;
    }
}

/**
 * Using Index Map and Custom Sort
 *
 * <pre>
 * Time Complexity:
 * 1. Populate idxMap = O(N2)
 * 2. Custom Sort = O(N1 * log(N1))
 * 3. Convert int to Integer and back to int = O(2 * N1)
 * Total time complexity = O(N1*2 + N2 + N1*log(N1)))
 *
 * Space Complexity: O(N2 + N1 + Space required for sorting)
 * </pre>
 *
 * N1 = Length of arr1. N2 = Length of arr2.
 */
class Solution2 {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr1.length <= 1) {
            return arr1;
        }
        if (arr2 == null || arr2.length == 0) {
            Arrays.sort(arr1);
            return arr1;
        }

        Map<Integer, Integer> idxMap = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            idxMap.put(arr2[i], i);
        }

        int len1 = arr1.length;
        Integer[] tmpArr1 = new Integer[len1];
        for (int i = 0; i < len1; i++) {
            tmpArr1[i] = arr1[i];
        }

        Arrays.sort(tmpArr1, (a, b) -> {
            Integer idx1 = idxMap.get(a);
            Integer idx2 = idxMap.get(b);

            if (idx1 == null && idx2 == null) {
                return Integer.compare(a, b);
            }
            return idx2 == null ? -1 : (idx1 == null ? 1 : Integer.compare(idx1, idx2));
            // if (idx2 == null) {
            // return -1;
            // }
            // if (idx1 == null) {
            // return 1;
            // }
            // return Integer.compare(idx1, idx2);
        });

        for (int i = 0; i < len1; i++) {
            arr1[i] = tmpArr1[i];
        }

        return arr1;
    }
}
