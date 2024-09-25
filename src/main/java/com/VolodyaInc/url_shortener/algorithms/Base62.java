package com.VolodyaInc.url_shortener.algorithms;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class Base62 {
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567";

    public static String generateShortenedUrl(String originalUrl) {
        // Remove trailing slash from URL
        originalUrl = originalUrl.replaceAll("/$", "");

        // Use MessageDigest to generate a hash value
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hashBytes = md.digest(originalUrl.getBytes());
        byte[] truncatedHashBytes = Arrays.copyOfRange(hashBytes, 0, 16); // Truncate to 128 bits

        return encode(truncatedHashBytes);
    }

    public static String encode(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            int val = b & 0xFF;
            sb.append(ALPHABET.charAt(val >> 3));
            sb.append(ALPHABET.charAt(val & 7));
        }
        return sb.toString();
    }
}