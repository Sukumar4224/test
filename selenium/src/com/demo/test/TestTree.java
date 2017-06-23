package com.demo.test;

import java.util.Map;
import java.util.TreeMap;

public class TestTree {

	public static void main(String[] args) {
		
		TreeMap<Integer, String> tr = new TreeMap<Integer, String>();
		tr.put(1, "q");
		tr.put(2, "w");
		tr.put(3, "a");
		for(Map.Entry<Integer, String> m:tr.entrySet()){
			System.out.println(m.getKey()+""+m.getValue());
		}

	}

}
