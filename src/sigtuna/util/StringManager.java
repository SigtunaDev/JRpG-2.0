package sigtuna.util;

import java.net.URL;

import org.apache.commons.io.FileUtils;

import sigtuna.main.Main;

public class StringManager {
	
	static Main plugin = Main.plugin;
	
	public static void init() {
		
		try {
		
			String lang = plugin.cc.getString("lang");

			if(lang.equals("zh_TW")) {

				FileUtils.copyURLToFile(new URL("https://pastebin.com/raw/9iZFLGFb"), plugin.lang);
				
				System.out.println("語言已被設置為：zh_TW");
				
			}else if(lang.equals("en_US")) {
				
				FileUtils.copyURLToFile(new URL("https://pastebin.com/raw/tw4kVKWF"), plugin.lang);
				
				System.out.println("語言已被設置為：en_US");
				
			}
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		
	}
	
	public static String getTagMessage(String str) {
		
		return plugin.lc.getString(str);
		
	}
	
	public static String INFO_TEXT_SUCCESS = plugin.lc.getString("INFO_TEXT_SUCCESS");
	public static String INFO_ITEM_INIT_NAME = plugin.lc.getString("INFO_ITEM_INIT_NAME");
	public static String INFO_ITEM_INIT_LORE = plugin.lc.getString("INFO_ITEM_INIT_LORE");
	public static String INFO_ITEM_INIT_SKILL = plugin.lc.getString("INFO_ITEM_INIT_SKILL");
	public static String INFO_ITEM_INIT_TAG_SKILL = plugin.lc.getString("INFO_ITEM_INIT_TAG_SKILL");
	public static String INFO_ITEM_INIT_CLASS = plugin.lc.getString("INFO_ITEM_INIT_CLASS");
	public static String INFO_ITEM_TAG_ITEM_LIST = plugin.lc.getString("INFO_ITEM_TAG_ITEM_LIST");
	public static String INFO_ITEM_NAME_CHANGE = plugin.lc.getString("INFO_ITEM_NAME_CHANGE");
	public static String INFO_ITEM_LORE_ADD = plugin.lc.getString("INFO_ITEM_LORE_ADD");
	public static String INFO_ITEM_LORE_SET = plugin.lc.getString("INFO_ITEM_LORE_SET");
	public static String INFO_ITEM_LORE_PRINT_HOVER = plugin.ic.getString("INFO_ITEM_LORE_PRINT_HOVER");
	public static String INFO_ITEM_LORE_DELETE = plugin.lc.getString("INFO_ITEM_LORE_DELETE");
	public static String INFO_ITEM_SET_MATERIAL = plugin.lc.getString("INFO_ITEM_SET_MATERIAL");
	public static String INFO_ITEM_LINE_BETWEEN = plugin.lc.getString("INFO_ITEM_LINE_BETWEEN");
	public static String INFO_ITEM_ADDED_TO_INVENTORY = plugin.lc.getString("INFO_ITEM_ADDED_TO_INVENTORY");
	public static String INFO_ITEM_UUID_SEARCH = plugin.lc.getString("INFO_ITEM_UUID_SEARCH");
	public static String ERROR_ITEM_NOT_EXIST = plugin.lc.getString("ERROR_ITEM_NOT_EXIST");
	
	public static String INFO_COMMAND_CREATE = plugin.lc.getString("INFO_COMMAND_CREATE");
	public static String INFO_COMMAND_EXIST = plugin.lc.getString("INFO_COMMAND_EXIST");
	public static String ERROR_COMMAND_NOPERMISSION = plugin.lc.getString("ERROR_COMMAND_NOPERMISSION");
	public static String ERROR_COMMAND_NOTPLAYERUSE = plugin.lc.getString("ERROR_COMMAND_NOTPLAYERUSE");
	public static String ERROR_COMMAND_ERROR = plugin.lc.getString("ERROR_COMMAND_ERROR");

}
