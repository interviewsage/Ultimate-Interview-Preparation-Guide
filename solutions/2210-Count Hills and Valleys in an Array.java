// LeetCode Question URL: https://leetcode.com/problems/count-hills-and-valleys-in-an-array/
// LeetCode Discuss URL:

/**
 * One Pass solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int countHillValley(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len <= 2) {
            return 0;
        }

        int result = 0;
        int prev = nums[0];

        for (int i = 1; i < len - 1; i++) {
            if ((prev < nums[i] && nums[i] > nums[i + 1]) || (prev > nums[i] && nums[i] < nums[i + 1])) {
                result++;
                prev = nums[i];
            }
        }

        return result;
    }
}

/**
 * One Pass solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int countHillValley(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len <= 2) {
            return 0;
        }

        int prev = nums[0];
        int cur = nums[1];
        int idx = 2;
        int result = 0;

        while (idx < len) {
            while (idx < len && cur == nums[idx]) {
                cur = nums[idx++];
            }
            if (idx == len) {
                return result;
            }

            int next = nums[idx++];
            if ((prev < cur && cur > next) || (prev > cur && cur < next)) {
                result++;
            }

            prev = cur;
            cur = next;
        }

        return result;
    }
}

/**
 * One Pass solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution3 {
    public int countHillValley(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Invalid input");
        }

        int len = nums.length;
        if (len <= 2) {
            return 0;
        }

        int res = 0;
        int i = 1;
        while (i < len - 1) {
            int cur = i++;
            while (i < len) {
                if (nums[i] != nums[cur]) {
                    break;
                }
                i++;
            }
            if (i == len) {
                break;
            }
            if ((nums[cur - 1] < nums[cur] && nums[cur] > nums[i])
                    || (nums[cur - 1] > nums[cur] && nums[cur] < nums[i])) {
                res++;
            }
        }

        return res;
    }
}
