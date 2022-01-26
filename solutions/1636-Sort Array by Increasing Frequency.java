// LeetCode Question URL: https://leetcode.com/problems/sort-array-by-increasing-frequency/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Bucket Sort
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create countMap
 * 2. O(M + M*logM) --> To create buckets map
 * 3. O(N+N) --> To populate the result array.
 * 4. Total Time Complexity: O(3*N + M + M*logM) = O(N + M + M*logM)
 *
 * Space Complexity:
 * 1. O(2*M) --> Count Map
 * 2. O(2*M) --> Buckets Map
 * 3. Total Space Complexity: O(4*M) = O(M)
 * </pre>
 *
 * N = Length of input array. M = Unique numbers.
 */
class Solution1 {
    public int[] frequencySort(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return Arrays.copyOf(nums, len);
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        Map<Integer, Set<Integer>> buckets = new HashMap<>();
        int minCount = len;
        for (int num : countMap.keySet()) {
            int count = countMap.get(num);
            minCount = Math.min(minCount, count);
            buckets.putIfAbsent(count, new TreeSet<>(Collections.reverseOrder()));
            buckets.get(count).add(num);
        }

        int[] result = new int[len];
        int resIdx = 0;
        for (int i = minCount; resIdx < len; i++) {
            Set<Integer> bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            for (int num : bucket) {
                for (int j = 0; j < i; j++) {
                    result[resIdx++] = num;
                }
            }
        }

        return result;
    }
}

/**
 * Using PriorityQueue
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create countMap
 * 2. O(M*logM) --> To populate PriorityQueue
 * 3. O(M*logM + N) --> To poll and populate the result array.
 * 4. Total Time Complexity: O(2*N + 2*M*logM) = O(N + M*logM)
 *
 * Space Complexity:
 * 1. O(2*M) --> Count Map
 * 2. O(M) --> PriorityQueue
 * 3. Total Space Complexity: O(3*M) = O(M)
 * </pre>
 *
 * N = Length of input array. M = Unique numbers.
 */
class Solution2 {
    public int[] frequencySort(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return Arrays.copyOf(nums, len);
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(
                (a, b) -> (a.getValue() != b.getValue() ? a.getValue() - b.getValue() : b.getKey() - a.getKey()));
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            pq.offer(entry);
        }

        int[] result = new int[len];
        int i = 0;
        while (!pq.isEmpty()) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            int num = entry.getKey();
            int count = entry.getValue();
            for (int j = 0; j < count; j++) {
                result[i++] = num;
            }
        }

        return result;
    }
}

/**
 * Using Map + List + Sorting
 *
 * <pre>
 * Time Complexity:
 * 1. O(N) --> To create countMap + List
 * 2. O(N*logN) --> To sort
 * 3. O(N) --> To populate the result array.
 * 4. Total Time Complexity: O(2*N + N*logN) = O(N + N*logN)
 *
 * Space Complexity:
 * 1. O(2*M) --> Count Map
 * 2. O(N) --> List
 * 3. O(N) --> Sort
 * 3. Total Space Complexity: O(2*M + 2*N) = O(M + N)
 * </pre>
 *
 * N = Length of input array. M = Unique numbers.
 */
class Solution3 {
    public int[] frequencySort(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = nums.length;
        if (len <= 1) {
            return Arrays.copyOf(nums, len);
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            list.add(n);
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        Collections.sort(list,
                (a, b) -> (countMap.get(a) != countMap.get(b) ? countMap.get(a) - countMap.get(b) : b - a));

        int[] result = new int[len];
        int i = 0;
        for (int n : list) {
            result[i++] = n;
        }

        return result;
    }
}
