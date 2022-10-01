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
    long windowSum;

    public MovingAverage(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid size");
        }

        capacity = size;
        queue = new ArrayDeque<>(size + 1);
        windowSum = 0;
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

    int[] window;
    long windowSum;
    int windowCurSize;
    int insertIdx;

    public MovingAverage2(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid size");
        }

        window = new int[size];
        windowSum = 0;
        windowCurSize = 0;
        insertIdx = 0;
    }

    public double next(int val) {
        windowSum += val - window[insertIdx];
        window[insertIdx] = val;
        insertIdx = (insertIdx + 1) % window.length;

        if (windowCurSize < window.length) {
            windowCurSize++;
        }

        return (double) windowSum / windowCurSize;
    }
}

class MovingAverage3 {

    int[] queue;
    int capacity;
    int sum;
    int count;

    public MovingAverage3(int size) {
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

// Your MovingAverage object will be instantiated and called as such:
// MovingAverage obj = new MovingAverage(size);
// double param_1 = obj.next(val);
