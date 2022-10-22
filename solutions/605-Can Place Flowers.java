// LeetCode Question URL: https://leetcode.com/problems/can-place-flowers/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/can-place-flowers/discuss/103898/Java-Greedy-solution-O(flowerbed)-beats-100/106900
 *
 * Time Complexity: O(N/2) = O(N)
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
        if (n > (len + 1) / 2) {
            return false;
        }

        int idx = 0;
        while (idx < len) {
            if (flowerbed[idx] == 1) {
                idx += 2;
                continue;
            }
            if (idx < len - 1 && flowerbed[idx + 1] == 1) {
                idx += 3;
            } else {
                if (--n == 0) {
                    return true;
                }
                idx += 2;
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
        }

        return false;
    }
}

class Solution2 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || n < 0) {
            throw new IllegalArgumentException("Input is invalid");
        }
        if (n == 0) {
            return true;
        }
        int len = flowerbed.length;
        if (n > (len + 1) / 2) {
            return false;
        }
        if (len == 1) {
            return flowerbed[0] == 0;
        }
        if (len == 2) {
            return flowerbed[0] == 0 && flowerbed[1] == 0;
        }

        int left = 0;
        int right = len - 1;
        while (left < right - 1) {
            if (flowerbed[left] == 1) {
                left += 2;
            } else if (left < len - 1 && flowerbed[left + 1] == 1) {
                left += 3;
            } else {
                if (--n == 0) {
                    return true;
                }
                left += 2;
            }

            if (flowerbed[right] == 1) {
                right -= 2;
            } else if (right > 0 && flowerbed[right - 1] == 1) {
                right -= 3;
            } else {
                if (--n == 0) {
                    return true;
                }
                right -= 2;
            }
        }

        if (left == right && flowerbed[left] == 0 && flowerbed[left - 1] == 0 && flowerbed[left + 1] == 0) {
            return n == 1;
        }
        return false;
    }
}

class Solution3 {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || n < 0) {
            throw new IllegalArgumentException("Invalid Input");
        }
        if (n == 0) {
            return true;
        }
        int len = flowerbed.length;
        if (n > (len + 1) / 2) {
            return false;
        }
        if (len == 1) {
            return flowerbed[0] == 0;
        }
        if (len == 2) {
            return flowerbed[0] == 0 && flowerbed[1] == 0;
        }

        int i = 0;
        if (flowerbed[0] == 0) {
            if (flowerbed[1] == 0) {
                if (--n == 0) {
                    return true;
                }
                i = 2;
            } else {
                i = 3;
            }
        } else {
            i = 2;
        }

        int j = len - 1;
        if (flowerbed[len - 1] == 0) {
            if (flowerbed[len - 2] == 0) {
                if (--n == 0) {
                    return true;
                }
                j = len - 3;
            } else {
                j = len - 4;
            }
        } else {
            j = len - 3;
        }

        while (j - i >= 2) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else {
                if (flowerbed[i - 1] == 0) {
                    if (flowerbed[i + 1] == 0) {
                        if (--n == 0) {
                            return true;
                        }
                        i += 2;
                    } else {
                        i += 3;
                    }
                } else {
                    i++;
                }
            }

            if (flowerbed[j] == 1) {
                j -= 2;
            } else {
                if (flowerbed[j - 1] == 0) {
                    if (flowerbed[j + 1] == 0) {
                        if (--n == 0) {
                            return true;
                        }
                        j -= 2;
                    } else {
                        j -= 3;
                    }
                } else {
                    j--;
                }
            }
        }

        if (i == j && flowerbed[i] == 0 && flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
            return n == 1;
        }

        return false;
    }
}
