package com.koala.member.api.errors;


import com.koala.utils.gateway.entity.AbstractReturnCode;

public class MemberErrorCodes extends AbstractReturnCode {
    public MemberErrorCodes(String desc, int code) {
        super(desc, code);
    }

    //会员登录   0-5
    public static final int MEMBER_LOGIN_FAILED = 40001;
    public static final MemberErrorCodes errorCodes_MEMBER_LOGIN_FAILED = new MemberErrorCodes("用户名或密码错误", MEMBER_LOGIN_FAILED);

    //获取会员信息   11-15
    public static final int MEMBER_INFO_FAILED = 40010;
    public static final MemberErrorCodes errorCodes_MEMBER_INFO_FAILED = new MemberErrorCodes("获取会员信息失败", MEMBER_INFO_FAILED);

    //重置密码  16-20
    public static final int Member_PASSWORDRESET_ERROR = 40026;
    public static final MemberErrorCodes errorCodes_Member_PASSWORDRESET_ERROR = new MemberErrorCodes("重置密码失败", Member_PASSWORDRESET_ERROR);



    //获取客户收货地址信息  21-30
    public static final int MEMBER_ADDRESSLIST_GET_FAILED = 40032;
    public static final MemberErrorCodes errorCodes_MEMBER_ADDRESSLIST_GET_FAILED = new MemberErrorCodes("获取会员收货地址信息失败", MEMBER_ADDRESSLIST_GET_FAILED);

    public static final int MEMBER_ADDRESS_GET_FAILED = 40033;
    public static final MemberErrorCodes errorCodes_MEMBER_ADDRESS_GET_FAILED = new MemberErrorCodes("获取会员收货地址详细失败", MEMBER_ADDRESS_GET_FAILED);


    //删除客户收货地址信息  31-40
    public static final int MEMBER_DELETEADDRESS_FAILED = 40037;
    public static final MemberErrorCodes errorCodes_MEMBER_DELETEADDRESS_FAILED = new MemberErrorCodes("删除会员收货地址失败", MEMBER_DELETEADDRESS_FAILED);

    public static final int MEMBER_ADDADDRESS_SAVEFAILED = 40042;
    public static final MemberErrorCodes errorCodes_MEMBER_ADDADDRESS_SAVEFAILED = new MemberErrorCodes("新增会员收货地址失败", MEMBER_ADDADDRESS_SAVEFAILED);


    //编辑收货地址信息  51-60
    public static final int MEMBER_ADDRESS_MODIFY_FAILED = 40052;
    public static final MemberErrorCodes errorCodes_MEMBER_ADDRESS_MODIFY_FAILED = new MemberErrorCodes("修改会员收货地址失败", MEMBER_ADDRESS_MODIFY_FAILED);

    public static final int MEMBER_PROVINCE_ERROR = 40053;
    public static final MemberErrorCodes errorCodes_MEMBER_PROVINCE_ERROR = new MemberErrorCodes("参数值不正确：province", MEMBER_PROVINCE_ERROR);

    public static final int MEMBER_CITY_ERROR = 40054;
    public static final MemberErrorCodes errorCodes_MEMBER_CITY_ERROR = new MemberErrorCodes("参数值不正确：city", MEMBER_CITY_ERROR);

    public static final int MEMBER_CITYZONE_ERROR = 40055;
    public static final MemberErrorCodes errorCodes_MEMBER_CITYZONE_ERROR= new MemberErrorCodes("参数值不正确：cityZone", MEMBER_CITYZONE_ERROR);


    //手机号码格式不对
    public static final int MEMBER_MOBILE_FORMAT_ERROR = 40061;
    public static final MemberErrorCodes errorCodes_MEMBER_MOBILE_FORMAT_ERROR = new MemberErrorCodes("手机号码格式错误", MEMBER_MOBILE_FORMAT_ERROR);

    /*public static final int MOBILE_UNBIND_ERROR = 40062;
    public static final MemberErrorCodes errorCodes_MOBILE_UNBIND_ERROR = new MemberErrorCodes("手机号绑定失败", MOBILE_UNBIND_ERROR);
*/

    //日期格式不正确
    public static final int MEMBER_DATE_FORMAT_ERROR = 40071;
    public static final MemberErrorCodes errorCodes_MEMBER_DATE_FORMAT_ERROR = new MemberErrorCodes("日期格式化错误", MEMBER_DATE_FORMAT_ERROR);

    //该用户名未注册
    public static final int MEMBER_USERNAME_NOT_EXIST = 40072;
    public static final MemberErrorCodes errorCodes_MEMBER_USERNAME_NOT_EXIST = new MemberErrorCodes("用户名不存在", MEMBER_USERNAME_NOT_EXIST);

    //密码不正确
    public static final int MEMBER_PASSWORD_ERROR = 40073;
    public static final MemberErrorCodes errorCodes_MEMBER_PASSWORD_ERROR = new MemberErrorCodes("密码错误", MEMBER_PASSWORD_ERROR);

    //U鲜卡充值 40100
    public static final int MEMBER_BALANCERECHARGE_WELFARECARD_NOT_RECHARGE = 40102;
    public static final MemberErrorCodes errorCodes_MEMBER_BALANCERECHARGE_WELFARECARD_NOT_RECHARGE = new MemberErrorCodes("福利卡不能充值", MEMBER_BALANCERECHARGE_WELFARECARD_NOT_RECHARGE);

    public static final int MEMBER_BALANCERECHARGE_ALREADY_BIND = 40103;
    public static final MemberErrorCodes errorCodes_MEMBER_BALANCERECHARGE_ALREADY_BIND = new MemberErrorCodes("该卡已被他人绑定过", MEMBER_BALANCERECHARGE_ALREADY_BIND);

    public static final int MEMBER_MEMBER_BALANCERECHARGE_CARD_NOT_ACTIVATION = 40104;
    public static final MemberErrorCodes errorCodes_MEMBER_BALANCERECHARGE_CARD_NOT_ACTIVATION = new MemberErrorCodes("该卡没有激活", MEMBER_MEMBER_BALANCERECHARGE_CARD_NOT_ACTIVATION);

    public static final int MEMBER_CARD_NOT_IN_VALID= 40105;
    public static final MemberErrorCodes errorCodes_MEMBER_CARD_NOT_IN_VALID = new MemberErrorCodes("该卡不在有效期内", MEMBER_CARD_NOT_IN_VALID);

    public static final int MEMBER_BALANCERECHARGE_BALANCE_IS_ZERO = 40106;
    public static final MemberErrorCodes errorCodes_MEMBER_BALANCERECHARGE_BALANCE_IS_ZERO = new MemberErrorCodes("该卡余额为0！", MEMBER_BALANCERECHARGE_BALANCE_IS_ZERO);

    public static final int MEMBER_BALANCERECHARGE_CARD_OR_PASS_ERROR = 40108;
    public static final MemberErrorCodes errorCodes_MEMBER_BALANCERECHARGE_CARD_OR_PASS_ERROR = new MemberErrorCodes("卡号或者密码错误！", MEMBER_BALANCERECHARGE_CARD_OR_PASS_ERROR);

    public static final int MEMBER_BALANCERECHARGE_RECHARGE_ERROR = 40109;
    public static final MemberErrorCodes errorCodes_BALANCERECHARGE_RECHARGE_ERROR = new MemberErrorCodes("充值失败！", MEMBER_BALANCERECHARGE_RECHARGE_ERROR);

    public static final int MEMBER_USER_NOT_EXIST= 40111;
    public static final MemberErrorCodes errorCodes_MEMBER_USER_NOT_EXIST = new MemberErrorCodes("用户不存在", MEMBER_USER_NOT_EXIST);

    public static final int MEMBER_VERIFY_CODE_ERROR= 40112;
    public static final MemberErrorCodes errorCodes_MEMBER_VERIFY_CODE_ERROR= new MemberErrorCodes("验证码错误", MEMBER_VERIFY_CODE_ERROR);

    public static final int MEMBER_VERIFY_CODE_EXPIRED= 40113;
    public static final MemberErrorCodes errorCodes_MEMBER_VERIFY_CODE_EXPIRED= new MemberErrorCodes("验证码过期", MEMBER_VERIFY_CODE_EXPIRED);


    public static final int MEMBER_MOBILENO_HAVE_BEEN_OCCUPIED= 40114;
    public static final MemberErrorCodes errorCodes_MEMBER_MOBILENO_HAVE_BEEN_OCCUPIED= new MemberErrorCodes("手机号被占用", MEMBER_MOBILENO_HAVE_BEEN_OCCUPIED);


    public static final int MEMBER_REGISTER_ERROR= 40115;
    public static final MemberErrorCodes errorCodes_MEMBER_REGISTER_ERROR= new MemberErrorCodes("注册失败", MEMBER_REGISTER_ERROR);

    public static final int MEMBER_USER_UN_REGISTER= 40116;
    public static final MemberErrorCodes errorCodes_MEMBER_USER_UN_REGISTER= new MemberErrorCodes("此帐号未注册", MEMBER_USER_UN_REGISTER);

    public static final int MEMBER_SENDSMS_FREQUENTLY= 40117;
    public static final MemberErrorCodes errorCodes_MEMBER_SENDSMS_FREQUENTLY= new MemberErrorCodes("短信发送太频繁", MEMBER_SENDSMS_FREQUENTLY);

    public static final int MEMBER_SMSCONTEXT_NOT_EXIST= 40118;
    public static final MemberErrorCodes errorCodes_MEMBER_SMSCONTEXT_NOT_EXIST= new MemberErrorCodes("短信类型不存在", MEMBER_SMSCONTEXT_NOT_EXIST);

    public static final int MEMBER_GENERATER_ORDER_NO_ERROR= 40119;
    public static final MemberErrorCodes errorCodes_MEMBER_GENERATER_ORDER_NO_ERROR= new MemberErrorCodes("生成订单号失败！", MEMBER_GENERATER_ORDER_NO_ERROR);


    public static final int MEMBER_GET_ACCOUNT_INFO_ERROR= 40120;
    public static final MemberErrorCodes errorCodes_MEMBER_GET_ACCOUNT_INFO_ERROR= new MemberErrorCodes("获取账户信息失败！", MEMBER_GET_ACCOUNT_INFO_ERROR);

    public static final int MEMBER_ACCOUNT_BALANCE_DEFICIENCY= 40121;
    public static final MemberErrorCodes errorCodes_MEMBER_ACCOUNT_BALANCE_DEFICIENCY= new MemberErrorCodes("账户余额不足！", MEMBER_ACCOUNT_BALANCE_DEFICIENCY);

    public static final int MEMBER_GET_ACCOUNT_ERROR= 40122;
    public static final MemberErrorCodes errorCodes_MEMBER_GET_ACCOUNT_ERROR= new MemberErrorCodes("获取账户信息失败！", MEMBER_GET_ACCOUNT_ERROR);



    public  static final int MEMBER_GET_VALIDATE_CODE_ERROR = 40201;
    public static final MemberErrorCodes errorCodes_MEMBER_GET_VALIDATE_CODE_ERROR= new MemberErrorCodes("获取图片验证码失败！", MEMBER_GET_VALIDATE_CODE_ERROR);

    public static final int MEMBER_VALIDATE_CODE_VERIFY_ERROR = 40211;
    public static final MemberErrorCodes errorCodes_MEMBER_VALIDATE_CODE_Verify_ERROR= new MemberErrorCodes("图片验证码,验证失败！", MEMBER_VALIDATE_CODE_VERIFY_ERROR);
}
