// LeetCode Question URL: https://leetcode.com/problems/kth-largest-element-in-a-stream/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Priority Queue
 *
 * Time Complexity: KthLargest() -> O(N log K). Every operation of add() ->
 * O(2*log K)
 *
 * Space Complexity: O(K)
 *
 * N = Length of initial array passed to the constructor. K = input k.
 */
class KthLargest {

    PriorityQueue<Integer> minHeap;
    int k;

    public KthLargest(int k, int[] nums) {
        if (k <= 0) {
            throw new IllegalArgumentException("Input k is invalid");
        }

        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int n : nums) {
            add(n);
        }
    }

    public int add(int val) {
        if (minHeap.size() == k) {
            if (minHeap.peek() >= val) {
                return minHeap.peek();
            }
            minHeap.poll();
        }
        minHeap.offer(val);

        return minHeap.peek();
    }
}

class KthLargest1 {

    PriorityQueue<Integer> minHeap;
    int k;

    public KthLargest1(int k, int[] nums) {
        if (k <= 0) {
            throw new IllegalArgumentException("Input k is invalid");
        }

        this.k = k;
        this.minHeap = new PriorityQueue<>();
        for (int n : nums) {
            add(n);
        }
    }

    public int add(int val) {
        if (minHeap.size() < k) {
            minHeap.offer(val);
        } else if (val > minHeap.peek()) {
            minHeap.poll();
            minHeap.offer(val);
        }
        return minHeap.peek();
    }
}

// Your KthLargest object will be instantiated and called as such:
// KthLargest obj = new KthLargest(k, nums);
// int param_1 = obj.add(val);
