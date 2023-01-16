// LeetCode Question URL: https://leetcode.com/problems/logger-rate-limiter/
// LeetCode Discuss URL:

/**
 * IMP:
 * Refer this for all different Solutions:
 * https://leetcode.com/problems/logger-rate-limiter/discuss/391558/Review-of-four-different-solutions:-HashMap-Two-Sets-Queue-with-Set-Radix-buckets-(Java-centric)
 */

import java.util.*;

/**
 * Using LinkedHashMap
 *
 * Time Complexity: Amortized O(1)
 *
 * Space Complexity: O(Number of messages in a valid window)
 */
class Logger1 {

    Map<String, Integer> logMap;

    public Logger1() {
        logMap = new LinkedHashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        Iterator<Map.Entry<String, Integer>> itr = logMap.entrySet().iterator();
        while (itr.hasNext()) {
            if (timestamp >= itr.next().getValue() + 10) {
                itr.remove();
            } else {
                break;
            }
        }

        if (logMap.containsKey(message)) {
            return false;
        }

        logMap.put(message, timestamp);
        return true;
    }
}

/**
 * Concurrent Using LinkedHashMap
 *
 * Time Complexity: Amortized O(1)
 *
 * Space Complexity: Difficult to Calculate. It can range from O(All messages
 * seen so far) to O(Number of messages in a valid window)
 */
class Logger2 {

    Map<String, Integer> logMap;
    Object lock;

    public Logger2() {
        logMap = new LinkedHashMap<>();
        lock = new Object();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        Integer preTime = logMap.get(message);

        if (preTime == null || timestamp - preTime >= 10) {
            synchronized (lock) {
                Iterator<Map.Entry<String, Integer>> itr = logMap.entrySet().iterator();
                while (itr.hasNext()) {
                    if (timestamp - itr.next().getValue() >= 10) {
                        itr.remove();
                    } else {
                        break;
                    }
                }

                if (!logMap.containsKey(message)) {
                    logMap.put(message, timestamp);
                    return true;
                }
            }
        }

        return false;
    }
}

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
class Logger3 {

    Deque<Pair<String, Integer>> queue;
    Set<String> messages;

    public Logge3() {
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

class Logger4 {

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
    public Logger4() {
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