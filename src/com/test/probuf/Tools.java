package com.test.probuf;

import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.danga.MemCached.MemCachedClient;
import com.danga.MemCached.SockIOPool;
import com.sohu.passport.protocol.protobuf.Passport.Result;



public class Tools {
	 public static void buff2Image(byte[] b,String tagSrc)throws Exception{
	        FileOutputStream fout = new FileOutputStream(tagSrc);
	        //将字节写入文件
	        fout.write(b);
	        fout.close();
	    }
	 public static Method getMethod(String methodName){
		 Method method=null;
		 java.lang.reflect.Method[] methods =  probuf.class.getDeclaredMethods();
		 //for (int i=0;i<methods.length;i++) {
          for(Method m:methods)
          {
			if(m.getName().equals(methodName)){
				
			method=m;
			//System.out.println(m.getName());	

			
		 }}
      	return method;
		 
	 }
	
	 public static  Result  JustRun(String methodName,String userid) throws Exception{
		 Result result = null;
		// Class<?> clazz = Class.forName("com.test.probuf.probuf");
		
			 java.lang.reflect.Method[] methods =  probuf.class.getDeclaredMethods();
			 //for (int i=0;i<methods.length;i++) {
	          for(Method m:methods)
	          {
//				if(!m.getName().equals("main")){
//					
//					m.invoke(0, null);
//System.out.println(m.getName());	
//				}
	        	  if(m.getName().equals(methodName)){
	        		  m.setAccessible(true);
	        		  result=	(Result) m.invoke(0, userid);
					
	        	  }
//				
			 }
			return result;
		  
	   }
	 public static String getPhoneNum() {// 生成手机号

			return "19"+ String.valueOf((int) (Math.random() * 900000000) + 100000000);
		}
	 public static  void testDanga() {  
	        /*初始化SockIOPool，管理memcached的连接池*/  
	     System.out.println("tt");  
	        String[] servers = { "10.5.107.31:11211"};  
	        SockIOPool pool = SockIOPool.getInstance();  
	       
	        pool.setServers(servers);  
	        pool.setFailover(true);  
	        pool.setInitConn(10);  
	        pool.setMinConn(5);  
	        pool.setMaxConn(250);  
	        //设置主线程睡眠时间，每30秒苏醒一次，维持连接池大小  
	        pool.setMaintSleep(30);  
	        //关闭套接字缓存  
	        pool.setNagle(false);  
	        //连接建立后的超时时间  
	        pool.setSocketTO(3000);  
	        pool.setAliveCheck(true);  
	        pool.initialize();  
	        System.out.println("tt"); 
	        /*建立MemcachedClient实例*/  
	        MemCachedClient memCachedClient = new MemCachedClient();  
	      
	        memCachedClient.add("yy", "gfhfgh");
	        memCachedClient.set("yy", "haha");  
	        String result =  (String) memCachedClient.get("yy");  
	        System.out.println(result);  
	    }  
	      
	   
		public static void main(String[] args) throws Exception {
			 // System.out.print("test"+probuf_test.authuser("ppauthest54@sohu.com").getCode());
				//new probuf().initClient();
			testDanga();
				/*for(int i=1;i<=1001;i++){
					updateuser("probuftest"+i+"@sohu.com","probuf"+i);
				}*/
				//activate(client,"ppauthtest3@sohu.com");
//				Result result=Tools.JustRun("get_uniqname","ppauthtest3@sohu.com");
//				  System.out.format("%s: %s%n", result.getCode(), result.getMessage());
//		          System.out.println(result.getData());
				//wapbindmobile(client, "ppauthtest39@sohu.com", "19810606513");
				//getWebloginCaptcha();
				//updateuser("ppauthtest28@sohu.com");
			
				
			}
}
