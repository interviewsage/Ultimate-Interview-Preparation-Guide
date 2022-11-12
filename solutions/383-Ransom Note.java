// LeetCode Question URL: https://leetcode.com/problems/ransom-note/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(R + M)
 *
 * Space Complexity: O(Unique chars in magazine). Can be maximum O(26) = O(1)
 *
 * R = Length of ransomNote string. M = Length of magazine string.
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int rLen = ransomNote.length();
        int mLen = magazine.length();
        if (mLen < rLen) {
            return false;
        }
        if (rLen == 0) {
            return true;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < rLen; i++) {
            char c = ransomNote.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < mLen; i++) {
            char c = magazine.charAt(i);
            Integer count = countMap.get(c);
            if (count == null) {
                continue;
            }
            if (count == 1) {
                if (countMap.size() == 1) {
                    return true;
                }
                countMap.remove(c);
            } else {
                countMap.put(c, count - 1);
            }
        }

        return false;
    }
}
