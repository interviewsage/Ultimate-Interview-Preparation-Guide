// LeetCode Question URL: https://leetcode.com/problems/top-k-frequent-words/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Find the frequency of each word in words array and use Priority Queue to find
 * top k words.
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create count map
 * 2. O(2 * N * L * logK) --> Every element is added once and polled once.
 * 3. Total Time Complexity = O(N + N*L*logK)
 *
 * Space Complexity:
 * 1. O(2*N) --> count map keys + values. We will only store reference of the input strings
 * 2. O(K) --> PriorityQueue will maximum grow to size k.
 * 3. Total Space Complexity: O(N + K)
 * </pre>
 *
 * N = Length of input array. L = Average length of each word. K = Input number.
 */
class Solution1 {
    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<String> result = new LinkedList<>();
        if (words.length == 0 || k == 0) {
            return result;
        }

        Map<String, Integer> countMap = new HashMap<>();
        for (String w : words) {
            countMap.put(w, countMap.getOrDefault(w, 0) + 1);
        }
        if (countMap.size() == 1) {
            result.add(words[0]);
            return result;
        }

        // This is a min heap, thus the comparator will be opposite of what is given in
        // the question.
        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>((a,
                b) -> (a.getValue() != b.getValue() ? a.getValue() - b.getValue() : b.getKey().compareTo(a.getKey())));

        for (Map.Entry<String, Integer> e : countMap.entrySet()) {
            queue.offer(e);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        while (!queue.isEmpty()) {
            result.add(0, queue.poll().getKey());
        }

        return result;
    }
}

/**
 * Using Quick Select to find the First K Strings and then sort them using
 * custom comparator.
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create count map
 * 2. O(N) --> To create list of entry set.
 * 3. O(N*L) --> Best/Average time taken by quick select. (In worst case: O(N^2 * L))
 * 4. O(K * L * logK) --> Sort the sub list.
 * 5. O(K) --> To add the Strings to result list.
 * 6. Total Time Complexity = O(N + N*L + K*L*logK + K) = O(N*L + K*L*LogK)
 *
 * Here K = min(N, K);
 *
 * Space Complexity:
 * 1. O(2*N) --> count map keys + values. We will only store reference of the input strings
 * 2. O(N) --> For list of entry set.
 * 3. O(K) --> For sorting of sub list.
 * 4. Total Space Complexity: O(N + K) = O(N)
 *
 * Here K = min(N, K);
 * </pre>
 *
 * N = Length of input array. L = Average length of each word. K = Input number.
 */
class Solution2 {
    private static final Comparator<Map.Entry<String, Integer>> comparator = (a,
            b) -> (a.getValue() != b.getValue() ? b.getValue() - a.getValue() : a.getKey().compareTo(b.getKey()));
    private static final Random random = new Random();

    public List<String> topKFrequent(String[] words, int k) {
        if (words == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<String> result = new ArrayList<>();
        int wordLen = words.length;
        if (wordLen == 0 || k == 0) {
            return result;
        }

        Map<String, Integer> countMap = new HashMap<>();
        for (String w : words) {
            countMap.put(w, countMap.getOrDefault(w, 0) + 1);
        }
        int countMapSize = countMap.size();
        if (countMapSize == 1) {
            result.add(words[0]);
            return result;
        }

        List<Map.Entry<String, Integer>> entryList = new ArrayList<>(countMap.entrySet());

        // No need to perform quick select if we have less than k unique words.
        if (countMapSize > k) {
            int start = 0;
            int end = countMapSize - 1;

            while (start < end) {
                int partition = quickSelectPartition(entryList, start, end);
                if (partition == k - 1) {
                    break;
                }
                if (partition < k - 1) {
                    start = partition + 1;
                } else {
                    end = partition - 1;
                }
            }
        } else {
            k = countMapSize;
        }

        Collections.sort(entryList.subList(0, k), comparator);
        for (int i = 0; i < k; i++) {
            result.add(entryList.get(i).getKey());
        }

        return result;
    }

    private int quickSelectPartition(List<Map.Entry<String, Integer>> list, int start, int end) {
        swap(list, start, start + random.nextInt(end - start + 1));
        int insertPos = start;
        Map.Entry<String, Integer> startEntry = list.get(start);

        for (int i = start + 1; i <= end; i++) {
            if (comparator.compare(list.get(i), startEntry) <= 0) {
                insertPos++;
                swap(list, insertPos, i);
            }
        }

        swap(list, insertPos, start);
        return insertPos;
    }

    private void swap(List<Map.Entry<String, Integer>> list, int a, int b) {
        if (a != b) {
            Map.Entry<String, Integer> t = list.get(a);
            list.set(a, list.get(b));
            list.set(b, t);
        }
    }
}

/**
 * Bucket Sort + Trie
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create count map
 * 2. O(N + N*L) --> To create bucket map + Add all words in Trie.
 * 3. O(N + 26*N*L + K) --> To get first K words from the buckets and Tries.
 * 4. Total Time Complexity = O(3*N + 27*N*L + K) = O(N*L)
 *
 * Space Complexity:
 * 1. O(2*N) --> count map keys + values. We will only store reference of the input strings
 * 2. O(N + N*L) --> For Bucket Map + all Tries.
 * 3. O(N) --> For temp list to store the words found in the trie.
 * 4. Total Space Complexity: O(4*N + N*L) = O(N*L)
 * </pre>
 *
 * N = Length of input array. L = Average length of each word. K = Input number.
 */
class Solution3 {

    public class TrieNode {
        Map<Character, TrieNode> children;
        String word;

        TrieNode() {
            this.children = new HashMap<>();
        }
    }

    private void addWord(TrieNode root, String word) {
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);
        }
        cur.word = word;
    }

    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return result;
        }
        int len = words.length;
        if (len == 1) {
            result.add(words[0]);
            return result;
        }

        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        Map<Integer, TrieNode> buckets = new HashMap<>();
        int minCount = len;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            buckets.putIfAbsent(count, new TrieNode());
            addWord(buckets.get(count), word);
            minCount = Math.min(minCount, count);
            maxCount = Math.max(maxCount, count);
        }

        for (int i = maxCount; i >= minCount && result.size() < k; i--) {
            TrieNode bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            List<String> list = new ArrayList<>();
            getSortedWords(bucket, list);
            for (int j = 0; j < list.size() && result.size() < k; j++) {
                result.add(list.get(j));
            }
        }

        return result;
    }

    private void getSortedWords(TrieNode curNode, List<String> list) {
        if (curNode == null) {
            return;
        }
        if (curNode.word != null) {
            list.add(curNode.word);
        }
        for (char c = 'a'; c <= 'z'; c++) {
            getSortedWords(curNode.children.get(c), list);
        }
    }
}

/**
 * Bucket Sort
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create count map
 * 2. O(N) --> To create bucket map.
 * 3. O(N + N*L*logN + K) --> To get the first sorted K words from the buckets. (This is worst case if all words are in same bucket)
 * 4. Total Time Complexity = O(3*N + N*L*logN + K) = O(N*L*logN)
 *
 * Space Complexity:
 * 1. O(2*N) --> count map keys + values. We will only store reference of the input strings
 * 2. O(2*N) --> For Bucket Map
 * 3. O(N) --> For sorting a bucket.
 * 4. Total Space Complexity: O(5*N) = O(N)
 * </pre>
 *
 * N = Length of input array. L = Average length of each word. K = Input number.
 */
class Solution4 {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return result;
        }

        int len = words.length;
        if (len == 1) {
            result.add(words[0]);
            return result;
        }

        Map<String, Integer> countMap = new HashMap<>();
        for (String word : words) {
            countMap.put(word, countMap.getOrDefault(word, 0) + 1);
        }

        Map<Integer, List<String>> buckets = new HashMap<>();
        int minCount = len;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            String word = entry.getKey();
            int count = entry.getValue();
            buckets.putIfAbsent(count, new ArrayList<>());
            buckets.get(count).add(word);
            minCount = Math.min(minCount, count);
            maxCount = Math.max(maxCount, count);
        }

        for (int i = maxCount; i >= minCount && result.size() < k; i--) {
            List<String> bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            int bucketSize = bucket.size();
            if (bucketSize == 1) {
                result.add(bucket.get(0));
                continue;
            }
            Collections.sort(bucket);
            for (int j = 0; j < bucketSize && result.size() < k; j++) {
                result.add(bucket.get(j));
            }
        }

        return result;
    }
}

/**
 * Bucket Sort + TreeSet
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create count map
 * 2. O(N + N*L*logN) --> To create bucket map. (This is worst case if all words are in same bucket)
 * 3. O(N + K) --> To get the first sorted K words from the buckets.
 * 4. Total Time Complexity = O(3*N + N*L*logN + K) = O(N*L*logN)
 *
 * Space Complexity:
 * 1. O(2*N) --> count map keys + values. We will only store reference of the input strings
 * 2. O(2*N) --> For Bucket Map
 * 3. Total Space Complexity: O(4*N) = O(N)
 * </pre>
 *
 * N = Length of input array. L = Average length of each word. K = Input number.
 */
/**
 * Using Bucket Sort. Using TreeSet
 *
 * Time Complexity: O(N + N'*l*log N' + K)
 *
 * Space Complexity: O(N'*l)
 *
 * N = Length of input array. K = input k. N' = Number of unique words.
 */
class Solution5 {
    public List<String> topKFrequent(String[] words, int k) {
        ArrayList<String> result = new ArrayList<>();
        if (words == null || words.length == 0 || k <= 0) {
            return result;
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (String w : words) {
            map.put(w, map.getOrDefault(w, 0) + 1);
        }

        HashMap<Integer, TreeSet<String>> buckets = new HashMap<>();

        for (String w : map.keySet()) {
            int freq = map.get(w);
            if (!buckets.containsKey(freq)) {
                buckets.put(freq, new TreeSet<>());
            }
            buckets.get(freq).add(w);
        }

        int cnt = 0;

        for (int i = words.length; i > 0 && cnt < k; i--) {
            if (!buckets.containsKey(i)) {
                continue;
            }
            for (String w : buckets.get(i)) {
                result.add(w);
                cnt++;
                if (cnt == k) {
                    break;
                }
            }
        }

        return result;
    }
}
