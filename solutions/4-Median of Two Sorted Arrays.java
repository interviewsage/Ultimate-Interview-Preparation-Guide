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
        if (nums1 == null || nums2 == null || (nums1.length == 0 && nums2.length == 0)) {
            throw new IllegalArgumentException("Input arrays are null or empty");
        }

        int l1 = nums1.length;
        int l2 = nums2.length;
        if (l1 > l2) {
            return findMedianSortedArrays(nums2, nums1);
        }
        // Below if conditions is not required as the binary search it self will find
        // the result in O(1) if either length is zero.
        // if (l1 == 0) {
        // return findMedian(nums2);
        // }

        int start = 0;
        int end = l1;

        while (start <= end) {
            int i = start + (end - start) / 2;
            int j = (l1 + l2 + 1) / 2 - i;

            if (i > 0 && j < l2 && nums1[i - 1] > nums2[j]) {
                end = i - 1;
            } else if (i < l1 && j > 0 && nums2[j - 1] > nums1[i]) {
                start = i + 1;
            } else {
                int maxLeft;
                if (i == 0) {
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }

                if ((l1 + l2) % 2 != 0) {
                    return maxLeft;
                }

                int minRight;
                if (i == l1) {
                    minRight = nums2[j];
                } else if (j == l2) {
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums1[i], nums2[j]);
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
