// LeetCode Question URL: https://leetcode.com/problems/reverse-words-in-a-string/
// LeetCode Discuss URL:

/**
 * Refer:
 * https://leetcode.com/problems/reverse-words-in-a-string/discuss/47740/In-place-simple-solution
 *
 * Reverse each word and then reverse the whole sentence.
 *
 * Time Complexity: O(5*N) = O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution1 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        char[] charArr = s.toCharArray();
        int len = charArr.length;
        int i = 0;
        int insertPos = 0;

        while (i < len) {
            if (charArr[i] == ' ') {
                i++;
                continue;
            }

            if (insertPos > 0) {
                charArr[insertPos++] = ' ';
            }

            int wordStart = insertPos;
            while (i < len && charArr[i] != ' ') {
                charArr[insertPos++] = charArr[i++];
            }
            i++;
            reverseRange(charArr, wordStart, insertPos - 1);
        }

        reverseRange(charArr, 0, insertPos - 1);
        return new String(charArr, 0, insertPos);
    }

    private void reverseRange(char[] charArr, int start, int end) {
        while (start < end) {
            char tmp = charArr[start];
            charArr[start] = charArr[end];
            charArr[end] = tmp;
            start++;
            end--;
        }
    }
}

/**
 * Reverse each word and then reverse the whole sentence.
 *
 * Time Complexity: O(5*N) = O(N)
 *
 * Space Complexity: O(2*N)
 *
 * N = Length of the input array.
 */
class Solution2 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        StringBuilder sb = new StringBuilder();
        int len = s.length();
        int i = 0;

        while (i < len) {
            if (s.charAt(i) == ' ') {
                i++;
                continue;
            }

            StringBuilder word = new StringBuilder();
            while (i < len && s.charAt(i) != ' ') {
                word.append(s.charAt(i++));
            }
            i++;

            if (sb.length() > 0) {
                sb.append(' ');
            }
            sb.append(word.reverse());
        }

        return sb.reverse().toString();
    }
}

class SolutionGeneralized {
    public String reverseWords(String s) {
        if (s == null) {
            return s;
        }

        char[] charArr = s.toCharArray();
        int finalPos = reverseWordsCharArr(charArr);
        return new String(charArr, 0, finalPos);
    }

    public int reverseWordsCharArr(char[] charArr) {
        if (charArr == null) {
            throw new IllegalArgumentException("Input array is null");
        }

        int len = charArr.length;
        if (len == 0) {
            return 0;
        }

        int i = 0;
        int insertPos = 0;

        while (i < len) {
            if (charArr[i] == ' ') {
                i++;
                continue;
            }

            if (insertPos > 0) {
                charArr[insertPos++] = ' ';
            }

            int wordStart = insertPos;
            while (i < len && charArr[i] != ' ') {
                charArr[insertPos++] = charArr[i++];
            }
            i++;

            reverseRange(charArr, wordStart, insertPos - 1);
        }

        reverseRange(charArr, 0, insertPos - 1);
        return insertPos;
    }

    private void reverseRange(char[] charArr, int start, int end) {
        while (start < end) {
            char tmp = charArr[start];
            charArr[start] = charArr[end];
            charArr[end] = tmp;
            start++;
            end--;
        }
    }
}

/**
 * Reverse each word and then reverse the whole sentence.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution3 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        String[] words = s.split(" ");

        StringBuilder result = new StringBuilder();

        for (String w : words) {
            if (w.length() == 0) {
                continue;
            }
            if (result.length() != 0) {
                result.append(" ");
            }
            result.append(new StringBuilder(w).reverse());
        }

        return result.reverse().toString();
    }
}

/**
 * Reverse each word and then reverse the whole sentence.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = Length of the input array.
 */
class Solution4 {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }

        StringBuilder result = new StringBuilder();
        StringBuilder word = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c != ' ') {
                word.append(c);
            } else {
                if (word.length() == 0) {
                    continue;
                }

                if (result.length() != 0) {
                    result.append(" ");
                }
                result.append(word.reverse());
                word.setLength(0);
            }
        }

        if (word.length() != 0) {
            if (result.length() != 0) {
                result.append(" ");
            }
            result.append(word.reverse());
        }

        return result.reverse().toString();
    }
}
