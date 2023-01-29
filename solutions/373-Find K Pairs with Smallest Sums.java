// LeetCode Question URL: https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
 * 2. https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84550/Slow-1-liner-to-Fast-solutions
 *
 * Time Complexity:
 * 1. Populating PQ = min(len1, k) * log(min(len1, k))
 * 2. While loop to find k results = 2*k * log(min(len1, k))
 * Total time complexity = (2*k + min(len1, k)) * log(min(len1, k))
 *
 * Space Complexity: O(min(len1, k))
 * </pre>
 */
class Solution1 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 == 0 || len2 == 0 || k == 0) {
            return result;
        }

        if (k == 1) {
            result.add(List.of(nums1[0], nums2[0]));
            return result;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(k,
                (a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < Math.min(len1, k); i++) {
            pq.offer(new int[] { i, 0 });
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            result.add(List.of(nums1[cur[0]], nums2[cur[1]]));

            if (cur[1] < len2 - 1) {
                cur[1]++;
                pq.offer(cur);
            }
        }

        return result;
    }
}

/**
 * <pre>
 * Refer:
 * 1. https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84551/simple-Java-O(KlogK)-solution-with-explanation
 * 2. https://leetcode.com/problems/find-k-pairs-with-smallest-sums/discuss/84550/Slow-1-liner-to-Fast-solutions
 *
 * Time Complexity:
 * 1. Populating PQ = min(len1, len2, k) * log(min(len1, len2, k))
 * 2. While loop to find k results = 2*k * log(min(len1, len2, k))
 * Total time complexity = (2*k + min(len1, len2, k)) * log(min(len1, len2, k))
 *
 * Space Complexity: O(min(len1, len2, k))
 * </pre>
 */
class Solution2 {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1 == null || nums2 == null || k < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        List<List<Integer>> result = new ArrayList<>();
        int len1 = nums1.length;
        int len2 = nums2.length;

        if (len1 == 0 || len2 == 0 || k == 0) {
            return result;
        }

        if (k == 1) {
            result.add(List.of(nums1[0], nums2[0]));
            return result;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(k,
                (a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));

        boolean isLen1SmallOrEqual = len1 <= len2;

        int len = isLen1SmallOrEqual ? Math.min(len1, k) : Math.min(len2, k);
        for (int i = 0; i < len; i++) {
            pq.offer(isLen1SmallOrEqual ? new int[] { i, 0 } : new int[] { 0, i });
        }

        while (k-- > 0 && !pq.isEmpty()) {
            int[] cur = pq.poll();
            result.add(List.of(nums1[cur[0]], nums2[cur[1]]));

            if (isLen1SmallOrEqual && cur[1] < len2 - 1) {
                cur[1]++;
                pq.offer(cur);
            } else if (!isLen1SmallOrEqual && cur[0] < len1 - 1) {
                cur[0]++;
                pq.offer(cur);
            }
        }

        return result;
    }
}
