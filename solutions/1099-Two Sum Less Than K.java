// LeetCode Question URL: https://leetcode.com/problems/two-sum-less-than-k/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Clarifying Questions:
 * https://leetcode.com/problems/two-sum-less-than-k/solution/#further-thoughts
 */

/**
 * Sort the array and use two pointers to find the closest sum
 *
 * Time Complexity: O(N log N + N) = O(N log N)
 *
 * Space Complexity: O(space taken by sort algorithm) = O(log N) (Java uses Dual
 * Pivot Quick Sort for arrays of primitives type)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int twoSumLessThanK(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        if (nums.length < 2) {
            return -1;
        }

        Arrays.sort(nums);

        int left = 0;
        int right = nums.length - 1;
        Integer result = null;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum < k) {
                result = result == null ? sum : Math.max(result, sum);
                left++;
            } else {
                right--;
            }
        }

        return result == null ? -1 : result;
    }
}

/**
 * Find the range of nums arrays and also create a count map of each num in the
 * array. Then run two pointers approach from min to max to find the closest
 * sum.
 *
 * Time Complexity: O(N + Range)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int twoSumLessThanK(int[] nums, int k) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return -1;
        }

        int max = nums[0];
        int min = nums[0];
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            max = Math.max(max, n);
            min = Math.min(min, n);
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        Integer result = null;
        while (min <= max) {
            int sum = min + max;
            if (sum >= k || !countMap.containsKey(max)) {
                max--;
            } else {
                if ((min == max && countMap.get(max) > 1) || (min != max && countMap.containsKey(min))) {
                    result = result == null ? sum : Math.max(result, sum);
                }
                min++;
            }
        }

        return result == null ? -1 : result;
    }
}
