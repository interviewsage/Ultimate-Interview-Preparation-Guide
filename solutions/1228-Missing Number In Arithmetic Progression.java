// LeetCode Question URL: https://leetcode.com/problems/missing-number-in-arithmetic-progression/
// LeetCode Discuss URL:

/**
 * Binary Search
 *
 * <pre>
 * Refer:
 * 1. Approach 2: Binary Search - https://leetcode.com/problems/missing-number-in-arithmetic-progression/solution/
 * 2. https://leetcode.com/problems/missing-number-in-arithmetic-progression/discuss/408474/JavaC++Python-Arithmetic-Sum-and-Binary-Search
 * </pre>
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int missingNumber(int[] arr) {
        if (arr == null || arr.length <= 2) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = arr.length;
        // a, a+d, a+2*d, ...., a+(n+1)*d --> There are n+1 valid numbers.. one number
        // is missing and thus the input length is n.
        int diff = (arr[len - 1] - arr[0]) / len;

        // Trying to find insertion point of the missing number.
        // Missing number cannot be inserted at index 0. Thus start will be 1.
        int start = 1;
        int end = len - 1;

        String.valueOf(10);

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == arr[0] + mid * diff) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        return arr[0] + start * diff;
    }
}

/**
 * Binary Search
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int missingNumber(int[] arr) {
        if (arr == null || arr.length <= 2) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int start = 0;
        int end = arr.length - 1;

        while (end - start > 1) {
            int mid = start + (end - start) / 2;
            double leftAvgDiff = (double) (Math.abs(arr[mid] - arr[start])) / (mid - start);
            double rightAvgDiff = (double) (Math.abs(arr[end] - arr[mid])) / (end - mid);
            if (leftAvgDiff < rightAvgDiff) {
                start = mid;
            } else {
                end = mid;
            }
        }

        return (arr[start] + arr[end]) / 2;
    }
}
