/**
 * 
 */
package com.triplepoint.code.components;

import java.util.List;

/**
 * @author ankitkumar
 *
 */
public class HelpCommand extends Command {
	
	protected List<Command> allCommands;
	
	//not sure passing list of commands is best way here.
	public HelpCommand(String name,String description,List<Command> allComamnds) {
		super(name,description);
		this.allCommands=allComamnds;
	}

	/* (non-Javadoc)
	 * @see com.triplepoint.code.components.Command#execute(java.lang.String[])
	 */
	@Override
	protected void execute(String... input) {
		allCommands.forEach(System.out::println);
	}

}
