// LeetCode Question URL: https://leetcode.com/problems/find-peak-element/
// LeetCode Discuss URL:

/**
 * Solution of this question is same as 852-Peak Index in a Mountain Array
 * https://leetcode.com/problems/peak-index-in-a-mountain-array/
 */

/**
 * Binary Search (Iterative)
 *
 * Check Page 5
 * (https://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf) for
 * definition of the peak.
 *
 * A[i-1] <= A[i] >= A[i+1]
 *
 * Refer: 1) https://leetcode.com/problems/find-peak-element/solution/ 2)
 * https://leetcode.com/problems/find-peak-element/discuss/50232/Find-the-maximum-by-binary-search-(recursion-and-iteration)
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array is invalid");
        }

        int start = 0;
        int end = nums.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] < nums[mid + 1]) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return start;
    }
}

/**
 * Binary Search (Recursive)
 *
 * Check Page 5
 * (https://courses.csail.mit.edu/6.006/spring11/lectures/lec02.pdf) for
 * definition of the peak.
 *
 * A[i-1] <= A[i] >= A[i+1]
 *
 * Refer: 1) https://leetcode.com/problems/find-peak-element/solution/ 2)
 * https://leetcode.com/problems/find-peak-element/discuss/50232/Find-the-maximum-by-binary-search-(recursion-and-iteration)
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(log N). Recursion stack space.
 *
 * N = Length of the input array.
 */
class Solution2 {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input array is invalid");
        }

        return searchHelper(nums, 0, nums.length - 1);
    }

    private int searchHelper(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }

        int mid = start + (end - start) / 2;

        if (nums[mid] < nums[mid + 1]) {
            return searchHelper(nums, mid + 1, end);
        } else {
            return searchHelper(nums, start, mid);
        }
    }
}
