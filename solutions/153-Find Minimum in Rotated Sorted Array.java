// LeetCode Question URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
// LeetCode Discuss URL: https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/discuss/1529313/Java-or-TC:-O(logN)-or-SC:-O(1)-or-Optimal-Binary-Search-with-Early-Exit

/**
 * Binary Search
 *
 * Refer:
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/discuss/48493/Compact-and-clean-C++-solution
 *
 * Looking at sub-array with index [start,end]. If the first member is less than
 * the last member, there's no rotation in the array. So we could directly
 * return the first element in this sub-array.
 *
 * If the first element is larger than the last one, then we compute the element
 * in the middle, and compare it with the first element. If value of the element
 * in the middle is less that the last element, we know the rotation is in the
 * first half of this array. Else, its in the second half of the array.
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 1 || nums[0] < nums[len - 1]) {
            return nums[0];
        }
        if (len == 2) {
            return Math.min(nums[0], nums[1]);
        }

        int start = 0;
        int end = len - 1;

        while (start < end) {
            if (nums[start] < nums[end]) {
                return nums[start];
            }

            int mid = start + (end - start) / 2;

            // This is better check
            if (nums[mid] <= nums[end]) {
                end = mid;
            } else {
                start = mid + 1;
            }

            // Above check is better
            // if (nums[start] <= nums[mid]) {
            // start = mid+1;
            // } else {
            // end = mid;
            // }
        }

        return nums[start];
    }
}
