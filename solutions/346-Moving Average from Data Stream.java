// LeetCode Question URL: https://leetcode.com/problems/moving-average-from-data-stream/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Maintain a queue with last K elements.
 *
 * Time Complexity: MovingAverage() -> O(1). next() -> O(1)
 *
 * Space Complexity: MovingAverage() -> O(1). next() -> O(1). Overall Queue size
 * = O(K+1)
 *
 * K = Max Capacity of the window.
 */
class MovingAverage {

    Deque<Integer> queue;
    int capacity;
    int windowSum;

    public MovingAverage(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Negative widow size provided");
        }
        queue = new ArrayDeque<>(size + 1);
        capacity = size;
    }

    public double next(int val) {
        queue.offer(val);
        windowSum += val;
        if (queue.size() > capacity) {
            windowSum -= queue.poll();
        }
        return (double) windowSum / queue.size();
    }
}

class MovingAverage2 {

    int[] queue;
    int capacity;
    int sum;
    int count;

    public MovingAverage2(int size) {
        queue = new int[size];
        sum = 0;
        count = 0;
        capacity = size;
    }

    public double next(int val) {
        sum += val - queue[count % capacity];
        queue[count % capacity] = val;
        count++;
        return (double) sum / Math.min(count, capacity);
    }
}

public class MovingAverage3 {
    private int[] window;
    private int n, insert;
    private long sum;

    /** Initialize your data structure here. */
    public MovingAverage3(int size) {
        window = new int[size];
        insert = 0;
        sum = 0;
    }

    public double next(int val) {
        sum -= window[insert];
        sum += val;
        window[insert] = val;
        insert = (insert + 1) % window.length;

        if (n < window.length) {
            n++;
        }
        return (double) sum / n;
    }
}

// Your MovingAverage object will be instantiated and called as such:
// MovingAverage obj = new MovingAverage(size);
// double param_1 = obj.next(val);
