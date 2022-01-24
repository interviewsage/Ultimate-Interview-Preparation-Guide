// LeetCode Question URL: https://leetcode.com/problems/custom-sort-string/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer this for alternate approach using a custom comparator:
 * https://leetcode.com/problems/custom-sort-string/discuss/116615/JavaPython-3-one-Java-10-liner-Python-6-liner-and-2-liner-solutions-w-comment/522710
 */

/**
 * Count chars in S and write the result.
 *
 * Time Complexity: O(3*S + O) = O(S + O)
 *
 * Space Complexity: O(2*S) = O(S)
 *
 * S = Length of string S. O = Length of order string.
 */
class Solution {
    public String customSortString(String order, String s) {
        if (order == null || order.length() <= 1 || s == null || s.length() <= 1) {
            return s;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < order.length(); i++) {
            char c = order.charAt(i);
            Integer count = countMap.remove(c);
            if (count == null) {
                continue;
            }
            while (count-- > 0) {
                sb.append(c);
            }
        }

        for (Map.Entry<Character, Integer> entry : countMap.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            while (count-- > 0) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
