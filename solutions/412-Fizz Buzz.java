// LeetCode Question URL: https://leetcode.com/problems/fizz-buzz/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution1 {

    private static final String FIZZ_BUZZ = "FizzBuzz";
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    public List<String> fizzBuzz(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        List<String> result = new ArrayList<>(n);
        for (int i = 1; i <= n; i++) {
            boolean divBy3 = i % 3 == 0;
            boolean divBy5 = i % 5 == 0;

            if (divBy3 && divBy5) {
                result.add(FIZZ_BUZZ);
            } else if (divBy3) {
                result.add(FIZZ);
            } else if (divBy5) {
                result.add(BUZZ);
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}

class Solution2 {

    private static final String FIZZ_BUZZ = "FizzBuzz";
    private static final String FIZZ = "Fizz";
    private static final String BUZZ = "Buzz";

    public List<String> fizzBuzz(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Invalid input");
        }

        List<String> result = new ArrayList<>(n);
        int fizz = 3;
        int buzz = 5;
        for (int i = 1; i <= n; i++) {
            if (i == fizz && i == buzz) {
                result.add(FIZZ_BUZZ);
                fizz += 3;
                buzz += 5;
            } else if (i == fizz) {
                result.add(FIZZ);
                fizz += 3;
            } else if (i == buzz) {
                result.add(BUZZ);
                buzz += 5;
            } else {
                result.add(String.valueOf(i));
            }
        }
        return result;
    }
}

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution3 {
    public List<String> fizzBuzz(int n) {
        List<String> result = new ArrayList<>();
        if (n < 1) {
            return result;
        }

        for (int i = 1; i <= n; i++) {
            boolean div3 = (i % 3) == 0;
            boolean div5 = (i % 5) == 0;
            String toBeAdded = "";

            if (div3) {
                toBeAdded = "Fizz";
            }
            if (div5) {
                toBeAdded += "Buzz";
            }

            if (toBeAdded.length() == 0) {
                result.add(String.valueOf(i));
            } else {
                result.add(toBeAdded);
            }
        }

        return result;
    }
}
