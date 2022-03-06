// LeetCode Question URL: https://leetcode.com/problems/k-diff-pairs-in-an-array/
// LeetCode Discuss URL:

import java.util.*;

/**
 * One Pass
 *
 * Refer:
 * https://leetcode.com/problems/k-diff-pairs-in-an-array/discuss/100098/Java-O(n)-solution-one-Hashmap-easy-to-understand/146186
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input array
 */
class Solution1 {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Map<Integer, Boolean> map = new HashMap<>();
        int result = 0;

        for (int n : nums) {
            Boolean isUnique = map.get(n);
            if (isUnique == null) {
                if (map.containsKey(n - k)) {
                    result++;
                }
                if (map.containsKey(n + k)) {
                    result++;
                }
                map.put(n, true);
            } else {
                if (k == 0 && isUnique) {
                    result++;
                    map.put(n, false);
                }
            }
        }

        return result;
    }
}

class Solution2 {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;

        for (int n : nums) {
            int count = map.getOrDefault(n, 0);
            if (count > 0) {
                if (k == 0 && count == 1) {
                    result++;
                }
            } else {
                if (map.containsKey(n - k)) {
                    result++;
                }
                if (map.containsKey(n + k)) {
                    result++;
                }
            }
            map.put(n, count + 1);
        }

        return result;
    }
}

/**
 * Using Sort.
 *
 * If input is a sorted array, this is most optimized solution.
 *
 * If input k is negative, then take an absolute of k. This will lead to same
 * result.
 *
 * Time Complexity: O(N*logN + 2*N) = O(N * logN)
 *
 * Space Complexity: O(Space used by sorting which can between logN & N)
 *
 * N = Length of input array
 */
class Solution3 {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        Arrays.sort(nums);

        int left = 0;
        int right = 1;
        int result = 0;
        int len = nums.length;

        while (right < len) {
            while (right < len - 1 && nums[right] == nums[right + 1]) {
                right++;
            }
            // Below while is unnecessary.
            // while (left < len - 2 && nums[left] == nums[left + 1] && nums[left + 1] ==
            // nums[left + 2]) {
            // left++;
            // }

            int diff = nums[right] - nums[left];
            if (left == right || diff < k) {
                right++;
            } else if (diff > k) {
                left++;
            } else {
                left++;
                right++;
                result++;
            }
        }

        return result;
    }
}

class Solution4 {
    public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length <= 1 || k < 0) {
            return 0;
        }

        Arrays.sort(nums);

        int left = 0;
        int right = 1;

        int count = 0;

        while (right < nums.length) {
            while (right < nums.length - 1 && nums[right] == nums[right + 1]) {
                right++;
            }
            int diff = nums[right] - nums[left];
            if (diff == k) {
                if (left == right) {
                    right++;
                    continue;
                }
                count++;
                right++;
                left++;
            } else if (diff < k) {
                right++;
            } else {
                left++;
            }
        }
        return count;
    }
}
