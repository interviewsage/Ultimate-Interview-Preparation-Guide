// LeetCode Question URL: https://leetcode.com/problems/bulls-and-cows/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/bulls-and-cows/discuss/74621/One-pass-Java-solution
 *
 * The idea is to iterate over the numbers in secret and in guess and count all
 * bulls right away. For cows maintain an array that stores count of the number
 * appearances in secret and in guess. Increment cows when either number from
 * secret was already seen in guest or vice versa.
 *
 * Time Complexity: O(S)
 *
 * Space Complexity: O(10)
 *
 * S = Length of secret string.
 */
class Solution1 {
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() != guess.length()) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = secret.length();
        if (len == 0) {
            return "0A0B";
        }

        int[] countArr = new int[10];
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < len; i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bulls++;
                continue;
            }

            if (countArr[s] < 0) {
                cows++;
            }
            if (countArr[g] > 0) {
                cows++;
            }
            countArr[s]++;
            countArr[g]--;
        }

        return new StringBuilder().append(bulls).append('A').append(cows).append('B').toString();
    }
}

class Solution2 {
    public String getHint(String secret, String guess) {
        if (secret == null || guess == null || secret.length() != guess.length()) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int len = secret.length();
        if (len == 0) {
            return "0A0B";
        }

        int[] sMap = new int[10];
        int[] gMap = new int[10];
        int bulls = 0;
        int cows = 0;

        for (int i = 0; i < len; i++) {
            int s = secret.charAt(i) - '0';
            int g = guess.charAt(i) - '0';

            if (s == g) {
                bulls++;
                continue;
            }

            if (gMap[s] > 0) {
                gMap[s]--;
                cows++;
            } else {
                sMap[s]++;
            }
            if (sMap[g] > 0) {
                sMap[g]--;
                cows++;
            } else {
                gMap[g]++;
            }
        }

        return new StringBuilder().append(bulls).append('A').append(cows).append('B').toString();
    }
}
