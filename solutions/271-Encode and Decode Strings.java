// LeetCode Question URL: https://leetcode.com/problems/encode-and-decode-strings/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Refer: Approach 2: Chunked Transfer Encoding -
 * https://leetcode.com/problems/encode-and-decode-strings/solution/
 *
 * In this solution, encoding the length to a char array of size 2. In Java int
 * is 4 bytes and char is 2 bytes. Thus 2 chars can represent an int. This helps
 * us in saving space.
 *
 * Encode function
 *
 * Time Complexity: O(2 * N * (l + 2))
 *
 * Space Complexity: O(N * (l + 2))
 *
 * N = Number of strings. l = average length of each string.
 *
 *
 * Decode function
 *
 * Time Complexity: O(L)
 *
 * Space Complexity: O(1) -> Excluding the result.
 *
 * L = Length of the input string.
 */
class Codec {

    private static final char[] MINUS_ONE_CHAR_ARR = numToCharArray(-1);

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null) {
            throw new IllegalArgumentException("Input strs list is null");
        }

        StringBuilder sb = new StringBuilder();
        for (String s : strs) {
            if (s == null) {
                sb.append(MINUS_ONE_CHAR_ARR);
            } else {
                sb.append(numToCharArray(s.length())).append(s);
            }
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        List<String> result = new ArrayList<>();
        int idx = 0;
        while (idx < s.length()) {
            int val = s.charAt(idx++);
            val = (val << 16) + s.charAt(idx++);

            if (val == -1) {
                result.add(null);
            } else {
                result.add(s.substring(idx, idx + val));
                idx += val;
            }
        }

        return result;
    }

    private static char[] numToCharArray(int num) {
        char[] chArr = new char[2];
        chArr[0] = (char) (num >>> 16);
        chArr[1] = (char) (num & 0xffff);
        return chArr;
    }
}

class Codec2 {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        if (strs == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (str == null) {
                sb.append('#');
            } else {
                sb.append(str.length()).append('#').append(str);
            }
        }
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        if (s == null) {
            return null;
        }

        List<String> result = new ArrayList<>();
        int len = s.length();
        int idx = 0;

        while (idx < len) {
            if (s.charAt(idx) == '#') {
                result.add(null);
                idx++;
                continue;
            }

            int val = 0;
            while (idx < len && s.charAt(idx) != '#') {
                val = val * 10 + (s.charAt(idx++) - '0');
            }

            result.add(s.substring(idx + 1, idx + 1 + val));
            idx += val + 1;
        }

        return result;
    }
}

class Codec3 {

    // Encodes a list of strings to a single string.
    public String encode(List<String> strings) {
        if (strings == null || strings.size() == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (String s : strings) {
            sb.append(s.length());
            sb.append("#");
            sb.append(s);
        }

        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) {
            return result;
        }

        int i = 0;
        while (i < s.length()) {
            int idx = s.indexOf("#", i);
            int len = Integer.parseInt(s.substring(i, idx));
            String w = s.substring(idx + 1, idx + len + 1);
            result.add(w);
            i = idx + len + 1;
        }

        return result;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(strings));