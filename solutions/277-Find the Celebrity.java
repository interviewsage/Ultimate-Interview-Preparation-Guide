// LeetCode Question URL: https://leetcode.com/problems/find-the-celebrity/
// LeetCode Discuss URL:

/**
 * The knows API is defined in the parent class Relation.
 *
 * boolean knows(int a, int b);
 */

/**
 * 2 Pass Solution
 *
 * Refer:
 * https://leetcode.com/problems/find-the-celebrity/discuss/71227/Java-Solution.-Two-Pass/73409
 *
 * The first pass is to pick out the candidate. If candidate knows i, then
 * switch candidate. The second pass is to check whether the candidate is real.
 *
 *
 * If knows(A, B) = true --> Then we can say for sure A IS NOT the candidate. As
 * A knows someone.
 *
 * If knows(A, B) = false --> Then we can say for sure B IS NOT the candidate.
 * As B is not known by at least one person.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
public class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n <= 1) {
            return n == 0 ? -1 : 0;
        }

        int candidate = 0;

        /**
         * This helps to find a candidate. If Kth person was candidate, then candidate
         * does not know anyone from (K+1, N-1), but may or may not know anyone from (0,
         * K-1).
         */
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                candidate = i;
            }
        }

        /**
         * Following if can be split in 2 sections.
         *
         * For i < candidate we can verify both the conditions.
         *
         * For i > candidates we can only verify knows(i, candidate). This is because we
         * have already verified the other condition in previous pass.
         */
        for (int i = 0; i < n; i++) {
            if (i < candidate && (knows(candidate, i) || !knows(i, candidate))) {
                return -1;
            }
            if (i > candidate && !knows(i, candidate)) {
                return -1;
            }
        }

        return candidate;
    }
}

/**
 * Refer: https://leetcode.com/problems/find-the-celebrity/solution/535824
 *
 * Saving one duplicate call by saving previous candidate.
 */
public class Solution2 extends Relation {
    public int findCelebrity(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n <= 1) {
            return n == 0 ? -1 : 0;
        }

        int candidate = 0;
        int prev = -1;
        /**
         * This helps to find a candidate. If Kth person was candidate, then candidate
         * does not know anyone from (K+1, N-1), but may or may not know anyone from (0,
         * K-1).
         */
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                prev = candidate;
                candidate = i;
            }
        }

        /**
         * Following if can be split in 2 sections.
         *
         * For i < candidate we can verify both the conditions.
         *
         * For i > candidates we can only verify knows(i, candidate). This is because we
         * have already verified the other condition in previous pass.
         */
        for (int i = 0; i < n; i++) {
            if (i < candidate && (knows(candidate, i) || (i != prev && !knows(i, candidate)))) {
                return -1;
            }
            if (i > candidate && !knows(i, candidate)) {
                return -1;
            }
        }

        return candidate;
    }
}
