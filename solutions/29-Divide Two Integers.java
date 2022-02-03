// LeetCode Question URL: https://leetcode.com/problems/divide-two-integers/
// LeetCode Discuss URL:

/**
 * Check here for alternate solution that computes result in O(32)
 * https://leetcode.com/problems/divide-two-integers/discuss/142849/C++JavaPython-Should-Not-Use-"long"-Int
 */

/**
 * Solution using all int variables. Long is not used.
 *
 * Time Complexity = O((log N)^2) -> where N is the Search space = (dividend -
 * divisor).. Outer while loop will reduce the search space by half after every
 * iteration (log N). Inner loop will also take log N.
 *
 * <pre>
 * In worst case, the outer while loop only reduces the search space by half.
 * For example.. 95/3
 *
 * 1st Iteration will reduce the dividend to 47
 * 2nd Iteration will reduce the dividend to 23
 * 3rd Iteration will reduce the dividend to 11
 * 4th Iteration will reduce the dividend to 5
 * 6th Iteration will reduce the dividend to 2
 *
 * Thus the outer while loop can take upto O(log2(N)) in worst case.
 * </pre>
 *
 * Space Complexity = O(1)
 */
class Solution1 {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("divide by zero");
        }
        if (divisor == dividend) {
            return 1;
        }
        if (dividend == 0 || divisor == Integer.MIN_VALUE) {
            return 0;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : (~dividend + 1);
        }
        if (divisor == Integer.MAX_VALUE) {
            return dividend <= Integer.MIN_VALUE + 1 ? -1 : 0;
        }

        boolean resultNegative = (dividend > 0) ^ (divisor > 0);
        divisor = Math.abs(divisor);
        boolean dividendOverflow = dividend == Integer.MIN_VALUE;
        dividend = dividendOverflow ? Integer.MAX_VALUE : Math.abs(dividend);

        int quotient = 0;

        while (dividend >= divisor) {
            int multiplier = 1;
            int curDivisor = divisor;
            while ((dividend >= (curDivisor << 1)) && (((curDivisor << 1) >> 1) == curDivisor)) {
                curDivisor <<= 1;
                multiplier <<= 1;
            }
            quotient += multiplier;
            dividend -= curDivisor;
        }

        if (dividendOverflow) {
            if (dividend + 1 >= divisor) {
                quotient++;
            }
        }

        return resultNegative ? (~quotient + 1) : quotient;
    }
}

class Solution2 {
    public int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Invalid");
        }
        if (dividend == divisor) {
            return 1;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == 0 || divisor == Integer.MIN_VALUE) {
            return 0;
        }
        if (divisor == Integer.MAX_VALUE) {
            return dividend <= Integer.MIN_VALUE + 1 ? -1 : 0;
        }
        if (divisor == -1) {
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : ~dividend + 1;
        }

        int sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;
        int extraVal = 0;
        if (dividend == Integer.MIN_VALUE) {
            extraVal = 1;
            dividend = Integer.MAX_VALUE;
        } else {
            dividend = Math.abs(dividend);
        }
        divisor = Math.abs(divisor);
        int res = 0;

        while (dividend >= divisor) {
            int mul = 1;
            int tmp = divisor;
            while (dividend >= (tmp << 1) && ((tmp << 1) >> 1) == tmp) {
                tmp <<= 1;
                mul <<= 1;
            }
            res += mul;
            dividend -= tmp;
        }

        if (dividend + extraVal >= divisor) {
            res++;
        }

        return sign == -1 ? ~res + 1 : res;
    }
}

/**
 * Solution using all int variables. Long is not used.
 *
 * Time Complexity = O((log N)^2) -> where N is the Search space = (dividend -
 * divisor).. Outer while loop will reduce the search space by half after every
 * iteration (log N). Inner loop will also take log N.
 *
 * Space Complexity = O(1)
 */
class Solution3 {
    public int divide(int dividend, int divisor) {
        // Very basic base conditions.
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == divisor) {
            return 1;
        }

        int dividendOverflow = 0;

        // Base conditions to handle integer overflow while calculating Math.abs
        if (dividend == Integer.MIN_VALUE) {
            if (divisor == -1) {
                return Integer.MAX_VALUE;
            } else {
                dividendOverflow = 1;
            }
        }
        if (divisor == Integer.MIN_VALUE) {
            /**
             * At this state, dividend cannot be Integer.MIN_VALUE as it would have
             * satisfied dividend == divisor condition. Returing zero as dividing by -2^31
             * will always result in zero.
             */
            return 0;
        }

        // This condition is optional
        if (divisor == -1) {
            /**
             * At this state, dividend cannot be Integer.MIN_VALUE. If it was, it would have
             * been taken care by if conditions to handle overflow.
             */
            return ~dividend + 1;
        }

        int result = 0;
        boolean isNegative = (dividend > 0) ^ (divisor > 0);

        int absDividend = Math.abs(dividend + dividendOverflow);
        int absDivisor = Math.abs(divisor);

        while (absDividend >= absDivisor) {
            int tempDivisor = absDivisor;
            int times = 1;

            /**
             * Finding the highest power of 2 that is smaller than the absDividend.
             *
             * ((tempDivisor << 1) >> 1) == tempDivisor ==> helps in avoiding overflow
             * beyond 2^30. as 2^30 << 1 will lead to integer overflow.
             */
            while (absDividend >= (tempDivisor << 1) && ((tempDivisor << 1) >> 1) == tempDivisor) {
                tempDivisor <<= 1;
                times <<= 1;
            }

            absDividend -= tempDivisor;

            // If the dividend was Int.MIN, then add the overflow once.
            absDividend += dividendOverflow;
            dividendOverflow = 0;

            result += times;
        }

        return isNegative ? ~result + 1 : result;
    }
}

/**
 * Solution using int and long variables.
 *
 * Time Complexity = O((log N)^2) -> where N is the Search space = (dividend -
 * divisor).. Outer while loop will reduce the search space by half after every
 * iteration (log N). Inner loop will also take log N.
 *
 * Space Complexity = O(1)
 */
class Solution4 {
    public int divide(int dividend, int divisor) {
        // Very basic base conditions.
        if (divisor == 0) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (dividend == divisor) {
            return 1;
        }
        // Base condition to handle integer overflow in result.
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int result = 0;
        boolean isNegative = (dividend > 0) ^ (divisor > 0);

        long absDividend = Math.abs((long) dividend);
        long absDivisor = Math.abs((long) divisor);

        while (absDividend >= absDivisor) {
            long tempDivisor = absDivisor;
            int times = 1;

            while (absDividend >= (tempDivisor << 1)) {
                tempDivisor <<= 1;
                times <<= 1;
            }

            absDividend -= tempDivisor;
            result += times;
        }

        return isNegative ? ~result + 1 : result;
    }
}
