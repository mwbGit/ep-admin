package com.ep.util.mybatis;

import java.util.UUID;

/**
 * Created by 吴晓海 on 2017/10/25.
 */
public class UuidUtil {
    /**
     * 32位uuid
     * @return
     */
    public static String get32UUID() {
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        return uuid;
    }

    /**
     * 16位纯数字
     * @return
     */
    public static String get16NumUUID() {
        long l = System.currentTimeMillis();
        int i = (int)(100+Math.random()*999);
        String uuid = String.valueOf(l)+String.valueOf(i);
        return uuid;
    }

    public static void main(String[] args) {
        System.out.println(get16NumUUID());
        System.out.println(get32UUID());
    }
}
