// LeetCode Question URL: https://leetcode.com/problems/word-ladder-ii/
// LeetCode Discuss URL:

import java.util.*;

/**
 * BFS + DFS
 *
 * Time Complexity: O(N * min(26*L, N-1) + N + N * min(26*L, N-1)) = O (N * 26 *
 * L) assuming N is very big.
 *
 * Space Complexity: O(N * min(26*L, N-1) * L + N + N*L) = O(N * 26 * L^2)
 * assuming N is very big
 *
 * N = Number of words in the wordList. L = Length of each word.
 */
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0) {
            return result;
        }

        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return result;
        }

        Map<String, Set<String>> graph = new HashMap<>();
        buildGraph(graph, wordSet, beginWord, endWord);

        dfsHelper(result, graph, beginWord, endWord, new ArrayList<>());

        return result;
    }

    private void buildGraph(Map<String, Set<String>> graph, Set<String> wordSet, String beginWord, String endWord) {
        Map<String, Integer> distMap = new HashMap<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        distMap.put(beginWord, 0);
        int level = 0;
        boolean foundEnd = false;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            level++;

            for (int i = 0; i < levelSize; i++) {
                String curWord = queue.poll();
                Set<String> children = new HashSet<>();
                graph.put(curWord, children);
                char[] w = curWord.toCharArray();

                for (int j = 0; j < w.length; j++) {
                    char old = w[j];
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == old) {
                            continue;
                        }
                        w[j] = c;
                        String newWord = new String(w);
                        if (!wordSet.contains(newWord)) {
                            continue;
                        }

                        Integer distFromBeginWord = distMap.get(newWord);
                        if (distFromBeginWord == null) {
                            distMap.put(newWord, level);
                            queue.offer(newWord);
                            children.add(newWord);
                        } else if (distFromBeginWord.intValue() == distMap.get(curWord) + 1) {
                            children.add(newWord);
                        }

                        if (endWord.equals(newWord)) {
                            foundEnd = true;
                            break;
                        }
                    }
                    w[j] = old;
                }
            }
            if (foundEnd) {
                break;
            }
        }
    }

    private void dfsHelper(List<List<String>> result, Map<String, Set<String>> graph, String curWord, String target,
            List<String> tempList) {
        tempList.add(curWord);

        if (curWord.equals(target)) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        if (!graph.containsKey(curWord)) {
            return;
        }
        for (String w : graph.get(curWord)) {
            dfsHelper(result, graph, w, target, tempList);
            tempList.remove(tempList.size() - 1);
        }
    }
}