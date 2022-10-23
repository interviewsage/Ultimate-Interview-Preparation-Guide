// LeetCode Question URL: https://leetcode.com/problems/count-items-matching-a-rule/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N)
 *
 * Space Complexity; O(1)
 */
class Solution {

    private static final Map<String, Integer> RULE_MAP = Map.of(
            "type", 0,
            "color", 1,
            "name", 2);

    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        if (items == null || ruleKey == null || ruleValue == null) {
            throw new IllegalArgumentException("Input is null");
        }

        Integer ruleIdx = RULE_MAP.get(ruleKey);
        if (ruleIdx == null) {
            throw new IllegalArgumentException("Input ruleKey");
        }

        int count = 0;
        for (List<String> item : items) {
            if (ruleValue.equals(item.get(ruleIdx))) {
                count++;
            }
        }

        return count;
    }
}
