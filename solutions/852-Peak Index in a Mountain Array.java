// LeetCode Question URL: https://leetcode.com/problems/peak-index-in-a-mountain-array/
// LeetCode Discuss URL:

/**
 * Solution of this question is same as 162-Find Peak Element
 * https://leetcode.com/problems/find-peak-element/
 */

/**
 * Binary Search (Iterative)
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/peak-index-in-a-mountain-array/solution/
 * 2) https://leetcode.com/problems/peak-index-in-a-mountain-array/discuss/139848/C++JavaPython-Better-than-Binary-Search
 * </pre>
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int peakIndexInMountainArray(int[] arr) {
        if (arr == null || arr.length < 3) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int start = 0;
        int end = arr.length - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] < arr[mid + 1]) {
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
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(log N)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public int peakIndexInMountainArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        return searchHelper(nums, 0, nums.length - 1);
    }

    private int searchHelper(int[] nums, int start, int end) {
        if (start == end) {
            return start;
        }

        int mid = start + (end - start) / 2;

        if (nums[mid] > nums[mid + 1]) {
            return searchHelper(nums, start, mid);
        } else {
            return searchHelper(nums, mid + 1, end);
        }
    }
}
