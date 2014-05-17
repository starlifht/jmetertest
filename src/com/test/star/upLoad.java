package com.test.star;




import java.io.File;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


public class upLoad extends AbstractJavaSamplerClient {

 
	String url;
	String filePath;

	//String methodName;
	public void setupTest(JavaSamplerContext arg0) {
	
			url =arg0.getParameter("url");
			
	
	}

	public void teardownTest(JavaSamplerContext arg0) {
	
		
	}

	@Override
	public Arguments getDefaultParameters() {
		Arguments args = new Arguments();
		//args.addArgument("file", "probuftest${__Random(1,1000,)}@sohu.com");///s${__RandomString(8,zxcvbnmkjhgdfawt56io7890,)}@sohu.com
		args.addArgument("url", "http://10.11.5.64:8070/");//1${__Random(11111,99999,)}${__Random(11111,99999,)}
		args.addArgument("file", "${__counter(true,)}");//probuftest${__Random(1,1000,)}@sohu.com

		return args; 
	
	
	}
	
	@Override
	public SampleResult runTest(JavaSamplerContext arg0) {

		SampleResult sr = new SampleResult();
		//String UUID=String.valueOf(System.currentTimeMillis());
		sr.sampleStart();
		filePath=arg0.getParameter("file");
		sr.setSuccessful(false);
		try {
		 HttpClient httpclient = new DefaultHttpClient();  

	        HttpPost httppost = new HttpPost(  
	                url);  	
		  
	        FileBody file = new FileBody(new File(filePath));  
 
	       StringBody hook = new StringBody("ew0KICAgICJ1cmwiOiAiPGh0dHA6Ly8xMC4xMS41LjY0OjgwNzAvPiIsDQogICAgIm1ldGhvZCI6ICJQT1NUIiwNCiAgICAiaGVhZGVycyI6IHsNCiAgICAgICAgIlgtU0VDVVJJVFktUEFTU1dPUkQiOiAiMTM3NjMxNjQwMCwgNUJDQzI1MDYxRDg5MDMxQTNDQ0QyQ0RDQTk1NTE3OTMsIEZGNUU0NUY0NUEwNzY0NEM4Qjg0M0JBQjAzRUJFRTM4Ig0KICAgIH0sDQogICAgImRhdGEiOiB7DQogICAgICAgICJuYW1lIjogInRlc3QuZG9jeCIsDQogICAgICAgICJhdXRoIjogImxlb24iLA0KICAgICAgICAiY29udGVudCI6IG51bGwNCiAgICB9DQp9DQo=");	 	  
	        MultipartEntity reqEntity = new MultipartEntity();  
	        reqEntity.addPart("upload", file);  
	        reqEntity.addPart("hook", hook); 
	        httppost.setEntity(reqEntity); 
	        HttpResponse response = httpclient.execute(httppost);  	 
			StatusLine status =response.getStatusLine();		
	            HttpEntity entity = response.getEntity();  
	     
	            String respomse =EntityUtils.toString(entity);;
	            if (status.getStatusCode()==200&&respomse.contains("done")) {  
	          
	            	 System.out.println("[success]"+status.toString()+" "+respomse+" "+filePath);
	            	sr.setSuccessful(true);
	            } else{
	            System.out.println("[uperror]"+status.toString()+respomse);
	            }
		} catch (Exception e) {
			  System.out.println("[uperror]"+e.toString());
		
		} 
	     //   }  
		sr.sampleEnd();
		return sr;
	}


}
