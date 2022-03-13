// LeetCode Question URL: https://leetcode.com/problems/minimum-moves-to-equal-array-elements/
// LeetCode Discuss URL:

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/minimum-moves-to-equal-array-elements/discuss/93817/It-is-a-math-question/98131
 * 2. https://leetcode.com/problems/minimum-moves-to-equal-array-elements/discuss/1217502/JavaPython-Clear-explanation-O(N)
 * </pre>
 *
 * Lets define sum as the sum of all the numbers, before any moves. minNum as
 * the min number in the list. n is the length of the list. After, say m moves,
 * we get all the numbers as x:
 *
 * sum + m * (n - 1) = x * n
 *
 * here, x = minNum + m. Why: our goal is to increment minNum to be equal to
 * maxNum. No matter how many add operations are executed, the goal won't
 * change. Every time we do the add operation, the min number in the array must
 * participate in. After an add operation, the minNum is still the min number So
 * the minNum participate in every add operation So x = minNum + m.
 *
 * sum + m * (n-1) = (min + m) * n ==> m = sum - min * n
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input array.
 */
class Solution {
    public int minMoves(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int min = nums[0];
        int sum = nums[0];
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            min = Math.min(min, nums[i]);
            sum += nums[i];
        }

        return sum - n * min;
    }
}
