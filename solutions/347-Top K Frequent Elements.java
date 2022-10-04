// LeetCode Question URL: https://leetcode.com/problems/top-k-frequent-elements/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Bucket Sort
 *
 * Time Complexity: O(N + UniqueN + RangeCount(bounded by N) + K) = O(N)
 *
 * Space Complexity: O(2*UniqueN + 2*UniqueN) = O(N)
 *
 * <pre>
 * N = Length of input array.
 * K = input top k value found. K is also bounded by UniqueN.
 * UniqueN is bounded by N
 * </pre>
 */
class Solution1 {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        if (len == 0 || k == 0) {
            return new int[0];
        }
        if (len == 1) {
            return nums;
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        if (countMap.size() <= k) {
            int[] result = new int[countMap.size()];
            int i = 0;
            for (int num : countMap.keySet()) {
                result[i++] = num;
            }
            return result;
        }

        Map<Integer, List<Integer>> buckets = new HashMap<>();
        int maxCount = 0;
        for (int num : countMap.keySet()) {
            int count = countMap.get(num);
            buckets.putIfAbsent(count, new ArrayList<>());
            buckets.get(count).add(num);
            maxCount = Math.max(maxCount, count);
        }

        int[] result = new int[k];
        while (k > 0) {
            List<Integer> bucket = buckets.get(maxCount--);
            if (bucket == null) {
                continue;
            }
            for (int i = 0; i < bucket.size() && k > 0; i++) {
                result[--k] = bucket.get(i);
            }
        }

        return result;
    }
}

/**
 * Using Quick Select
 *
 * Time Complexity: O(N + UniqueN + UniqueN + K) --> In Best/Average Case. In
 * worst case it will be O(N + UniqueN + (UniqueN)^2 + K)
 *
 * Space Complexity: O(2*UniqueN + UniqueN) = O(N)
 *
 * N = Length of input array. K = input top k value.
 */
class Solution2 {
    private static final Random random = new Random();

    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            return Arrays.copyOf(nums, 1);
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }
        if (countMap.size() <= k) {
            int[] result = new int[countMap.size()];
            int i = 0;
            for (int n : countMap.keySet()) {
                result[i++] = n;
            }
            return result;
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(countMap.entrySet());
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            int mid = quickSelectPartition(list, start, end);
            if (mid == k - 1) {
                break;
            }
            if (mid < k - 1) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }

    private int quickSelectPartition(List<Map.Entry<Integer, Integer>> list, int start, int end) {
        swap(list, start, start + random.nextInt(end - start + 1));
        int insertPos = start;
        int startVal = list.get(start).getValue();

        for (int i = start + 1; i <= end; i++) {
            if (startVal < list.get(i).getValue()) {
                insertPos++;
                swap(list, insertPos, i);
            }
        }
        swap(list, insertPos, start);
        return insertPos;
    }

    private void swap(List<Map.Entry<Integer, Integer>> list, int x, int y) {
        if (x == y) {
            return;
        }
        Map.Entry<Integer, Integer> t = list.get(x);
        list.set(x, list.get(y));
        list.set(y, t);
    }
}

/**
 * Find the frequency of each num in nums array and use Priority Queue to find
 * top k elements.
 *
 * Time Complexity: O(N + 2*UniqueN*logK)
 *
 * Space Complexity: O(2*UniqueN + K).
 *
 * N = Length of input array. K = input top k value.
 */
class Solution3 {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }
        if (nums.length == 1) {
            return Arrays.copyOf(nums, 1);
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        for (int n : nums) {
            countMap.put(n, countMap.getOrDefault(n, 0) + 1);
        }
        if (countMap.size() <= k) {
            int[] result = new int[countMap.size()];
            int i = 0;
            for (int n : countMap.keySet()) {
                result[i++] = n;
            }
            return result;
        }

        PriorityQueue<Map.Entry<Integer, Integer>> queue = new PriorityQueue<>((a, b) -> (a.getValue() - b.getValue()));

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            queue.offer(entry);
            if (queue.size() > k) {
                queue.poll();
            }
        }

        int i = 0;
        int[] result = new int[queue.size()];
        while (!queue.isEmpty()) {
            result[i++] = queue.poll().getKey();
        }
        return result;
    }
}
