package com.test.probuf;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Param_probuf {

public static void params(){
	List<String> pramsList = new ArrayList<String>();
	pramsList.add("ppauthtest78@sohu.com");
	pramsList.add("ppauthtest哈哈@focus.cn");
	Iterator<String> iter = pramsList.iterator();
	while (iter.hasNext()) {
		System.out.println(iter.next());
	}

}
public static void activate(){
	
}
	public static void main(String[] args) {
Param_probuf.params();
	}

}
