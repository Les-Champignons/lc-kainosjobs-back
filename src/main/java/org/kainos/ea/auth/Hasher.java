package org.kainos.ea.auth;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    static final int RADIX = 16;
    static final int HASHTEXT_LENGTH = 32;
    protected Hasher() {
        throw new UnsupportedOperationException();
    }
    public static String getHash(final String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);

            StringBuilder hashtext = new StringBuilder(no.toString(RADIX));
            while (hashtext.length() < HASHTEXT_LENGTH) {
                hashtext.insert(0, "0");
            }
            return hashtext.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}
