// LeetCode Question URL: https://leetcode.com/problems/3sum-closest/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sort the input array and then iterate through all possible first element of a
 * triplet. For each possible first element we make a standard bi-directional
 * 2Sum sweep of the remaining part of the array. Also skip equal elements to
 * avoid duplicates checking.
 *
 * Time Complexity: O(N log N + N^2) = O(N^2)
 *
 * Space Complexity: O(Space taken by sort algorithm) = O(log N) ~ O(N)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        Arrays.sort(nums);

        int closestSum = 0;
        int minDiff = Integer.MAX_VALUE;

        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }
            int start = i + 1;
            int end = len - 1;

            while (start < end) {
                int sum = nums[i] + nums[start] + nums[end];
                if (sum == target) {
                    return sum;
                }
                if (Math.abs(target - sum) < minDiff) {
                    closestSum = sum;
                    minDiff = Math.abs(target - sum);
                }

                if (sum < target) {
                    while (start < end && nums[start] == nums[start + 1]) {
                        start++;
                    }
                    start++;
                } else {
                    while (start < end && nums[end] == nums[end - 1]) {
                        end--;
                    }
                    end--;
                }
            }
        }

        return closestSum;
    }
}

class Solution2 {
    public int threeSumClosest(int[] nums, int target) {
        if (nums == null || nums.length < 3) {
            throw new IllegalArgumentException("Input is invalid");
        }

        Arrays.sort(nums);

        int closestSum = nums[0] + nums[1] + nums[2];
        if (closestSum == target) {
            return closestSum;
        }
        int minDiff = Math.abs(target - closestSum);
        int len = nums.length;

        for (int i = 0; i < len - 2; i++) {
            if (i > 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int left = i + 1;
            int right = len - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == target) {
                    return sum;
                }

                if (Math.abs(target - sum) < minDiff) {
                    closestSum = sum;
                    minDiff = Math.abs(target - sum);
                }

                if (sum < target) {
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    left++;
                } else {
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    right--;
                }
            }
        }

        return closestSum;
    }
}
