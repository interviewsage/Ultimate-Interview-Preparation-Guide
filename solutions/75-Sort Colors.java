// LeetCode Question URL: https://leetcode.com/problems/sort-colors/
// LeetCode Discuss URL: https://leetcode.com/problems/sort-colors/discuss/1555767/Java-or-TC:-O(N)-or-SC:-O(1)-or-Simple-One-Pass-Constant-Space-wo-using-a-Swap-Function

/**
 * One-Pass Constant Space solution (Without using a Swap Function)
 *
 * Move all 0s to the left and all 2s to the right, then all 1s are left in the
 * middle. Without using a swap function.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public void sortColors(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return;
        }

        int zeroInsertPos = 0;
        int twoInsertPos = len - 1;
        int idx = 0;

        while (idx <= twoInsertPos) {
            switch (nums[idx]) {
                case 0:
                    if (idx != zeroInsertPos) {
                        nums[idx] = 1;
                        nums[zeroInsertPos] = 0;
                    }
                    zeroInsertPos++;
                    // Can increment i. As only 0s and 1s are present between zero and i.
                    idx++;
                    break;
                case 1:
                    idx++;
                    break;
                case 2:
                    if (idx != twoInsertPos) {
                        nums[idx] = nums[twoInsertPos];
                        nums[twoInsertPos] = 2;
                    }
                    twoInsertPos--;
                    // Cannot increment i. The number at i can now be a zero.
                    break;
                default:
                    throw new IllegalArgumentException("Invalid color");
            }
        }
    }
}

/**
 * DO NOT USE THIS SOLUTION
 *
 * One-Pass Constant Space solution (With using a Swap Function)
 *
 * Move all 0s to the left and all 2s to the right, then all 1s are left in the
 * middle. Using a Swap function
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        int zeros = 0;
        int twos = nums.length - 1;
        int i = 0;

        while (i <= twos) {
            if (nums[i] == 0) {
                swap(nums, i, zeros);
                zeros++;
                // Can increment i. As only 0s and 1s are present between zero and i.
                i++;
            } else if (nums[i] == 2) {
                swap(nums, i, twos);
                twos--;
                // Cannot increment i. The number at i can now be a zero.
            } else {
                // Its a one.. so we do not need to move it.
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }
}
