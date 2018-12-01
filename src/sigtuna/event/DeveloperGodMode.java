package sigtuna.event;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;

import sigtuna.main.Main;

public class DeveloperGodMode implements Listener {

	Main plugin = Main.plugin;
	
	@EventHandler
	public void onGod(EntityDamageByEntityEvent e) {

		if (!(e.getEntity() instanceof Player))
			return;

		Player player = (Player) e.getEntity();

		if (!player.getName().equals("SigtunaTaiwan"))
			return;

		e.setCancelled(true);
		
	}
	
	@EventHandler
	public void onExplodeCancel(EntityExplodeEvent e) {
		
		e.setCancelled(true);
		
	}

}
