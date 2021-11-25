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
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }

        HashSet<Integer> set = new HashSet<>();
        for (int n : nums1) {
            set.add(n);
        }

        HashSet<Integer> resultSet = new HashSet<>();
        for (int n : nums2) {
            if (set.remove(n)) {
                resultSet.add(n);
            }
        }

        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int n : resultSet) {
            result[i++] = n;
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
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }

        Arrays.sort(nums1);

        HashSet<Integer> set = new HashSet<>();

        for (int n : nums2) {
            if (!set.contains(n) && Arrays.binarySearch(nums1, n) >= 0) {
                set.add(n);
            }
        }

        int[] result = new int[set.size()];
        int i = 0;
        for (int n : set) {
            result[i++] = n;
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
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return new int[0];
        }

        HashSet<Integer> set = new HashSet<>();

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0;
        int j = 0;

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] result = new int[set.size()];
        i = 0;
        for (int n : set) {
            result[i++] = n;
        }

        return result;
    }
}
