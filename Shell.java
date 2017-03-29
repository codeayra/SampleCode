package com.triplepoint.code.components;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//should it be Singleton?
public class Shell {
	
	private List<Command> listOfCommands;
	
	public Shell() {
		initialize();
	}
	
	private void initialize(){
		listOfCommands=new ArrayList<>();
		listOfCommands.add(new DateCommand("date","Prints current date"));
		listOfCommands.add(new ExitCommand("exit", "Exit from the shell"));
		listOfCommands.add(new HelpCommand("help", "shows all existing commands",listOfCommands));
		listOfCommands.add(new PrintCommand("print","prints given input string on shell"));
	}
	
	public void addCommand(Command newCommand){
		listOfCommands.add(newCommand);
	}
	
	public void executeCommand(String name,String...input){
		listOfCommands.forEach((command)->{
			if(command.getName().equals(name)){
				command.execute(input);
			}else{
				System.out.println("Invalid command ! please see help");
			}
		});
	}
	
	public static void main(String[] args) {
		Shell myShell=new Shell();
		String nextLine="";
		while(nextLine!=null){
			System.out.println("myshell@ankit > ");
			try(Scanner input=new Scanner(System.in);){
			nextLine=input.nextLine();
			//not sure how space will represent.
			String[] command=nextLine.split("-");
			//no support for multiple parameters as of now.only single parameter.
			myShell.executeCommand(command[0],command[1]);
			}
		}
	}

}
