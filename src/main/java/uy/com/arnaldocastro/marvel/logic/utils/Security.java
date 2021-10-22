package uy.com.arnaldocastro.marvel.logic.utils;

import org.springframework.util.DigestUtils;

public class Security {
    public static String obtainMD5Key(String key) {
        String hash = DigestUtils.md5DigestAsHex(key.getBytes());
        return hash;
    }
}
