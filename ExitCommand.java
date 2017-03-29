/**
 * 
 */
package com.triplepoint.code.components;

/**
 * @author ankitkumar
 *
 */
public class ExitCommand extends Command {
	
	public ExitCommand(String name,String description) {
		super(name,description);
	}

	/* (non-Javadoc)
	 * @see com.triplepoint.code.components.Command#execute(java.lang.String[])
	 */
	@Override
	protected void execute(String... input) {
		
		System.exit(0);

	}

}
