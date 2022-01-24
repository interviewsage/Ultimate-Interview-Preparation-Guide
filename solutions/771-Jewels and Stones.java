// LeetCode Question URL: https://leetcode.com/problems/jewels-and-stones/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(J + S)
 *
 * Space Complexity: O(J)
 *
 * J = Number of characters in J. S = Number of characters in S.
 */
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        if (jewels == null || jewels.length() == 0 || stones == null || stones.length() == 0) {
            return 0;
        }

        Set<Character> jewelSet = new HashSet<>();
        for (int i = 0; i < jewels.length(); i++) {
            jewelSet.add(jewels.charAt(i));
        }

        int jewelCount = 0;
        for (int i = 0; i < stones.length(); i++) {
            if (jewelSet.contains(stones.charAt(i))) {
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
