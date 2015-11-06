package com.lux.you.test;

public class Recursion {

	public static void main(String[] args) {
		System.out.println(computeAge(8));
	}

	public static int computeAge(int n) {
		if (n == 1)
			return 10;
		return computeAge(n - 1) + 2;
	}
}
