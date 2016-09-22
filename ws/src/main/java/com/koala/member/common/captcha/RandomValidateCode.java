package com.koala.member.common.captcha;

import com.google.common.base.Strings;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

public class RandomValidateCode {

    public static final String RANDOMCODEKEY = "RANDOMVALIDATECODEKEY_%s";//放到session中的key
    private static final String IMAGEFORMATNAME = "jpg"; //图片格式
    private static BASE64Encoder encoder = new BASE64Encoder();
    private Random random = new Random();
    private String randString = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";//随机产生的字符串
    private String randomCode = "";


    private int width = 80;//图片宽
    private int height = 26;//图片高
    private int lineSize = 40;//干扰线数量
    private int stringNum = 4;//随机产生字符数量

    /**
     * 可设置的参数
     */
    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) width = 80;
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) height = 26;
        this.height = height;
    }

    public int getLineSize() {
        return lineSize;
    }

    public void setLineSize(int lineSize) {
        if (lineSize <= 0) lineSize = 0;
        this.lineSize = lineSize;
    }

    public int getStringNum() {
        return stringNum;
    }

    public void setStringNum(int stringNum) {
        if (stringNum <= 0) stringNum = 4;
        this.stringNum = stringNum;
    }

    /*
     * 获得字体
     */
    private Font getFont() {
        return new Font("Fixedsys", Font.CENTER_BASELINE, 18);
    }

    /*
     * 获得颜色
     */
    private Color getRandColor(int fc, int bc) {
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc - 16);
        int g = fc + random.nextInt(bc - fc - 14);
        int b = fc + random.nextInt(bc - fc - 18);
        return new Color(r, g, b);
    }

    /**
     * 生成随机验证码图片
     */
    private BufferedImage getRandomCodeImage() {
        //BufferedImage类是具有缓冲区的Image类,Image类是用于描述图像信息的类
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
        Graphics g = image.getGraphics();//产生Image对象的Graphics对象,改对象可以在图像上进行各种绘制操作
        try {
            g.fillRect(0, 0, width, height);
            g.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 18));
            g.setColor(getRandColor(110, 133));
            //绘制干扰线
            for (int i = 0; i <= lineSize; i++) {
                drowLine(g);
            }
            //绘制随机字符
            for (int i = 0; i < randomCode.length(); i++) {
                String randomString = String.valueOf(randomCode.charAt(i));
                drowString(g, randomString, i + 1);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            g.dispose();
        }
        return image;
    }

    public static String getCodeKey(String key) throws Exception {
        if (Strings.isNullOrEmpty(key)) {
            throw new Exception("图片验证码key不能为空");
        }
        return String.format(RANDOMCODEKEY, key);
    }

    /*
     * 获取随机的字符
     */
    private String getRandomCode() {
        randomCode = "";
        for (int i = 1; i <= stringNum; i++) {
            randomCode += String.valueOf(randString.charAt(random.nextInt(randString.length())));
        }
        return randomCode;
    }

    /**
     * 生成随机验证码图片的BASE64字符串
     *
     * @return
     */
    public RandomValidateCodeResult getRandomCodeResult(String key) throws Exception {

        RandomValidateCodeResult result = new RandomValidateCodeResult();
        result.setIsSuccess(true);
        result.setValidateCodeKey(getCodeKey(key));
        result.setValidateCode(getRandomCode());
        result.setBufferedImage(getRandomCodeImage());

        ByteArrayOutputStream baos = null;
        String base64String = null;
        try {
            baos = new ByteArrayOutputStream();
            ImageIO.write(result.getBufferedImage(), IMAGEFORMATNAME, baos);
            byte[] bytes = baos.toByteArray();
            base64String = encoder.encodeBuffer(bytes).trim();
        } catch (IOException ex) {
            result.setIsSuccess(false);
            ex.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                    baos = null;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (Strings.isNullOrEmpty(base64String)) {
            result.setIsSuccess(false);
        } else {
            result.setBase64String(base64String);
        }
        return result;
    }

    /*
     * 绘制字符串
     */
    private void drowString(Graphics g, String rand, int i) {
        g.setFont(getFont());
        g.setColor(new Color(random.nextInt(101), random.nextInt(111), random.nextInt(121)));
        g.translate(random.nextInt(3), random.nextInt(3));
        g.drawString(rand, 13 * i, 16);
    }

    /*
     * 绘制干扰线
     */
    private void drowLine(Graphics g) {
        int x = random.nextInt(width);
        int y = random.nextInt(height);
        int xl = random.nextInt(13);
        int yl = random.nextInt(15);
        g.drawLine(x, y, x + xl, y + yl);
    }
}
