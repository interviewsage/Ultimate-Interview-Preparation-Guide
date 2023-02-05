// LeetCode Question URL: https://leetcode.com/problems/encode-and-decode-tinyurl/
// LeetCode Discuss URL:

import java.util.*;

/**
 * Time Complexity:
 * keyGen() = O(1)
 * Codec() = O(1)
 * encode() = O(1) .. If there are collision then it will more than O(1)
 * decode() = O(1) .. replace takes constant time.
 *
 * Space Complexity: O(Number of URLs encoded)
 */
class Codec {

    private static final String CHARSET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/";
    private static final String BASE_URL = "http://tinyurl.com/";
    Map<String, String> urlMap;
    Random random;

    public Codec() {
        urlMap = new HashMap<>();
        random = new Random();
    }

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (longUrl == null) {
            throw new IllegalArgumentException("Input longUrl is null");
        }

        String key = keyGen();
        while (urlMap.containsKey(key)) {
            key = keyGen();
        }
        urlMap.put(key, longUrl);

        return new StringBuilder(BASE_URL).append(key).toString();
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        if (shortUrl == null) {
            throw new IllegalArgumentException("Input shortUrl is null");
        }

        String urlKey = shortUrl.replace(BASE_URL, "");
        String longUrl = urlMap.get(urlKey);
        if (longUrl == null) {
            throw new NoSuchElementException("Short URL not found");
        }
        return longUrl;
    }

    private String keyGen() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(CHARSET.charAt(random.nextInt(CHARSET.length())));
        }
        return sb.toString();
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));
