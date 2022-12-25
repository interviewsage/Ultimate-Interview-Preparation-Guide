// LeetCode Question URL: https://leetcode.com/problems/3sum/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Sort the input array and then iterate through all possible first element of a
 * triplet. For each possible first element we make a standard bi-directional
 * 2Sum sweep of the remaining part of the array. Also skip equal elements to
 * avoid duplicates in the answer.
 *
 * Time Complexity: O(N log N + N^2) = O(N^2)
 *
 * Space Complexity: O(Space taken by sort algorithm) = O(log N) ~ O(N)
 *
 * N = Length of input array.
 */
class Solution1 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        List<List<Integer>> result = new ArrayList<>();
        int len = nums.length;
        if (len < 3) {
            return result;
        }

        Arrays.sort(nums);
        int k = 0; // Target

        for (int i = 0; i < len - 2; i++) {
            if (nums[i] * 3 > k) {
                break;
            }
            if (i != 0 && nums[i - 1] == nums[i]) {
                continue;
            }

            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == k) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right - 1] == nums[right]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < k) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }
}

/**
 * This solution does not modify the input array
 *
 * Refer:
 * 1. Approach 3: "No-Sort" - https://leetcode.com/problems/3sum/solution/
 * 2. https://leetcode.com/problems/3sum/solution/1056876
 *
 * Time Complexity: O(N^2)
 *
 * Space Complexity: O(N + 2*N + N^3) = O(firstIndexSet + lastSeenMap +
 * resultSet) = O(N^3)
 *
 * N = Length of input array
 */
class Solution2 {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }
        int len = nums.length;
        if (len < 3) {
            return new ArrayList<>();
        }

        Set<List<Integer>> resultSet = new HashSet<>();
        Set<Integer> firstIndexSet = new HashSet<>();
        Map<Integer, Integer> lastSeenMap = new HashMap<>();
        int k = 0; // Target

        for (int i = 0; i < len - 2; i++) {
            if (firstIndexSet.add(nums[i])) {
                for (int j = i + 1; j < len; j++) {
                    int complement = k - nums[i] - nums[j];
                    if (Integer.valueOf(i).equals(lastSeenMap.get(complement))) {
                        List<Integer> resTriplet = Arrays.asList(nums[i], nums[j], complement);
                        Collections.sort(resTriplet);
                        resultSet.add(resTriplet);
                    }
                    lastSeenMap.put(nums[j], i); // Seen at ith iteration.
                }
            }
        }

        return new ArrayList<>(resultSet);
    }
}
