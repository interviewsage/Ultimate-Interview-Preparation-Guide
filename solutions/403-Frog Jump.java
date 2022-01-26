// LeetCode Question URL: https://leetcode.com/problems/frog-jump/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: From first stone possible future steps are N-1. From second
 * stone N-2 steps are possible. From second last stone only 1 step is possible.
 * Thus T(N) = N-1 + N-2 + .. + 1 = N(N-1)/2. Thus TC = O(N^2)
 *
 * Space Complexity: O(N + N^2)
 *
 * <pre>
 * Lets take a case where all the stones exist from 0 to N-1.
 * Then the series of number of jumps will be:
 * 1, 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5, 5, 5, 5, ...
 * Where N = 1 + 2 + 3 + 4 + 5 + ...
 *
 * Therefore, TC = 1^2 + 2^2 + 3^2 + 4^2 + ...
 * And, Sum of squares: n * (n+1) * (2*n+1) / 6
 * https://www.quora.com/What-is-the-value-of-1-2+2-2+3-2+-n-2
 * </pre>
 *
 * N = Number of stones.
 */
class Solution {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0 || stones[0] != 0) {
            return false;
        }
        int len = stones.length;
        if (len > 1 && stones[1] != 1) {
            return false;
        }
        if (len <= 2) {
            return true;
        }

        // Key = Stone location, Value = List of valid steps that can be taken from here
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 1; i < len - 1; i++) {
            map.put(stones[i], new HashSet<>());
        }
        // From stone at 1st, frog will take either jump of 1 unit or 2 units.
        map.get(1).addAll(Set.of(1, 2));

        for (int i = 1; i < len - 1; i++) {
            for (int jump : map.get(stones[i])) {
                int newPos = stones[i] + jump;
                if (newPos == stones[len - 1]) {
                    return true;
                }

                Set<Integer> set = map.get(newPos);
                if (set == null) {
                    continue;
                }
                if (jump > 1) {
                    set.add(jump - 1);
                }
                set.add(jump);
                set.add(jump + 1);
            }
        }

        return false;
    }
}
