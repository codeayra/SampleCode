package com.triplepoint.code.components;

import java.io.OutputStream;
import java.io.PrintWriter;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("Before");
		MyWriter out=new MyWriter(System.out);
		out.println("Using printWriter");
		out.close();
		System.out.println("After");
	}
	
	public static class MyWriter extends PrintWriter{

		public MyWriter(OutputStream out) {
			super(out);
		}
		
		@Override
		public void close(){
			
		}

		
		
	}

}
