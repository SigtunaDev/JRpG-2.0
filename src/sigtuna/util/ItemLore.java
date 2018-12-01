package sigtuna.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import net.md_5.bungee.api.ChatColor;
import sigtuna.main.Main;

public class ItemLore {
	
	String ID = null;
	
	public static Main plugin = Main.plugin;
	
	public ItemLore(String ID) {
		
		this.ID = ID;
		
	}
	
	public List<String> getLore(List<String> lore) {
		
		List<String> list = new ArrayList<String>();
		
		ItemData data = new ItemData(ID);

		list.add(ChatColor.YELLOW + StringManager.INFO_ITEM_LINE_BETWEEN);

		// 物品敘述
		list.addAll(lore);

		list.add(ChatColor.YELLOW + StringManager.INFO_ITEM_LINE_BETWEEN);

		// 物品屬性清單
		list.addAll(infoClassLoreMaker(data.getInfoClass()));

		list.add(ChatColor.YELLOW + StringManager.INFO_ITEM_LINE_BETWEEN);

		// 物品技能清單
		list.addAll(skillLoreMaker(data.getInfoSkillName(ID),ID));

		list.add(ChatColor.YELLOW + StringManager.INFO_ITEM_LINE_BETWEEN);
		
		list.add(ChatColor.BLACK + data.getStringUniqueID(ID));

		return list;
		
	}
	

	public static List<String> infoClassLoreMaker(String infoClass) {

		List<String> list = new ArrayList<String>();

		if (infoClass.equals("none")) {

			list.add(ChatColor.AQUA + StringManager.INFO_ITEM_INIT_CLASS);

		}

		return list;

	}
	
	public static List<String> moduleMaker(){
		
		List<String> list = new ArrayList<String>();
		
		return list;
		
	}

	// unfinished
	public static List<String> skillLoreMaker(Set<String> skill, String ID) {
		
		List<String> list = new ArrayList<String>();

		String[] skill_array = new String[200];

		skill.toArray(skill_array);

		if (skill.size() == 0 || plugin.ic.getString(String.format("%s.info.skill",ID)).equals("none")) {

			list.add(StringManager.INFO_ITEM_INIT_SKILL);

			return list;
		}

		list.add(ChatColor.AQUA + StringManager.INFO_ITEM_INIT_TAG_SKILL);

		for (int i = 0; i < skill.size(); i++) {

			list.add(ChatColor.GOLD + " - " + skill_array[i]);

		}

		list.add(ChatColor.YELLOW + StringManager.INFO_ITEM_LINE_BETWEEN);

		return list;

	}
	

}
