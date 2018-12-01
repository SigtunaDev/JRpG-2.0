package sigtuna.main;

import sigtuna.command.Jitem;

public class CommandLoader {

	Main plugin = Main.plugin;
	
	public CommandLoader() {
		
		plugin.getCommand("jitem").setExecutor(new Jitem());
		
	}
	
}
