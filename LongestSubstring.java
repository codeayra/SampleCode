package sample.ankit.amazon;

import java.util.Arrays;
import java.util.List;

public class LongestSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//List<String> words=Arrays.asList("bc","ab","bde","ae","ae");
		//String input="abcde";
		List<String> words=Arrays.asList("be");
		String input="abcde";
		System.out.println(findLongestSequence(words, input));
	}
	
	
	public static String findLongestSequence(List<String> words,String input){
		int sizeOfSequence=0;
		String finalWord="";
		for(String aWord:words){
			if(isASubSequence(aWord, input)){
				int sizeOfCurrentWord=aWord.length();
				if(sizeOfCurrentWord>sizeOfSequence){
						sizeOfSequence=sizeOfCurrentWord;
						finalWord=aWord;
				}
			}
		}
		return finalWord;
	}
	
	//least optimal = > for each word we have to scan the input word again
	private static boolean isSubSequence(String word,String input){
		int indexOfLast=-1;
		char[] allChars=word.toCharArray();
		for(char aChar:allChars){
			int index=input.indexOf(aChar);
			if(index==-1){
				return false;
			}else{
				if(index<=indexOfLast){
					return false;
				}
				indexOfLast=index;
			}
		}
		return true;
	}
	
	private static boolean isASubSequence(String word,String input){
		char[] inputChars=input.toCharArray();
		char[] wordChars=word.toCharArray();
		boolean result=true;
		boolean found=false;
		int i=0,j=0;
		while(i < wordChars.length){
			found=false;
			while(j< inputChars.length){
				if(wordChars[i] == inputChars[j]){
					i++;
					j++;
					found=true;
					break;
				}else{
					j++;
				}
			}
			if(found==false){
				result=false;
				break;
			}
		}		
			
	return result;
	}
	
	private static boolean isASubSequenceSingleLoop(String word,String input){
		char[] inputChars=input.toCharArray();
		char[] wordChars=word.toCharArray();
		for(int i=0,j=0;i<wordChars.length && j<inputChars.length; ){
			if(wordChars[i] == inputChars[j]){
				if(i==wordChars.length-1){
					return true;
				}
				i++;
				j++;
				
			}else{
				j++;
				
			}
		}
			
	return false;
	}


}
