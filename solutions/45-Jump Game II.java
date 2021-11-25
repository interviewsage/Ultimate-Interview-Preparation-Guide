// LeetCode Question URL: https://leetcode.com/problems/jump-game-ii/
// LeetCode Discuss URL:

/**
 * Greedy Solution. Kind of a level order traversal
 *
 * Refer:
 * https://leetcode.com/problems/jump-game-ii/discuss/18014/Concise-O(n)-one-loop-JAVA-solution-based-on-Greedy
 *
 * The main idea is based on greedy. Let's say the range of the current jump is
 * [curBegin, curEnd], curFarthest is the farthest point that all points in
 * [curBegin, curEnd] can reach. Once the current point reaches curEnd, then
 * trigger another jump, and set the new curEnd with curFarthest, then repeat
 * the above steps.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of input array.
 */
class Solution {
    public int jump(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int len = nums.length;
        if (len == 1) {
            return 0;
        }
        if (nums[0] >= len - 1) {
            return 1;
        }
        if (len > 1 && nums[0] == 0) {
            // Since first element is zero, we cannot reach the end.
            return -1;
        }

        int i = 1;
        int range = nums[0];
        int jumps = 1;

        while (i <= range) {
            jumps++; // Now we are going to take this jump in the below while loop
            int newRange = range;
            while (i <= range) {
                newRange = Math.max(newRange, i + nums[i]);
                if (newRange >= len - 1) {
                    return jumps;
                }
                i++;
            }
            range = newRange;
        }

        return -1;
    }
}
