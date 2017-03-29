/**
 * 
 */
package org.gradle;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author ankitkumar
 *
 */
public class ArraysDS {
	
	private final static String EMPTY_STRING=" ";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArraysDS.Arguments input=new ArraysDS.Arguments();
		ArraysDS mySolution=new ArraysDS();
		
		mySolution.readInput(input);
		
		
		mySolution.doReverse(input);
		
	}
	
	private void  doReverse(Arguments input){
		for(int index=input.length-1;index>=0;index--){
			System.out.print(input.inputArray[index]+EMPTY_STRING);
		}
		
	}

	private void readInput(Arguments input) {
		
		Scanner inputScanner=new Scanner(System.in);
		try{
		    input.length=Integer.parseInt(inputScanner.nextLine());
		    if(!(1<=input.length && input.length<=1000)){
				throw new IllegalArgumentException("Invalid length");
			}
		    String[] inputArrayString=inputScanner.nextLine().split(EMPTY_STRING);
		    if(input.length!=inputArrayString.length){
				throw new IllegalArgumentException("Invalid input array");
			}
		    input.inputArray=new int[input.length];
		    for(int index=0;index<input.length;index++){
		    	int element=Integer.parseInt(inputArrayString[index]);
		    	if(!(1<=element && element<=10000)){
		    		throw new IllegalArgumentException("Invalid input array element");
		    	}else{
		    		input.inputArray[index]=element;
		    	}
		    	
		    }
		    
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid input array");
		}finally{
			inputScanner.close();
		}
	}
	
	public static class Arguments {
		
			private int length;
			private int[] inputArray;

	  }

}
