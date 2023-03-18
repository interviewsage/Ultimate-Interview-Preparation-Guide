// LeetCode Question URL: https://leetcode.com/problems/bulb-switcher/
// LeetCode Discuss URL:

/**
 * Proof of the solution: Lets say there are n Bulbs, which are numbered from 1
 * 2 3 .. n. Initially all Bulbs are OFF.
 *
 * In round 1 -> 1, 2, 3, .. n bulbs will be toggled.
 *
 * In round 2 -> 2, 4, 6, .. bulbs will be toggled.
 *
 * In round 3 -> 3, 6, 9, .. bulbs will be toggled.
 *
 * ..
 *
 * In round n -> n bulb will be toggled.
 *
 * Thus we can observe from here that, if n > 6, the bulb 6 will be toggled in
 * rounds 1, 2, 3, 6. 1, 2, 3, 6 are all the factors of 6. Thus we can say, Bulb
 * i is toggled k times, where k is number of factors of i.
 *
 * Since Initial state is OFF: if k = Odd -> Final state of bulb i is ON. if k =
 * Even -> Final state of bulb i is OFF. k is Odd only if i is perfect square.
 * if i = 12, its factors are 1, 2, 3, 4, 6, 12 => here k = 6... this bulb will
 * be OFF in final state. if i = 16, its factors are 1, 2, 4, 8, 16 => k = 5...
 * this bulb will be ON in final state.
 *
 * Thus the final answer is to find the number of perfect square in range [1,n].
 * Which is equal to floor(sqrt(n)).
 *
 * Refer:
 * https://leetcode.com/problems/bulb-switcher/discuss/77104/Math-solution..
 *
 * Time Complexity: O(log n)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public int bulbSwitch(int n) {
        return (int) Math.sqrt(n);
    }
}

class Solution2 {
    public int bulbSwitch(int x) {
        if (x < 0) {
            throw new IllegalArgumentException("Input number is negative. Only positive numbers are supported");
        }
        // Base case to handle Input <= 3.
        if (x <= 3) {
            return x == 0 ? 0 : 1;
        }

        // Binary Search space will start from 2 to x/2.
        // Since we have already handled upto x=3 in the base case, 2 will be correct
        // answer for next possible input x=4.
        // For x>=4, Square root is always less than x/2. Thus end will be x/2.
        int start = 2;
        int end = x / 2;

        while (start < end) {
            int mid = start + (end - start) / 2 + 1;
            int div = x / mid;

            // x == (mid * mid). To Avoid Integer Overflow, we will do (x / mid) == mid.
            if (mid == div) {
                return mid;
            }
            if (mid > div) {
                end = mid - 1;
            } else {
                start = mid;
            }
        }

        // Here start == end.
        return start;
    }
}
