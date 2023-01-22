// LeetCode Question URL: https://leetcode.com/problems/sort-characters-by-frequency/
// LeetCode Discuss URL:

import java.util.*;

/**
 * First count the occurrence of each character and sort chars seen by count
 *
 * Time Complexity: O(N + C + C log C + N + N) = O(N + ClogC) = O(N) if charset
 * is a constant.
 * = CountMap + CharList + Sort On Unique Chars + Build SB + To String
 *
 * Space Complexity: O(C + C + C + C + N) = O(N).
 * = CountMap + CharList + Sort + SB
 *
 * N = Length of the input string. C = Number of unique characters in s.
 */
class Solution1 {
    public String frequencySort(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        List<Character> charList = new ArrayList<>(countMap.keySet());
        Collections.sort(charList, (a, b) -> (countMap.get(b) - countMap.get(a)));

        StringBuilder sb = new StringBuilder();
        for (char c : charList) {
            int count = countMap.get(c);
            while (count-- > 0) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}

/**
 * Follow-Up: Characters with same frequency should appear in same order
 * in output
 *
 * First count the occurrence of each character and sort chars seen by count
 *
 * Time Complexity: O(N + C + C log C + N + N) = O(N + ClogC) = O(N) if charset
 * is a constant.
 * = CountMap + CharList + Sort On Unique Chars + Build SB + To String
 *
 * Space Complexity: O(C + C + C + C + N) = O(N).
 * = CountMap + CharList + Sort + SB
 *
 * N = Length of the input string. C = Number of unique characters in s.
 */
class Solution1FollowUp {
    public String frequencySort(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        Map<Character, Integer> firstIdxMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            firstIdxMap.putIfAbsent(c, i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        List<Character> charList = new ArrayList<>(countMap.keySet());
        Collections.sort(charList, (a, b) -> (countMap.get(a) != countMap.get(b) ? countMap.get(b) - countMap.get(a)
                : firstIdxMap.get(a) - firstIdxMap.get(b)));

        StringBuilder sb = new StringBuilder();
        for (char c : charList) {
            int count = countMap.get(c);
            while (count-- > 0) {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}

/**
 * Using Bucket Sort
 *
 * Time Complexity: O(N + C + N + N + N) = O(N + C) = O(N)
 * = CountMap + Buckets + MaxCount Loop + Add All Chars + To String
 *
 * Space Complexity: O(C + C + N) = O(N)
 * = CountMap + Buckets + SB
 *
 * N = Length of the input string. C = Number of unique characters in s.
 */
class Solution2 {
    public String frequencySort(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

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
                for (int k = 0; k < i; k++) {
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
 * Time Complexity: O(N + C + N + N + N) = O(N + C) = O(N)
 * = CountMap + Buckets + MaxCount Loop + Add All Chars + To String
 *
 * Space Complexity: O(C + C + N) = O(N)
 * = CountMap + Buckets + SB
 *
 * N = Length of the input string. C = Number of unique characters in s.
 */
class Solution2FollowUp {
    public String frequencySort(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        if (len <= 1) {
            return s;
        }

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
                for (int k = 0; k < i; k++) {
                    sb.append(c);
                }
            }
        }

        return sb.toString();
    }
}
