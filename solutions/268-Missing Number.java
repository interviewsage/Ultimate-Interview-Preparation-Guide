// LeetCode Question URL: https://leetcode.com/problems/missing-number/
// LeetCode Discuss URL: https://leetcode.com/problems/missing-number/discuss/1555805/Java-Three-Simple-Approaches:-Bit-Manipulation-Sum-and-Binary-Search-(for-follow-up)

import java.util.*;

/**
 * Bit Manipulation.
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/missing-number/discuss/69791/4-Line-Simple-Java-Bit-Manipulate-Solution-with-Explaination
 * 2) https://leetcode.com/problems/missing-number/discuss/69791/4-Line-Simple-Java-Bit-Manipulate-Solution-with-Explaination/119313
 * </pre>
 *
 * XOR all numbers in the input array and then xor this with all number from 0
 * to N. We will be left with the missing number.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int missingNumber(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int result = nums.length;
        for (int i = 0; i < nums.length; i++) {
            result ^= i ^ nums[i];
        }

        return result;
    }
}

/**
 * Subtract all numbers from n * (n+1) / 2
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public int missingNumber(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        int sum = len * (len + 1) / 2;
        for (int n : nums) {
            sum -= n;
        }

        return sum;
    }
}

/**
 * Follow-Up: If the input array is sorted, can we find the missing number in
 * less than O(N) time.
 *
 * Binary Search
 *
 * <pre>
 * Time Complexity:
 * - O(logN) --> If array is already sorted.
 * - O(N*logN + logN) --> If we need to sort the array. (DO NOT USE this solution in this scenario)
 *
 * Space Complexity:
 * - O(1) --> If array is already sorted.
 * - O(Space taken by sorting algorithm) --> If we need to sort the array.
 * </pre>
 *
 * N = Length of the input array.
 */
class Solution3 {
    public int missingNumber(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len == 0) {
            return 0;
        }

        Arrays.sort(nums);
        // Here search space is from 0 to len, as the missing number is in range 0 to
        // len.
        int start = 0;
        int end = len;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] > mid) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}
