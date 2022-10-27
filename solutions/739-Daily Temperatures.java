// LeetCode Question URL: https://leetcode.com/problems/daily-temperatures/
// LeetCode Discuss URL: https://leetcode.com/problems/daily-temperatures/discuss/1574831/Java-2-Simple-Approaches-or-Constant-space-or-Stack-or-Beats-100-or-TC:-O(N)-SC:-O(1)

import java.util.*;

/**
 * Very similar to 503-Next Greater Element II.
 *
 * Time Complexity: O(2 * N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = temperatures.length;
        int[] result = new int[len];
        if (len <= 1) {
            return result;
        }

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int idx = stack.pop();
                result[idx] = i - idx;
            }
            stack.push(i);
        }

        return result;
    }
}

/**
 * Using constant space + One for-loop
 *
 * Refer: 1)
 * https://leetcode.com/problems/daily-temperatures/discuss/121787/C++-Clean-code-with-explanation:-O(n)-time-and-O(1)-space-(beats-99.13)
 *
 * 2)
 * https://leetcode.com/problems/daily-temperatures/discuss/397728/Easy-Python-O(n)-time-O(1)-space-beat-99.9
 *
 * Time Complexity: O(2 * N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = temperatures.length;
        int[] result = new int[len];
        if (len <= 1) {
            return result;
        }

        int hottestTemp = temperatures[len - 1];
        // We are populating the result array in the reverse order.
        for (int i = len - 2; i >= 0; i--) {
            // We are finding the hottestTemp, we can just update the hottestTemp and
            // continue as this the hottestTemp from right.
            if (hottestTemp <= temperatures[i]) {
                hottestTemp = temperatures[i];
                continue;
            }

            // We are now solving for ith temperature. We have already solved for everything
            // on the right side of index i.

            // Now we will start finding a hotter temp from next day onwards
            int days = 1;
            while (temperatures[i] >= temperatures[i + days]) {
                // We have found a lower temperature, we can just move pointer by the days
                // stored at result[i+days] to search for a higher temperature.
                // All temperatures between (i+days) and (i+days + result[i+days]) are smaller
                // than temperatures[i+days] so we can safely move to (i+days + result[i+days]).
                days += result[i + days];
            }
            // We have found the next hotter temp at i + days, saving the days at result[i].
            result[i] = days;
        }

        return result;
    }
}

/**
 * Using constant space + One for-loop
 *
 * Refer: 1)
 * https://leetcode.com/problems/daily-temperatures/discuss/121787/C++-Clean-code-with-explanation:-O(n)-time-and-O(1)-space-(beats-99.13)
 *
 * 2)
 * https://leetcode.com/problems/daily-temperatures/discuss/397728/Easy-Python-O(n)-time-O(1)-space-beat-99.9
 *
 * Time Complexity: O(2 * N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution3 {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = temperatures.length;
        int[] result = new int[len];
        if (len <= 1) {
            return result;
        }

        // We are populating the result array in the reverse order.
        for (int i = len - 2; i >= 0; i--) {
            // We are now solving for ith temperature. We have already solved for everything
            // on the right side of index i.

            // Start from index i+1
            int k = i + 1;
            while (temperatures[i] >= temperatures[k] && result[k] != 0) {
                // We have found a lower temperature, we can just move pointer by the days
                // stored at result[k] to search for a higher temperature.
                // All temperatures between k and k + result[k] are smaller than temperatures[k]
                // so we can safely move to k + result[k].
                k += result[k];
            }
            if (temperatures[i] < temperatures[k]) {
                // If a higher temperature is found, the difference of indexes will give us the
                // number days to get the higher temperature.
                result[i] = k - i;
            }
        }

        return result;
    }
}

/**
 * Using constant space
 *
 * Refer: 1)
 * https://leetcode.com/problems/daily-temperatures/discuss/121787/C++-Clean-code-with-explanation:-O(n)-time-and-O(1)-space-(beats-99.13)
 *
 * 2)
 * https://leetcode.com/problems/daily-temperatures/discuss/397728/Easy-Python-O(n)-time-O(1)-space-beat-99.9
 *
 * Time Complexity: O(3 * N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution4 {
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = temperatures.length;
        int[] result = new int[len];
        if (len <= 1) {
            return result;
        }

        for (int i = len - 2; i >= 0; i--) {
            int k = i + 1;
            while (temperatures[i] >= temperatures[k] && result[k] != 0) {
                k = result[k];
            }
            if (temperatures[i] < temperatures[k]) {
                result[i] = k;
            }
        }

        for (int i = 0; i < len; i++) {
            if (result[i] != 0) {
                result[i] -= i;
            }
        }
        return result;
    }
}
