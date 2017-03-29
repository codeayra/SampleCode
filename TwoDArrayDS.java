package org.gradle;

import java.util.Scanner;

public class TwoDArrayDS {
	
	private final static String EMPTY_STRING=" ";
	private final static int HOUR_GLASS_LIMIT=2;

	
	public static void main(String[] args) {
		int[][] inputArray=new int[6][6];
		TwoDArrayDS mySolution=new TwoDArrayDS();
		mySolution.readInput(inputArray);
		int hourGlassMaxSum=0;
		int initialCount=0;
		
		//i is row number
		for(int row=0;row<6;row++){
			if(row+HOUR_GLASS_LIMIT<=5){
				//j is column number
					for(int column=0;column<6;column++){
						if(column+HOUR_GLASS_LIMIT<=5){
							int hourGlassSum=mySolution.sumHourGlass(row, column, inputArray);
							if(initialCount==0){
								hourGlassMaxSum=hourGlassSum;
								initialCount++;
							}else if ((Integer.valueOf(hourGlassSum).compareTo(Integer.valueOf(hourGlassMaxSum))>0)){
								hourGlassMaxSum=hourGlassSum;
							}
						}
					}
			}
			
		}// row number loop finished
		
		System.out.println(hourGlassMaxSum);
	}
	
	private int sumHourGlass(int row,int column,int[][] inputArray){
		int hourGlassSum=0;
		
		for(int index=0;index<=HOUR_GLASS_LIMIT;index++){
			hourGlassSum=hourGlassSum+inputArray[row][column+index];
		}
	
		hourGlassSum=hourGlassSum+inputArray[row+1][column+1];
		
		for(int index=0;index<=HOUR_GLASS_LIMIT;index++){
			hourGlassSum=hourGlassSum+inputArray[row+HOUR_GLASS_LIMIT][column+index];
		}
		
		return hourGlassSum;
	}
	
	private void readInput(int[][] inputArray){
		Scanner inputScanner=new Scanner(System.in);
		
		try{
		    for(int i=0;i<=5;i++){
		    	String[] inputArrayString=inputScanner.nextLine().split(EMPTY_STRING);
			    if(inputArrayString.length!=6){
			    	throw new IllegalArgumentException("Invalid Input Array Length must be 6");
			    }
			    for(int j=0;j<inputArrayString.length;j++){
			    	int element=Integer.parseInt(inputArrayString[j]);
			    	if(!(-9<=element && element<=9)){
			    		throw new IllegalArgumentException("Invalid input Array Element ! should be between -9 and 9");
			    	}else{
			    		inputArray[i][j]=element;
			    	}
			    }
		    }
		    
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid input array");
		}finally{
			inputScanner.close();
		}
	}

}
