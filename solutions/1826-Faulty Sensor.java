// LeetCode Question URL: https://leetcode.com/problems/faulty-sensor/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/faulty-sensor/discuss/1160927/C++Java-Single-Pass
 *
 * Time Complexity: O(N)
 *
 * Space Complexity; O(1)
 */
class Solution {
    public int badSensor(int[] sensor1, int[] sensor2) {
        if (sensor1 == null || sensor2 == null || sensor1.length != sensor2.length) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = sensor1.length;
        int i = 0;

        // No need to check last index as if we reached last index the result is -1 as
        // we cannot determine which one was faulty.

        while (i < len - 1 && sensor1[i] == sensor2[i]) {
            i++;
        }
        while (i < len - 1 && sensor1[i] == sensor2[i + 1] && sensor1[i + 1] == sensor2[i]) {
            i++;
        }

        if (i >= len - 1) {
            return -1;
        }
        return sensor1[i] == sensor2[i + 1] ? 1 : 2;
    }
}
