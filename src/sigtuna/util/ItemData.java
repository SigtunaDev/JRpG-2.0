package sigtuna.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import sigtuna.main.Main;

public class ItemData {

	String ID;

	static Main plugin = Main.plugin;

	public ItemData(String ID) {

		this.ID = ID;

	}

	public String getName() {

		String name = plugin.ic.getString(String.format("%s.name", ID));

		return name;

	}

	public Material getMaterial() {

		String material_id = plugin.ic.getString(String.format("%s.material", ID));

		Material material = Material.getMaterial(material_id);

		return material;

	}

	public List<String> getLore() {

		List<String> list = plugin.ic.getStringList(String.format("%s.lore", ID));

		return list;

	}

	public String getInfoClass() {

		return plugin.ic.getString(String.format("%s.info.class", ID));

	}

	public Set<String> getInfoSkillName(String ID) {

		if (!(plugin.ic.getString(String.format("%s.info.class", ID)) != null)) {

			Set<String> set = plugin.ic.getConfigurationSection(String.format("%s.info.skill", ID)).getKeys(false);

			return set;

		}else {
			
			Set<String> set = new HashSet<String>();
			
			return set;
			
		}

	}

	public ItemStack getItem() {

		Material material = getMaterial();
		String name = getName();
		List<String> prelore = getLore();
		List<String> lore = ItemMaker.loreMaker(prelore, ID);

		ItemStack item = new ItemStack(material);

		ItemMeta meta = item.getItemMeta();

		meta.setDisplayName(name);
		meta.setLore(lore);

		item.setItemMeta(meta);

		return item;

	}
	
	public String getStringUniqueID(String ID) {
		
		String string_uuid = plugin.ic.getString(String.format("%s.uuid", ID));
		
		return string_uuid;
		
	}
	
	public void setName(String name) {
		
		name = translateColor(name);
		
		plugin.ic.set(String.format("%s.name", ID),name);
		
		plugin.reload();
		
	}
	
	public void setMaterial(String item_ID) {

		plugin.ic.set(String.format("%s.material", ID), item_ID);
		
		plugin.reload();
		
	}
	
	public void addLore(String lore) {
		
		List<String> list = plugin.ic.getStringList(String.format("%s.lore", ID));
		
		lore = translateColor(lore);
		
		if(list.get(0).equals("你可以輸入/item lore來使用指令設置物品敘述。")) {
		
			list.set(0, lore);
		
		}
		
		plugin.ic.set(String.format("%s.lore", ID), list);
		
		plugin.reload();
		
	}
	
	public void setLore(String lore,int line) {
		
		List<String> list = plugin.ic.getStringList(String.format("%s.lore", ID));
		
		lore = translateColor(lore);
		
		list.set(line,lore);
		
		plugin.ic.set(String.format("%s.lore", ID), list);
		
		plugin.reload();
		
	}
	
	private String translateColor(String text) {
		
		return ChatColor.translateAlternateColorCodes('&', text);
		
	}
	
	public static Set<String> getItemList(){
		
		Set<String> set = plugin.ic.getKeys(false);
		
		return set;
		
	}
	
	public void removeLore(int line) {
		
		List<String> list = plugin.ic.getStringList(String.format("%s.lore", ID));
		
		list.remove(line);
		
		plugin.ic.set(String.format("%s.lore", ID), list);
		
		plugin.reload();
		
	}
	
	public void setType(String type) {
		
		plugin.ic.set(String.format("%s.type", ID), type);
		
		plugin.reload();
		
	}
	
	public void setVersion(double d) {
		
		plugin.ic.set(String.format("%s.type", ID), d);
		
		plugin.reload();
		
	}
	
	public double getVersion() {
		
		double version = plugin.ic.getDouble(String.format("%s.type", ID));
		
		return version;
		
	}
	
	
	
}
