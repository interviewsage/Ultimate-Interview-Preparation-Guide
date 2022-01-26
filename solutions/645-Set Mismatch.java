// LeetCode Question URL: https://leetcode.com/problems/set-mismatch/
// LeetCode Discuss URL:

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/find-the-duplicate-number/solution/
 * 2. https://leetcode.com/problems/set-mismatch/solution/725073
 * 3. https://leetcode.com/problems/set-mismatch/discuss/105513/XOR-one-pass
 * 4. https://leetcode.com/problems/set-mismatch/discuss/1089475/Python-O(n)-timeO(1)-space-math-solution-explained
 * </pre>
 */

/**
 * Multiple found indexes and perform XOR to find duplicate and missing number.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution1 {
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        int[] result = new int[2];
        int xor = 0;
        for (int i = 0; i < len; i++) {
            int absNum = Math.abs(nums[i]);
            xor ^= (i + 1) ^ absNum;

            int idx = absNum - 1;
            if (nums[idx] < 0) {
                result[0] = absNum;
            } else {
                nums[idx] *= -1;
            }
        }

        result[1] = xor ^ result[0];
        return result;
    }
}

/**
 * Math.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution2 {
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int diff = 0;
        int squareDiff = 0;

        for (int i = 0; i < nums.length; i++) {
            diff += (i + 1) - nums[i];
            squareDiff += (i + 1) * (i + 1) - nums[i] * nums[i];
        }

        int sum = squareDiff / diff;
        return new int[] { (sum - diff) / 2, (sum + diff) / 2 };
    }
}

/**
 * Math Handled overflow
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution3 {
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("Input is invalid");
        }

        long len = nums.length;
        long diff = len * (len + 1) / 2;
        long squareDiff = len * (len + 1) * (2 * len + 1) / 6;

        for (int i = 0; i < len; i++) {
            diff -= nums[i];
            squareDiff -= nums[i] * nums[i];
        }

        long sum = squareDiff / diff;
        return new int[] { (int) (sum - diff) / 2, (int) (sum + diff) / 2 };
    }
}

/**
 * Two Pass solution using Swap
 *
 * Time Complexity: O(2 * N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution4 {
    public int[] findErrorNums(int[] nums) {
        if (nums == null || nums.length <= 1) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        int[] result = new int[2];
        for (int i = 0; i < len; i++) {
            while (nums[nums[i] - 1] != nums[i]) {
                swap(nums, nums[i] - 1, i);
            }
            if (nums[i] != i + 1) {
                result[0] = nums[i];
                result[1] = i + 1;
            }
        }

        return result;
    }

    private void swap(int[] nums, int a, int b) {
        if (a != b) {
            int t = nums[a];
            nums[a] = nums[b];
            nums[b] = t;
        }
    }
}
