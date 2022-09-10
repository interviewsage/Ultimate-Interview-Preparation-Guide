// LeetCode Question URL: https://leetcode.com/problems/simplify-path/

import java.util.*;

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of input path.
 */
class Solution {
    public String simplifyPath(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Input is null");
        }

        int len = path.length();
        if (len <= 1) {
            return "/";
        }

        Deque<int[]> deque = new ArrayDeque<>();
        int i = 0;

        while (i < len) {
            if (path.charAt(i) == '/') {
                i++;
                continue;
            }

            int start = i;
            while (i < len && path.charAt(i) != '/') {
                i++;
            }

            int dirLen = i - start;
            if (dirLen == 1 && path.charAt(start) == '.') {
                continue;
            }
            if (dirLen == 2 && path.charAt(start) == '.' && path.charAt(start + 1) == '.') {
                deque.pollLast();
                continue;
            }

            deque.addLast(new int[] { start, i });
        }

        if (deque.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();
        for (int[] dir : deque) {
            result.append('/').append(path.substring(dir[0], dir[1]));
        }
        return result.toString();
    }
}

/**
 * <pre>
 * Time Complexity: O(N)
 * 1. O(N) - Each character will be traversed once.
 * 2. O(N) - Each character will be added to StringBuilder.
 * 3. O(N) - sb.toString()
 * </pre>
 *
 * <pre>
 * Space Complexity: O(N)
 * 1. StringBuilder can take upto N characters.
 * 2. Stack will store an integer for each valid directory. (This is also bounded by O(N))
 * </pre>
 *
 * N = Length of input path.
 */
class Solution2 {
    public String simplifyPath(String path) {
        if (path == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        if (path.length() <= 1) {
            return "/";
        }

        // Stack is used to store the length of StringBuilder until previous directory.
        Deque<Integer> stack = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int i = 0;
        int len = path.length();

        while (i < len) {
            if (path.charAt(i) == '/') {
                i++;
                continue;
            }

            StringBuilder dir = new StringBuilder();
            while (i < len && path.charAt(i) != '/') {
                dir.append(path.charAt(i));
                i++;
            }

            String dirName = dir.toString();
            switch (dirName) {
                case ".":
                    break;
                case "..":
                    if (!stack.isEmpty()) {
                        sb.setLength(stack.pop());
                    }
                    break;
                default:
                    stack.push(sb.length());
                    sb.append('/').append(dirName);
            }
        }

        return sb.length() != 0 ? sb.toString() : "/";
    }
}

/**
 * Split path and save in Deque.
 *
 * Time Complexity: O(N + N) = O(N). Length of whole string is traversed twice.
 *
 * Space Complexity: O(N). Space taken by deque and StringBuilder.
 *
 * N = Length of the input string.
 */
class Solution3 {
    public String simplifyPath(String path) {
        if (path == null || path.length() == 0) {
            return "/";
        }

        Deque<String> dq = new LinkedList<>();

        for (String d : path.split("/")) {
            if (d.length() == 0 || d.equals(".")) {
                continue;
            }
            if (d.equals("..")) {
                if (!dq.isEmpty()) {
                    dq.pollLast();
                }
                continue;
            }
            dq.offerLast(d);
        }

        if (dq.isEmpty()) {
            return "/";
        }

        StringBuilder result = new StringBuilder();
        while (!dq.isEmpty()) {
            result.append("/").append(dq.pollFirst());
        }

        return result.toString();
    }
}