// LeetCode Question URL: https://leetcode.com/problems/rearrange-string-k-distance-apart/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer for an alternate good solution:
 * https://leetcode.com/problems/rearrange-string-k-distance-apart/discuss/83193/Java-15ms-Solution-with-Two-auxiliary-array.-O(N)-time.
 *
 * Find count of all characters. Use Priority Queue to add the most recurring,
 * then second most recurring one by one in each window.
 *
 * Time Complexity: O(N + M log M + N log M) = O(N log M) = O(Time to populate
 * map + Time to populate PQ + Time to generate whole string, each char is added
 * and removed once)
 *
 * Space Complexity: O(M + M + N) = O(N + M) = O(Map Size + PQ Size + SB size)
 *
 * N = Length of input string. M = Distinct chars in input string, bounded by
 * 26.
 */
class Solution {
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

/**
 * DO NOT CODE THIS IN INTERVIEW
 */
class Solution2 {
    public String rearrangeString(String s, int k) {
        if (s == null) {
            return "";
        }
        if (s.length() < 2 || k < 2) {
            return s;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a, b) -> (a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey() - b.getKey()));

        queue.addAll(map.entrySet());
        StringBuilder sb = new StringBuilder();
        Queue<Map.Entry<Character, Integer>> waitList = new LinkedList<>();

        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> cur = queue.poll();
            sb.append(cur.getKey());
            cur.setValue(cur.getValue() - 1);
            waitList.offer(cur);

            if (waitList.size() < k) {
                continue;
            }

            Map.Entry<Character, Integer> front = waitList.poll();
            if (front.getValue() > 0) {
                queue.offer(front);
            }
        }

        return sb.length() == s.length() ? sb.toString() : "";
    }
}