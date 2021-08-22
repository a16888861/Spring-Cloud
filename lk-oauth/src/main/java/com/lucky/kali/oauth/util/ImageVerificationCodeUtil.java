package com.lucky.kali.oauth.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 图片验证码生成工具
 *
 * @author edit by Elliot
 */
public class ImageVerificationCodeUtil {

    /**
     * 验证码图片的长和宽
     */
    private static final int WIDTH = 100;

    /**
     * 验证码图片的宽
     */
    private static final int HEIGHT = 40;

    /**
     * 验证码数组
     */
    private static final String CODES = "0123456789" +
            "abcdefghjkmnopqrstuvwxyz" +
            "ABCDEFGHJKMNPQRSTUVWXYZ";

    /**
     * 字体数组
     */
    private static final String[] FONT_NAMES = {Font.DIALOG, Font.DIALOG_INPUT, Font.MONOSPACED, Font.SANS_SERIF, Font.SERIF};

    /**
     * 获取随机数对象
     */
    private final Random r = new Random();

    /**
     * 用来保存验证码的文本内容
     */
    private String text;

    /**
     * 获取随机的颜色
     *
     * @return 颜色RGB
     */
    private Color randomColor() {
        int r = this.r.nextInt(208);
        int g = this.r.nextInt(208);
        int b = this.r.nextInt(230);
        return new Color(r, g, b);
    }

    /**
     * 获取随机字体
     *
     * @return 字体
     */
    private Font randomFont() {
        /*获取随机的字体*/
        int index = r.nextInt(FONT_NAMES.length);
        /*获取随机字体的字体名*/
        String fontName = FONT_NAMES[index];
        /*随机获取字体的样式，0是无样式，1是加粗，2是斜体，3是加粗加斜体*/
        int style = r.nextInt(4);
        /*随机获取字体的大小*/
        int size = r.nextInt(10) + 24;
        /*返回一个随机的字体*/
        return new Font(fontName, style, size);
    }

    /**
     * 获取随机字符
     *
     * @return 字符
     */
    private char randomChar() {
        int index = r.nextInt(CODES.length());
        return CODES.charAt(index);
    }

    /**
     * 给图片画干扰线，验证码干扰线用来防止计算机解析图片
     *
     * @param image 图片
     */
    private void drawLine(BufferedImage image) {
        /*定义干扰线的数量*/
        int num = 4 + r.nextInt(5);

        Graphics2D g = (Graphics2D) image.getGraphics();
        for (int i = 0; i < num; i++) {
            int x1 = r.nextInt(WIDTH);
            int y1 = r.nextInt(HEIGHT);
            int x2 = r.nextInt(WIDTH);
            int y2 = r.nextInt(HEIGHT);
            g.setColor(randomColor());
            g.drawLine(x1, y1, x2, y2);
        }
    }

    /**
     * 创建图片
     *
     * @return 图片
     */
    private BufferedImage createImage() {
        /*创建图片缓冲区*/
        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        /*获取画笔*/
        Graphics2D g = (Graphics2D) image.getGraphics();
        /*设置背景色随机*/
        g.setColor(new Color(r.nextInt(25) + 230, r.nextInt(25) + 230, r.nextInt(230) + 10));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        return image;
    }

    /**
     * 获取验证码图片
     *
     * @return 图片
     */
    public BufferedImage getImage() {
        BufferedImage image = createImage();
        /*获取画笔*/
        Graphics2D g = (Graphics2D) image.getGraphics();
        StringBuilder sb = new StringBuilder();
        /*画四个字符*/
        for (int i = 0; i < 4; i++) {
            /*随机生成字符，因为只有画字符串的方法，没有画字符的方法，所以需要将字符变成字符串再画*/
            String s = randomChar() + "";
            /*添加到StringBuilder里面*/
            sb.append(s);
            /*定义字符的x坐标*/
            float x = i * 1.0F * (WIDTH - 16) / 4 + 8;
            /*设置字体角度，随机*/
            double rotate = 0.5 - Math.random();
            /*设置字体，随机*/
            g.setFont(randomFont());
            /*设置颜色，随机*/
            g.setColor(randomColor());
            /*旋转*/
            g.rotate(rotate, x + 8, HEIGHT - 10);
            /*画线*/
            g.drawString(s, x, HEIGHT - 10);
            /*旋转*/
            g.rotate(0 - rotate, x + 12, HEIGHT - 10);
        }
        this.text = sb.toString();
        drawLine(image);
        g.dispose();
        image.flush();
        return image;
    }

    /**
     * 获取验证码文本
     *
     * @return 验证码文本内容
     */
    public String getText() {
        return text;
    }

}
