package com.wch.snippet.security.verification;


import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 图形验证码
 *
 * @author wch
 */
@Getter
public class Captcha extends VerificationCode {

    /**
     * 验证码图片
     */
    private BufferedImage image;

    private Captcha(BufferedImage image, String code, int expireIn) {
        super(code, expireIn);
        this.image = image;
    }

    /**
     * 制作图形验证码
     *
     * @param width    宽度
     * @param height   高度
     * @param length   验证码长度
     * @param expireIn 过期时间
     * @return
     */
    public static Captcha create(int width, int height, int length, int expireIn) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(randomColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, (int) (0.7 * height)));
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            String code = String.valueOf(ThreadLocalRandom.current().nextInt(10));
            builder.append(code);
            g.setColor(randomColor(80, 120));
            g.drawString(code, (int) ((width * 0.8) / length * i + width * 0.1), (int) (0.7 * height));
        }
        g.dispose();
        return new Captcha(image, builder.toString(), expireIn);
    }

    /**
     * 在一定范围内生成随机颜色
     *
     * @param min
     * @param max
     * @return
     */
    private static Color randomColor(int min, int max) {
        Random random = ThreadLocalRandom.current();
        int r = min + random.nextInt(max - min);
        int g = min + random.nextInt(max - min);
        int b = min + random.nextInt(max - min);
        return new Color(r, g, b);
    }

}
