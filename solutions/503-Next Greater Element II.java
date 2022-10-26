// LeetCode Question URL: https://leetcode.com/problems/next-greater-element-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Very similar to 496-Next Greater Element I. The only difference here is that
 * we use stack to keep the indexes of the decreasing subsequence
 *
 * Refer:
 * https://leetcode.com/problems/next-greater-element-ii/discuss/98273/Java-10-lines-and-C++-12-lines-linear-time-complexity-O(n)-with-explanation
 *
 * Time Complexity: O(3*N) = O(N). Each number is added once in stack and once
 * removed + An extra loop for circular property.
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = nums.length;
        int[] result = new int[len];
        if (len == 0) {
            return result;
        }
        if (len == 1) {
            result[0] = -1;
            return result;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * len; i++) {
            int idx = i % len;
            while (!stack.isEmpty() && nums[stack.peek()] < nums[idx]) {
                result[stack.pop()] = nums[idx];
            }
            /*
             * Following if condition is for early exit. In the first loop stack.peek() and
             * idx cannot meet as stack.peek() will be always less.
             *
             * During the second loop, if they meet that means we can safely exit:
             *
             * 1. as we have already compared every number on the right in first loop.
             *
             * 2. Left numbers did not pop this value as they did not satisfy previous while
             * loop.
             *
             * Example: Nums has all numbers in descending order.
             */
            if (!stack.isEmpty() && stack.peek() == idx) {
                break;
            }
            if (i < len) {
                result[i] = -1;
                stack.push(i);
            }
        }

        return result;
    }
}

/**
 * Constant Space Solution.
 *
 * Refer:
 * https://leetcode.com/problems/next-greater-element-ii/discuss/98264/NO-STACK:-O(n)-time-complexity-and-O(1)-space-complexity-using-DP
 *
 * Time Complexity: O(2*N + 2*N + N) = O(N). 2N for 2 for-loops. 2N for 2 while
 * loops. N for final for-loop.
 *
 * Worst case time will be in [6,1,2,3,4,5]. In this case it will be 5*N.
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = nums.length;
        // Initially results array will store the indexes of the next greater element
        // and then we will replace them with actual values.
        int[] result = new int[len];
        if (len == 0) {
            return result;
        }
        result[len - 1] = -1;
        if (len == 1) {
            return result;
        }

        // This for loop considers the arrays as non-circular.
        // It will populate all the next greater element indexes in the results array
        // and for index there was no greater element (non-circular), it will put -1.
        for (int i = len - 2; i >= 0; i--) {
            int k = i + 1;
            // This while loop's job is to find the next greater element
            // or end at result[k] == -1.
            while (nums[i] >= nums[k] && result[k] != -1) {
                k = result[k];
            }
            result[i] = nums[i] < nums[k] ? k : -1;
        }

        // This for loop considers the arrays as circular.
        // It will populate all the next greater element indexes for the elements whose
        // greater element was not found in the previous loop.
        for (int i = len - 1; i >= 0; i--) {
            if (result[i] != -1) {
                continue;
            }
            int k = (i + 1) % len;
            while (nums[i] >= nums[k] && result[k] != -1) {
                k = result[k];
            }
            result[i] = nums[i] < nums[k] ? k : -1;
        }

        for (int i = 0; i < len; i++) {
            result[i] = result[i] != -1 ? nums[result[i]] : -1;
        }
        return result;
    }
}

/**
 * Constant Space Solution. This combines 2 for loops to remove duplicate code.
 *
 * Refer:
 * https://leetcode.com/problems/next-greater-element-ii/discuss/98264/NO-STACK:-O(n)-time-complexity-and-O(1)-space-complexity-using-DP
 *
 * Time Complexity: O(2*N + 2*N + N) = O(N). 2N for first for-loop. 2N for while
 * loop. N for final for-loop.
 *
 * Worst case time will be in [6,1,2,3,4,5]. In this case it will be 5*N.
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution3 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }
        int len = nums.length;
        // Initially results array will store the indexes of the next greater element
        // and then we will replace them with actual values.
        int[] result = new int[len];
        if (len == 0) {
            return result;
        }
        result[len - 1] = -1;
        if (len == 1) {
            return result;
        }

        // We have combined the non-circular and circular portions into one for-loop
        // Non-Circular portion will run from i = 2*len-2 to len (which is len-1 times)
        // Circular portion will run from i = len-1 to 0 (which is len times)
        for (int i = 2 * len - 2; i >= 0; i--) {
            if (i < len && result[i] != -1) {
                continue;
            }
            int I = i % len;
            int k = (I + 1) % len;
            // This while loop's job is to find the next greater element
            // or end at result[k] == -1.
            while (nums[I] >= nums[k] && result[k] != -1) {
                k = result[k];
            }
            result[I] = nums[I] < nums[k] ? k : -1;
        }

        for (int i = 0; i < len; i++) {
            result[i] = result[i] != -1 ? nums[result[i]] : -1;
        }
        return result;
    }
}
