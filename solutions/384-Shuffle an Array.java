// LeetCode Question URL: https://leetcode.com/problems/shuffle-an-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Fisher-Yates Algorithm
 *
 * Refer: https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle
 *
 * In Shuffle function, probability of each item in array is equal (1 / N). This
 * is due to reservoir sampling with size of reservoir as 1.
 *
 * Suppose the indexes of the array are from 1 to N. You have already placed i-1
 * elements. Now you are trying to place ith element. The probability to place
 * it at an index between 1 and i is 1/i. Now you do not want to replace ith
 * number by any future numbers.. Thus, the final probability for ith element =
 * 1/i * (1 - 1/(i+1)) * (1 - 1/(i+2)) * .. * (1 - 1/N) = 1 / N.
 *
 * Time Complexity: 1) Solution() -> O(N).. 2) reset() -> O(N).. 3) shuffle() ->
 * O(N)
 *
 * Space Complexity: O(N). This is required for clone of original array.
 *
 * N = length of the input array.
 */
class Solution {

    int[] nums;
    int[] orig;
    Random random;

    public Solution(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input nums array is null");
        }

        this.nums = nums;
        this.orig = Arrays.copyOf(nums, nums.length);
        this.random = new Random();
    }

    public int[] reset() {
        nums = Arrays.copyOf(orig, orig.length);
        return nums;
    }

    public int[] shuffle() {
        for (int i = 1; i < nums.length; i++) {
            swap(nums, i, random.nextInt(i + 1));
        }
        return nums;
    }

    private void swap(int[] nums, int a, int b) {
        if (a != b) {
            int t = nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }
    }
}

// Your Solution object will be instantiated and called as such:
// Solution obj = new Solution(nums);
// int[] param_1 = obj.reset();
// int[] param_2 = obj.shuffle();
