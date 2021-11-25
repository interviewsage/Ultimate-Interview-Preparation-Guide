// LeetCode Question URL: https://leetcode.com/problems/longest-word-in-dictionary
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Trie + DFS
 *
 * <pre>
 * Time Complexity:
 * 1. O(N * L) --> To Add all words to Trie
 * 2. O(All Trie Nodes) --> To Explore all TrieNodes. This is bounded by O(N * L)
 * 3. O(N * L) --> This is for comparing the strings, if the len is same. (This is very upper bound. Actually it will be much lower)
 * 4. Total Time Complexity = O(N * L)
 *
 * Space Complexity:
 * 1. O(N * L) --> Space taken by Trie
 * 2. O(max word len) --> Recursion Depth.
 * 3. Total Space Complexity = O(N*L)
 * </pre>
 *
 * N = Number of words in input array. L = Average length of each word.
 */
class Solution1 {

    public class TrieNode {
        Map<Character, TrieNode> children;
        String word;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0].length() == 1 ? words[0] : "";
        }

        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.word = word;
        }

        String[] result = { "" };
        longestWordDfsHelper(root, result);
        return result[0];
    }

    private void longestWordDfsHelper(TrieNode cur, String[] result) {
        if (cur.word != null) {
            if (cur.word.length() > result[0].length()
                    || (cur.word.length() == result[0].length() && cur.word.compareTo(result[0]) < 0)) {
                result[0] = cur.word;
            }
        }

        for (TrieNode child : cur.children.values()) {
            if (child.word == null) {
                continue;
            }
            longestWordDfsHelper(child, result);
        }
    }
}

/**
 * Using Trie + Sorting
 *
 * <pre>
 * Time Complexity:
 * 1. O(N log N) --> To sort the words by their length.
 * 2. O(N * L) --> To Add & Explore in Trie
 * 3. O(N * L) --> This is for comparing the strings, if the len is same. (This is very upper bound. Actually it will be much lower)
 * 4. Total Time Complexity = O(N*logN + N*L)
 *
 * Space Complexity:
 * 1. O(N * L) --> Space taken by Trie
 * 2. O(N) --> Space taken by sorting.
 * 3. Total Space Complexity = O(N*L)
 * </pre>
 *
 * N = Number of words in input array. L = Average length of each word.
 */
class Solution2 {

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        Arrays.sort(words, (a, b) -> (a.length() - b.length()));

        if (words[0].length() > 1) {
            return "";
        }
        if (words.length == 1) {
            return words[0];
        }

        String result = "";
        TrieNode root = new TrieNode();

        for (int idx = 0; idx < words.length; idx++) {
            String word = words[idx];
            int wordLen = word.length();
            if (wordLen == 0) {
                continue;
            }
            if (idx > 0 && wordLen - words[idx - 1].length() > 1) {
                break;
            }

            // Only Valid words are added in Trie
            TrieNode cur = root;
            boolean isValid = true;
            for (int i = 0; i < wordLen; i++) {
                char c = word.charAt(i);
                TrieNode child = cur.children.get(c);
                if (i < wordLen - 1 && (child == null || !child.isEnd)) {
                    isValid = false;
                    break;
                }
                if (child == null) {
                    child = new TrieNode();
                    cur.children.put(c, child);
                }
                cur = child;
            }
            if (!isValid) {
                continue;
            }
            cur.isEnd = true;

            if (result.length() < wordLen || word.compareTo(result) < 0) {
                result = word;
            }
        }

        return result;
    }
}

class Solution3 {

    public class TrieNode {
        TrieNode[] children;
        String word;

        public TrieNode() {
            children = new TrieNode[26];
        }
    }

    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0].length() == 1 ? words[0] : "";
        }

        TrieNode root = new TrieNode();

        for (String word : words) {
            TrieNode cur = root;
            for (int i = 0; i < word.length(); i++) {
                int c = word.charAt(i) - 'a';
                if (cur.children[c] == null) {
                    cur.children[c] = new TrieNode();
                }
                cur = cur.children[c];
            }
            cur.word = word;
        }

        String[] result = { "" };
        longestWordDfsHelper(root, result);
        return result[0];
    }

    private void longestWordDfsHelper(TrieNode cur, String[] result) {
        if (cur.word != null && cur.word.length() > result[0].length()) {
            result[0] = cur.word;
        }

        for (TrieNode child : cur.children) {
            if (child == null || child.word == null) {
                continue;
            }
            longestWordDfsHelper(child, result);
        }
    }
}

class Solution4 {

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        public TrieNode() {
            children = new HashMap<>();
        }

        public void addWord(String word) {
            TrieNode cur = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.isEnd = true;
        }

        public boolean isValidWord(String word) {
            TrieNode cur = this;
            boolean isValid = true;
            for (int i = 0; i < word.length(); i++) {
                cur = cur.children.get(word.charAt(i));
                if (cur == null || !cur.isEnd) {
                    isValid = false;
                    break;
                }
            }
            return isValid;
        }
    }

    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        if (words.length == 1) {
            return words[0].length() == 1 ? words[0] : "";
        }

        TrieNode root = new TrieNode();
        int minLen = Integer.MAX_VALUE;
        int maxLen = 0;
        Map<Integer, Set<String>> buckets = new HashMap<>();

        for (String word : words) {
            int wordLen = word.length();
            if (wordLen == 0) {
                continue;
            }
            minLen = Math.min(minLen, wordLen);
            maxLen = Math.max(maxLen, wordLen);
            buckets.putIfAbsent(wordLen, new HashSet<>());
            buckets.get(wordLen).add(word);

            root.addWord(word);
        }

        if (minLen != 1) {
            return "";
        }

        for (int i = 2; i < maxLen; i++) {
            if (!buckets.containsKey(i)) {
                maxLen = i - 1;
                break;
            }
        }

        String result = "";
        for (int l = maxLen; l >= 1; l--) {
            for (String word : buckets.get(l)) {
                if (result.length() > 0 && word.compareTo(result) > 0) {
                    continue;
                }
                if (root.isValidWord(word)) {
                    result = word;
                }
            }
            if (result.length() > 0) {
                break;
            }
        }

        return result;
    }
}

/*
 * Using Trie. Save all the words in Trie. Now for each word check if there is
 * an end after each character.
 *
 * Time Complexity = O(n * l)
 *
 * Space Complexity = O(n * l)
 *
 * n = Number of words. l = Average length of the words.
 */
class Solution5 {
    class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEnd;

        TrieNode() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }

        TrieNode root = new TrieNode();
        for (String word : words) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                if (!cur.map.containsKey(c)) {
                    cur.map.put(c, new TrieNode());
                }
                cur = cur.map.get(c);
            }
            cur.isEnd = true;
        }

        String result = "";

        for (String word : words) {
            if (result.length() > word.length() || (result.length() == word.length() && result.compareTo(word) < 0)) {
                continue;
            }
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                cur = cur.map.get(c);
                if (!cur.isEnd) {
                    break;
                }
            }
            if (cur.isEnd) {
                result = word;
            }
        }

        return result;
    }
}
