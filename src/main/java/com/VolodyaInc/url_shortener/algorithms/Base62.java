package com.VolodyaInc.url_shortener.algorithms;

import java.util.Arrays;
import java.util.UUID;

public class Base62 {

    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    private static final int BASE = ALPHABET.length;

    public static String encodeUUID(UUID uuid) {
        long mostSignificantBits = uuid.getMostSignificantBits();
        long leastSignificantBits = uuid.getLeastSignificantBits();

        String encodedMostSignificantBits = encode(mostSignificantBits);
        String encodedLeastSignificantBits = encode(leastSignificantBits);

        return encodedMostSignificantBits + encodedLeastSignificantBits;
    }

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append(ALPHABET[(int) (num % BASE)]);
            num /= BASE;
        }
        return sb.reverse().toString();
    }

    public static UUID decodeUUID(String base62Code) {
        if (base62Code.length() != 22) {
            throw new IllegalArgumentException("Invalid Base62-encoded UUID");
        }

        String mostSignificantBitsStr = base62Code.substring(0, 11);
        String leastSignificantBitsStr = base62Code.substring(11);

        long mostSignificantBits = decode(mostSignificantBitsStr);
        long leastSignificantBits = decode(leastSignificantBitsStr);

        return new UUID(mostSignificantBits, leastSignificantBits);
    }

    public static long decode(String str) {
        long num = 0;
        for (char c : str.toCharArray()) {
            int index = Arrays.asList(ALPHABET).indexOf(c);
            if (index == -1) {
                throw new IllegalArgumentException("Invalid Base62 character: " + c);
            }
            num = num * BASE + index;
        }
        return num;
    }
}