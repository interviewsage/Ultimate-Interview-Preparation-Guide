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
        if (order == null || s == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int sLen = s.length();
        int orderLen = order.length();
        if (sLen <= 1 || orderLen <= 1) {
            return s;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < sLen; i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder(sLen);
        for (int i = 0; i < orderLen; i++) {
            char c = order.charAt(i);
            Integer count = countMap.remove(c);
            if (count == null) {
                continue;
            }
            while (count-- > 0) {
                sb.append(c);
            }
        }

        for (Character c : countMap.keySet()) {
            int count = countMap.get(c);
            while (count-- > 0) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
