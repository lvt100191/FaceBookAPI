/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.facebook.tunglv.util;

import java.nio.charset.Charset;

/**
 *
 * @author TUNGLV
 */
public class Convert {

    public static final Charset ISO_8859_1 = Charset.forName("ISO-8859-1");
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    public static void main(String[] args) {
        System.out.println(""+convertToUTF8("Xu h\\u01b0\\u1edbng gi\\u1edbi tr\\u1ebb"));

    }

    public static String convertToUTF8(String str) {
        String rs = null;
        byte[] ptext = str.getBytes(ISO_8859_1);
        rs = new String(ptext, UTF_8);
        return rs;
    }

}
