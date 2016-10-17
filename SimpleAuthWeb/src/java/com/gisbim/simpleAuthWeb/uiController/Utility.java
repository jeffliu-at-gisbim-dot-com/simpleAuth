/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gisbim.simpleAuthWeb.uiController;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author jeffliu
 */
public class Utility {

    private Utility() {
    }

    private static class UtilityHolder {

        private static final Utility INSTANCE = new Utility();
    }

    public static Utility getInstance() {
        return UtilityHolder.INSTANCE;
    }

    public String md5(String value) {
        String md5val = "";
        MessageDigest algorithm = null;

        try {
            algorithm = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException nsae) {
            System.out.println("Cannot find digest algorithm");
        }


        byte[] defaultBytes = value.getBytes();
        algorithm.reset();
        algorithm.update(defaultBytes);
        byte messageDigest[] = algorithm.digest();
        StringBuffer hexString = new StringBuffer();

        for (int i = 0; i < messageDigest.length; i++) {
            String hex = Integer.toHexString(0xFF & messageDigest[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        md5val = hexString.toString();
        return md5val;
    }
}
