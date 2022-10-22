// LeetCode Question URL: https://leetcode.com/problems/jewels-and-stones/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Time & Space Complexity:
 *      - S == 0 || J == 0 ---> TC: O(1), SC: O(1)
 *      - S == 1           ---> TC: O(J), SC: O(1)
 *      - All other cases  ---> TC: O(J + S), SC: O(J)
 * </pre>
 *
 * J = Number of characters in J. S = Number of characters in S.
 */
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        if (jewels == null || stones == null) {
            throw new IllegalArgumentException("Input strings are null");
        }

        int jLen = jewels.length();
        int sLen = stones.length();
        if (jLen == 0 || sLen == 0) {
            return 0;
        }
        if (sLen == 1) {
            return jewels.contains(stones) ? 1 : 0;
        }

        Set<Character> jewelsSet = new HashSet<>();
        for (int i = 0; i < jLen; i++) {
            jewelsSet.add(jewels.charAt(i));
        }

        int jewelCount = 0;
        for (int i = 0; i < sLen; i++) {
            if (jewelsSet.contains(stones.charAt(i))) {
                jewelCount++;
            }
        }

        return jewelCount;
    }
}

/**
 * Time Complexity: O(J * S)
 *
 * Space Complexity: O(S)
 *
 * J = Number of characters in J. S = Number of characters in S.
 */
class Solution2 {
    public int numJewelsInStones(String J, String S) {
        if (J == null || S == null || J.length() == 0 || S.length() == 0) {
            return 0;
        }

        String resStr = S.replaceAll("[^" + J + "]", "");
        return resStr.length();
    }
}
