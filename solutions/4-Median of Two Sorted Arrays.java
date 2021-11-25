// LeetCode Question URL: https://leetcode.com/problems/median-of-two-sorted-arrays/
// LeetCode Discuss URL:

/**
 * Binary Search
 *
 * Refer:
 * https://leetcode.com/problems/median-of-two-sorted-arrays/discuss/2481/Share-my-O(log(min(mn))-solution-with-explanation
 *
 * Time Complexity: O(log(min(M, N)))
 *
 * Space Complexity: O(1)
 *
 * M = Length of nums1. N = Length of nums2.
 */
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            throw new IllegalArgumentException("Input arrays are null");
        }

        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 == 0 && len2 == 0) {
            throw new IllegalArgumentException("Both Input arrays are empty");
        }
        // Below 2 if conditions are not required as the binary search it self will find
        // the result in O(1) if either length is zero.
        // if (len1 == 0) {
        // return findMedian(nums2);
        // }
        // if (len2 == 0) {
        // return findMedian(nums1);
        // }

        if (len1 > len2) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int start = 0;
        int end = len1;
        while (start <= end) {
            int i = start + (end - start) / 2;
            int j = (len1 + len2 + 1) / 2 - i;

            if (i > 0 && j < len2 && nums1[i - 1] > nums2[j]) {
                end = i - 1;
            } else if (i < len1 && j > 0 && nums2[j - 1] > nums1[i]) {
                start = i + 1;
            } else {
                int maxLeft = Integer.MIN_VALUE;
                if (i > 0) {
                    maxLeft = Math.max(maxLeft, nums1[i - 1]);
                }
                if (j > 0) {
                    maxLeft = Math.max(maxLeft, nums2[j - 1]);
                }

                if ((len1 + len2) % 2 == 1) {
                    return maxLeft;
                }

                int minRight = Integer.MAX_VALUE;
                if (i < len1) {
                    minRight = Math.min(minRight, nums1[i]);
                }
                if (j < len2) {
                    minRight = Math.min(minRight, nums2[j]);
                }

                return (maxLeft + minRight) / 2.0;
            }
        }

        throw new IllegalArgumentException("Median not found. Invalid Input");
    }

    // public static double findMedian(int[] nums) {
    // int len = nums.length;
    // if (len % 2 == 1) {
    // return nums[len / 2];
    // } else {
    // return (nums[len / 2] + nums[(len-1) / 2]) / 2.0;
    // }
    // }
}