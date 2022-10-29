// LeetCode Question URL: https://leetcode.com/problems/sort-array-by-increasing-frequency/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Bucket Sort
 *
 * <pre>
 * Time Complexity:
 * 1. O(N + 2*M*logk) --> To create countMap & buckets
 * 2. O(N+N) --> To populate the result array.
 * Total Time Complexity: O(3*N + 2*M*logk) = O(N + M*logk)
 *
 * Space Complexity:
 * 1. O(2*M) --> Count Map
 * 2. O(N+M) --> Buckets Map
 * 3. Total Space Complexity: O(N + 3*M) = O(N+M)
 * </pre>
 *
 * N = Length of input array. M = Unique numbers.
 * k = Max No. of numbers having same count. (1 <= k <= M)
 */
class Solution1 {
    public int[] frequencySort(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("input array is null");
        }
        int len = nums.length;
        if (len <= 1) {
            return nums;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Set<Integer>> buckets = new HashMap<>();
        int minCount = len;
        int maxCount = 1;
        for (int n : nums) {
            int preCount = countMap.getOrDefault(n, 0);
            countMap.put(n, preCount + 1);
            if (preCount > 0) {
                Set<Integer> preBucket = buckets.get(preCount);
                // if (preBucket.size() == 1) {
                // buckets.remove(preCount);
                // if (minCount == preCount) {
                // minCount = preCount+1;
                // }
                // } else {
                // preBucket.remove(n);
                // }
                if (preBucket.size() == 1 && minCount == preCount) {
                    minCount = preCount + 1;
                }
                preBucket.remove(n);
            }
            buckets.putIfAbsent(preCount + 1, new TreeSet<>(Collections.reverseOrder()));
            buckets.get(preCount + 1).add(n);
            minCount = Math.min(minCount, preCount + 1);
            maxCount = Math.max(maxCount, preCount + 1);
        }

        int[] result = new int[len];
        int idx = 0;
        for (int i = minCount; i <= maxCount; i++) {
            Set<Integer> bucket = buckets.get(i);
            if (bucket == null) {
                continue;
            }
            for (int n : bucket) {
                for (int k = 0; k < i; k++) {
                    result[idx++] = n;
                }
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
class Solution2 {
    public int[] frequencySort(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("input array is null");
        }
        int len = nums.length;
        if (len <= 1) {
            return nums;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for (int n : nums) {
            result.add(n);
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }

        Collections.sort(result,
                (a, b) -> (countMap.get(a) != countMap.get(b) ? countMap.get(a).intValue() - countMap.get(b).intValue()
                        : b - a));

        return result.stream().mapToInt(n -> n).toArray();
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
