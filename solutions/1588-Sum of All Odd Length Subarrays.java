// LeetCode Question URL: https://leetcode.com/problems/sum-of-all-odd-length-subarrays/
// LeetCode Discuss URL:

/**
 * Count how many times each index will appear in odd length subarrays. Refer to
 * this post and youtube video for more info
 * https://leetcode.com/problems/sum-of-all-odd-length-subarrays/discuss/854184/JavaC++Python-O(N)-Time-O(1)-Space/763425
 *
 * <pre>
 * For example lets say input array is [1, 2, 3, 4, 5], and we have to find all subarrays that include 2.
 *
 * Subarrays on the left that include 2:
 *         2
 *      1, 2
 * Number of such subarrays = idx + 1. (Here idx of 2 is 1)
 *
 * Subarrays on the right that include 2:
 *      2
 *      2, 3
 *      2, 3, 4
 *      2, 3, 4, 5
 * Number of such subarrays = len - idx. (Here idx of 2 is 1 & len is 5)
 *
 * Also, there will be subarrays that will start on left and end on right. Those include following combinations
 * Left:
 *      1, 2
 * Right:
 *      2, 3
 *      2, 3, 4
 *      2, 3, 4, 5
 * Note: Subarray 2 was ignored as it was already included in above.
 * Number of such subarrays = (idx + 1 - 1) * (len - idx - 1)
 *
 * Thus total number of subarrays = (idx + 1) + (len - idx) - 1 + (idx + 1 - 1) * (len - idx - 1)
 *                                = len + idx * (len - idx - 1)
 *                                = (len - idx) + idx * (len - idx)
 *                                = (len - idx) * (idx + 1)
 *
 * Now, If the total number of subarrays is odd, there will be an extra odd length array:
 * All Subarrays that have 1:
 * [1], [1,2], [1,2,3], [1,2,3,4], [1,2,3,4,5]
 * Total = 5
 * Odd Length = 3
 * Even Length = 2
 *
 * All Subarrays that have 2:
 * [2], [1,2], [2,3] [1,2,3], [2,3,4] [1,2,3,4], [2,3,4,5], [1,2,3,4,5]
 * Total = 8
 * Odd Length = 4
 * Even Length = 4
 *
 * Thus, odd length subarrays at an index idx = (Total_no_of_subarrays + 1) / 2
 *                                            = ((len - idx) * (idx + 1) + 1) / 2
 *
 * And, even length subarrays at an index idx = Total_no_of_subarrays / 2
 *                                            = (len - idx) * (idx + 1) / 2
 * </pre>
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int sumOddLengthSubarrays(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = arr.length;
        int result = 0;
        for (int i = 0; i < len; i++) {
            result += ((len - i) * (i + 1) + 1) / 2 * arr[i];
        }
        return result;
    }
}
