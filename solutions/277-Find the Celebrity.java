// LeetCode Question URL: https://leetcode.com/problems/find-the-celebrity/
// LeetCode Discuss URL:

/**
 * The knows API is defined in the parent class Relation.
 *
 * boolean knows(int a, int b);
 */

/**
 * 2 Pass Solution. Saving one duplicate call by saving previous candidate.
 *
 * Refer:
 * https://leetcode.com/problems/find-the-celebrity/discuss/71227/Java-Solution.-Two-Pass/73409
 *
 * Refer: https://leetcode.com/problems/find-the-celebrity/solution/535824
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
class Solution extends Relation {
    public int findCelebrity(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input number of people is invalid");
        }

        int candidate = 0;
        int prevCandidate = -1;
        /**
         * This helps to find a candidate. If Kth person was candidate, then candidate
         * does not know anyone from (K+1, N-1), but may or may not know anyone from (0,
         * K-1).
         */
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                prevCandidate = candidate;
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != candidate && i != prevCandidate && !knows(i, candidate)) {
                return -1;
            }
            if (i < candidate && knows(candidate, i)) {
                return -1;
            }
        }

        return candidate;
    }
}

class Solution2 extends Relation {
    public int findCelebrity(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Input number of people is invalid");
        }

        int candidate = 0;
        int prevCandidate = -1;
        /**
         * This helps to find a candidate. If Kth person was candidate, then candidate
         * does not know anyone from (K+1, N-1), but may or may not know anyone from (0,
         * K-1).
         */
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i)) {
                prevCandidate = candidate;
                candidate = i;
            }
        }

        for (int i = 0; i < n; i++) {
            if (i != candidate && i != prevCandidate && !knows(i, candidate)) {
                return -1;
            }
            if (i < candidate && knows(candidate, i)) {
                return -1;
            }
        }

        return candidate;
    }
}
