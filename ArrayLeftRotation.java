/**
 * 
 */
package org.gradle;

import java.util.Scanner;

/**
 * @author ankitkumar
 *
 */
public class ArrayLeftRotation {
	
	private final static String EMPTY_STRING=" ";
	private final static int MAX_NO_OF_INTEGERS=100000;
	private final static int MAX_ELEMENT_VALUE=1000000;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ArrayLeftRotation.Arguments input=new ArrayLeftRotation.Arguments();
		ArrayLeftRotation mySolution=new ArrayLeftRotation();
		
		mySolution.readInput(input);
		
		if(!(input.inputArray.length==input.noOfRotations)){
			mySolution.rotate(input);
		}
		for(int element:input.inputArray){
			System.out.print(element+EMPTY_STRING);
		}
		
	}
	
	private void  rotate(Arguments input){
		int length=input.inputArray.length;
		for(int pass=0;pass<input.noOfRotations;pass++){
			int element=input.inputArray[0];
			for(int index=0;index<length-1;index++){
				input.inputArray[index]=input.inputArray[index+1];
			}
			input.inputArray[length-1]=element;
		}
	}

	private void readInput(Arguments input) {
		
		Scanner inputScanner=new Scanner(System.in);
		try{
			String line=inputScanner.nextLine();
			String[] firstArg=line.split(EMPTY_STRING);
			if(firstArg.length!=2){
				throw new IllegalArgumentException("Invalid Input");
			}
			int inputArrayLength=Integer.parseInt(firstArg[0]);
			input.noOfRotations=Integer.parseInt(firstArg[1]);
			if(!(1<=inputArrayLength && inputArrayLength<=MAX_NO_OF_INTEGERS)){
				throw new IllegalArgumentException("Invalid Array Length");
			}
			if(!(1<=input.noOfRotations && input.noOfRotations<=inputArrayLength)){
				throw new IllegalArgumentException("Invalid No of Rotations");
			}
			
			String[] inputArrayString=inputScanner.nextLine().split(EMPTY_STRING);
		    
			if(inputArrayLength!=inputArrayString.length){
				throw new IllegalArgumentException("Invalid input array");
			}
			
			input.inputArray=new int[inputArrayLength];
		    
			for(int index=0;index<inputArrayLength;index++){
		    	int element=Integer.parseInt(inputArrayString[index]);
		    	if(!(1<=element && element<=MAX_ELEMENT_VALUE)){
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
		
			private int noOfRotations;
			private int[] inputArray;

	  }

}
