// LeetCode Question URL: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/search-in-rotated-sorted-array-ii/discuss/1529305/Java-or-TC:-O(N2)-or-SC:-O(1)-or-Modified-Binary-Search-optimal-solution

/**
 * Modified binary search. This solution can handle duplicate values in input
 * array.
 *
 * <pre>
 * Time Complexity:
 * Worst Case: O(N/2). If all nums are same and target is not equal to mid.
 * This will reduce to log(N) if there are no duplicates
 * </pre>
 *
 * Space Complexity: O(1)
 *
 * N = length of input array.
 */
class Solution1 {
    public boolean search(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                return true;
            }

            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                while (start < end && nums[start] == nums[end]) {
                    start++;
                    end--;
                }
            }

            if (nums[start] <= nums[mid]) {
                // Left side is sorted. Right side is unsorted.
                if (nums[start] <= target && target < nums[mid]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // Left side is unsorted. Right side is sorted.
                if (nums[mid] < target && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }
}

/**
 * Modified binary search. This solution can handle duplicate values in input
 * array.
 *
 * <pre>
 * Time Complexity:
 * Worst Case: O(N/2). If all nums are same and target is not equal to mid.
 * This will reduce to log(N) in there are no duplicates
 * </pre>
 *
 * Space Complexity: O(1)
 *
 * N = length of input array.
 */
class Solution2 {
    public boolean search(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int start = 0;
        int end = nums.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target == nums[mid]) {
                return true;
            }

            if (nums[start] == nums[mid] && nums[mid] == nums[end]) {
                start++;
                end--;
            } else if (nums[start] <= nums[mid]) {
                // Left side is sorted. Right side is unsorted.
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                // Left side is unsorted. Right side is sorted.
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }

        return false;
    }
}
