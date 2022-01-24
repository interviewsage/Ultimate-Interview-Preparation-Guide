// LeetCode Question URL: https://leetcode.com/problems/can-place-flowers/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/can-place-flowers/discuss/103898/Java-Greedy-solution-O(flowerbed)-beats-100/106900
 *
 * Time Complexity: O(Length / 2 + 1) = O(Length)
 *
 * Space Complexity: O(1)
 */
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n == 0) {
            return true;
        }

        int len = flowerbed.length;
        if (n > len / 2 + 1) {
            return false;
        }

        int i = 0;
        while (i < len) {
            if (flowerbed[i] == 1) {
                i += 2;
                continue;
            }
            if (i < len - 1 && flowerbed[i + 1] == 1) {
                i += 3;
                continue;
            }

            // Below condition is not needed. (Assuming input array is always valid)
            // Case 1: 1, 0, X --> In this i will jump by 2, and always land after zero
            // Case 2: 0, 1, 0, X --> In this i will jump by 3, and always land after zero
            // Case 3: 0, 0, X --> In this i will jump by 2, and always land after zero
            // Thus, we do not need the below check.

            // if (i > 0 && flowerbed[i - 1] == 1) {
            // i++;
            // continue;
            // }

            if (n == 1) {
                return true;
            }
            n--;
            i += 2;
        }

        return false;
    }
}
