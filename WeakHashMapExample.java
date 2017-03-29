package com.ankit.gcexamples;

import java.util.Date;
import java.util.WeakHashMap;

public class WeakHashMapExample {

	public static void main(String[] args) {
		WeakHashMap<Person1, PersonMetaData> weakHashMap=new WeakHashMap<>();
		//strong ref
		Person1 ankit=new Person1();
		//this key is weak reference
		weakHashMap.put(ankit, new PersonMetaData());
		
		PersonMetaData p=weakHashMap.get(ankit);
		System.out.println(p);
		
		//nulling strong ref
		ankit=null;
		//running gc so that person object is garbage collected
		//and weak reference in weak hashmap should be removed as obj is gcd
		System.gc();
		
		//if gc has collected this obj map key and value also be removed.
		if(weakHashMap.containsValue(p)){
			System.out.println("still contains key");
		}else{
			System.out.println("key gone");
		}

	}

}

final class Person1{
	
}

class PersonMetaData{
	Date date;
	
	public PersonMetaData() {
		date=new Date();
	}

	@Override
	public String toString() {
		return "PersonMetaData [date=" + date + "]";
	}
	
	
}
