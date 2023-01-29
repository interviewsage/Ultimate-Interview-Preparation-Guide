// LeetCode Question URL: https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/
// LeetCode Discuss URL:

/**
 * Refer for alternate solution:
 * https://leetcode.com/problems/element-appearing-more-than-25-in-sorted-array/discuss/451249/Simple-Java-Solution-O(n)-time-O(1)-space
 */

/**
 * Binary Search
 *
 * Time Complexity: O(2 * log N) = O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int findSpecialInteger(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = arr.length;
        if (len <= 3) {
            return arr[len == 3 ? 1 : 0];
        }
        if (isSpecialInteger(arr, arr[len / 4])) {
            return arr[len / 4];
        }
        if (isSpecialInteger(arr, arr[len / 2])) {
            return arr[len / 2];
        }
        return arr[3 * len / 4];

        // return arr[len <= 2 ? 0
        // : (len == 3 ? 1
        // : (isSpecialInteger(arr, arr[len / 4]) ? len / 4
        // : (isSpecialInteger(arr, arr[len / 2]) ? len / 2 : 3 * len / 4)))];
    }

    private boolean isSpecialInteger(int[] arr, int target) {
        int len = arr.length;
        int start = 0;
        int end = len - 1;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == target) {
                end = mid;
            } else if (arr[mid] > target) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }

            // if (arr[mid] < target) {
            // start = mid + 1;
            // } else {
            // end = mid - 1;
            // }
        }

        return arr[start] == arr[start + len / 4];
    }
}
