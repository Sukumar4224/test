package com.demo.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Java {

	public static void main(String[] args) {

	// Vector
		/*List<String> vc= new Vector<String>();
		vc.add("time");
		vc.add("time1");
		vc.add("time2");
		vc.add("time3");
		vc.add("time4");
		vc.add("time5");
		vc.add("time6");
		vc.add(2, "time00");
		vc.remove(2);
		System.out.println(vc.isEmpty());
		//vc.toArray(null);
		System.out.println(vc.hashCode());
		
		
		//System.out.println(vc.isEmpty());
		
		String[] a= vc.toArray(new String[vc.size()]);
		System.out.println("Array element");
		for(int i=0; i<=a.length-1;i++){
			System.out.println(a[i]);
		}
		
		vc.remove("time5"!="Zero");
		
		Iterator<String> ss=vc.iterator();
		while(ss.hasNext()){
			String s = ss.next();
		
			System.out.println(s);
		}
	//ARRAY LIST 
/*		List<Integer> s= new ArrayList<Integer>();
		s.add(123);
		s.add(124);
		s.add(125);
		List<Integer> ls= new ArrayList<Integer>();
		ls.add(600);
		ls.add(13);
		ls.add(12);
		ls.add(14);
		ls.add(15);
		ls.add(16);
		ls.add(19);
		ls.set(1, 133);
		//ls.remove(3);
		ls.add(5, 100);
		ls.addAll(0, s);
		Iterator<Integer> it=ls.iterator();
		while(it.hasNext()){
			Integer i = it.next();
			
			System.out.println(i);
	}
//		System.out.println(ls.isEmpty());
//		System.out.println(ls.size());
//		ls.clear();
//		System.out.println(ls.size());
		System.out.println(ls.indexOf(13));*/
		
	
		 LinkedList<String> al=new LinkedList<String>();
		 al.add("Sukumar0");
		 al.add("Sukumar1");
		 al.add("Sukumar2");
		 al.add("Sukumar3");
		 al.add("Sukumar4");
		 al.add("Sukumar5");
		 al.add("Sukumar6");
		 al.add("Sukumar7");
		 al.add("Sukumar8");
		 System.out.println(al.size());
		 al.add(8, "Bubun");
		 al.add(8, "newly added");
		 Iterator<String> itr= al.iterator();
		 while(itr.hasNext()){
			 
			 System.out.println(itr.next());
		 }
		 System.out.println("#####################");
		 System.out.println(al.get(7));
		 System.out.println(al.contains("Sukumar17"));
		 //System.out.println(al.descendingIterator());
		 System.out.println(al.lastIndexOf("Sukumar8"));
		 System.out.println(al.size());
	}
}
