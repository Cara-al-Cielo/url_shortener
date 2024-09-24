package com.VolodyaInc.url_shortener.algorithms;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class Base62 {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateShortenedUrl(String originalUrl) {
        long hashValue = originalUrl.hashCode();
        return encode(hashValue);
    }

    public static String encode(long num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            int index = (int) (num % 62);
            sb.append(ALPHABET.charAt(index));
            num /= 62;
        }
        return sb.reverse().toString();
    }
}