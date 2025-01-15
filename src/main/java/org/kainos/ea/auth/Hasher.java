package org.kainos.ea.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    static final int RADIX = 16;
    static final int HASHTEXT_LENGTH = 64;
    protected Hasher() {
        throw new UnsupportedOperationException();
    }
    public static String getHash(final String input, final String salt) {
        String toHash = input + salt;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(toHash.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);

            StringBuilder hashtext = new StringBuilder(number.toString(RADIX));
            while (hashtext.length() < HASHTEXT_LENGTH) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
