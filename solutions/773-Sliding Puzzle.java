// LeetCode Question URL: https://leetcode.com/problems/sliding-puzzle/
// LeetCode Discuss URL: https://leetcode.com/problems/sliding-puzzle/discuss/1524764/Java-or-TC:-O((MN)!)-or-SC-:-O((MN)!)-or-Bit-Manipulation-+-Double-Ended-BFS

import java.util.*;

/**
 * Double-Ended BFS
 *
 * Find possible zero moves for each zero location. Use this to find next states
 * of current state. Perform Double-Ended BFS to find the total number of moves.
 *
 * <pre>
 * Time Complexity: O(V + E).
 * V = Number of possible states = (R*C)!. Each state generation takes R*C time.
 * E = 3 * V, because there can be a maximum of 3 edges from each vertex. Also, each state will take R*C time for String generation.
 * Total TC = O(RC*(RC)! + RC*3*(RC)!) = O(RC*(RC)!) = O(4 * 6 * 6!)
 * </pre>
 *
 * Space Complexity: O(R*C * (R*C)!) to save each state in begin, end and
 * visited collections. SC = O(R*C * (R*C)!) = O(6 * 6!)
 *
 * R = Number of rows. C = Number of cols.
 */
class Solution1 {

    private static final String FINAL_STATE = "123450";
    private static final int[][] DIRS = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length != 2 || board[0].length != 3) {
            throw new IllegalArgumentException("Input board is invalid");
        }

        StringBuilder sb = new StringBuilder();
        int zeroIdx = -1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    if (zeroIdx != -1) {
                        throw new IllegalArgumentException("Multiple zeros");
                    }
                    zeroIdx = 3 * i + j;
                }
                sb.append(board[i][j]);
            }
        }

        if (zeroIdx == -1) {
            throw new IllegalArgumentException("No zeros");
        }

        final String inputState = sb.toString();
        if (FINAL_STATE.equals(inputState)) {
            return 0;
        }

        Set<String> visitedStates = new HashSet<>();
        int moves = 0;

        Map<String, Integer> beginMap = new HashMap<>();
        beginMap.put(inputState, zeroIdx);
        visitedStates.add(inputState);

        Map<String, Integer> endMap = new HashMap<>();
        endMap.put(FINAL_STATE, 5);
        visitedStates.add(FINAL_STATE);

        while (!beginMap.isEmpty()) {
            if (beginMap.size() > endMap.size()) {
                Map<String, Integer> t = beginMap;
                beginMap = endMap;
                endMap = t;
            }

            Map<String, Integer> nextLevel = new HashMap<>();
            moves++;
            for (String curState : beginMap.keySet()) {
                char[] curStateArr = curState.toCharArray();
                int curZeroIdx = beginMap.get(curState);

                for (int d : DIRS[curZeroIdx]) {
                    swap(curStateArr, curZeroIdx, d);
                    String nextState = new String(curStateArr);
                    if (endMap.containsKey(nextState)) {
                        return moves;
                    }
                    if (visitedStates.add(nextState)) {
                        nextLevel.put(nextState, d);
                    }
                    swap(curStateArr, curZeroIdx, d);
                }
            }

            beginMap = nextLevel;
        }

        return -1;
    }

    private void swap(char[] chArr, int a, int b) {
        if (a != b) {
            char t = chArr[a];
            chArr[a] = chArr[b];
            chArr[b] = t;
        }
    }
}

/**
 * Double-Ended BFS + Bit Manipulation
 *
 * Find possible zero moves for each zero location. Use this to find next states
 * of current state. Perform Double-Ended BFS to find the total number of moves.
 *
 * To save space, we are using a single integer to represent a state. Since our
 * number is between 0 & 5, it will take maximum 3 bits for each number. Thus we
 * will need only 18 bits to store each state.
 *
 * <pre>
 * Time Complexity: O(V + E).
 * V = Number of possible states = (M*N)!.
 * E = 3 * V, because there can be a maximum of 3 edges from each vertex.
 * Total TC = O((MN)! + 3*(MN)!) = O((MN)!) = O(4 * 6!)
 * </pre>
 *
 * Space Complexity: O((MN)!) to save each state in begin, end and visited
 * collections = O(6!)
 *
 * M = Number of rows. N = Number of cols.
 */
class Solution2 {
    // 3-Bit Binary representation of 123450
    private static final int FINAL_STATE = 0b001010011100101000;
    // Possible destination of zero. Here DIRS[0] represents the most significant
    // 3-Bit number in the state. And, DIRS[5] represents the least significant
    // 3-Bit number in the state.
    private static final int[][] DIRS = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length != 2 || board[0].length != 3) {
            throw new IllegalArgumentException("Input board is invalid");
        }

        int zeroIdx = -1;
        int curState = 0;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                // Inserting the num at the end of integer
                curState = (curState << 3) | board[i][j];
                if (board[i][j] == 0) {
                    zeroIdx = i * 3 + j;
                }
            }
        }

        if (FINAL_STATE == curState) {
            return 0;
        }

        HashSet<Integer> visited = new HashSet<>();
        int moves = 0;

        HashMap<Integer, Integer> begin = new HashMap<>();
        begin.put(curState, zeroIdx);
        visited.add(curState);

        HashMap<Integer, Integer> end = new HashMap<>();
        end.put(FINAL_STATE, 5);
        visited.add(FINAL_STATE);

        while (!begin.isEmpty()) {
            if (begin.size() > end.size()) {
                HashMap<Integer, Integer> tempSet = begin;
                begin = end;
                end = tempSet;
            }

            HashMap<Integer, Integer> next = new HashMap<>();
            moves++;
            for (int cur : begin.keySet()) {
                zeroIdx = begin.get(cur);

                for (int d : DIRS[zeroIdx]) {
                    int newState = swap(cur, zeroIdx, d);
                    if (end.containsKey(newState)) {
                        return moves;
                    }
                    if (visited.add(newState)) {
                        next.put(newState, d);
                    }
                }
            }
            begin = next;
        }

        return -1;
    }

    private int swap(int state, int zeroIdx, int destIdx) {
        // Generate mask for destIdx
        int mask = 0b111 << ((5 - destIdx) * 3);
        // Extracting num at destIdx
        int num = state & mask;
        // Moving num to zeroIdx
        if (zeroIdx < destIdx) {
            num <<= (destIdx - zeroIdx) * 3;
        } else {
            num >>>= (zeroIdx - destIdx) * 3;
        }

        // Setting zero at destIdx
        state &= ~mask;
        // Setting num at zeroIdx
        return state | num;
    }
}

/**
 * BFS
 *
 * Find possible zero moves for each zero location. Use this to find next states
 * of current state. Perform BFS to find the destination.
 *
 * <pre>
 * Time Complexity: O(V + E).
 * V = Number of possible states = (R*C)!. Each state generation takes R*C time.
 * E = 3 * V, because there can be a maximum of 3 edges from each vertex. Also, each state will take R*C time for String generation.
 * Total TC = O(RC*(RC)! + RC*3*(RC)!) = O(RC*(RC)!) = O(4 * 6 * 6!)
 * </pre>
 *
 * Space Complexity: O(R*C * (R*C)!) to save each state in begin, end and
 * visited collections. SC = O(R*C * (R*C)!) = O(6 * 6!)
 *
 * R = Number of rows. C = Number of cols.
 */
class Solution3 {
    private static final String SOLVED = "123450";
    private static final int[][] DIRS = { { 1, 3 }, { 0, 2, 4 }, { 1, 5 }, { 0, 4 }, { 1, 3, 5 }, { 2, 4 } };

    public int slidingPuzzle(int[][] board) {
        if (board == null || board.length != 2 || board[0].length != 3) {
            throw new IllegalArgumentException("Input board is invalid");
        }

        int zeroIdx = -1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
                if (board[i][j] == 0) {
                    zeroIdx = i * 3 + j;
                }
            }
        }
        String inputState = sb.toString();
        if (SOLVED.equals(inputState)) {
            return 0;
        }

        HashSet<String> visited = new HashSet<>();
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        visited.add(inputState);
        queue.offer(new Pair<>(inputState, zeroIdx));
        int moves = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            moves++;

            for (int i = 0; i < levelSize; i++) {
                Pair<String, Integer> cur = queue.poll();
                char[] curState = cur.getKey().toCharArray();
                zeroIdx = cur.getValue();

                for (int d : DIRS[zeroIdx]) {
                    swap(curState, zeroIdx, d);
                    String newState = new String(curState);
                    if (SOLVED.equals(newState)) {
                        return moves;
                    }
                    if (visited.add(newState)) {
                        queue.offer(new Pair<>(newState, d));
                    }
                    swap(curState, zeroIdx, d);
                }
            }
        }

        return -1;
    }

    private void swap(char[] charArr, int x, int y) {
        char t = charArr[x];
        charArr[x] = charArr[y];
        charArr[y] = t;
    }
}
