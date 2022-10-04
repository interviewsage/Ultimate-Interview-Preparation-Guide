// LeetCode Question URL: https://leetcode.com/problems/merge-sorted-array/
// LeetCode Discuss URL: https://leetcode.com/problems/merge-sorted-array/discuss/1555774/Java-TC:-O(M+N)-or-SC:-O(1)-or-Simple-and-Concise-One-Pass-Three-Pointer-Solution

/**
 * One Pass, Three Pointer Solution. Fill the numbers from right-side.
 *
 * Time Complexity: O(M + N)
 *
 * Space Complexity: O(1)
 *
 * M = nums1 length. N = nums2 length.
 */
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null || m < 0 || n < 0 || nums1.length < m + n || nums2.length < n) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n == 0) {
            return;
        }

        m--;
        n--;
        while (m >= 0 && n >= 0) {
            if (nums1[m] >= nums2[n]) {
                nums1[m + n + 1] = nums1[m--];
            } else {
                nums1[m + n + 1] = nums2[n--];
            }
        }
        while (n >= 0) {
            nums1[n] = nums2[n--];
        }
    }
}
