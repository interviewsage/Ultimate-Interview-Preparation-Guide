// LeetCode Question URL: https://leetcode.com/problems/group-shifted-strings/

import java.util.*;

/**
 * Generate a key for each word to group them.
 *
 * Consider acf and pru. Now notice the difference between each characters. acf
 * = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case,
 * you can simply use integers ASCII difference to form key.
 *
 * Now consider corner case, az and ba, where az = 0->25 and ba = 0->-1. When it
 * goes negative, just make it positive(rotate or mod equivalent) by adding 26.
 * So it becomes, ba = 0->25, which forms same group.
 *
 * Refer:
 * https://leetcode.com/problems/group-shifted-strings/discuss/67442/My-Concise-JAVA-Solution/69322
 *
 * <pre>
 * Time Complexity: O(N * 2L + N) = O(N * L)
 *
 * 1. O(N * 2L) -> Time to group similar strings.
 * 2. O(N) -> Time to create final result list.
 * </pre>
 *
 * Space Complexity: O(N * L + N) = O(N * L)
 *
 * N = Number of words. L = Average length of all words.
 */
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        if (strings == null) {
            throw new IllegalArgumentException("Input is null");
        }

        // if (strings.length <= 1) {
        // List<List<String>> result = new ArrayList<>();
        // result.add(Arrays.asList(strings));
        // return result;
        // }

        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strings) {
            String signature = getSignature(s);
            groups.putIfAbsent(signature, new ArrayList<>());
            groups.get(signature).add(s);
        }

        return new ArrayList<>(groups.values());
    }

    private String getSignature(String s) {
        int len = s.length();
        if (len <= 1) {
            return len == 0 ? null : "";
        }

        StringBuilder sb = new StringBuilder(len - 1);
        for (int i = 1; i < len; i++) {
            sb.append((char) ((s.charAt(i) - s.charAt(i - 1) + 26) % 26));
        }
        return sb.toString();
    }
}
