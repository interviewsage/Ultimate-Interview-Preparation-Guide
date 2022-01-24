// LeetCode Question URL: https://leetcode.com/problems/friends-of-appropriate-ages/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 *      Y <= X/2 + 7
 * ==>  Y <  X/2 + 7 + ε (Removing equality by adding ε)
 *
 * AND -Y < -X
 *
 * ADD the above equations
 * ==>  0 < -X/2 + 7 + ε
 * ==> X < 14 + 2ε
 * ==> X <= 14 (Since X is integer)
 *
 * Thus X cannot send friend request if its less than or equal to 14.
 * </pre>
 */

/**
 * Group people with same age and then check for conditions.
 *
 * Refer:
 * https://leetcode.com/problems/friends-of-appropriate-ages/discuss/127341/10ms-concise-Java-solution-O(n)-time-and-O(1)-space
 *
 * Time Complexity: O(N + maxAge). Here maxAge = 120. Thus TC = O(N + 120) =
 * O(N)
 *
 * Space Complexity: O(N' + maxAge) = O(120) = O(1)
 *
 * N = Length of ages array. maxAge = Maximum age found in the input array. N' =
 * Number of unique ages.
 */
class Solution {
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length <= 1) {
            return 0;
        }

        int[] ageBuckets = new int[121];
        int minAge = 120;
        int maxAge = 0;
        for (int a : ages) {
            if (a >= 15) {
                ageBuckets[a]++;
                minAge = Math.min(minAge, a);
                maxAge = Math.max(maxAge, a);
            }
        }

        // No one is >=15
        if (maxAge == 0) {
            return 0;
        }

        int[] agePrefixSum = new int[maxAge + 1];
        int result = 0;
        for (int a = minAge; a <= maxAge; a++) {
            agePrefixSum[a] += ageBuckets[a] + agePrefixSum[a - 1];
            result += ageBuckets[a] * (agePrefixSum[a] - agePrefixSum[a / 2 + 7] - 1);
        }

        return result;
    }
}

class Solution1 {
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 2) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        int maxAge = 0;
        int minAge = Integer.MAX_VALUE;

        for (int a : ages) {
            map.put(a, map.getOrDefault(a, 0) + 1);
            maxAge = Math.max(maxAge, a);
            minAge = Math.min(minAge, a);
        }

        /**
         * (B <= A/2 + 7) & (B > A) ==> (A <= A/2+7) ==> A <= 14 .. for these ages of A
         * its not valid.
         * ε
         *
         * Thus A cannot send friend request if its less than or equal to 14.
         */
        if (maxAge < 15) {
            return 0;
        }
        minAge = Math.max(minAge, 15);

        int[] prefixSum = new int[maxAge + 1];
        int result = 0;

        for (int i = minAge; i <= maxAge; i++) {
            int count = map.getOrDefault(i, 0);
            prefixSum[i] = prefixSum[i - 1] + count;

            /**
             * prefixSum[i] = Count of people less than or equal to age i
             *
             * prefixSum[i / 2 + 7] = Count of people that do not match `age[B] <= 0.5 *
             * age[A] + 7`
             *
             * -1 for Self
             */
            result += (prefixSum[i] - prefixSum[i / 2 + 7] - 1) * count;
        }

        return result;
    }
}

/**
 * Group people with same age and then check for conditions.
 *
 * Time Complexity: O(N + N'^2).
 *
 * Space Complexity: O(N')
 *
 * N = Length of ages array. N' = Number of unique ages.
 */
class Solution2 {
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 2) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int a : ages) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        int result = 0;

        for (int ageA : map.keySet()) {
            if (ageA < 15) {
                continue;
            }

            int upper = ageA;
            int lower = ageA / 2 + 7;

            for (int ageB : map.keySet()) {
                if (ageB > upper || ageB <= lower) {
                    continue;
                }

                int cntA = map.get(ageA);
                int cntB = map.get(ageB);

                if (ageA == ageB) {
                    result += cntA * (cntA - 1);
                } else {
                    result += cntA * cntB;
                }
            }
        }

        return result;
    }
}

/**
 * Group people with same age and then check for conditions.
 *
 * Refer:
 * https://leetcode.com/problems/friends-of-appropriate-ages/discuss/127341/10ms-concise-Java-solution-O(n)-time-and-O(1)-space
 *
 * Time Complexity: O(N + maxAge). Here maxAge = 120. Thus TC = O(N + 120) =
 * O(N)
 *
 * Space Complexity: O(maxAge) = O(120) = O(1)
 *
 * N = Length of ages array. maxAge = Maximum age found in the input array.
 */
class Solution3 {
    public int numFriendRequests(int[] ages) {
        if (ages == null || ages.length < 2) {
            return 0;
        }

        int[] ageBuckets = new int[121];
        int[] prefixSum = new int[121];
        int result = 0;

        for (int a : ages) {
            ageBuckets[a]++;
        }

        /**
         * (B <= A/2 + 7) & (B > A) ==> (A <= A/2+7) ==> A <= 14 .. for these ages of A
         * its not valid.
         *
         * Thus A cannot send friend request if its less than or equal to 14.
         */
        for (int i = 15; i <= 120; i++) {
            prefixSum[i] = prefixSum[i - 1];
            if (ageBuckets[i] > 0) {
                prefixSum[i] += ageBuckets[i];
                /**
                 * prefixSum[i] = Count of people less than or equal to age i
                 *
                 * prefixSum[i / 2 + 7] = Count of people that do not match `age[B] <= 0.5 *
                 * age[A] + 7`
                 *
                 * -1 for Self
                 */
                result += (prefixSum[i] - prefixSum[i / 2 + 7] - 1) * ageBuckets[i];
            }
        }

        return result;
    }
}