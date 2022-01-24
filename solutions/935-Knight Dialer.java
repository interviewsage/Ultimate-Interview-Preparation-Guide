// LeetCode Question URL: https://leetcode.com/problems/knight-dialer/
// LeetCode Discuss URL:

import java.util.*;

/**
 * log(N) solution: https://leetcode.com/problems/knight-dialer/discuss/189252/O(logN)/310120
 */

/**
 * BFS (Bottom Up approach)
 *
 * DP[i] = Number of distinct ways Knight can reach i after N hops
 *
 * Time Complexity: O(N * 10 * 3). Thus complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Number of hops
 */
class Solution1 {
    public int knightDialer(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 10;
        }

        int mod = 1000000007;
        Map<Integer, Set<Integer>> graph = Map.of(
                0, Set.of(4, 6),
                1, Set.of(6, 8),
                2, Set.of(7, 9),
                3, Set.of(4, 8),
                4, Set.of(0, 3, 9),
                5, Collections.emptySet(),
                6, Set.of(0, 1, 7),
                7, Set.of(2, 6),
                8, Set.of(1, 3),
                9, Set.of(2, 4));

        int[] prevDP = new int[10];
        Arrays.fill(prevDP, 1);

        while (--n > 0) {
            int[] curDP = new int[10];
            for (int i = 0; i < 10; i++) {
                for (int jumpingFrom : graph.get(i)) {
                    curDP[i] = (curDP[i] + prevDP[jumpingFrom]) % mod;
                }
            }
            prevDP = curDP;
        }

        int result = 0;
        for (int i : prevDP) {
            result = (result + i) % mod;
        }

        return result;
    }
}

/**
 * DFS (Top down approach)
 *
 * Cur_N = if at current and have to take N hops, then count is the value in the
 * map.
 *
 * Time Complexity: O(Number of States * Time taken for each state + 10) = O(N *
 * 10 * 3 + 10) = O(N)
 *
 * Space Complexity: O(Number of states + Recursion Depth) = O(N*10 + N) = O(N)
 *
 * N = Number of hops
 */
class Solution2 {
    private static final int MOD = 1000000007;
    private static final Map<Integer, Set<Integer>> graph = Map.of(
            0, Set.of(4, 6),
            1, Set.of(6, 8),
            2, Set.of(7, 9),
            3, Set.of(4, 8),
            4, Set.of(0, 3, 9),
            5, Collections.emptySet(),
            6, Set.of(0, 1, 7),
            7, Set.of(2, 6),
            8, Set.of(1, 3),
            9, Set.of(2, 4));

    public int knightDialer(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 10;
        }

        Map<String, Integer> memo = new HashMap<>();
        int result = 0;

        for (int i = 0; i < 10; i++) {
            result = (result + knightDialerHelper(i, n - 1, memo)) % MOD;
        }

        return result;
    }

    private int knightDialerHelper(int cur, int n, Map<String, Integer> memo) {
        if (n == 0) {
            return 1;
        }

        String key = cur + "_" + n;
        Integer memoCount = memo.get(key);
        if (memoCount != null) {
            return memoCount;
        }

        int count = 0;
        for (int jump : graph.get(cur)) {
            count = (count + knightDialerHelper(jump, n - 1, memo)) % MOD;
        }

        memo.put(key, count);
        return count;
    }
}
