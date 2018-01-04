package sample.ankit.amazon;

import java.util.Map;

public class MyLinkedHashMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> testMap=new java.util.LinkedHashMap<String,String>(10,.75f,true);
		testMap.put("Ankit", "Kumar");
		testMap.put("Rahul", "Kamboj");
		testMap.put("Anusha", "Varshney");
		testMap.put("Payal ", "Kamboj");
		
		System.out.println(testMap);
		testMap.get("Anusha");
		System.out.println(testMap);
		}
	

}
