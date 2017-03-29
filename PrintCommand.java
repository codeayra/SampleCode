package com.triplepoint.code.components;

public class PrintCommand extends Command {
	
	public PrintCommand(String name,String description) {
		super(name,description);
	}

	@Override
	protected void execute(String... input) {
		
		if (input.length==1){
			System.out.println(input[0]);
		}else{
			System.out.println("Wrong inputs ..please see help");
		}

	}

}
