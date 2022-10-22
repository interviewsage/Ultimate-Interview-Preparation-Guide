// LeetCode Question URL: https://leetcode.com/problems/maximum-average-subarray-i/
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Input is invalid");
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        double sum = 0;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }

        double maxSum = sum;
        for (int i = k; i < len; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum / k;
    }
}

class Solution2 {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Input is invalid");
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        long sum = 0;
        long maxSum = Long.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            sum += nums[i];
            if (i >= k) {
                sum -= nums[i - k];
            }
            if (i >= k - 1) {
                maxSum = Math.max(maxSum, sum);
            }
        }

        return (double) maxSum / k;
    }
}

class Solution3 {
    public double findMaxAverage(int[] nums, int k) {
        if (nums == null || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Input is invalid");
        }
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        double avg = 0;
        for (int i = 0; i < k; i++) {
            avg += (double) nums[i] / k;
        }

        double maxAvg = avg;
        for (int i = k; i < nums.length; i++) {
            avg -= (double) nums[i - k] / k;
            avg += (double) nums[i] / k;
            maxAvg = Math.max(maxAvg, avg);
        }

        return maxAvg;
    }
}
