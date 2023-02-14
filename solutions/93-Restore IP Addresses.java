// LeetCode Question URL: https://leetcode.com/problems/restore-ip-addresses/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer this link for backtracking solution
 * Approach 1: Backtracking (DFS) - https://leetcode.com/problems/restore-ip-addresses/solution/
*/

/**
 * Time Complexity : O(3^3* 12*3) = O(27) = O(1) --> 12 * 3 for substring,
 * stringbuilder, toString.
 * --> assuming substring takes O(1). In newer versions of Java substring takes
 * O(N).. then total complexity will become O(N).
 *
 * For every i, j runs thrice. For every j, k runs thrice. Thus for every i, k
 * runs 3*3 times. Since i runs thrice too, all for loops will run for 3*3*3
 * times.
 *
 * Space Complexity: O(12) .. This is required for Substring
 *
 * N = Length of the input string
 */
class Solution1 {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }

        int len = s.length();

        for (int i = Math.max(1, len - 9); i <= Math.min(3, len - 3); i++) {
            String o1 = s.substring(0, i);
            if (!isValidOctet(o1)) {
                break;
            }
            StringBuilder sb = new StringBuilder(o1).append('.');
            int sbLen1 = sb.length();

            for (int j = Math.max(i + 1, len - 6); j <= Math.min(i + 3, len - 2); j++) {
                String o2 = s.substring(i, j);
                if (!isValidOctet(o2)) {
                    break;
                }
                sb.append(o2).append('.');
                int sbLen2 = sb.length();

                for (int k = Math.max(j + 1, len - 3); k <= Math.min(j + 3, len - 1); k++) {
                    String o3 = s.substring(j, k);
                    if (!isValidOctet(o3)) {
                        break;
                    }
                    String o4 = s.substring(k, len);
                    if (!isValidOctet(o4)) {
                        continue;
                    }
                    sb.append(o3).append('.').append(o4);
                    result.add(sb.toString());
                    sb.setLength(sbLen2);
                }

                sb.setLength(sbLen1);
            }
        }

        return result;
    }

    private boolean isValidOctet(String o) {
        try {
            return Character.isDigit(o.charAt(0)) && !(o.length() > 1 && o.charAt(0) == '0')
                    && Integer.parseInt(o) <= 255;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

class Solution2 {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }

        int len = s.length();
        for (int i = 0; i < len; i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return result;
            }
        }

        for (int i = Math.max(0, len - 10); i <= Math.min(2, len - 4); i++) {
            StringBuilder sb = new StringBuilder();
            if (!isValidToken(s, 0, i, sb)) {
                continue;
            }
            sb.append('.');
            int sbLen1 = sb.length();

            for (int j = Math.max(i + 1, len - 7); j <= Math.min(i + 3, len - 3); j++) {
                sb.setLength(sbLen1);
                if (!isValidToken(s, i + 1, j, sb)) {
                    continue;
                }
                sb.append('.');
                int sbLen2 = sb.length();

                for (int k = Math.max(j + 1, len - 4); k <= Math.min(j + 3, len - 2); k++) {
                    if (len - 1 - k > 3) {
                        continue;
                    }
                    sb.setLength(sbLen2);
                    if (!isValidToken(s, j + 1, k, sb)) {
                        continue;
                    }
                    sb.append('.');
                    if (!isValidToken(s, k + 1, len - 1, sb)) {
                        continue;
                    }
                    result.add(sb.toString());
                }
            }
        }

        return result;
    }

    private boolean isValidToken(String s, int start, int end, StringBuilder sb) {
        if (start < end && s.charAt(start) == '0') {
            return false;
        }

        int val = 0;
        for (int i = start; i <= end; i++) {
            char c = s.charAt(i);
            val = val * 10 + (c - '0');
            sb.append(c);
        }
        return val <= 255;
    }
}

class Solution3 {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }

        int len = s.length();

        for (int i = 1; i < 4 && i < len - 2; i++) {
            String o1 = s.substring(0, i);
            if (!isValidOctet(o1)) {
                break;
            }

            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                String o2 = s.substring(i, j);
                if (!isValidOctet(o2)) {
                    continue;
                }

                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String o3 = s.substring(j, k);
                    String o4 = s.substring(k, len);
                    if (isValidOctet(o3) && isValidOctet(o4)) {
                        result.add(o1 + "." + o2 + "." + o3 + "." + o4);
                    }
                }
            }
        }

        return result;
    }

    private boolean isValidOctet(String o) {
        if (o.length() == 0 || o.length() > 3 || (o.length() > 1 && o.charAt(0) == '0') || Integer.parseInt(o) > 255) {
            return false;
        }

        return true;
    }
}
