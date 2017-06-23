package com.demo.test;

public class Recursion {

	public static void main(String[] args) {
		System.out.println(factorial(5));
		System.out.println(fabSeries(6));
		
	}

	private static int fabSeries(int i) {
		if (i==0 || i==1)
				return i;
		
		return fabSeries(i-1)+fabSeries(i-2);
		
		
	}



	private static int factorial(int i) {
		
		if (i==0)
				return 1; 
		else 
			return i*factorial(i-1);
	}
	
	

}
