package com.jmeter.probuf;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


public class agent_request extends AbstractJavaSamplerClient {
	public void setupTest(JavaSamplerContext arg0) {
	}

	public void teardownTest(JavaSamplerContext arg0) {
	
		
	}

//	@Override
//	public Arguments getDefaultParameters() {
//		Arguments args = new Arguments();
//		args.addArgument("appid", "93667");///s${__RandomString(8,zxcvbnmkjhgdfawt56io7890,)}@sohu.com
//	
//		return args; 
//	
//	
//	}

	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {
		//String appid=arg0.getParameter("appid");
		SampleResult sr = new SampleResult();
		sr.sampleStart();
		HttpClient httpClient = new DefaultHttpClient();
		   // 目标地址   
	//	 HttpClient httpClient=new DefaultHttpClient();
			//	 httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT,10000);
			//	 httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT,10000);
			     HttpGet httpget = new HttpGet("http://10.10.32.166:80"); 
			     int a = (int) (Math.random()*100);
			     System.out.println(a);
			     int b = (int)(Math.random()*100);
			     System.out.println(b);
			     String ip = new String("10.10"+"."+String.valueOf(a)+"."+String.valueOf(b));
			     System.out.println(ip);
			     httpget.setHeader("X-Real-IP",ip); 
			     System.out.println("请求: " + httpget.getRequestLine());
			     System.out.println("----------------------------------------");  
			   
		        try {
		            // 发送请求，返回响应
		            HttpResponse response = httpClient.execute(httpget);
		 
		            // 打印响应信息
		            System.out.println(response.getStatusLine());
		       //     HttpResponse response1 = httpclient.executeMethod(response1);
		            String cont = EntityUtils.toString(response.getEntity());
		            System.out.println(cont);
		        } catch (ClientProtocolException e) {
		            // 协议错误
		            e.printStackTrace();
		        } catch (IOException e) {
		            // 网络异常
		            e.printStackTrace();
		        }
				
		sr.sampleEnd();
		return sr;
	}


	

}
