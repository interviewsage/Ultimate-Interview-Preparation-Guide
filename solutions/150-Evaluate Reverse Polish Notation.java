// LeetCode Question URL: https://leetcode.com/problems/evaluate-reverse-polish-notation/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Using Stack to store the numbers and their result.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 */
class Solution1 {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }

        Deque<Integer> stack = new ArrayDeque<>();

        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    if (divisor == 0) {
                        throw new ArithmeticException("Division by zero");
                    }
                    stack.push(stack.pop() / divisor);
                    break;
                default:
                    try {
                        stack.push(Integer.parseInt(token));
                    } catch (NumberFormatException e) {
                        throw new IllegalArgumentException("Invalid RPN Expression: Token not supported: " + token);
                    }

            }
        }

        return stack.pop();
    }
}

/**
 * Modifying the input array. This helps us in achieving constant space
 * solution.
 *
 * Refer: Solution 3 in the below post.
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/discuss/1229370/Short-and-Simple-Solution-w-Explanation-or-O(N)-and-O(1)-Space-Solutions
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(1)
 */
class Solution2 {
    private static final Set<String> OPERATORS = Set.of("+", "-", "*", "/");

    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }
        if (tokens.length == 1) {
            return Integer.parseInt(tokens[0]);
        }

        int topIndex = 0;

        for (String token : tokens) {
            if (OPERATORS.contains(token)) {
                int num2 = Integer.parseInt(tokens[--topIndex]);
                int num1 = Integer.parseInt(tokens[--topIndex]);
                int res = 0;

                switch (token) {
                    case "+":
                        res = num1 + num2;
                        break;
                    case "-":
                        res = num1 - num2;
                        break;
                    case "*":
                        res = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        res = num1 / num2;
                        break;
                }
                tokens[topIndex++] = String.valueOf(res);
            } else {
                tokens[topIndex++] = token;
            }
        }

        return Integer.parseInt(tokens[0]);
    }
}

/**
 * Using Stack to store the numbers and their result. (LinkedIn Follow-Up)
 *
 * Includes: Factorial, Less Than Equal To, and Greater Than Equal To.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 */
public class ReversePolishNotation {

    public int evalRPN(String[] tokens) throws IllegalArgumentException, ArithmeticException {
        if (tokens == null) {
            throw new IllegalArgumentException("Input tokens array is null");
        }
        if (tokens.length == 0) {
            return 0;
        }

        Stack<Integer> stack = new Stack<>();
        HashSet<String> operators = new HashSet<>(Arrays.asList("/", "*", "+", "-", "!", "<=", ">="));

        for (String token : tokens) {
            if (token == null || token.length() == 0) {
                throw new IllegalArgumentException("Invalid RPN Expression");
            }

            if (operators.contains(token)) {
                int[] nums;

                switch (token) {
                    case "/":
                        nums = getTwoNumbers(stack);
                        if (nums[1] == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        stack.push(nums[0] / nums[1]);
                        break;
                    case "*":
                        nums = getTwoNumbers(stack);
                        stack.push(nums[0] * nums[1]);
                        break;
                    case "+":
                        nums = getTwoNumbers(stack);
                        stack.push(nums[0] + nums[1]);
                        break;
                    case "-":
                        nums = getTwoNumbers(stack);
                        stack.push(nums[0] - nums[1]);
                        break;
                    case "!":
                        stack.push(fact(getOneNumber(stack)));
                        break;
                    case "<=":
                        nums = getTwoNumbers(stack);
                        stack.push(nums[0] <= nums[1] ? 1 : 0);
                        break;
                    case ">=":
                        nums = getTwoNumbers(stack);
                        stack.push(nums[0] >= nums[1] ? 1 : 0);
                        break;
                    default:
                        throw new IllegalArgumentException("Operator not supported");
                }
            } else {
                try {
                    stack.push(Integer.parseInt(token));
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("Invalid RPN Expression: Token not supported: " + token);
                }
            }
        }

        if (stack.size() != 1) {
            throw new IllegalArgumentException("Invalid RPN Expression");
        }

        return stack.pop();
    }

    private int[] getTwoNumbers(Stack<Integer> stack) throws IllegalArgumentException {
        if (stack.size() < 2) {
            throw new IllegalArgumentException("Invalid RPN Expression");
        }

        int num2 = stack.pop();
        int num1 = stack.pop();

        return new int[] { num1, num2 };
    }

    private int getOneNumber(Stack<Integer> stack) throws IllegalArgumentException {
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid RPN Expression");
        }

        return stack.pop();
    }

    private int fact(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
