// LeetCode Question URL: https://leetcode.com/problems/utf-8-validation/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/utf-8-validation/discuss/87462/Concise-C++-implementation/92357
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input data array.
 */
class Solution1 {
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return false;
        }

        int len = data.length;
        int mask7 = 1 << 7;
        int mask6 = 1 << 6;
        int i = 0;

        while (i < len) {
            int count = 0;
            int mask = mask7;
            while (count < 5 && (data[i] & mask) != 0) {
                count++;
                mask >>= 1;
            }
            i++;

            if (count == 0) {
                continue;
            }
            if (count == 1 || count == 5 || count - 1 > len - i) {
                return false;
            }

            while (count-- > 1) {
                if ((data[i] & mask7) == 0 || (data[i] & mask6) != 0) {
                    return false;
                }
                i++;
            }
        }

        return true;
    }
}

class Solution2 {
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return true;
        }

        int count = 0;

        for (int d : data) {
            d = d & 255;
            if (count == 0) {
                if (d >> 5 == 0b110) {
                    count = 2;
                } else if (d >> 4 == 0b1110) {
                    count = 3;
                } else if (d >> 3 == 0b11110) {
                    count = 4;
                } else if (d >> 7 == 0) {
                    count = 1;
                } else {
                    return false;
                }
            } else if (d >> 6 != 0b10) {
                return false;
            }
            count--;
        }

        return count == 0;
    }
}

/**
 * Refer: https://leetcode.com/problems/utf-8-validation/solution/
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 *
 * N = Length of the input data array.
 */
class Solution3 {
    public boolean validUtf8(int[] data) {
        if (data == null || data.length == 0) {
            return true;
        }

        int count = 0;
        int mask1 = 1 << 7;
        int mask2 = 1 << 6;

        for (int d : data) {
            if (count == 0) {
                int mask = 1 << 7;
                while ((d & mask) != 0) {
                    count++;
                    mask >>= 1;
                }

                if (count == 0) {
                    continue;
                }
                if (count > 4 || count == 1) {
                    return false;
                }
            } else {
                if (!((d & mask1) != 0 && (d & mask2) == 0)) {
                    return false;
                }
            }

            count--;
        }

        return count == 0;
    }
}
