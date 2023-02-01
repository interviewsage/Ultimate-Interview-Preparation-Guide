// LeetCode Question URL: https://leetcode.com/problems/sliding-window-median/
// LeetCode Discuss URL: https://leetcode.com/problems/sliding-window-median/discuss/1507981/Java-or-TC:-O(N*logK)-or-SC:-(K)-or-Optimized-sliding-window-using-TreeSet

import java.util.*;

/**
 * Using TreeSet. (Here time complexity is most optimized)
 *
 * Refer:
 * https://leetcode.com/problems/sliding-window-median/discuss/96346/Java-using-two-Tree-Sets-O(n-logk)/538502
 *
 * Very similar to https://leetcode.com/problems/find-median-from-data-stream/
 *
 * <pre>
 * Add Elements = O(5*Log(K/2) * N)
 * Remove Elements = O(4*Log(K/2) * (N-K)) ==> TreeSet.remove() in JAVA is O(log K)
 * Get Median = O(2*Log(K/2) * (N-K+1))
 * Total Time Complexity: O(log(K/2) * (11*N - 6*K + 2)) = O((N-K) * log(K))
 * </pre>
 *
 * Space Complexity: O(K)
 *
 * N = Length of nums array. K = Input k.
 */
class Solution1 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        int len = nums.length;
        if (len <= 1 || k == 1) {
            double[] result = new double[len];
            for (int i = 0; i < len; i++) {
                result[i] = (double) nums[i];
            }
            return result;
            // return Arrays.stream(nums).asDoubleStream().toArray();
        }
        if (len < k) {
            Arrays.sort(nums);
            double[] result = new double[1];
            if (len % 2 != 0) {
                result[0] = (double) nums[len / 2];
            } else {
                result[0] = (nums[len / 2 - 1] + nums[len / 2]) / 2.0;
            }
        }

        double[] result = new double[len - k + 1];
        Comparator<Integer> comparator = (a,
                b) -> (nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b));
        TreeSet<Integer> smallNums = new TreeSet<>(comparator.reversed());
        TreeSet<Integer> largeNums = new TreeSet<>(comparator);

        for (int i = 0; i < len; i++) {
            if (i >= k) {
                removeElement(smallNums, largeNums, i - k);
            }
            addElement(smallNums, largeNums, i);
            if (i >= k - 1) {
                result[i - k + 1] = getMedian(smallNums, largeNums, nums);
            }
        }

        return result;
    }

    private void addElement(TreeSet<Integer> smallNums, TreeSet<Integer> largeNums, int idx) {
        smallNums.add(idx);
        largeNums.add(smallNums.pollFirst());
        if (smallNums.size() < largeNums.size()) {
            smallNums.add(largeNums.pollFirst());
        }
    }

    private void removeElement(TreeSet<Integer> smallNums, TreeSet<Integer> largeNums, int idx) {
        if (largeNums.contains(idx)) {
            largeNums.remove(idx);
            if (smallNums.size() == largeNums.size() + 2) {
                largeNums.add(smallNums.pollFirst());
            }
        } else {
            smallNums.remove(idx);
            if (smallNums.size() < largeNums.size()) {
                smallNums.add(largeNums.pollFirst());
            }
        }
    }

    private double getMedian(TreeSet<Integer> smallNums, TreeSet<Integer> largeNums, int[] nums) {
        if (smallNums.size() == largeNums.size()) {
            return ((double) nums[smallNums.first()] + nums[largeNums.first()]) / 2;
        } else {
            return (double) nums[smallNums.first()];
        }
    }
}

/**
 * Using Priority Queue. (Here time complexity is not optimized)
 *
 * Very similar to https://leetcode.com/problems/find-median-from-data-stream/
 *
 * <pre>
 * Time Complexity: O((N-K)*K + N*log K).
 * Add Elements = O(N*Log K)
 * Remove Elements = O((N-K)*K) ==> PQ.remove() in JAVA is O(K)
 * </pre>
 *
 * Space Complexity: O(K)
 *
 * N = Length of nums array. K = Input k.
 */
class Solution2 {
    public double[] medianSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = nums.length;
        double[] result = new double[len - k + 1];
        if (k == 1) {
            for (int i = 0; i < len; i++) {
                result[i] = (double) nums[i];
            }
            return result;
            // return Arrays.stream(nums).asDoubleStream().toArray();
        }

        // MaxHeap
        PriorityQueue<Integer> smallNums = new PriorityQueue<>(Collections.reverseOrder());
        // Min Heap
        PriorityQueue<Integer> largeNums = new PriorityQueue<>();

        for (int i = 0; i < len; i++) {
            if (i >= k) {
                removeElement(smallNums, largeNums, nums[i - k]);
            }
            addElement(smallNums, largeNums, nums[i]);
            if (i >= k - 1) {
                result[i - (k - 1)] = getMedian(smallNums, largeNums);
            }
        }

        return result;
    }

    private void addElement(PriorityQueue<Integer> smallNums, PriorityQueue<Integer> largeNums, int n) {
        smallNums.offer(n);
        largeNums.offer(smallNums.poll());
        if (smallNums.size() < largeNums.size()) {
            smallNums.offer(largeNums.poll());
        }
    }

    private void removeElement(PriorityQueue<Integer> smallNums, PriorityQueue<Integer> largeNums, int n) {
        if (n >= largeNums.peek()) {
            largeNums.remove(n);
            if (smallNums.size() == largeNums.size() + 2) {
                largeNums.offer(smallNums.poll());
            }
        } else {
            smallNums.remove(n);
            if (smallNums.size() < largeNums.size()) {
                smallNums.offer(largeNums.poll());
            }
        }
    }

    private double getMedian(PriorityQueue<Integer> smallNums, PriorityQueue<Integer> largeNums) {
        if (smallNums.size() == largeNums.size()) {
            return ((double) smallNums.peek() + largeNums.peek()) / 2.0;
        }
        return smallNums.peek();
    }
}
