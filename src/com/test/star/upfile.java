package com.test.star;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class upfile {
	public static String doPostFile(String url,String filePath) throws Exception{
		 HttpClient httpclient = new DefaultHttpClient();  
	        //请求处理页面  
	        HttpPost httppost = new HttpPost(  
	                url);  
	        //创建待处理的文件  
	       // FileBody file = new FileBody(new File("d:/22.rar"));  
	      // httppost.addHeader("Content-Type","application/octet-stream; charset=" + DEFAULT_CHARSET);
	
	  
	        FileBody file = new FileBody(new File(filePath));  
	        //创建待处理的表单域内容文本  
	       StringBody hook = new StringBody("ew0KICAgICJ1cmwiOiAiPGh0dHA6Ly8xMC4xMS41LjY0OjgwNzAvPiIsDQogICAgIm1ldGhvZCI6ICJQT1NUIiwNCiAgICAiaGVhZGVycyI6IHsNCiAgICAgICAgIlgtU0VDVVJJVFktUEFTU1dPUkQiOiAiMTM3NjMxNjQwMCwgNUJDQzI1MDYxRDg5MDMxQTNDQ0QyQ0RDQTk1NTE3OTMsIEZGNUU0NUY0NUEwNzY0NEM4Qjg0M0JBQjAzRUJFRTM4Ig0KICAgIH0sDQogICAgImRhdGEiOiB7DQogICAgICAgICJuYW1lIjogInRlc3QuZG9jeCIsDQogICAgICAgICJhdXRoIjogImxlb24iLA0KICAgICAgICAiY29udGVudCI6IG51bGwNCiAgICB9DQp9DQo=");  	  
	        //对请求的表单域进行填充  
	        MultipartEntity reqEntity = new MultipartEntity();  
	        reqEntity.addPart("upload", file);  
	       reqEntity.addPart("hook", hook);  
	        //设置请求  
	        httppost.setEntity(reqEntity); 
	    
	        //执行  
	        HttpResponse response = httpclient.execute(httppost);  
	        //HttpEntity resEntity = response.getEntity();  
	        //System.out.println(response.getStatusLine());  
	       // if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){  
			String status =response.getStatusLine().toString();
			System.out.println(status);
	            HttpEntity entity = response.getEntity();  
	            //显示内容  
	            String result=null;
	            if (entity != null) {  
	            	result=EntityUtils.toString(entity);
	            	System.out.println(result);
	            	return result; 
	            } else{
	            	return "entity is null";
	            }
	        
	     //   }  
	        
	}

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		try {
			new upfile().doPostFile("http://10.11.5.64:8070/", "D:/test.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
