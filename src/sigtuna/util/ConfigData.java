package sigtuna.util;

import sigtuna.main.Main;

public class ConfigData {
	
	static Main plugin = Main.plugin;
	
	public static void init() {
		
		plugin.cc.set("version", "0.1");
		plugin.cc.set("lang", "zh_TW");
			
		plugin.reload();
		
	}

}
