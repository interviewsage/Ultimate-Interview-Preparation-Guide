// LeetCode Question URL: https://leetcode.com/problems/range-sum-query-immutable/
// LeetCode Discuss URL: https://leetcode.com/problems/range-sum-query-immutable/discuss/1494994/Java-or-TC:-init-greater-O(N)-sumRange-greater-O(1)-or-Using-prefix-sum

/**
 * Using a prefix sum array
 *
 * prefixSum[i] = prefixSum[i-1] + nums[i-1]. Length of prefixSum array is 1 +
 * length of input array.
 *
 * sumRange(1,3) = prefixSum[3+1] - prefixSum[1]
 *
 * Time Complexity: NumArray() -> O(N). sumRange() -> O(1)
 *
 * Space Complexity: O(N) (Can be O(1) if allowed to modify nums array)
 *
 * N = Length of input array.
 */
class NumArray {

    int[] prefixSum;

    public NumArray(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        prefixSum = new int[len + 1];
        for (int i = 0; i < len; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        if (left < 0 || right >= prefixSum.length - 1 || left > right) {
            throw new IllegalArgumentException("Invalid input");
        }
        // Here both left and right are inclusive.
        // right maps to right+1 in prefixSum. left maps to left+1 in prefixSum.
        // To get the result subtract the prefixSum before left index from prefixSum at
        // right index.
        return prefixSum[right + 1] - prefixSum[left];
    }
}

// Your NumArray object will be instantiated and called as such:
// NumArray obj = new NumArray(nums);
// int param_1 = obj.sumRange(i,j);
