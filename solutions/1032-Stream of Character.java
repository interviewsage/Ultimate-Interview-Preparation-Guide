// LeetCode Question URL: https://leetcode.com/problems/stream-of-characters/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Trie and Deque
 *
 * <pre>
 * Time Complexity:
 * 1. StreamChecker() Constructor --> O(N*L) to build trie.
 * 2. query() --> O(M) to search the suffix in trie
 *
 * Space Complexity:
 * 1. O(N*L) --> for Trie
 * 2. O(M) --> for window of incoming stream of characters
 * Total Space Complexity: O(N*L + M)
 * </pre>
 *
 * Alternate Solution:
 * https://leetcode.com/problems/stream-of-characters/discuss/278769/Java-Trie-Solution/288292
 *
 * N = Number of words. L = Average length of each word. M = Max Length of a
 * word in input array.
 */
class StreamChecker {

    public class TrieNode {
        Map<Character, TrieNode> children;
        boolean isEnd;

        public TrieNode() {
            children = new HashMap<>();
        }

        public void addWordReverse(String word) {
            TrieNode cur = this;
            for (int i = word.length() - 1; i >= 0; i--) {
                char c = word.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
            }
            cur.isEnd = true;
        }
    }

    TrieNode root;
    int maxLen;
    Deque<Character> window;

    public StreamChecker(String[] words) {
        root = new TrieNode();
        maxLen = 0;
        window = new ArrayDeque<>();

        if (words == null || words.length == 0) {
            return;
        }

        for (String word : words) {
            maxLen = Math.max(maxLen, word.length());
            root.addWordReverse(word);
        }
    }

    public boolean query(char letter) {
        window.addFirst(letter);
        if (window.size() > maxLen) {
            window.pollLast();
        }

        TrieNode cur = root;
        Iterator<Character> itr = window.iterator();
        while (itr.hasNext()) {
            char c = itr.next();
            cur = cur.children.get(c);
            if (cur == null) {
                return false;
            }
            if (cur.isEnd) {
                return true;
            }
        }

        return false;
    }
}

// Your StreamChecker object will be instantiated and called as such:
// StreamChecker obj = new StreamChecker(words);
// boolean param_1 = obj.query(letter);
