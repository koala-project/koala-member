package com.koala.member.common.captcha;

import java.awt.image.BufferedImage;

public class RandomValidateCodeResult {
    /**
     * 是否成功
     */
    private boolean isSuccess;
    /**
     * 验证码
     */
    private String validateCode;
    /**
     * 验证码 Key
     */
    private String validateCodeKey;
    /**
     * 验证码 BASE64字符串
     */
    private String base64String;
    /**
     * 验证码图片
     */
    private BufferedImage bufferedImage;

    public boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public String getValidateCode() {
        return validateCode;
    }

    public void setValidateCode(String code) {
        this.validateCode = code;
    }

    public String getValidateCodeKey() {
        return validateCodeKey;
    }

    public void setValidateCodeKey(String validateCodeKey) {
        this.validateCodeKey = validateCodeKey;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }

    public BufferedImage getBufferedImage() {
        return bufferedImage;
    }

    public void setBufferedImage(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
    }
}
