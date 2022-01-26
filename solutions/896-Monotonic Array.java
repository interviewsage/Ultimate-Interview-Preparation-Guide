// LeetCode Question URL: https://leetcode.com/problems/monotonic-array/
// LeetCode Discuss URL:

/**
 * One Pass solution
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public boolean isMonotonic(int[] A) {
        if (A == null) {
            return false;
        }

        int len = A.length;

        if (len <= 2) {
            return true;
        }

        boolean inc = true;
        boolean dec = true;

        for (int i = 1; i < len; i++) {
            inc = inc && (A[i] >= A[i - 1]);
            dec = dec && (A[i] <= A[i - 1]);

            if (!inc && !dec) {
                return false;
            }
        }

        // return inc || dec;
        return true;
    }
}

class Solution2 {
    public boolean isMonotonic(int[] nums) {
        if (nums == null) {
            return false;
        }

        int len = nums.length;
        if (len <= 2) {
            return true;
        }

        boolean inc = true;
        boolean dec = true;

        for (int i = 1; i < len; i++) {
            inc &= nums[i - 1] <= nums[i];
            dec &= nums[i - 1] >= nums[i];

            // if (inc && nums[i-1] > nums[i]) {
            // inc = false;
            // } else if (dec && nums[i-1] < nums[i]) {
            // dec = false;
            // }

            if (!(inc || dec)) {
                return false;
            }
        }

        return true;
    }
}

class Solution3 {
    public boolean isMonotonic(int[] A) {
        int store = 0;
        for (int i = 0; i < A.length - 1; ++i) {
            int c = Integer.compare(A[i], A[i + 1]);
            if (c != 0) {
                if (c != store && store != 0)
                    return false;
                store = c;
            }
        }

        return true;
    }
}