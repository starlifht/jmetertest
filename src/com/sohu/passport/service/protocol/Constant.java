package com.sohu.passport.service.protocol;


/**
 * 常量
 * 
 * @author kaizhao
 *
 */
public interface Constant {

	/**
	 * 1天，单位：秒
	 */
	public static final int TIME_1D = 86400;

	/**
	 * 2天，单位：秒
	 */
	public static final  int TIME_2D = 172800;

	/**
	 * 14天，单位：秒
	 */
	public static final int TIME_14D = 1209600;

	/**
	 * 30天，单位：秒
	 */
	public static final int TIME_30D = 2592000;

	/**
	 * 5分钟，单位：秒
	 */
	public static final int TIME_3M = 300;

	/**
	 * 10分钟，单位：秒
	 */
	public static final int TIME_10M = 600;

	/**
	 * 外域注册账号激活链接
	 */
	public static final String EX_DOMAIN_ACTIVATE_URL = "https://passport.sohu.com/web/remind_activate.jsp";

	/**
	 * 手机应用注册账号激活链接
	 */
	public static final String MOBILE_REGISTER_ACTIVATE_URL = "https://passport.sohu.com/web/toCaptchaActivate.action";
    /**
     * 验证码生成链接
     */
    public static final String SCE_CAPTCHA_URL = "http://captcha.sce.sohu.com/";


    /**
     * 注册的链接激活
     */
    public static final String REG_EMAIL_ACTIVE_URL = "https://passport.sohu.com/regist/activate";

    /**
     * 邮件密码找回
     */
    public static final String EMAIL_RECOVER_PWD_URL ="http://passport.sohu.com/web/recoverPsw.action";

	/*
	 * 使用枚举类型主要是用于内部逻辑的判断，对参数进行强制性约束
	 * 使用static fianl String定义变量，主要是为了取值的效率
	 */

	// 请求参数的key定义
	public  static final String KEY_URL = "url";
	public  static final String KEY_VERSIONL = "version";
	public  static final String KEY_APPID = "appid";
	public  static final String KEY_PROD_APPID = "product_appid";
	public  static final String KEY_SIGNATURE = "signature";
	public  static final String KEY_SIGNATURE_ALGORITHM = "signature_algorithm";
	public  static final String KEY_CURRENT_TIME = "current_time";
	public  static final String KEY_CLIENT_IP = "client_ip";
	public  static final String KEY_REQUEST_IP = "request_ip";
	public  static final String KEY_PASSWORD = "password";
	public  static final String KEY_GID= "gid";
	public  static final String KEY_MOBILE = "mobile";
	public  static final String KEY_EMAIL = "email";
	public  static final String KEY_TOKEN = "token";
	public  static final String KEY_M_TOKEN = "mtoken";
	public  static final String KEY_NEW_PWD = "new_password";

	public  static final String KEY_USERID_TYPE = "userid_type";
	public  static final String KEY_PASSWORD_TYPE = "password_type";
	public  static final String KEY_BUSINESS_TYPE = "business_type";

	public  static final String KEY_USERID = "userid";
	public  static final String KEY_UID = "uid";
	public  static final String KEY_UUID = "uuid";
	public  static final String KEY_UNIQNAME = "uniqname";

	public  static final String KEY_PPINF = "ppinf";
	public  static final String KEY_PPRDIG = "pprdig";
	public  static final String KEY_PPSMU = "ppsmu";
	public  static final String KEY_SMU = "s_m_u";
	public  static final String KEY_LASTDOMAIN = "lastdomain";
	public  static final String KEY_ACTIVATEINFO = "activateinfo";

	public  static final String KEY_STATUS = "status";
	public  static final String KEY_MESSAGE = "message";
	public  static final String KEY_DATA = "data";
    public  static final String SEND_VALIDATE_MAIL_KEY = "djfkajf&&UIII%%%&HH";

    //限制
    public  static final String LIMIT_PROJECT_NAME="passport";
    //限制类型：登录
    public  static final String  LIMIT_TYPE_LOGIN="login";
    //限制类型：注册
    public  static final String  LIMIT_TYPE_REG="web_regist";
    //限制类型：发送邮箱注册验证码
    public  static final String LIMIT_TYPE_REG_CAPTCHA="reg_captcha";
    //邮箱注册验证码-发送次数
    public  static final String LIMIT_REG_CAPTCHA_SENDTIMES="times";
    //邮箱注册验证码-检查次数
    public  static final String LIMIT_REG_CAPTCHA_CHECK="check";
    //注册 ip限制策略名称
    public  static final String  LIMIT_REG_IP="ip_black";
    //限制类型：手机验证码
    public  static final String LIMIT_TYPE_MOBILE_CAPTCHA="mobile_captcha";
    //手机注册的验证码校验次数
    public  static final String LIMIT_MOBILE_CAPTCHA_REG_AUTH="auth";
    // 限制类型：校验用户身份
    public  static final String  LIMIT_TYPE_AUTHUSER="authuser";
    //手机注册的验证码发送次数
    public  static final String LIMIT_MOBILE_CAPTCHA_REG_TIMES="times";
    //修改绑定邮箱时旧密码错误
    public  static final String LIMIT_PASSWORD_ERROR_EMAIL = "email";
    //限制类型：修改密码
    public  static final String  LIMIT_TYPE_PASSWORD_ERROR="password_error";
    //找回密码 旧密保答案错误
    public  static final String LIMIT_RECOVER_PASSWORD_ANSWER="answer";
    // 限制类型：找回密码
    public  static final String  LIMIT_TYPE_RECOVER_PASSWORD="recover_pwd";
    //找回密码 账号限制策略名称
    static public final String LIMIT_RECOVER_PASSWORD_USERID="userid_black";
    //找回密码 ip限制策略名称
    static public final String LIMIT_RECOVER_PASSWORD_IP="ip_black";
    //限制类型：修改密码
    public  static final String  LIMIT_TYPE_UPDATE_PASSWORD="update_pwd";
    // 使用旧密码修改新密码
    public  static final String LIMIT_PASSWORD_ERROR_OLD = "byoldpassword";
    //限制类型：修改用户信息
    public  static final String  LIMIT_TYPE_UPDATE_INFO="update";
    //绑定手机
    public  static final String LIMIT_UPDATE_BIND_MOBILE="bind_mobile";
    //手机验证码获取 限制策略名字
    public  static final String  LIMIT_LOGIN_MOBILE_SEND_TIMES="mobile_send_times_black";
    //手机验证码登录，发送短信ip限制策略名称
    public static final String  LIMIT_LOGIN_IP_SEND_TIMES="ip_send_times_black";
    //手机验证码登录，ip登录错误限制策略名称
    public  static  final String  LIMIT_LOGIN_IP_AUTH_TIMES="ip_auth_times_black";
    //手机验证码登录 发送短信 手机号限制策略名字
    public  static  final String  LIMIT_LOGIN_MOBILE_AUTH_TIMES="mobile_auth_times_black";
    //手机验证码发送间隔 策略名称
    static public final String  LIMIT_LOGIN_MOBILE_INTERVAL="send_interval_black";
    // 限制类型：重置密码
    static public final String LIMIT_TYPE_RESET_PASSWORD = "reset_pwd";
    // 管理员重置密码
    static public final String LIMIT_RESET_PASSWORD_ADMIN = "admin";
    //登陆验证码次数
    static public final Integer LIMIT_LOGIN_ERROR_TIMES = 3;
    //缓存的前缀
    // 存储用户扩展信息[extinfo_userid, serviceuse|createtime|i_res1|c_res2]
    public static final String PERFIX_EXT_INFO = "extinfo_";
    // 存储手机登陆的验证码
    public static final String PERFIX_MOBILE_LOGIN_CODE = "mobilelogincode";
    // 存储手机应用登陆的前缀
    public static final String PERFIX_MOBILE_TOKEN = "mobile_token_";
    // 存储密码的前缀
    public static final String PERFIX_PASSWORD = "pwd_";
    // 存昵称的前缀
    public static final String PERFIX_UNIQNAME = "un_";
    // 存页面刷新的token
    public static final String PERFIX_PAGE_TOKEN = "pt_";
    // userid-uid
    public static final String PERFIX_USERID_UID = "u1_";
    public static final String PERFIX_USERID_UUID = "u2_";
    // 邮箱注册验证码
    public static final String PERFIX_EMAIL_REG_CAPTCHA = "ec_";


    //手机号 STANBY
    public static final String STANBY_BINDING = "bind";
    public static final String STANBY_UNBINDING = "unbind";
    public static final String STANBY_RECOVER_PWD = "recoverpwd";
    public static final String STANDY_REGISTER_MOBILE = "regmobile";
    public static final String STANBY_SMS_BIND_MOBILE = "smsbindmobile";


    //cookie的生命周期
    public static final int PERSISTCOOKIETIME = 86400 * 14;
    public static final int PERSISTCOOKIETIME_FOCUS = 86400 * 365 * 10;
    public static final int PERSISTCOOKIETIME_FOCUS_UNSELECT = 86400 * 365 ;
    public static final int COOKIETIME_LASTDOMAIN = 86400 * 14;
    public static final long RECOVERPWD_MINTIME = 86400 * 5 * 1000;
    public static final String MOBILEBINDAPPID = "9998";
    public static final long MOBILEBINDLIVETIME = 300; //5分钟
    public static final long MOBILE_SMS_UNBINDLIVETIME = 3600;

    //验证码,手机验证码的生命周期
    public static final long MOBILEREGLIVETIME = 300; //5分钟

    //与第三方用户的关联
    public static final String  NO_RELATION="0";          //没有关联关系
    public static final String  MAIN_IN_RELATION="1";     //在关联关系中处于主账号地位
    public static final String  ATTACH_IN_RELATION="2";   //在关联关系中处于从属地位

    //手机验证码的位数
    public static final int MOBILE_TOKEN_LEN = 4;
    // 有效期
    //邮件验证码的周期   10分钟60*10;
    public static final int EXP_EMAIL_CAPTCHA = 600;
    // 手机登陆验证码的有效期：
    public static final int LIVE_MOBILE_LOGIN_CODE = 600;// 10分钟60*10;
    //用户登陆日志用的分隔符
    public static final String VALUE_SEPARATOR = "|";
    //session_token分隔符
    public static final String SESSION_TOKEN_SEPARATOR = ".|.";

    public static final String VALUE_OTHERPLACE_DEFALUT="-";
    public static final String VALUE_OTHERPLACE_CN="cn";

    //token的长度
    public static final int TOKENLEN = 30;

    //历史登陆的验证值
    public static final String HISTORY_APPID="1001";
    public static final String HISTORY_APP_KEY="YNPoe%m6b97dH1%THl&xNf(jZGuL(j38";

    //次数限制
    public static final String  LIMITAUTHUSER_ERROR_PREFIX ="limitAuthUserPswError";



    /**
	 * 自动生成的昵称的前缀
	 */
	public static final String UNIQNAME_PREFIX = "搜狐网友";

	public enum UserStatus {
		NOT_ACTIVATE_MINUS1(-1), NOT_ACTIVATE_0(0), NORMAL(1), LOGIN_WITH_HUDUN(1), LOCKED_BY_ONESELF(2), LOCKED_BY_ADMIN(3);

		private int value;

		UserStatus(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum DataType {
		JSON, XML;
	}

	public enum PasswordType {
		PLAIN_TEXT(0), MD5(1), MD5_SALT(2), DISABLED(9);

		private int value;

		PasswordType(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	public enum EMailType {
        //注册激活
		EX_DOMAIN_REGISTER_ACTIVATE("ex_domain_register_activate"),
        //密码找回
        NOTIFY_MODIYF_PASSWORD("notify_modify_password"),
        EX_DOMAIN_REGISTER_TOKEN("ex_domain_register_token");

		private String value;

		EMailType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum SecurityType {
        ABNORMAL_LOCATION_SCT("abnormal_location"),
        MODIFY_PASSWORD_SCT("modify_password"),
        MODIFY_BIND_MOBILE_SCT("modify_bind_mobile"),
        MODIFY_BIND_EMAIL_SCT("modify_bind_email"),
        MODIFY_QUESTION_SCT("modify_question");

		private String value;

        SecurityType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public enum AuthResult {
		RIGHT(0), WRONG(1), DISABLED(2);

		private int value;

		AuthResult(int value) {
			this.value = value;
		}

		public int getValue() {
			return this.value;
		}
	}

	/**
     * @author kaizhao
     *
     */
    public enum CachePrefix{

    	// 外域注册邮箱激活用token
        EX_DOMAIN_ACTIVATE_TOKEN("ex_domain_activate_token_"),
        MOBILE_REGISTER_ACTIVATE_TOKEN("mobile_register_activate_token_"),
        CAPTCHA("captcha_");

        private String value;

        CachePrefix(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    /**
     * TMP_LOGIN:用于搜狐域登录的token
     * TMP_OPEN_LOGIN:用于第三方账号登录的token
     * TMP_ED_ACTIVE：用于激活的临时token
     * TMP_MOBILE_ACTIVE：用于手机应用注册的账号激活的临时token
     * @author kaizhao
     *
     */
    public enum TOKEN_TYPE{

        TMP_LOGIN("1"), TMP_OPEN_LOGIN("2"), TMP_ED_ACTIVE("3"), TMP_MOBILE_ACTIVE("4"),
        USER_APP_SESSION_TOKEN("5"), TMP_REGISTER("6"), TMP_BIND_MOBILE("7"), TMP_UNBIND_MOBILE("8");

        private String value;

        TOKEN_TYPE(String value){
            this.value = value;
        }

        public String getValue(){
            return value;
        }
    }

    /**
     * limit限制的配置(迁移)
     */
    public interface Limit{

        public static final String DEFAULT_NAME = "default";

        public static final String APP_LIMIET_NODE = "/applimit";

        public static final String PATH_APP_LIMIET = "/applimit/";

        public static final String MAILLIST = "maillist";

        public static final String MAIL_SUBJECT = "API Flow Control: Requests Out of Limit";

        public static final String MAIL_CONTENT = "project: %s access: %s Last Counter: %s  Threshold Config: %s";

        public static final String MAIL_DEFAULT = "default";

        public static final String CONS_SEND_MAIL_FLAG = "sendmailflag";

        public static final String EXPIRE_SUFFIX = "_exp"; //存过期时间的key的后缀

        public static final String PERFIX_MAIL = "mail_"; //存mail的前缀

    }

}
