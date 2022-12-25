// LeetCode Question URL: https://leetcode.com/problems/3sum-smaller/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sort the input array and then iterate through all possible first element of a
 * triplet. For each possible first element we make a standard bi-directional
 * 2Sum sweep of the remaining part of the array.
 *
 * IMP Note: Do not ignore duplicate numbers as the question asks for index
 * triplets.
 *
 * Time Complexity: O(N log N + N^2) = O(N^2)
 *
 * Space Complexity: O(Space taken by sort algorithm) = O(log N) ~ O(N)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len < 3) {
            return 0;
        }

        Arrays.sort(nums);

        int result = 0;

        for (int i = 0; i < len - 2; i++) {
            if (nums[i] * 3 >= target) {
                break;
            }

            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                if (nums[i] + nums[left] + nums[right] < target) {
                    result += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}

class Solution2 {
    public int threeSumSmaller(int[] nums, int target) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        Arrays.sort(nums);
        int count = 0;

        for (int i = 0; i < len - 2; i++) {
            if (nums[i] * 3 >= target) {
                break;
            }

            int start = i + 1;
            int end = len - 1;

            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum < target) {
                    while (start < end && nums[start] == nums[start + 1]) {
                        count += end - start;
                        start++;
                    }
                    count += end - start;
                    start++;
                } else {
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    end--;
                }

            }
        }

        return count;
    }
}