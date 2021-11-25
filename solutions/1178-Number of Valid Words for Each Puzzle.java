import java.util.*;

class Solution {
    public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
        if (words == null || puzzles == null) {
            throw new IllegalArgumentException("Input is invalid");
        }

        int puzzlesLen = puzzles.length;
        List<Integer> result = new ArrayList<>(puzzlesLen);
        if (puzzlesLen == 0 || words.length == 0) {
            for (int i = 0; i < puzzlesLen; i++) {
                result.add(0);
            }
            return result;
        }

        // This map will store the count of unique word masks
        HashMap<Integer, Integer> wordMaskCountMap = new HashMap<>();
        for (String word : words) {
            int wordMask = getMask(word, 0);
            wordMaskCountMap.put(wordMask, wordMaskCountMap.getOrDefault(wordMask, 0) + 1);
        }

        for (String puzzle : puzzles) {
            // We will only create subsets of last 6 characters because 1st character needs
            // to be present in all words
            int puzzleMask = getMask(puzzle, 1);
            int firstCharMask = 1 << (puzzle.charAt(0) - 'a');
            int subsetMask = puzzleMask;
            int count = wordMaskCountMap.getOrDefault(firstCharMask, 0);

            while (subsetMask != 0) {
                // Check if the (puzzle subset + first character) is present in the
                // wordMaskCountMap map and add its count.
                count += wordMaskCountMap.getOrDefault(subsetMask | firstCharMask, 0);

                // Get next subset of the puzzle.
                subsetMask = (subsetMask - 1) & puzzleMask;
            }

            result.add(count);
        }

        return result;
    }

    // In this function, we are generating a bit mask of each String.
    // Here we set 0th bit for 'a', 1st bit for 'b', and so on.
    // We will be setting upto 26 bits only.
    private static int getMask(String s, int start) {
        int mask = 0;
        for (int i = start; i < s.length(); i++) {
            mask |= 1 << (s.charAt(i) - 'a');
        }
        return mask;
    }
}
