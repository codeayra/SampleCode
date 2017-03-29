package com.triplepoint.code.components;

import java.util.Date;

public class DateCommand extends Command {
	
	public DateCommand(String name,String description){
		super(name,description);
	}

	@Override
	protected void execute(String... input) {
		System.out.println(new Date());
	}

}
