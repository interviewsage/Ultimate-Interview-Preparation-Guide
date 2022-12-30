// LeetCode Question URL: https://leetcode.com/problems/single-element-in-a-sorted-array/
// LeetCode Discuss URL:

/**
 * Binary Search
 *
 * Consider a1, a1, a2, a2, a3, a4, a4, a5, a5. Since the input array is sorted,
 * single element will always be on the even index. On the left of single
 * element, the pairs will be (even, odd). On the right of single element, the
 * pairs will be (odd, even).
 *
 * Time Complexity: O(log(N/2)) = O(log N)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int singleNonDuplicate(int[] nums) {
        if (nums == null || (nums.length & 1) == 0) {
            throw new IllegalArgumentException("Input nums array is invalid");
        }

        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        // We are only considering even indexes. So, start and end will always point to
        // even indexes.
        int start = 0;
        int end = len - 1;
        while (start < end) {
            int mid = start + (end - start) / 2;

            // incrementing by 1 here would not work, it'd lead to an infinite loop as the
            // search space would not be reduced in some cases.
            // Also, it can also lead to Array Out of Bounds Exception.
            if ((mid & 1) == 1) {
                mid--;
            }

            if (nums[mid] == nums[mid + 1]) {
                // Found a pair. The single element must be on the right.
                start = mid + 2;
            } else {
                // Did not find a pair. The single element must be either mid or on the left.
                end = mid;
            }
        }

        return nums[start];
    }
}
