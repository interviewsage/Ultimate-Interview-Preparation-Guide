// LeetCode Question URL: https://leetcode.com/problems/intersection-of-three-sorted-arrays/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using 3 Pointers
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/intersection-of-three-sorted-arrays/discuss/397603/Simple-Java-solution-beats-100
 * 2) https://leetcode.com/problems/intersection-of-three-sorted-arrays/discuss/397603/Simple-Java-solution-beats-100/357444
 * </pre>
 *
 * Time Complexity: O(M + N + O)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> result = new ArrayList<>();

        if (arr1 == null || arr2 == null || arr3 == null || arr1.length == 0 || arr2.length == 0 || arr3.length == 0) {
            return result;
        }

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                result.add(arr1[i]);
                i++;
                j++;
                k++;
            } else {
                if (arr1[i] < arr2[j]) {
                    i++;
                } else if (arr2[j] < arr3[k]) {
                    j++;
                } else {
                    k++;
                }
            }
        }

        return result;
    }
}

class Solution2 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> result = new ArrayList<>();

        if (arr1 == null || arr2 == null || arr3 == null || arr1.length == 0 || arr2.length == 0 || arr3.length == 0) {
            return result;
        }

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                result.add(arr1[i]);
                i++;
                j++;
                k++;
            } else if (arr1[i] <= arr2[j] && arr1[i] <= arr3[k]) {
                i++;
            } else if (arr2[j] <= arr1[i] && arr2[j] <= arr3[k]) {
                j++;
            } else {
                k++;
            }
        }

        return result;
    }
}
