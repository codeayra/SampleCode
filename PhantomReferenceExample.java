package com.ankit.gcexamples;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.ArrayList;
import java.util.List;

public class PhantomReferenceExample {
	
	public static void main(String[] args) {
		
		ReferenceQueue<MyPerson> refQueue=new ReferenceQueue<>();
		List<MyPerson> people=new ArrayList<>();
		List<FinalizePerson> list=new ArrayList<>();
		
		for(int i=0;i<10;i++){
			MyPerson person=new MyPerson();
			people.add(person);
			list.add(new FinalizePerson(person, refQueue));
		}
		
		people=null;
		//now myperson is gced.
		System.gc();
		
		//once object is garbage collected its phantom reference is added to ReferenceQueue
		for(PhantomReference<MyPerson> reference:list){
			//it will print true only when object is garbage collected
			System.out.println(reference.isEnqueued());
		}
		
		//we can poll and get it and call its cleanup method
		//this code should be run in background thread.
		//now cleanup code is in our control which in case of finalizer was in control of gc.
		Reference<? extends MyPerson> refFromQueue;
		while((refFromQueue= refQueue.poll())!=null){
			((FinalizePerson)refFromQueue).cleanup();
		}
		
	}
}
	
class MyPerson{
	
}

//we can never get original object from phantom reference 
class FinalizePerson extends PhantomReference<MyPerson>{

	public FinalizePerson(MyPerson referent, ReferenceQueue<? super MyPerson> q) {
		super(referent, q);
	}
	
	//this method will be executed after object is gcd and we nedd to execute this method.
	public void cleanup(){
		System.out.println("My person is finazing the resources");
	}
	
}


