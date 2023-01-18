// LeetCode Question URL: https://leetcode.com/problems/search-suggestions-system/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Trie
 *
 * <pre>
 * Time Complexity:
 * 1. Build Trie -> N * min(L, l) * (l*log3 + log3) = (Number Of Words) * Min(Search Word Len, Avg Len of Each Product) * (Trie Add + Trie Poll Last)
 * 2. Search -> L * 3
 * Total Time Complexity: O(N * min(l, L) * l + L)
 *
 * Space Complexity:
 * 1. Trie Nodes: O(N * min(L, l))
 * 2. Space of Each Node is O(1)
 * 3. Only references of Strings will be stored in TreeSet.
 * Total Space Complexity: O(N * min(L, l))
 * </pre>
 *
 * N = Number Of Words
 * L = Search Word Len
 * l = Avg Len of Each Product
 */
class Solution {

    public class TrieNode {
        Map<Character, TrieNode> map;
        TreeSet<String> suggestedProducts;

        public TrieNode() {
            map = new HashMap<>();
            suggestedProducts = new TreeSet<>();
        }
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        if (searchWord == null || products == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<String>> result = new ArrayList<>();
        int numProducts = products.length;
        int sLen = searchWord.length();
        if (numProducts == 0 || sLen == 0) {
            for (int i = 0; i < sLen; i++) {
                result.add(Collections.emptyList());
            }
            return result;
        }

        TrieNode root = buildTrie(products, sLen);

        TrieNode cur = root;
        int idx = 0;
        while (idx < sLen) {
            cur = cur.map.get(searchWord.charAt(idx));
            if (cur == null) {
                break;
            }
            result.add(new ArrayList<>(cur.suggestedProducts));
            idx++;
        }
        while (idx++ < sLen) {
            result.add(Collections.emptyList());
        }

        return result;
    }

    private TrieNode buildTrie(String[] products, int searchWordLen) {
        TrieNode root = new TrieNode();

        for (String product : products) {
            if (product == null || product.length() == 0) {
                continue;
            }
            int len = Math.min(searchWordLen, product.length());
            TrieNode cur = root;
            for (int i = 0; i < len; i++) {
                char c = product.charAt(i);
                TrieNode next = cur.map.get(c);
                if (next == null) {
                    next = new TrieNode();
                    cur.map.put(c, next);
                }
                cur = next;
                cur.suggestedProducts.add(product);
                if (cur.suggestedProducts.size() > 3) {
                    cur.suggestedProducts.pollLast();
                }
            }
        }

        return root;
    }
}

/**
 * Only is Interviewer asks for alternate solution
 *
 * Refer:
 * https://leetcode.com/problems/search-suggestions-system/discuss/436674/C++JavaPython-Sort-and-Binary-Search-the-Prefix
 *
 * Time Complexity: O(2*N*l*logN + 2*L*l*logN) = O((N + L) * l*logN)
 *
 * Space Complexity: O(N + N + N) = O(Sort + TreeMap + ArrayList) = O(N)
 *
 * N = Number Of Words
 * L = Search Word Len
 * l = Avg Len of Each Product
 */
class Solution2 {
    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        List<List<String>> res = new ArrayList<>();
        TreeMap<String, Integer> map = new TreeMap<>();
        Arrays.sort(products);
        List<String> productsList = Arrays.asList(products);

        for (int i = 0; i < products.length; i++) {
            map.put(products[i], i);
        }

        String key = "";
        for (char c : searchWord.toCharArray()) {
            key += c;
            // For below lines we can get map.entry instead of just key. This will avoid
            // second lookup in TreeMap.
            String ceiling = map.ceilingKey(key); // Returns lexicographically smallest match
            String floor = map.floorKey(key + "~"); // Returns lexicographically largest match
            if (ceiling == null || floor == null)
                break;
            res.add(productsList.subList(map.get(ceiling), Math.min(map.get(ceiling) + 3, map.get(floor) + 1)));
        }

        while (res.size() < searchWord.length())
            res.add(new ArrayList<>());
        return res;
    }
}
