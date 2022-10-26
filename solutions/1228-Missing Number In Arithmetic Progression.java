// LeetCode Question URL: https://leetcode.com/problems/missing-number-in-arithmetic-progression/
// LeetCode Discuss URL:

/**
 * Binary Search
 *
 * <pre>
 * Refer:
 * 1. Approach 2: Binary Search - https://leetcode.com/problems/missing-number-in-arithmetic-progression/solution/
 * 2. https://leetcode.com/problems/missing-number-in-arithmetic-progression/discuss/408474/JavaC++Python-Arithmetic-Sum-and-Binary-Search
 * 3. Learn about arithmetic progressions: https://byjus.com/maths/arithmetic-progression/#first-term
 * </pre>
 *
 * Time Complexity: O(log N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int missingNumber(int[] arr) {
        if (arr == null || arr.length <= 1) {
            throw new IllegalArgumentException("Input is invalid");
        }
        int len = arr.length;
        // a, a+d, a+2*d, ...., a+(n+1)*d --> There are n+1 valid numbers.. one number
        // is missing and thus the input length is n.
        int diff = (arr[len - 1] - arr[0]) / len;
        if (diff == 0 || len == 2 || arr[1] - arr[0] != diff) {
            return arr[0] + diff;
        }
        if (arr[len - 1] - arr[len - 2] != diff) {
            return arr[len - 2] + diff;
        }

        // Trying to find insertion point of the missing number.
        // We have already solved for index 0, 1 & len-1 in above base cases.
        int start = 2;
        int end = len - 2;
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (arr[mid] == arr[0] + mid * diff) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }

        if (arr[start] - arr[start - 1] == diff) {
            throw new IllegalArgumentException("Input does not have a missing number");
        }

        return arr[start] - diff;
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
