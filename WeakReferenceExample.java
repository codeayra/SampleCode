/**
 * 
 */
package com.ankit.gcexamples;

import java.lang.ref.WeakReference;

/**
 * @author ankitkumar
 *
 */
public class WeakReferenceExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Person p=new Person();
		System.out.println("Original Person.."+p);
		WeakReference<Person> wr=new WeakReference<Person>(p);
		
		Person p1=wr.get();
		System.out.println("Original Person thru WeakReference..."+p1);
		
		p=null;
		p1=null;
		
		//even if original strong ref is set to null u can get it
		//thru WeakRefernce until its garbage collected.
		Person p2=wr.get();
		System.out.println("Original Person thru WeakReference even original is set to null..."+p2);
		
		//it can invoke gc based on JVM implementation
		System.gc();
		//it should be null if GC has claimed it as it has no Strong ref.
		Person p3=wr.get();
		System.out.println("Original Person after garbage collected.."+p3);
		
	}

}

class Person{
	
}
