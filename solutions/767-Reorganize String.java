// LeetCode Question URL: https://leetcode.com/problems/reorganize-string/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Same as 358-Rearrange String k Distance Apart (LeetCode)
 * (https://leetcode.com/problems/rearrange-string-k-distance-apart/)
 *
 * Find count of all characters. Use Priority Queue to add the most recurring,
 * then second most recurring one by one in each window of size 2.
 *
 * Time Complexity: O(N + M log M + N log M) = O(Time to populate map + Time to
 * populate PQ + Time to generate whole string, each char is added and removed
 * once)
 *
 * Space Complexity: O(M + M + N) = O(N) = O(Map Size + PQ Size + SB size)
 *
 * N = Length of input string. M = Distinct chars in input string, bounded by N.
 */
class Solution {
    public String reorganizeString(String s) {
        if (s == null) {
            return "";
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        int maxCount = 1;
        char maxCountChar = s.charAt(0);
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int count = countMap.getOrDefault(c, 0) + 1;
            countMap.put(c, count);
            if (count > maxCount) {
                maxCountChar = c;
                maxCount = count;
            }
        }

        if (maxCount > (len + 1) / 2) {
            return "";
        }

        char[] result = new char[len];
        int idx = 0;

        while (maxCount-- > 0) {
            result[idx] = maxCountChar;
            idx += 2;
        }
        countMap.remove(maxCountChar);

        for (char c : countMap.keySet()) {
            int count = countMap.get(c);
            while (count-- > 0) {
                if (idx >= len) {
                    idx = 1;
                }
                result[idx] = c;
                idx += 2;
            }
        }

        return new String(result);
    }
}

/**
 * DO NOT CODE THIS IN INTERVIEW
 */
class Solution2 {
    public String reorganizeString(String s) {
        return rearrangeString(s, 2);
    }

    public String rearrangeString(String s, int k) {
        if (s == null || s.length() == 0 || k < 0) {
            return "";
        }

        int len = s.length();
        if (len == 1 || k <= 1) {
            return s;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        int maxCount = 1;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int count = countMap.getOrDefault(c, 0);
            if (count != 0) {
                count++;
                countMap.put(c, count);
                maxCount = Math.max(maxCount, count);
            } else {
                countMap.put(c, 1);
            }
        }
        if (maxCount > (len + k - 1) / k) {
            return "";
        }

        int uniqueChars = countMap.size();
        if (uniqueChars < k) {
            return uniqueChars == len ? s : "";
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(uniqueChars,
                (a, b) -> (a.getValue().equals(b.getValue()) ? a.getKey().compareTo(b.getKey())
                        : b.getValue().compareTo(a.getValue())));
        pq.addAll(countMap.entrySet());

        StringBuilder result = new StringBuilder();

        while (!pq.isEmpty()) {
            List<Map.Entry<Character, Integer>> tempList = new ArrayList<>();
            int window = k;
            while (window > 0 && !pq.isEmpty()) {
                Map.Entry<Character, Integer> entry = pq.poll();
                result.append(entry.getKey());
                int count = entry.getValue();
                if (count > 1) {
                    entry.setValue(count - 1);
                    tempList.add(entry);
                }
                window--;
            }
            pq.addAll(tempList);
            if (pq.isEmpty()) {
                break;
            }
            if (window != 0) {
                return "";
            }
        }

        return result.toString();
    }
}
