// LeetCode Question URL: https://leetcode.com/problems/intersection-of-two-arrays/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using 2 HashSets
 *
 * Time Complexity: O(M + N + min(UniqueM, UniqueN))
 *
 * Space Complexity: O(min(UniqueM, UniqueN)) = O(min(M, N))
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution1 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("Input arrays are null");
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 || len2 == 0) {
            return new int[0];
        }

        if (len1 > len2) {
            return intersection(nums2, nums1);
        }

        Set<Integer> nums1Set = new HashSet<>();
        for (int n1 : nums1) {
            nums1Set.add(n1);
        }

        List<Integer> resultList = new ArrayList<>();
        for (int n2 : nums2) {
            if (nums1Set.remove(n2)) {
                resultList.add(n2);
            }
        }

        int[] result = new int[resultList.size()];
        int idx = 0;
        for (int n : resultList) {
            result[idx++] = n;
        }
        return result;
    }
}

/**
 * Sorting smallest array and Binary Search
 *
 * Time Complexity: O((M+N)*log(min(M,N) + min(UniqueM, UniqueN))
 *
 * Space Complexity: O(min(M, N) + min(UniqueM, uniqueN))
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution2 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("Input arrays are null");
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 || len2 == 0) {
            return new int[0];
        }

        if (len1 > len2) {
            return intersection(nums2, nums1);
        }

        Arrays.sort(nums1);
        Set<Integer> resultSet = new HashSet<>();
        for (int n2 : nums2) {
            if (!resultSet.contains(n2) & Arrays.binarySearch(nums1, n2) >= 0) {
                resultSet.add(n2);
            }
        }

        int[] result = new int[resultSet.size()];
        int idx = 0;
        for (int n : resultSet) {
            result[idx++] = n;
        }
        return result;
    }
}

/**
 * Sorting and then using 2 pointers
 *
 * Time Complexity: O(M*logM + N*logN + M+N + min(UniqueM, UniqueN))
 *
 * Space Complexity: O(M + N + min(UniqueM, uniqueN))
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution3 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("Input arrays are null");
        }

        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 == 0 || len2 == 0) {
            return new int[0];
        }

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        Set<Integer> resultSet = new HashSet<>();
        int i = 0;
        int j = 0;

        while (i < len1 && j < len2) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                resultSet.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] result = new int[resultSet.size()];
        int idx = 0;
        for (int n : resultSet) {
            result[idx++] = n;
        }
        return result;
    }
}
