// LeetCode Question URL: https://leetcode.com/problems/design-search-autocomplete-system/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Trie
 *
 * <pre>
 * N = Total Number of Sentences saved in Trie
 * L = Average length of each sentence. / Also the length of search sentence.
 *
 * Time Complexity:
 * AutocompleteSystem() Constructor --> O(N * L * log 3) = O(N * L)
 * input() --> Case 1: c != '#' --> O(1) -> Just goto next node and then just populate the result list.
 *         --> Case 2: c == '#'. New sentence has to be added --> O(L * log 3) = O(L)
 *
 * Space Complexity:
 * 1. O(N*L) --> Maximum Number of Trie Nodes. Each TrieNode will have constant space.
 * 2. O(N*L) --> A copy of each sentence will be saved in the memory (JVM)
 * 3. Total Space Complexity = O(N*L)
 * </pre>
 */
class AutocompleteSystem {

    public class TrieNode {
        HashMap<Character, TrieNode> children;
        int count;
        TreeSet<Pair<String, Integer>> hotSentences;

        TrieNode() {
            children = new HashMap<>();
            hotSentences = new TreeSet<>((a, b) -> (a.getValue() != b.getValue() ? b.getValue() - a.getValue()
                    : a.getKey().compareTo(b.getKey())));
        }

        public void addSentenceWithCount(String sentence, int count) {
            TrieNode cur = this;
            Pair<String, Integer> mapEntryWithOneLessCount = new Pair<>(sentence, count - 1);
            Pair<String, Integer> mapEntryWithCurCount = new Pair<>(sentence, count);

            for (int i = 0; i < sentence.length(); i++) {
                char c = sentence.charAt(i);
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
                cur.hotSentences.add(mapEntryWithCurCount);
                if (!cur.hotSentences.remove(mapEntryWithOneLessCount) && cur.hotSentences.size() > 3) {
                    cur.hotSentences.pollLast();
                }
            }
            cur.count = count;
        }
    }

    TrieNode root;
    TrieNode curSearchNode;
    StringBuilder sb;

    public AutocompleteSystem(String[] sentences, int[] times) {
        root = new TrieNode();
        curSearchNode = root;
        sb = new StringBuilder();

        if (sentences == null || times == null || sentences.length == 0 || sentences.length != times.length) {
            return;
        }

        for (int i = 0; i < sentences.length; i++) {
            root.addSentenceWithCount(sentences[i], times[i]);
        }
    }

    public List<String> input(char c) {
        List<String> result = new ArrayList<>();
        if (c == '#') {
            root.addSentenceWithCount(sb.toString(), 1 + (curSearchNode == null ? 0 : curSearchNode.count));
            curSearchNode = root;
            sb.setLength(0);
            return result;
        }

        sb.append(c);
        if (curSearchNode != null) {
            curSearchNode = curSearchNode.children.get(c);
        }
        if (curSearchNode == null) {
            return result;
        }
        for (Pair<String, Integer> pair : curSearchNode.hotSentences) {
            result.add(pair.getKey());
        }
        return result;
    }
}

/**
 * Alternate Solution
 *
 * Refer: https://leetcode.com/submissions/detail/590389042/
 */
class AutocompleteSystem2 {

    TrieNode root, cur;
    Map<String, Integer> fmap;
    StringBuilder sb;

    public AutocompleteSystem2(String[] sentences, int[] times) {
        root = new TrieNode();
        cur = root;
        sb = new StringBuilder();
        fmap = new HashMap<>();

        int n = sentences.length;
        for (int i = 0; i < n; i++) {
            fmap.put(sentences[i], times[i]);
            insert(sentences[i]);
        }
    }

    private void insert(String s) {
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int index = c == ' ' ? 26 : c - 'a';
            if (node.list[index] == null) {
                node.list[index] = new TrieNode();
            }
            node = node.list[index];
            update(node, s);
        }
    }

    private void update(TrieNode node, String s) {
        List<String> hots = node.hots;
        hots.remove(s); // fre will increase after calling input method.

        for (int i = 0; i < 3; i++) {
            if (i == hots.size()) {
                hots.add(s);
                return;
            }
            int pre = fmap.get(hots.get(i));
            int cur = fmap.get(s);
            if (cur > pre || (cur == pre && s.compareTo(hots.get(i)) < 0)) {
                hots.add(i, s);
                break;
            }
        }
        if (hots.size() == 4)
            hots.remove(3);
    }

    public List<String> input(char c) {
        if (c == '#') {
            String s = sb.toString();
            fmap.put(s, fmap.getOrDefault(s, 0) + 1);
            insert(s);
            sb = new StringBuilder();
            cur = root;
            return new ArrayList<>();
        }
        sb.append(c);

        if (cur != null)
            cur = cur.list[c == ' ' ? 26 : c - 'a'];
        return cur == null ? new ArrayList<>() : cur.hots;
    }
}

class TrieNode {
    TrieNode[] list;
    List<String> hots;

    public TrieNode() {
        list = new TrieNode[27];
        hots = new ArrayList<>();
    }
}

// Your AutocompleteSystem object will be instantiated and called as such:
// AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
// List<String> param_1 = obj.input(c);
