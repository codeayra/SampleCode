package com.triplepoint.code.components;

public abstract class Command {

	protected String name;
	protected String description;
	
	public Command(String name,String description) {
		this.name=name;
		this.description=description;
	}
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	protected abstract void execute(String... input);


	@Override
	public String toString() {
		return name + " , description: " + description ;
	}
	
	

}
