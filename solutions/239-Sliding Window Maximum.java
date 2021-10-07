// LeetCode Question URL: https://leetcode.com/problems/sliding-window-maximum/
// LeetCode Discuss URL: https://leetcode.com/problems/sliding-window-maximum/discuss/1506048/Java-or-TC:-O(N)-or-SC:-O(K)-or-Using-Deque-as-Sliding-Window

import java.util.*;

/**
 * Using Deque as Sliding Window
 *
 * Refer:
 * https://leetcode.com/problems/sliding-window-maximum/discuss/65884/Java-O(n)-solution-using-deque-with-explanation
 *
 * If an element in the deque and it is out of i-(k-1), we discard them. We just
 * need to poll from the head, as we are using a deque and elements are ordered
 * as the sequence in the array
 *
 * Now only those elements within [i-(k-1),i] are in the deque. We then discard
 * elements smaller than a[i] from the tail. This is because if a[x] <a[i] and
 * x<i, then a[x] has no chance to be the "max" in [i-(k-1),i], or any other
 * subsequent window: a[i] would always be a better candidate.
 *
 * As a result elements in the deque are ordered in both sequence in array and
 * their value. At each step the head of the deque is the max element in
 * [i-(k-1),i]
 *
 * Time Complexity: O(N). Each number is added once to the queue and then
 * removed once from the queue
 *
 * Space Complexity: O(K) -> Excluding the space taken by result array.
 *
 * N = Length of input array. K = Window size.
 */
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        int len = nums.length;
        if (len == 0 || k == 1) {
            return Arrays.copyOf(nums, len);
        }
        if (len <= k) {
            return new int[] { getMaxVal(nums) };
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[len - k + 1];

        for (int i = 0; i < len; i++) {
            if (!deque.isEmpty() && deque.peekFirst() == i - k) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

    private int getMaxVal(int[] nums) {
        int max = nums[0];
        for (int n : nums) {
            max = Math.max(max, n);
        }
        return max;
    }
}
