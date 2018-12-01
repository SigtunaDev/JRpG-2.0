package sigtuna.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import sigtuna.main.Main;

public class PlayerLoginInitalize implements Listener {
	
	Main plugin = Main.plugin;
	
	@EventHandler
	public void onLoginInit(PlayerJoinEvent e) {

		if(plugin.pc.get(e.getPlayer().getUniqueId().toString()) != null) return;
		
		String uuid = e.getPlayer().getUniqueId().toString();
		
		plugin.pc.set(uuid + ".name", e.getPlayer().toString());
		plugin.pc.set(uuid + ".value.food", 100);
		plugin.pc.set(uuid + ".value.health", 100);
		plugin.pc.set(uuid + ".level.run", 0);
		plugin.pc.set(uuid + ".level.jump", 0);
		
		plugin.reload();
		
	}

}
