// LeetCode Question URL: https://leetcode.com/problems/valid-mountain-array/
// LeetCode Discuss URL:

/**
 * Two people walking up the mountain from each side. They will continue walking
 * till they find a peak or flat land. If the peak found by them is same, we can
 * return true or else return false.
 *
 * Time Complexity:
 * - Best Case: O(N/2) - If slope of both sides is same.
 * - Worst Case: O(N) -- if slope of one side is just 1.
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public boolean validMountainArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = arr.length;
        if (len < 3 || arr[0] >= arr[1] || arr[len - 2] <= arr[len - 1]) {
            return false;
        }

        int left = 1;
        int right = len - 2;

        while (left < right && (arr[left] < arr[left + 1] || arr[right - 1] > arr[right])) {
            // early exit condition.
            if (arr[left] == arr[left + 1] || arr[right - 1] == arr[right]) {
                return false;
            }
            if (arr[left] < arr[left + 1]) {
                left++;
            }
            if (arr[right - 1] > arr[right]) {
                right--;
            }
        }

        return left == right;
    }
}

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public boolean validMountainArray(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = arr.length;
        if (len < 3 || arr[0] >= arr[1] || arr[len - 2] <= arr[len - 1]) {
            return false;
        }

        int idx = 2;
        while (idx < len - 1) {
            if (arr[idx - 1] == arr[idx]) {
                return false;
            }
            if (arr[idx - 1] > arr[idx]) {
                break;
            }
            idx++;
        }

        while (idx < len - 1) {
            if (arr[idx - 1] <= arr[idx]) {
                return false;
            }
            idx++;
        }

        return true;
    }
}
