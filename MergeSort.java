Ipackage org.gradle;

import java.util.Arrays;
import java.util.Scanner;

public class MergeSort {

	public static void main(String[] args) {
		int[] inputArray=null;
		Scanner sc=new Scanner(System.in);
		try{
			String[] input=sc.nextLine().split(" ");
			inputArray=new int[input.length];
			for(int i=0;i<input.length;i++){
				inputArray[i]=Integer.parseInt(input[i]);
			}
		}catch(NumberFormatException e){
			throw new IllegalArgumentException("Number should be integer");
		}finally{
			sc.close();
		}
		int[] sortedArray=mergerSort(inputArray);
		for(int element:sortedArray){
			System.out.print(element+" ");
		}
		
	}
	
	
	private static int[] mergerSort(int[] inputArray){
		if(inputArray.length==1){
			return inputArray;
		}else{
			int length=inputArray.length;
			int midIndex=length/2;
			int[] array1=Arrays.copyOfRange(inputArray, 0, midIndex);
			int[] array2=Arrays.copyOfRange(inputArray, midIndex, length);
			array1=mergerSort(array1);
			array2=mergerSort(array2);
			return merge(array1,array2);
		}
		
	}
	
	private static int[] merge(int[] array1,int[] array2){		
		int returnArrayLength=array1.length+array2.length;
		int[] returnArray=new int[returnArrayLength];
		int i=0 ,j=0, k=0;
		while(i<array1.length && j<array2.length){
			if(array1[i]<=array2[j]){
				returnArray[k]=array1[i];
				i++;
			}else if(array2[j]<array1[i]){
				returnArray[k]=array2[j];
				j++;
			}
			k++;
		}
		if(i<array1.length){
			for(int index=i;index<array1.length;index++){
				returnArray[k]=array1[index];
				k++;
			}
			
		}
		if(j<array2.length){
			for(int index=j;index<array2.length;index++){
				returnArray[k]=array2[index];
				k++;
			}
			
		}
		return returnArray;
		
	}

}
