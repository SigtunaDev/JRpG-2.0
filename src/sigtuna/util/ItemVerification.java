package sigtuna.util;

import java.util.List;
import java.util.Set;

import org.bukkit.inventory.ItemStack;

import net.md_5.bungee.api.ChatColor;
import sigtuna.main.Main;

public class ItemVerification {
	
	static Main plugin = Main.plugin;
	
	public static boolean item_exist(String ID) {
		
		Set<String> set = plugin.ic.getKeys(false);
		
		return set.contains(ID);
		
	}
	
	public static boolean isModule(String ID) {
		
		String type = plugin.ic.getString(ID + ".type");
		
		return type.equals("module");
		
	}
	
	public static String getItemUUIDFromItem(ItemStack item) {
		
		if(item.getItemMeta() == null) return null;
		
		List<String> list = item.getItemMeta().getLore();
		
		return ChatColor.stripColor(list.get(list.size() - 1));
		
	}

}
