// LeetCode Question URL: https://leetcode.com/problems/fraction-to-recurring-decimal/
// LeetCode Discuss URL:

import java.util.*;

/**
 * <pre>
 * VERY IMP. CHECK THIS!!!!
 *
 * Solutions without using long.
 *
 * 1. https://leetcode.com/problems/fraction-to-recurring-decimal/discuss/51237/Accepted-cpp-solution-without-converting-int-to-long-long
 * 2. https://leetcode.com/problems/fraction-to-recurring-decimal/discuss/51195/My-C++-solution-without-using-long
 * </pre>
 */

/**
 * Long Division
 *
 * <pre>
 * Refer for complexity:
 * 1. https://leetcode.com/problems/fraction-to-recurring-decimal/discuss/51106/My-clean-Java-solution/146456
 * 2. https://leetcode.com/problems/fraction-to-recurring-decimal/solution/778855
 *
 * Good example for time & space complexity is 1/17. Here are all remainders between 1 & 16 are possible.
 *
 * Time Complexity:
 * 1. To add sign to SB: O(1)
 * 2. To add integer part to SB: O(10) (It can be maximum 10 digits)
 * 3. To add decimal point: O(1)
 * 4. To add fractional part: O(Denominator) + O(Denominator+1) for inserting brackets.
 * 5. ToString: O(14 + Denominator)
 *
 * Thus total time complexity: O(Denominator)
 *
 * Space Complexity:
 * 1. HashMap -> O(2*Denominator)
 * 2. StringBuilder -> O(14 + Denominator)
 *
 * Thus space time complexity: O(Denominator)
 * </pre>
 */
class Solution1 {
    public String fractionToDecimal(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Invalid Input: Division by zero");
        }
        if (numerator == 0) {
            return "0";
        }
        if (numerator == denominator) {
            return "1";
        }
        if (denominator == 1) {
            return String.valueOf(numerator);
        }

        StringBuilder sb = new StringBuilder();
        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append('-');
        }

        long num = Math.abs((long) numerator);
        long den = Math.abs((long) denominator);

        sb.append(num / den);
        long rem = num % den;

        if (rem == 0) {
            return sb.toString();
        }

        sb.append('.');
        Map<Long, Integer> remMap = new HashMap<>();
        while (rem > 0) {
            remMap.put(rem, sb.length());
            rem *= 10;
            sb.append(rem / den);
            rem %= den;

            Integer lenFound = remMap.get(rem);
            if (lenFound != null) {
                sb.insert(lenFound, "(");
                // sb.insert(lenFound.intValue(), "(");
                sb.append(')');
                break;
            }
        }

        return sb.toString();
    }
}

class Solution2 {
    public String fractionToDecimal(int numerator, int denominator) throws IllegalArgumentException {
        if (denominator == 0) {
            throw new IllegalArgumentException("Division by Zero");
        }
        if (numerator == 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();

        if ((numerator < 0) ^ (denominator < 0)) {
            sb.append("-");
        }

        long ln = Math.abs((long) numerator);
        long ld = Math.abs((long) denominator);

        sb.append(ln / ld);

        long rem = ln % ld;
        if (rem == 0) {
            return sb.toString();
        }

        sb.append(".");

        // Key = remainder. Value = Current length of the result string.
        Map<Long, Integer> map = new HashMap<>();

        while (rem > 0) {
            map.put(rem, sb.length());
            rem *= 10;
            sb.append(rem / ld);
            rem %= ld;

            // If seeing the same remainder again...
            if (map.containsKey(rem)) {
                sb.insert(map.get(rem), "(");
                sb.append(")");
                break;
            }
        }

        return sb.toString();
    }
}