package com.test.probuf;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sohu.passport.protocol.protobuf.Passport.Result;

public class Test {
	public static String errorInfo;
	public static void runAll() {
		int i = 0;
		java.lang.reflect.Method[] methods = probuf.class.getDeclaredMethods();
		// for (int i=0;i<methods.length;i++) {
		System.out.println(methods.length);
		List<String> pramsList = new ArrayList<String>();
		pramsList.add("ppauthtest78@sohu.com");
		pramsList.add("ppauthtest哈哈@focus.cn");
		Iterator<String> iter = pramsList.iterator();
	
		for (Method m : methods) {
			i++;
			System.out.println("执行次数" + i);
			Result result = null;
			try {
				System.out.println("方法：" + m.getName());
			
			
				if(m.getName().indexOf("mobile")==-1){	
				result = (Result) m.invoke(0, "ppauthtest87@sohu.com");
				}else{
					result = (Result) m.invoke(0, Tools.getPhoneNum());	
				}
				// System.out.println(m.getName());
				if (!result.getCode().equals("200")) {
					errorInfo=m.getName() + "|" + result.getCode() + "|"
							+ result.getMessage() + "|" + result.getData()+probuf.sendInfo+System.lineSeparator()+errorInfo;
					
				}
			} catch (Exception e) {
				e.printStackTrace();
				errorInfo=e.toString()+probuf.sendInfo+System.lineSeparator()+errorInfo;
			}

		}

	}

	public static void main(String[] args) throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InterruptedException {
		// TODO Auto-generated method stub
		new probuf().initClient();
		Test.runAll();
		new probuf().client.close();
		System.out.println("==================================");
		System.out.println(errorInfo);
	}

}
