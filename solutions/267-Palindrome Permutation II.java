// LeetCode Question URL: https://leetcode.com/problems/palindrome-permutation-ii/
// LeetCode Discuss URL: https://leetcode.com/problems/palindrome-permutation-ii/discuss/1527948/Java-or-TC:-O(N*(N2)!)-or-SC:-O(N)-or-Optimal-Backtracking-using-CountMap

import java.util.*;

/**
 * This solution uses a combination of solutions for questions 47-Permutations
 * II and 266-Palindrome Permutation
 *
 * <pre>
 * Time Complexity:
 *      O(N) --> To create countMap, check if palindrome is possible or not and find midChar.
 *      O(3*N * (N/2)!) --> To find all permutations.
 * Total Time Complexity = O(N + 3*N*(N/2)!) = O(N * (N/2)!)
 *
 * Space Complexity:
 *      O(N) --> countMap
 *      O(N/2) --> oddCharSet
 *      O(N/2) --> StringBuilder to be used for backtracking.
 *      O(N/2) --> Recursion Depth
 *      O(N) --> StringBuilder to generate final string
 * Total Space Complexity = O(N)
 * </pre>
 *
 * N = Length of Input String.
 */
class Solution1 {
    public List<String> generatePalindromes(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Input string is null");
        }

        int len = s.length();
        List<String> result = new ArrayList<>();
        if (len <= 1) {
            result.add(s);
            return result;
        }

        Map<Character, Integer> countMap = new HashMap<>();
        Set<Character> oddCharSet = new HashSet<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            // Below condition is a simplification of (oddCharSet.size() - (sLen-1-i)) >= 2
            if (oddCharSet.add(c)) {
                if (oddCharSet.size() > len - i) {
                    return result;
                }
            } else {
                oddCharSet.remove(c);
            }
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }

        // All characters are same, thus only one permutation is possible.
        if (countMap.size() == 1) {
            result.add(s);
            return result;
        }

        Character midChar = null;
        if (oddCharSet.size() == 1) {
            midChar = oddCharSet.iterator().next();
            int midCount = countMap.get(midChar);
            if (midCount == 1) {
                countMap.remove(midChar);
            } else {
                countMap.put(midChar, midCount - 1);
            }
        }

        generatePalindromesHelper(result, countMap, midChar, new StringBuilder(), len / 2);

        return result;
    }

    private void generatePalindromesHelper(List<String> result, Map<Character, Integer> countMap, Character midChar,
            StringBuilder sb, int length) {
        if (length == 0) {
            StringBuilder sbCopy = new StringBuilder(sb);
            if (midChar != null) {
                sbCopy.append(midChar);
            }
            sbCopy.append(sb.reverse());
            // sbCopy + midChar + sb.reverse()
            result.add(sbCopy.toString());
            sb.reverse();
            return;
        }

        for (char c : countMap.keySet()) {
            int count = countMap.get(c);
            if (count == 0) {
                continue;
            }
            countMap.put(c, count - 2);
            sb.append(c);
            generatePalindromesHelper(result, countMap, midChar, sb, length - 1);
            sb.setLength(sb.length() - 1);
            countMap.put(c, count);
        }
    }
}

/**
 * Basically, the idea is to perform permutation on half of the palindromic
 * string and then form the full palindromic result.
 *
 * Time Complexity : O(N + N + N*(N/2)!) = O(N * (N/2)!)
 *
 * Space Complexity : O(N + N/2 + N/2) = O(N)
 *
 * N = Length of input string.
 */
class Solution2 {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        if (s.length() < 2) {
            result.add(s);
            return result;
        }

        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        String midChar = "";

        for (char c : map.keySet()) {
            int cnt = map.get(c);
            if (cnt % 2 != 0) {
                if (midChar.length() != 0) {
                    return result;
                }
                midChar += c;
            }
            for (int i = 0; i < cnt / 2; i++) {
                sb.append(c);
            }
        }

        char[] chArr = sb.toString().toCharArray();

        // Below sorting is not required as sb will be have same chars together.
        // Arrays.sort(chArr);

        helper(result, new StringBuilder(), chArr, new boolean[chArr.length], midChar);

        return result;
    }

    private void helper(List<String> result, StringBuilder sb, char[] s, boolean[] used, String mid) {
        if (sb.length() == s.length) {
            result.add(sb.toString() + mid + sb.reverse().toString());
            sb.reverse();
            return;
        }

        for (int i = 0; i < s.length; i++) {
            if (used[i]) {
                continue;
            }
            if (i > 0 && s[i - 1] == s[i] && !used[i - 1]) {
                continue;
            }

            used[i] = true;
            sb.append(s[i]);
            helper(result, sb, s, used, mid);
            used[i] = false;
            sb.setLength(sb.length() - 1);
        }
    }
}
