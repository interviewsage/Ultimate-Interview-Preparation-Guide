// LeetCode Question URL: https://leetcode.com/problems/integer-to-english-words/
// LeetCode Discuss URL:

/**
 * Convert group by group
 *
 * <pre>
 * Time Complexity:
 * Lets assume L is Average length of each word & Int can have maximum 10 digits
 * For 1st set of 3 digits = 4 * L (Can have max 4 words)
 * For 2nd set of 3 digits = 4 * L + L (for Thousands word) +  4*L (For old result)
 * For 3rd set of 3 digits = 4 * L + L (for Thousands word) +  9*L (For old result)
 * For Last digit          = L     + L (for Thousands word) + 14*L (For old result)
 *
 * Thus, total time complexity = O(43*L + 16*L(fot toString)) = O(59 * L)
 * </pre>
 *
 * Space Complexity: At a point of time, there can be 2 StringBuilders. Thus,
 * max space required will be O(14*L + 16*L) = O(30*L)
 *
 * L = Average length of each word in the output.
 */
class Solution1 {
    private static final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    private static final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety" };
    private static final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("Input is negative");
        }
        if (num == 0) {
            return "Zero";
        }

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (num > 0) {
            if (num % 1000 > 0) {
                StringBuilder sb = new StringBuilder();
                getWordsLessThanThousand(num % 1000, sb);
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(THOUSANDS[i]);
                if (result.length() > 0) {
                    sb.append(" ").append(result);
                }
                result = sb;
            }
            i++;
            num /= 1000;
        }

        return result.toString();
    }

    private void getWordsLessThanThousand(int n, StringBuilder sb) {
        if (n >= 100) {
            sb.append(LESS_THAN_20[n / 100]);
            sb.append(" Hundred");
            n %= 100;
            if (n > 0) {
                sb.append(" ");
            }
        }
        if (n >= 20) {
            sb.append(TENS[n / 10]);
            n %= 10;
            if (n > 0) {
                sb.append(" ");
            }
        }
        if (n > 0) {
            sb.append(LESS_THAN_20[n]);
        }
    }
}

/**
 * Divide and Conquer
 *
 * Time Complexity: O(log10 N) = O(Number of digits in input num)
 *
 * Space Complexity: O(L * log10 N).
 *
 * L = Average length of each word in the output.
 */
class Solution2 {
    private static final String[] LESS_THAN_20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight",
            "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen",
            "Nineteen" };
    private static final String[] TENS = { "", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety" };
    private static final String[] THOUSANDS = { "", "Thousand", "Million", "Billion" };

    public String numberToWords(int num) {
        if (num < 0) {
            return "";
        }
        if (num == 0) {
            return "Zero";
        }

        String result = "";
        int i = 0;
        while (num > 0) {
            if (num % 1000 > 0) {
                StringBuilder sb = new StringBuilder();
                getWordsLessThanThousand(num % 1000, sb);
                sb.append(THOUSANDS[i]);
                sb.append(" ");
                result = sb.toString() + result;
            }
            num /= 1000;
            i++;
        }

        return result.trim();
    }

    private void getWordsLessThanThousand(int n, StringBuilder sb) {
        if (n >= 100) {
            sb.append(LESS_THAN_20[n / 100]);
            sb.append(" Hundred ");
            n %= 100;
        }
        if (n >= 20) {
            sb.append(TENS[n / 10]);
            sb.append(" ");
            n %= 10;
        }
        if (n > 0) {
            sb.append(LESS_THAN_20[n]);
            sb.append(" ");
        }
    }
}