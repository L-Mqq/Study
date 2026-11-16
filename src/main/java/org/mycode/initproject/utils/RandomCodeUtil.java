package org.mycode.initproject.utils;

import java.util.Random;

//生成验证码
public class RandomCodeUtil {
    private static final Random random = new Random();

    public static String getRandomCode() {
        int randomNum = random.nextInt(900000) + 100000;
        return String.format("%06d", randomNum);
    }
}
