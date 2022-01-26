// LeetCode Question URL: https://leetcode.com/problems/kth-largest-element-in-a-stream/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Priority Queue
 *
 * Time Complexity: KthLargest() -> O(N log K). Every operation of add() ->
 * O(log K)
 *
 * Space Complexity: O(K)
 *
 * N = Length of initial array passed to the constructor. K = input k.
 */

class KthLargest {

    PriorityQueue<Integer> pq;
    int k;

    public KthLargest(int k, int[] nums) {
        this.pq = new PriorityQueue<>();
        this.k = k;
        if (nums != null) {
            for (int n : nums) {
                add(n);
            }
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}

class KthLargest1 {

    PriorityQueue<Integer> queue;
    int K;

    public KthLargest1(int k, int[] nums) throws IllegalArgumentException {
        if (k <= 0) {
            throw new IllegalArgumentException("Invalid value of k.");
        }

        queue = new PriorityQueue<>();
        K = k;

        if (nums == null) {
            return;
        }

        for (int n : nums) {
            queue.offer(n);
            if (queue.size() > K) {
                queue.poll();
            }
        }
    }

    public int add(int val) {
        queue.offer(val);
        if (queue.size() > K) {
            queue.poll();
        }

        return queue.peek();
    }
}

// Your KthLargest object will be instantiated and called as such:
// KthLargest obj = new KthLargest(k, nums);
// int param_1 = obj.add(val);