// LeetCode Question URL: https://leetcode.com/problems/majority-element/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Boyer–Moore majority vote algorithm
 *
 * <pre>
 * Refer:
 * 1. https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
 * 2. https://www.quora.com/What-is-the-proof-of-correctness-of-Moores-voting-algorithm
 * </pre>
 *
 * Proof: Since majority element occurs more than half times, then it will be
 * left in the end with positive count.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of nums array.
 */
class Solution1 {
    public int majorityElement(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is array is null");
        }

        int len = nums.length;
        if (len == 0) {
            throw new NoSuchElementException("No Majority element found");
        }

        int candidate = nums[0];
        int count = 1;

        for (int i = 1; i < len; i++) {
            if (count == 0) {
                candidate = nums[i];
                count = 1;
            } else if (candidate == nums[i]) {
                count++;
            } else {
                count--;
            }
        }

        count = 0;
        for (int n : nums) {
            if (n == candidate) {
                if (++count > len / 2) {
                    return candidate;
                }
            }
        }

        throw new NoSuchElementException("No Majority element found");
    }
}

/**
 * Refer: https://leetcode.com/submissions/detail/592807991/
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N/2)
 *
 * N = Length of nums array.
 */
class Solution2 {
    public int majorityElement(int[] nums) {
        return recurse(nums, 0);
    }

    public int recurse(int[] nums, int start) {
        int n = nums.length;
        int tracker = 1;
        for (int i = start + 1; i < n; i++) {
            if (nums[start] == nums[i]) {
                tracker++;
            } else {
                tracker--;
            }
            if (tracker == 0) {
                return recurse(nums, i + 1);
            }
        }
        return nums[start];
    }
}
