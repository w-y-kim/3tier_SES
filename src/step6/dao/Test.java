package step6.dao;

import java.util.ArrayList;

public class Test {
	public static void main(String[] args) {
		ArrayList<Object> arr = null;
		System.out.println(arr);
//		System.out.println(arr.hashCode());

		System.out.println("=========");

		arr = new ArrayList<>(); 
		System.out.println(arr);
		System.out.println(arr.hashCode());
		
		System.out.println("=========");
		
		arr = new ArrayList<>();
		arr.add(null); 
		System.out.println(arr);
		System.out.println(arr.hashCode());

		System.out.println("=========");

		arr = new ArrayList<>();
		arr.add(args); 
		System.out.println(arr);
		System.out.println(arr.hashCode());

	}
}
