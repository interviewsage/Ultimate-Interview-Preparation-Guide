// LeetCode Question URL: https://leetcode.com/problems/word-break-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1. Complexity Analysis of Approach 1: https://leetcode.com/problems/word-break-ii/solution/
 * 2. https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS/215095
 * 3. https://stackoverflow.com/questions/39519667/whats-the-time-complexity-of-this-recursive-algorithm
 * 4. https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS
 *
 * Time Complexity:
 * 1. O(W) --> To find minimum and maximum length of words in the dictionary. Also add all words to the set.
 * 2. O(N^3) --> To check if Word Break is possible or not. Use Word Break I to check this.
 * 3. O(V+E) = O(N + N*(N+1)/2) --> Time take for Tree Traversal (DFS). Total number of nodes is N. Total number of edges visited will be (i = 1 -> N) ∑ (i)
 * 4. O(N * 2^N) = Total number of potential combinations will be 2^N and the length of each combination will be N.
 *
 * Space Complexity:
 * 1. O(W) --> Word Set.
 * 2. O(N) --> DP array in word break 1.
 * 3. O(N) --> HashMap Keys
 * 4. O(N*2^N) --> HashMap values
 * 5. O(N) --> Recursion Depth
 * </pre>
 */
class Solution1 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return result;
        }

        Set<String> wordSet = new HashSet<>();
        int minLen = Integer.MAX_VALUE;
        int maxLen = 0;
        for (String word : wordDict) {
            int wLen = word.length();
            minLen = Math.min(minLen, wLen);
            maxLen = Math.max(maxLen, wLen);
            wordSet.add(word);
        }

        int sLen = s.length();
        // Checking if word break is possible or not
        if (sLen < minLen || !wordBreak1(s, wordSet)) {
            return result;
        }

        Map<Integer, List<List<String>>> memoMap = new HashMap<>();
        memoMap.put(0, List.of(Collections.emptyList()));

        List<List<String>> rawResult = wordBreakHelper(s, sLen, wordSet, minLen, maxLen, memoMap);
        for (List<String> r : rawResult) {
            result.add(String.join(" ", r));
        }
        return result;
    }

    private List<List<String>> wordBreakHelper(String s, int sLen, Set<String> wordSet, int minLen, int maxLen,
            Map<Integer, List<List<String>>> memoMap) {
        List<List<String>> result = memoMap.get(sLen);
        if (result != null) {
            return result;
        }

        result = new ArrayList<>();
        if (sLen < minLen) {
            return result;
        }

        for (int i = minLen; i <= Math.min(maxLen, sLen); i++) {
            // Start Index of the word to be checked in the dictionary
            int idx = sLen - i;
            String word = s.substring(idx, sLen);
            if (!wordSet.contains(word)) {
                continue;
            }

            List<List<String>> temp = wordBreakHelper(s, idx, wordSet, minLen, maxLen, memoMap);
            for (List<String> t : temp) {
                List<String> res = new ArrayList<>(t);
                res.add(word);
                result.add(res);
            }
        }

        memoMap.put(sLen, result);
        return result;
    }

    public boolean wordBreak1(String s, Set<String> wordSet) {
        if (wordSet.contains(s)) {
            return true;
        }

        int len = s.length();
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;

        for (int i = 1; i <= len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }
}

class Solution2 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return result;
        }

        List<List<String>> rawResult = wordBreakHelper(s, new HashSet<>(wordDict), new HashMap<>());
        for (List<String> r : rawResult) {
            result.add(String.join(" ", r));
        }
        return result;
    }

    private List<List<String>> wordBreakHelper(String s, Set<String> wordSet, Map<String, List<List<String>>> memoMap) {
        List<List<String>> result = memoMap.get(s);
        if (result != null) {
            return result;
        }

        result = new ArrayList<>();
        if (wordSet.contains(s)) {
            result.add(Arrays.asList(s));
        }

        for (int i = 1; i < s.length(); i++) {
            String word = s.substring(i);
            if (!wordSet.contains(word)) {
                continue;
            }

            List<List<String>> temp = wordBreakHelper(s.substring(0, i), wordSet, memoMap);
            for (List<String> t : temp) {
                List<String> res = new ArrayList<>(t);
                res.add(word);
                result.add(res);
            }
        }

        memoMap.put(s, result);
        return result;
    }
}

/**
 * Refer:
 * https://leetcode.com/problems/word-break-ii/discuss/44167/My-concise-JAVA-solution-based-on-memorized-DFS/43441
 *
 * Time & Space Complexity: O(N * 2^N)
 *
 * Refer:
 * https://www.dropbox.com/s/yzf6mzwxqxckt0p/LC%20-%20140%20-%20Word%20Break%20II.pdf?dl=0
 *
 * N = Length of the input string.
 */
class Solution3 {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return new ArrayList<>();
        }

        Map<String, List<String>> memoMap = new HashMap<>();
        memoMap.put("", Arrays.asList(""));

        return wordBreakHelper(s, new HashSet<>(wordDict), memoMap);
    }

    private List<String> wordBreakHelper(String s, Set<String> wordSet, Map<String, List<String>> memoMap) {
        List<String> result = memoMap.get(s);
        if (result != null) {
            return result;
        }

        result = new ArrayList<>();

        for (int i = 0; i < s.length(); i++) {
            String word = s.substring(i);
            if (!wordSet.contains(word)) {
                continue;
            }

            List<String> temp = wordBreakHelper(s.substring(0, i), wordSet, memoMap);
            for (String t : temp) {
                if (t.length() > 0) {
                    result.add(new StringBuilder(t).append(" ").append(word).toString());
                } else {
                    result.add(word);
                }
            }
        }

        memoMap.put(s, result);
        return result;
    }
}
