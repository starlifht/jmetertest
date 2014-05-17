package com.test.probuf;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sohu.passport.commons.PassportServiceParams;
import com.sohu.passport.protocol.protobuf.Passport.Parameter;
import com.sohu.passport.protocol.protobuf.Passport.Result;
import com.sohu.passport.service.protocol.Constant;
import com.sohu.passport.utils.CommonUtils;
import com.sohu.passport.utils.CryptionUtils;

public class probuf {
	private static String APPID_AD = "9998";
    private static String APPKEY_AD = "iqE?q#gv8--`G/jGA51]=ZANMg?=Lm";
    private static String APPID = "200";
    private static String APPKEY = "sohu_test";
	  public static String IP = "10.10.50.21";
	  // public static String IP = "10.10.40.71";
	  // public static String IP = "10.1.177.133";
	   public static String request;
	    public  static int PORT = 8001;
	    static String sendInfo=null;
	
	    public    static  ProtoBufClient client ;
	    public  void initClient() throws InterruptedException{
	    	client = new ProtoBufClient(IP, PORT);
	    
	    }
	    public  void endClient(){
	        client.close();
	    }
	    public static Result wapbindmobile(String userID) throws Exception, JsonMappingException, IOException{//绑定手机
	       

	            String url = "/3/user/old/wapbindmobile";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = "18810606510";
	            //String userid = "liuhaibao123123@sohu.com";
	            String userid=userID;
	            String appid = "1097";
	            String key = "SN*ET4EH+%=&Su<m%U[9oclrO)s%s4";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("userid",userid);
	            data.put("appid",appid);

	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            writer.close();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	           System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	           System.out.println(result.getData());
				return result;

	     
	    }
	    public static Result preLogin(String userId) throws Exception{//登陆的预校验   获取验证码（新）
	       

	            String url = "/3/user/prelogin";
	            //String userid = "jock.zh@sohu.com";
	            String appid ="1050";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String userid =userId;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            String appkey = "e8yye8Ee83&%$(eieinvnaa_832wqqpph83hYE*YY";
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + appkey + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
				

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	            return result;
	   
	    }
	    public static Result locationLoginSet(String userID) throws Exception{//设置常用登陆地
	 
	       

	            String url = "/3/user/locallogin/set";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            String userid = userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            data.put(PassportServiceParams.LOGIN_CN_CODE.getName(), "120000");
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	         
	     
	    }
	    public static Result get_weblogincaptcha(String userID) throws Exception{
	       
	         

	            String url = "/3/user/get_weblogincaptcha";
	            //String userid = "jock.zh@sohu.com";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("business_type", "3");
	            data.put(Constant.KEY_CLIENT_IP, IP);

	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	        
	            Map resultMap = CommonUtils.jsonToMap(result.getData());
	            String tagSrc = "d://a.jpg";

	            byte[] bytes = Base64.decodeBase64((String)resultMap.get("base64_image"));
	           
	              Tools.buff2Image(bytes,tagSrc);
				return result;
	          

	       
	 
	    }
	public static  Result authuser(String userID) throws Exception{//验证用户
       
         

            String url = "/3/user/authuser";
		/*	String userid = "jock.zh.t001@sohu.com";
			String password = "12345687";*/
            String userid = userID;
            String password = "12345687";
            String ct = String.valueOf(System.currentTimeMillis());
            String version = "3";

            Map<String, String> data = new HashMap<String, String>();
            data.put("userid", userid);
            data.put("userid_type", "9");
            data.put("password", password);
            data.put("captcha", "w2yxf");
          //  data.put("client_ip","280.10.25.36");
            data.put("password_type", "2");
            data.put(Constant.KEY_CLIENT_IP, "280.135.151.250");
            //data.put(Constant.KEY_BUSINESS_TYPE, "1");

            StringWriter writer = new StringWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, data);

            Parameter.Builder builder = Parameter.newBuilder();
            builder.setUrl(url);
            builder.setAppid(APPID);
            builder.setVersion(version);
            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
            builder.setCurrentTime(ct);
            builder.setData(writer.toString());
            sendInfo=writer.toString();
          writer.close();
            Result result = client.getResult(builder);

            // Print the response at last but not least.
//            System.out.format("%s: %s%n", result.getCode(), result.getMessage());           
//            System.out.println(result.getData());
        
           // assertThat(result.getData(), containsString("ec2ff1f367e3437s"));
      
			return result;

    }
	public   static Result get_uniqname(String userID) throws Exception{//获取昵称/
     
           
            
            String url = "/3/user/get_uniqname";
            //String userid = "jock.zh@sohu.com";
            String ct = String.valueOf(System.currentTimeMillis());
            String version = "3";
            String appid = "1122";
            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
            //String userid = "jock.zh@sohu.com";
            String userid=userID;
            Map<String, String> data = new HashMap<String, String>();
            data.put("userid",userid);
            data.put("appid",appid);
            StringWriter writer = new StringWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, data);

            Parameter.Builder builder = Parameter.newBuilder();
            builder.setUrl(url);
            builder.setAppid(appid);
            builder.setVersion(version);
            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
            builder.setCurrentTime(ct);
            builder.setData(writer.toString());
            sendInfo=writer.toString();
            writer.close();
           // System.out.println("999");
            Result result =client.getResult(builder);
     
            // Print the response at last but not least.
          //  System.out.format("%s: %s%n", result.getCode(), result.getMessage());
           // System.out.println(result.getData());
   
        
			return result;
     
    }
	public static Result check_uniqname(String uniqName) throws Exception{//检查昵称是否存在
     
        

            String url = "/3/user/check_uniqname";
            String ct = String.valueOf(System.currentTimeMillis());
            String version = "3";
            String appid = APPID;
            String key = APPKEY;
            String uniqname = "EWLmEG";

            Map<String, String> data = new HashMap<String, String>();
            data.put("uniqname", uniqname);
            StringWriter writer = new StringWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, data);

            Parameter.Builder builder = Parameter.newBuilder();
            builder.setUrl(url);
            builder.setAppid(appid);
            builder.setVersion(version);
            builder.setSignature(CryptionUtils.md5(uniqname + appid + key+ct));
            builder.setCurrentTime(ct);
            builder.setData(writer.toString());
            sendInfo=writer.toString();
            Result result = client.getResult(builder);
//            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//            System.out.println(result.getData());       
		return result;
    }
	public static  Result getWebloginCaptcha(String userID) throws Exception{//获得页面登陆验证码
     
          

            String url = "/3/token/getWebloginCaptcha";
            //String userid = "jock.zh@sohu.com";
            //String userid = "houlinyan@sohu.com";
            String userid=userID;
            String ct = String.valueOf(System.currentTimeMillis());
            String version = "3";

            Map<String, String> data = new HashMap<String, String>();
            data.put("userid", userid);
            data.put("business_type", "3");
            data.put(Constant.KEY_CLIENT_IP, IP);

            StringWriter writer = new StringWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, data);

            Parameter.Builder builder = Parameter.newBuilder();
            builder.setUrl(url);
            builder.setAppid(APPID);
            builder.setVersion(version);
            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
            builder.setCurrentTime(ct);
            builder.setData(writer.toString());
            sendInfo=writer.toString();
            Result result = client.getResult(builder);

            // Print the response at last but not least.
//            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//            System.out.println(result.getData());
          
            Map resultMap = CommonUtils.jsonToMap(result.getData());
            String tagSrc = "d://a.jpg";

            byte[] bytes = Base64.decodeBase64((String)resultMap.get("base64_image"));
         
               Tools.buff2Image(bytes,tagSrc);
           

            client.close();
			return result;
    
    }
	public static  Result verifyWebLoginCaptcha(String userID) throws Exception{//校验登陆 验证码
  
           

            String url = "/3/token/verifyWebloginCaptcha";
            //String userid = "jock.zh@sohu.com";
           // String userid = "houlinyan@sohu.com";
            String userid=userID;
            String ct = String.valueOf(System.currentTimeMillis());
            String version = "3";

            Map<String, String> data = new HashMap<String, String>();
            data.put("userid", userid);
            data.put("business_type", "3");
            data.put("answer", "w5ax6");
            data.put(Constant.KEY_CLIENT_IP, IP);

            StringWriter writer = new StringWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, data);

            Parameter.Builder builder = Parameter.newBuilder();
            builder.setUrl(url);
            builder.setAppid(APPID);
            builder.setVersion(version);
            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
            builder.setCurrentTime(ct);
            builder.setData(writer.toString());
            sendInfo=writer.toString();
            Result result = client.getResult(builder);

            // Print the response at last but not least.
//            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//            System.out.println(result.getData());
	     

			return result;
 
    }
	 public static Result reg_user(String userID) throws Exception{//注册搜狐通行证账号
	      

	            String url = "/3/user/reg_user";
	            //String userid = "jock.zh@sohu.com";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("password", "12345687");
	            data.put("password_type", "0");
	           // data.put("mtoken", "8609");
	            data.put("question", "我的名字");
	            data.put("answer", "sohutest");
	            data.put("client_ip", "10.1.177.68");
	            data.put("uniqname_force", "0");
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);
	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	           
	         
	      
			return result;
	    }

	 
	  public static Result get_userinfo(String userID,String UUID) throws Exception{//获得用户基本信息
	       
	         
				
				/*String url = "/3/user/get";
				String userid = "jock.zh@sohu.com";
				String ct = String.valueOf(System.currentTimeMillis());
				String version = "3";
				
				Map<String, String> data = new HashMap<String, String>();
				data.put("userid", userid);
				data.put("userid_type", "1");*/
	            String url = "/3/user/get_userinfo";
	            String userid = userID;
	           // String userid = "houlinyan@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put(Constant.KEY_BUSINESS_TYPE, "1");
	            data.put("url", url);
	            data.put("client_ip", "10.1.177.68");
	            data.put("UUID", UUID);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            writer.close();
	            Result result = client.getResult(builder);
				return result;

	            // Print the response at last but not least.
	           // System.out.format("%s: %s%n", result.getCode(), result.getMessage());
	           // System.out.println(result.getData());
	         
	   
	    }

	
	 public static Result wapauth(String userID) throws Exception{//wap应用检查用户名和密码是否正确（wap_auth）
	      
	           

	            String url = "/3/user/old/wapauth";
			/*	String userid = "jock.zh.t001@sohu.com";
				String password = "12345687";*/
	           // String userid = "ppauthtest82@sohu.com";
	            String userid=userID;
	            String password = "12345687";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("userid_type", "9");
	            data.put("password", password);
	            // data.put("captcha", "w2yxf");
	            data.put("password_type", "2");
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            //data.put(Constant.KEY_BUSINESS_TYPE, "1");

	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
	           
	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	         
				return result;
	       
	    }
	 public static Result  activate(String userID) throws Exception{
	   
	          //  ProtoBufClient client = new ProtoBufClient(IP, PORT);

	            String url = "/3/user/old/activate";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	          //  String userid = "houlinyan001@sohu.com";
	            String userid=userID;
	            String appid = "1073";
	            String key = "?qPQ(UIeRoq.=c+IYYx%x7159ZpF'=";
	            data.put("userid",userid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	          
	         
				return result;
	     
	    }
	
	 public static Result  update_user(String userID,String uniqname) throws Exception{//更改用户基本信息
	 

	            String url = "/3/user/update_user";
	            //String userid = "jock.zh@sohu.com";
	            //String userid = "hey钟宏燕@focus.cn";
	            String userid=userID;
	          
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
//	            String newquestion = "我的名字";
//	            String newanswer = "sohutest";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            //data.put("uniqname",RandomStringUtils.randomAlphanumeric(6));
	            data.put("uniqname", uniqname);
	            data.put("client_ip", "10.1.177.68");
//	            data.put("new_question",newquestion);
//	            data.put("new_answer",newanswer);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	         
				return result;
	  

	    }
	 public static Result activateWebreg(String userID) throws Exception{//外语注册激活
	      

	            String url = "/3/user/activate_webreg";
	            //String userid = "jock.zh@sohu.com";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String code = "1680290711edfb004f1f3ae3bdc49a48";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("code", code);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put("token", "69a4e70ab8dd94d39bcd8493c0a25edb");
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	          
	    }
	  public static Result  activeemail(String userID) throws Exception, JsonMappingException, IOException{//重新发送激活邮件
	       

	            String url = "/3/user/activate_email";
	            //String userid = "jock.zh@sohu.com";

	            String activateInfo = "a@a.com|9998|69a4e70ab8dd94d39bcd8493c0a25edb";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_ACTIVATEINFO, activateInfo);
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(activateInfo + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
	            
	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
		     
				
	    }
	  public static Result updateProtectQuestion(String userID) throws Exception{//修改密保问题
	    	
	            //  ProtoBufClient client = new ProtoBufClient(IP, PORT);
	           

	            String url = "/3/user/question/update";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            // String userid = "13810765026";
	            String userid = userID;
	           // String passwd = CryptionUtils.md5("houlinyan");
	            String token = "verfMds2fsg1V2d";
	            String passwd = "12345687";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_PASSWORD, passwd);
	            data.put("question", "我的名字");
	            data.put("answer", "sohutest");
	         data.put("old_answer","sohutest");
	            data.put("client_ip", IP);
	         
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

//	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	      
	    }
	  public static Result loadhead(String userID) throws Exception {
	    

	             String url = "/3/user/loadhead";
	             //String userid = "jock.zh@sohu.com";
	             String ct = String.valueOf(System.currentTimeMillis());
	             String version = "3";
	             //  String userid = "liuhaibao456456@sohu.com";
	             String userid = userID;
	             Map<String, String> data = new HashMap<String, String>();
	             data.put(Constant.KEY_CLIENT_IP, IP);
	             data.put(Constant.KEY_USERID, userid);
	             data.put("head_pic", "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");
	             StringWriter writer = new StringWriter();

	             ObjectMapper objectMapper = new ObjectMapper();
	             objectMapper.writeValue(writer, data);

	             Parameter.Builder builder = Parameter.newBuilder();
	             builder.setUrl(url);
	             builder.setAppid(APPID);
	             builder.setVersion(version);
	             builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	             builder.setCurrentTime(ct);
	             builder.setData(writer.toString());
	             sendInfo=writer.toString();
	             Result result = client.getResult(builder);

	             // Print the response at last but not least.
//	             System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	             System.out.println(result.getData());
				return result;
	          
	    }
	  public static Result clearuserinfo(String userID) throws JsonGenerationException, JsonMappingException, IOException, InterruptedException{
	     


	           String url = "/3/admin/old/clearuserinfo";
	           //String userid = "jock.zh@sohu.com";
	           String ct = String.valueOf(System.currentTimeMillis());
	           String version = "3";
	           String appid = "1122";
	           String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	           String userid = userID;
	           Map<String, String> data = new HashMap<String, String>();
	           data.put("userid", userid);
	           data.put("type","2");
	           StringWriter writer = new StringWriter();
	           ObjectMapper objectMapper = new ObjectMapper();
	           objectMapper.writeValue(writer, data);
	           Parameter.Builder builder = Parameter.newBuilder();
	           builder.setUrl(url);
	           builder.setAppid(appid);
	           builder.setVersion(version);
	           builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	           builder.setCurrentTime(ct);
	           builder.setData(writer.toString());
	           sendInfo=writer.toString();
	           Result result = client.getResult(builder);
	           // Print the response at last but not least.
//	           System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	           System.out.println(result.getData());
			return result;
	    
	   }
	  public static Result deleteusers(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	     

	            String url = "/3/user/old/deleteusers";
	            String appid = "1013";
	    		String appkey = "djfi(Y&%ye483y45&%^830934dHisd2y%*HIUGDdii";
	            //String userid = "jock.zh@sohu.com";
	            //多个账号,分割，但是只能是vip账号(appid是1013,domain是vip.sohu.com)
	            String userids ="ppauthtest112@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userids", userids);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userids + appid + appkey + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
				return result;

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	           
	    }
	  public static Result lockuserinfo(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	    

	           String url = "/3/admin/old/lockuserinfo";
	           //String userid = "jock.zh@sohu.com";
	           String ct = String.valueOf(System.currentTimeMillis());
	           String version = "3";
	           String appid = "1122";
	           String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	           String userid = userID;

	           Map<String, String> data = new HashMap<String, String>();
	           data.put("userid", userid);
	           data.put("type","1");
	           StringWriter writer = new StringWriter();
	           ObjectMapper objectMapper = new ObjectMapper();
	           objectMapper.writeValue(writer, data);
	           Parameter.Builder builder = Parameter.newBuilder();
	           builder.setUrl(url);
	           builder.setAppid(appid);
	           builder.setVersion(version);
	           builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	           builder.setCurrentTime(ct);
	           builder.setData(writer.toString());
	           sendInfo=writer.toString();
	           Result result = client.getResult(builder);
			return result;

	           // Print the response at last but not least.
//	           System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	           System.out.println(result.getData());

	   }
	  public static Result update_pwd(String userID) throws JsonGenerationException, JsonMappingException, IOException{//修改密码和密保
	       

	            String url = "/3/user/update_pwd";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String password = "12345687";
	            String userid = userID;
	            String newPassword = "12345687";
	            String newquestion = "我的名字";
	            String newanswer = "sohutest";

	            Map<String, String> data = new HashMap<String, String>();

	            data.put("userid",userid);
	            data.put("password",password);
	            data.put("new_password",newPassword);
	            data.put("new_question",newquestion);
	            data.put("new_answer",newanswer);
	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	       }
	
	    public static Result recpwd_bindmobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{//根据绑定手机找回密码
	  

	            String url = "/3/user/recpwd_bindmobile";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("mobile", "15210415413");
	            data.put("new_password", "123123123");
	            data.put("mtoken", "7248");
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	         
	    }
	    public static Result get_token_mailrecpwd(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获取邮件找回密码的token
	       

	            String url = "/3/user/get_token_mailrecpwd";
	            String userid = "ppauthtest54@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            // String userid = "liuhaibao456456@sohu.com";
	            // String userid = "13810765026";
	            //String userid = userID;
	            String email = "154985201@qq.com";
	            //  String password = "5pn4783m$nbNajvc2qFf2/i.bLDkAu0";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_EMAIL, email);

	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
				return result;

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	          
	    }
	    public static Result  mailsynchuser(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	      
	    	  String appid = "1113";
		       String key = "<aVc0Ze*P#xY8y()OWcavQOP(GmsQD";
	            String url = "/3/user/old/mailsynchuser";
	            //String userid = "jock.zh@sohu.com";
	            //多个账号,分割，但是只能是vip账号(appid是d,domain是vip.sohu.com)
	            String userid =userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid+ key + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();	            
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	      
	    }
	    public static Result recpwd_mail(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	  

	            String url = "/3/user/recpwd_mail";
	            String userid =userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("email", userid);
	            data.put("new_password", "12345687");
	            data.put("token", "rjIL7NGCAaCUA_Stwvu1-uh6qxsihO");
	            data.put("code", "33052d7b498876bc22cbbe4b0aeb4cc8");
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	          
	    }
	    public static Result  do_appeal(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	 
	             String url = "/3/user/do_appeal";
	             String userid = userID;
	             String ct = String.valueOf(System.currentTimeMillis());
	             String version = "3";
	             Map<String, String> data = new HashMap<String, String>();
	             data.put(Constant.KEY_CLIENT_IP, IP);
	             data.put("userid", userid);
	             data.put("username", "侯林燕");
	             data.put("uniqname", "uniqname");
	             data.put("personal_id", "230223198609260035");
	             data.put("personalId_pic", "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");
	             data.put("gender", "1");
	             data.put("birthday", "2013-05-01");
	             data.put("email", "122052876@qq.com");
	             data.put("mobile", "18618268934");
	             data.put("register_date", "2013-05-01");
	             data.put("login_date", "2013-05-01");
	             data.put("password1", "123123123");
	             data.put("password2", "houlinyan");
	             data.put("otherinfo", "fdsafsadfsadfdsafsadfsdaf");

	             StringWriter writer = new StringWriter();

	             ObjectMapper objectMapper = new ObjectMapper();
	             objectMapper.writeValue(writer, data);

	             Parameter.Builder builder = Parameter.newBuilder();
	             builder.setUrl(url);
	             builder.setAppid(APPID);
	             builder.setVersion(version);
	             builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	             builder.setCurrentTime(ct);
	             builder.setData(writer.toString());
	             sendInfo=writer.toString();
	             Result result = client.getResult(builder);

	             // Print the response at last but not least.
	            // System.out.format("%s: %s%n", result.getCode(), result.getMessage());
				return result;
	      
	    }
	    public static Result adminupdatepassword(String userID) throws JsonGenerationException, JsonMappingException, IOException{//管理员更新密码
	      
	            

	            String url = "/3/admin/old/adminupdatepassword";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String userid = userID;
	            String password = "12345687";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("password",password);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID_AD);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID_AD + APPKEY_AD+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	           
	    }
	    public static Result get_token_bindmail(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获取绑定邮件Token
	    	

	            String url = "/3/user/get_token_bindmail";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            String userid = userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_PASSWORD, "12345687");
	            data.put(PassportServiceParams.BIND_EMAIL.getName(), "253577292@qq.com");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	        
	    }
	    public static Result bind_mail(String userID) throws JsonGenerationException, JsonMappingException, IOException{//验证绑定


	            String url = "/3/user/bind_mail";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            String userid = userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            data.put(PassportServiceParams.BIND_EMAIL.getName(), "253577292@qq.com");
	            data.put(PassportServiceParams.TOKEN.getName(), "5HWejxHUqZJsJeLrS");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
				return result;

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	        
	    }
	    public static Result send_bindmail(String userID) throws JsonGenerationException, JsonMappingException, IOException{//发送绑定邮件
	    	      String url = "/3/user/old/send_bindmail";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            String userid = userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_PASSWORD, CryptionUtils.md5("12345687"));
	            data.put("ru", Constant.EMAIL_RECOVER_PWD_URL);
	            data.put(PassportServiceParams.BIND_EMAIL.getName(), "453454353@qq.com");
	            data.put(PassportServiceParams.OLD_BIND_EMAIL.getName(), "154983455201@qq.com");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	        
	    }
	    public static Result send_emailcaptcha(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	      
	    	//注册搜狐账号的邮箱验证码
	            String url = "/3/user/old/send_emailcaptcha";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String email = userID;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("email",email);
	            data.put("appid",appid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(email + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	        
	    }
	    public static Result bind_mobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{//手机绑定
	
	             String url = "/3/user/bind_mobile";
	             //String userid = "jock.zh@sohu.com";
	             String ct = String.valueOf(System.currentTimeMillis());
	             String version = "3";
	             //  String userid = "liuhaibao456456@sohu.com";
	             // String userid = "13810765026";
	             String userid = "userID";
	             String mobile = "19665268934";
	             String token = "0305";
	             //  String password = "5pn4783m$nbNajvc2qFf2/i.bLDkAu0";
	             Map<String, String> data = new HashMap<String, String>();
	             data.put(Constant.KEY_USERID, userid);
	             data.put(Constant.KEY_MOBILE, mobile);
	             data.put(Constant.KEY_TOKEN, token);
	             StringWriter writer = new StringWriter();
	             ObjectMapper objectMapper = new ObjectMapper();
	             objectMapper.writeValue(writer, data);
	             Parameter.Builder builder = Parameter.newBuilder();
	             builder.setUrl(url);
	             builder.setAppid(APPID);
	             builder.setVersion(version);
	             builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	             builder.setCurrentTime(ct);
	             builder.setData(writer.toString());
	             sendInfo=writer.toString();
	             Result result = client.getResult(builder);
	             // Print the response at last but not least.
//	             System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	             System.out.println(result.getData());
				return result;


	    }
	    public static Result unbind_mobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	      

	            String url = "/3/user/unbind_mobile";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            String userid =userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_M_TOKEN, "6402");
	            data.put(Constant.KEY_MOBILE, "19665268934");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	          
	    }
	    public static  Result bindmobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{//根据 captcha绑定手机
	        

	             String url = "/3/user/old/bindmobile";
	             //String userid = "jock.zh@sohu.com";
	             String ct = String.valueOf(System.currentTimeMillis());
	             String version = "3";
	             String mobile = "13831688127";
	             String userid = userID;
	             String appid = "1097";
	             String key = "SN*ET4EH+%=&Su<m%U[9oclrO)s%s4";

	             Map<String, String> data = new HashMap<String, String>();
	             data.put("mobile",mobile);
	             data.put("userid",userid);
	             data.put("appid",appid);
	             data.put("captcha","64183");

	             data.put(Constant.KEY_BUSINESS_TYPE,"2");
	             data.put("client_ip","127.0.0.1");
	             StringWriter writer = new StringWriter();

	             ObjectMapper objectMapper = new ObjectMapper();
	             objectMapper.writeValue(writer, data);

	             Parameter.Builder builder = Parameter.newBuilder();
	             builder.setUrl(url);
	             builder.setAppid(appid);
	             builder.setVersion(version);
	             builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	             builder.setCurrentTime(ct);
	             builder.setData(writer.toString());
	             sendInfo=writer.toString();
	             Result result = client.getResult(builder);

	             // Print the response at last but not least.
//	             System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	             System.out.println(result.getData());
				return result;
	          
	     }
	    
	    public static  Result unbindmobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	      
	            String url = "/3/user/old/unbindmobile";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = "13831688127";
	            String appid = "1097";
	            String key = "SN*ET4EH+%=&Su<m%U[9oclrO)s%s4";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("appid",appid);
	            data.put("captcha","70406");

	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	      
	    }
	    public static Result wapauthmobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{//验证手机号
	       

	            String url = "/3/user/old/wapauthmobile";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = "15210415413";
	            String userid = "liuhaibao123123@sohu.com";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("userid",userid);
	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	        
	    }
	    public static Result wapgetmobileinfo(String userID) throws JsonGenerationException, JsonMappingException, IOException{//根据userid获取绑定手机号
	      


            String url = "/3/user/old/wapgetmobileinfo";
            //String userid = "jock.zh@sohu.com";
            String ct = String.valueOf(System.currentTimeMillis());
            String version = "3";
            String appid = "1122";
            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
            String userid = userID;

            Map<String, String> data = new HashMap<String, String>();
            data.put("userid",userid);
            data.put("appid",appid);
            StringWriter writer = new StringWriter();

            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(writer, data);

            Parameter.Builder builder = Parameter.newBuilder();
            builder.setUrl(url);
            builder.setAppid(appid);
            builder.setVersion(version);
            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
            builder.setCurrentTime(ct);
            builder.setData(writer.toString());
            sendInfo=writer.toString();
            Result result = client.getResult(builder);

            // Print the response at last but not least.
//            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//            System.out.println(result.getData());
				return result;
	        }
	    public static  Result  wapswitchmobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{//交换绑定
	      

	            String url = "/3/user/old/wapswitchmobile";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = "15210415413";
	            String userid1 = "liuhaibao123123@sohu.com";
	            String userid2 = "houlinyan@sohu.com";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("userid1",userid1);
	            data.put("userid2",userid2);
	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid1+userid2 + APPID + APPKEY+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	       
	    }
	    public static Result wapunbindmobile(String number) throws JsonGenerationException, JsonMappingException, IOException{//解绑
	     
	            String url = "/3/user/old/wapunbindmobile";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = number;
//	            String appid = "1097";
//	            String key = "SN*ET4EH+%=&Su<m%U[9oclrO)s%s4";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("appid",APPID);
	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + APPID + APPKEY+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	    }
	    public static Result wapgetuserid(String number) throws JsonGenerationException, JsonMappingException, IOException{//根据手机号获取userid
	       
	            String url = "/3/user/wapgetuserid";
	            String userid = "18810606513";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile", userid);
	            data.put("client_ip", "10.1.177.68");
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	      
	    }
	    public static Result getquestion(String userID) throws JsonGenerationException, JsonMappingException, IOException{//信息获取(密保等)安全中心
	     
	            String url = "/3/user/question/get";
	            //String userid = "jock.zh@sohu.com";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            // String mobile = "13811296710";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	        
	    }
	    public static Result get_appinfo(String uerID) throws JsonGenerationException, JsonMappingException, IOException{
	    

	             String url = "/3/user/get_appinfo";
	             //String userid = "jock.zh@sohu.com";
	             String cookieid = "9002";
	             String ct = String.valueOf(System.currentTimeMillis());
	             String version = "3";

	             Map<String, String> data = new HashMap<String, String>();
	             data.put("cookieid", cookieid);
	             data.put(Constant.KEY_CLIENT_IP, IP);
	             StringWriter writer = new StringWriter();

	             ObjectMapper objectMapper = new ObjectMapper();
	             objectMapper.writeValue(writer, data);

	             Parameter.Builder builder = Parameter.newBuilder();
	             builder.setUrl(url);
	             builder.setAppid(APPID);
	             builder.setVersion(version);
	             builder.setSignature(CryptionUtils.md5(cookieid + APPID + APPKEY + ct));
	             builder.setCurrentTime(ct);
	             builder.setData(writer.toString());
	             sendInfo=writer.toString();
	             Result result = client.getResult(builder);

	             // Print the response at last but not least.
//	             System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	             System.out.println(result.getData());
				return result;
	         
	    }
	    public static Result wapgetmobile(String userID) throws JsonGenerationException, JsonMappingException, IOException{//根据userid获取绑定手机号
	       
	            String url = "/3/user/wapgetmobile";
	            String userid = "ppauthtest2@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("client_ip", "10.1.177.68");

	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	   
	    }
	    public static  Result  wapgetbindemail(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获取用户绑定邮箱
	      
	            String url = "/3/user/wapgetbindemail";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
				return result;

	            // Print the response at last but not least.
	      /*      System.out.format("%s: %s%n", result.getCode(), result.getMessage());
	            System.out.println(result.getData());*/
	          
	    }
	    public static Result getloginhistory(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获取用户登录历史
	      

	            String url = "/3/user/loginhistory/get";
	            //String userid = "jock.zh@sohu.com";
	            String userid =userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            // String mobile = "13811296710";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);
	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	          
	    }
	    public static Result get_createip(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获取客户端ip和时间
	      
	            String url = "/3/user/get_createip";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String userid =userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);
	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	           
	    }
	    public static Result  get_lastlogin(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	      

	            String url = "/3/user/get_lastlogin";
	            //String userid = "jock.zh@sohu.com";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            // String mobile = "13811296710";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	          //  System.out.println(writer.toString());
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	        
	    }
	    public static Result getuidbyuserid(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	       
	            String url = "/3/user/old/getuidbyuserid";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String userid = userID;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid",userid);
	            data.put("appid",appid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	        
	    }
	    public static Result getuuidbyuserid(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	      

	            String url = "/3/user/old/getuuidbyuserid";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String userid = userID;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid",userid);
	            data.put("appid",appid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	       
	    }
	    public static Result getuseridbyuid(String UID) throws JsonGenerationException, JsonMappingException, IOException{
	       
	            String url = "/3/user/old/getuseridbyuid";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String uid="5499fe497402415s";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("uid",uid);
	            data.put("appid",appid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(uid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	         
	}
	    public static Result getuseridbyuuid(String UUID) throws JsonGenerationException, JsonMappingException, IOException{
	     

	            String url = "/3/user/old/getuseridbyuuid";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String uuid="5499fe497402415s";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("uuid",uuid);
	            data.put("appid",appid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(uuid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	        
	    }
	    public static Result verify_pwd(String userID) throws JsonGenerationException, JsonMappingException, IOException{//用户密码校验
	       
	            String url = "/3/user/verify_pwd";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            String userid = userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_PASSWORD, "12345687");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	          
	    }
	    public static Result check_user(String userID) throws JsonGenerationException, JsonMappingException, IOException{//检查用户是否存在
	        
	            String url = "/3/user/check_user";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
	         //   System.out.format("%s: %s%n", result.getCode(), result.getMessage());
				return result;
	          
	    }
	    public static Result check_v(String userID) throws JsonGenerationException, JsonMappingException, IOException{//检查用户是否加V
	        

	            String url = "/3/user/check_v";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String userid =userID;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	          
	    }
	    public static Result send_mobileregcaptcha(String number) throws JsonGenerationException, JsonMappingException, IOException{//发送手机注册验证码
	      

	            String url = "/3/user/old/send_mobileregcaptcha";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "200";
	            String key = "sohu_test";
	            String mobile=number;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile", mobile);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	           
	    }
	    public static Result reg_mobile_mailuser(String userID) throws JsonGenerationException, JsonMappingException, IOException{ //手机app注册邮件账号
	       
	            String url = "/3/user/reg_mobile_mailuser";
	            String userid = userID;


	            String appid = "200";
	            String key = "sohu_test";
	            String ostype ="02";
	            String modeltype ="ffff";
	            String mask="1101";

	            String imei="1";
	            String imsi="1097";
	            String mac="";
	            String uuid="1097";
	            String gid=null;
	            if("".equals(imei) && "".equals(imsi) &&"".equals(mac)){
	                gid = ostype + modeltype + appid + mask + CryptionUtils.md5(uuid);
	            }else{
	                gid = ostype + modeltype + appid + mask + CryptionUtils.md5(imei + imsi + mac);
	            }


	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();
	            data.put(Constant.KEY_USERID,userid);
	            data.put(Constant.KEY_GID,gid);
	            data.put(Constant.KEY_PASSWORD,"Hyf0926_");

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + gid+key));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	    }
	    public static Result reg_mobile_appuser(String userID) throws JsonGenerationException, JsonMappingException, IOException{//手机app注册
	      
	            String url = "/3/user/reg_mobile_appuser";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "200";
	            String key = "sohu_test";
	            String mobile="19618251234";
	            String password = "12345687";
	            String captcha ="2146";
	            String ostype ="02";
	            String modeltype ="ffff";
	            String mask="1101";
	            String imei="1";
	            String imsi="1097";
	            String mac="";
	            String uuid="1097";
	            String gid=null;
	            if("".equals(imei) && "".equals(imsi) &&"".equals(mac)){
	                gid = ostype + modeltype + appid + mask + CryptionUtils.md5(uuid);
	            }else{
	                gid = ostype + modeltype + appid + mask + CryptionUtils.md5(imei + imsi + mac);
	            }

	            String sig = CryptionUtils.md5(mobile+appid+gid+key );


	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile", mobile);
	            data.put("captcha", captcha);
	            data.put("gid", gid);
	            data.put("sig", sig);
	            data.put("password", password);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + gid+key));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	         
	    }
	    public static Result get_mobtoken(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获取手机相关Token
	     
	            String url = "/3/user/get_mobiletoken";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            // String userid = "13810765026";
	            String userid = userID;
	            String password = CryptionUtils.md5("12345687");
	            //  String password = "5pn4783m$nbNajvc2qFf2/i.bLDkAu0";
//	            String appid = "1106";
//	            String key = "UGA7aNYJeU)Uc6@16E*2C759Bo3fTc";	  
	            String appid = APPID;
	            String key = APPKEY;	 
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_PASSWORD, password);
	            String ostype ="02";
	            String modeltype ="ffff";
	            String mask="1101";

	            String imei="1";
	            String imsi="1097";
	            String mac="";
	            String uuid="1097";
	            String gid=null;
	            if("".equals(imei) && "".equals(imsi) &&"".equals(mac)){
	                gid = ostype + modeltype + appid + mask + CryptionUtils.md5(uuid);
	            }else{
	                gid = ostype + modeltype + appid + mask + CryptionUtils.md5(imei + imsi + mac);
	            }

	            data.put("gid", gid);

	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + gid+ key));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	    }
	    public static Result getmobileCode(String number) throws JsonGenerationException, JsonMappingException, IOException{//获取手机登录验证码
	     

	            String url = "/3/user/get_mobcode";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String mobile = number;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("appid",appid);
	            data.put("ip","10.1.177.68");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	          
	    }
	    public static Result wapgetuseridlist(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	      //获取手机号的 绑定和验证码列表

	            String url = "/3/user/old/wapgetuseridlist";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "1122";
	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String mobile = "15210415413";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("appid",appid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	       
	    }
	    public static  Result reg_mobile_direct(String number) throws JsonGenerationException, JsonMappingException, IOException{ //手机号直接注册
	  

	            String url = "/3/user/reg_mobile_direct";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = APPID;
	            String key = APPKEY;
	            String mobile = number;
	            String password = "12345687";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("password",password);
	            data.put("appid",appid);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	         
	    }
	    public static Result sendmobilecaptcha(String number) throws JsonGenerationException, JsonMappingException, IOException{
	    	//送手机注册验证码,bustype 1,2,(注册与登陆)    3,4（绑定手机与解绑手机
	       

	            String url = "/3/user/old/sendcaptcha";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = number;
	            String appid = "1097";
	            String key = "SN*ET4EH+%=&Su<m%U[9oclrO)s%s4";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("appid",appid);

	            data.put(Constant.KEY_BUSINESS_TYPE,"1");
	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	         
	    }
	    public static Result get_mobilecaptcha(String number) throws JsonGenerationException, JsonMappingException, IOException{//获取手机绑定验证码
	    

	            String url = "/3/user/get_mobilecaptcha";
	            String userid = "ppauthtest82@sohu.com";
	            String mobile = number;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	           data.put("userid", userid);
	            data.put("business_type", "6");//5、绑定手机验证码
//	            6、web端手机号注册验证码
//	            7、返回找回密码手机验证码
//	            8、解绑手机验证码
	            data.put("mobile",mobile);
	            data.put("client_ip", "10.1.177.68");

	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	           
	    }
	    public static Result verify_mobilecaptcha(String Token) throws JsonGenerationException, JsonMappingException, IOException{//校验验证码
	     
	            String url = "/3/user/verify_mobilecaptcha";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = "18810607588";
	            String appid = "1097";
	            String key = "SN*ET4EH+%=&Su<m%U[9oclrO)s%s4";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("mobile",mobile);
	            data.put("appid",appid);
	            data.put("captcha","4589");

	            data.put(Constant.KEY_BUSINESS_TYPE,"1");
	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;


	    }
	    public static Result get_cookieinfo(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获得cookie信息
	    

	            String url = "/3/user/get_cookieinfo";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
//	            String appid = "1122";
//	            String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
	            String appid=APPID;
	            String key=APPKEY;
	            String userid = userID;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("persistentcookie","0");
	            data.put("userid",userid);
	            data.put("appid",appid);
	            data.put("ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
				return result;

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	        
	    }
	    public static Result  getservertoken(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获得server Token
	       

	            String url = "/3/user/old/getservertoken";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "200";
	            String key = "sohu_test";
	            String userid = userID;

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("type","2");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	       
	    }
	    public static Result send_jms(String number) throws JsonGenerationException, JsonMappingException, IOException{
	    

	            String url = "/3/user/old/send_jms";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String mobile = number;
	           String appid = "1097";
	           String key = "SN*ET4EH+%=&Su<m%U[9oclrO)s%s4";
	           // String appid = APPID;
	           // String key = APPKEY;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("message",mobile);
	            data.put("appid",appid);
	            data.put("client_ip","127.0.0.1");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(mobile + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	    }
	    public static Result AuthServerToken(String Token) throws JsonGenerationException, JsonMappingException, IOException{//验证server  Token
	       
	            String url = "/3/user/old/auth";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            String appid = "200";
	            String key = "sohu_test";
	            String userid = "jock.zh@sohu.com";
	            String token = "721226f7507e7ecbe4042a0f31dce700";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("type","2");
	            data.put("token",token);
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(appid);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + appid + key+ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);
				return result;

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
	        
	    }
	    public static Result getSecurity(String userID) throws JsonGenerationException, JsonMappingException, IOException{//获取安全提醒信息
	   
	            String url = "/3/user/security/get";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";

	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put("client_ip", "10.1.177.68");
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	         
	    }
	    public static  Result updateSecurity(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	    	
	             String url = "/3/user/security/update";
	             String userid = userID;
	             String ct = String.valueOf(System.currentTimeMillis());
	             String version = "3";
	             Map<String, String> data = new HashMap<String, String>();
	             data.put("userid", userid);
	             data.put("warn_content", "fdsfsdafdsafsdafsdafsdafsadfsafsadf");
	             data.put("client_ip", "10.1.177.68");
	             StringWriter writer = new StringWriter();
	             ObjectMapper objectMapper = new ObjectMapper();
	             objectMapper.writeValue(writer, data);

	             Parameter.Builder builder = Parameter.newBuilder();
	             builder.setUrl(url);
	             builder.setAppid(APPID);
	             builder.setVersion(version);
	             builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	             builder.setCurrentTime(ct);
	             builder.setData(writer.toString());
	             sendInfo=writer.toString();
	             Result result = client.getResult(builder);

	             // Print the response at last but not least.
//	             System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	             System.out.println(result.getData());
				return result;
	           
	    }
	    public static Result getUserquestion(String userID) throws JsonGenerationException, JsonMappingException, IOException{//信息获取(密保等)安全中心
	      
	            String url = "/3/user/question/get";
	            //String userid = "jock.zh@sohu.com";
	            String userid = userID;
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            // String mobile = "13811296710";
	            Map<String, String> data = new HashMap<String, String>();
	            data.put("userid", userid);
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            StringWriter writer = new StringWriter();
	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;
	        
	    }
	   
	    public static Result recpwd_question(String userID) throws JsonGenerationException, JsonMappingException, IOException{
	     
	            String url = "/3/user/recpwd_question";
	            //String userid = "jock.zh@sohu.com";
	            String ct = String.valueOf(System.currentTimeMillis());
	            String version = "3";
	            //  String userid = "liuhaibao456456@sohu.com";
	            String userid = userID;
	            Map<String, String> data = new HashMap<String, String>();
	            data.put(Constant.KEY_CLIENT_IP, IP);
	            data.put(Constant.KEY_USERID, userid);
	            data.put(Constant.KEY_BUSINESS_TYPE,"2");
	            data.put("answer", "sohutest");
	            data.put("new_password", "12345687");
	            StringWriter writer = new StringWriter();

	            ObjectMapper objectMapper = new ObjectMapper();
	            objectMapper.writeValue(writer, data);

	            Parameter.Builder builder = Parameter.newBuilder();
	            builder.setUrl(url);
	            builder.setAppid(APPID);
	            builder.setVersion(version);
	            builder.setSignature(CryptionUtils.md5(userid + APPID + APPKEY + ct));
	            builder.setCurrentTime(ct);
	            builder.setData(writer.toString());
	            sendInfo=writer.toString();
	            Result result = client.getResult(builder);

	            // Print the response at last but not least.
//	            System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//	            System.out.println(result.getData());
				return result;

	      
	    }

	public static void main(String[] args) throws Exception {
	 // System.out.print("test"+probuf_test.authuser("ppauthest54@sohu.com").getCode());
		new probuf().initClient();
//		new probuf().client88 = new ProtoBufClient("10.16.28.151", 9001);
		for(int i=1;i<=200;i++){
			//Result result1=authuser("ppauthtest"+i+"@sohu.com");
			Result result1=authuser("pp4u8thte9st"+i+"@sohu.com");
			System.out.format("%s: %s%n", result1.getCode(), result1.getMessage());
	        System.out.println(result1.getData());
			System.out.println(i);
		}
		//activeemail();
//		//activate(client,"ppauthtest3@sohu.com");
//		Result result=Tools.JustRun("get_uniqname","ppauthtest3@sohu.com");
//		  System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//          System.out.println(result.getData());
		//Result result=get_appinfo();
	//	Result result=authuser("ppauthtest28@sohu.com");
	//	System.out.format("%s: %s%n", result.getCode(), result.getMessage());
        //System.out.println(JSONObject.fromObject(result.getData()).getString("uniqname"));
        
//        Result result1=getuuidbyuserid("ppauthtest65@sohu.com");
//		System.out.format("%s: %s%n", result1.getCode(), result1.getMessage());
//        System.out.println(result1.getData());
//        Result result2=getuseridbyuid("5499fe497402415s");
//		System.out.format("%s: %s%n", result2.getCode(), result2.getMessage());
//        System.out.println(result2.getData());
//        Result result3=getuseridbyuuid("5499fe497402415s");
//		System.out.format("%s: %s%n", result3.getCode(), result3.getMessage());
//        System.out.println(result3.getData());
        
		//getWebloginCaptcha();
	//	updateuser("ppauthtest18@sohu.com");
		client.close();
	
		 
	}

}
