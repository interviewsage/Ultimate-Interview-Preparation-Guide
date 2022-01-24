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