// LeetCode Question URL: https://leetcode.com/problems/defanging-an-ip-address/
// LeetCode Discuss URL:

/**
 * Scan the array, insert "[.]" instead of '.' in the string builder.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N) for string builder.
 *
 * N = Length of the input array.
 */
class Solution1 {
    public String defangIPaddr(String address) {
        if (address == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            sb.append(c == '.' ? "[.]" : c);
        }
        return sb.toString();
    }
}

class Solution2 {
    public String defangIPaddr(String address) {
        if (address == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            char c = address.charAt(i);
            if (c == '.') {
                sb.append("[.]");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }
}
