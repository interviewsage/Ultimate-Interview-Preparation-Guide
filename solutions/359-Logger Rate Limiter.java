// LeetCode Question URL: https://leetcode.com/problems/logger-rate-limiter/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Maintain a queue and a HashSet of log messages received in last 10 seconds.
 *
 * Time Complexity: Logger() - O(1), shouldPrintMessage() - Amortized O(1).
 * Worst O(N)
 *
 * Space Complexity: O(M)
 *
 * M = Number of unique messages received in last 10 seconds
 */
class Logger {

    Deque<Pair<String, Integer>> queue;
    Set<String> messages;

    public Logger() {
        queue = new ArrayDeque<>();
        messages = new HashSet<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!queue.isEmpty() && timestamp - queue.peek().getValue() >= 10) {
            messages.remove(queue.poll().getKey());
        }

        if (!messages.add(message)) {
            return false;
        }

        queue.offer(new Pair<>(message, timestamp));
        return true;
    }
}

class Logger2 {

    class Log {
        int time;
        String s;

        Log(int t, String m) {
            time = t;
            s = m;
        }
    }

    Queue<Log> queue;
    HashSet<String> messages;

    /** Initialize your data structure here. */
    public Logger2() {
        queue = new LinkedList<>();
        messages = new HashSet<>();
    }

    /**
     * Returns true if the message should be printed in the given timestamp,
     * otherwise returns false. If this method returns false, the message will not
     * be printed. The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        while (!queue.isEmpty() && timestamp - queue.peek().time >= 10) {
            messages.remove(queue.poll().s);
        }

        if (!messages.contains(message)) {
            queue.offer(new Log(timestamp, message));
            messages.add(message);
            return true;
        }

        return false;
    }
}

/**
 * Refer for Concurrent Solution:
 * https://leetcode.com/problems/logger-rate-limiter/discuss/83298/Thread-Safe-Solution
 */
class LoggerConcurrent {
    private final HashMap<String, Integer> msgMap;
    private final Object lock;

    /** Initialize your data structure here. */
    public LoggerConcurrent() {
        msgMap = new HashMap<String, Integer>();
        lock = new Object();
    }

    /**
     * Returns true if the message should be printed in the given timestamp,
     * otherwise returns false. If this method returns false, the message will not
     * be printed. The timestamp is in seconds granularity.
     */
    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer ts = msgMap.get(message);
        if (ts == null || timestamp - ts >= 10) {
            synchronized (lock) {
                Integer ts2 = msgMap.get(message);
                if (ts == null || timestamp - ts2 >= 10) {
                    msgMap.put(message, timestamp);
                    return true;
                }
            }
        }

        return false;
    }
}

// Your Logger object will be instantiated and called as such:
// Logger obj = new Logger();
// boolean param_1 = obj.shouldPrintMessage(timestamp,message);