// LeetCode Question URL: https://leetcode.com/problems/count-items-matching-a-rule/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N)
 *
 * Space Complexity; O(1)
 */
class Solution {
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        if (items == null || items.size() == 0 || ruleKey == null || ruleValue == null) {
            return 0;
        }

        int idx = "type".equals(ruleKey) ? 0 : "color".equals(ruleKey) ? 1 : 2;
        int result = 0;

        for (List<String> item : items) {
            if (ruleValue.equals(item.get(idx))) {
                result++;
            }
        }

        return result;
    }
}
