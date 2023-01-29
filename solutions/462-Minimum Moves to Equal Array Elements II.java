// LeetCode Question URL: https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Better Solution. Using Median of Medians
 *
 * Approach 7: Using Median of Medians
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/solution/
 */

/**
 * Two Pointer Method
 *
 * This solution is same as 296. Best Meeting Point
 *
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/94937/Java(just-like-meeting-point-problem)/195799
 * 2. https://asvrada.github.io/blog/median-shortest-distance-sum/
 * 3. https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/discuss/1217473/C++PythonJava-2-Solutions-(w-and-wo-Median)-Explained-with-Example-implementation
 * </pre>
 */
class Solution {
    public int minMoves2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Arrays.sort(nums);

        int result = 0;
        int p1 = 0;
        int p2 = nums.length - 1;
        while (p1 < p2) {
            result += (nums[p2--] - nums[p1++]);
        }

        return result;
    }
}
