// LeetCode Question URL: https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Sort.
 *
 * <pre>
 * Refer:
 * 1) https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/discuss/1445186/EASY-C++-solution-with-great-explanation-and-comments-(nlogn)-sorting
 * 2) Approach 1 - https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/solution/
 * 3) Approach 1 - https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/discuss/1452696/O(n)
 * </pre>
 *
 * Time Complexity: O(N + N * Log N)
 *
 * Space Complexity: O(Space Taken by Sort) = O(N)
 *
 * N = Number of properties.
 */
class Solution1 {
    public int numberOfWeakCharacters(int[][] properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Input properties array is null");
        }

        int numProps = properties.length;
        if (numProps <= 1) {
            return 0;
        }

        Arrays.sort(properties, (a, b) -> (a[0] != b[0] ? a[0] - b[0] : b[1] - a[1]));
        int maxDefenseSoFar = properties[numProps - 1][1];
        int result = 0;

        for (int i = numProps - 2; i >= 0; i--) {
            if (properties[i][1] < maxDefenseSoFar) {
                result++;
            } else {
                maxDefenseSoFar = Math.max(maxDefenseSoFar, properties[i][1]);
            }
        }

        return result;
    }
}

/**
 * Greedy Approach.
 *
 * <pre>
 * Refer:
 * 1) Approach 2 - https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/solution/
 * 1) Approach 2 - https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/discuss/1452696/O(n)
 * </pre>
 *
 * Time Complexity: O(N + K)
 *
 * Space Complexity: O(K)
 *
 * N = Number of properties.
 * K = Max Attack Value in the input array.
 */
class Solution2 {
    public int numberOfWeakCharacters(int[][] properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Input properties array is null");
        }

        int numProps = properties.length;
        if (numProps <= 1) {
            return 0;
        }

        // Find the maximum attack value
        int maxAttack = 0;
        for (int[] p : properties) {
            maxAttack = Math.max(maxAttack, p[0]);
        }

        int[] maxDefense = new int[maxAttack + 2];
        // Store the maximum defense for an attack value
        for (int[] p : properties) {
            maxDefense[p[0]] = Math.max(maxDefense[p[0]], p[1]);
        }

        // Store the maximum defense for attack greater than or equal to a value
        for (int i = maxAttack - 1; i >= 0; i--) {
            maxDefense[i] = Math.max(maxDefense[i], maxDefense[i + 1]);
        }

        int result = 0;
        for (int[] p : properties) {
            // If their is a greater defense for properties with greater attack
            if (p[1] < maxDefense[p[0] + 1]) {
                result++;
            }
        }

        return result;
    }
}

/**
 * Optimized Greedy Approach.
 *
 * <pre>
 * Refer:
 * 1) Approach 2 - https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/solution/
 * 1) Approach 2 - https://leetcode.com/problems/the-number-of-weak-characters-in-the-game/discuss/1452696/O(n)
 * </pre>
 *
 * Time Complexity: O(N + min(MaxAttackVal, MaxDefenseVal))
 *
 * Space Complexity: O(K)
 *
 * N = Number of properties.
 */
class Solution3 {
    public int numberOfWeakCharacters(int[][] properties) {
        if (properties == null) {
            throw new IllegalArgumentException("Input properties array is null");
        }

        int numProps = properties.length;
        if (numProps <= 1) {
            return 0;
        }

        int maxAttack = 0;
        int maxDefense = 0;
        for (int[] p : properties) {
            maxAttack = Math.max(maxAttack, p[0]);
            maxDefense = Math.max(maxDefense, p[1]);
        }

        if (maxAttack <= maxDefense) {
            return numberOfWeakCharactersHelper(properties, maxAttack, 0);
        } else {
            return numberOfWeakCharactersHelper(properties, maxDefense, 1);
        }
    }

    private int numberOfWeakCharactersHelper(int[][] properties, int maxVal, int pIdx) {
        int[] maxArr = new int[maxVal + 2];
        for (int[] p : properties) {
            maxArr[p[pIdx]] = Math.max(maxArr[p[pIdx]], p[1 - pIdx]);
        }

        for (int i = maxVal - 1; i >= 0; i--) {
            maxArr[i] = Math.max(maxArr[i], maxArr[i + 1]);
        }

        int res = 0;
        for (int[] p : properties) {
            if (p[1 - pIdx] < maxArr[p[pIdx] + 1]) {
                res++;
            }
        }

        return res;
    }
}
