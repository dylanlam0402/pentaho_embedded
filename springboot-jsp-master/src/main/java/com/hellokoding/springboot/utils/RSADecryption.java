package com.hellokoding.springboot.utils;
import sun.security.util.DerInputStream;
import sun.security.util.DerValue;
import java.security.spec.RSAPrivateCrtKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;
import java.io.*;
import java.math.BigInteger;
import java.security.*;


public class RSADecryption {

    public static String decrypt(String cipherText, PrivateKey privateKey) throws IOException, GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(cipherText)), "UTF-8");
    }

    public static PrivateKey getPemPrivateKey(String filename, String algorithm) throws Exception {
        File f = new File(filename);
        FileInputStream fis = new FileInputStream(f);
        DataInputStream dis = new DataInputStream(fis);
        byte[] keyBytes = new byte[(int) f.length()];
        dis.readFully(keyBytes);
        dis.close();

        String temp = new String(keyBytes);
        String privKeyPEM = temp.replace("-----BEGIN RSA PRIVATE KEY-----", "");
        privKeyPEM = privKeyPEM.replace("-----END RSA PRIVATE KEY-----", "");
        privKeyPEM = privKeyPEM.replaceAll("\\s", "");
        //System.out.println("Private key\n"+privKeyPEM);

        DerInputStream derReader = new DerInputStream(java.util.Base64.getDecoder().decode(privKeyPEM));

        DerValue[] seq = derReader.getSequence(0);

        if (seq.length < 9) {
            throw new GeneralSecurityException("Could not parse a PKCS1 private key.");
        }

        // skip version seq[0];
        BigInteger modulus = seq[1].getBigInteger();
        BigInteger publicExp = seq[2].getBigInteger();
        BigInteger privateExp = seq[3].getBigInteger();
        BigInteger prime1 = seq[4].getBigInteger();
        BigInteger prime2 = seq[5].getBigInteger();
        BigInteger exp1 = seq[6].getBigInteger();
        BigInteger exp2 = seq[7].getBigInteger();
        BigInteger crtCoef = seq[8].getBigInteger();

        RSAPrivateCrtKeySpec keySpec = new RSAPrivateCrtKeySpec(modulus, publicExp, privateExp, prime1, prime2, exp1, exp2, crtCoef);
        KeyFactory factory = KeyFactory.getInstance(algorithm);
        return factory.generatePrivate(keySpec);
    }

}
