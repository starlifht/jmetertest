package com.jmeter.probuf;



import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sohu.passport.protocol.protobuf.Passport.Parameter;
import com.sohu.passport.protocol.protobuf.Passport.Result;
import com.sohu.passport.service.protocol.Constant;
import com.sohu.passport.utils.CryptionUtils;
import com.test.probuf.ProtoBufClient;
import com.test.probuf.Tools;
import com.test.probuf.probuf;

public class jmeterTest extends AbstractJavaSamplerClient {
	ProtoBufClient client;
	Method method;
	String status;
	String APPID="200";
	String APPKEY="sohu_test";
	//String methodName;
	public void setupTest(JavaSamplerContext arg0) {
		try {
			
			client = new ProtoBufClient(arg0.getParameter("ip"), Integer.parseInt(arg0.getParameter("port")));
				
			//methodName= arg0.getParameter("methodName");
			method=Tools.getMethod(arg0.getParameter("methodName"));
			status=arg0.getParameter("status");
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		}
	}

	public void teardownTest(JavaSamplerContext arg0) {
		// probuf.client.close();
		 client.close();
		
	}

	@Override
	public Arguments getDefaultParameters() {
		Arguments args = new Arguments();
		args.addArgument("userid", "probuftest${__Random(1,1000,)}@sohu.com");///s${__RandomString(8,zxcvbnmkjhgdfawt56io7890,)}@sohu.com
		args.addArgument("methodName", "");//1${__Random(11111,99999,)}${__Random(11111,99999,)}
		args.addArgument("ip", "10.10.44.151");//probuftest${__Random(1,1000,)}@sohu.com
		args.addArgument("port", "8001");
		args.addArgument("status", "200");
		return args; 
	
	
	}
	public   Result get_uniqname(String userID) throws Exception{//获取昵称/
	     
        
        
        String url = "/3/user/get_uniqname";
        //String userid = "jock.zh@sohu.com";
        String ct = String.valueOf(System.currentTimeMillis());
        String version = "3";
//        String appid = "1122";
//        String key = "t*Gcm>0*oT1EXm826g09gP7<197(@t";
        //String userid = "jock.zh@sohu.com";
        String userid=userID;
        Map<String, String> data = new HashMap<String, String>();
        data.put("userid",userid);
        data.put("appid",APPID);
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
      
        writer.close();
       // System.out.println("999");
        Result result =client.getResult(builder);
 
        // Print the response at last but not least.
      //  System.out.format("%s: %s%n", result.getCode(), result.getMessage());
       // System.out.println(result.getData());

    
		return result;
 
}
	public Result update_user(String userID,String uniqname) throws JsonGenerationException, JsonMappingException, IOException{
		 String url = "/3/user/update_user";
         //String userid = "jock.zh@sohu.com";
         //String userid = "hey钟宏燕@focus.cn";
         String userid=userID;
       
         String ct = String.valueOf(System.currentTimeMillis());
         String version = "3";
//         String newquestion = "我的名字";
//         String newanswer = "sohutest";
         Map<String, String> data = new HashMap<String, String>();
         data.put("userid", userid);
         //data.put("uniqname",RandomStringUtils.randomAlphanumeric(6));
         data.put("uniqname", uniqname);
         data.put("client_ip", "10.1.177.68");
//         data.put("new_question",newquestion);
//         data.put("new_answer",newanswer);
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
     Result result = client.getResult(builder);
	return result;
	}
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {

		SampleResult sr = new SampleResult();
		//String UUID=String.valueOf(System.currentTimeMillis());
		sr.sampleStart();
		sr.setSuccessful(false);
		String uniqname="狐"+RandomStringUtils.randomAlphanumeric(6);
	//	probuf.client=client;	
	String userID = arg0.getParameter("userid");
		//String phone=Tools.getPhoneNum();
		try {
			//Result result=(Result) method.invoke(0,userid,UUID);
//			String url = "/3/user/get_userinfo";
//            String userid = userID;
//           // String userid = "houlinyan@sohu.com";
//            String ct = String.valueOf(System.currentTimeMillis());
//            String version = "3";
//            String UUID=ct;
//            Map<String, String> data = new HashMap<String, String>();
//            data.put("userid", userid);
//            data.put(Constant.KEY_BUSINESS_TYPE, "1");
//            data.put("url", url);
//            data.put("client_ip", "10.1.177.68");
//            data.put("UUID", UUID);
//            StringWriter writer = new StringWriter();
//
//            ObjectMapper objectMapper = new ObjectMapper();
//            objectMapper.writeValue(writer, data);
//
//            Parameter.Builder builder = Parameter.newBuilder();
//            builder.setUrl(url);
//            builder.setAppid("200");
//            builder.setVersion(version);
//            builder.setSignature(CryptionUtils.md5(userid + "200" + "sohu_test" + ct));
//            builder.setCurrentTime(ct);
//            builder.setData(writer.toString());
//            //sendInfo=writer.toString();
//            writer.close();
			  Result result1=update_user(userID, uniqname);
			if (result1.getCode().equals(status)&&result1!=null){
				
				Result result2=get_uniqname(userID);
				String uniqmane2=JSONObject.fromObject(result2.getData()).getString("uniqname");
				if(result2.getCode().equals(status)&&result2!=null&&uniqmane2.equals(uniqname)){
				sr.setSuccessful(true);}else{
					System.out.println("sohuerror"+userID+"|"+uniqname+"|"+uniqmane2);
				}
//				if(result.getMessage().indexOf(UUID)!=-1){
//					sr.setSuccessful(true);
//				}else
//				{
//					System.out.println(userid+"|"+UUID+"|"+result.getMessage());
//					
//				}
			}else{
				
				System.out.println(result1.getCode()+"|"+result1.getMessage()+"|"+result1.getData());
			}
			//sr.setSuccessful(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(e.toString());
			
		}finally{
		
		}
		sr.sampleEnd();
		return sr;
	}


}
