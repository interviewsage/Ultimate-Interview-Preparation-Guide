// LeetCode Question URL: https://leetcode.com/problems/sort-array-by-parity/
// LeetCode Discuss URL:

/**
 * Solution using sorting:
 *
 * Arrays.sort(B, (a, b) -> Integer.compare(a%2, b%2));
 */

/**
 * One Pass with minimum Swaps
 *
 * Time Complexity: O(N)
 *
 * Space Complexity : O(1)
 *
 * N = Length of input array
 */
class Solution1 {
    public int[] sortArrayByParity(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int even = 0;
        int odd = nums.length - 1;

        while (even < odd) {
            while (even < odd && (nums[even] & 1) == 0) {
                even++;
            }
            while (even < odd && (nums[odd] & 1) == 1) {
                odd--;
            }
            if (even < odd) {
                int t = nums[even];
                nums[even] = nums[odd];
                nums[odd] = t;
                even++;
                odd--;
            }
        }

        return nums;
    }
}

/**
 * One Pass
 *
 * Time Complexity: O(N)
 *
 * Space Complexity : O(1)
 *
 * N = Length of input array
 */
class Solution2 {
    public int[] sortArrayByParity(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }

        int insertEvenPos = -1;
        for (int i = 0; i < nums.length; i++) {
            if ((nums[i] & 1) == 0) {
                ++insertEvenPos;
                if (insertEvenPos != i) {
                    int t = nums[i];
                    nums[i] = nums[insertEvenPos];
                    nums[insertEvenPos] = t;
                }
            }
        }

        return nums;
    }
}
