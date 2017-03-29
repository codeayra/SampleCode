/**
 * 
 */
package com.ankit.gcexamples;

import java.util.Random;

/**
 * After running this program do following steps
 * 1) run jps command to find out the process id of this program <pid>
 * 2) jstat -gcutil <pid>  => shoes space in %
 * 3) jstat -gccause <pid> => cause of garbage collection
 * 4) jstat -gccapacity <pid> => amount of memory allocated min max and current
 * 5) jstat -gc <pid> =>  capacity and utilization of memory
 * 6) jstat -gc <pid> <ms> <no of rows> => shows total rows after each ms provided.
 * 
 * This program uses default gc which allocates memory fast as uses pointer
 * use VM argument -XX:+UseG1GC to use G1 gc which allocate memory slowly as 1/3.
 * 
 * in place of jstat use "jvisualvm" tool and its visualgc plugin which is default supplied by jdk.
 * @author ankitkumar
 *
 */
public class AllocationOverwrite {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		
		int arraySize=1000000;
		GCMe gcmes[]=new GCMe[arraySize];
		
		int count=0;
		Random random=new Random();
		while(true){
			
			gcmes[random.nextInt(arraySize)]=new GCMe();
			if(count%arraySize==0){
			//2 million allocation a second
			//if(count%2000000==0){
				System.out.print(".");
				//GCMxBeanExample.workingMXBean();
			}
			count++;
			
			//added to see easily what happening in "visualgc"
			//to see visualgc run "visualvm" command on cmd as its comes with jdk.
			Thread.sleep(1);
		}

	}
	
}

class GCMe{
	long a;
	long aa;
	long aaa;
	long aaaa;
	long aaaaa;
	long aaaaaa;
	long aaaaaaa;
	long aaaaaaaa;
	long aaaaaaaaa;
	long aaaaaaaaaa;
	long aaaaaaaaaaa;
	long aaaaaaaaaaaa;
	long aaaaaaaaaaaaa;
	long aaaaaaaaaaaaaa;
}
