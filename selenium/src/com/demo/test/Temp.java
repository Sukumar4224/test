package com.demo.test;

import java.util.HashMap;import java.util.Map;

public class Temp {

	public static void main(String[] args) {
		
		HashMap<Integer,String> hm = new HashMap<Integer, String>();
		hm.put(1, "Sukumar");
		hm.put(2, "Bubun");
		
	
		 for(Map.Entry m:hm.entrySet()){  
			   System.out.println(m.getKey()+" "+m.getValue());  
			  }  
		 System.out.println(hm.size());
		 hm.clear();
		System.out.println(hm.size());
		//System.out.println(hm.hashCode());
		System.out.println(hm.isEmpty());
		System.out.println(hm.getClass());
	
		
		
		
	}

}
