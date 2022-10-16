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
class Solution1 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        if (arr1 == null || arr2 == null || arr3 == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            int n1 = arr1[i];
            int n2 = arr2[j];
            int n3 = arr3[k];

            if (n1 == n2 && n2 == n3) {
                result.add(n1);
                i++;
                j++;
                k++;
                continue;
            }

            if (n1 < n2 || n1 < n3) {
                i++;
            }
            if (n2 < n1 || n2 < n3) {
                j++;
            }
            if (n3 < n1 || n3 < n2) {
                k++;
            }
        }

        return result;
    }
}

class Solution2 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        if (arr1 == null || arr2 == null || arr3 == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<Integer> result = new ArrayList<>();

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < arr1.length && j < arr2.length && k < arr3.length) {
            if (arr1[i] == arr2[j]) {
                if (arr2[j] == arr3[k]) {
                    result.add(arr1[i]);
                    i++;
                    j++;
                    k++;
                } else {
                    if (arr1[i] < arr3[k]) {
                        i++;
                        j++;
                    } else {
                        k++;
                    }
                }
            } else if (arr1[i] < arr2[j]) {
                i++;
                if (arr2[j] < arr3[k]) {
                    j++;
                } else if (arr2[j] > arr3[k]) {
                    k++;
                }
            } else {
                if (arr2[j] >= arr3[k]) {
                    k++;
                    j++;
                } else {
                    j++;
                    if (arr1[i] < arr3[k]) {
                        i++;
                    } else if (arr1[i] > arr3[k]) {
                        k++;
                    }
                }
            }
        }

        return result;
    }
}

class Solution3 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        if (arr1 == null || arr2 == null || arr3 == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<Integer> result = new ArrayList<>();

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

class Solution4 {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        if (arr1 == null || arr2 == null || arr3 == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<Integer> result = new ArrayList<>();

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
