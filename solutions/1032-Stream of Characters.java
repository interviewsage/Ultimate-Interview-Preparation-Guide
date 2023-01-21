// LeetCode Question URL: https://leetcode.com/problems/stream-of-characters/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Trie
 *
 * <pre>
 * Time Complexity:
 * StreamChecker --> O(N * AVG_WL)
 * query() --> O(min(MAX_WL, SL))
 *
 * Space Complexity: O(N*AVG_WL + min(MAX_WL, SL))
 *
 * N = Number of words
 * AVG_WL = Average Word Len
 * MAX_WL = Max Word Len
 * SL = Stream Length
 * </pre>
 */
class StreamChecker {

    public class TrieNode {
        Map<Character, TrieNode> map;
        boolean isEnd;

        public TrieNode() {
            map = new HashMap<>();
            isEnd = false;
        }
    }

    TrieNode root;
    Deque<Character> stream;
    int maxWordLen;

    public StreamChecker(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Input words array is null");
        }

        root = new TrieNode();
        stream = new ArrayDeque<>();
        maxWordLen = 0;

        for (String w : words) {
            if (w != null) {
                int len = w.length();
                maxWordLen = Math.max(maxWordLen, len);
                TrieNode cur = root;
                for (int i = len - 1; i >= 0; i--) {
                    char c = w.charAt(i);
                    TrieNode next = cur.map.get(c);
                    if (next == null) {
                        next = new TrieNode();
                        cur.map.put(c, next);
                    }
                    cur = next;
                }
                cur.isEnd = true;
            }
        }
    }

    public boolean query(char letter) {
        if (stream.size() == maxWordLen) {
            stream.pollLast();
        }
        stream.offerFirst(letter);

        TrieNode cur = root;
        for (char c : stream) {
            cur = cur.map.get(c);
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

/**
 * Your StreamChecker object will be instantiated and called as such:
 * StreamChecker obj = new StreamChecker(words);
 * boolean param_1 = obj.query(letter);
 */
