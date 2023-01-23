// LeetCode Question URL: https://leetcode.com/problems/groups-of-special-equivalent-strings/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Find signature of each string and then put that in a HashSet. Size of set is
 * the result.
 *
 * Time Complexity: O(N * (L + 52*12)) = O(N * L)
 *
 * Space Complexity: O(N * 52*12) = O(N)
 *
 * N = Number of words. L = Average length of all words.
 */
class Solution1 {
    public int numSpecialEquivGroups(String[] words) {
        if (words == null) {
            throw new IllegalArgumentException("Input words array is null");
        }

        if (words.length <= 1) {
            return words.length;
        }

        Set<String> signatures = new HashSet<>();
        for (String w : words) {
            int[] count = new int[52];
            for (int i = 0; i < w.length(); i++) {
                int chIdx = w.charAt(i) - 'a';
                if ((i & 1) == 0) {
                    // Even Index
                    count[chIdx]++;
                } else {
                    // Odd Index
                    count[chIdx + 26]++;
                }
            }

            signatures.add(Arrays.toString(count));
        }

        return signatures.size();
    }
}

class Solution2 {
    public int numSpecialEquivGroups(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }
        if (words.length == 1) {
            return 1;
        }

        Set<String> signatures = new HashSet<>();

        for (String w : words) {
            int[] countArr = new int[52];

            for (int i = 0; i < w.length(); i++) {
                int idx = w.charAt(i) - 'a';
                if ((i & 1) == 0) {
                    // Even Indexes
                    countArr[26 + idx]++;
                } else {
                    // Odd Indexes
                    countArr[idx]++;
                }
            }

            signatures.add(convertArrayToString(countArr));
        }

        return signatures.size();
    }

    private String convertArrayToString(int[] arr) {
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]).append(',').append(arr[26]);

        for (int i = 1; i < 26; i++) {
            sb.append(',').append(arr[i]);
            sb.append(',').append(arr[26 + i]);
        }

        return sb.toString();
    }
}

class Solution3 {
    public int numSpecialEquivGroups(String[] words) {
        if (words == null || words.length == 0) {
            return 0;
        }

        if (words.length == 1) {
            return 1;
        }

        Set<String> groups = new HashSet<>();
        for (String word : words) {
            int[] even = new int[26];
            int[] odd = new int[26];
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (i % 2 == 0) {
                    even[c - 'a']++;
                } else {
                    odd[c - 'a']++;
                }
            }
            StringBuilder sb = new StringBuilder();
            sb.append(even[0]).append(',').append(odd[0]);
            for (int i = 1; i < 26; i++) {
                sb.append(',').append(even[i]);
                sb.append(',').append(odd[i]);
            }
            groups.add(sb.toString());
        }

        return groups.size();
    }
}
