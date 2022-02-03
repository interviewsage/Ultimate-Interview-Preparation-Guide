// LeetCode Question URL: https://leetcode.com/problems/queue-reconstruction-by-height/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Also check alternate solution using Binary Index Tree:
 * https://leetcode.com/problems/queue-reconstruction-by-height/discuss/427157/Three-different-C++-solutions.-from-O(n2)-to-O(nlogn).-faster-than-99.
 */

/**
 * Greedy Solution
 *
 * Time Complexity: O(N*logN + N^2)
 *
 * Space Complexity: O(Space used by Sorting) = O(N) or O(logN)
 */
class Solution {
    public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length <= 1) {
            return people;
        }

        Arrays.sort(people, (a, b) -> (a[0] != b[0] ? Integer.compare(b[0], a[0]) : Integer.compare(a[1], b[1])));

        for (int i = 1; i < people.length; i++) {
            if (people[i - 1][0] == people[i][0] || people[i][1] == i) {
                continue;
            }

            int[] cur = people[i];
            for (int j = i; j > cur[1]; j--) {
                people[j] = people[j - 1];
            }
            people[cur[1]] = cur;
        }

        return people;
    }
}
