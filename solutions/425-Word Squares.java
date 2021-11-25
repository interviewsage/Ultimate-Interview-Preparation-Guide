// LeetCode Question URL: https://leetcode.com/problems/word-squares/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Prefix Search + Backtracking
 *
 * <pre>
 * Let us assume:
 * --> N = Number of words
 * --> L = Length of each word
 *
 * Time Complexity Analysis:
 * T(L) = N*T(L-1) + O(N)
 * T(L-1) = N*T(L-2) + O(N+1)
 * T(L-2) = N*T(L-3) + O(N+2)
 * ...
 * T(2) = N*T(1) + O(N+L-2)
 * T(1) = N*T(0) + O(N+L-1)
 * T(0) = O(L)

 * T(L) = N + (N+1)*N + (N+2)*N^2 + (N+3)*N^3 + ... + (N+L-2)*N^(L-2) + (N+L-1)*N^(L-1) + L*N^L
 *      = (N + N^2 + N^3 + ... + N^(L-1) + N^L) + (N + 2*N^2 + 3*N^3 + ... + (L-2)*N^(L-2) + (L-1)*N^(L-1) + L*N^L)
 *      = N * (N^L - 1) / (N - 1) + (i -> 1 to L) ∑ (i * N^i)
 *
 * Space Complexity Analysis:
 * 1. O(N*L + N*L) --> Space Taken by Trie (Characters & Word Indexes)
 * 2. O(L + L) --> Space taken by TempList + Recursion Stack
 * Total Space Complexity: O(N*L + L)
 * </pre>
 */
class Solution {
    public class TrieNode {
        HashMap<Character, TrieNode> children;
        HashSet<Integer> wordIndexes;

        public TrieNode() {
            children = new HashMap<>();
            wordIndexes = new HashSet<>();
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }

        TrieNode root = buildTrie(words);
        List<String> tempList = new ArrayList<>();
        for (String word : words) {
            tempList.add(word);
            wordSquaresHelper(result, words, root, tempList);
            tempList.remove(tempList.size() - 1);
        }
        return result;
    }

    private void wordSquaresHelper(List<List<String>> result, String[] words, TrieNode root, List<String> tempList) {
        if (tempList.size() == words[0].length()) {
            result.add(new ArrayList<>(tempList));
            return;
        }
        for (int i : getMatchingWordIndexes(root, tempList)) {
            tempList.add(words[i]);
            wordSquaresHelper(result, words, root, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }

    private HashSet<Integer> getMatchingWordIndexes(TrieNode root, List<String> wordList) {
        int col = wordList.size();
        TrieNode cur = root;

        for (int i = 0; i < col; i++) {
            char c = wordList.get(i).charAt(col);
            cur = cur.children.get(c);
            if (cur == null) {
                return new HashSet<>();
            }
        }

        return cur.wordIndexes;
    }

    private TrieNode buildTrie(String[] words) {
        TrieNode root = new TrieNode();

        for (int i = 0; i < words.length; i++) {
            TrieNode cur = root;
            String word = words[i];
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
                cur.wordIndexes.add(i);
            }
        }

        return root;
    }
}
