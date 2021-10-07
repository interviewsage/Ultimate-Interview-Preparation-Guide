// LeetCode Question URL: https://leetcode.com/problems/find-median-from-data-stream/
// LeetCode Discuss URL: https://leetcode.com/problems/find-median-from-data-stream/discuss/1506078/Java-Detailed-Code-Solutions-for-Follow-Ups

import java.util.*;

/**
 * Using two heaps
 *
 * Refer:
 * https://leetcode.com/articles/find-median-from-data-stream/#approach-3-two-heaps
 *
 * <pre>
 * Time Complexity:
 * 1) addNum -> O(5 * log (N/2)) = O(log N)
 * 2) findMedian -> O(1)
 * </pre>
 *
 * Space Complexity: O(N)
 *
 * N = Count of numbers in the data stream.
 */
class MedianFinder {

    PriorityQueue<Integer> smallNums; // Max Heap
    PriorityQueue<Integer> largeNums; // Min Heap

    public MedianFinder() {
        smallNums = new PriorityQueue<>(Collections.reverseOrder());
        largeNums = new PriorityQueue<>();
    }

    public void addNum(int num) {
        // Add to maxHeap first (Group of smaller numbers)
        smallNums.offer(num);
        // Balance the heaps
        largeNums.offer(smallNums.poll());
        if (largeNums.size() > smallNums.size()) {
            smallNums.offer(largeNums.poll());
        }
    }

    public double findMedian() {
        if (smallNums.size() != largeNums.size()) {
            return smallNums.peek();
        }

        return (smallNums.peek() + largeNums.peek()) / 2.0;
    }
}

/**
 * Follow-Up 1 - Numbers are between [0, 100]
 *
 * Maintain a cumulative count array.
 *
 * Refer:
 * https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups
 *
 * <pre>
 * Time Complexity:
 * 1) addNum -> O(101) = O(1)
 * 2) findMedian -> O(log(101)) = O(1)
 * </pre>
 *
 * Space Complexity: O(101) = O(1)
 */
class MedianFinder1a {

    int[] count;

    public MedianFinder1a() {
        count = new int[101];
    }

    public void addNum(int num) {
        for (int i = num; i < 101; i++) {
            count[i]++;
        }
    }

    public double findMedian() {
        int totalNums = count[100];
        int medianCount = totalNums % 2 == 0 ? totalNums / 2 : (totalNums + 1) / 2;
        int idx = binarySearch(count, medianCount);

        if (totalNums % 2 == 0) {
            if (medianCount < count[idx]) {
                return idx;
            }

            for (int i = idx + 1; i < 101; i++) {
                if (count[i] > count[idx]) {
                    return (idx + i) / 2.0;
                }
            }
        }

        return idx;
    }

    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        ;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}

/**
 * Follow-Up 1 - Numbers are between [0, 100]
 *
 * Maintain a count array. Here Add num is optimized to true O(1)
 *
 * Refer:
 * https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups
 *
 * <pre>
 * Time Complexity:
 * 1) addNum -> O(1)
 * 2) findMedian -> O(101) = O(1)
 * </pre>
 *
 * Space Complexity: O(101) = O(1)
 */
class MedianFinder1b {

    int[] count;
    int totalNums;

    public MedianFinder1b() {
        count = new int[101];
    }

    public void addNum(int num) {
        count[num]++;
        totalNums++;
    }

    public double findMedian() {
        int medianCount = totalNums % 2 == 0 ? totalNums / 2 : (totalNums + 1) / 2;

        int countSum = 0;
        int idx = 0;
        while (idx <= 100) {
            countSum += count[idx];
            if (medianCount <= countSum) {
                break;
            }
            idx++;
        }

        if (totalNums % 2 == 0) {
            if (medianCount < countSum) {
                return idx;
            }

            for (int i = idx + 1; i < 101; i++) {
                if (count[i] != 0) {
                    return (idx + i) / 2.0;
                }
            }
        }

        return idx;
    }
}

/**
 * Follow-Up 2 - 99% Numbers are between [0, 100]
 *
 * Until a threshold use two heaps to find the median. Once the threshold is met
 * use the cumulative count array.
 *
 * Refer: (Inspired from this comment)
 * https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups/348377
 *
 * <pre>
 * Time Complexity:
 * 1) addNum -> Until threshold O(101 + 5*log(threshold/2)). After threshold O(101)
 * 2) findMedian -> Until threshold O(1). After threshold O(101)
 * </pre>
 *
 * Space Complexity: O(101+threshold)
 */
class MedianFinder2a {

    int[] count;
    int lessThanZero;
    int totalNums;
    int threshold;
    PriorityQueue<Integer> smallNums; // Max Heap
    PriorityQueue<Integer> largeNums; // Min Heap

    public MedianFinder2a() {
        count = new int[101];
        threshold = 100;
        smallNums = new PriorityQueue<>(Collections.reverseOrder());
        largeNums = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (num < 0) {
            lessThanZero++;
        }
        for (int i = Math.max(num, 0); i < 101; i++) {
            count[i]++;
        }
        totalNums++;

        if (totalNums <= threshold) {
            smallNums.offer(num);
            largeNums.offer(smallNums.poll());
            if (largeNums.size() > smallNums.size()) {
                smallNums.offer(largeNums.poll());
            }
        }
    }

    public double findMedian() {
        if (totalNums <= threshold) {
            if (smallNums.size() != largeNums.size()) {
                return smallNums.peek();
            }

            return (smallNums.peek() + largeNums.peek()) / 2.0;
        }

        int medianCount = totalNums % 2 == 0 ? totalNums / 2 : (totalNums + 1) / 2;
        int idx = binarySearch(count, medianCount);

        if (totalNums % 2 == 0) {
            if (medianCount < count[idx]) {
                return idx;
            }

            for (int i = idx + 1; i < 101; i++) {
                if (count[i] > count[idx]) {
                    return (idx + i) / 2.0;
                }
            }
        }

        return idx;
    }

    private int binarySearch(int[] nums, int target) {
        int start = 0;
        int end = nums.length - 1;
        ;

        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }
}

/**
 * Follow-Up 2 - 99% Numbers are between [0, 100]
 *
 * Until a threshold use two heaps to find the median. Once the threshold is met
 * use the count array.
 *
 * Refer: (Inspired from this comment)
 * https://leetcode.com/problems/find-median-from-data-stream/discuss/275207/Solutions-to-follow-ups/348377
 *
 * <pre>
 * Time Complexity:
 * 1) addNum -> Until threshold O(5*log(threshold/2)). After threshold O(1)
 * 2) findMedian -> Until threshold O(1). After threshold O(log(101))
 * </pre>
 *
 * Space Complexity: O(101+threshold)
 */
class MedianFinder2b {

    int[] count;
    int lessThanZero;
    int totalNums;
    int threshold;
    PriorityQueue<Integer> smallNums; // Max Heap
    PriorityQueue<Integer> largeNums; // Min Heap

    public MedianFinder2b() {
        count = new int[101];
        threshold = 100;
        smallNums = new PriorityQueue<>(Collections.reverseOrder());
        largeNums = new PriorityQueue<>();
    }

    public void addNum(int num) {
        if (num < 0) {
            lessThanZero++;
        }
        count[num]++;
        totalNums++;

        if (totalNums <= threshold) {
            smallNums.offer(num);
            largeNums.offer(smallNums.poll());
            if (largeNums.size() > smallNums.size()) {
                smallNums.offer(largeNums.poll());
            }
        }
    }

    public double findMedian() {
        if (totalNums <= threshold) {
            if (smallNums.size() != largeNums.size()) {
                return smallNums.peek();
            }

            return (smallNums.peek() + largeNums.peek()) / 2.0;
        }

        int medianCount = totalNums % 2 == 0 ? totalNums / 2 : (totalNums + 1) / 2;

        int countSum = lessThanZero;
        int idx = 0;
        while (idx <= 100) {
            countSum += count[idx];
            if (medianCount <= countSum) {
                break;
            }
            idx++;
        }

        if (totalNums % 2 == 0) {
            if (medianCount < countSum) {
                return idx;
            }

            for (int i = idx + 1; i < 101; i++) {
                if (count[i] != 0) {
                    return (idx + i) / 2.0;
                }
            }
        }

        return idx;
    }
}

// Your MedianFinder object will be instantiated and called as such:
// MedianFinder obj = new MedianFinder();
// obj.addNum(num);
// double param_2 = obj.findMedian();
