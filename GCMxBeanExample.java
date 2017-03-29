/**
 * 
 */
package com.ankit.gcexamples;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 *Mx Beans to get garbage Collection information
 *This program can be run with diff VM arguments to control GC.
 *-XX:+UseConcMarkSweepGC as vm args
 * @author ankitkumar
 *
 */
public class GCMxBeanExample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		GCMxBeanExample.workingMXBean();
		
	}
	
	public static void workingMXBean(){
		List<GarbageCollectorMXBean> list=ManagementFactory.getGarbageCollectorMXBeans();
		list.forEach((bean) ->{
			System.out.println("Name..." + bean.getName());
			System.out.println("No. of Collections.."+bean.getCollectionCount());
			System.out.println("Collection Time.."+bean.getCollectionTime()+"...ms");
			System.out.println("Pool Names...");
			
			for(String poolName:bean.getMemoryPoolNames()){
				System.out.println("\t" +poolName);
			}
			
			System.out.println();
		});
	}

}
