package org.gradle;
import java.util.*;

public class Solution {
	
	private static Stack<Character> stack=new Stack<>();
	private static String SAMPLE="{[()]}";

    public static void main(String[] args) {
    	
    	List<String> inputs=new ArrayList<>();
        Scanner in = new Scanner(System.in);
        try{
        int t = in.nextInt();
        if(!(1<=t && t<=1000)){
        	throw new IllegalArgumentException("Invalid no of strings");
        }
        for(int a0 = 0; a0 < t; a0++){
            String input = in.next();
            if(!(1<=input.length() && input.length()<=1000)){
            	throw new IllegalArgumentException("Invalid no of strings");
            }
            inputs.add(input);
            
        }
        for(String input:inputs){
        	checkBalanced(input);
        }
        
        
    	}finally{
    		in.close();
    	}
    }
    
    private static void checkBalanced(String input){
    	stack.clear();
    	char[] charArray=input.toCharArray();
    	for(int index=0;index<charArray.length;index++){
    		char text=charArray[index];
    		if(!SAMPLE.contains(Character.toString(text))){
    			throw new IllegalArgumentException("Input can only contain brackets");
    		}
    		if(text=='{' || text=='[' || text=='('){
    			stack.push(text);
    		}else{
    			if(stack.empty()){
    				System.out.println("NO");
    				return;
    			}
    			char top=stack.pop();
    			if((top == '[' && text!=']') || (top == '{' && text!='}') || (top == '(' && text!=')')){
    				System.out.println("NO");
    				return;
    			}
    		}
    	}
    	if(stack.empty()){
    		System.out.println("YES");
    	}else{
    		System.out.println("NO");
    	}
    }
    
    
}
