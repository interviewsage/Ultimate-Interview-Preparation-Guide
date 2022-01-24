// LeetCode Question URL: https://leetcode.com/problems/happy-number/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Fast and Slow pointers
 *
 * <pre>
 * Time Complexity:
 * 1. Do-While loop runs for X+Y time.
 * 2. There can be 3 * (X+Y) calls to getSumOfDigitsSquare()
 * 3. For each number we will need O(Number of Digits) = O(log10(num)) = O(10)
 * 4. Total Time Complexity = O(3*(X+Y) * log10(num))
 *
 * Space Complexity: O(1)
 * </pre>
 */
class Solution1 {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        int slow = n;
        int fast = n;

        do {
            slow = getSumOfDigitsSquare(slow);
            fast = getSumOfDigitsSquare(getSumOfDigitsSquare(fast));
        } while (fast != 1 && fast != slow);

        return fast == 1;
    }

    private int getSumOfDigitsSquare(int n) {
        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }
        return sum;
    }
}

/**
 * Using Fast and Slow pointers + Memo
 *
 * <pre>
 * Time Complexity:
 * 1. Do-While loop runs for X+Y time.
 * 2. There can be 3 * (X+Y) calls to getSumOfDigitsSquare()
 * 3. Total Unique numbers seen will be X+C. This is bounded by INT_MAX.
 * 4. For each unique number we will need O(Number of Digits) = O(log10(num)) = O(10)
 *
 * Space Complexity: Cache map will store all unique numbers seen. This will be X+C and is bounded by INT_MAX.
 * </pre>
 */
class Solution2 {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        if (n == 1) {
            return true;
        }

        int slow = n;
        int fast = n;

        HashMap<Integer, Integer> cache = new HashMap<>();
        cache.put(1, 1);

        do {
            slow = getSumOfDigitsSquare(cache, slow);
            fast = getSumOfDigitsSquare(cache, getSumOfDigitsSquare(cache, fast));
        } while (fast != 1 && fast != slow);

        return fast == 1;
    }

    private int getSumOfDigitsSquare(HashMap<Integer, Integer> cache, int n) {
        Integer val = cache.get(n);
        if (val != null) {
            return val;
        }

        int sum = 0;
        while (n > 0) {
            int d = n % 10;
            sum += d * d;
            n /= 10;
        }

        cache.put(n, sum);
        return sum;
    }
}
