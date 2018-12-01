package sigtuna.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import sigtuna.main.Main;
import sigtuna.util.ItemVerification;

public class ItemUpdateClick implements Listener{

	Main plugin = Main.plugin;
	
	@EventHandler
	public static void click(InventoryClickEvent e) {
		
		if(e.getCurrentItem() != null) {
			
			ItemStack item = e.getCurrentItem();
			
			String uuid = ItemVerification.getItemUUIDFromItem(item);
			
			if(uuid != null) {
			
				e.getWhoClicked().sendMessage(uuid);
				
			}	
			
		}
		
	}
	
}
