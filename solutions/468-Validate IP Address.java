// LeetCode Question URL: https://leetcode.com/problems/validate-ip-address
// LeetCode Discuss URL:

/**
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = length of input string.
 */
class Solution {
    public String validIPAddress(String queryIP) {
        if (queryIP == null) {
            throw new IllegalArgumentException("Input queryIP is null");
        }

        if (isIPV4(queryIP)) {
            return "IPv4";
        }
        if (isIPV6(queryIP)) {
            return "IPv6";
        }

        return "Neither";
    }

    private boolean isIPV4(String queryIP) {
        int len = queryIP.length();
        if (len < 7 || len > 15 || !Character.isDigit(queryIP.charAt(0))
                || !Character.isDigit(queryIP.charAt(len - 1))) {
            return false;
        }

        String[] octets = queryIP.split("\\.");
        if (octets.length != 4) {
            return false;
        }

        for (String octet : octets) {
            int oLen = octet.length();
            if (oLen == 0 || oLen > 3 || (oLen > 1 && octet.charAt(0) == '0')) {
                return false;
            }

            try {
                int val = Integer.parseInt(octet);
                if (val < 0 || val > 255) {
                    return false;
                }
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return true;
    }

    private boolean isIPV6(String queryIP) {
        int len = queryIP.length();
        if (len < 15 || len > 39 || isHexaDecimalChar(queryIP.charAt(0))
                || isHexaDecimalChar(queryIP.charAt(len - 1))) {
            return false;
        }

        String[] hextets = queryIP.split(":");
        if (hextets.length != 8) {
            return false;
        }

        for (String hextet : hextets) {
            int hLen = hextet.length();
            if (hLen == 0 || hLen > 4) {
                return false;
            }

            try {
                int val = Integer.parseInt(hextet, 16);
                if (val < 0) {
                    return false;
                }
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return true;
    }

    private boolean isHexaDecimalChar(char c) {
        return Character.isDigit(c) || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
}

class Solution2 {
    public String validIPAddress(String queryIP) {
        return isIPv4(queryIP) ? "IPv4" : (isIPv6(queryIP) ? "IPv6" : "Neither");
    }

    public boolean isIPv4(String queryIP) {
        if (queryIP == null) {
            return false;
        }

        int len = queryIP.length();
        if (len < 7 || len > 15 || !Character.isDigit(queryIP.charAt(0))
                || !Character.isDigit(queryIP.charAt(len - 1))) {
            return false;
        }

        String[] tokens = queryIP.split("\\.");
        if (tokens.length != 4) {
            return false;
        }

        for (String token : tokens) {
            int l = token.length();
            if (l == 0 || l > 3) {
                return false;
            }
            if (token.charAt(0) == '0') {
                if (l != 1) {
                    return false;
                } else {
                    continue;
                }
            }
            try {
                int val = Integer.parseInt(token);
                if (val <= 0 || val > 255) {
                    return false;
                }
            } catch (NumberFormatException nfe) {
                return false;
            }
        }

        return true;
    }

    public boolean isIPv6(String queryIP) {
        if (queryIP == null) {
            return false;
        }

        int len = queryIP.length();
        if (len < 15 || len > 39 || !isValidIPv6Char(queryIP.charAt(0)) || !isValidIPv6Char(queryIP.charAt(len - 1))) {
            return false;
        }

        String[] tokens = queryIP.split(":");
        if (tokens.length != 8) {
            return false;
        }

        for (String token : tokens) {
            int l = token.length();
            if (l == 0 || l > 4) {
                return false;
            }
            for (int i = 0; i < l; i++) {
                if (!isValidIPv6Char(token.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean isValidIPv6Char(char c) {
        return Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F');
    }
}

class Solution3 {
    public String validIPAddress(String queryIP) {
        return validIPv4Address(queryIP) ? "IPv4" : (validIPv6Address(queryIP) ? "IPv6" : "Neither");
    }

    private boolean validIPv4Address(String queryIP) {
        if (queryIP == null) {
            return false;
        }

        int len = queryIP.length();
        if (len < 7 || len > 15 || !Character.isDigit(queryIP.charAt(0))
                || !Character.isDigit(queryIP.charAt(len - 1))) {
            return false;
        }

        String[] tokens = queryIP.split("\\.");
        if (tokens.length != 4) {
            return false;
        }

        for (String token : tokens) {
            int l = token.length();
            if (l == 0 || l > 3 || (l > 1 && token.charAt(0) == '0')) {
                return false;
            }
            int val = 0;
            for (int i = 0; i < l; i++) {
                char c = token.charAt(i);
                if (!Character.isDigit(c)) {
                    return false;
                }
                val = val * 10 + (c - '0');
                if (val > 255) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean validIPv6Address(String queryIP) {
        if (queryIP == null) {
            return false;
        }

        int len = queryIP.length();
        if (len < 15 || len > 39 || !validIPv6Char(queryIP.charAt(0)) || !validIPv6Char(queryIP.charAt(len - 1))) {
            return false;
        }

        String[] tokens = queryIP.split(":");
        if (tokens.length != 8) {
            return false;
        }

        for (String token : tokens) {
            int l = token.length();
            if (l == 0 || l > 4) {
                return false;
            }
            for (int i = 0; i < token.length(); i++) {
                if (!validIPv6Char(token.charAt(i))) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean validIPv6Char(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }
}

class Solution4 {
    public String validIPAddress(String IP) {
        if (IP == null) {
            return "Neither";
        }

        if (IP.length() >= 7 && IP.length() <= 15 && isIPv4(IP)) {
            return "IPv4";
        }
        if (IP.length() >= 15 && IP.length() <= 39 && isIPv6(IP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isIPv4(String IP) {
        if (!Character.isDigit(IP.charAt(0)) || !Character.isDigit(IP.charAt(IP.length() - 1))) {
            return false;
        }
        String[] octets = IP.split("\\.");
        if (octets.length != 4) {
            return false;
        }
        for (String o : octets) {
            if (o.length() < 1 || o.length() > 3 || !isValidOctet(o)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidOctet(String o) {
        if (o.charAt(0) == '0') {
            return o.length() == 1;
        }
        try {
            int parsedVal = Integer.parseInt(o);
            if (parsedVal <= 0 || parsedVal > 255) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isIPv6(String IP) {
        if (IP.charAt(0) == ':' || IP.charAt(IP.length() - 1) == ':') {
            return false;
        }
        String[] hextets = IP.split("\\:");
        if (hextets.length != 8) {
            return false;
        }
        for (String h : hextets) {
            if (h.length() < 1 || h.length() > 4 || !isValidHextet(h)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidHextet(String h) {
        for (char c : h.toCharArray()) {
            if (!(Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Using definition of IPv6 from Wikipedia.
 *
 * Extra condition: One or more consecutive groups containing zeros only may be
 * replaced with a single empty group, using two consecutive colons (::). The
 * substitution may only be applied once in the address, however, because
 * multiple occurrences would create an ambiguous representation.
 *
 * Time Complexity: O(N)
 *
 * Space Complexity: O(N)
 *
 * N = length of input string.
 */
class Solution5 {
    public String validIPAddress(String IP) {
        if (IP == null) {
            return "Neither";
        }

        if (IP.length() >= 7 && IP.length() <= 15 && isIPv4(IP)) {
            return "IPv4";
        }
        if (IP.length() >= 2 && IP.length() <= 39 && isIPv6(IP)) {
            return "IPv6";
        }
        return "Neither";
    }

    private boolean isIPv4(String IP) {
        if (IP.charAt(0) == '.' || IP.charAt(IP.length() - 1) == '.') {
            return false;
        }
        String[] octets = IP.split("\\.");
        if (octets.length != 4) {
            return false;
        }
        for (String o : octets) {
            if (o.length() < 1 || o.length() > 3 || !isValidOctet(o)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidOctet(String o) {
        if (o.charAt(0) == '0') {
            return o.length() == 1;
        }
        try {
            int parsedVal = Integer.parseInt(o);
            if (parsedVal <= 0 || parsedVal > 255) {
                return false;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private boolean isIPv6(String IP) {
        int len = IP.length();
        boolean twoColon = false;

        int i = IP.indexOf(':');
        if (i == -1 || i == len - 1) {
            return false;
        }

        int idx = IP.indexOf("::");
        if (idx == -1) {
            twoColon = false;
        } else {
            idx = IP.indexOf("::", idx + 1);
            if (idx == -1) {
                twoColon = true;
            } else {
                return false;
            }
        }

        String[] hextets = IP.split("\\:", 8);

        int hl = hextets.length;
        if (hl > 8) {
            return false;
        } else if (!twoColon) {
            if (hl != 8) {
                return false;
            }
        } else if (IP.charAt(len - 1) == ':' && hl > 6) {
            return false;
        } else if (IP.charAt(0) != ':' && hl > 7) {
            return false;
        }

        for (String h : hextets) {
            if (h.length() > 4 || !isValidHextet(h)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidHextet(String h) {
        for (char c : h.toCharArray()) {
            if (!(Character.isDigit(c) || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F'))) {
                return false;
            }
        }
        return true;
    }
}
