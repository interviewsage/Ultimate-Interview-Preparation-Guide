// LeetCode Question URL: https://leetcode.com/problems/ransom-note/
// LeetCode Discuss URL:

import java.util.HashMap;

/**
 * Time Complexity: O(R + M)
 *
 * Space Complexity: O(Unique chars in magazine). Can be maximum O(26) = O(1)
 *
 * R = Length of ransomNote string. M = Length of magazine string.
 */
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote == null || magazine == null || ransomNote.length() > magazine.length()) {
            return false;
        }

        int ransomNoteLen = ransomNote.length();
        if (ransomNoteLen == 0) {
            return true;
        }

        HashMap<Character, Integer> charCountMap = new HashMap<>();
        for (int i = 0; i < magazine.length(); i++) {
            char c = magazine.charAt(i);
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < ransomNoteLen; i++) {
            char c = ransomNote.charAt(i);

            if (!charCountMap.containsKey(c)) {
                return false;
            }

            int curCount = charCountMap.get(c);
            if (curCount == 1) {
                charCountMap.remove(c);
            } else {
                charCountMap.put(c, curCount - 1);
            }
        }

        return true;
    }
}
