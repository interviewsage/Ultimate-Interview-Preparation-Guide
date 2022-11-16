// LeetCode Question URL: https://leetcode.com/problems/group-anagrams/
// LeetCode Discuss URL: https://leetcode.com/problems/group-anagrams/discuss/1551701/Java-or-TC:-O(TotalChars+N)-or-SC:-O(N)-or-Group-by-signature-of-each-string

import java.util.*;

/**
 * Categorize by Count of characters
 *
 * <pre>
 * Time Complexity:
 * - O(TotalChars + 26*11*2N) -> To calculate the count of characters and create the signature using StringBuilder.
 * - O(N) -> to generate final result List of groups.
 *
 * Total Time Complexity = O(TotalChars + N)
 *
 * Space Complexity:
 * - O(26*11*N) -> To save signatures in HashMap.
 *                 We do not need to count the space required for values in HashMap as they will be used in result.
 * Total Space Complexity = O(N)
 * </pre>
 *
 * N = Number of strings in strs array.
 */
class Solution1 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        List<List<String>> result = new ArrayList<>();
        int len = strs.length;
        if (len == 0) {
            return result;
        }
        if (len == 1) {
            result.add(Arrays.asList(strs[0]));
            return result;
        }

        Map<String, List<String>> groups = new HashMap<>();
        for (String s : strs) {
            String signature = getSignature(s);
            groups.putIfAbsent(signature, new ArrayList<>());
            groups.get(signature).add(s);
        }

        result.addAll(groups.values());
        return result;
    }

    private String getSignature(String s) {
        int[] charCount = new int[26];
        for (int i = 0; i < s.length(); i++) {
            charCount[s.charAt(i) - 'a']++;
        }

        // Use a HashMap and then put the keySet in a TreeSet if characters-set is
        // big or un-defined.
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            if (charCount[i] != 0) {
                sb.append((char) ('a' + i)).append(charCount[i]);
            }
        }
        return sb.toString();
    }
}

/**
 * Categorize by Sorted String
 *
 * Time Complexity: O(N*K * logK). The outer loop has complexity O(N) as we
 * iterate through each string. Then, we sort each string in O(K*logK) time.
 *
 * Space Complexity: O(N*K), the total information content stored in map.
 *
 * N = Length of strs array. K = Maximum Length of a string in strs.
 */
class Solution2 {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] cA = s.toCharArray();
            Arrays.sort(cA);
            String sorted = new String(cA);
            if (!map.containsKey(sorted)) {
                map.put(sorted, new ArrayList<>());
            }
            map.get(sorted).add(s);
        }

        return new ArrayList<>(map.values());
    }
}
