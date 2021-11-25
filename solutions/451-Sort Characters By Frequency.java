// LeetCode Question URL: https://leetcode.com/problems/sort-characters-by-frequency/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Bucket Sort
 *
 * Time Complexity: O(4 * N) = O(N)
 *
 * Space Complexity: O(5 * N). = O(N)
 *
 * N = Length of the input string.
 */
class Solution1 {
    public String frequencySort(String s) {
        if (s == null || s.length() <= 2) {
            return s;
        }

        int len = s.length();

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        Map<Integer, List<Character>> buckets = new HashMap<>();
        int maxCount = 0;
        for (char c : countMap.keySet()) {
            int count = countMap.get(c);
            buckets.putIfAbsent(count, new ArrayList<>());
            buckets.get(count).add(c);
            maxCount = Math.max(maxCount, count);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = maxCount; sb.length() < len; i--) {
            List<Character> bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            for (char c : bucket) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}

/**
 * Using Bucket Sort (Characters with same frequency should appear in same order
 * in output)
 *
 * Time Complexity: O(4 * N) = O(N)
 *
 * Space Complexity: O(5 * N). = O(N)
 *
 * N = Length of the input string.
 */
class SolutionFollowUp {
    public String frequencySort(String s) {
        if (s == null || s.length() <= 2) {
            return s;
        }

        int len = s.length();

        Map<Character, Integer> countMap = new LinkedHashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        Map<Integer, List<Character>> buckets = new HashMap<>();
        int maxCount = 0;
        for (char c : countMap.keySet()) {
            int count = countMap.get(c);
            buckets.putIfAbsent(count, new ArrayList<>());
            buckets.get(count).add(c);
            maxCount = Math.max(maxCount, count);
        }

        StringBuilder sb = new StringBuilder();
        for (int i = maxCount; sb.length() < len; i--) {
            List<Character> bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            for (char c : bucket) {
                for (int j = 0; j < i; j++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}

/**
 * First count the occupance of each character and sort using PriorityQueue
 *
 * Time Complexity: O(N + M log M)
 *
 * Space Complexity: O(M + N). M = Size of HashMap & Priority queue. N = Size of
 * string builder.
 *
 * N = Length of the input string. M = Number of unique characters in s.
 */
class Solution2 {
    public String frequencySort(String s) {
        if (s == null) {
            return "";
        }
        if (s.length() <= 1) {
            return s;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        PriorityQueue<Map.Entry<Character, Integer>> queue = new PriorityQueue<>(
                (a, b) -> (b.getValue() - a.getValue()));

        for (Map.Entry<Character, Integer> e : map.entrySet()) {
            queue.offer(e);
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            Map.Entry<Character, Integer> cur = queue.poll();
            for (int i = 0; i < cur.getValue(); i++) {
                sb.append(cur.getKey());
            }
        }

        return sb.toString();
    }
}
