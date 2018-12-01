package sigtuna.main;

import sigtuna.event.DeveloperGodMode;
import sigtuna.event.ItemUpdateClick;

public class EventLoader {

	Main plugin = Main.plugin;
	
	public EventLoader() {
		
		plugin.getServer().getPluginManager().registerEvents(new DeveloperGodMode(), plugin);
		plugin.getServer().getPluginManager().registerEvents(new ItemUpdateClick(), plugin);
		
	}
	
}
